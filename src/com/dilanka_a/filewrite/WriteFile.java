package com.dilanka_a.filewrite;

import com.dilanka_a.dbconnection.DBConnection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Dilanka Nimsara
 */
public class WriteFile {

    public static void main(String[] args) {

        Connection con = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        long startTime = System.nanoTime();

        String sql = "SELECT id FROM users";

        try {

            FileWriter fileWriter = new FileWriter("sql_table.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                bufferedWriter.write(rs.getString("id"));
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

            bufferedWriter.close();
            System.out.println("File create successfully");

        } catch (Exception e) {
            System.out.println("Error creating file");
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        long endTime = System.nanoTime();
        System.out.println("Execution Time : " + (endTime - startTime) / 100000 + "ms");

    }


}
