<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		alert("회원 정보가 수정되었습니다.");
		location.href="../../index.do";
	</script>
<%-- <c:choose>
	<c:when test="${isUpdate eq true }">
		<script>
			alert("회원 정보가 수정되었습니다.");
			location.href="../../index.do";
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("회원 정보 수정에 실패했습니다.");
			location.href="../../index.do";
		</script>
	</c:otherwise>
</c:choose> --%>
</body>
</html>