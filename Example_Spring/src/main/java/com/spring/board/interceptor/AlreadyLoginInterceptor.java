package com.spring.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AlreadyLoginInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
		// 세션 객체 생성
		HttpSession session = request.getSession();
		// 로그인 x
		if(session.getAttribute("mem_id")==null) {  
			// 요청페이지로 이동
			return true;
		// 로그인 o
		} else {
			response.sendRedirect(request.getContextPath()+"/index.do");
			// 요청페이지로 이동하지 x
			return false;
		}
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
						Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}
