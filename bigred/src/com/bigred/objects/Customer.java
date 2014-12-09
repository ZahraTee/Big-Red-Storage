package com.bigred.objects;

public class Customer {
	
	
	private CustomerType type;
	private Account account;
	private String firstName,lastName,email,phone;
	public static Customer getCustomerFromCookieValue(String value)
	{
		//TO-DO
		return null;
	}
	public Customer(CustomerType type)
	{
		this.type=type;
		this.account=null;
	}
	public boolean isLoggedIn()
	{
		return account!=null;
	}
	public String getWelcomeStatement()
	{
		return "Welcome <b>"+this.account.getUserName()+"</b>";
	}
	public void register(String email,String username,String password)
	{
		this.account = new Account(username,password);
		//TO-DO database insert
	}
	private Customer findCustomer(String username,String password)
	{
return null;
	}
	public boolean login(String username,String password)
	{
		//TO-DO
		return false;
		//returns null if login is invalid
	}
}
