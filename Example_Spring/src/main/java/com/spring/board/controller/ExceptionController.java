package com.spring.board.controller;


import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
	@RequestMapping("/exception/denial")
	public String denial() {
		return "exception/denial.tiles";
	}
	
	@RequestMapping("/exception/404")
	public String error400(){
		return "exception/404.tiles";
	}
	@RequestMapping("/exception/500")
	public String error500(){
		return "exception/500.tiles";
	}
	@RequestMapping("/exception/error")
	public String error(){
		return "exception/error.tiles";
	}
	
	@ExceptionHandler(DataAccessException.class)
	public ModelAndView dataAccess(DataAccessException dae) {
		ModelAndView mView=new ModelAndView();
		mView.addObject("exception", dae);
		// view page 설정
		mView.setViewName("error/data_access");   
		return mView;
	}
}
