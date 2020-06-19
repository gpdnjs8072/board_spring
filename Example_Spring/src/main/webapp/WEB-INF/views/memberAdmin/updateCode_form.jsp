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

<h2>회원 상태/권한 변경</h2>
<form action="updateCode.do" method="post">
	<p>아이디 : ${dto.mem_id} </p>
	<input type="hidden" name="mem_id" id="mem_id" value="${dto.mem_id }" />
	<div class="container">
		<label for="mem_stateCode">회원 상태</label>
		 <select name="mem_stateCode" id="mem_stateCode" >
				<option value="101" <c:if test="${dto.stateName eq '정상' }"> selected </c:if> >정상</option>
				<option value="102" <c:if test="${dto.stateName eq '정지' }"> selected </c:if> >정지</option>
				<option value="103" <c:if test="${dto.stateName eq '탈퇴' }"> selected </c:if> >탈퇴</option>
		</select>
	</div>
	<div class="container">
		<label for="mem_authCode">회원 권한</label>
		<select name="mem_authCode" id="mem_authCode" >
			<option value="001" <c:if test="${dto.authName eq '일반' }">selected</c:if> >일반</option>
			<option value="002" <c:if test="${dto.authName eq '회원관리자' }">selected</c:if> >회원 관리자</option>
			<option value="003" <c:if test="${dto.authName eq '게시판관리자' }">selected</c:if> >게시판 관리자</option>
		</select>
	</div>
	<button type="submit">수정하기</button>
</form>
</body>
</html>