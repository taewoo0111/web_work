package com.example.spring15.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.spring15.dto.CommentDto;
import com.example.spring15.dto.CommentListRequest;
import com.example.spring15.dto.PostDto;
import com.example.spring15.dto.PostListDto;
import com.example.spring15.repository.CommentDao;
import com.example.spring15.repository.PostDao;

@Service
public class PostServiceImpl implements PostService{

	final int PAGE_ROW_COUNT = 5;
	final int PAGE_DISPLAY_COUNT = 5;
	
	@Autowired private PostDao postDao;
	@Autowired private CommentDao commentDao;
	
	/*
	 * pageNum 과 검색 조건, 키워드가 담겨 있을 수 있는 PostDto 를 전달하면
	 * 해당 글 목록을 리턴하는 메소드 
	 */
	@Override
	public PostListDto getPosts(int pageNum, PostDto search) {
		//보여줄 페이지의 시작 ROWNUM 1,4,7,10,...의 글이 첫번째로 온다.
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM 3,6,9,12,...의 글이 마지막에 온다.
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		//하단 시작 페이지 번호 
		//해당 페이지(pageNum)가 몇 번째 페이지 그룹에 속해 있는지를 구하고, 그 그룹의 첫 번째 페이지 번호를 계산하는 공식
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		//현재 페이지 그룹에서 마지막 페이지 번호(endPageNum)를 구하는 공식
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		
		//전체 글의 갯수
		int totalRow=postDao.getCount(search);
		
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		// 1페이지에 1개씩 보여주면 endpagenum 과 totalpagecount 가 같을 수는 있다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}	
		search.setStartRowNum(startRowNum);
		search.setEndRowNum(endRowNum);
		
		List<PostDto> list = postDao.getList(search);
		
		String findQuery = "";
		if(search.getKeyword() != null) {
			findQuery = "&keyword=" + search.getKeyword() + "&condition=" + search.getCondition();
		}
		
		PostListDto dto = PostListDto.builder()
				.list(list).startPageNum(startPageNum).endPageNum(endPageNum).totalPageCount(totalPageCount)
				.pageNum(pageNum).totalRow(totalRow).findQuery(findQuery).condition(search.getCondition())
				.keyword(search.getKeyword()).build();
		
		return dto;
	}

	@Override
	public long createPost(PostDto dto) {
		String writer = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(writer);
		long num = postDao.getSequence();
		dto.setNum(num);
		postDao.insert(dto);
		return num;
	}

	@Override
	public PostDto getByNum(long num) {
		return postDao.getData(num);
	}

	@Override
	public PostDto getDetail(PostDto dto) {
		return postDao.getDetail(dto);
	}

	@Override
	public void updatePost(PostDto dto) {
		postDao.update(dto);
	}

	@Override
	public void deletePost(long num) {
		// 글의 작성자와 로그인된 userName 과 다르면 Exception 을 발생시키고 ExceptionController 에서 처리하게 한다.
//		String writer = postDao.getData(num).getWriter();
//		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//		if(!writer.equals(userName)) {
//			throw new DeniedException("게시글 작성자가 아닙니다!");
//		}
		
		postDao.deleteReaded(num);
		postDao.delete(num);
	}

	@Override
	public void manageViewCount(long num, String sessionId) {
		boolean isReaded = postDao.isReaded(num, sessionId);
		if(!isReaded) {
			postDao.addViewCount(num);
			postDao.insertReaded(num, sessionId);
		}
	}

	@Override
	public void createComment(CommentDto dto) {
		String writer = SecurityContextHolder.getContext().getAuthentication().getName();
		long num = commentDao.getSequence();
		long parentNum = dto.getParentNum();
		if(parentNum == 0) {
			parentNum = num;
		}
		dto.setNum(num);
		dto.setWriter(writer);
		dto.setParentNum(parentNum);
		commentDao.insert(dto);
	}

	@Override
	public void updateComment(CommentDto dto) {
		commentDao.update(dto);
	}

	@Override
	public void deleteComment(long num) {
		commentDao.delete(num);
	}

	@Override
	public Map<String, Object> getComments(CommentListRequest clr) {
		CommentDto dto = new CommentDto();
		dto.setPostNum(clr.getPostNum());
		
		/*
			[ 댓글 페이징 처리에 관련된 로직 ]
		*/
		//한 페이지에 댓글을 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT = 10;
		int pageNum = clr.getPageNum();
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum = pageNum * PAGE_ROW_COUNT;
		//계산된 값을 dto 에 담는다
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		// 전체 댓글의 개수
		int totalRow = commentDao.getCount(clr.getPostNum());
		// 전체 페이지의 개수
		int totalPageCount = (int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		
		List<CommentDto> list = commentDao.getList(dto);
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("totalPageCount", totalPageCount);
		
		return map;
	}
}
