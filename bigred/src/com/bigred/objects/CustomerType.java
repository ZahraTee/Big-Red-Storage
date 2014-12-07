package com.bigred.objects;

public class CustomerType {
	
	private int id;
	private String name;
	private int discount;
	private String image;
	private String description;
	
	public CustomerType (int id, String name, int discount, String image, String description) {
		this.id = id;
		this.name = name;
		this.discount = discount;
		this.image = image;
		this.description = description;
	}
	
	public int getId (){
		return id;
	}
	public String getName (){
		return name;
	}
	public int getDiscount (){
		return discount;
	}
	public String getImage (){
		return image;
	}
	public String getDescription (){
		return description;
	}
	

}
