package com.dilanka_a.timetask;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Dilanka Nimsara
 */
public class TimerMain {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task completed..");
            }
        };
        timer.scheduleAtFixedRate(timerTask,0,120000);
        Calendar calendar = Calendar.getInstance();
    }
}
