package com.spring.board.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.spring.board.dto.MemberDto;


public interface MemberService {
	//회원가입 메소드
	public void addMember(MemberDto dto,HttpServletRequest request);
	//아이디 중복체크
	public Map<String,Object> isExistId(String mem_id);
	//회원 아이디,비밀번호 확인 (로그인)
	public void validMember(MemberDto dto,HttpServletRequest request);
	//아이디 찾기
	public void getId(MemberDto dto,HttpServletRequest request);
	//비밀번호 찾기
	public boolean isExistMember(MemberDto dto,HttpServletRequest request);
	//새비밀번호
	public void changeNewPwd(MemberDto dto,HttpServletRequest request);
	//회원 정보 수정 폼
	public void detailMember(HttpServletRequest request);
	//회원 정보 수정
	public void updateMember(MemberDto dto,HttpServletRequest request);
	//회원 탈퇴
	public void deleteMember(HttpServletRequest request);
	
}
