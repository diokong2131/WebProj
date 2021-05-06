package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/empList.do")
public class EmpServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		String dept = req.getParameter("dept");

		EmpDAO dao = new EmpDAO();

		List<Employee> list = null;

		if (dept == null) {
			list = dao.getempList();
		} else {
			list = dao.getEmpByDept(dept);
		}

		String jsonData = "[";
		// [{"empId":"?", "fname":"?" ,"lname":"?"} ... ]
		int cnt = 0;
		for (Employee emp : list) {
			jsonData += ("{\"empId\":\"" + emp.getEmployeeId()//
					+ "\",\"lname\":\"" + emp.getLastName() //
					+ "\",\"fname\":\"" + emp.getFirstName() //
					+ "\",\"salary\":\"" + emp.getSalary() //
					+ "\",\"email\":\"" + emp.getEmail() //
					+ "\",\"hdate\":\"" + emp.getHireDate() //
					+ "\",\"jobId\":\"" + emp.getJobId() //
					+ "\"}");
			if (++cnt == list.size()) {
				continue;
			}
			jsonData += ",";
		}
		jsonData += "]";

		out.println(jsonData);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	      super.doPost(req, resp);
		String lastName = req.getParameter("last_name");
		String firstName = req.getParameter("first_name");
		int salary = Integer.parseInt(req.getParameter("salary"));
		String email = req.getParameter("email");
		String hireDate = req.getParameter("hire_date");
		String jobId = req.getParameter("job_id");
		


		Employee emp = new Employee();
		emp.setLastName(lastName);
		emp.setFirstName(firstName);
		emp.setSalary(salary);
		emp.setEmail(email);
		emp.setHireDate(hireDate);
		emp.setJobId(jobId);
		

		EmpDAO dao = new EmpDAO();
		Employee empl = dao.insertEmpBySeq(emp);
		//{"eid":"?","fName":"?".......}
		PrintWriter out = resp.getWriter();
		out.print("{\"employee_id\":\""+empl.getEmployeeId()+"\","//
	            +"\"last_name\":\""+empl.getLastName()+"\","//
	            +"\"first_name\":\""+empl.getFirstName()+"\","//
	            +"\"salary\":\""+empl.getSalary()+"\","//
			    +"\"email\":\""+empl.getEmail()+"\","//
	            +"\"hire_date\":\""+empl.getHireDate()+"\","//
	            +"\"job_id\":\""+empl.getJobId()+"\""//
	            +"}");

		
	}
}
