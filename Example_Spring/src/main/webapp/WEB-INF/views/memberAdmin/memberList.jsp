<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>

<div class="container">
	<h2>회원 목록</h2>
</div>
<script>
	
	// 원하는 페이지로 이동시 검색조건, 키워드 값을 유지하기 위해 
	function list(page){
		location.href="${pageContext.request.contextPath }/memberAdmin/memberList.do?curPage="+page+"&searchOption=${map2.searchOption}"+"&keyword=${map2.keyword}"+"&mem_authCode=${map2.mem_authCode}";
	}
</script>
<br />	
<div class="container">	
	<form name="form" method="post" action="memberList.do">
		<select name="searchOption">
			<!-- 검색조건을 검색처리후 결과화면에 보여주기위해  c:out 출력태그 사용, 삼항연산자 -->
			<option value="all" <c:out value="${map2.searchOption == 'all'?'selected':''}"/> >아이디+이메일+이름</option>
			<option value="mem_id" <c:out value="${map2.searchOption == 'mem_id'?'selected':''}"/> >아이디</option>
			<option value="mem_email" <c:out value="${map2.searchOption == 'mem_email'?'selected':''}"/> >이메일</option>
			<option value="mem_name" <c:out value="${map2.searchOption == 'mem_name'?'selected':''}"/> >이름</option>
		</select>
		<input name="keyword" value="${map2.keyword}"><br />
		회원권한:
		<label><input type="radio" name="mem_authCode"  id="mem_authCode" value="001"  
					<c:out value="${map2.mem_authCode == '001'?'checked':''}"/>/>일반</label>
		<label><input type="radio" name="mem_authCode" id="mem_authCode" value="002" 
			<c:out value="${map2.mem_authCode == '002'?'checked':''}"/>/>회원관리자</label>
		<label><input type="radio" name="mem_authCode" id="mem_authCode" value="003" 
			<c:out value="${map2.mem_authCode == '003'?'checked':''}"/>/>게시판관리자</label>
		<input type="submit" value="조회">
	
	</form>
<table class="table table-striped table-condensed">
	<colgroup>
			<col class="col-xs-1"/>
			<col class="col-xs-1"/>
			<col class="col-xs-1"/>
			<col class="col-xs-1"/>
			<col class="col-xs-1"/>
			<col class="col-xs-1"/>
			<col class="col-xs-1"/>
		</colgroup>
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>상태</th>
			<th>권한</th>
			<th>가입날짜</th>
			<th>변경</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="tmp" items="${map2.list }">
			<tr>
				<td>${tmp.mem_id }</td>
				<td>${tmp.mem_name }</td>
				<td>${tmp.mem_email }</td>
				<td>${tmp.stateName }</td>
				<td>${tmp.authName }</td>
				<td>
					  <fmt:parseDate value="${tmp.mem_regdate}" var="regdate"  pattern="yyyy-MM-dd HH:mm:ss.S" scope="page"/>
					  <fmt:formatDate value="${regdate}"  pattern="yyyy.MM.dd"/>
				</td>
				<td>
					<a href=
						"updateCode_form.do?
							mem_id=${tmp.mem_id}&curPage=${map2.curPage }&
							searchOption=${map2.searchOption}&keyword=${map2.keyword}&mem_authCode=${map2.mem_authCode}">
						수정하기
					</a>
				</td>
				
			</tr>
		</c:forEach>
		
		
		<!-- 페이징 -->
		<tr>
			<td colspan="5">
				<!-- 처음페이지로 이동 : 현재 페이지가 1보다 크면  [처음]하이퍼링크를 화면에 출력-->
				<c:if test="${map2.pagingDto.curBlock > 1}">
					<a href="javascript:list('1')">&#171;</a>
				</c:if>
				
				<!-- 이전페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전]하이퍼링크를 화면에 출력 -->
				<c:if test="${map2.pagingDto.curBlock > 1}">
					<a href="javascript:list('${map2.pagingDto.prevPage}')">&#60;</a>
				</c:if>
				
				<!-- **하나의 블럭 시작페이지부터 끝페이지까지 반복문 실행 -->
				<c:forEach var="num" begin="${map2.pagingDto.blockBegin}" end="${map2.pagingDto.blockEnd}">
					<!-- 현재페이지이면 하이퍼링크 제거 -->
					<c:choose>
						<c:when test="${num == map2.pagingDto.curPage}">
							<span style="color: red">${num}</span>&nbsp;
						</c:when>
						<c:otherwise>
							<a href="javascript:list('${num}')">${num}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<!-- 다음페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 [다음]하이퍼링크를 화면에 출력 -->
				<c:if test="${map2.pagingDto.curBlock <= map2.pagingDto.totBlock}">
					<a href="javascript:list('${map2.pagingDto.nextPage}')">&#62;</a>
				</c:if>
				
				<!-- 끝페이지로 이동 : 현재 페이지가 전체 페이지보다 작거나 같으면 [끝]하이퍼링크를 화면에 출력 -->
				<c:if test="${map2.pagingDto.curPage <= map2.pagingDto.totPage}">
					<a href="javascript:list('${map2.pagingDto.totPage}')">&#187;</a>
				</c:if>
			</td>
		</tr>
		<!-- 페이징 -->
	</tbody>
</table>
</div>
</body>
</html>