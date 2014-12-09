package com.bigred.objects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RoomType {
	
	private int id;
	private String name;
	private double size;
	private double price;
	private String description;
	private String image_url;
	
	

	public static RoomType getRoomType(int index)
	{
		DataSource dataSource=null;
		 Connection connection=null;
		 Statement statement=null;
			RoomType value=null;
			
			ResultSet resultSet = null;
	        try {
	        	Context initContext  = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
	            // Get Connection and Statement
	            connection = dataSource.getConnection();
	            statement = connection.createStatement();
	            String query = "SELECT  room_type_id, size_feet, price_per_week, image_url,name,description "
	            		+ "FROM Room_types "
	            		+ "WHERE room_type_id = " + index;
	            resultSet = statement.executeQuery(query);
	            
	            while (resultSet.next()) {
	                int id = resultSet.getInt("room_type_id");
	                double size = resultSet.getDouble("size_feet");
	                double price = resultSet.getDouble("price_per_week");
	                String image = resultSet.getString("image_url");
	                String name = resultSet.getString("name");
	                String description = resultSet.getString("description");
	                value = new RoomType(id, name, size, price, description, image);
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
	public RoomType (int id, String name, double size, double price, String description, String image_url) {
		this.id = id;
		this.name = name;
		this.size = size;
		this.price = price;
		this.description = description;
		this.image_url = image_url;
	}
	
	public int getId (){
		return id;
	}
	public String getName (){
		return name;
	}
	public double getSize (){
		return size;
	}
	public double getPrice (){
		return price;
	}
	public String getDescription (){
		return description;
	}
	public String getImageUrl (){
		return image_url;
	}
	
	public double getSizeInMeters () {
		 double size_meters = size * 0.09290304;
		 return size_meters;
	}
	
	
}
