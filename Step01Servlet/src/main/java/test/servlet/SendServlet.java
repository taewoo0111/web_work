package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/*
 * 요청 파리미터 추출은 HttpServletRequest 객체를 이요해서 추출하면 된다.
 */
@WebServlet("/send")
public class SendServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException {
		// 응답 인코딩
		reps.setCharacterEncoding("utf-8");
		// 응답 컨텐트 설정
		reps.setContentType("text/html; charset = utf-8");
		String msg = req.getParameter("msg");
		System.out.println("폼 전송된 내용:" +msg);
		
		PrintWriter pw =reps.getWriter();
		pw.println("폼 전송된 내용: " +msg);
		pw.close();
	}
}
