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
<script>
	new Vue({
		el:"#container",
		data:{
			url:"${url}",
			mem_id:"${mem_id}",
			error1:"${error1}",
			error2:"${error2}",
			error3:"${error3}",
			error4:"${error4}",
		},
		created:function(){
			if(this.url!="no"){
				if(this.error1.length>0){
					alert("5회 이상 실패로 해당 아이디가 정지되었습니다.");
					location.href="login_form.do?url=${encodedUrl}";
				}else if(this.error2.length>0){
					alert("사용자 인증정보가 잘못되었습니다.");
					location.href="login_form.do?url=${encodedUrl}";
				}else if(this.error3.length>0){
					alert("정지된 회원입니다.");
					location.href="login_form.do?url=${encodedUrl}";
				}else if(this.error4.length>0){
					alert("탈퇴한 회원입니다.");
					location.href="login_form.do?url=${encodedUrl}";
				}else if(this.mem_id.length>0){
					alert("${mem_id} 님이 로그인하셨습니다.");
					location.href="${url}";
				}
			}else{
				if(this.error1.length>0){
					alert("5회 이상 실패로 해당 아이디가 정지되었습니다.");
					location.href="login_form.do";
				}else if(this.error2.length>0){
					alert("사용자 인증정보가 잘못되었습니다.");
					location.href="login_form.do";
				
				}else if(this.error3.length>0){
					alert("정지된 회원입니다.");
					location.href="login_form.do";
				
				}else if(this.error4.length>0){
					alert("탈퇴한 회원입니다.");
					location.href="login_form.do";
				}else if(this.mem_id.length>0){
					alert("${mem_id} 님이 로그인하셨습니다.");
					location.href="../index.do";
				}
			}
		}
		
	})
</script>
	
</body>
</html>