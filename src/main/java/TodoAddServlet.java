
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDao;
import todo.dto.TodoDto;

@WebServlet("/addTodo")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TodoDao dao = new TodoDao();
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String sequence = request.getParameter("sequence");
		int seq = Integer.parseInt(sequence);
		TodoDto dto = new TodoDto(title, name, seq);
		try {
			dao.addTodo(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("main");
	}

}
