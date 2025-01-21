<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
	JSTL 사용 설정하기 
	uri = "import 할 경로"
	prefix = "접두어"
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jstl/hello.jsp</title>
</head>
<body>
	<h1>Java Standard Tag Library (JSTL)</h1>
	<c:forEach var="i" begin="0" end="9">
		<p>
			Hello JSTL <strong>${i }</strong>
		</p>
	</c:forEach>
</body>
</html>