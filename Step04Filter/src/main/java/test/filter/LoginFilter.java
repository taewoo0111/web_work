package test.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({"/test/protected/*"})
public class LoginFilter implements Filter{
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		if(id == null) {
			String cpath = request.getContextPath();
			HttpServletResponse response = (HttpServletResponse)resp;
			response.sendRedirect(cpath + "/user/loginform.jsp");
		}else {
			chain.doFilter(req, resp);
		}
	}

}
