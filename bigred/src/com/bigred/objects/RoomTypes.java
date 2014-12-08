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

public class RoomTypes {
	 private DataSource dataSource;
	 private Connection connection;
	 private Statement statement;
	 
	 public RoomTypes () {
		 try {
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
			} catch (NamingException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 public List<RoomType> getAvailableBranchRoomTypes(int branch_id, String startDate, String endDate) {
			
			List<RoomType> room_types_list = new ArrayList<RoomType>();
			
			ResultSet resultSet = null;
	        try {
	            // Get Connection and Statement
	            connection = dataSource.getConnection();
	            statement = connection.createStatement();
	            String query = "SELECT DISTINCT r.room_type_id, rt.size_feet, rt.price_per_week, rt.image_url "
	            		+ "FROM Rooms r "
	            		+ "LEFT JOIN Room_types rt "
	            		+ "ON r.room_type_id = rt.room_type_id "
	            		+ "WHERE r.branch_id = " + branch_id;
	            resultSet = statement.executeQuery(query);
	            
	            while (resultSet.next()) {
	                int id = resultSet.getInt("room_type_id");
	                int size = resultSet.getInt("size_feet");
	                int price = resultSet.getInt("price_per_week");
	                String image = resultSet.getString("image_url");
	                RoomType room_type = new RoomType(id, "", size, price, "", image);
	                room_types_list.add(room_type);
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
	        
	        return room_types_list;
			
		}
}
