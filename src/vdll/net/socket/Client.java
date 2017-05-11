package vdll.net.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Hocean on 2017/5/11.
 */
public class Client {
    public Socket clientSocket;
    public Conned conned;

    public String ip;
    public int prot;

    public Client() {
        this("127.0.0.1", 61234);
    }

    public Client(String ip, int prot) {
        this.ip = ip;
        this.prot = prot;
    }

    public Conned Conn() {
        //设定服务器IP地址  
        try {
            clientSocket = new Socket(ip, prot);
            conned = new Conned(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conned;
    }

    public void Close() {
        try {
            if (conned != null) conned.close();
            if (clientSocket != null) clientSocket.close();
        } catch (Exception e) {

        }
    }
}
