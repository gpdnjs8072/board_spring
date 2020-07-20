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



<script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>
<div>
	<div v-if="${isChange eq true }">
		<script>
			alert("비밀번호가 변경되었습니다.");
			location.href="login_form.do";
		</script>
	</div>
	<div v-else>
		<script>
			alert("비밀번호 변경을 실패했습니다.");
			location.href="searchPwd_form.do";
		</script>
	</div>
</div>
</body>
</html>