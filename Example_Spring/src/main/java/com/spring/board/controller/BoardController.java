package com.spring.board.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.dto.BoardDto;
import com.spring.board.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/list")
	public ModelAndView list(ModelAndView mView,HttpServletRequest request) {
		service.getCode(request);
		service.list(request);
		mView.setViewName("board/list.tiles");
		return mView;
	}
	
	@RequestMapping("/board/private/insert_form")
	public ModelAndView insert_form(ModelAndView mView,HttpServletRequest request) {
		service.getCode(request);
		mView.setViewName("board/private/insert_form.tiles");
		return mView;
	}
	
	@RequestMapping(value="/board/private/insert",method=RequestMethod.POST)
	public ModelAndView insert(ModelAndView mView,HttpServletRequest request,
							@RequestParam("file1") MultipartFile file1) throws IOException{
		service.insert(request, file1);
		mView.setViewName("board/private/insert.tiles");
		return mView;
	}
	
	@RequestMapping("/board/detail")
	public ModelAndView detail(ModelAndView mView, HttpServletRequest request,@RequestParam int board_num) {
		service.detailBoard(request, board_num);
		mView.setViewName("board/detail.tiles");
		return mView;
	}
	
	@RequestMapping("/board/private/update_form")
	public ModelAndView update_form(ModelAndView mView, HttpServletRequest request,@RequestParam int board_num) {
		service.updateForm(request, board_num);
		mView.setViewName("board/private/update_form.tiles");
		return mView;
	}
	
	@RequestMapping(value="/board/private/update",method=RequestMethod.POST)
	public ModelAndView update(ModelAndView mView, HttpServletRequest request,
						@RequestParam("file1") MultipartFile file1) {
		service.updateBoard(request, file1);
		mView.setViewName("board/private/update.tiles");
		return mView;
	}
	
	@RequestMapping("/board/private/delete")
	public ModelAndView delete(ModelAndView mView, HttpServletRequest request,@RequestParam int board_num) {
		service.deleteBoard(request, board_num);
		mView.setViewName("board/private/delete.tiles");
		return mView;
	}
	
	@RequestMapping("/board/private/download")
	public ModelAndView download(ModelAndView mView,HttpServletRequest request,HttpServletResponse response,@RequestParam int file_num) {
		service.fileDownload(request, response, file_num);
		mView.setViewName("board/detail.tiles");
		return mView;
	}
	
	@RequestMapping("/board/private/reply_form")
	public ModelAndView reply_form(ModelAndView mView, HttpServletRequest request) {
		service.getBoardNum(request);
		mView.setViewName("board/private/reply_form.tiles");
		return mView;
	}
	
	@RequestMapping("/board/private/reply")
	public ModelAndView reply(ModelAndView mView,HttpServletRequest request,@RequestParam("file1") MultipartFile file1) throws IOException {
		service.reply(request, file1);
		mView.setViewName("board/private/reply.tiles");
		return mView;
	}
}
