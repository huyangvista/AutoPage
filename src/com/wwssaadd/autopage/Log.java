package com.wwssaadd.autopage;

import vdll.data.msql.MySql;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Hocean on 2017/5/5.
 */
public class Log {

    private JButton button1;
    public JPanel panel1;
    private JButton button2;
    MySql mySql = new MySql();

    public Log() {
        mySql.open();
        Main.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mySql.close();
                super.windowClosing(e);
            }
        });


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
