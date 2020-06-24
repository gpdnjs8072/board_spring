package com.spring.board.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.board.dto.BoardDto;
import com.spring.board.dto.FileDto;
import com.spring.board.dto.PagingDto;




@Service
@Repository
public class BoardServiceImpl implements BoardService{
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	SqlSession session;

	@Override
	public void getCode(HttpServletRequest request) {
		//파라미터의 게시글 타입코드 얻기
		String co_code=request.getParameter("board_typeCode");
		String typeName=session.selectOne("code.selectCodeName",co_code);
		request.setAttribute("typeName", typeName);
		request.setAttribute("board_typeCode", co_code);
		
	}

	@Override
	public void insert(HttpServletRequest request,MultipartFile file1) {
		
		
		String file_saveName=""; //저장된 이름
		String file_oriName=""; //원본 이름
		long file_size=0; //파일 사이즈
		
		String board_writer=request.getSession().getAttribute("mem_id").toString();
		String board_typeCode=request.getParameter("board_typeCode");
		String board_title=request.getParameter("board_title");
		String board_content=request.getParameter("board_content");
		
		
		BoardDto boardDto=new BoardDto();
		boardDto.setBoard_writer(board_writer);
        boardDto.setBoard_typeCode(board_typeCode);
        boardDto.setBoard_title(board_title);
        boardDto.setBoard_content(board_content);
		
        FileDto fileDto=new FileDto();
		
		//파일 저장 경로
		String path=request.getServletContext().getRealPath("/upload");
	
		if(!file1.isEmpty()) {   //파일이 존재할 때
			
			//저장할 파일의 상세 경로
			String filePath=path+File.separator;
			File file=new File(filePath);
			if(!file.exists()) {//디렉토리가 존재하지 않으면
				file.mkdir();  //디렉토리 생성
			}
			file_oriName=file1.getOriginalFilename();
			file_size=file1.getSize();
			//저장되는 파일 이름은 현재시간+oriName
			file_saveName=System.currentTimeMillis()+file_oriName;
			
			//게시글 정보를 저장
			boardDto.setFile_saveName(file_saveName);
			session.insert("board.insert2",boardDto);
			
			int board_num=session.selectOne("board.getBoardNum",file_saveName);
			
			fileDto.setBoard_num(board_num);
			fileDto.setFile_oriName(file_oriName);
        	fileDto.setFile_saveName(file_saveName);
        	fileDto.setFile_size(file_size);
        	fileDto.setFile_writer(board_writer);
        	fileDto.setBoard_num(board_num);
        	session.insert("file.insertFile",fileDto);
        	
			try {
				//파일 저장
				file1.transferTo(new File(filePath+file_saveName));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {  //파일 존재 x
			session.insert("board.insert1",boardDto);
		}
		
		
		request.setAttribute("board_typeCode", board_typeCode);
		logger.info("게시글 등록 : 종류-"+board_typeCode);
	}

	@Override
	public void detailBoard(HttpServletRequest request, int board_num) {
		//검색조건 유지시키기 위해
		String searchOption=request.getParameter("searchOption");
		String keyword=request.getParameter("keyword");
		int curPage=Integer.parseInt(request.getParameter("curPage"));
		String board_time=request.getParameter("board_time");
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("searchOption", searchOption); // 검색옵션
		map.put("keyword", keyword); // 검색키워드
		map.put("curPage", curPage);
		map.put("board_time", board_time);
		request.setAttribute("map", map);
		
		int board_subNum=session.selectOne("board.subNum", board_num);
		request.setAttribute("board_subNum", board_subNum);
		
		String board_typeCode=request.getParameter("board_typeCode");

		BoardDto dto=session.selectOne("board.detail",board_num);
		String fileExist=dto.getFile_saveName();
		
		if(fileExist!=null) { //해당 게시글에 첨부파일이 존재하면
			FileDto fileDto=session.selectOne("file.getFile",board_num);
			request.setAttribute("fileDto", fileDto);
		}
		request.setAttribute("dto",dto );
		request.setAttribute("board_typeCode", board_typeCode);
	}

	@Override
	public void updateForm(HttpServletRequest request, int board_num) {
//		String board_typeCode=request.getParameter("board_typeCode");
		//검색조건 유지시키기 위해
		String searchOption=request.getParameter("searchOption");
		String keyword=request.getParameter("keyword");
		int curPage=Integer.parseInt(request.getParameter("curPage"));
		String board_time=request.getParameter("board_time");
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("searchOption", searchOption); // 검색옵션
		map.put("keyword", keyword); // 검색키워드
		map.put("curPage", curPage);
		map.put("board_time", board_time);
		request.setAttribute("map", map);
		
		BoardDto dto=session.selectOne("board.detail",board_num);
	
		FileDto fileDto=session.selectOne("file.getFile",board_num);
		
		request.setAttribute("dto",dto );
		request.setAttribute("fileDto", fileDto);
//		request.setAttribute("board_typeCode", board_typeCode);
		
	}

	@Override
	public void updateBoard(HttpServletRequest request,MultipartFile file1) {
		//검색조건 유지시키기 위해
		String searchOption=request.getParameter("searchOption");
		String keyword=request.getParameter("keyword");
		int curPage=Integer.parseInt(request.getParameter("curPage"));
		String board_time=request.getParameter("board_time");
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("searchOption", searchOption); // 검색옵션
		map.put("keyword", keyword); // 검색키워드
		map.put("curPage", curPage);
		map.put("board_time", board_time);
		request.setAttribute("map", map);
		
		int board_num=Integer.parseInt(request.getParameter("board_num"));
		
		
		String file_saveName=""; //저장된 이름
		String file_oriName=""; //원본 이름
		long file_size=0; //파일 사이즈
		
		String board_typeCode=request.getParameter("board_typeCode");
		String board_title=request.getParameter("board_title");
		String board_content=request.getParameter("board_content");
		String board_writer=request.getParameter("board_writer");
		
		BoardDto boardDto=new BoardDto();
		boardDto.setBoard_num(board_num);
        boardDto.setBoard_typeCode(board_typeCode);
        boardDto.setBoard_title(board_title);
        boardDto.setBoard_content(board_content);
		
        FileDto fileDto=new FileDto();
        BoardDto boardDto2=session.selectOne("board.detail",board_num);
		String fileExist=boardDto2.getFile_saveName();
        
		//파일 저장 경로
		String path=request.getServletContext().getRealPath("/upload");
	
		if(!file1.isEmpty()) {   //파일이 존재할 때
			
			//저장할 파일의 상세 경로
			String filePath=path+File.separator;
			File file=new File(filePath);
			if(!file.exists()) {//디렉토리가 존재하지 않으면
				file.mkdir();  //디렉토리 생성
			}
			file_oriName=file1.getOriginalFilename();
			file_size=file1.getSize();
			//저장되는 파일 이름은 현재시간+oriName
			file_saveName=System.currentTimeMillis()+file_oriName;
			
			//게시글 정보를 저장
			boardDto.setFile_saveName(file_saveName);
			session.update("board.update2",boardDto);
			
		
			fileDto.setBoard_num(board_num);
			fileDto.setFile_oriName(file_oriName);
        	fileDto.setFile_saveName(file_saveName);
        	fileDto.setFile_size(file_size);
        	fileDto.setFile_writer(board_writer);
        	
        	
        	
    		if(fileExist!=null) { //기존글에 첨부파일이 존재했다면
    			session.update("file.update",fileDto);	
    		
        	}else {  //존재하지 않았다면 
        		session.insert("file.insertFile",fileDto);
        		
        	}
        	
			try {
				//파일 저장
				file1.transferTo(new File(filePath+file_saveName));
			}catch (Exception e) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				PrintStream printStream = new PrintStream(out);
		
				e.printStackTrace(printStream);
				String stackTraceString=out.toString();
			    logger.error("error : "+stackTraceString);
			}
		}else {  //파일 존재 x
			session.update("board.update1",boardDto);
		
		}
		
		request.setAttribute("board_num", board_num);
		request.setAttribute("board_typeCode", board_typeCode);
		logger.info("게시글 수정 : 글 번호-"+board_num);
	}

	@Override
	public void deleteBoard(HttpServletRequest request, int board_num) {
		//검색조건 유지시키기 위해
		String searchOption=request.getParameter("searchOption");
		String keyword=request.getParameter("keyword");
		int curPage=Integer.parseInt(request.getParameter("curPage"));
		String board_time=request.getParameter("board_time");
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("searchOption", searchOption); // 검색옵션
		map.put("keyword", keyword); // 검색키워드
		map.put("curPage", curPage);
		map.put("board_time", board_time);
		request.setAttribute("map", map);
		
		String board_typeCode=request.getParameter("board_typeCode");

//		String mem_id=request.getSession().getAttribute("mem_id").toString();
//		String mem_authCode=request.getSession().getAttribute("mem_authCode").toString();
//		if(mem_id.equals(dto.getFile_writer())||mem_authCode.equals("002")) {
//			
//		}
		
		BoardDto boardDto=session.selectOne("board.detail",board_num);
		String fileExist=boardDto.getFile_saveName();  //해당 게시글의 파일 이름 가져오기
		
		if(fileExist!=null) { //해당 게시글에 첨부파일이 존재하면
			FileDto fileDto=session.selectOne("file.getFile",board_num);
			String file_saveName=fileDto.getFile_saveName();
			//file 삭제
			session.delete("file.delete",board_num);
			//폴더에서 해당 파일 삭제
			String path=request.getServletContext().getRealPath("/upload")+File.separator+file_saveName;
			File file=new File(path);
			file.delete();
		}
		
		//게시글 삭제
		session.delete("board.delete",board_num);
		request.setAttribute("board_typeCode", board_typeCode);
		logger.info("게시글 삭제 : 글 번호-"+board_num);
		
	}

	@Override
	public void fileDownload(HttpServletRequest request,HttpServletResponse response, int file_num) {
		String board_typeCode=request.getParameter("board_typeCode");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		FileDto dto=session.selectOne("file.getData",file_num);
		
		String realPath=request.getServletContext().getRealPath("/upload")+File.separator;
		
		FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    BufferedOutputStream bos = null;
		try {
			 
		    String header = request.getHeader("User-Agent");
		    
		    // ISO-8895-1 인코딩은 대부분의 브라우저에 설정된 기본 문자셋      
		    //한글 파일 받기 위해서 둘다 "UTF-8"로 설정함
		    String path = new String(realPath.toString().getBytes("UTF-8"), "UTF-8");
		    //실제 파일 이름
		    String fileName = new String(dto.getFile_oriName().toString().getBytes("UTF-8"), "ISO-8859-1");
		    String viewFileName = "";
		    
		    if (header.contains("MSIE") || header.contains("Trident")) {// 익스플로러의 경우 한글처리
		        //저장되는 파일 이름
		    	viewFileName = URLEncoder.encode(dto.getFile_saveName().toString(),"UTF-8").replaceAll("\\+", "%20");
		    } else {   // 익스플로러 이외 한글처리
		        //저장되는 파일이름
		    	viewFileName = new String(dto.getFile_saveName().toString().getBytes("UTF-8"),"UTF-8");
		    }
		    
		 
		    File file = new File(path+viewFileName);
		 
		    response.reset();        // 모두 초기화 된다.
		    response.setContentType("application/octer-stream");
		 
		    // 해더에 내가 원하는 전송할 파일 이름을 설정
		    response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "");
		    response.setHeader("Content-Transper-Encoding", "binary");    // 인코딩 설정 변경
		    response.setContentLength((int) file.length());                // 파일 사이즈 설정
		    response.setHeader("Pargma", "no-cache");
		    response.setHeader("Expires", "-1");
		    
		    byte[] data = new byte[2048];
		    fis = new FileInputStream(file);
		    bis = new BufferedInputStream(fis);
		    bos = new BufferedOutputStream(response.getOutputStream());
		    
		    int count = 0;
		    while ((count = bis.read(data)) != -1) {
		        bos.write(data,0,count);
		    }
		 
		    if (fis != null)
		        fis.close();
		    if (bis != null)
		        bis.close();
		    if (bos != null)
		        bos.close();
		    
		    
		} catch (Exception e) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream printStream = new PrintStream(out);
	
			e.printStackTrace(printStream);
			String stackTraceString=out.toString();
		    logger.error("error : "+stackTraceString);
		} finally { 
			try {
				 if (fis != null) {						 
					 fis.close();
				 }
				 if (bis != null)
			        bis.close();
			    if (bos != null)
			        bos.close();
			} catch (IOException e) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				PrintStream printStream = new PrintStream(out);
		
				e.printStackTrace(printStream);
				String stackTraceString=out.toString();
			    logger.error("error : "+stackTraceString);
			}
	   
		}
	}

	@Override
	public void list(HttpServletRequest request) {
		String board_typeCode=request.getParameter("board_typeCode");
		String searchOption=request.getParameter("searchOption");
		String keyword=request.getParameter("keyword");
		String board_time=request.getParameter("board_time");
		
		if(keyword=="") {
			keyword=null;
		}
		if(searchOption=="") {
			searchOption=null;
		}
		if(board_time=="") {
			board_time=null;
		}
		
		// *일 전
		String date1=null;
		//현재
		String date2=null;

		if(board_time!=null) {
			
			Calendar cal=new GregorianCalendar(Locale.KOREA);
			cal.setTime(new Date());
			
			DateFormat df=new SimpleDateFormat("YYYYMMdd");
			date2=df.format(cal.getTime());

			int minus=Integer.parseInt(board_time);
			cal.add(Calendar.DATE,-minus );
			date1=df.format(cal.getTime());
		}
				
		
		int curPage=1;
		
		if(request.getParameter("curPage")!=null) {
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("board_typeCode", board_typeCode);
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		map.put("date1", date1);
		map.put("date2", date2);
		map.put("board_time", board_time);
		
		int count=session.selectOne("board.count",map);
	
		
		//PagingDto(게시글 갯수,현재페이지,페이지당 게시글 수,화면의 페이지수)
		PagingDto pagingDto=new PagingDto(count, curPage,10, 5);
		int start = pagingDto.getPageBegin();
		int end = pagingDto.getPageEnd();
		
		map.put("start", start);
		map.put("end", end);
	
		List<BoardDto> list=session.selectList("board.getList",map);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("list", list); // list
		map2.put("count", count); // 레코드의 갯수
		map2.put("board_typeCode", board_typeCode); //게시글 종류
		map2.put("searchOption", searchOption); // 검색옵션
		map2.put("keyword", keyword); // 검색키워드
		map2.put("pagingDto", pagingDto);
		map2.put("board_time",board_time); //검색조건 -시간
		map2.put("curPage", curPage);
		request.setAttribute("map2", map2);
		
		request.setAttribute("board_typeCode", board_typeCode);
		
		String typeName=session.selectOne("code.selectCodeName",board_typeCode);
		request.setAttribute("typeName", typeName);
	}

	@Override
	public void getBoardNum(HttpServletRequest request) {
		int board_num=Integer.parseInt(request.getParameter("board_num"));
	
		String board_typeCode=request.getParameter("board_typeCode");
		String typeName=session.selectOne("code.selectCodeName",board_typeCode);
		
		request.setAttribute("board_num", board_num);
		request.setAttribute("board_typeCode", board_typeCode);
		request.setAttribute("typeName", typeName);
	}

	@Override
	public void reply(HttpServletRequest request, MultipartFile file1) {
		String board_writer=request.getSession().getAttribute("mem_id").toString();
		String board_typeCode=request.getParameter("board_typeCode");
		String board_title=request.getParameter("board_title");
		board_title="&nbsp;ㄴ[RE]&nbsp; "+board_title;
		String board_content=request.getParameter("board_content");

		int board_num=Integer.parseInt(request.getParameter("board_num"));

		int board_subNum=session.selectOne("board.maxSubNum", board_num);
		board_subNum=board_subNum+1;
		
		String file_saveName=""; //저장된 이름
		String file_oriName=""; //원본 이름
		long file_size=0; //파일 사이즈
		
	
		
		
		BoardDto boardDto=new BoardDto();
		boardDto.setBoard_writer(board_writer);
        boardDto.setBoard_typeCode(board_typeCode);
        boardDto.setBoard_title(board_title);
        boardDto.setBoard_content(board_content);
		boardDto.setBoard_oriNum(board_num);
		boardDto.setBoard_subNum(board_subNum);
        
        FileDto fileDto=new FileDto();
		
		//파일 저장 경로
		String path=request.getServletContext().getRealPath("/upload");
	
		if(!file1.isEmpty()) {   //파일이 존재할 때
			
			//저장할 파일의 상세 경로
			String filePath=path+File.separator;
			File file=new File(filePath);
			if(!file.exists()) {//디렉토리가 존재하지 않으면
				file.mkdir();  //디렉토리 생성
			}
			file_oriName=file1.getOriginalFilename();
			file_size=file1.getSize();
			//저장되는 파일 이름은 현재시간+oriName
			file_saveName=System.currentTimeMillis()+file_oriName;
			
			//게시글 정보를 저장
			boardDto.setFile_saveName(file_saveName);
			session.insert("board.insert4",boardDto);
			
			int board_num2=session.selectOne("board.getBoardNum",file_saveName);
			fileDto.setBoard_num(board_num2);
			fileDto.setFile_oriName(file_oriName);
        	fileDto.setFile_saveName(file_saveName);
        	fileDto.setFile_size(file_size);
        	fileDto.setFile_writer(board_writer);
        	session.insert("file.insertFile",fileDto);
        	
			try {
				//파일 저장
				file1.transferTo(new File(filePath+file_saveName));
			}catch (Exception e) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				PrintStream printStream = new PrintStream(out);
		
				e.printStackTrace(printStream);
				String stackTraceString=out.toString();
			    logger.error("error : "+stackTraceString);
			}
		}else {  //파일 존재 x
			session.insert("board.insert3",boardDto);
		}
		
		
		request.setAttribute("board_typeCode", board_typeCode);
		logger.info("게시글 답글 등록 : 원글-"+board_num);
	}
	
	
}
