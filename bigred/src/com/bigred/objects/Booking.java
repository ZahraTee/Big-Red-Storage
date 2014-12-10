package com.bigred.objects;
import java.sql.*;
import java.util.Date;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Booking {
	
	
	private int lastPage=1;
	private int id;
	private Branch branch;
	private Date startDate,endDate;
	private RoomType roomType;
	private List<BookingOption> options;
	public Booking()
	{
		lastPage=1;
	}
	
	public void setBranch(Branch branch)
	{
		this.branch=branch;
		lastPage=2;
	}
	public void setDate(Date startDate,Date endDate)
	{
		this.startDate=startDate;
		this.endDate=endDate;
		lastPage=3;
	}
	public void setRoomType(int roomTypeIndex)
	{
		this.roomType=RoomType.getRoomType(roomTypeIndex);
		lastPage=4;
	}
	public void setBookingOptions(List<BookingOption> options)
	{
		this.options=options;
		lastPage=5;
	}
	
	//TO-DO: find a room
	public void bookingConfirmed(Customer customer)
	{
		int customerId=customer.getId();
		int discount = customer.getCustomerType().getDiscount();
		DataSource dataSource=null;
		 Connection conn=null;
		 Statement st=null;
		try { 
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			 dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
	           conn = dataSource.getConnection();
	            st = conn.createStatement();
	            
	            //TO-DO find a room_id
	            int roomId=1;
	            
	          //Insert Booked_Room.
           st.executeUpdate("INSERT INTO Booked_rooms (room_id,start_date,end_date) " + 
               "VALUES ("+roomId+", '"+new java.sql.Date(startDate.getTime())+"', '"+new java.sql.Date(endDate.getTime())+"')"); 
           
           
           
           //Insert Booking and set Id
           PreparedStatement statement = conn.prepareStatement("INSERT INTO Bookings (customer_id,room_id,date_booked,start_date,end_date,total_price) " + 
                   "VALUES ("+customerId+", "+roomId+", '"+new java.sql.Date(new Date().getTime())+"', '"+new java.sql.Date(startDate.getTime())+"', '"+new java.sql.Date(endDate.getTime())+"', "+totalCost(discount)+")"
                   ,Statement.RETURN_GENERATED_KEYS);
           statement.executeUpdate(); 
           ResultSet set = statement.getGeneratedKeys();
           if(set.next())
           		this.id=(int)set.getLong(1);
           
           
           //Insert Booking_options_to_Bookings
           for(BookingOption option : options)
           {
        	   st.executeUpdate("INSERT INTO Booking_options_to_Bookings (booking_id,booking_option_id) " + 
                       "VALUES ("+this.id+", "+option.getId()+")"); 
           }
           st.executeUpdate("INSERT INTO Payments (booking_id,amount,date) " + 
                   "VALUES ("+this.id+", "+totalCost(discount)+", '"+new java.sql.Date(new Date().getTime())+"')"); 
           conn.close(); 
       } catch (Exception e) { 
           System.err.println(e.getMessage()); 
       } 
		//TO-DO
		//Write to the database all what has been done
	}
	

	public Branch getBranch()
	{
		return branch;
	}
	public RoomType getRoomType()
	{
		return roomType;
	}
	public List<BookingOption> getOptions()
	{
		return options;
	}
	public int getId()
	{
		return id;
	}
	public Date getStartDate()
	{
		return startDate;
	}
	public Date getEndDate()
	{
		return endDate;
	}
	public int getLastPage()
	{
		return lastPage;
	}
	
	public double getWeeklyCost()
	{
		double value = 0;
		if(roomType!=null)
			value+=roomType.getPrice();
		for(BookingOption option : options)
			if(option.getPriceType().equals("weekly"))
				value+=option.getPrice();
		return value;
	}
	public double getExtraCost()
	{
		double value=0;
		for(BookingOption option : options)
			if(!option.getPriceType().equals("weekly"))
				value+=option.getPrice();
		return value;
	}
	public double totalCost()
	{
		return getWeeklyCost()+getExtraCost();
	}
	public double totalCost(int discount)
	{
		return totalCost()*(1-discount/100.0);
	}
}
