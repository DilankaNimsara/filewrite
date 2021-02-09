package com.dilanka_a.usingthreads;

import com.dilanka_a.filewrite.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author dilanka_a
 */
public class main {

    public static boolean IS_COMPLETED = false;

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<String> transactionID = new ArrayList<String>();

        long startTime = System.nanoTime();

        String sql = "SELECT ID FROM LVMT_SWT_ST_TRANSACTION";

        try {

            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                transactionID.add(rs.getString("ID"));
            }

            Thread writeFile = new WriterFileThread(transactionID);
            writeFile.start();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
            }
        }
        while (IS_COMPLETED) {

        }
        long endTime = System.nanoTime();
        System.out.println("Execution Time : " + (endTime - startTime) / 100000 + "ms");
    }

}
