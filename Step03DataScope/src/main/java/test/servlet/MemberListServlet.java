package test.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.member.dto.MemberDto;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<MemberDto> list = new ArrayList<>();
		MemberDto dto1 = new MemberDto(1, "김구라", "노량진");
		MemberDto dto2 = new MemberDto(2, "김태우", "수내동");
		MemberDto dto3 = new MemberDto(3, "원숭이", "동물원");
		list.add(dto1);
		list.add(dto2);
		list.add(dto3);
		
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/test/memberlist.jsp");
		rd.forward(request, response);
	}
}
