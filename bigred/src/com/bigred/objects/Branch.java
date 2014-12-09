package com.bigred.objects;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Branch {
	
	private int id;
	private String name,latitude,longitude;
	private String address1,address2,city,country;
	private String postcode,phone,opening_hours,description;
	private Branch(int id,String [] str)
	{
		this.id=id;
		this.name=str[0];
		this.latitude=str[1];
		this.longitude=str[2];
		this.address1=str[3];
		this.address2=str[4];
		this.city=str[5];
		this.country=str[6];
		this.postcode=str[7];
		this.phone=str[8];
		this.opening_hours=str[9];
		this.description=str[10];
	}
	
	public static Branch getBranch(int index)
	{
		DataSource dataSource=null;
		 Connection connection=null;
		 Statement statement=null;
			Branch value=null;
			
			ResultSet resultSet = null;
	        try {
	        	Context initContext  = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
	            // Get Connection and Statement
	            connection = dataSource.getConnection();
	            statement = connection.createStatement();
	            String query = "SELECT  * "
	            		+ "FROM Branches "
	            		+ "WHERE branch_id = " + index;
	            resultSet = statement.executeQuery(query);
	            
	            while (resultSet.next()) {
	                int id = resultSet.getInt("branch_id");
	                String str[] = new String[11];
	                str[0]=resultSet.getString("name");
	        		str[1]=resultSet.getString("latitude");
	        		str[2]=resultSet.getString("longitude");
	        		str[3]=resultSet.getString("address1");
	        		str[4]=resultSet.getString("address2");
	        		str[5]=resultSet.getString("city");
	        		str[6]=resultSet.getString("country");
	        		str[7]=resultSet.getString("postcode");
	        		str[8]=resultSet.getString("phone");
	        		str[9]=resultSet.getString("opening_hours");
	        		str[10]=resultSet.getString("description");
	                value = new Branch(id,str);
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
	public static List<Branch> getAllBranches()
	{
		DataSource dataSource=null;
	    Connection connection=null;
	    Statement statement=null;
		List<Branch> allBranches = new ArrayList<>();
		
		ResultSet resultSet = null;
        try {
        	Context initContext  = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
            // Get Connection and Statement
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM Branches";
            resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                int id = resultSet.getInt("branch_id");
                String str[] = new String[11];
                str[0]=resultSet.getString("name");
        		str[1]=resultSet.getString("latitude");
        		str[2]=resultSet.getString("longitude");
        		str[3]=resultSet.getString("address1");
        		str[4]=resultSet.getString("address2");
        		str[5]=resultSet.getString("city");
        		str[6]=resultSet.getString("country");
        		str[7]=resultSet.getString("postcode");
        		str[8]=resultSet.getString("phone");
        		str[9]=resultSet.getString("opening_hours");
        		str[10]=resultSet.getString("description");
                allBranches.add(new Branch(id,str));
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
        
        return allBranches;
	}
	public List<RoomType> getAvailableTypes(Date startDate,Date endDate)
	{
		java.sql.Date sd = new java.sql.Date(startDate.getTime());
		java.sql.Date ed = new java.sql.Date(endDate.getTime());
		String SQL_QUERY =
		"SELECT * FROM Room_types "+
		"WHERE room_type_id IN "+
		"( "+
		"  SELECT room_type_id FROM Rooms "+
		"  WHERE branch_id = "+id+" AND room_id NOT IN "+
		"  ( "+
		"    SELECT room_id FROM Booked_rooms "+
		"    WHERE (start_date<='"+sd+"' AND '"+sd+"' <=end_date) OR "+
		"          (start_date<='"+ed+"' AND '"+ed+"' <=end_date) "+
		"    GROUP BY room_id "+
		"  ) "+
		"  GROUP BY room_type_id "+
		") ";
		
		
		DataSource dataSource=null;
	    Connection connection=null;
	    Statement statement=null;
		List<RoomType> roomTypeList = new ArrayList<>();
		ResultSet resultSet = null;
        try {
        	Context initContext  = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
            // Get Connection and Statement
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_QUERY);
            
            while (resultSet.next()) {
                int id = resultSet.getInt("room_type_id");
                String name = resultSet.getString("name");
                double size = resultSet.getDouble("size_feet");
                double price = resultSet.getDouble("price_per_week");
                String description = resultSet.getString("description");
                String imageUrl = resultSet.getString("image_url");
                roomTypeList.add(new RoomType(id,name,size,price,description,imageUrl));
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
        
        return roomTypeList; 
	}
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public String getLatitude()
	{
		return latitude;
	}
	public String getLongitude()
	{
		return longitude;
	}
	public String getAddress()
	{
		return address1+"\n"+address2+"\n"+postcode+", "+city+", "+country;
	}
	public String toString()
	{
		return name+"\n"+getAddress()+"\nPhone: "+phone+"\nOpening Hours:"+opening_hours;
	}
}
