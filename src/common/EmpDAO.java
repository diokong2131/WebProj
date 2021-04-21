package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement psmt;

	public Employee insertEmpBySeq(Employee emp) {
		conn = DBCon.getConnect();
		
		Employee empl = new Employee();
		
		String sql1 = "select employees_seq.nextval from dual";
		String sql2 = "insert into emp_temp(employee_id, last_name, email, hire_date, job_id)"
				+ "values ((select max(employee_id)+1 from emp_temp), ?, ?, ?, ?)";
		try {
			int empId = 0;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			if (rs.next()) {
				empId = rs.getInt(1);

			}

			conn.prepareStatement(sql2);
			psmt.setInt(1, empId);
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getHireDate());
			psmt.setString(5, emp.getJobId());
			

			int up = psmt.executeUpdate();
			System.out.println(up + "건 입력됨.");
			
			empl.setEmployeeId(empId);
			empl.setEmail(emp.getEmail());
			empl.setLastName(emp.getLastName());
			empl.setJobId(emp.getJobId());
			empl.setHireDate(emp.getHireDate());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void insertEmp(Employee emp) {
		String sql = "insert into emp_temp(employee_id, last_name, email, hire_date, job_id)"
				+ "values ((select max(employee_id)+1 from emp_temp), ?, ?, ?, ?)";
		conn = DBCon.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getLastName());
			psmt.setString(2, emp.getEmail());
			psmt.setString(3, emp.getHireDate());
			psmt.setString(4, emp.getJobId());

			int up = psmt.executeUpdate();
			System.out.println(up + "건 입력됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<Employee> getEmpByDept(String dept) {
		String sql = "select * from emp_temp where department_id = " + dept //
				+ " order by employee_id";
		conn = DBCon.getConnect();

		List<Employee> employees = new ArrayList<Employee>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));

				employees.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return employees;
	}

	public List<Employee> getempList() {
		// 사원 정보를 가지고 오는 처리.
		String sql = "select * from emp_temp order by employee_id";
		DBCon.getConnect();
		conn = DBCon.getConnect();
		List<Employee> employees = new ArrayList<Employee>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));

				employees.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return employees;
	}
}
