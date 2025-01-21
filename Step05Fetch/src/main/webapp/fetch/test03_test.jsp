<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/fetch/test05.jsp</title>
</head>
<body>
	<button id="btn">버튼</button>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script>
		console.log("1");
		
		$("#btn").on("click", ()=>{
			
			console.log("2");
			
			fetch("send2.jsp")
			.then(res=>res.json())
			.then(data=>{
				console.log(data);
				console.log("3");
			})
			
			.catch(error=>{
				console.log("에러정보:" + error);
			});
			
			console.log("4");
		});
		
		console.log("5");
	</script>
</body>
</html>