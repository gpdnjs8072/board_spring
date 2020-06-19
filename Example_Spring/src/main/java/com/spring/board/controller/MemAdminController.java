package com.spring.board.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.dto.MemberDto;
import com.spring.board.service.MemAdminService;

@Controller
public class MemAdminController {
	@Autowired
	MemAdminService service;
	
	@RequestMapping(value="/memberAdmin/memberList",method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView memberList(ModelAndView mView, HttpServletRequest request,
									@RequestParam(defaultValue="") String searchOption,
									@RequestParam(defaultValue="") String mem_authCode,
									@RequestParam(defaultValue="") String keyword,
									@RequestParam(defaultValue="1") int curPage
									) throws Exception{
		service.getList(request, searchOption, keyword, mem_authCode, curPage);
		mView.setViewName("memberAdmin/list.tiles");
		return mView;
	}
	
	@RequestMapping("/memberAdmin/updateCode_form")
	public ModelAndView updateCode_form (ModelAndView mView,@RequestParam String mem_id,HttpServletRequest request) {
		service.memberCode(request, mem_id);
		mView.setViewName("member/updateCode_form.tiles");
		return mView;
	}
	
	@RequestMapping("/memberAdmin/updateCode")
	public ModelAndView updateCode(ModelAndView mView,@ModelAttribute("dto") MemberDto dto,HttpServletRequest request) {
		service.changeCode(request, dto);
		mView.setViewName("memberAdmin/updateCode.tiles");
		return mView;
	}
}
