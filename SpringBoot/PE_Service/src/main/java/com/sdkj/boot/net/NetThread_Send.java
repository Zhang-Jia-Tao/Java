package com.sdkj.boot.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

//弃用
/*

public class NetThread_Send implements Runnable{

    DatagramSocket socket = null;


    private int fromPort;
    private String toIp;
    private int toPort;
    private int bool;

    public NetThread_Send(int fromPort, String toIp, int toPort,int bool){
        this.fromPort = fromPort;
        this.toIp = toIp;
        this.toPort = toPort;
        this.bool = bool;

        try{
            socket = new DatagramSocket(fromPort);
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while(true){
            try{
                String msg;
                if(bool == 1) {
                    msg = "true";
                } else {
                    msg = "false";
                }
                byte[] datas = msg.getBytes(StandardCharsets.UTF_8);
                DatagramPacket packet = new DatagramPacket(datas,0,datas.length,new InetSocketAddress(this.toIp,this.toPort));
                socket.send(packet);

            } catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}

 */

