<%@page import="test.dto.PostDto"%>
<%@page import="test.dao.PostDao"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	PostDto dto = new PostDto();
	
	// 한 페이지에 몇 개의 글의 나오게 할지 정함
	final int PAGE_ROW_COUNT = 3 ;
	// 페이징 처리 한 것을 몇 개의 그룹으로 나눌지를 정함
	final int PAGE_DISPLAY_COUNT = 5;
	
	// 첫 페이지 로딩 시 페이지의 번호
	int pageNum = 1;
	
	// 밑에서 페이징 처리된 그룹을 골랐을 때 몇 번째 그룹인지를 알게 하려고 작성
	String strPageNum = request.getParameter("pageNum");
	if(strPageNum != null){
		pageNum = Integer.parseInt(strPageNum);
	}  
	
	// 페이지 첫 글이 test_posts 테이블에서 몇 번째 글인지를 알아내는 변수
	int startRowNum = 1 + (pageNum-1) * PAGE_ROW_COUNT;
	// 페이지 마지막 글이 test_posts 테이블에서 몇 번째 글인지를 알아내는 변수
	int endRowNum = pageNum * PAGE_ROW_COUNT;
	
	// 페이징 그룹을 정하는 변수 -> 1,2,3페이지는 1번 페이징 처리, 4,5,6 페이지는 2번 페이징 처리, ...
	int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
	// 페이징이 끝나는 번호를 알기 위한 변수 1번 페이징 처리를 했으면 5, 2번 페이징 처리를 했으면 10
	int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
	// 결국 위의 2줄은 밑의 페이징 처리 버튼을 만들 때 다음 페이징 그룹으로 넘어가면 그 페이징 그룹에 해당하는 숫자를 알려주기 위한 공식!
	
	// test_posts에 몇 개의 글이 있는지를 반환
	int totalRow = PostDao.getInstance().getCount();
	// 그래서 총 몇 페이지가 나오는지를 계산
	int totalPageCount = (int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
	
	// 마지막 페이징 번호가 존재하는 페이징보다 클 경우 -> 예를 들어 마지막 페이징처리가 10인데 총 페이지가 8이면 8까지만 나와야 함!
	if(endPageNum > totalPageCount){
		endPageNum=totalPageCount; 
	}
	
	// dto에 between startrownum and endrownum 을 위해 넣어줌
	dto.setStartRowNum(startRowNum);
	dto.setEndRowNum(endRowNum);
	
	// 위에서 startrownum과 endrownum을 넣어준 dto로 리스트를 불러옴
	List<PostDto> list = PostDao.getInstance().getList(dto);
	request.setAttribute("list", list);
	request.setAttribute("startPageNum", startPageNum);
	request.setAttribute("endPageNum", endPageNum);
	request.setAttribute("totalPageCount", totalPageCount);
	request.setAttribute("pageNum", pageNum);
	request.setAttribute("totalRow", totalRow);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 페이지</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container mt-5">
		<div class="container mt-5">
			<h1>글 작성하는 버튼</h1>
				<a href="insertForm.jsp" class="btn btn-primary">글 작성하러 가기!</a>
		</div>
		<div class="container mt-5">
			<h1>메인 페이지로 가기</h1>
				<a href="../index.jsp" class="btn btn-secondary">메인 페이지로 가기</a>
		</div>
		<div class="container mt-5">
			<h1>게시글 목록입니다</h1>
			<table class="table table-bordered table-striped">
				<thead class="table-dark">
					<tr>
						<th>글 번호</th>
						<th>제목</th>
						<th>작성자(닉네임)</th>
						<th>조회수</th>
						<th>글 작성일</th>
						<th>글 수정일</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty list}">
							<tr>
								<td colspan="6" class="text-center">게시글이 없어요!</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="dto" items="${list}">
								<tr>
									<td>${dto.num}</td>
									<td>
										<a href="view.jsp?num=${dto.num }">${dto.title}</a>
									</td>
									<td>${dto.writer}</td>
									<td>${dto.viewcount}</td>
									<td>${dto.createdAt}</td>
									<td>${dto.updatedAt}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<nav>
			<ul class="pagination">
				<!-- Prev 버튼 -->
				<c:if test="${startPageNum ne 1}">
					<li class="page-item">
						<a class="page-link" href="list.jsp?pageNum=${startPageNum - 1}">Prev</a>
					</li>
				</c:if>
				<!-- 페이지 번호 -->
				<c:forEach begin="${startPageNum}" end="${endPageNum}" var="i">
					<li class="page-item ${i == pageNum ? 'active' : ''}">
						<a class="page-link" href="list.jsp?pageNum=${i}">${i}</a>
					</li>
				</c:forEach>
				<!-- Next 버튼 -->
				<c:if test="${endPageNum < totalPageCount}">
					<li class="page-item">
						<a class="page-link" href="list.jsp?pageNum=${endPageNum + 1}">Next</a>
					</li>
				</c:if>
			</ul>		
		</nav>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>