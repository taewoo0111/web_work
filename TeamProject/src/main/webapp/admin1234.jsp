<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div>
			웹 이용하는 회사들 등록
			<br />
			<form action="admin/insertCompany.jsp" method="get">
			<input type="text" name="comname" placeholder="회사이름" />
			<button id="addCompany">회사 추가</button>
			</form>
		</div>
		<div>
			웹 이용하는 회사의 체인점 등록
			<br />
			<form action="admin/insertStore.jsp">
			<input type="text" name="storecall" placeholder="전화번호"/>
			<button id="addStore">매장 추가</button>
			</form>
		</div>
		<div>
			웹 이용하는 회사의 관리자 계정 등록
			<br />
			<form action="admin/insertCeo.jsp" method="get">
			<input type="text" name="comid" placeholder="회사번호" />
			<input type="text" name="ename" placeholder="이름"/>
			<input type="text" name="ecall" placeholder="전화번호"/>
			<input type="text" name="epwd" placeholder="비밀번호"/>
			<button id="addCeo">관리자 추가</button>
			</form>
		</div>
	</div>
</body>
</html>