package com.sdkj.boot.net;

import com.sdkj.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

//弃用

/*
@Component
public class NetThread_Receive implements Runnable {

    @Autowired
    UserService userService;

    private DatagramSocket socket = null;
    @Value("9999")
    private int port;


    public NetThread_Receive() {
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
                socket.receive(packet);

                String UsernameAndPassword = new String(packet.getData(), 0, packet.getLength());
                String[] strings = UsernameAndPassword.split(":");  //strings[0] 为用户名 strings[1] 为密码

                int bool = userService.UserLogin(strings[0], strings[1]);
                if (bool == 1) {
                    new Thread(new NetThread_Send(5555, "localhost", 8888, 1)).start();
                } else {
                    new Thread(new NetThread_Send(5555, "localhost", 8888, 0)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

 */

