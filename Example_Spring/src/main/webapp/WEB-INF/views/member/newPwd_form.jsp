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
<div class="container">
<h2 >새로운 비밀번호 입력</h2>
	<br /><br />
	<c:if test="${not empty sessionScope.mem_id }">
		<script>
			alert("잘못된 접근입니다.");
			location.href="../index.jsp";
		</script>
	</c:if>

	<form action="newPwd.do" method="post">
		<input type="hidden" name="mem_id" id="mem_id" value="${mem_id }"/>
		<div>
			<label for="mem_pwd">새 비밀번호</label>
			<input type="text" name="mem_pwd" id="mem_pwd" required="required" />
		</div>
		<div>
			<label for="mem_pwd2">새 비밀번호 확인</label>
			<input type="text" name="mem_pwd2" id="mem_pwd2"  required="required"/>
		</div>
		<button type="submit" id="changeBtn">비밀번호 변경</button>
	</form>
	<script>
		$("#changeBtn").click(function(){
			var pwd=$("#mem_pwd").val();
			var pwd2=$("#mem_pwd2").val();
			if(pwd!=pwd2){
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
		})
		$("#changeBtn").on("click",function(){
			var pwd=$("#mem_pwd").text();
			var pwd2=$("#mem_pwd2").text();
			if(pwd!=pwd2){
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
		});
	</script>
	</div>
</body>
</html>