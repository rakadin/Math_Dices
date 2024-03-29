package com.example.math_dices.controller;

import android.os.Handler;
/*
  controll time delay
 */
public class Utils {
    // Delay mechanism

    public interface DelayCallback{
        void afterDelay();
    }

    public static void delay(int secs, final DelayCallback delayCallback){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                delayCallback.afterDelay();
            }
        }, secs * 500); // afterDelay will be executed after (secs*1000) milliseconds.
    }
}
