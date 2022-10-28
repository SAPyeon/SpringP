package org.sap.controller;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.sap.mapper.KakaoLoginBO;
import org.sap.mapper.NaverLoginBO;
import org.sap.model.MemberVO;
import org.sap.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private final MemberService memberService;
	private NaverLoginBO naverLoginBO;
	private KakaoLoginBO kakaoLoginBO;
	private String apiResult = null;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	@Autowired
	private void setKakaoLoginBO(KakaoLoginBO kakaoLoginBO) {
		this.kakaoLoginBO = kakaoLoginBO;
	}
	// 회원가입
	@RequestMapping(value = "/member/signup", method = RequestMethod.GET)
	public void SignUp() {

	}

	@RequestMapping(value = "/member/signup", method = RequestMethod.POST)
	public String SignUpPost(MemberVO mvo) {
		memberService.signup(mvo);
		return "redirect:/";
	}

	// 로그인
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public void login(Model model, HttpSession session) {
		
		/* 네아로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		/* 인증요청문 확인 */
		System.out.println("네이버:" + naverAuthUrl);
		/* 객체 바인딩 */
		model.addAttribute("urlNaver", naverAuthUrl);
		
		/* 카아로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String kakaoAuthUrl = kakaoLoginBO.getAuthorizationUrl(session);
		/* 인증요청문 확인 */
		System.out.println("카카오:" + kakaoAuthUrl);
		/* 객체 바인딩 */
		model.addAttribute("urlKakao", kakaoAuthUrl);
		
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String loginPost(MemberVO mvo, HttpSession session) {
		
		try {
			session.setAttribute("loginName", memberService.login(mvo).getName());
			memberService.login(mvo);
			return "redirect:/";
		}catch(Exception e) {
			return "redirect:/member/login";
		}
	}
	
	// 네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/callbackNaver", method = { RequestMethod.GET, RequestMethod.POST })
	public String callbackNaver(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, MemberVO mvo)
			throws Exception {
		System.out.println("로그인 성공 callbackNaver");
		System.out.println("콜백세션: "+ state);
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		// 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj;

		jsonObj = (JSONObject) jsonParser.parse(apiResult);
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		System.out.println(response_obj);
		// 프로필 조회
		//String email = (String) response_obj.get("email");
		String name = (String) response_obj.get("name");
		// 세션에 사용자 정보 등록
		session.setAttribute("signIn", apiResult);
		//session.setAttribute("email", email);
		session.setAttribute("loginName", name);
		mvo.setId((String) response_obj.get("id"));
		
		//아이디가 테이블에 있는지 조회 후 없으면 insert
		if(memberService.findById(mvo.getId())==null) {
			mvo.setPassword((String) response_obj.get("email"));
			mvo.setName(name);
			memberService.signup(mvo);
		}
		return "redirect:/";
	}
	//카카오로그인 성공시 callback
	@RequestMapping(value = "/callbackKakao", method = { RequestMethod.GET, RequestMethod.POST })
	public String callbackKakao(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) 
			throws Exception {
		System.out.println("로그인 성공 ");
		OAuth2AccessToken oauthToken;
		oauthToken = kakaoLoginBO.getAccessToken(session, code, state);	
		// 로그인 사용자 정보를 읽어온다
		apiResult = kakaoLoginBO.getUserProfile(oauthToken);
				
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj;
		
		jsonObj = (JSONObject) jsonParser.parse(apiResult);
		JSONObject response_obj = (JSONObject) jsonObj.get("kakao_account");	
		JSONObject response_obj2 = (JSONObject) response_obj.get("profile");
		System.out.println(response_obj);
		// 프로필 조회
		String email = (String) response_obj.get("email");
		String name = (String) response_obj2.get("nickname");
		// 세션에 사용자 정보 등록
		// session.setAttribute("islogin_r", "Y");
		session.setAttribute("signIn", apiResult);
		session.setAttribute("email", email);
		session.setAttribute("name", name);

		return "redirect:/";
	}
    
	
	// 로그아웃
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		logger.info("로그아웃되었습니다.");
		return "redirect:/";
	}

	

}
