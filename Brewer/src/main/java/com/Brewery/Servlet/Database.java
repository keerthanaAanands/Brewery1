package com.Brewery.Servlet;import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    String url = "jdbc:mysql://localhost:3306/brewery_review_system";
    String un = "root";
    String pswd = "keerthana";
    static PreparedStatement pstmt;
    static Connection con;
    static Statement stmt;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                con = DriverManager.getConnection(url, un, pswd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getReviewsByBreweryId(String breweryId) {
        try {
            String query = "SELECT * FROM reviews WHERE brewery_id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, breweryId);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addReview(String breweryId, String userName, int rating, String message) {
    	try {
    	    String query = "INSERT INTO reviews ( brewery_id, user_name, rating, message) VALUES ( ?, ?, ?,?)";
    	    PreparedStatement pstmt = con.prepareStatement(query);
    	    
    	    pstmt.setString(1, breweryId);
    	    pstmt.setString(2, userName);
    	    pstmt.setInt(3, rating);
    	    pstmt.setString(4, message);
    	    pstmt.executeUpdate();
    	    
    	    System.out.println("Data inserted");
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}

    }

    public String check(String user) {
        String query = "SELECT id FROM users WHERE username=?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, user);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                return "1"; // User exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "0"; // User doesn't exist
    }

    public String log(String name, String password) {
        String query = "SELECT password FROM users where username=?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                return res.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "no";
    }
    
    public ResultSet getReviewsByBreweryId(int breweryId) {
        try {
            String query = "SELECT * FROM reviews WHERE brewery_id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, breweryId);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void insert(String name, String password, String email) {
        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

	
}}