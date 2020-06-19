package com.spring.board.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class BoardServiceImpl implements BoardService{
	@Autowired
	SqlSession session;

	@Override
	public void getCode(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}
	
	
}
