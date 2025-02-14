package test.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.dao.UserDao;
import test.dto.UserDto;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		UserDto dto = UserDao.getInstance().getData(id);
		if(!id.equals(dto.getId())) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");
			dispatcher.forward(req, res);
		}else {
			res.sendRedirect("signup-form.jsp");
		}
	}
}
