package com.spring.board.custom;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class LogoutHandler extends SimpleUrlLogoutSuccessHandler{
	private static final Logger logger = LoggerFactory.getLogger(LogoutHandler.class);
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		System.out.println("로그아웃 핸들러");
	    if (authentication != null && authentication.getDetails() != null) {
            try {
                 request.getSession().invalidate();
                 System.out.println("로그아웃완료");
            } catch (Exception e) {
            	ByteArrayOutputStream out = new ByteArrayOutputStream();
    			PrintStream printStream = new PrintStream(out);
    	
    			e.printStackTrace(printStream);
    			String stackTraceString=out.toString();
    		    logger.error("error : "+stackTraceString);
            }
        } 
	    setDefaultTargetUrl("/");

	   
		super.onLogoutSuccess(request, response, authentication);
	}
}
