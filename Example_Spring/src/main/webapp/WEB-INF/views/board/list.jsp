<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<h2>${typeName }</h2>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>

<!-- <script>

	// 원하는 페이지로 이동시 검색조건, 키워드 값을 유지하기 위해 
	function list(page){
		location.href="/board/board/list.do?board_typeCode=${board_typeCode}"+
			"&curPage="+page+"&searchOption=${map2.searchOption}"+"&keyword=${map2.keyword}&board_time=${map2.board_time}";
	}
</script> -->

<div class="container" id="container">

	<form name="form1" method="post" action="list.do">
		<input type="hidden" name="board_typeCode" id="board_typeCode" value="${board_typeCode }" />
		<select name="searchOption">
			<!-- 검색조건을 검색처리후 결과화면에 보여주기위해  c:out 출력태그 사용, 삼항연산자 -->
			<option value="all" <c:out value="${map2.searchOption == 'all'?'selected':''}"/> >작성자+제목+내용</option>
			<option value="board_writer" <c:out value="${map2.searchOption == 'board_writer'?'selected':''}"/> >
				<spring:message code="board.writer"></spring:message></option>
			<option value="board_title" <c:out value="${map2.searchOption == 'board_title'?'selected':''}"/> >
				<spring:message code="board.title"></spring:message></option>
			<option value="board_content" <c:out value="${map2.searchOption == 'board_content'?'selected':''}"/> >
				<spring:message code="board.content"></spring:message></option>
		</select>
		<input name="keyword" value="${map2.keyword}"><br />
		기간 :
		<label><input type="radio" name="board_time"  id="board_time" value="1"  
					<c:out value="${map2.board_time == '1'?'checked':''}"/>/>1일전</label>
		<label><input type="radio" name="board_time" id="board_time" value="3" 
			<c:out value="${map2.board_time == '3'?'checked':''}"/>/>3일전</label>
		<label><input type="radio" name="board_time" id="board_time" value="7" 
			<c:out value="${map2.board_time == '7'?'checked':''}"/>/>7일전</label>
		<input type="submit" value="조회">
	
	</form>
	
<table  class="table table-striped table-condensed">
		<colgroup>
			<col class="col-xs-1"/>
			<col class="col-xs-1"/>
			<col class="col-xs-1"/>
			<col class="col-xs-1"/>

		</colgroup>
	<thead>
			<tr>
				<th><spring:message code="board.num"></spring:message></th>
				<th><spring:message code="board.title"></spring:message></th>
				<th><spring:message code="board.writer"></spring:message></th>
				<th><spring:message code="board.time"></spring:message></th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach var="tmp" items="${map2.list }">
				<tr>
					<td>${tmp.board_num }</td>
					<td>
						<a href="detail.do?board_typeCode=${board_typeCode }&board_num=${tmp.board_num }
							&curPage=${map2.curPage}&searchOption=${map2.searchOption}&keyword=${map2.keyword}&board_time=${map2.board_time}">
						${tmp.board_title }</a>
					</td>
					<td>${tmp.board_writer }</td>
					<td>${tmp.board_time }</td>
				</tr>
			</c:forEach>
			
			
		<!-- 페이징 -->
		<tr id="paging">
			<td colspan="5">
				<!-- 처음페이지로 이동 : 현재 페이지가 1보다 크면  [처음]하이퍼링크를 화면에 출력-->
				<a v-if="curBlock>1" v-on:click="list('1')">&#171;</a>
			
				<!-- 이전페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전]하이퍼링크를 화면에 출력 -->
				<a v-if="curBlock>1" v-on:click="list('${map2.pagingDto.prevPage}')">&#60; </a>
	
				<!-- **하나의 블럭 시작페이지부터 끝페이지까지 반복문 실행 -->
				<c:forEach var="num" begin="${map2.pagingDto.blockBegin}" end="${map2.pagingDto.blockEnd}">
				 <!-- <div v-for="n in ({{blockBengin}},{{blockEnd}})"> -->
					
					<!-- 현재페이지이면 하이퍼링크 제거	x -->	
					<%-- <span v-if="this.num===this.curPage" style="color: red" >${num }</span>&nbsp;
				
					<a v-else v-on:click="list('${num }')">${num }</a>&nbsp; 
 --%>
 					<c:choose>
						<c:when test="${num == map2.pagingDto.curPage}">
							<span style="color: red">${num}</span>&nbsp;
						</c:when>
						<c:otherwise>
							<a  v-on:click="list('${num}')">${num}</a>&nbsp;
						</c:otherwise>
					</c:choose>					
				</c:forEach>
				

				
				<!-- 다음페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 [다음]하이퍼링크를 화면에 출력 -->
				<a v-if="curBlock <= totBlock"  v-on:click="list('${map2.pagingDto.nextPage}')">&#62;</a>

				<!-- 끝페이지로 이동 : 현재 페이지가 전체 페이지보다 작거나 같으면 [끝]하이퍼링크를 화면에 출력 -->
				<a v-if="curPage<=totPage" v-on:click="list('${map2.pagingDto.totPage}')">&#187;</a>

			</td>
		</tr>
		<!-- 페이징 -->
		</tbody>
</table>

	<script>
		var paging=new Vue({
			el:"#paging",
			data:{
				curBlock:"${map2.pagingDto.curBlock}",
				prevPage:"${map2.pagingDto.prevPage}",
				blockBegin:"${map2.pagingDto.blockBegin}",
				blockEnd:"${map2.pagingDto.blockEnd}",
				curPage:"${map2.pagingDto.curPage}",
			
				totBlock:"${map2.pagingDto.totBlock}",
				totPage:"${map2.pagingDto.totPage}",
				nextPage:"${map2.pagingDto.nextPage}"
			},
			methods:{
				// 원하는 페이지로 이동시 검색조건, 키워드 값을 유지하기 위해 
				list:function(page){
					location.href="/board/board/list.do?board_typeCode=${board_typeCode}"+
					"&curPage="+page+"&searchOption=${map2.searchOption}"+"&keyword=${map2.keyword}&board_time=${map2.board_time}";
					
				}
			}
		})
	</script>

	
 	<div id="link">
		<div v-if="isNotice">
			<a v-if="isAdmin" href="private/insert_form.do?board_typeCode=${board_typeCode}">글쓰기</a>			
		</div>
		<div v-else>
			<a href="private/insert_form.do?board_typeCode=${board_typeCode}">글쓰기</a>			
			
		</div>
	</div>
	
	<script>
		var link=new Vue({
			el:"#link",
			data:{
				board_typeCode:"${board_typeCode}",
				mem_authCode:"${mem_authCode}",
				isAdmin:false,
				isNotice:false
			},
			created:function(){
				if(this.mem_authCode.length>0 && this.board_typeCode==="201"){
					this.isNotice=true;
				}
				if(this.mem_authCode.length>0&&this.mem_authCode==="003"){
					this.isAdmin=true;
				}
			}
		})
	</script>
	<!-- <script>
		new Vue({
			el:'#link',
			data:{
				board_typeCode:${board_typeCode},
				mem_authCode:${mem_authCode}
			}
			
		})
	</script>  -->
<%--  <c:choose>
	<c:when test="${board_typeCode eq '201' }">
		<c:if test="${mem_authCode eq '003' }">
			<a href="private/insert_form.do?board_typeCode=${board_typeCode}">글쓰기</a>
		</c:if>
	</c:when>
	<c:otherwise>
		<a href="private/insert_form.do?board_typeCode=${board_typeCode}">글쓰기</a>
	</c:otherwise>
</c:choose>  --%>
</div>
</body>
</html>