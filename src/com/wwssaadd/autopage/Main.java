package com.wwssaadd.autopage;

import javax.swing.*;

/**
 * Created by Hocean on 2017/1/25.
 */
public class Main {
    public static JFrame frame;
    public static void main(String[] args) {
        frame = new JFrame("Win");
        //frame.setContentPane(new Win().panel1);
        frame.setContentPane(new Test().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
}
}
