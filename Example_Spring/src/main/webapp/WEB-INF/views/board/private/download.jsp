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
		alert("파일이 다운로드 되었습니다.");
		location.herf="../detail.do?board_typeCode=${board_typeCode}&board_num=${board_num}";
	</script>
</body>
</html>