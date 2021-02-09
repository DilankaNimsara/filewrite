package com.dilanka_a.waitnotify;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class db {

    public static Connection CON = null;

    public synchronized void getConnection() {
        if (db.CON == null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                db.CON = DriverManager.getConnection("jdbc:oracle:thin:@192.168.20.217:1521:ceft", "LVMT_DEV2", "password");
            } catch (Exception e) {
                System.out.println("from db class >>>>>> " + e);
            }
            notify();
        }
    }

    public synchronized void print() {

        if (db.CON == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {

            Connection con = db.CON;
            PreparedStatement ps = null;
            ResultSet rs = null;
            FileWriter fileWriter;
            BufferedWriter bufferedWriter = null;

            long startTime = System.nanoTime();

            String sql = "SELECT ID FROM LVMT_SWT_ST_TRANSACTION";

            try {

                fileWriter = new FileWriter("sql_table_not_wait.txt");
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


}
