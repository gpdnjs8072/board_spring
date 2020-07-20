package com.spring.board.custom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class FailHandler implements AuthenticationFailureHandler{
	private String errorMsg;
	private String defaultFailureUrl;
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
//		String mem_id=request.getParameter("mem_id");
//		String mem_pwd=request.getParameter("mem_pwd");
//		String errorMsg2=exception.getMessage();
//		System.out.println(errorMsg2);
//		request.setAttribute("errorMsg", errorMsg2);
//		response.sendRedirect(request.getContextPath()+"/member/loginfail.do?error="+errorMsg2);
//		request.setAttribute("error", "error");
		if(exception instanceof AuthenticationServiceException){
			
			response.sendRedirect(request.getContextPath() + "/member/login_form.do?error=1");

		}

		

		if(exception instanceof BadCredentialsException){
			request.setAttribute("error", "2");
			request.getRequestDispatcher("/member/login_form.do?error=2").forward(request, response);
//			response.sendRedirect(request.getContextPath() + "/member/login_form.do?error=2");

		}

		

		if(exception instanceof LockedException){

			response.sendRedirect(request.getContextPath() + "/member/login_form.do?error=3");

		}



	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg(String errorMsg) {
		this.errorMsg=errorMsg;
	}
	
	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}
	
	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl=defaultFailureUrl;
	}

}
