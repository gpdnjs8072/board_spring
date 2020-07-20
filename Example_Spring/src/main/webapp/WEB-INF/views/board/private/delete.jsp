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
		alert("글이 삭제되었습니다.");
		location.href="../list.do?board_typeCode=${board_typeCode}"+
		"&curPage=${map.curPage }&searchOption=${map.searchOption}&keyword=${map.keyword}&board_time=${map.board_time}";
	</script>

	
</body>
</html>