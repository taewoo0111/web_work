<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cooks = request.getCookies();
	
	for(Cookie tmp:cooks){
		String key = tmp.getName();
		String value = tmp.getValue();
		
		System.out.println("key: " + key + " value: " + value );
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_cookie/cookie_read.jsp</title>
</head>
<body>
	<div class="container">
		<h1>저장된 쿠키 목록</h1>
		<table>
			<thead>
				<tr>
					<th>key</th>
					<th>value</th>
				</tr>
			</thead>
			<tbody>
				<%for(Cookie tmp:cooks){ %>
					<tr>
						<td><%=tmp.getName() %></td>
						<td><%=tmp.getValue() %></td>
					</tr>
				<%} %>
			</tbody>
		</table>
		<a href="cookie_form.jsp">다시 테스트</a>
	</div>
</body>
</html>