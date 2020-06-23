package com.spring.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;


public interface BoardService {
	//타입코드 이름
	public void getCode(HttpServletRequest request);
	//게시글 등록
	public void insert(HttpServletRequest request,MultipartFile file1);
	//게시글 상세보기
	public void detailBoard(HttpServletRequest request,int board_num);
	//게시글 수정 폼
	public void updateForm(HttpServletRequest request,int board_num);
	//게시글 수정
	public void updateBoard(HttpServletRequest request,MultipartFile file1);
	//게시글 삭제
	public void deleteBoard(HttpServletRequest request,int board_num);
	//첨부파일 다운로드
	public void fileDownload(HttpServletRequest request,HttpServletResponse response,int file_num);
	//게시글 목록
	public void list(HttpServletRequest request);
	//답변 등록 폼
	public void getBoardNum(HttpServletRequest request);
	//답변 등록
	public void reply(HttpServletRequest request,MultipartFile file1);
}
