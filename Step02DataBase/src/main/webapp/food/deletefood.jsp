<%@page import="test.food.dao.FoodDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	FoodDao dao = new FoodDao();
	boolean isSuccess = dao.delete(num);
	
	// context path 는 HttpServletRequest 객체를 이용해서 얻어낸 다음 사용해야 한다.
	//String cPath = request.getContextPath();
	response.sendRedirect(request.getContextPath() + "/food/list.jsp");
%>
<%-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/deletefood.jsp</title>
<style>
	#returnlist {
		transform: scale(1.5);
		transform-origin: top left;
	}
</style>
</head>
<body>
	<div class="container">
		<h3>알림</h3>
		<%if(isSuccess){ %>
			<strong><%=num %></strong> 번 맛집 정보를 삭제했습니다.
			<br>
			<button id="returnlist">리스트로 돌아가기</button>
		<%} else{ %>
			맛집정보 저장 실패!
			<a href="list.jsp">다시 삭제</a>
		<%} %>
	</div>
	<script>
		document.querySelector("#returnlist").addEventListener("click", ()=>{location.href="list.jsp"});
	</script>
</body>
</html> --%>