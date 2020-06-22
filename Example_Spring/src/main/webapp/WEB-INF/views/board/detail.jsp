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
	<h2>글 상세보기</h2>
	<table >
		
		<tr>
			<th>글 번호</th>
			<td>${dto.board_num }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.board_writer }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.board_title}</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${dto.board_time}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${dto.board_content }</td>
		</tr>
		<c:if test="${not empty fileDto.file_oriName }">
			<tr>
				<th>첨부파일</th>
				<td><a href="private/download.do?board_typeCode=${dto.board_typeCode }&file_num=${fileDto.file_num}&board_num=${dto.board_num}">${fileDto.file_oriName }</a></td>
			</tr>
		
		</c:if>
	</table>
	<br/>
	<br/>
	
	
	<a href="list.do?board_typeCode=${dto.board_typeCode }">목록 보기</a>

		<c:if test="${dto.board_writer eq mem_id }">
			<a href="private/update_form.do?board_num=${dto.board_num }&board_typeCode=${board_typeCode}">
			수정
			</a>
		</c:if>
		

		<c:if test="${(dto.board_writer eq mem_id) or (mem_authCode eq '002') }">
			
			<a href="javascript:deleteConfirm()">삭제</a>
		</c:if>
</div>
<script>
	function deleteConfirm(){
		var isDelete=confirm("글을 삭제하시겠습니까?");
		if(isDelete){
			location.href="private/delete.do?board_num=${dto.board_num}&board_typeCode=${board_typeCode}";
		}
	}
</script>
</body>
</html>