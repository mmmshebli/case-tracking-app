package us.alkubaisi.casemanager.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public TestDbServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String jdbcUrl = "jdbc:mysql://localhost:3306/case-manager?useSSL=false";
		String user = "casetrackingdbadmin";
		String password = "casetrackingdbadmin";
		String driver = "com.mysql.jdbc.Driver";
		
		try{
			PrintWriter out = response.getWriter();
			out.println("Connecting to the Data Base " + jdbcUrl);
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
			out.println("Connection Successfull!!!!");
			connection.close();
			
		}catch(Exception e){
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
