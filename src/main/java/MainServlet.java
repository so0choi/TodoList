
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDao;
import todo.dto.TodoDto;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		TodoDao dao = new TodoDao();
		List<TodoDto> todo = new ArrayList<>();
		List<TodoDto> doing = new ArrayList<>();
		List<TodoDto> done = new ArrayList<>();

		todo = dao.getTodos("TODO");
		doing = dao.getTodos("DOING");
		done = dao.getTodos("DONE");

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
		request.setAttribute("todo", todo);
		request.setAttribute("doing", doing);
		request.setAttribute("done", done);

		requestDispatcher.forward(request, response);
	}

}
