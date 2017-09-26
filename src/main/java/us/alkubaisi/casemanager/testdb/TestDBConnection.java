package us.alkubaisi.casemanager.testdb;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDBConnection {
	
	public static void main(String[] args){
		String url = "jdbc:mysql://localhost:3306/case-manager?useSSL=false";
		String username = "casetrackingdbadmin";
		String password = "casetrackingdbadmin";
		
		try{
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Successful!!!");
		}catch(Exception e){
			e.printStackTrace();
		}	
	}	
}
