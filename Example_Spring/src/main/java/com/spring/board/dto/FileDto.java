package com.spring.board.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileDto {
	private int file_num;
	private int board_num;
	private String file_writer;
	private String file_oriName;
	private String file_saveName;
	private Long file_size;
	private String file_time;
	
	
	public FileDto() {}

	public FileDto(int file_num, int board_num, String file_writer, String file_oriName, String file_saveName,
			Long file_size, String file_time) {
		super();
		this.file_num = file_num;
		this.board_num = board_num;
		this.file_writer = file_writer;
		this.file_oriName = file_oriName;
		this.file_saveName = file_saveName;
		this.file_size = file_size;
		this.file_time = file_time;
		
	}

	public int getFile_num() {
		return file_num;
	}

	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getFile_writer() {
		return file_writer;
	}

	public void setFile_writer(String file_writer) {
		this.file_writer = file_writer;
	}

	public String getFile_oriName() {
		return file_oriName;
	}

	public void setFile_oriName(String file_oriName) {
		this.file_oriName = file_oriName;
	}

	public String getFile_saveName() {
		return file_saveName;
	}

	public void setFile_saveName(String file_saveName) {
		this.file_saveName = file_saveName;
	}

	public Long getFile_size() {
		return file_size;
	}

	public void setFile_size(Long file_size) {
		this.file_size = file_size;
	}

	public String getFile_time() {
		return file_time;
	}

	public void setFile_time(String file_time) {
		this.file_time = file_time;
	}


	
}
