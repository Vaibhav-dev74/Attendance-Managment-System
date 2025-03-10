/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.*;
/**
 *
 * @author HP
 */
public class ConnectionProvider {
    
    private static final String DB_NAME = "attendancesystem";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/attendancesystem";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "W7301@jqir#";
    
    public static Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//.cj removed
            Connection con = DriverManager.getConnection(DB_URL + "?useSSL=false", DB_USERNAME, DB_PASSWORD);

		if (!databaseExists(con, DB_NAME)) {
		    createDatabase(con, DB_NAME);
		}

		// Reconnect to the specific database after it's been created (if needed)
		con = DriverManager.getConnection(DB_URL + "?useSSL=false", DB_USERNAME, DB_PASSWORD);

		return con;

            
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
     private static boolean databaseExists(Connection con, String dbName) throws Exception {
        Statement stmt = con.createStatement();
        return stmt.executeQuery("SHOW DATABASES LIKE '" + dbName + "'").next(); // Fixed query concatenation
    }
     
    private static void createDatabase(Connection con, String dbName) throws Exception {
	    Statement stmt = con.createStatement();
	    stmt.executeUpdate("CREATE DATABASE " + dbName);
	    System.out.println("Database " + dbName + " created successfully.");
     }
}

