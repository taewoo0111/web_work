<%@page import="test.dao.PostDao"%>
<%@page import="test.dto.SessionDto"%>
<%@page import="test.dto.PostDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	SessionDto user = (SessionDto)session.getAttribute("User");
	String writer = user.getUsername();
	
	PostDto dto = new PostDto();
	dto.setTitle(title);
	dto.setContent(content);
	dto.setWriter(writer);
	
	boolean isSuccess = PostDao.getInstance().insert(dto);
	request.setAttribute("isSuccess", isSuccess);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록 여부 확인 페이지</title>
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${isSuccess }">
				<p>
					<strong>${User.username }</strong> 님이 작성한 글을 저장했습니다.
					<a href="list.jsp">확인</a>
				</p>
			</c:when>
			<c:otherwise>
				<p>
					글 저장 실패!
					<a href="insertForm.jsp">다시 작성</a>
				</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>