package com.bigred.objects;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CustomerTypes {
	
    private DataSource dataSource;
    private Connection connection;
    private Statement statement;
	
	public CustomerTypes () {
		try {
            // Get DataSource
            Context initContext  = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
        } catch (NamingException e) {
            e.printStackTrace();
        }
	}
	
	public List<CustomerType> getCustomerList() {
		
		List<CustomerType> customer_type_list = new ArrayList<CustomerType>();
		
		ResultSet resultSet = null;
        try {
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
            
            
        } catch (SQLException e) {
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

}
