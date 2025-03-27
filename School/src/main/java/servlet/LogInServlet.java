package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/login")
public class LogInServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
        String nickname = req.getParameter("nickname");
        String grade = req.getParameter("grade");
        String gender = req.getParameter("gender");
        String[] classType = req.getParameterValues("class");

        req.setAttribute("name", name);
        req.setAttribute("nickname", nickname);
        req.setAttribute("grade", grade);
        req.setAttribute("gender", gender);
        req.setAttribute("classType", classType);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/step1/login.jsp");
        dispatcher.forward(req, resp);
	}
}
