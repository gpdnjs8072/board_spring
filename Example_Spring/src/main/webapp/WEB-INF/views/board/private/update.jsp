<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		alert("글이 수정되었습니다.")
		location.href="../detail.do?board_typeCode=${board_typeCode }&board_num=${board_num}";
	</script>
</body>
</html>