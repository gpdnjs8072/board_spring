package com.spring.board.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
		// 세션 객체 생성
		HttpSession session = request.getSession();
		// 세션에 id가 null이면
		if(session.getAttribute("mem_id") == null) {
			//url
			String url=request.getRequestURI();
			//파라미터
			String query=request.getQueryString();
			String encodedUrl=null;
			if(query==null) {  //파라미터가 없다면
    			encodedUrl=URLEncoder.encode(url);
    		}else { //파라미터가 있다면
				encodedUrl=URLEncoder.encode(url+"?"+query);
			}
			// 로그인 페이지로 이동
			response.sendRedirect(request.getContextPath()+"/member/login_form.do?url="+encodedUrl);
			// 요청페이지로 이동하지 x
			return false;
		// null이 아니면
		} else {
			// 요청페이지로 이동
			return true;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
						Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}
