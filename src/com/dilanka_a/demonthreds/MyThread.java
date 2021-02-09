package com.dilanka_a.demonthreds;

public class MyThread {
    public static void main(String[] args) {
        System.out.println("main thread start");
        Thread thread = new DThread();
        thread.setName("DemonThread");
        thread.setDaemon(true);
        thread.start();
        System.out.println("main thread end");
    }
}
