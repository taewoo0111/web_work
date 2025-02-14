<%@page import="test.dao.PostDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	
	PostDao.getInstance().deleteReaded(num);
	boolean isSuccess = PostDao.getInstance().delete(num);
	request.setAttribute("isSuccess", isSuccess);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제 여부 확인 페이지</title>
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${isSuccess }">
				<p>
					<strong>${User.username }</strong> 님이 작성한 글을 삭제했습니다.
					<a href="list.jsp">확인</a>
				</p>
			</c:when>
			<c:otherwise>
				<p>
					글 저장 실패!
					<a href="view.jsp?num=<%=num%>">다시 삭제</a>
				</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>