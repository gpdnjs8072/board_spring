package com.spring.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/list")
	public ModelAndView list(ModelAndView mView,HttpServletRequest request) {
		
		mView.setViewName("board/list.tiles");
		return mView;
	}
	
	@RequestMapping("/board/insert_form")
	public ModelAndView insert_form(ModelAndView mView,HttpServletRequest request) {
		
		mView.setViewName("board/insert_form.tiles");
		return mView;
	}
	
	@RequestMapping("/board/insert")
	public ModelAndView insert(ModelAndView mView,HttpServletRequest request) {
		
		mView.setViewName("board/insert.tiles");
		return mView;
	}
}
