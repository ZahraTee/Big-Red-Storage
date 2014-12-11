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

public class BookingOption {

	private int id;
	private String name;
	private String description;
	private int price;
	private String price_type;
	//private int customer_type_id;
	
	public static List<BookingOption> getBookingOptions(int customer_type) {
		DataSource dataSource=null;
	    Connection connection=null;
	    Statement statement=null;
		List<BookingOption> booking_options_list = new ArrayList<BookingOption>();
		ResultSet resultSet = null;
        try {
        	Context initContext  = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
            // Get Connection and Statement
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            
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
            
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if(null!=resultSet)resultSet.close();} catch (SQLException e) 
            {e.printStackTrace();}
            try { if(null!=statement)statement.close();} catch (SQLException e) 
            {e.printStackTrace();}
            try { if(null!=connection)connection.close();} catch (SQLException e) 
            {e.printStackTrace();}
        }
        
        return booking_options_list; 
	}
	public static List<BookingOption> getBookingOptionsById (String option_ids) {
		DataSource dataSource=null;
	    Connection connection=null;
	    Statement statement=null;

		ResultSet resultSet = null;
		
		List<BookingOption> booking_options_list = new ArrayList<BookingOption>();
		if(option_ids.length()==2)
			return booking_options_list;
        try {
        	Context initContext  = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
            // Get Connection and Statement
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            
            String query = "SELECT bo.booking_option_id, bo.name, bo.description, bo.price, pt.name AS price_type FROM Booking_options bo, Price_types pt WHERE bo.price_type_id=pt.price_type_id AND booking_option_id IN " + option_ids;
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
            
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if(null!=resultSet)resultSet.close();} catch (SQLException e) 
            {e.printStackTrace();}
            try { if(null!=statement)statement.close();} catch (SQLException e) 
            {e.printStackTrace();}
            try { if(null!=connection)connection.close();} catch (SQLException e) 
            {e.printStackTrace();}
        }
        
        return booking_options_list; 
	}
	
	private BookingOption (int id, String name, String description, int price, String price_type) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.price_type = price_type;
	}
	
	public int getId (){
		return id;
	}
	public String getName (){
		return name;
	}
	public String getDescription (){
		return description;
	}
	public int getPrice (){
		return price;
	}
	public String getPriceType (){
		return price_type;
	}
	/*public int getCustomerTypeId (){
		return customer_type_id;
	}*/	
	
}
