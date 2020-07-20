package com.spring.board;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	//로그인 폼 이동
	@RequestMapping("/member/login_form")
	public String login3() {
		
		return "member/login_form.tiles";
	}
	
	//로그인 실패시 이동
	@RequestMapping("/member/loginfail")
	public String loginFail() {
		return "member/loginfail.tiles";
	}
//	//로그아웃
//	@RequestMapping("/member/private/logout")
//	public String logout() {
//		return "member/private/logout";
//	}
	
}
