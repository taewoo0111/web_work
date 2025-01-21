package test.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fortune")
public class FortuneServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fortune = "동쪽으로 가면 귀인을 만나요!";
		request.setAttribute("fortuneToday", fortune);
		
		RequestDispatcher rd = request.getRequestDispatcher("/test/fortune.jsp");
		rd.forward(request, response);
	}
}
