<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/insertfood.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
				<a href="${pageContext.request.contextPath }/">Home</a>
				</li>
				<li class="breadcrumb-item">
					<a href="${pageContext.request.contextPath }/food/list.jsp">맛집목록</a>
				</li>
				<li class="breadcrumb-item">맛집추가</li>
			</ol>
		</nav>
		<h1>맛집 추가폼</h1>
	</div>	
		<form action="${pageContext.request.contextPath }/food/insertfood.jsp" method="post">
			<fieldset>
    			<legend class="container-fluid ml-3">종류를 선택하세요</legend>
    			<div class="container-fluid mb-3">
    			<label class="form-label"><input type="radio" name="type" value="한식"> 한식</label>
    			<label class="form-label"><input type="radio" name="type" value="중식"> 중식</label>
    			<label class="form-label"><input type="radio" name="type" value="양식"> 양식</label>
    			<label class="form-label"><input type="radio" name="type" value="일식"> 일식</label>
    			<label class="form-label"><input type="radio" name="type" value="기타"> 기타</label>
    			</div>
  			</fieldset>
  			<div class="container-fluid mt-3">
			<fieldset>
				<label class="form-label" for="name">이름</label>
				<input class="form-control" type="text" name="name" id="name" placeholder="이름 입력..." />
			</fieldset>
			</div>
			<div class="container-fluid mt-3">
			<fieldset>
				<label class="form-label" for="price">가격</label>
				<input class="form-control" type="text" name="price" id="price" placeholder="가격 입력..." />
			</fieldset>
			</div>
			<br>
			<div class="container-fluid mt-3">
			<button class="btn btn-success" type="submit">저장</button>
			</div>
		</form>
</body>
</html>