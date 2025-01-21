<%@page import="test.food.dto.FoodDto"%>
<%@page import="test.food.dao.FoodDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	FoodDto dto = new FoodDao().getinfo(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/updatefoodform.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
				<a href="${pageContext.request.contextPath }/">Home</a>
				</li>
				<li class="breadcrumb-item">
					<a href="${pageContext.request.contextPath }/food/list.jsp">맛집목록</a>
				</li>
				<li class="breadcrumb-item">맛집추가</li>
			</ol>
		</nav>
	</div>
	<div class="container-fluid mt-3">
		<h1>선택한 정보를 보여드릴게요</h1>
		<p>번호: <%=dto.getNum() %></p>
		<p>종류: <%=dto.getType() %></p>
		<p>이름: <%=dto.getName() %></p>
		<p>가격: <%=dto.getPrice() %></p>
	</div>
	<div class="container-fluid mt-3">
		<h1>맛집 수정하기</h1>
		<form action="updatefood.jsp?num=<%=dto.getNum() %>" method = "post">
			<!-- 화면에 보이지는 않지만 폼 제출할 때 같이 전송되는 값
			<input type="hidden" name="num" value="" /> -->
			<fieldset>
    			<legend>종류를 선택하세요</legend>
    			<label><input type="radio" name="type" value="한식"> 한식</label>
    			<label class="form-label"><input type="radio" name="type" value="중식"> 중식</label>
    			<label class="form-label"><input type="radio" name="type" value="양식"> 양식</label>
    			<label class="form-label"><input type="radio" name="type" value="일식"> 일식</label>
    			<label class="form-label"><input type="radio" name="type" value="기타"> 기타</label>
  			</fieldset>
			<fieldset>
				<label class="form-label" for="name">이름</label>
				<input class="form-control" type="text" name="name" id="name" placeholder="이름 입력..." />
			</fieldset>
			<fieldset>
				<label class="form-label" for="price">가격</label>
				<input class="form-control" type="text" name="price" id="price" placeholder="가격 입력..." />
			</fieldset>
			<br>
			<button class="btn btn-success" type="submit">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-tools" viewBox="0 0 16 16">
  						<path d="M1 0 0 1l2.2 3.081a1 1 0 0 0 .815.419h.07a1 1 0 0 1 .708.293l2.675 2.675-2.617 2.654A3.003 3.003 0 0 0 0 13a3 3 0 1 0 5.878-.851l2.654-2.617.968.968-.305.914a1 1 0 0 0 .242 1.023l3.27 3.27a.997.997 0 0 0 1.414 0l1.586-1.586a.997.997 0 0 0 0-1.414l-3.27-3.27a1 1 0 0 0-1.023-.242L10.5 9.5l-.96-.96 2.68-2.643A3.005 3.005 0 0 0 16 3q0-.405-.102-.777l-2.14 2.141L12 4l-.364-1.757L13.777.102a3 3 0 0 0-3.675 3.68L7.462 6.46 4.793 3.793a1 1 0 0 1-.293-.707v-.071a1 1 0 0 0-.419-.814zm9.646 10.646a.5.5 0 0 1 .708 0l2.914 2.915a.5.5 0 0 1-.707.707l-2.915-2.914a.5.5 0 0 1 0-.708M3 11l.471.242.529.026.287.445.445.287.026.529L5 13l-.242.471-.026.529-.445.287-.287.445-.529.026L3 15l-.471-.242L2 14.732l-.287-.445L1.268 14l-.026-.529L1 13l.242-.471.026-.529.445-.287.287-.445.529-.026z"/>
				</svg>
				수정</button>
		</form>
	</div>
	<br>
	<div class="container-fluid">
	<button class="btn btn-warning" id="returnBtn">
		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-left" viewBox="0 0 16 16">
 			 <path fill-rule="evenodd" d="M14.5 1.5a.5.5 0 0 1 .5.5v4.8a2.5 2.5 0 0 1-2.5 2.5H2.707l3.347 3.346a.5.5 0 0 1-.708.708l-4.2-4.2a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 8.3H12.5A1.5 1.5 0 0 0 14 6.8V2a.5.5 0 0 1 .5-.5"/>
		</svg>
		이전 페이지로 가기</button>
	</div>
	<script>
		document.querySelector("#returnBtn").addEventListener("click", ()=>{location.href="list.jsp"});
	</script>
</body>
</html>