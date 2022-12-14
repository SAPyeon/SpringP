package org.sap.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.sap.model.ImageDto;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;


@Controller
public class UploadController {

	// 날짜폴더만들기
	private String getFolder() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		return str.replace("-", "\\");
	}

	// 이미지파일 유효성검사
	public boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@RequestMapping(value = "/uploadAjaxAction", method = RequestMethod.POST)
	public void uploadAjaxPost(HttpServletRequest req, HttpServletResponse resp, MultipartHttpServletRequest multiFile) throws IOException {
		
		JsonObject jsonObject = new JsonObject();
		PrintWriter printWriter = null;
		OutputStream out = null;
		
		// 인코딩
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		MultipartFile file = multiFile.getFile("upload");
		System.out.println(file);
		
		ArrayList<ImageDto> list = new ArrayList();

		if(file != null) {
			if(file.getSize() >0 && StringUtils.isNotBlank(file.getName())) { // 파일 사이즈가 0보다 크고 파일 이름이 있는경우
				if(file.getContentType().toLowerCase().startsWith("image/")) { // 파일이 이미지파일인지 유효성 검사
				    try{
				    	ImageDto ImageDto = new ImageDto();
			            String fileName = file.getOriginalFilename();
			            
			            byte[] bytes = file.getBytes();
			            // 폴더 경로
			    		String uploadPath = "C:\\Users\\skeh0\\upload"; // 이미지 경로
			            //String uploadPath = req.getSession().getServletContext().getRealPath("D:\\01-STUDY\\upload"); //저장경로
			            System.out.println("uploadPath:"+uploadPath);
			            
			            File uploadFile = new File(uploadPath, getFolder()); // 이미지 경로에 날짜폴더 붙이기
			            
			            if(!uploadFile.exists()) { // uploadPath가 존재하지 않으면
			            	uploadFile.mkdirs(); // 부모디렉토리를 포함해 모든 디렉토리 생성
			            }
			            
			            System.out.println("경로설정 = "+uploadFile); // "D:\01-STUDY/upload\2022\11\10"
			            String fileName2 = UUID.randomUUID().toString();
			            uploadPath = uploadFile.getPath() + "/" + fileName2 +fileName;
			            
			            // 어느폴더에, 어떤파일이름으로
						File saveFile = new File(uploadFile, fileName2+fileName);
						System.out.println("보낼파일 = "+saveFile);
						System.out.println(uploadPath);
						
						// 변수 리스트에 저장
						ImageDto.setUploadPath(getFolder());
						ImageDto.setFileName(fileName);
						ImageDto.setUuid(fileName2);
						ImageDto.setFullPath(uploadPath);
						ImageDto.setImage(true);
						
						file.transferTo(saveFile); // 이미지 파일 저장할 폴더에 전송  // error!!!!
						
			            out = new FileOutputStream(new File(uploadPath));
			            out.write(bytes);
			            
			            // 데이터 보여주는 컨트롤러에 parameter로 넘길때  "\\"는 인코딩의 문제로 보내지지 않음 
			            String datePath = getFolder().replace("\\", "**"); // 전송하기 위한 대체문자 **로 변경 
			            System.out.println("날짜 = "+ datePath);
			            
			            printWriter = resp.getWriter();
			            //데이터를 보여주는 컨트롤러 주소입력, localhost나 도메인이 붙어있어야함
			            String fileUrl = "http://localhost:8080/display?fileName=" +datePath+"**"+ fileName2+fileName; // url 경로
			            System.out.println("fileUrl :" + fileUrl);
			            
			            // 기존 블로그에서 json타입으로 데이터를 불러오는 경우
//			            String fileUrl = req.getContextPath() + uploadPath; //url경로
//			            JsonObject json = new JsonObject();
//			            json.addProperty("uploaded", 1);
//			            json.addProperty("fileName", fileName);
//			            json.addProperty("url", fileUrl);
//			            printWriter.print(json); // json타입으로 불러올경우
//			            System.out.println(json);
			       
			            // javascript로 함수를 불러옴
			            String callback = req.getParameter("CKEditorFuncNum"); //1이라는 숫자가 나와야 이미지 전송가능
			            
			            printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
			                    + callback
			                    + ",'"
			                    + fileUrl
			                    + "','이미지를 업로드 하였습니다.'"
			                    + ")</script>");
			            // 서버 전송을 누르면 알림창으로 "이미지를 업로드 하였습니다." 를 띄우고 이미지주소로 해당 이미지를 불러옴
			           
			            list.add(ImageDto);
			            System.out.println("리스트 = "+list);
			            
			        }catch(IOException e){
			            e.printStackTrace();
			        } finally {
			            if (out != null) {
		                    out.close();
		                }
		                if (printWriter != null) {
		                    printWriter.close();
		                }
			        }
				}
		}
	}
	}// uploadAjaxPost 끝

	// 이미지 주소 생성
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getFile(@RequestParam String fileName) {
		fileName = fileName.replace("**", "\\"); // 대체문자인 ** 를 다시 경로설정하는 \\로 변경
		System.out.println(fileName);
		File file = new File("C:\\Users\\skeh0\\upload\\" +fileName);
		ResponseEntity<byte[]> result = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("이미지주소생성오류");
		}
		return result;
	} // getFile 끝
	
	// 다운로드 주소 생성
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(String fileName) {
		Resource resource = new FileSystemResource("C:\\Users\\skeh0\\upload\\" + fileName);
		// 다운로드 시 파일의 이름을 처리
		String resourceName = resource.getFilename();
		HttpHeaders headers = new HttpHeaders();
		try {
			// 다운로드 파일이름이 한글일 때, 깨지지 않게 하기 위한 설정
			headers.add("Content-Disposition", "attachment;filename=" + new String(resourceName.getBytes("utf-8"), "ISO-8859-1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
}