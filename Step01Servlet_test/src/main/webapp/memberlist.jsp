<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="test.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<MemberDto> list = new ArrayList<>();
	list.add(new MemberDto(1, "김태우", "수내동"));
	list.add(new MemberDto(2, "김구라", "노량진"));
	list.add(new MemberDto(3, "원숭이", "동물원"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/memberlist.jsp</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>주소</th>
			</tr>
		</thead>
		<tbody>
		<%for(MemberDto dto: list) {%>
			<tr>
				<td><%=dto.getNum() %></td>
				<td><%=dto.getName() %></td>
				<td><%=dto.getAddr() %></td>
			</tr>
		<%} %>
		</tbody>
	</table>
</body>
</html>