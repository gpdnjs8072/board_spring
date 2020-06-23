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

	<c:choose>
		
		<c:when test="${notExist eq 'notExist'}">
			<script>
				alert("사용자 인증에 실패했습니다.")
				location.href="searchId_form.do";
			</script>
		</c:when>
		<c:otherwise>
			<h3>아이디는 ${requestScope.mem_id} 입니다.</h3>
			<a href="login_form.do">로그인</a>
			<a href="searchPwd_form.do">비밀번호찾기</a>
		</c:otherwise>
	</c:choose>
</body>
</html>