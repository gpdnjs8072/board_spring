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
	<div id="table">
		<table>
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
		
			<tr v-if="file_oriName.length>0">
				<th>첨부파일</th>
				<td><a href="private/download.do?board_typeCode=${dto.board_typeCode }&file_num=${fileDto.file_num}&board_num=${dto.board_num}">${fileDto.file_oriName }</a></td>
			</tr>
			
		</table>
	</div>
	<br/>
	<br/>
	

	
	<div id="link">
		<a href="list.do?board_typeCode=${dto.board_typeCode }
		&curPage=${map.curPage }&searchOption=${map.searchOption}&keyword=${map.keyword}&board_time=${map.board_time}">목록 보기</a>

		<!-- board_writer = mem_id -->
		<a v-if="isEqual"
			href="private/update_form.do?board_num=${dto.board_num }&board_typeCode=${board_typeCode}
			&curPage=${map.curPage }&searchOption=${map.searchOption}&keyword=${map.keyword}&board_time=${map.board_time}">
		수정
		</a>
		
		<!-- board_writer=mem_id  or  mem_authCode='002'  작성자이거나 관리자 -->
		<a v-if="check" v-on:click="deleteConfirm" >삭제</a>
		
		<!-- 원글인 경우만 답글가능 -->
		<a v-if="board_subNum==='0'" 
		   href="private/reply_form.do?board_num=${dto.board_num }&board_typeCode=${board_typeCode}">답글쓰기</a>
		
	</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script> 
<script>
	var table=new Vue({
		el:"#table",
		data:{
			file_oriName:"${fileDto.file_oriName}"
		}
	});

	
	var link=new Vue({
		el:"#link",
		data:{
			board_writer:"${dto.board_writer}",
			mem_id:"${mem_id}",
			mem_authCode:"${mem_authCode}",
			board_subNum:"${board_subNum}",
			isEqual:false,
			check:false
		},
		created:function(){
			if(this.board_writer===this.mem_id){

				this.isEqual=true;
			}
			if(this.isEqual||this.mem_authCode==="002"){
				this.check=true;
			}
		},
		methods:{
			deleteConfirm:function(){
				var isDelete=confirm("글을 삭제하시겠습니까?");
				if(isDelete){
					location.replace("private/delete.do?board_num=${dto.board_num}&board_typeCode=${board_typeCode}"+
						"&curPage=${map.curPage }&searchOption=${map.searchOption}&keyword=${map.keyword}&board_time=${map.board_time}");
				}
			}
			
		}
	
	})
</script>
</body>
</html>