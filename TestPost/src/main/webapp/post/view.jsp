<%@page import="test.dto.SessionDto"%>
<%@page import="test.dao.PostDao"%>
<%@page import="test.dto.PostDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	PostDto dto = PostDao.getInstance().getData(num);
	
	SessionDto user = (SessionDto)session.getAttribute("User");
	String session_id = user.getId();
	
	boolean isReaded = PostDao.getInstance().isReaded(num, session_id);
	if(!isReaded){
		PostDao.getInstance().addViewCount(num);
		PostDao.getInstance().insertReaded(num, session_id);
	}
	request.setAttribute("dto", dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보는 페이지</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
<style>
	 #contents {
        margin-top: 20px;
        padding: 20px;
        background-color: #fefefe;
        border-radius: 10px;
        box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
        border: 1px solid #ddd;
        transition: transform 0.3s ease, box-shadow 0.3s ease;
    }
    #contents:hover {
        transform: translateY(-5px);
        box-shadow: 0 15px 30px rgba(0, 0, 0, 0.3);
    }
    
    #content{
    	width: 100%;
    	height: 300px;
    }
</style>
</head>
<body>
	<div class="container">
		<h1>글 상세 보기</h1>
			<table class="table table-bordered">
			<tr>
				<th>글번호</th>
				<td>${dto.num }</td>
			</tr>
			<tr>
				<th>작성자(닉네임)</th>
				<td>${dto.writer }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.title }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${dto.viewcount }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${dto.createdAt }</td>
			</tr>
			<tr>
				<th>수정일</th>
				<td>${dto.updatedAt }</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="contents">${dto.content }</div>
				</td>
			</tr>
		</table>
		<c:if test="${dto.writer eq User.username }">
			<a href="updateForm.jsp?num=${dto.num }" class="btn btn-success">수정</a>
			<a href="javascript:" onclick="deleteConfirm()" class="btn btn-danger">삭제</a>
			<script>
				function deleteConfirm(){
					const isDelete = confirm("정말로 삭제하시겠습니까?");
					if(isDelete){
						window.location.href = "delete.jsp?num=${dto.num}";
					}
				}
			</script>
		</c:if>
	</div>
</body>
</html>