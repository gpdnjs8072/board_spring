package com.spring.board.mapper;

import java.util.List;
import java.util.Map;

import com.spring.board.dto.MemberDto;

public interface MemberMapper {
	
	void insert(MemberDto dto) throws Exception;
	
	String isExistId(String mem_id) throws Exception;
	
	String isExistEmail(String mem_email) throws Exception;
	
	MemberDto isValid(MemberDto dto) throws Exception;
	
	String selectPwdHash(String mem_id) throws Exception;
	
	void updateCount(String mem_id) throws Exception;
	
	void updateOriCount(String mem_id) throws Exception;
	
	void updateStateCode(String mem_id) throws Exception;
	
	MemberDto selectMember(String mem_id) throws Exception;
	
	String searchId(MemberDto dto) throws Exception;
	
	String searchPwd(MemberDto dto) throws Exception;
	
	void changePwd(MemberDto dto) throws Exception;
	
	void updateMember(MemberDto dto) throws Exception;
	
	MemberDto detailMember(String mem_id) throws Exception;
	
	void deleteMember(String mem_id) throws Exception;
	
	List<MemberDto> selectMemberList(Map<String, Object> map) throws Exception;   // 확인
	  
	int count(Map<String, Object> map) throws Exception;     //확인
 	
	void updateCode(MemberDto dto) throws Exception;
}
