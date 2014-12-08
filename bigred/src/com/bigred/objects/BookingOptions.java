package com.bigred.objects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BookingOptions {
	
	private DataSource dataSource;
    private Connection connection;
    private Statement statement;
	
	public BookingOptions() {
		try {
            // Get DataSource
            Context initContext  = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
        } catch (NamingException e) {
            e.printStackTrace();
        }
	}
	
	public List<BookingOption> getBookingOptions(int customer_type) {
		
		List<BookingOption> booking_options_list = new ArrayList<BookingOption>();
		
		ResultSet resultSet = null;
        try {
            // Get Connection and Statement
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            /*String query = "SELECT bo.booking_option_id, bo.name, bo.description, bo.price, pt.name AS price_type "
            		+ "FROM Booking_options_to_Customer_types botct, Price_types pt "
            		+ "LEFT JOIN Booking_options bo "
            		+ "ON botct.booking_option_id = bo.booking_option_id "
            		+ "WHERE bo.price_type_id = pt.price_type_id AND botct.customer_type_id = " + customer_type;*/
            
            String query = "SELECT bo.booking_option_id, bo.name, bo.description, bo.price, pt.name AS price_type "
            		+ "FROM Booking_options_to_Customer_types botct, Price_types pt, Booking_options bo "
            		+ "WHERE bo.price_type_id = pt.price_type_id AND botct.booking_option_id = bo.booking_option_id AND botct.customer_type_id = " + customer_type;
            resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                int id = resultSet.getInt("booking_option_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                String price_type = resultSet.getString("price_type");
                
                BookingOption booking_option = new BookingOption (id, name, description, price, price_type);
                booking_options_list.add(booking_option);
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
        
        return booking_options_list; 
	}
}
