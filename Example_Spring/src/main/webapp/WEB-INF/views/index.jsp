<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h3>INDEX</h3>
	<div class="well">
		<spring:message code="language"/>
		<select class="form-control" id="testBox">
			<option>선택</option>
			<option value="ko">한국어</option>
			<option value="en">English</option>
		</select>
		
		<p>mem.id : <spring:message code="mem.id" ></spring:message></p>
		<p>board.title : <spring:message code="board.title"></spring:message></p> 

	</div>
</div>

	<script>
		$(document).ready(function(){
			$('#testBox').on('change', function(){
				var lan = $('#testBox').val();
				location.href='<c:url value="index.do?lang='+lan+'"/>';	
			});
		
		});
	</script>
</body>
</html>