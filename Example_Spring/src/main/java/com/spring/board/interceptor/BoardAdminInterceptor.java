package com.spring.board.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BoardAdminInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
		// 세션 객체 생성
		HttpSession session = request.getSession();
		
		String query=request.getQueryString();
	

		boolean isNotice=false;
		if(query!=null) { //파라미터가 있다면
			isNotice=query.contains("board_typeCode=201");  
		}
		
		if(isNotice) { //파라미터가 공지사항이면
			
			// 권한코드가 게시판관리자이면
			if(session.getAttribute("mem_authCode").equals("003")) {  
				// 요청페이지로 이동
				return true;
			} else { // 관리자가 아니면
				response.sendRedirect(request.getContextPath()+"/exception/denial.do");
				// 요청페이지로 이동하지 x
				return false;
			}
		}else { //공지사항이 아니면
			return true;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
						Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}
