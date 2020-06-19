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
<c:choose>
	<c:when test="${success eq true }">
		<script>
			alert("글이 삭제되었습니다.");
			location.href="../list.do?board_typeCode=${board_typeCode}";
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("글 삭제에 실패했습니다.");
			location.href="../list.do?board_typeCode=${board_typeCode}";
		</script>
	</c:otherwise>
</c:choose>
</body>
</html>