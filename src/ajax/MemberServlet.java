package ajax;

import java.beans.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.ldap.Rdn;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.DBCon;

@WebServlet("/jquery/memberServlet.do")
public class MemberServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MemberServlet() {
      super();

   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      //์กฐํ์์
      Connection conn = DBCon.getConnect();
      PreparedStatement psmt = null;
      Statement stmt;
      ResultSet rs = null;
      
      String sql = "select * from member";
      String json = "[";
      
      try {
         psmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
         rs = psmt.executeQuery();
         
         while(rs.next()) {
            String memId = rs.getString(1);
            String memName = rs.getString(2);
            int memAge = rs.getInt(3);
            
          
            json += "{\"id\":\"" + memId + //
                    "\",\"name\":\"" + memName + //
                    "\",\"age\":" + memAge + "}";
            

            
            
            if(!rs.isLast())
               json += ",";
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         if(rs != null) {
            try {
               rs.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
         if(psmt != null) {
            try {
               psmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
         if(conn != null) {
            try {
               conn.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      }
       json += "]";
      //์กฐํ ๊ฒฐ๊ณผ๋ฅผ json ํ์์ผ๋ก ์์ฑํด๋ณด๊ธฐ
      //[{"id":1, "name":"choi", "age":31},
      // {"id":1, "name":"choi", "age":31},
      // {"id":1, "name":"choi", "age":31}]
      
      //๊ฒฐ๊ณผ๋ฅผ response.getWriter().print();๋ก ์ถ๋?ฅํด๋ณด๊ธฐ
      response.getWriter().print(json);
      
      response.setContentType("text/html;charset=UTF-8"); // ๋ธ๋ผ์ฐ์?๋ก ๋ณด๋ด์ง๋ ๋ฐ์ดํฐ์ ํ๊ธ ์ฒ๋ฆฌ
      // Tip> ๋ง์ฝ ํ๋ผ๋ฏธํฐ๋ก ์?๋ฌ๋๋ ๋ฐ์ดํฐ์ ํ๊ธ ์ฒ๋ฆฌ๊ฐ ํ์ํ? ๋๋
      // request.setCharacterEncoding("utf-8"); ์ ์ฌ์ฉํ๋ค.
      // response.setContentType๋ request.setCharacterEncoding์
      // ๊ฐ์ ๋ฐ์์ค๋ ๋ณ์๋ ํ๋ฉด์ ์ถ๋?ฅํ๋(print ๊ฐ์) ๊ตฌ๋ฌธ ์์ ๋จผ์? ์์นํด์ผ ํ๋ค.
//      response.getWriter().print("<h2>Complete Search.</h2>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      //์๋?ฅ์์
                        //       โ>jq_ajax4.html์ input์ ์๋ name์ ๊ฐ์ด ํ๋ผ๋ฏธํฐ๋ก ์ฐ์.
      String p1 = request.getParameter("m_id");
      String p2 = request.getParameter("m_name");
      String p3 = request.getParameter("m_age");

      Connection conn = DBCon.getConnect();
      PreparedStatement psmt = null;
      String sql = "insert into member values(?,?,?)";
      try {
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, p1);
         psmt.setString(2, p2);
         psmt.setString(3, p3);
         int r = psmt.executeUpdate();

         System.out.println(r + "๊ฑด ์๋?ฅ์๋ฃ.");
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
      String json = "{\"id\":"+p1+"\"name\":"+p2+"\"age\":"+p3+"}";
      
      response.getWriter().print(json);
   }

}