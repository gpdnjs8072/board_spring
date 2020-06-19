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
	<script>
		alert("회원탈퇴 되었습니다.");
		location.href="../../index.do";
	</script>
<%-- <c:choose>
	<c:when test="${isDelete eq true }">
		<script>
			alert("회원탈퇴 되었습니다.");
			location.href="../../index.do";
		</script>
	</c:when>
	<c:when test="${isDelete eq false}">
		<script>
			alert("회원탈퇴에 실패했습니다.");
			location.href="info_form.do";
		</script>
	</c:when>
</c:choose> --%>
</body>
</html>