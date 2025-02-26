<%@page import="test.user.dto.SessionDto"%>
<%@page import="test.post.dao.PostDao"%>
<%@page import="test.post.dto.PostDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	//검색조건이 있는지 읽어와 본다.
	String condition=request.getParameter("condition");
	String keyword=request.getParameter("keyword");
	String findQuery=null;
	//있다면 dto 에 해당 정보를 담는다.
	PostDto findDto=new PostDto();
	if(condition != null){
		//findDto=new PostDto();
		findDto.setCondition(condition);
		findDto.setKeyword(keyword);
		findQuery="&condition="+condition+"&keyword="+keyword;
	}
	//자세히 보여줄 글의 번호를 읽어온다. 
	int num=Integer.parseInt(request.getParameter("num"));
	findDto.setNum(num);
	
	//DB 에서 해당 글의 정보를 얻어와서 
	PostDto dto=PostDao.getInstance().getData(findDto);
	
	//세션 아이디를 읽어와서 
	String sessionId=session.getId();
	//이미 읽었는지 여부를 얻어낸다 
	boolean isReaded=PostDao.getInstance().isReaded(num, sessionId);
	if(!isReaded){
		//글 조회수도 1 증가 시킨다
		PostDao.getInstance().addViewCount(num);
		//이미 읽었다고 표시한다. 
		PostDao.getInstance().insertReaded(num, sessionId);
	}
	
	request.setAttribute("dto", dto);
	request.setAttribute("findDto", findDto);
	request.setAttribute("findQuery", findQuery);
	//응답한다 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/post/view.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<style>
    #contents {
        margin-top: 20px;
        padding: 20px;
        background-color: #fefefe;
        border-radius: 10px;
        box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
        border: 1px solid #ddd;
        transition: transform 0.3s ease, box-shadow 0.3s ease;
    }
    #contents:hover {
        transform: translateY(-5px);
        box-shadow: 0 15px 30px rgba(0, 0, 0, 0.3);
    }
    
    #content{
    	width: 100%;
    	height: 300px;
    }
	/* 댓글 프로필 이미지를 작은 원형으로 만든다. */
	.profile-image{
		width: 50px;
		height: 50px;
		border: 1px solid #cecece;
		border-radius: 50%;
	}
	/* ul 요소의 기본 스타일 제거 */
	.comments ul{
		padding: 0;
		margin: 0;
		list-style-type: none;
	}
	
	/* .reply_icon 을 li 요소를 기준으로 배치 하기 */
	.comments li{
		position: relative;
	}
	.comments .reply-icon{
		position: absolute;
		top: 1rem;
		left: 1rem;
		color: red;
	}
	
	/* 대댓글을 들여 쓰기 위한 클래스 */
	.indent{
		padding-left: 50px;
	}
	
	/* 답글 아이콘은 일단 보이지 않게  */
	.reply-icon{
		display: none;
	}
	
	.comment-form, .re-insert-form, .update-form{
		display: flex;
	}
	
	.comment-form textarea, .re-insert-form textarea, .update-form textarea{
		height: 100px;
		flex-grow: 1;
	}
	
	.comment-form button, .re-insert-form button, .update-form button{
		flex-basis: 100px;
	}
	/* 대댓글폼은 일단 숨겨 놓는다 */
	.re-insert-form, .update-form{
		display: none;
	}
	
	/* 댓글 출력 디자인 */
	.comments pre {
	  display: block;
	  padding: 9.5px;
	  margin: 5px 0;
	  font-size: 13px;
	  line-height: 1.42857143;
	  color: #333333;
	  word-break: break-all;
	  word-wrap: break-word;
	  background-color: #f5f5f5;
	  border: 1px solid #ccc;
	  border-radius: 4px;
	}
	/* 댓글 컨테이너 */
	.comment-header {
	    display: flex;
	    align-items: center;
	    justify-content: space-between; /* 좌측: 프로필+작성자 | 우측: 버튼 */
	    gap: 10px;
	    padding: 10px 0;
	    border-bottom: 1px solid #ddd;
	}
	
	/* 프로필 이미지와 작성자 정보 */
	.comment-profile {
	    display: flex;
	    align-items: center;
	    gap: 10px;
	}
	
	/* 프로필 이미지 */
	.comment-profile .profile-image {
	    width: 40px;
	    height: 40px;
	    border-radius: 50%;
	    object-fit: cover;
	    border: 1px solid #cecece;
	}
	
	
	
	/* 작성자와 날짜 정보 */
	.comment-meta {
	    display: flex;
	    flex-direction: column;
	    gap: 3px;
	}
	
	.comment-writer {
	    font-weight: bold;
	    color: #333;
	}
	
	.comment-date {
	    font-size: 0.85em;
	    color: #777;
	}
	
	/* 답글, 수정, 삭제 버튼 */
	.comment-actions {
	    display: flex;
	    gap: 10px; /* 버튼 간 간격 */
	    font-size: 0.9em;
	}
	
	.comment-actions a {
	    text-decoration: none;
	    color: #007bff;
	}
	
	.comment-actions a:hover {
	    text-decoration: underline;
	}

