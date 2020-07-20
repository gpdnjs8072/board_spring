package com.spring.board.custom;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;


public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private static final Logger logger = LoggerFactory.getLogger(SuccessHandler.class);
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	private HttpSession session=null;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		String mem_id=authentication.getName();
		try {

			if(!mem_id.equals("anonymousUser")) {
				session=request.getSession();
				session.setAttribute("mem_id",mem_id);
				
			}
				
		}catch (Exception e) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream printStream = new PrintStream(out);
			e.printStackTrace(printStream);
			String stackTraceString=out.toString();
		    logger.error("error : "+stackTraceString);
		}
		resultRedirectStrategy(request, response, authentication);
	}
		
	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		session=request.getSession();
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		List<String> roleNames = new ArrayList<>();
		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());

		});

		//로그인을 하지 않은 상태에서 다른 경로로 들어갔을 때  로그인 전의 페이지로 이동
		if (savedRequest != null) {  
			if(roleNames.contains("ROLE_USER")) {
				session.setAttribute("mem_authCode", "001");
			}else if(roleNames.contains("ROLE_ADMIN")){
				session.setAttribute("mem_authCode", "002");
			}else if(roleNames.contains("ROLE_MANAGER")){
				session.setAttribute("mem_authCode", "003");
			}
			String targetUrl = savedRequest.getRedirectUrl();
			redirectStratgy.sendRedirect(request, response, targetUrl);
			
		} else { //로그인 폼으로 이동해서 로그인했을 때 index로 이동
			if(roleNames.contains("ROLE_USER")) {
				session.setAttribute("mem_authCode", "001");
			}else if(roleNames.contains("ROLE_ADMIN")){
				session.setAttribute("mem_authCode", "002");
			}else if(roleNames.contains("ROLE_MANAGER")){
				session.setAttribute("mem_authCode", "003");
			}
			redirectStratgy.sendRedirect(request, response, "/index.do");
		}

	}
}
