<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String content = request.getParameter("content");
	String content2 = content.replace("\"", "\\\""); // -> " 을 \" 로 변경
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/toastui/save.jsp</title>
	<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
 	<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
 	<style>
        .container{
            width: 80%;
            margin: 0 auto;
        }
      </style>
</head>
<body>
	<div class="container">
		<h1>게시글</h1>
		<div><%=content %></div>
		
		<h1>글 수정 폼</h1>
		<div id="editor"></div>
		<form action="save.jsp" method="post" id="updateForm">
        	<input type="hidden" name="content" id="content"/>
        	<button type="submit">저장</button>
        </form>
	</div>
	<script>
    	const Editor = toastui.Editor;
    	const editor = new Editor({
        	el: document.querySelector('#editor'),
        	height: '500px',
        	initialEditType: 'markdown',
        	previewStyle: 'vertical'
    	});
    	
    	editor.setHTML("<%=content2 %>");
    	document.querySelector("#updateForm").addEventListener("submit", ()=>{
        	document.querySelector("#content").value = editor.getHTML();
        });
	</script>
</body>
</html>