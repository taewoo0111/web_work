<%@page import="java.sql.Connection"%>
<%@page import="test.util.DbcpBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// DbcpBean 객체를 이용해서 Connection 객체 얻어오기
	Connection conn = new DbcpBean().getConn();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/connection/test.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container border border-primary-subtle mt-3">
		<h1>오라클 연결 확인 페이지</h1>
			<h3 >알림</h3>
			<%if(conn != null){ %>	
				<p class="alert alert-success">
					오라클 연결 성공!
				</p>
			<%} else{ %>
				<p class="alert alert-danger">
					오라클 연결 실패!
				</p>
			<%} %>
			<button class="btn btn-info mt-3" id="mvBtn">
				목록 이동
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-skip-backward-btn" viewBox="0 0 16 16">
  					<path d="M11.21 5.093A.5.5 0 0 1 12 5.5v5a.5.5 0 0 1-.79.407L8.5 8.972V10.5a.5.5 0 0 1-.79.407L5 8.972V10.5a.5.5 0 0 1-1 0v-5a.5.5 0 0 1 1 0v1.528l2.71-1.935a.5.5 0 0 1 .79.407v1.528z"/>
  					<path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm15 0a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1z"/>
				</svg>
			</button>
	</div>	
	<script>
		document.querySelector("#mvBtn").addEventListener("click", ()=>{
			location.href = "../index.jsp";
		});
	</script>
</body>
</html>
<%
	if(conn!=null)conn.close(); // close()를 호출하면 Connection 이 Pool 에 반환된다.
%>