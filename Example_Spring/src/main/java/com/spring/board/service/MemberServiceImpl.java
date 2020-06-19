package com.spring.board.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.spring.board.dto.MemberDto;


@Service
@Repository
public class MemberServiceImpl implements MemberService{
	@Autowired
	SqlSession session;
	
	//회원가입
	@Override
	public void addMember(MemberDto dto,HttpServletRequest request) {
		//비밀번호 암호화
		String encodePwd=new BCryptPasswordEncoder().encode(dto.getMem_pwd());
		dto.setMem_pwd(encodePwd);
		session.insert("member.insert",dto);
		request.setAttribute("mem_id", dto.getMem_id());
	}
	
	//아이디 중복확인
	@Override
	public Map<String, Object> isExistId(String mem_id) {
		String isExist=session.selectOne("member.isExistId",mem_id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isExist", isExist);
		return map;
	}
	
	//회원 아이디,비밀번호 확인 (로그인)
	@Override
	public void validMember(MemberDto dto, HttpServletRequest request) {
		//아이디,비밀번호가 유효한지 여부
		boolean isValid=false;
		//아이디를 이용해서 저장된 비밀번호를 읽어온다.
		String pwdHash=session.selectOne("member.selectPwdHash",dto.getMem_id());  //아이디 존재o,암호화된 비번
		System.out.println("pwdHash : "+pwdHash);
		MemberDto dto2; //해당아이디의 상태코드,권한 코드
		
		boolean isEqualPwd=false;
		if(pwdHash!=null) {  //아이디 존재 O
			isEqualPwd=BCrypt.checkpw(dto.getMem_pwd(), pwdHash);
			System.out.println(isEqualPwd);
			if(isEqualPwd) {  //비밀번호 일치 o
				dto2=session.selectOne("member.selectMember",dto.getMem_id());
				System.out.println(dto2.getMem_loginFailCount());
				System.out.println(dto2.getMem_stateCode().equals("101"));
				if(dto2.getMem_loginFailCount()<5 && dto2.getMem_stateCode().equals("101")) {
					//로그인 실패횟수 0으로 변경
					session.update("member.updateOriCount",dto.getMem_id());
					//세션에 아이디 저장
					request.getSession().setAttribute("mem_id", dto.getMem_id());
					//세션에 권한코드 저장
					request.getSession().setAttribute("mem_authCode", dto2.getMem_authCode());
//					logger.info("로그인 : "+dto.getMem_id());
				}else if(dto2.getMem_stateCode().equals("102")) { //상태코드 '정지'
					request.setAttribute("error3", "stop"); 
				}else if(dto2.getMem_stateCode().equals("103")) { //상태코드 '탈퇴'
					request.setAttribute("error4", "withdraw");
				}
				
			}else { //비밀번호 일치 x
				//로그인 실패횟수 증가
				session.update("member.updateCount",dto.getMem_id());
				dto2=session.selectOne("member.selectMember",dto.getMem_id());
				if(dto2.getMem_loginFailCount()>=5) {
					//회원 상태를 정지로 변경
					session.update("member.updateStateCode",dto.getMem_id());
					//회원상태 정지 알림
					request.setAttribute("error1", "changeStopState");
				}else {
					request.setAttribute("error2", "fail");
				}
			}
		}else { //아이디 존재 x
			request.setAttribute("error2", "fail");
		}
		
		
	}

	//아이디 찾기
	@Override
	public void getId(MemberDto dto, HttpServletRequest request) {
		String mem_id=session.selectOne("member.searchId",dto);
		if(mem_id!=null) {
			request.setAttribute("mem_id", mem_id);
		}else {
			request.setAttribute("notExist", "notExist");
		}
		
	}
	
	//비밀번호 찾기 (존재여부)
	@Override
	public boolean isExistMember(MemberDto dto, HttpServletRequest request) {
		boolean isExistMember=false;
		String mem_id=session.selectOne("member.searchPwd",dto);
		if(mem_id !=null) {
			request.setAttribute("mem_id", mem_id);
			isExistMember=true;
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
		session.update("member.changePwd",dto);
		request.setAttribute("isChange", true);
	}
	
	
	//회원 정보 폼
	@Override
	public void detailMember(HttpServletRequest request) {
		String mem_id=request.getSession().getAttribute("mem_id").toString();
		MemberDto dto=session.selectOne("member.detailMember", mem_id);
		request.setAttribute("dto", dto);
		
	}
	
	//회원 정보 변경
	@Override
	public void updateMember(MemberDto dto, HttpServletRequest request) {
		session.update("member.updateMember",dto);
		
	}

	//회원 탈퇴
	@Override
	public void deleteMember( HttpServletRequest request) {
		String mem_id=request.getSession().getAttribute("mem_id").toString();
		session.update("member.deleteMember",mem_id);
		request.getSession().invalidate();
	}
	
}
