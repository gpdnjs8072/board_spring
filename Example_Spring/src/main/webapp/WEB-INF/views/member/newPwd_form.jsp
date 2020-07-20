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
	

	<form action="newPwd.do" method="post" id="fr">
		<input type="hidden" name="mem_id" id="mem_id" value="${mem_id }"/>
		<div>
			<label for="mem_pwd">새 비밀번호</label>
			<input type="text" name="mem_pwd" id="mem_pwd" required="required" v-model="mem_pwd"/>
		</div>
		<div>
			<label for="mem_pwd2">새 비밀번호 확인</label>
			<input type="text" name="mem_pwd2" id="mem_pwd2"  required="required" v-model="mem_pwd2"/>
		</div>
		<button type="submit" id="changeBtn" :disabled="mem_pwd!=mem_pwd2">비밀번호 변경</button>
	</form>

	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>
	<script>
		//비밀번호와 새비밀번호의 길이가 맞지않으면 버튼을 비활성화 시킴
		new Vue({
			el:"#fr",
			data:{
				mem_pwd:'',
				mem_pwd2:''
			}
		})
	</script>
</div>
</body>
</html>



