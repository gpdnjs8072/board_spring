<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<!DOCTYPE html> 
<html>
<head> 
	<meta charset="UTF-8" /> 
	<title>title</title> 
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.3.1.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/bootstrap.js"></script>

	<script src="//code.jquery.com/jquery.min.js"></script>
        
        <!-- BootStrap -->
        <!-- 합쳐지고 최소화된 최신 CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <!-- 부가적인 테마 -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
        <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<tiles:insertAttribute name="header" ignore="true" /> 

</head> 
<body>
	<section id="content"> 
	<div class="container" style="margin-top: 100px;margin-bottom: 100px">
		<tiles:insertAttribute name="body" ignore="true" /> 
	</div>
	</section> 
	<footer id="footer"> 
		<tiles:insertAttribute name="footer" ignore="true" /> 
	</footer> 
</body> 
</html>

