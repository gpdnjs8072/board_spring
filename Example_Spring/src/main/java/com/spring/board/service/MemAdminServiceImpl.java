package com.spring.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.board.dto.MemberDto;
import com.spring.board.dto.PagingDto;

@Service
@Repository
public class MemAdminServiceImpl implements MemAdminService{
	private static final Logger logger = LoggerFactory.getLogger(MemAdminServiceImpl.class);
	@Autowired
	SqlSession session;

	@Override
	public void getList(HttpServletRequest request) {
		
		String searchOption=request.getParameter("searchOption");
		String mem_authCode=request.getParameter("mem_authCode");
		String keyword=request.getParameter("keyword");
		int curPage=1;
		
		if(request.getParameter("curPage")!=null) {
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		
		
		if(keyword=="") {
			keyword=null;
		}
		if(searchOption=="") {
			searchOption=null;
		}
		if(mem_authCode=="") {
			mem_authCode=null;
		}
		
		
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
		map2.put("list", list);
		map2.put("count", count); // 레코드의 갯수
		map2.put("searchOption", searchOption); // 검색옵션
		map2.put("mem_authCode", mem_authCode); // 검색옵션
		map2.put("keyword", keyword); // 검색키워드
		map2.put("pagingDto", pagingDto);
		map2.put("curPage", curPage);
		
		request.setAttribute("map2", map2);
		
	}

	@Override
	public void memberCode(HttpServletRequest request,String mem_id) {
		//검색조건 유지시키기 위해
		String searchOption=request.getParameter("searchOption");
		String mem_authCode=request.getParameter("mem_authCode");
		String keyword=request.getParameter("keyword");
		int curPage=Integer.parseInt(request.getParameter("curPage"));
	
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("searchOption", searchOption); // 검색옵션
		map.put("mem_authCode", mem_authCode); // 검색옵션
		map.put("keyword", keyword); // 검색키워드
		map.put("curPage", curPage);
		request.setAttribute("map", map);
		
		MemberDto dto=session.selectOne("member.detailMember",mem_id);
		request.setAttribute("dto", dto);
		
	}

	@Override
	public void changeCode(HttpServletRequest request,MemberDto dto) {
		//검색조건 유지시키기 위해
		String searchOption=request.getParameter("searchOption");
		String mem_authCode=request.getParameter("mem_authCode2");
		String keyword=request.getParameter("keyword");
		int curPage=Integer.parseInt(request.getParameter("curPage"));
	
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("searchOption", searchOption); // 검색옵션
		map.put("mem_authCode", mem_authCode); // 검색옵션
		map.put("keyword", keyword); // 검색키워드
		map.put("curPage", curPage);
		request.setAttribute("map", map);
		
		session.selectOne("member.updateCode",dto);
		logger.info("회원 상태/권한 변경 : 아이디-"+dto.getMem_id());
	}

	

	
	
}
