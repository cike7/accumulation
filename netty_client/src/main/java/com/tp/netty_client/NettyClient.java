package com.tp.netty_client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NettyClient extends Thread {

    private Socket socket;
    private Build build;

    private NettyClient(Build build){

//        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
//
//        try {
//            Bootstrap bootstrap = new Bootstrap();
//            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
//            .handler(new ClientInitializer(build.receiveData));
//
//            bootstrap.connect(build.host,build.port).sync().channel();
//
//        }catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        this.build = build;

    }

    @Override
    public void run() {
        super.run();
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(build.host,build.port),2000);
            new ClientThread(new DataInputStream(socket.getInputStream()),build.receiveData).start();
            build.receiveData.onReceive("连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientThread extends Thread {

        private DataInputStream inputStream;
        private ReceiveData<String> receiveData;

        public ClientThread(DataInputStream inputStream, ReceiveData<String> receiveData) {
            this.inputStream = inputStream;
            this.receiveData = receiveData;
        }

        @Override
        public void run() {

            try {
                byte[] bytes = new byte[1024];
                while (inputStream.read(bytes) != -1) {
                    String msg = new String(bytes);
                    receiveData.onReceive(msg.trim());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Build{

        private String host = "127.0.0.1";

        private int port = 20530;

        private ReceiveData receiveData;

        public Build(String host,int port){
            this.host = host;
            this.port = port;
        }

        public Build setOnReceive(ReceiveData receiveData){
            this.receiveData = receiveData;
            return this;
        }

        public NettyClient create(){
            return new NettyClient(this);
        }

    }


//    public static void main(String[] args) {
//        NettyClient client = new NettyClient.Build("192.168.1.192",20530).setOnReceive(new ReceiveData() {
//            @Override
//            public void onReceive(Object msg) {
//                System.out.println(">" + msg.toString());
//            }
//        }).create();
//    }

}