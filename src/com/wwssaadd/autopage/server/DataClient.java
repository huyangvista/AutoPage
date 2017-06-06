package com.wwssaadd.autopage.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import vdll.net.socket.Client;
import vdll.net.socket.Conned;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by Hocean on 2017/6/6.
 */
public class DataClient {
    private JTextField textField1;
    private JPanel panel1;
    private JButton button1;

    public DataClient() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gson gson = new Gson();

                Client client = new Client();
                client.setReceive(new Conned.IReceive() {
                    @Override
                    public void invoke(Object msg) {
                        Command comm = gson.fromJson(msg.toString(), Command.class);
                        switch (comm.getActionData()){
                            case "u":
                                System.out.println("影响行数：" + comm.getValue());

                                break;
                            case "q":
                                Type mtype = new TypeToken<List<Map<String, Object>>>() { }.getType();
                                List<Map<String, Object>> list = gson.fromJson(comm.getValue(), mtype);

                                System.out.println("数据集：" + list);
                                break;
                        }
                    }
                });
                client.Conn();
                Command comm = new Command();
                comm.setActionData("u");
                comm.setDbURL("jdbc:mysql://vives.cc:3306/vives?useUnicode=true&characterEncoding=utf8");
                comm.setUsername("root");
                comm.setPassword("hoceanvista");
                comm.setSql("SELECT * FROM `t_user`;");
                comm.setSql("UPDATE `t_user` SET `user_phone`='888' WHERE (`id`='3');");

                String json = gson.toJson(comm);
                client.conned.send(json);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DataClient");
        frame.setContentPane(new DataClient().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
