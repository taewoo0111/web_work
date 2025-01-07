package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.dto.MemberDto;

@WebServlet("/member")
public class MemberServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberDto dto = new MemberDto(1, "김태우", "수내동");
		
		// 응답 인코딩
		resp.setCharacterEncoding("utf-8");
		// 응답 컨텐트 설정
		resp.setContentType("text/html; charset = utf-8");

		PrintWriter pw = resp.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset = 'utf-8'>");
		pw.println("<title></title>");
		pw.println("</head>");
		pw.println("<body>");
			pw.println("<p> 번호: <strong>" + dto.getNum() + "</strong></p>");
			pw.println("<p> 이름: <strong>" + dto.getName() + "</strong></p>");
			pw.println("<p> 주소: <strong>" + dto.getAddr() + "</strong></p>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
