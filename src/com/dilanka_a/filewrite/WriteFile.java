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
        PreparedStatement ps = null;
        ResultSet rs = null;
        FileWriter fileWriter;
        BufferedWriter bufferedWriter = null;

        long startTime = System.nanoTime();

        String sql = "SELECT ID FROM LVMT_SWT_ST_TRANSACTION";

        try {

            fileWriter = new FileWriter("sql_table.txt");
            bufferedWriter = new BufferedWriter(fileWriter);

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                bufferedWriter.write(rs.getString("ID"));
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

            System.out.println("File create successfully");

        } catch (Exception e) {
            System.out.println("Error creating file >> " + e);
        } finally {
            try {
                bufferedWriter.close();
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException | IOException throwables) {
            }
        }

        long endTime = System.nanoTime();
        System.out.println("Execution Time : " + (endTime - startTime) / 100000 + "ms");

    }


}
