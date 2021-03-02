package com.binginx.myboot.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketUtil {

    public static void sendMsg(String msg,String ip, int port) throws IOException {
        Socket socket = null;
        OutputStream outputStream;
        InputStream inputStream;
        ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(ip, port);
        try {
            socket = new Socket();
            socket.connect(inetSocketAddress, 5000);
            socket.setSoTimeout(20000);;
            outputStream = socket.getOutputStream();
            outputStream.write(msg.getBytes());
            outputStream.flush();
            inputStream = socket.getInputStream();
            int len;
            byte[] b=new byte[1024];
            while((len=inputStream.read(b))!=-1) {
                bufferStream.write(b, 0, len);
            }
            System.out.println("返回报文："+new String(bufferStream.toByteArray()));
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(socket!=null){
                socket.close();
            }
        }
    }
}
