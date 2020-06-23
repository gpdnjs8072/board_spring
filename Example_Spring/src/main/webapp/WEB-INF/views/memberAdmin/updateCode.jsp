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
		alert("회원 상태/권한이 변경되었습니다.");
		location.href=
			"memberList.do?curPage=${map.curPage}&searchOption=${map.searchOption}"+
				"&keyword=${map.keyword}&mem_authCode=${map.mem_authCode}";
	</script>


</body>
</html>