package com.spring.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.spring.board.dto.MemberDto;
import com.spring.board.dto.PagingDto;

@Service
@Repository
public class MemAdminServiceImpl implements MemAdminService{
	@Autowired
	SqlSession session;

	@Override
	public void getList(HttpServletRequest request,
									String searchOption, String keyword, String mem_authCode,
									int curPage) {
		
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		map.put("mem_authCode",mem_authCode);
		//해당 검색조건과 일치하는 목록의 갯수
		int count=session.selectOne("member.count", map);  
		// 페이지 나누기 관련 처리  PagingDto(count,curPage,페이지당 게시물 수 ,화면당 페이지수)
		PagingDto pagingDto = new PagingDto(count, curPage,3,5);
		int start = pagingDto.getPageBegin();
		int end = pagingDto.getPageEnd();
		map.put("start", start);
		map.put("end", end);
		List<MemberDto> list=session.selectList("member.selectMemberList",map);
		
		Map<String, Object> map2=new HashMap<String, Object>();
		map.put("list", list);
		map.put("count", count); // 레코드의 갯수
		map.put("searchOption", searchOption); // 검색옵션
		map.put("mem_authCode", mem_authCode); // 검색옵션
		map.put("keyword", keyword); // 검색키워드
		map.put("pagingDto", pagingDto);
		
		request.setAttribute("map2", map2);
		
	}

	@Override
	public void memberCode(HttpServletRequest request,String mem_id) {
		MemberDto dto=session.selectOne("member.detailMember",mem_id);
		request.setAttribute("dto", dto);
		
	}

	@Override
	public void changeCode(HttpServletRequest request,MemberDto dto) {
		session.selectOne("member.updateCode",dto);
		
	}

	

	
	
}
