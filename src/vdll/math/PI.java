package vdll.math;

import java.util.Scanner;

/**
 * Created by Hocean on 2017/6/1.
 */
public class PI {
    static double cutPi = 0;
    static double limitPi = 0;


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // cut();
                limit();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //System.out.println("cut " + cutPi);
                    System.out.println("limit " + limitPi);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    static void cut() {
        double y=1.0;
        for(long i=0;true;i++){
            double pi=3*Math.pow(2, i)*y;
            cutPi = pi;
            System.out.println("第"+i+"次切割,为正"+(6+6*i)+"边形，圆周率π≈"+pi);
            y=Math.sqrt(2-Math.sqrt(4-y*y));
        }
    }

    static void limit() {
        double pi = 0;
        boolean ou = true;
        for (long i = 1; true; i += 2) {
            if (ou) {
                pi += 1.0 / i;
            } else {
                pi -= 1.0 / i;
            }
            ou = !ou;
            limitPi = pi * 4;
        }
    }
}
