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

<div class="container">
	<h2 >아이디 찾기</h2>
	<br /><br />
	<c:if test="${not empty sessionScope.mem_id }">
		<script>
			alert("잘못된 접근입니다.");
			location.href="../index.jsp";
		</script>
	</c:if>

	 <form action="searchId.do" method="post">
	 	<table >
	 	<tbody >
	 		<tr>
		 		<th>
		 			<label for="mem_name">이름</label>
		 		</th>
		 		<td>
		 			<input type="text" id="mem_name" name="mem_name" required="required"/> 
		 		</td>
	 		</tr>
	 		<tr>
	 			<th>
	 				<label for="mem_email">이메일</label>
	 			</th>
	 			<td>
	 				<input type="email" id="mem_email" name="mem_email" required="required" />
	 			</td>
	 		</tr>
	 		
	 	</tbody>
	 	</table>
	 	<button type="submit" id="searchId_btn" name="searchId_btn">아이디 찾기</button>
	 </form>
		<!-- <script>
			$("#searchId_btn").on("click",function(){
				var mem_name=$("#mem_name").val();
				var mem_email=$("#mem_email").val();
				if(mem_name==null){
					alert("")
				}
			});
		</script> -->
</div>
</body>
</html>