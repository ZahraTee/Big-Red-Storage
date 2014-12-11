package com.bigred.objects;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Account 
{
	private String username;
	private String password;
	
	public static boolean isValidUserName(String username)
	{
		DataSource dataSource=null;
		 Connection connection=null;
		 Statement statement=null;
			boolean result = true;
			
			ResultSet resultSet = null;
	        try {
	        	Context initContext  = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
	            // Get Connection and Statement
	            connection = dataSource.getConnection();
	            statement = connection.createStatement();
	            String query = "SELECT  * FROM Accounts WHERE username = '"+username+"'";
	            resultSet = statement.executeQuery(query);
	            
	            if (resultSet.next()) {
	            	result=false;
	            }
	            
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            try { if(null!=resultSet)resultSet.close();} catch (SQLException e) 
	            {e.printStackTrace();}
	            try { if(null!=statement)statement.close();} catch (SQLException e) 
	            {e.printStackTrace();}
	            try { if(null!=connection)connection.close();} catch (SQLException e) 
	            {e.printStackTrace();}
	        }
	        
	        return result;
	}
	
	public Account(String username,String password)
	{
		this.username=username;
		this.password=password;
	}
	public String getUserName()
	{
		return username;
	}
	public static Account getAccount(int customerIndex)
	{
		DataSource dataSource=null;
		 Connection connection=null;
		 Statement statement=null;
			Account value=null;
			
			ResultSet resultSet = null;
	        try {
	        	Context initContext  = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
	            // Get Connection and Statement
	            connection = dataSource.getConnection();
	            statement = connection.createStatement();
	            String query = "SELECT  username, password "	         
	            		+ "FROM Accounts "
	            		+ "WHERE customer_id = " + customerIndex;
	            resultSet = statement.executeQuery(query);
	            
	            while (resultSet.next()) {
	            	String username = resultSet.getString("username");
	            	String password = resultSet.getString("password");
	                value = new Account(username,password);
	            }
	            
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            try { if(null!=resultSet)resultSet.close();} catch (SQLException e) 
	            {e.printStackTrace();}
	            try { if(null!=statement)statement.close();} catch (SQLException e) 
	            {e.printStackTrace();}
	            try { if(null!=connection)connection.close();} catch (SQLException e) 
	            {e.printStackTrace();}
	        }
	        
	        return value;
	}
}
