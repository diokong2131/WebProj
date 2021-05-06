package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement psmt;
	
	//resource 해제
	
	public void close() {
		
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			if(psmt!=null)
				try {
					psmt.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
	}
	
	

	public Employee insertEmpBySeq(Employee emp) {
		conn = DBCon.getConnect();

		Employee empl = new Employee();

		String sql1 = "select employees_seq.nextval from dual";
		String sql2 = "insert into emp_temp(employee_id, last_name, first_name, salary, email, hire_date, job_id)"
				+ "values(?, ?, ?, ?, ?, ?, ?)";
		try {
			int empId = 0;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			if (rs.next()) {
				empId = rs.getInt(1);

			}

			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, empId);
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getFirstName());
			psmt.setInt(4, emp.getSalary());
			psmt.setString(5, emp.getEmail());
			psmt.setString(6, emp.getHireDate());
			psmt.setString(7, emp.getJobId());
			

			int up = psmt.executeUpdate();
			System.out.println(up + "건 입력됨.");

			empl.setEmployeeId(empId);
			
			empl.setLastName(emp.getLastName());
			empl.setFirstName(emp.getFirstName());
			empl.setSalary(emp.getSalary());
			empl.setEmail(emp.getEmail());
			empl.setHireDate(emp.getHireDate());
			empl.setJobId(emp.getJobId());
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return empl;
	}

	public void insertEmp(Employee emp) {
		String sql = "insert into emp_temp(employee_id, last_name, first_name, salary, email, hire_date, job_id) + values ((select max(employee_id)+1 from emp_temp), ?, ?, ?, ?, ?, ?)";
		conn = DBCon.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getLastName());
			psmt.setString(2, emp.getFirstName());
			psmt.setInt(3, emp.getSalary());
			psmt.setString(4, emp.getEmail());
			psmt.setString(5, emp.getHireDate());
			psmt.setString(6, emp.getJobId());

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
		String sql = "select * from emp_temp where department_id = " + dept + " order by employee_id";
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
				emp.setSalary(rs.getInt("salary"));
				emp.setEmail(rs.getString("email"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				
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
				emp.setSalary(rs.getInt("salary"));
				emp.setEmail(rs.getString("email"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				
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




	//empl_demp
public List<Employee> getEmployeeList() {
	// 사원 정보를 가지고 오는 처리.
	String sql = "select * from empl_demo order by employee_id";
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
			emp.setSalary(rs.getInt("salary"));
			emp.setEmail(rs.getString("email"));
			emp.setHireDate(rs.getString("hire_date"));
			emp.setJobId(rs.getString("job_id"));
			emp.setPhoneNumber(rs.getString("phone_number"));
			
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
 public Map<String, Integer> getEmployeebyDept() {
	 //부서명, 사원수
	 Map<String, Integer> map = new HashMap<>();
	
	 
		String sql = "select d.department_name, count(1)\r\n"//
				+ "from empl_demo e, departments d\r\n"//
				+ "where e.department_id = d.department_id\r\n"//
				+ "group by d.department_name";
		conn = DBCon.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			if(psmt!=null)
				try {
					psmt.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		}
		return map;
 }
 
 	//스케줄 정보 가지고 오는 메소드 
 
 	public List<ScheduleVO> getScheduleList(){
 		conn = DBCon.getConnect();
 		String sql = "select * from schedule";
 		List<ScheduleVO> list = new ArrayList<>(); 		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ScheduleVO vo = new ScheduleVO();
				vo.setTitle(rs.getString("title"));
				vo.setStartDay(rs.getString("start_Day"));
				vo.setEndDay(rs.getString("end_Day"));
				
				list.add(vo);
				
						
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			if(psmt!=null)
				try {
					psmt.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		}
 		return list;
 	}
 	
 	// 한 건 입력.
 	public void insertSchedule(ScheduleVO vo) {
 		String sql = "insert into schedule(title, start_day, end_day) values(?, ?, ?)";
 		conn = DBCon.getConnect();

 		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getStartDay());
			psmt.setString(3, vo.getEndDay());
			

			int up = psmt.executeUpdate();
			System.out.println(up + "건 입력됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
 	}
 	// 삭제
 	public void deleteSchedule(ScheduleVO vo) {
 		String sql = "delete from schedule where title=?";
 		conn = DBCon.getConnect();

 		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			
			int up = psmt.executeUpdate();
			System.out.println(up + "건 삭제됨.");
 	}catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close();
	}

}
}