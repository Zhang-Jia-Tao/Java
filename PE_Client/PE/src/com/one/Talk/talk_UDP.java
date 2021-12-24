package com.one.Talk;



//网络聊天  利用UDP 不需要连接服务器

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class talk_UDP {


    public talk_UDP(String content,String IP,int port){
        //1.创建一个Socket
        try {
            DatagramSocket socket = new DatagramSocket();

            //2.创建一个包
            InetAddress inetAddress = InetAddress.getByAddress(IP.getBytes(StandardCharsets.UTF_8));

            DatagramPacket packet = new DatagramPacket(content.getBytes(StandardCharsets.UTF_8),0,content.getBytes().length,
                    inetAddress,port);
            //3.发送这个包
            socket.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {

    }
}
