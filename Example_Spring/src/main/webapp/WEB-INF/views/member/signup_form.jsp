<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://tiles.apache.org/tags-tiles" prefix = "tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

<div class="container">
	<h1>회원 가입 </h1>
	<form action="signup.do" method="post" id="fr">
		<spring:message code="mem.id"/> : <input type="text" name="mem_id" id="mem_id" required="required" v-model="mem_id"/> 
		<!-- <p id="exist" v-model="exist"></p> -->
		<is></is>
		<br/>
		<spring:message code="mem.pwd"/> : <input type="password" name="mem_pwd" id="mem_pwd" required="required" v-model="mem_pwd"/><br />
		비밀번호 확인 : <input type="password" name="mem_pwd2" id="mem_pwd2"  required="required" v-model="mem_pwd2"><br />
		<spring:message code="mem.name"/> : <input type="text" name="mem_name" id="mem_name" required="required" /><br />
		<spring:message code="mem.email"/> : <input type="email" name="mem_email" id="mem_email" required="required" />
		<p id="exist2" ></p>
		<br />
		
		<button type="submit" id="insertBtn" :disabled="mem_pwd!=mem_pwd2">회원 가입</button>
	</form>
</div>
	
<!-- 	<script>
		
		var mem_id=null;
		$("#mem_id").on("input",function(){
			mem_id=$("#mem_id").val();
			
			$.ajax({
				url:"${pageContext.request.contextPath }/member/existId.do",
				method:"GET",     //요청 메소드
				data:{mem_id:mem_id},   //요청파라미터:전달받은 아이디  existId.jsp?inputId=xxx  와 같은것
				success:function(responseData){   //성공적으로 응답을 하면은 이 함수가 호출됨 ,응답한 데이터는 ()안으로 들어옴,페이지 전환x
					if(responseData.isExist){ //이미 존재하는 아이디라면
						$("#exist").text("아이디가 존재합니다.");
						$("#exist").css("color","red");
						/* isExistId=true; */
						/*  $("#insertBtn").attr("disabled", true);  */
					}else{
						$("#exist").text("사용가능한 아이디입니다.");
						$("#exist").css("color","green");
						/* isExistId=false; */
						/*  $("#insertBtn").attr("disabled", false);  */
					}
					
				}
			});
			button();
			isEmpty();
		});
		
		var mem_email=null;
		$("#mem_email").on("input",function(){
			
			mem_email=$("#mem_email").val();
			$.ajax({
				url:"${pageContext.request.contextPath }/member/existEmail.do",
				method:"GET",     //요청 메소드
				data:{mem_email:mem_email},   //요청파라미터:전달받은 아이디  existId.jsp?inputId=xxx  와 같은것
				success:function(responseData){   //성공적으로 응답을 하면은 이 함수가 호출됨 ,응답한 데이터는 ()안으로 들어옴,페이지 전환x
					if(responseData.isExistEmail){ //이미 존재하는 아이디라면
						$("#exist2").text("이메일이 존재합니다.");
						$("#exist2").css("color","red");
						/* isExistEmail=true; */
						/*  $("#insertBtn").attr("disabled", true); */
					}else{
						$("#exist2").text("사용가능한 이메일입니다.");
						$("#exist2").css("color","green");
						/* isExistEmail=false; */
						/*   $("#insertBtn").attr("disabled", false);  */
					}
					
				}
			});
			button();
		});
		
		
		function button(){
			var isId=document.getElementById('exist').innerText;
			
			var isEmail=document.getElementById('exist2').innerText;
			
			var isExistId=false;
			var isExistEmail=false;
			if(isId.includes("사용")){
				isExistId=true;
			}else{
				isExisId=false;
			}
			 if(isEmail.includes("사용")){
				 isExistEmail=true;
			}else{
				isExistEmail=false;
			}
			
			if(isExistId&&isExistEmail){
				$("#insertBtn").attr("disabled", false);
			
			}else{
				$("#insertBtn").attr("disabled", true);
			} 
			
		}
		
		function isEmpty(){
			var inputId=document.getElementById('mem_id').innerText;
			console.log(inputId);
			if(inputId.length==0){
				console.log("d");
				$("#exist").text("");
				
			}
			
		}
	
		
	</script> -->
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script> 
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script>
		var vm=new Vue({
			el:'#fr',
			data:{
				mem_id:'',
				mem_pwd:'',
				mem_pwd2:'',
				mem_email:'',
				isexist:''
			},
			components:{
				"is":{
					template:'<span>{{ isexist }}</span>'
				},
				/* 'notExist':{
					template:'<span>사용가능한 아이디입니다.</span>'
				} */
			},
			methods:{
				getId:function(){
					var vm=this;
					var isexist=vm.isexist;
					axios.get("/member/existId.do")
					     .then(function(response){
					    	 console.log(isexist);
							if(response.data===mem_id){
								console.log(response.data);
								console.log(response.data.mem_id);
								isexist="이미 존재하는 아이디 입니다."
								
							}else{
								isexist="사용가능한 아이디 입니다.";
							}
							
					})
				}
			}
		})
		
	</script>
</body>
</html>