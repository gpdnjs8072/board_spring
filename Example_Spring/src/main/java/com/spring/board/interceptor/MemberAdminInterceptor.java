package com.spring.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MemberAdminInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
		// 세션 객체 생성
		HttpSession session = request.getSession();
			// 권한코드가 회원 관리자이면
			if(session.getAttribute("mem_authCode").equals("002")) {  
				// 요청페이지로 이동
				return true;
			// 관리자가 아니면
			} else {
				response.sendRedirect(request.getContextPath()+"/exception/denial.do");
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
