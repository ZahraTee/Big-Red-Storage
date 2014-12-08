package com.bigred.objects;

public class RoomType {
	
	private int id;
	private String name;
	private int size;
	private int price;
	private String description;
	private String image_url;
	
	public RoomType (int id, String name, int size, int price, String description, String image_url) {
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
	public int getSize (){
		return size;
	}
	public int getPrice (){
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
