package com.alex.components;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class XTimer {
    private static javax.swing.Timer t;

    /**
     * @param runnable
     * @param delay
     */
    public static void timeOut(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * @param e
     * @param milis
     */
    public static void interval(ActionListener e, int milis) {
        t = null;
        t = new Timer(milis, e);

        java.util.Timer tt = new java.util.Timer(false);
        tt.schedule(new TimerTask() {
            @Override
            public void run() {
                t.start();
            }
        }, 0);
    }
}
