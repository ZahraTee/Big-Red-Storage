package com.bigred.objects;
import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Customer {
	
	
	private CustomerType type;
	private Account account;
	private int id;
	private String firstName,lastName,email,phone;

	public static Customer getCustomerFromCookieValue(String value)
	{
		//for now
		return getCustomer(Integer.parseInt(value));
	}
	public static Customer getCustomer(int index)
	{
		DataSource dataSource=null;
		 Connection connection=null;
		 Statement statement=null;
			Customer value=null;
			
			ResultSet resultSet = null;
	        try {
	        	Context initContext  = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
	            // Get Connection and Statement
	            connection = dataSource.getConnection();
	            statement = connection.createStatement();
	            String query = "SELECT  customer_id, customer_type_id, first_name, last_name, email, phone "	         
	            		+ "FROM Customers "
	            		+ "WHERE customer_id = " + index;
	            resultSet = statement.executeQuery(query);
	            
	            while (resultSet.next()) {
	            	int customer_id = resultSet.getInt("customer_id");
	            	int customer_type_id=resultSet.getInt("customer_type_id");
	            	String first_name = resultSet.getString("first_name");
	            	String last_name = resultSet.getString("last_name");
	            	String email = resultSet.getString("email");
	            	String phone = resultSet.getString("phone");
	            	CustomerType customerType = CustomerType.getCustomerType(customer_type_id);	  
	                value=new Customer(customerType);
	                value.id=customer_id;
	                value.firstName=first_name;
	                value.lastName=last_name;
	                value.firstName=first_name;
	                value.email=email;
	                value.phone=phone;
	                value.account=Account.getAccount(customer_id);
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
	public Customer(CustomerType type)
	{
		this.type=type;
		this.account=null;
	}
	public boolean isLoggedIn()
	{
		return account!=null;
	}
	public void register(String firstName,String lastName,String email,String username,String password)
	{
		this.account = new Account(username,password);
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		int typeId = type.getId();
			
		DataSource dataSource=null;
		Connection conn=null;
		PreparedStatement st=null;
		try { 
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			String SQL_INSERT = "INSERT INTO Customers (customer_type_id,first_name,last_name,email,phone) " + 
	                "VALUES ("+typeId+", '"+firstName+"', '"+lastName+"', '"+email+"','')";
			dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
	        conn = dataSource.getConnection();
	        st = conn.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
	          //Insert to Customer and put customerId in id.
            st.executeUpdate(); 
            ResultSet set = st.getGeneratedKeys();
            if(set.next()) {
            	this.id=(int)set.getLong(1);
            }
            System.out.println(this.id);
            
            
            //Insert Account
            st.executeUpdate("INSERT INTO Accounts (username,password,customer_id) " + 
                    "VALUES ('"+username+"', '"+password+"', "+this.id+")"); 
                     
            conn.close(); 
        } catch (Exception e) { 
            System.err.println(e.getMessage()); 
        } 
		
	}
	public static Customer findCustomer(String username,String password)
	{
		DataSource dataSource=null;
		Connection connection=null;
		Statement statement=null;
		int customerIndex=-1;
			
			ResultSet resultSet = null;
	        try {
	        	Context initContext  = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
	            // Get Connection and Statement
	            connection = dataSource.getConnection();
	            statement = connection.createStatement();
	            String query = "SELECT  customer_id "	         
	            		+ "FROM Accounts "
	            		+ "WHERE username = '" + username + "' AND password = '"+password+"'";
	            resultSet = statement.executeQuery(query);
	            
	            while (resultSet.next()) {
	            	customerIndex=resultSet.getInt("customer_id");
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
	        
		return customerIndex==-1?null:getCustomer(customerIndex);
	}

	public String getName()
	{
		return lastName+", "+firstName;
	}
	public CustomerType getCustomerType()
	{
		return type;
	}
	public int getId()
	{
		return id;
	}
	public String getEmail()
	{
		return email;
	}
	public String getPhone()
	{
		return phone;
	}
}
