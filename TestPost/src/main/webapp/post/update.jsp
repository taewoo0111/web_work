<%@page import="test.dao.PostDao"%>
<%@page import="test.dto.PostDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	int num=Integer.parseInt(request.getParameter("num"));
	String title=request.getParameter("title");
	String content=request.getParameter("content");
	
	PostDto dto = new PostDto();
	dto.setNum(num);
	dto.setTitle(title);
	dto.setContent(content);
	
	boolean isSuccess = PostDao.getInstance().update(dto);
	request.setAttribute("isSuccess", isSuccess);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 확인 페이지</title>
</head>
<body>
	<c:choose>
		<c:when test="${isSuccess }">
			<script>
				alert("수정 했습니다");
				location.href="view.jsp?num=${param.num}";
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert("수정실패!");
				location.href="updateForm.jsp?num=${param.num}";
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>