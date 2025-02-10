<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Step13_toasteditor.html</title>
    <head>
        <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
        <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
      </head>
      <style>
        .container{
            width: 80%;
            margin: 0 auto;
        }
      </style>
</head>
<body>
    <div class="container">
        <h1>Toast UI Editor 사용하기</h1>
        <div id="editor"></div>
        <form action="save.jsp" method="post" id="saveForm">
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
        /*
        	에디터에 입력한 내용 읽어오는 방법
        	
        	1. markdown
        	editor.getMarkdown();
        
        	2. html
        	editor.getHTML();
        */
        document.querySelector("#saveForm").addEventListener("submit", ()=>{
        	document.querySelector("#content").value = editor.getHTML();
        });
    </script> 
</body>
</html>