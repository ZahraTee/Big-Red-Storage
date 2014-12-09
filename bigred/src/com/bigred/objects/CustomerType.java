package com.bigred.objects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CustomerType {
	
	private int id;
	private String name;
	private int discount;
	private String image;
	private String description;
	
	public static List<CustomerType> getCustomerList() {
		DataSource dataSource=null;
	    Connection connection=null;
	    Statement statement=null;
		List<CustomerType> customer_type_list = new ArrayList<CustomerType>();
		
		ResultSet resultSet = null;
        try {
        	Context initContext  = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
            // Get Connection and Statement
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM Customer_types";
            resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                int id = resultSet.getInt("customer_type_id");
                String name = resultSet.getString("name");
                int discount = resultSet.getInt("discount_percentage");
                String image = resultSet.getString("image_url");
                String description = resultSet.getString("short_description");
                CustomerType customer_type = new CustomerType (id, name, discount, image, description);
                customer_type_list.add(customer_type);
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
        
        return customer_type_list;
		
	}
	public static CustomerType getCustomerType(int index)
	{
		DataSource dataSource=null;
		 Connection connection=null;
		 Statement statement=null;
			CustomerType value=null;
			
			ResultSet resultSet = null;
	        try {
	        	Context initContext  = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
	            // Get Connection and Statement
	            connection = dataSource.getConnection();
	            statement = connection.createStatement();
	            String query = "SELECT  customer_type_id, name, discount_percentage, image_url, short_description "	         
	            		+ "FROM Customer_types "
	            		+ "WHERE customer_type_id = " + index;
	            resultSet = statement.executeQuery(query);
	            
	            while (resultSet.next()) {
	            	int id = resultSet.getInt("customer_type_id");
	            	String name = resultSet.getString("name");
	            	int discount = resultSet.getInt("discount_percentage");
	            	String image = resultSet.getString("image_url");
	            	String description = resultSet.getString("short_description");
	                value = new CustomerType(id,name,discount,image,description);
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
	private CustomerType (int id, String name, int discount, String image, String description) {
		this.id = id;
		this.name = name;
		this.discount = discount;
		this.image = image;
		this.description = description;
	}
	
	public int getId (){
		return id;
	}
	public String getName (){
		return name;
	}
	public int getDiscount (){
		return discount;
	}
	public String getImage (){
		return image;
	}
	public String getDescription (){
		return description;
	}
	

}
