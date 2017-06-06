package com.wwssaadd.autopage.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import vdll.data.msql.MySql;
import vdll.net.socket.Conned;
import vdll.net.socket.Server;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by Hocean on 2017/6/6.
 */
public class DataServer {
    public static void main(String[] args) {
        new DataServer();
    }

    public DataServer() {
        Server server = new Server() {
            @Override
            public Conned.IReceive getReceive(final Conned conned) {
                return new Conned.IReceive() {
                    @Override
                    public void invoke(Object msg) {
                        dispose(msg, conned);
                    }
                };
            }
        };
        server.Conn();
    }

    public void dispose(Object msg, Conned conned) {
        Gson gson = new Gson();
        Command comm = gson.fromJson(msg.toString(), Command.class);
        MySql mysql = new MySql();
        mysql.open(comm.getDbURL(), comm.getUsername(), comm.getPassword());
        switch (comm.getActionData()){
            case "u":
                int res =   mysql.exeU(comm.getSql());
                comm.setValue("" + res);
                break;
            case "q":
                mysql.exeQ(comm.getSql());
                List<Map<String, Object>> data = mysql.getData();
                Type mtype = new TypeToken<List<Map<String, Object>>>() { }.getType();
                String json = gson.toJson(data, mtype);
                comm.setValue(json);
                break;
        }
        mysql.close();
        conned.send(gson.toJson(comm));
    }


}
