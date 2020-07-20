<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<h2>회원 정보 수정</h2>
	<form action="info.do" method="post" id="fr">
		<table>	
			<tr>
				<th>아이디</th>
				<td><input type="hidden" name="mem_id" id="mem_id" value="${dto.mem_id }"/>${dto.mem_id }</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="mem_pwd" id="mem_pwd" required="required" v-model="mem_pwd"/></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="mem_pwd2" id="mem_pwd2" required="required" v-model="mem_pwd2" /></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="mem_name" id="mem_name" value="${dto.mem_name }" required="required" /></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" name="mem_email" id="mem_email" value="${dto.mem_email }" required="required" /></td>
			</tr>
			<tr>
				<th>회원 권한</th>
				<td>${dto.authName }</td>
			</tr>
			<tr>
				<th>회원 상태</th>
				<td>${dto.stateName }</td>
			</tr>
			<tr>
				<th>가입날짜</th>
				<td>${dto.mem_regdate }</td>
			</tr>
		</table>
		<button type="submit" id="updateBtn" :disabled="mem_pwd!=mem_pwd2">수정하기</button>
		<button id="deleteBtn" v-on:click="deleteBtn" >탈퇴하기</button>
	</form>
</div>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>

	<script>
	new Vue({
		el:"#fr",
		data:{
			mem_pwd:'',
			mem_pwd2:''
		},
		methods:{
			deleteBtn : function(){
				var isDelete=confirm("탈퇴하시겠습니까?");
				if(isDelete){
					location.href="delete.do";
				}else{
					false;
				}
			}
		}
	})
	</script>
</body>
</html>