package com.spring.board.service;

import javax.servlet.http.HttpServletRequest;

import com.spring.board.dto.MemberDto;

public interface MemAdminService {
	//회원 목록
	public void getList(HttpServletRequest request,String searchOption,String keyword,String mem_authCode,int curPage);
	//회원 상태/권한 수정 폼
	public void memberCode(HttpServletRequest request,String mem_id);
	//회원 상태/권한 수정
	public void changeCode(HttpServletRequest request,MemberDto dto);
	
}
