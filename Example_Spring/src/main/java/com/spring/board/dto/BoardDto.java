package com.spring.board.dto;

public class BoardDto {
	private int board_num;
	private String board_title;
	private String board_writer;
	private String board_content;
	private String board_typeCode;
	private int board_viewCount;
	private String board_time;
	private String typeName;
	private String file_saveName;
	private int board_oriNum;
	private int board_subNum;
	
	public BoardDto() {}

	public BoardDto(int board_num, String board_title, String board_writer, String board_content, String board_typeCode,
			int board_viewCount, String board_time, String typeName, String file_saveName, int board_oriNum,
			int board_subNum) {
		super();
		this.board_num = board_num;
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_content = board_content;
		this.board_typeCode = board_typeCode;
		this.board_viewCount = board_viewCount;
		this.board_time = board_time;
		this.typeName = typeName;
		this.file_saveName = file_saveName;
		this.board_oriNum = board_oriNum;
		this.board_subNum = board_subNum;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_typeCode() {
		return board_typeCode;
	}

	public void setBoard_typeCode(String board_typeCode) {
		this.board_typeCode = board_typeCode;
	}

	public int getBoard_viewCount() {
		return board_viewCount;
	}

	public void setBoard_viewCount(int board_viewCount) {
		this.board_viewCount = board_viewCount;
	}

	public String getBoard_time() {
		return board_time;
	}

	public void setBoard_time(String board_time) {
		this.board_time = board_time;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getFile_saveName() {
		return file_saveName;
	}

	public void setFile_saveName(String file_saveName) {
		this.file_saveName = file_saveName;
	}

	public int getBoard_oriNum() {
		return board_oriNum;
	}

	public void setBoard_oriNum(int board_oriNum) {
		this.board_oriNum = board_oriNum;
	}

	public int getBoard_subNum() {
		return board_subNum;
	}

	public void setBoard_subNum(int board_subNum) {
		this.board_subNum = board_subNum;
	}

	
	
	
}
