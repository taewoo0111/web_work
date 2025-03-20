package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/hello")
public class TestServlet2 extends HttpServlet{
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String fortuneToday = "동쪽으로 가면 귀인을 만나요!";

		// 응답 인코딩
		res.setCharacterEncoding("utf-8");
		// 응답 컨텐트 설정
		res.setContentType("text/html; charset = utf-8");

		PrintWriter pw = res.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset = 'utf-8'>");
		pw.println("<title>오늘의 운세 페이지</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<p> 오늘의 운세: <strong>Hello</strong></p>");
		pw.println("<p> 오늘의 운세: <strong>" + fortuneToday + "</strong></p>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
