package com.spring.board.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.board.util.ClientUtil;


public class LoggerInterceptor extends HandlerInterceptorAdapter{
	private static final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
						Object handler, ModelAndView modelAndView) throws Exception {
		String url=request.getRequestURI();
		String ip=ClientUtil.getRemoteIP(request);
		logger.info("Client IP : "+ip+" , URL : "+url);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(ex!=null) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream printStream = new PrintStream(out);
	
			ex.printStackTrace(printStream);
			String stackTraceString=out.toString();
		    
			logger.error("error msg : "+stackTraceString);
		}
	}
}
