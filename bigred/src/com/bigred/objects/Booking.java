package com.bigred.objects;
import java.sql.*;
import java.util.Date;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Booking {
	
	
	public static List<Booking> allBookingsOf(Customer customer)
	{
		List<Booking> list = new ArrayList<>();
		DataSource dataSource=null;
	    Connection connection=null;
	    Statement statement=null;		
		ResultSet resultSet = null,set=null;
        try {
        	Context initContext  = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
            // Get Connection and Statement
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "SELECT b.booking_id, r.branch_id, "
            		+ "r.room_type_id AS room_type_id, b.start_date, b.end_date, b.total_price "
            		+ "FROM Bookings b, Rooms r WHERE b.room_id=r.room_id "
            		+ "AND customer_id="+customer.getId();
            
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Booking b = new Booking();
                b.id=resultSet.getInt("booking_id");
                b.roomType=RoomType.getRoomType(resultSet.getInt("room_type_id"));
                b.branch=Branch.getBranch(resultSet.getInt("branch_id"));
                b.price=resultSet.getDouble("total_price");
                b.startDate=resultSet.getTimestamp("start_date");
                b.endDate=resultSet.getTimestamp("end_date");
                Statement st = connection.createStatement();
                set = st.executeQuery("SELECT booking_option_id FROM "
                		+ "Booking_options_to_Bookings WHERE booking_id="+b.id);
                String str="(";
                boolean first=true;
                while(set.next())
                {
                	if(first)
                		first=false;
                	else
                		str+=",";
                	str+=set.getInt("booking_option_id");
                }
                str+=")";
                b.options=BookingOption.getBookingOptionsById(str);
                list.add(b);
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
		return list;
	}
	
	private int lastPage=1;
	private int id;
	private Branch branch;
	private Date startDate,endDate;
	private RoomType roomType;
	private List<BookingOption> options;
	private double price;
	
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
			ResultSet set;
			 dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
	           conn = dataSource.getConnection();
	            st = conn.createStatement();
	            java.sql.Date sd = new java.sql.Date(startDate.getTime());
	    		java.sql.Date ed = new java.sql.Date(endDate.getTime());
	            //TO-DO find a room_id
	            int roomId=1;
	            String SQL_QUERY =
	            		"SELECT room_id FROM Rooms "+
	            		"WHERE branch_id = "+branch.getId()+" AND room_id NOT IN "+
	            		"( "+
	            		"  SELECT room_id FROM Booked_rooms "+
	            		"  WHERE (start_date<='"+sd+"' AND '"+sd+"' <=end_date) OR "+
	            		"        (start_date<='"+ed+"' AND '"+ed+"' <=end_date) "+
	            		"  GROUP BY room_id "+
	            		") ";
	            
	            set=st.executeQuery(SQL_QUERY);
	       set.next();
	       roomId=set.getInt("room_id");
	          //Insert Booked_Room.
           st.executeUpdate("INSERT INTO Booked_rooms (room_id,start_date,end_date) " + 
               "VALUES ("+roomId+", '"+new java.sql.Date(startDate.getTime())+"', '"+new java.sql.Date(endDate.getTime())+"')"); 
           
           
           
           //Insert Booking and set Id
           PreparedStatement statement = conn.prepareStatement("INSERT INTO Bookings (customer_id,room_id,date_booked,start_date,end_date,total_price) " + 
                   "VALUES ("+customerId+", "+roomId+", '"+new java.sql.Date(new Date().getTime())+"', '"+new java.sql.Date(startDate.getTime())+"', '"+new java.sql.Date(endDate.getTime())+"', "+totalCost(discount)+")"
                   ,Statement.RETURN_GENERATED_KEYS);
           statement.executeUpdate(); 
           set = statement.getGeneratedKeys();
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
	}
	public void setPrice(double price)
	{
		this.price= price;
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
		double timeInWeeks = (double)(endDate.getTime()-startDate.getTime())/(1000.0*60*60*24*7);
		return timeInWeeks*getWeeklyCost()+getExtraCost();
	}
	public double totalCost(int discount)
	{
		return totalCost()*(1-discount/100.0);
	}

	public String toString()
	{
		String str="";
		str+="Booking Id: "+id;
		str+="\nStarts: "+startDate;
		str+="\nEnds: "+endDate;
		str+="\nRoom Type: "+roomType.getName();
		if(options.size()!=0)
		{
			str+="\nExtra Features:";
			for(BookingOption option : options)
				str+="\n\t"+option.getName();
		}
		str+="\nTotal Price: "+String.format("%.2f",price)+"£";
		str+="\nLocation:\n"+branch.getAddress();
		return str;
	}
}
