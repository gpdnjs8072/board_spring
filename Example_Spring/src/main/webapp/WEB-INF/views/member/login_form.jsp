<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div class="container">
	<h2>로그인</h2>
	<form action="login.do" method="post">
		<table>
			<tr>
			<input type="hidden" name="url" id="url" value="${url }" /></tr> 
			<tr>
				<th>
					<label for="mem_id"><spring:message code="mem.id"/></label>
				</th>
				<td>
					<input type="text" id="mem_id" name="mem_id" required="required"/>
				</td>
			</tr>
			<tr>
				<th>
					<label for="mem_pwd"><spring:message code="mem.pwd"/></label>
				</th>
				<td>
					<input type="password" id="mem_pwd" name="mem_pwd" required="required" />
				</td>
			</tr>
		</table>
		<button type="submit"><spring:message code="mem.login"/></button>
	</form>
		<button onclick="location.href='searchId_form.do'">아이디 찾기</button>
		<button onclick="location.href='searchPwd_form.do'">비밀번호 찾기</button>
		
	</div>
</body>
</html>