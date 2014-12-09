package com.bigred.objects;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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
	            String query = "SELECT  branch_id, name, latitude, longitude,address1,address2 "
	            		+",city,country,postcode,phone,opening_hours,description "
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
		//TO-DO
		return null;
	}
	public List<RoomType> getAvailableTypes(Date startDate,Date endDate)
	{
		//TO-DO
		return null;
	}

}
