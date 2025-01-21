<%@page import="test.food.dao.FoodDao"%>
<%@page import="test.food.dto.FoodDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String type = request.getParameter("type");
	String name = request.getParameter("name");
	int price = Integer.parseInt(request.getParameter("price"));
	
	FoodDto dto = new FoodDto();
	dto.setType(type);
	dto.setName(name);
	dto.setPrice(price);
	
	FoodDao dao = new FoodDao();
	boolean isSuccess = dao.insert(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/insert.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container mt-5">
		<h3>알림</h3>
		<%if(isSuccess){ %>
			<p class="alert alert-success">
				<strong><%=name %></strong> 맛집의 정보를 저장했습니다.
				<a class="alert-link" href="list.jsp">목록 보기</a>
			</p>
		<%} else{ %>
			<p class="alert alert-danger">
				맛집정보 저장 실패!
				<a class="alert-link" href="insertfoodform.jsp">다시 작성</a>
			</p>
		<%} %>
	</div>
</body>
</html>