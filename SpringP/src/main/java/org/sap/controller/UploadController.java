package org.sap.controller;

import java.io.File;
import java.io.FileInputStream;
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
import org.sap.model.ImageVO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;

import net.coobird.thumbnailator.Thumbnailator;

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
		
		ArrayList<ImageVO> list = new ArrayList();
		System.out.println("111111");

		if(file != null) {
			if(file.getSize() >0 && StringUtils.isNotBlank(file.getName())) {
				if(file.getContentType().toLowerCase().startsWith("image/")) {
				    try{
				    	ImageVO imageVO = new ImageVO();
			            String fileName = file.getOriginalFilename();
			            
			            byte[] bytes = file.getBytes();
			            // 폴더 경로
			    		String uploadPath = "D:\\01-STUDY\\upload";
			            //String uploadPath = req.getSession().getServletContext().getRealPath("D:\\01-STUDY\\upload"); //저장경로
			            System.out.println("uploadPath:"+uploadPath);
			            
			            File uploadFile = new File(uploadPath, getFolder());
			            
			            if(!uploadFile.exists()) { // uploadPath가 존재하지 않으면
			            	uploadFile.mkdir(); // 부모디렉토리를 포함해 모든 디렉토리 생성
			            }
			           
			            System.out.println("경로설정 = "+uploadFile);
			            String fileName2 = UUID.randomUUID().toString();
			            uploadPath = uploadFile.getPath() + "/" + fileName2 +fileName;
			            
			            // 어느폴더에, 어떤파일이름으로
						File saveFile = new File(uploadFile, fileName2+fileName);
						System.out.println("보낼파일 = "+saveFile);
						// 변수 리스트에 저장
						imageVO.setUploadPath(getFolder());
						imageVO.setFileName(fileName);
						imageVO.setUuid(fileName2);
						imageVO.setFullPath(uploadPath);
						imageVO.setImage(true);
						
						file.transferTo(saveFile);
						
			            out = new FileOutputStream(new File(uploadPath));
			            out.write(bytes);
			            
			            printWriter = resp.getWriter();
			            String fileUrl = "http://localhost:8080/display?fileName=" + fileName2+fileName; // 작성화면
			            //String fileUrl = req.getContextPath() + uploadPath; //url경로
			            
			            System.out.println("fileUrl :" + fileUrl);
			            
			            JsonObject json = new JsonObject();
			            json.addProperty("uploaded", 1);
			            json.addProperty("fileName", fileName);
			            json.addProperty("url", fileUrl);
			            
			            String callback = req.getParameter("CKEditorFuncNum");
			            System.out.println("콜백 = "+ callback);
			            //printWriter.print(json);
			            printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
			                    + callback
			                    + ",'"
			                    + fileUrl
			                    + "','이미지를 업로드 하였습니다.'"
			                    + ")</script>");
			            
			            System.out.println(json);
			            
			            list.add(imageVO);
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
	public ResponseEntity<byte[]> getFile(String fileName) {

		System.out.println(fileName);
		
		File file = new File("D:\\01-STUDY\\upload\\" + getFolder() + "\\"+fileName);

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
		Resource resource = new FileSystemResource("D:\\01-STUDY\\upload\\" + fileName);
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
