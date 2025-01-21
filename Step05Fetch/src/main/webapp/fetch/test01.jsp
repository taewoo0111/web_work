<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/fetch/test01.jsp</title>
</head>
<body>
	<h3>fetch 함수 테스트</h3>
	<button id="myBtn">눌러보셈</button>
	<script>
		document.querySelector("#myBtn").addEventListener("click", ()=>{
			
			fetch("${pageContext.request.contextPath }/index.jsp")
			.then(res=>res.text())
			.then((data)=>{
				console.log(data);
			});
		});
		
		/* 서버(jsp or servlet) 에서 응답한 문자열이 json 형식이면
		return res.json();
		
		그 이외의 형식이면 html, xml, plain text 등등
		return res.text(); */
		
	</script>
</body>
</html>