package org.sap.controller;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.sap.component.KakaoLogin;
import org.sap.component.NaverLogin;
import org.sap.model.MemberDto;
import org.sap.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private final NaverLogin naverLogin;
	private final KakaoLogin kakaoLogin;
	private String apiResult = null;
	

	// 회원가입
	@RequestMapping(value = "/member/signup", method = RequestMethod.GET)
	public void SignUp() {

	}

	@RequestMapping(value = "/member/signup", method = RequestMethod.POST)
	public String SignUpPost(MemberDto mdto) {
		memberService.signup(mdto);
		return "redirect:/";
	}

	// 로그인
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public void login(Model model, HttpSession session) {
		
		/* 네아로 인증 URL을 생성하기 위하여 naverLogin클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLogin.getAuthorizationUrl(session);
		/* 인증요청문 확인 */
		System.out.println("네이버:" + naverAuthUrl);
		/* 객체 바인딩 */
		model.addAttribute("urlNaver", naverAuthUrl);
		
		/* 카아로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String kakaoAuthUrl = kakaoLogin.getAuthorizationUrl(session);
		/* 인증요청문 확인 */
		System.out.println("카카오:" + kakaoAuthUrl);
		/* 객체 바인딩 */
		model.addAttribute("urlKakao", kakaoAuthUrl);
		
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String loginPost(MemberDto mdto, HttpSession session) {
		
		try {
			session.setAttribute("loginName", memberService.login(mdto).getName());
			memberService.login(mdto);
			return "redirect:/";
		}catch(Exception e) {
			return "redirect:/member/login";
		}
	}
	
	// 네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/callbackNaver", method = { RequestMethod.GET, RequestMethod.POST })
	public String callbackNaver(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, MemberDto mdto)
			throws Exception {
		System.out.println("로그인 성공 callbackNaver");
		System.out.println("콜백세션: "+ state);
		OAuth2AccessToken oauthToken;
		oauthToken = naverLogin.getAccessToken(session, code, state);
		// 로그인 사용자 정보를 읽어온다.
		apiResult = naverLogin.getUserProfile(oauthToken);
		
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
		mdto.setId("N+"+(String) response_obj.get("id"));
			
		//아이디가 테이블에 있는지 조회 후 없으면 insert
		if(memberService.findById(mdto.getId())==null) {
			mdto.setPassword((String) response_obj.get("email"));
			mdto.setName(name);
			memberService.signup(mdto);
		}
		return "redirect:/";
	}
	//카카오로그인 성공시 callback
	@RequestMapping(value = "/callbackKakao", method = { RequestMethod.GET, RequestMethod.POST })
	public String callbackKakao(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, MemberDto mdto) 
			throws Exception {
		System.out.println("로그인 성공 ");
		OAuth2AccessToken oauthToken;
		oauthToken = kakaoLogin.getAccessToken(session, code, state);	
		// 로그인 사용자 정보를 읽어온다
		apiResult = kakaoLogin.getUserProfile(oauthToken);
				
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj;
		
		jsonObj = (JSONObject) jsonParser.parse(apiResult);
		JSONObject response_obj = (JSONObject) jsonObj.get("kakao_account");	
		JSONObject response_obj2 = (JSONObject) response_obj.get("profile");
		System.out.println(jsonObj);
		System.out.println(response_obj);
		System.out.println(response_obj2);
		// 프로필 조회
		
		String name = (String) response_obj2.get("nickname");
		// 세션에 사용자 정보 등록
		session.setAttribute("signIn", apiResult);
		session.setAttribute("loginName", name);
		
		mdto.setId("K+"+(Long) jsonObj.get("id"));
		//아이디가 테이블에 있는지 조회 후 없으면 insert
		if(memberService.findById(mdto.getId())==null) {
			if((String) response_obj.get("email")!=null) {
				String email = (String) response_obj.get("email");
				session.setAttribute("email", email);
				mdto.setPassword((String) response_obj.get("email"));
			}
			mdto.setName(name);
			memberService.signup(mdto);
		}
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
