<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// 여기 안의 영역은 service() 메소드 안의 영역과 같다	
	String fortuneToday = "서쪽으로 가면 강남역을 만나요!";
	// 요청 파리미터 출력
	String msg = request.getParameter("msg");
	// 콘솔에 출력
	System.out.println(msg);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>send.jsp</title>
</head>
<body>
	<h1>나는 jsp 페이지</h1>
	<p>오늘의 운세: <strong><%out.print(fortuneToday); %></strong></p>
	<p>오늘의 운세: <strong><%=fortuneToday %></strong></p>
</body>
</html>