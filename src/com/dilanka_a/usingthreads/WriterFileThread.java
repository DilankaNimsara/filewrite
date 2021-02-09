package com.dilanka_a.usingthreads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author dilanka_a
 */
public class WriterFileThread extends Thread {

    ArrayList<String> transactionIDs;

    public WriterFileThread(ArrayList<String> transactionID) {
        this.transactionIDs = transactionID;
    }

    @Override
    public void run() {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter("sql_table_threads.txt");
            bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < transactionIDs.size(); i++) {
                bufferedWriter.write(transactionIDs.get(i));
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            System.out.println("File create successfully...");
        } catch (IOException e) {
            System.out.println(">>>>>>>> " + e);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                System.out.println("Error creation file >>>>>>>" + e);
            }
            main.IS_COMPLETED = true;
        }
    }


}
