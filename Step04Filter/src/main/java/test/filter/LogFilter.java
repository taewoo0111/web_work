package test.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

/*
 * -필터 만드는 방법-
 * 
 * 1. Filter interface 구현하기
 * 2. 추상 메소드 오버라이드
 * 3. @WebFilter 어노테이션 이용해서 요청 맵핑하기
 */
@WebFilter("/*") // "/*" 이 프로젝트에 오는 모든 요청에 대해서 필터가 동작 되게 한다.
public class LogFilter	implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("Log 필터 수행됨!");
		
		HttpServletRequest request = (HttpServletRequest)req;
		String uri = request.getRequestURI();
		System.out.println("요청 uri:" + uri);
		
		String clientIp = request.getRemoteAddr();
		System.out.println("client ip:" + clientIp);
		
		System.out.println("시간: " + LocalDateTime.now());
		
		// 요청의 흐름 계속 이어가기
		chain.doFilter(req, resp);
	}
	
}
