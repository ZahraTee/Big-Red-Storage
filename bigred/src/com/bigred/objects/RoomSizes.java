package com.bigred.objects;

import java.sql.Connection;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoomSizes {
	 private DataSource dataSource;
	 private Connection connection;
	 private Statement statement;
	 
	 public RoomSizes () {
		 try {
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
			} catch (NamingException e) {
	            e.printStackTrace();
	        }
	 }
}
