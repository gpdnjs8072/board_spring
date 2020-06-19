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


<c:if test="${not empty sessionScope.mem_id }">
	<script>
		alert("잘못된 접근입니다.");
		location.href="../index.do";
	</script>
</c:if>


	<c:if test="${isExist eq false}">
		<script>
			alert("사용자 인증에 실패했습니다.");
			location.href="searchPwd_form.do";
		</script>
	
	</c:if>

	
		



</body>
</html>