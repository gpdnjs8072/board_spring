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

<tiles:insertAttribute name="header" ignore="true" /> 

</head> 
<body>

<script src="${pageContext.request.contextPath }/resources/js/jquery-3.3.1.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/bootstrap.js"></script>
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

