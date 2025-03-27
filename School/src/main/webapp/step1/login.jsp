<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로그인 정보</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>로그인 정보</h2>
        <form>
            <div class="mb-3">
                <label for="name" class="form-label">이름</label>
                <input type="text" class="form-control" id="name" value="${name}" readonly />
            </div>
            <div class="mb-3">
                <label for="nickname" class="form-label">별명</label>
                <input type="text" class="form-control" id="nickname" value="${nickname}" readonly />
            </div>
            <div class="mb-3">
                <label for="grade" class="form-label">학년</label>
                <input type="text" class="form-control" id="grade" value="${grade}" readonly />
            </div>
            <div class="mb-3">
                <label for="gender" class="form-label">성별</label>
                <input type="text" class="form-control" id="gender" value="${gender}" readonly />
            </div>
            <div class="mb-3">
                <label for="classType" class="form-label">수강과목</label>
                <input type="text" class="form-control" id="classType" value="<c:forEach var='class' items='${classTypes}'>${class}, </c:forEach>" readonly />
            </div>
        </form>
    </div>
</body>
</html> 