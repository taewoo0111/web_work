<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/insertform.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
					<a href="${pageContext.request.contextPath }/">Home</a>
				</li>
				<li class="breadcrumb-item">
					<a href="${pageContext.request.contextPath }/guest/list.jsp">방명록 목록</a>
				</li>
				<li class="breadcrumb-item">새 글 작성</li>
			</ol>
		</nav>
		<h1>좋은 글을 남겨 주세요</h1>
		<form action="insert.jsp" method="post">
			<div class="mb-2">
				<label class="form-label" for="writer">작성자</label>
				<input class="form-control" type="text" name="writer" id="writer" />
			</div>
			<div class="mb-2">
				<label for="content">내용</label>
				<textarea class="form-control" name="content" id="content" style="height:200px"></textarea>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호 입력</label>
				<input class="form-control" type="password" name="pwd" id="pwd" />
			</div>
			<button class="btn btn-outline-success btn-sm" type="submit">저장</button>
		</form>
	</div>
</body>
</html>