<%@page import="test.food.dto.FoodDto"%>
<%@page import="java.util.List"%>
<%@page import="test.food.dao.FoodDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	FoodDao dao = new FoodDao();
	List<FoodDto> list = dao.getList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/list.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/include/navbar.jsp">
		<jsp:param value="food" name="current"/>
	</jsp:include>
	<div class = "container-fluid mt-3 ml-2">
		<h1>맛집 추가하는 버튼입니다</h1>
		<button class="btn btn-success mt-3" id="addBtn">
			맛집 추가
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square-fill" viewBox="0 0 16 16">
  				<path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm6.5 4.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3a.5.5 0 0 1 1 0"/>
			</svg>
		</button>
	</div>
	<div class = "container-fluid mt-3 ml-2">
		<h1>맛집 목록입니다.</h1>
		<table class="table table-bordered">
			<colgroup>
                <col width="50">
                <col width="100">
                <col width="100">
                <col width="100">
                <col width="100">
                <col width="100">
            </colgroup>
			<thead class="table-info">
				<tr>
					<th>번호</th>
					<th>종류</th>
					<th>이름</th>
					<th>가격</th>
					<th>삭제버튼</th>
					<th>수정버튼</th>
				</tr>
			</thead>
			<tbody>
			<% for(FoodDto dto: list){%>
				<tr>
					<td><%=dto.getNum() %></td>
					<td><%=dto.getType() %></td>
					<td><%=dto.getName() %></td>
					<td><%=dto.getPrice() %></td>
					<td><button class="btn btn-danger" onclick="javascript: deleteConfirm('<%=dto.getNum()%>')">
							삭제 
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-square-fill" viewBox="0 0 16 16">
  								<path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708"/>
							</svg>
						</button></td>
					<td><button class="btn btn-primary" onclick="location.href='updatefoodform.jsp?num=<%=dto.getNum()%>'">
					수정
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-tools" viewBox="0 0 16 16">
  						<path d="M1 0 0 1l2.2 3.081a1 1 0 0 0 .815.419h.07a1 1 0 0 1 .708.293l2.675 2.675-2.617 2.654A3.003 3.003 0 0 0 0 13a3 3 0 1 0 5.878-.851l2.654-2.617.968.968-.305.914a1 1 0 0 0 .242 1.023l3.27 3.27a.997.997 0 0 0 1.414 0l1.586-1.586a.997.997 0 0 0 0-1.414l-3.27-3.27a1 1 0 0 0-1.023-.242L10.5 9.5l-.96-.96 2.68-2.643A3.005 3.005 0 0 0 16 3q0-.405-.102-.777l-2.14 2.141L12 4l-.364-1.757L13.777.102a3 3 0 0 0-3.675 3.68L7.462 6.46 4.793 3.793a1 1 0 0 1-.293-.707v-.071a1 1 0 0 0-.419-.814zm9.646 10.646a.5.5 0 0 1 .708 0l2.914 2.915a.5.5 0 0 1-.707.707l-2.915-2.914a.5.5 0 0 1 0-.708M3 11l.471.242.529.026.287.445.445.287.026.529L5 13l-.242.471-.026.529-.445.287-.287.445-.529.026L3 15l-.471-.242L2 14.732l-.287-.445L1.268 14l-.026-.529L1 13l.242-.471.026-.529.445-.287.287-.445.529-.026z"/>
					</svg>
						</button></td>
				</tr>
			<%} %>			
			</tbody>
		</table>
	</div>
	<script>
		document.querySelector("#addBtn").addEventListener("click", ()=>{location.href="insertfoodform.jsp"});
		function deleteConfirm(num){
			const isDelete = confirm("삭제하시겠습니까?");
			if(isDelete){
				location.href = "deletefood.jsp?num="+num;
			};
		};
	</script>
</body>
</html>