</style>
</head>
<body>
	<div class="container">
		<c:if test="${dto.prevNum ne 0}">
			<a href="view.jsp?num=${dto.prevNum}${findQuery}">이전글</a>
		</c:if>
		<c:if test="${dto.nextNum ne 0}">
			<a href="view.jsp?num=${dto.nextNum}${findQuery}">다음글</a>
		</c:if>
		<c:if test="${not empty findDto.condition}">
			<p>
				<strong>${findDto.condition }</strong> 조건
				<strong>${findDto.keyword }</strong>검색어로 검색된 내용 자세히보기
			</p>
		</c:if>
		<h3>글 상세 보기</h3>
		<table class="table table-bordered">
			<tr>
				<th>글번호</th>
				<td>${dto.num }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dto.writer }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.title }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${dto.viewCount }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${dto.createdAt }</td>
			</tr>
			<tr>
				<th>수정일</th>
				<td>${dto.updatedAt }</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="contents">${dto.content }</div>
				</td>
			</tr>
		</table>
		
		<%-- 만일 글 작성자가 로그인된 아이디와 같다면 수정, 삭제 링크를 제공한다. --%>
		<c:if test="${dto.writer eq sessionDto.userName }">
			<a class="btn btn-outline-success btn-sm" href="${pageContext.request.contextPath }/post/protected/edit.jsp?num=${dto.num }">수정</a>
			<a class="btn btn-outline-danger btn-sm" href="javascript:" onclick="deleteConfirm()">삭제</a>
			<script>
				function deleteConfirm(){
					const isDelete=confirm("이 글을 삭제 하겠습니까?");
					if(isDelete){
						//javascript 를 이용해서 페이지 이동 시키기
						location.href="${pageContext.request.contextPath }/post/protected/delete.jsp?num=${dto.num}";
					}
				}
			</script>
		</c:if>
		<h4>댓글을 입력해 주세요</h4>
		<!-- 원글에 댓글을 작성할 폼 -->	
		<form class="comment-form" action="protected/comment-insert.jsp" method="post">
			<!-- 원글의 글번호가 댓글의 postNum 이 된다. -->
			<input type="hidden" name="postNum" value="${dto.num}"/>
			<!-- 원글의 작성자가 댓글의 대상자가 된다. -->
			<input type="hidden" name="targetWriter" value="${dto.writer}"/>
		 	<textarea name="content">${empty sessionDto ? '댓글 작성을 위해 로그인이 필요합니다' : ''}</textarea>
			<button type="submit">등록</button>
		</form>
		<!-- 댓글 목록 -->
		<div class="comments">
			<ul>
			
			</ul>
			<div class="d-grid col-sm-6 mx-auto mb-5">
				<button class="btn btn-success" id="moreBtn">
					<span id="moreText">댓글 더보기</span>
					<div id="spinner" class="spinner-border" role="status">
  						<span class="visually-hidden">Loading...</span>
					</div>
				</button>
			</div>
		</div>
	</div>
	<script>
		let totalPageCount=0;
		let currentPage=1;
		setLoading(false);
	
		function setLoading(loading){
			if(loading){
				document.querySelector("#moreText").style.display = "none";
				document.querySelector("#spinner").style.display = "inline-block";
			}else{
				document.querySelector("#moreText").style.display = "inline";
				document.querySelector("#spinner").style.display = "none";
			}
		}
		
		document.querySelector("#moreBtn").addEventListener("click", ()=>{
			if(currentPage >= totalPageCount){
				alert("댓글의 마지막 페이지 입니다.");
				return;
			}
			setLoading(true);
			//댓글 페이지 번호를 1 증가 시키고
			currentPage++;
			//해당페이지의 정보를 요청해서 받아온다. 
			fetch(`comment-list.jsp?pageNum=\${currentPage}&postNum=${dto.num}`)
			.then(res=>res.json())
			.then(commentData=>{
				setLoading(false);
				//전체 페이지의 갯수
				totalPageCount=commentData.totalPageCount;
				//댓글 목록에 있는 댓글 정보 하나 하나를 참조하면서 
				commentData.list.forEach(item=>{
					//댓글 하나의 정보를 makeList 함수에 전달해서 댓글 정보가 출력된 li 를 얻어낸다.
					const li = makeList(item);
					//얻어낸 li 요소를 ul 에 추가한다.
					document.querySelector(".comments ul").append(li);
				});
			});
		});
	
		function refreshComments(){
			//출력된 내용을 모두 지우고 
			document.querySelector(".comments ul").innerHTML="";
			
			//댓글 1page 내용을 fetch() 를 이용해서 받아온다.
			fetch("comment-list.jsp?pageNum=1&postNum=${dto.num}")
			.then(res=>res.json())
			.then(commentData=>{
				//전체 페이지의 갯수
				totalPageCount=commentData.totalPageCount;
				
				//댓글 목록에 있는 댓글 정보 하나 하나를 참조하면서 
				commentData.list.forEach(item=>{
					//댓글 하나의 정보를 makeList 함수에 전달해서 댓글 정보가 출력된 li 를 얻어낸다.
					const li = makeList(item);
					//얻어낸 li 요소를 ul 에 추가한다.
					document.querySelector(".comments ul").append(li);
				});
				
				
			});
		}
		
		refreshComments();

	
		//로그인된 사용자의 이름
		const userName="${sessionDto.userName}";
		//로그인 여부
		const isLogin=${not empty sessionDto};
	
		document.querySelector(".comment-form").addEventListener("submit", (e)=>{
			e.preventDefault();
			if(!isLogin){
				alert("로그인 페이지로 이동합니다");
				location.href="${pageContext.request.contextPath }/user/login-form.jsp?url=${pageContext.request.contextPath }/post/view.jsp?num=${dto.num}";
				return;
			}
			//폼 제출 막기 
			//e.preventDefault();
			//폼에 작성된 내용을 이용해서 query 문자열을 얻어낸다. 
			const formData=new FormData(e.target);
			const queryString = new URLSearchParams(formData).toString();
			// fetch() 함수를 이용해서 댓글 정보를 페이지 전환 없이 서버에 전송한다.
			fetch("protected/comment-insert.jsp",{
				method:"POST",
				headers: { "Content-Type": "application/x-www-form-urlencoded" },
				body:queryString
			})
			.then(res=>res.json())
			.then(comment=>{	
				//댓글 1page 내용을 다시 출력해준다.
				refreshComments();
			});
		});
		
		//함수 호출하면서 댓글 하나의 정보를 담고 있는 object 를 전달하면 li 요소가 리턴된다.
		function makeList(comment){
			// li 요소를 만들어서 
			const li = document.createElement("li");
			li.classList.add(comment.num !== comment.parentNum ? "indent" : "not");
			
			//만일 삭제된 댓글 이라면 
			if(comment.deleted == "yes"){
				li.innerHTML=`
					<svg style="\${comment.num != comment.parentNum ? 'display:inline' : ''}"  class="reply-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
		  				<path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
					</svg>
					<pre>삭제된 댓글입니다</pre>
				`;
				//삭제된 댓글입니다가 출력된 li 를 바로 리턴해 준다.
				return li;
			}
			
			// 프로필 이미지 처리
            const profileImage = comment.profileImage 
                ? `<img class="profile-image" src="${pageContext.request.contextPath }/upload/\${comment.profileImage}" alt="Profile Image">`
                : `<svg class="profile-image default-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                    <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                    <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                </svg>`; 
                
            //수정 삭제 링크 처리
            const link = userName == comment.writer
            	? `
            		<a class="update-link" href="javascript:">수정</a>
					<a class="delete-link" href="javascript:">삭제</a>
            	` 
            	: "";
            
            //li 요소안에 dl 을 출력한다. 
			li.innerHTML=`
				<svg style="\${comment.num != comment.parentNum ? 'display:inline' : ''}"  class="reply-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
	  				<path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
				</svg>
				<dl>
					<dt class="comment-header">
					    <!-- 프로필 이미지 -->
					    <div class="comment-profile">
					        \${profileImage}
					        <div class="comment-meta">
					            <span class="comment-writer">
					            	\${comment.writer}
					            	\${comment.num != comment.parentNum ? '@' + comment.targetWriter : ''}
					            </span>
					            <small class="comment-date">\${comment.createdAt}</small>
				        	</div>
					    </div>
					
					    <!-- 답글, 수정, 삭제 버튼 -->
					    <div class="comment-actions">
					        <a class="reply-link" href="javascript:">답글</a>
					       \${link}
					    </div>
					</dt>

					<dd>
						<pre>\${comment.content}</pre>
					</dd>
				</dl>
				<!-- 댓글의 댓글 작성할 폼 미리 출력하기 -->
				<form class="re-insert-form"  method="post">
					<input type="hidden" name="postNum" th:value="${postDto.num }"/>
					<input type="hidden" name="targetWriter" value="\${comment.writer }"/>
					<input type="hidden" name="parentNum" value="\${comment.parentNum }"/>
					<textarea name="content"></textarea>
					<button type="submit">등록</button>
				</form>
				<!-- 댓글 수정폼 -->
				<form  class="update-form"  method="post">
					<input type="hidden" name="num" value="\${comment.num}"/>
					<textarea name="content">\${comment.content}</textarea>
					<button type="submit">수정확인</button>
				</form>		
			`;
			
			li.querySelector(".delete-link") && li.querySelector(".delete-link").addEventListener("click", (e)=>{
				const isDelete=confirm("댓글을 삭제 하시겠습니까?");
				if(isDelete){
					fetch("protected/comment-delete.jsp?num="+comment.num)
					.then(res=>res.json())
					.then(data=>{
						if(data.isSuccess){
							//댓글이 있었던 자리에 "삭제된 댓글입니다" 를 출력해준다.
							li.innerHTML=`
								<svg style="\${comment.num != comment.parentNum ? 'display:inline' : ''}"  class="reply-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
					  				<path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
								</svg>
								<pre>삭제된 댓글입니다</pre>
							`;
						}
					});
				}
			});
			
			li.querySelector(".update-form").addEventListener("submit", (e)=>{
				//폼 제출을 막은 다음 
				e.preventDefault();
				//submit 이벤트가 일어난 form 의 참조값을 form 이라는 변수에 담기 
				const form=e.target;
				//폼에 입력된 전송할 내용을 query 문자열 형식으로 얻어내기
				const queryString=new URLSearchParams(new FormData(form)).toString();
				fetch("protected/comment-update.jsp", {
					method:"POST",
					headers:{"Content-Type":"application/x-www-form-urlencoded; charset=utf-8"},
					body:queryString
				})
				.then(res=>res.json())
				.then(data=>{
					if(data.isSuccess){
						//수정된 댓글 내용을 pre 요소에 출력
						//수정폼에서 name="content" 인 요소의 value 값을 pre 에 출력
						li.querySelector("pre").innerText=form.content.value;
						//form 숨기기
						form.style.display="none";
						//링크 내용 수정
						li.querySelector(".update-link").innerText="수정";
					}
					
				})
			});
			
			
			// li.querySelector(".update-link") 가 null 이 아니라면
			li.querySelector(".update-link") && li.querySelector(".update-link").addEventListener("click", (e)=>{
				//수정폼의 참조값
				const form=li.querySelector(".update-form");
				//눌러진 링크의 innerText 읽어오기
				const currentText=e.target.innerText;
				if(currentText === "수정"){
					//보이게 하기 
					form.style.display="flex";
					e.target.innerText="수정취소";
				}else if(currentText === "수정취소"){
					form.style.display="none";
					e.target.innerText="수정";
				}	
			})
			
			li.querySelector(".reply-link").addEventListener("click", (e)=>{
				if(!isLogin){
					alert("로그인 페이지로 이동합니다");
					location.href="${pageContext.request.contextPath }/user/login-form.jsp?url=${pageContext.request.contextPath }/post/view.jsp?num=${dto.num}";
					return;
				}
				
				//보여주거나 숨길 form 의 참조값 얻어내기 
				const form=li.querySelector(".re-insert-form");
				//눌러진 링크의 innerText 읽어오기
				const currentText=e.target.innerText;
				if(currentText === "답글"){
					//보이게 하기 
					form.style.display="flex";
					e.target.innerText="취소";
				}else if(currentText === "취소"){
					form.style.display="none";
					e.target.innerText="답글";
				}	
			});
			li.querySelector(".re-insert-form").addEventListener("submit", (e)=>{
				e.preventDefault();
				//폼에 작성된 내용을 이용해서 query 문자열을 얻어낸다. 
				const formData=new FormData(e.target);
				const queryString = new URLSearchParams(formData).toString();
				// fetch() 함수를 이용해서 댓글 정보를 페이지 전환 없이 서버에 전송한다.
				fetch("protected/comment-insert.jsp",{
					method:"POST",
					headers: { "Content-Type": "application/x-www-form-urlencoded" },
					body:queryString
				})
				.then(res=>res.json())
				.then(comment=>{
					//댓글 1page 내용을 다시 출력해준다.
					refreshComments();
				});
			});
			
			return li;
		}
		
	</script>
</body>
</html>