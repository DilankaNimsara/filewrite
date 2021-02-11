package com.dilanka_a.pool;

import com.dilanka_a.filewrite.DBConnection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dilanka_a
 */
public class PoolMain {

    public static BufferedWriter bufferedWriter = null;

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        FileWriter fileWriter;

        long startTime = System.nanoTime();

        String sql = "SELECT ID FROM LVMT_SWT_ST_TRANSACTION";

        try {

            fileWriter = new FileWriter("sql_table_threads_pool.txt");

            bufferedWriter = new BufferedWriter(fileWriter);

            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            ArrayList<String> transactions = new ArrayList<String>();

            while (rs.next()) {
                transactions.add(rs.getString("ID"));
//                pool.submit(new PoolWrite(rs.getString("ID"), bufferedWriter));
//                Runnable runnable=new PoolWrite(rs.getString("ID"), bufferedWriter);
//                pool.execute(runnable);
            }

//            new Thread(() -> {
//                ExecutorService pool = Executors.newFixedThreadPool(5);
//                for (int i = 0; i < transactions.size() / 4; i++) {
//                    Runnable writeFile = new PoolWrite(transactions.get(i), bufferedWriter);
//                    pool.execute(writeFile);
//                }
//                pool.shutdown();
//            }).start();
//
//            new Thread(() -> {
//                ExecutorService pool = Executors.newFixedThreadPool(5);
//                for (int i = transactions.size() / 4; i < transactions.size()/2; i++) {
//                    Runnable writeFile = new PoolWrite(transactions.get(i), bufferedWriter);
//                    pool.execute(writeFile);
//                }
//                pool.shutdown();
//            }).start();
//
//            new Thread(() -> {
//                ExecutorService pool = Executors.newFixedThreadPool(5);
//                for (int i = transactions.size() / 2; i < transactions.size(); i++) {
//                    Runnable writeFile = new PoolWrite(transactions.get(i), bufferedWriter);
//                    pool.execute(writeFile);
//                }
//                pool.shutdown();
//            }).start();


            ExecutorService pool1 = Executors.newFixedThreadPool(10);
            ExecutorService pool2 = Executors.newFixedThreadPool(10);
            ExecutorService pool3 = Executors.newFixedThreadPool(10);

            for (int i = 0; i < transactions.size() / 4; i++) {
                Runnable writeFile = new PoolWrite(transactions.get(i), bufferedWriter);
                pool1.execute(writeFile);
            }

            for (int i = transactions.size() / 4; i < transactions.size() / 2; i++) {
                Runnable writeFile = new PoolWrite(transactions.get(i), bufferedWriter);
                pool2.execute(writeFile);
            }

            for (int i = transactions.size() / 2; i < transactions.size(); i++) {
                Runnable writeFile = new PoolWrite(transactions.get(i), bufferedWriter);
                pool3.execute(writeFile);
            }


            pool1.shutdown();
            pool2.shutdown();
            pool3.shutdown();

            while (!pool1.isTerminated() && !pool2.isTerminated() && !pool3.isTerminated()) {
            }

            System.out.println("File created successfully...");

        } catch (Exception e) {
            System.out.println("Error creating file >>>>>>>>> " + e);
        } finally {
            try {
                bufferedWriter.flush();
                bufferedWriter.close();
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException | IOException throwables) {
            }
        }

        long endTime = System.nanoTime();
        System.out.println("Execution Time : " + (endTime - startTime) / 100000 + "ms");

    }

}
