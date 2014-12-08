package com.bigred.objects;

public class BookingOption {

	private int id;
	private String name;
	private String description;
	private int price;
	private String price_type;
	//private int customer_type_id;
	
	
	public BookingOption (int id, String name, String description, int price, String price_type) {
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
