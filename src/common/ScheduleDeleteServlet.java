package common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scheduleDeleteServlet")
public class ScheduleDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ScheduleDeleteServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		
		ScheduleVO vo = new ScheduleVO();
		vo.setTitle(title);
		
		
		EmpDAO dao = new EmpDAO();
		dao.deleteSchedule(vo);
	}

}
