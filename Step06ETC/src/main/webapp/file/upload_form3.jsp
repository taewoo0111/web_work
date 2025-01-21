<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/file/upload_form3.jsp</title>
</head>
<body>
	<div class="container">
		<h3>이미지 업로드 폼</h3>
		<form action="${pageContext.request.contextPath }/file/upload3" method="post" enctype="multipart/form-data" id="myForm">
			<input type="text" name="title" placeholder="설명 입력..." />
			<br />
			이미지<input type="file" name="myImage" accept="image/*" />
			<br />
			<button type="submit">업로드</button>
		</form>
		<img id="image" width="300" />
	</div>
	<script>
		document.querySelector("#myForm").addEventListener("submit", (event)=>{
			event.preventDefault();
			// event.target => 해당 이벤트가 발생한 바로 그 요소의 참조값 (form의 참조값)
			const data = new FormData(event.target);
			fetch("${pageContext.request.contextPath }/file/upload3", {
				method:"post",
				body:data
			})
			.then(res=>res.json())
			.then(data=>{
				console.log(data);
				const requestPath = "${pageContext.request.contextPath }/upload/" + data.saveFileName;
				document.querySelector("#image").setAttribute("src", requestPath);
			});
		});
	</script>
</body>
</html>