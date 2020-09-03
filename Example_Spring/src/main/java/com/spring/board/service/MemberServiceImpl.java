package com.spring.board.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.spring.board.dto.MemberDto;
import com.spring.board.mapper.MemberMapper;


@Service
@Repository
public class MemberServiceImpl implements MemberService{
	private static final Logger logger = LoggerFactory.getLogger(MemAdminServiceImpl.class);
	
	@Autowired
	MemberMapper mapper;
	
	//회원가입
	@Override
	public void addMember(MemberDto dto,HttpServletRequest request) {
		//비밀번호 암호화
		String encodePwd=new BCryptPasswordEncoder().encode(dto.getMem_pwd());
		dto.setMem_pwd(encodePwd);
		try {
			mapper.insert(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("mem_id", dto.getMem_id());
		logger.info("회원가입 -아이디 : "+dto.getMem_id());
	}
	
	//아이디 중복확인
	@Override
	public Map<String, Object> isExistId(String mem_id) {
		Map<String, Object> map=null;
		try {
			String isExist = mapper.isExistId(mem_id);
			map=new HashMap<String, Object>();
			map.put("isExist", isExist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	//이메일 중복확인
	@Override
	public Map<String, Object> isExistEmail(String mem_email) {
		Map<String, Object> map=null;
		String isExistEmail;
		try {
			isExistEmail = mapper.isExistEmail(mem_email);
			map=new HashMap<String, Object>();
			map.put("isExistEmail", isExistEmail);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return map;
	}
	
	//회원 아이디,비밀번호 확인 (로그인)
	@Override
	public void validMember(MemberDto dto, HttpServletRequest request) {
		
		
		//아이디,비밀번호가 유효한지 여부
		boolean isValid=false;
		//아이디를 이용해서 저장된 비밀번호를 읽어온다.
		String pwdHash;
		try {
			pwdHash = mapper.selectPwdHash(dto.getMem_id()); //아이디 존재o,암호화된 비번
			MemberDto dto2; //해당아이디의 상태코드,권한 코드
			
			boolean isEqualPwd=false;
			if(pwdHash!=null) {  //아이디 존재 O
				isEqualPwd=BCrypt.checkpw(dto.getMem_pwd(), pwdHash);
				
				if(isEqualPwd) {  //비밀번호 일치 o
					dto2=mapper.selectMember(dto.getMem_id());
					
					if(dto2.getMem_loginFailCount()<5 && dto2.getMem_stateCode().equals("101")) {
						//로그인 실패횟수 0으로 변경
						mapper.updateOriCount(dto.getMem_id());
						//세션에 아이디 저장
						request.getSession().setAttribute("mem_id", dto.getMem_id());
						//세션에 권한코드 저장
						request.getSession().setAttribute("mem_authCode", dto2.getMem_authCode());
						logger.info("로그인 : "+dto.getMem_id());
					}else if(dto2.getMem_stateCode().equals("102")) { //상태코드 '정지'
						request.setAttribute("error3", "stop"); 
					}else if(dto2.getMem_stateCode().equals("103")) { //상태코드 '탈퇴'
						request.setAttribute("error4", "withdraw");
					}
					
				}else { //비밀번호 일치 x
					//로그인 실패횟수 증가
					mapper.updateCount(dto.getMem_id());
					dto2=mapper.selectMember(dto.getMem_id());
					if(dto2.getMem_loginFailCount()>=5) {
						//회원 상태를 정지로 변경
						mapper.updateStateCode(dto.getMem_id());
						//회원상태 정지 알림
						request.setAttribute("error1", "changeStopState");
					}else {
						request.setAttribute("error2", "fail");
					}
				}
			}else { //아이디 존재 x
				request.setAttribute("error2", "fail");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}  
	
		
	}

	//아이디 찾기
	@Override
	public void getId(MemberDto dto, HttpServletRequest request) {
		String mem_id;
		try {
			mem_id = mapper.searchId(dto);
			if(mem_id!=null) {
				request.setAttribute("mem_id", mem_id);
			}else {
				request.setAttribute("notExist", "notExist");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	//비밀번호 찾기 (존재여부)
	@Override
	public boolean isExistMember(MemberDto dto, HttpServletRequest request) {
		boolean isExistMember=false;
		String mem_id;
		try {
			mem_id = mapper.searchPwd(dto);
			if(mem_id !=null) {
				request.setAttribute("mem_id", mem_id);
				isExistMember=true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return isExistMember;
		
	}
	
	//비밀번호 찾기 후 비밀번호 변경
	@Override
	public void changeNewPwd(MemberDto dto, HttpServletRequest request) {
		String mem_id=request.getParameter("mem_id");
		String mem_pwd=request.getParameter("mem_pwd");
		String newPwd=new BCryptPasswordEncoder().encode(mem_pwd);
		dto.setMem_id(mem_id);
		dto.setMem_pwd(newPwd);
		try {
			mapper.changePwd(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("isChange", true);
	}
	
	
	//회원 정보 폼
	@Override
	public void detailMember(HttpServletRequest request) {
		String mem_id=request.getSession().getAttribute("mem_id").toString();
		MemberDto dto;
		try {
			dto = mapper.detailMember(mem_id);
			request.setAttribute("dto", dto);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	//회원 정보 변경
	@Override
	public void updateMember(MemberDto dto, HttpServletRequest request) {
		try {
			mapper.updateMember(dto);
			logger.info("회원 정보 변경 : 아이디-"+dto.getMem_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//회원 탈퇴
	@Override
	public void deleteMember( HttpServletRequest request) {
		String mem_id=request.getSession().getAttribute("mem_id").toString();
		try {
			mapper.deleteMember(mem_id);
			request.getSession().invalidate();
			logger.info("회원 탈퇴 : 아이디-"+mem_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
