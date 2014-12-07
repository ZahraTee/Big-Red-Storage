package com.bigred.first;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/SecondServlet")
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private DataSource dataSource;
    private Connection connection;
    private Statement statement;
     
    public void init() throws ServletException {
        try {
            // Get DataSource
            Context initContext  = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            dataSource = (DataSource)envContext.lookup("jdbc/sql260399");
 
             
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
 
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
         
        ResultSet resultSet = null;
        try {
            // Get Connection and Statement
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM Customer_types";
            resultSet = statement.executeQuery(query);
            PrintWriter out = resp.getWriter();
            while (resultSet.next()) {
                out.println(resultSet.getString(1) + "; " + resultSet.getString(2) + "; " + resultSet.getString(3) + ";" + resultSet.getString(4) + ";" + resultSet.getString(5) + ";");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try { if(null!=resultSet)resultSet.close();} catch (SQLException e) 
            {e.printStackTrace();}
            try { if(null!=statement)statement.close();} catch (SQLException e) 
            {e.printStackTrace();}
            try { if(null!=connection)connection.close();} catch (SQLException e) 
            {e.printStackTrace();}
        }
    }
}