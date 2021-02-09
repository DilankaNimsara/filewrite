package com.dilanka_a.waitnotify;

/**
 * @author dilanka_a
 */
public class WriteFileWithNotify {
    public static void main(String[] args) throws InterruptedException {

        db dbCon = new db();

        new Thread(() -> {
            dbCon.getConnection();
        }).start();

        new Thread(() -> dbCon.print()).start();

    }


}
