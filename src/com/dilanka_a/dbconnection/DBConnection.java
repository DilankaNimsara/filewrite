package com.dilanka_a.dbconnection;

import java.sql.*;

/**
 * @author Dilanka Nimsara
 */
public class DBConnection {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/usernew", "root", "");
            return con;
        } catch (Exception e) {
            return null;
        }
    }

}
