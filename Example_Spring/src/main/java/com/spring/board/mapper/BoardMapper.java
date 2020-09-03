package com.spring.board.mapper;

import java.util.List;
import java.util.Map;

import com.spring.board.dto.BoardDto;

public interface BoardMapper {
	
	void insert1(BoardDto dto) throws Exception;
	
	void insert2(BoardDto dto) throws Exception;
	
	void insert3(BoardDto dto) throws Exception;
	
	void insert4(BoardDto dto) throws Exception;
	
	int getBoardNum(String file_saveName) throws Exception;
	
	BoardDto detail(int board_num) throws Exception;
	
	void update1(BoardDto dto) throws Exception;
	
	void update2(BoardDto dto) throws Exception;
	
	void delete(int board_num) throws Exception;
	
	int subNum(int board_num) throws Exception;
	
	int maxSubNum(int board_oriNum) throws Exception;
	
	List<BoardDto> getList(Map<String, Object> map) throws Exception;      //확인하기 (리스트 부분)
	
	int count(Map<String, Object> map) throws Exception; //확인
	
	
	
}
