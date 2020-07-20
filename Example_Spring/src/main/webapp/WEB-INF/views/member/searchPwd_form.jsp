<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

<div class="container">
	<h2>비밀번호 찾기</h2>
	<br /><br />
	
	
	<form action="newPwd_form.do" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		
			<label for="mem_id">아이디</label>
			<input type="text" name="mem_id" id="mem_id" required="required"  /><br />
		
	
			<label for="mem_name">이름</label>
			<input type="text" name="mem_name" id="mem_name" required="required" /><br />
		
		
			<label for="mem_email">이메일</label>
			<input type="email" name="mem_email" id="mem_email" required="required" /><br />
	
		<button type="submit">비밀번호 찾기</button>
	</form>
</div>
</body>
</html>