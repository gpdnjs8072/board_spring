package com.spring.board.mapper;

import com.spring.board.dto.FileDto;

public interface FileMapper {
	void insertFile(FileDto dto) throws Exception;
	
	FileDto getFile(int board_num) throws Exception;
	
	FileDto getData(int file_num) throws Exception;
	
	void update(FileDto dto) throws Exception;
	
	void delete(int board_num) throws Exception;
	
	int isExist(int board_num) throws Exception;
	
}
