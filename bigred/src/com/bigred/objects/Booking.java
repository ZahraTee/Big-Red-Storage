package com.bigred.objects;
import java.util.*;

public class Booking {
	
	
	private int lastPage=1;
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
	public int getLastPage()
	{
		return lastPage;
	}
	public Branch getBranch()
	{
		return branch;
	}
	public Date getStartDate()
	{
		return startDate;
	}
	public Date getEndDate()
	{
		return endDate;
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
	public void bookingConfirmed()
	{
		//TO-DO
		//Write to the database all what has been done
	}
}
