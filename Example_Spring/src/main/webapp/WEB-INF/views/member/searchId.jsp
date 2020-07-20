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
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

	<div id="app">
	 
		<h3>아이디는 ${mem_id} 입니다.</h3>
		<a href="login_form.do">로그인</a>
		<a href="searchPwd_form.do">비밀번호찾기</a>
	</div>
	<script>
		 new Vue({
			el:"#app",
			
			data:{
				mem_id:"${requestScope.mem_id}",
				notExist:"${notExist}"
				
			},
			created:function(){
			
				if(this.notExist=="notExist"){
					
					alert("사용자 인증에 실패했습니다.");
					location.href="searchId_form.do";
				} 
			},
			
			
		}) 
		
	</script>
	
</body>
</html>