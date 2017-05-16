package com.wwssaadd.autopage;

import vdll.utils.time.DateTime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hocean on 2017/5/16.
 */
public class Holiday {
    private JButton button1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Holiday");
        frame.setContentPane(new Holiday().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel panel1;

    public Holiday() {
        DateTime dt = DateTime.createDate("2017-05-01");
        DateTime dtEnd = DateTime.createDate("2018-01-01");

        for (int i = 0; dt.getTime().getTime() < dtEnd.getTime().getTime(); i++,dt.addDate(1)) {

            int week = dt.getWeek();
            if(week == 6 || week == 0){
                System.out.println(dt);
            }
        }







        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
