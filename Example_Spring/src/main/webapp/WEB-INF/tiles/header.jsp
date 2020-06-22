<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://tiles.apache.org/tags-tiles" prefix = "tiles"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<!-- 홈페이지 링크와 버튼을 넣어둘 div -->
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath }/index.do">
				HOME
			</a>
			<button class="navbar-toggle" data-toggle="collapse" data-target="#one">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
		</div>
		<!-- xs 영역에서는 숨겨졌다가 버튼을 누르면 나오게 할 컨텐츠를 넣을 div -->
		<div class="collapse navbar-collapse" id="one">
			<ul class="nav navbar-nav">
				<li>
					<a href="${pageContext.request.contextPath }/board/list.do?board_typeCode=201 ">
						공지사항
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath }/board/list.do?board_typeCode=202 ">
						Q&A
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath }/board/list.do?board_typeCode=203">
						자유게시판
					</a>
				</li>
				<c:if test="${sessionScope.mem_authCode eq '002' }">
					<li>
						<a href="${pageContext.request.contextPath }/memberAdmin/memberList.do ">
							회원목록
						</a>
					</li>
				</c:if>
			</ul>
			<c:choose>
				<c:when test="${empty sessionScope.mem_id }">
					<div class="pull-right">
						<!-- btn-xs : 작은버튼 ,navbar-btn :navbar에 쓰는 버튼 -->
						<a class="btn btn-primary navbar-btn btn-xs " href="${pageContext.request.contextPath }/member/login_form.do">로그인</a>
						<a class="btn btn-warning navbar-btn btn-xs " href="${pageContext.request.contextPath }/member/signup_form.do">회원가입</a>
					</div>
				</c:when>
				<c:otherwise>
					<p class="navbar-text pull-right">
						<c:if test="${sessionScope.mem_authCode eq '002' }">
							<strong>관리자</strong>
						</c:if>
						<strong>
							<a class="navbar-link" href="${pageContext.request.contextPath }/member/private/info_form.do">
								${sessionScope.mem_id }
							</a>
						</strong>
						<a class="navbar-link" href="${pageContext.request.contextPath }/member/private/logout.do">로그아웃</a>
					</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>