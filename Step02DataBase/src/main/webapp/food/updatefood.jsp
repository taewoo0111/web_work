<%@page import="java.io.PrintWriter"%>
<%@page import="test.food.dao.FoodDao"%>
<%@page import="test.food.dto.FoodDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	FoodDto dto = new FoodDao().getinfo(num);
	String type = request.getParameter("type");
	String name = request.getParameter("name");
	try{
		int price = Integer.parseInt(request.getParameter("price"));
	} catch(NumberFormatException e){
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter pw = response.getWriter();
	    pw.println("<script type='text/javascript'>");
	    pw.println("alert('잘못된 가격 입력입니다.');");
	    pw.println("window.history.back();");  // 이전 페이지로 돌아가기
	    pw.println("</script>");
	};
	dto.setType(type);
	dto.setName(name);
	dto.setPrice(Integer.parseInt(request.getParameter("price")));
	//new FoodDao().update(dto);
	boolean isSuccess = new FoodDao().update(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/updatefood.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container mt-5">
		<h3>알림</h3>
		<%if(isSuccess){ %>
			<p class="alert alert-success">
				<strong><%=name %></strong> 맛집의 정보를 수정했습니다.
				<a class="alert-link" href="list.jsp">목록 보기</a>
			</p>
		<%} else{ %>
			<p class="alert alert-danger">
				맛집정보 수정 실패!
				<a class="alert-link" href="insertfoodform.jsp">다시 작성</a>
			</p>
		<%} %>
	</div>
</body>
</html>