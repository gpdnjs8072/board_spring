package com.spring.board.controller;


import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

//	
//	@RequestMapping("/exception/404")
//	public String error400(){
//		return "exception/404.tiles";
//	}
//	@RequestMapping("/exception/500")
//	
//	public String error500(){
//		return "exception/500.tiles";
//	}

//	
	@ExceptionHandler(DataAccessException.class)
	public ModelAndView dataAccess(DataAccessException dae) {
		ModelAndView mView=new ModelAndView();
		mView.addObject("exception", dae);
		// view page 설정
		mView.setViewName("error/data_access.tiles");   
		return mView;
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String runtimeException() {
		return "exception/error.tiles";
	}
	
	@ExceptionHandler(Exception.class)
	public String exception() {
		return "exception/error.tiles";
	}

}
