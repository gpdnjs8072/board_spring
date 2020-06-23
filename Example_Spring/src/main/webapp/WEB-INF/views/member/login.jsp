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
<c:choose>
	<c:when test="${url ne 'no'}">
		<c:choose>
		
			<c:when test="${error1 eq 'changeStopState'}">
				<script>
					alert("5회 이상 실패로 해당 아이디가 정지되었습니다.");
					location.href="login_form.do?url=${encodedUrl}";
				</script>		
			</c:when>
			<c:when test="${error2 eq 'fail'}">
				<script>
					alert("사용자 인증정보가 잘못되었습니다.");
					location.href="login_form.do?url=${encodedUrl}";
				</script>		
			</c:when>
			
			<c:when test="${error3 eq 'stop'}">
				<script>
					alert("정지된 회원입니다.");
					location.href="login_form.do?url=${encodedUrl}";
				</script>		
			</c:when>
			<c:when test="${error4 eq 'withdraw'}">
				<script>
					alert("탈퇴한 회원입니다.");
					location.href="login_form.do?url=${encodedUrl}";
				</script>		
			</c:when>
		
			<c:when test="${not empty mem_id}">
				<script>
					alert("${mem_id} 님이 로그인하셨습니다.");
					location.href="${url}";
				</script>
			</c:when>
		</c:choose> 
	</c:when>
	<c:otherwise>
		<c:choose>
	
		<c:when test="${error1 eq 'changeStopState'}">
			<script>
				alert("5회 이상 실패로 해당 아이디가 정지되었습니다.");
				location.href="login_form.do";
			</script>		
		</c:when>
		<c:when test="${error2 eq 'fail'}">
			<script>
				alert("사용자 인증정보가 잘못되었습니다.");
				location.href="login_form.do";
			</script>		
		</c:when>
		
		<c:when test="${error3 eq 'stop'}">
			<script>
				alert("정지된 회원입니다.");
				location.href="login_form.do";
			</script>		
		</c:when>
		<c:when test="${error4 eq 'withdraw'}">
			<script>
				alert("탈퇴한 회원입니다.");
				location.href="login_form.do";
			</script>		
		</c:when>
	
		<c:when test="${not empty mem_id}">
			<script>
				alert("${mem_id} 님이 로그인하셨습니다.");
				location.href="../index.do";
			</script>
		</c:when>
	</c:choose>
	</c:otherwise>
</c:choose>


	
</body>
</html>