<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/fetch/test02.jsp</title>
</head>
<body>
	<input type="text" id="msg" placeholder="메세지 입력..." />
	<button id="sendBtn">전송</button>
	<button id="sendBtn2">전송2</button>
	<script>
		document.querySelector("#sendBtn").addEventListener("click", ()=>{
			const msg = document.querySelector("#msg").value;
			fetch("send.jsp?msg=" + msg)
			.then(res=>res.text())
			.then(data=>{
				console.log(data);
			});
		});
	</script>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script>
		$("#sendBtn2").on("click",()=>{
			const msg = $("#msg").val();
			fetch("send2.jsp?msg=" + msg)
			.then(res=>res.json())
			.then(data=>{
				console.log(data);
			})
			.catch(error=>{
				console.log("에러정보:" + error);
			})
		});
	</script>
</body>
</html>