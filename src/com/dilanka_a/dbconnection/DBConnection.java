package com.dilanka_a.dbconnection;

import java.sql.*;

/**
 * @author Dilanka Nimsara
 */
public class DBConnection {

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.20.217:1521:ceft", "LVMT_DEV2", "password");
            return con;
        } catch (Exception e) {
            return null;
        }
    }

}
