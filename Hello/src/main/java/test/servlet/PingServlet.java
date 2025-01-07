package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ping") // ping 요청이 왔을 때 이 클래스로 생성된 객체로 응답을 할지 정해야 한다.
public class PingServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트에게 문자열을 출력할 수 있는 객체를 얻어낸다.
		PrintWriter pw = response.getWriter();
		// 이 객체를 이용해서 출력하는 문자열은 요청을 한 클라이언트에게 출력된다.
		pw.println("pong!");
		pw.close();
	}
}
