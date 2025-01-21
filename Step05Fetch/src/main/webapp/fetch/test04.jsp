<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/fetch/test04.jsp</title>
</head>
<body>
	<button id="getBtn">친구이름 목록 받아오기</button>
	<ul id="friendsList">
	
	</ul>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script>
		$("#getBtn").on("click", ()=>{
			fetch("friends.jsp")
			.then(res=>res.json())
			.then(data=>{
				console.log(data);
				for(let i=0; i<data.length; i++){
					//const newli = document.createElement("li");
					//newli.innerText = data[i];
					//document.querySelector("#friendsList").append(newli);
					$("#friendsList").append($("<li>").text(data[i]));
				}
			})
			.catch(error=>{
				console.log("에러정보:" + error);
			});
			
		});
	</script>
</body>
</html>