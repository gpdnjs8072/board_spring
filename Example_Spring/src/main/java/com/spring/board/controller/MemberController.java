package com.spring.board.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.dto.MemberDto;
import com.spring.board.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService service;
	
	//회원 가입 폼
	@RequestMapping("/member/signup_form")
	public ModelAndView singup_form(HttpServletRequest request) {

		return new ModelAndView("member/signup_form.tiles");
	}
	
	 //아이디 중복확인
	 @ResponseBody
	 @RequestMapping(value="/member/existId",method=RequestMethod.GET) 
	 public Map<String, Object> existId(HttpServletRequest request,@RequestParam String mem_id){
		 Map<String, Object> map=service.isExistId(mem_id);
		 return  map;
	}
	 
	 //이메일 중복확인
	 @ResponseBody
	 @RequestMapping(value="/member/existEmail",method=RequestMethod.GET) 
	 public Map<String, Object> existEmail(HttpServletRequest request,@RequestParam String mem_email){
		 Map<String, Object> map=service.isExistEmail(mem_email);
		 return  map;
	}
	
	
	//회원가입
	@RequestMapping(value="/member/signup",method=RequestMethod.POST)
	public ModelAndView signup(ModelAndView mView,@ModelAttribute("dto") MemberDto dto,HttpServletRequest request) {
		service.addMember(dto,request);
		mView.setViewName("member/signup");
		return mView;
	}
	
	
	//로그인 폼
	@RequestMapping("/member/login_form")
	public ModelAndView login_form(HttpServletRequest request) {
		//url
		String url=request.getParameter("url");
		request.setAttribute("url", url);
		
		return new ModelAndView("member/login_form.tiles");
	}
	
	//로그인
	@RequestMapping("/member/login")
	public ModelAndView login(ModelAndView mView,@ModelAttribute("dto") MemberDto dto,HttpServletRequest request) {
		//url
		String url=request.getParameter("url");
		if(url==""||url==null){//url이 없으면
			request.setAttribute("url", "no");
		}else {
			
			String encodedUrl=URLEncoder.encode(url);
			request.setAttribute("encodedUrl", encodedUrl);
			request.setAttribute("url", url);
		}
	
		service.validMember(dto, request);
		mView.setViewName("member/login");
		return mView;
	}
	
	//로그아웃
	@RequestMapping("/member/private/logout")
	public ModelAndView logout(HttpSession session,ModelAndView mView) {
		if(session.getAttribute("mem_id")!=null) {
			session.invalidate();		
		}
		mView.setViewName("member/private/logout");
		return mView;
	}
	
	//아이디찾기 폼
	@RequestMapping("/member/searchId_form")
	public ModelAndView searchId_form() {
		return new ModelAndView("member/searchId_form.tiles");
	}
	
	//아이디찾기
	@RequestMapping(value="/member/searchId",method=RequestMethod.POST)
	public ModelAndView searchId(ModelAndView mView,@ModelAttribute("dto") MemberDto dto,HttpServletRequest request) {
		service.getId(dto, request);
		mView.setViewName("member/searchId.tiles");
		return mView;
	}
	
	//비밀번호 찾기 폼
	@RequestMapping("/member/searchPwd_form")
	public ModelAndView searchPwd_form() {
		return new ModelAndView("member/searchPwd_form.tiles");
	}
	
	//비밀번호 찾기
	@RequestMapping(value="/member/newPwd_form",method=RequestMethod.POST)
	public ModelAndView newPwd_form(ModelAndView mView,@ModelAttribute("dto") MemberDto dto,HttpServletRequest request) {
		boolean isExistMember=service.isExistMember(dto, request);
		if(isExistMember) {
			mView.setViewName("member/newPwd_form.tiles");			
		}else {
			mView.setViewName("member/searchPwd");
			request.setAttribute("isExist", "false");
		}
		return mView;
	}
	
	//새 비밀번호 폼
	@RequestMapping(value="/member/newPwd",method=RequestMethod.POST)
	public ModelAndView newPwd(ModelAndView mView,@ModelAttribute MemberDto dto,HttpServletRequest request) {
		service.changeNewPwd(dto, request);
		mView.setViewName("member/newPwd");
		return mView;
	}
	
	//회원 정보 폼
	@RequestMapping(value="/member/private/info_form")
	public ModelAndView info_form(ModelAndView mView,HttpServletRequest request) {
		service.detailMember(request);
		mView.setViewName("member/private/info_form.tiles");
		return mView;
	}
	
	//회원 정보 수정
	@RequestMapping(value="/member/private/info", method=RequestMethod.POST )
	public ModelAndView info(ModelAndView mView,@ModelAttribute("dto") MemberDto dto,HttpServletRequest request) {
		service.updateMember(dto, request);
		mView.setViewName("member/private/info.tiles");
		return mView;
	}
	
	//회원 탈퇴
	@RequestMapping("/member/private/delete")
	public ModelAndView delete(ModelAndView mView,HttpServletRequest request) {
		service.deleteMember (request);
		mView.setViewName("/member/private/delete");
		return mView;
	}
}
