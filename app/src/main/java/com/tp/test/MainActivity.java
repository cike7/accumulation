package com.tp.test;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.tp.netty_client.NettyClient;
import com.tp.netty_client.ReceiveData;
import com.tp.test.adapter.DesktopCardAdapter;
import com.tp.test.annotation.AutoFragment;
import com.tp.test.annotation.AutoProperties;
import com.tp.test.annotation.BindString;
import com.tp.test.control.ClientService;
import com.tp.test.customize.DesktopCardView;
import com.tp.test.fragment.BaseFragment;
import com.tp.test.fragment.ComponentFour;
import com.tp.test.fragment.ComponentOne;
import com.tp.test.fragment.ComponentThree;
import com.tp.test.fragment.ComponentTwo;
import com.tp.test.fragment.FragmentDecorator;
import com.tp.test.model.DesktopCardModel;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @BindString("companyName")
    private String str;

    private GridView gridView;

    private DrawerLayout drawerLayout;

    NestedScrollView nsv_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoProperties.bind(this,"config.properties");

//        AutoFragment.bind(this);

//        nsv_root = findViewById(R.id.nsv_root);
//
//        nsv_root.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                Log.e("Parameter", "view name:" + v.toString() + "  top >" + top + "    oldTop >" + oldTop);
//            }
//        });

//        gridView = findViewById(R.id.gridView);
//
//        ArrayList<DesktopCardModel> cardModels = new ArrayList<>();
//
//        for (int i = 0; i < 30; i++) {
//            DesktopCardModel item = new DesktopCardModel();
//            item.setCheck(i);
//            item.setComment(i);
//            item.setName("作者" + i);
//            item.setId(i);
//            cardModels.add(item);
//        }
//
//        DesktopCardAdapter adapter = new DesktopCardAdapter(this, cardModels);
//
//        gridView.setAdapter(adapter);
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                DesktopCardView item = (DesktopCardView) parent.getChildAt(position);
//
//                Log.e("home", ">" + item.getModel().getName());
//            }
//        });
//
//
//        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                DesktopCardView item = (DesktopCardView) parent.getChildAt(position);
//                Log.e("home", "item" + item.getModel().getName() + "position:" + position + "id:" + id);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Log.e("home", "position" + parent.toString());
//            }
//        });

//        new CommunicationThread().start();

        Intent intent = new Intent(MainActivity.this,ClientService.class);
//        startService(intent);
        bindService(intent,conn,Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

    //服务绑定的连接对象
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ClientService.ClientReceive receive = (ClientService.ClientReceive)service;
            ClientService clientService = receive.getServer();
            clientService.setReceiveData(new ReceiveData() {
                @Override
                public void onReceive(Object msg) {
                    Message message = new Message();
                    message.obj = msg;
                    handler.sendMessage(message);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //创建一个通知管理器
            NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            Notification notification = new Notification.Builder(MainActivity.this)
                    .setContentTitle("标题")
                    .setContentText(">>>>" + msg.obj.toString())
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(MainActivity.this.getResources(),R.mipmap.ic_launcher))
                    .build();

            notificationManager.notify(0,notification);

        }
    };


    private class CommunicationThread extends Thread{

        @Override
        public void run() {
            super.run();

            try {

                Socket socket = new Socket();

                socket.connect(new InetSocketAddress("192.168.1.192",20530),2000);

                new ClientThread(socket).start();

                new SendThread(socket).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static final String TAG = "ClientThread";

    private class ClientThread extends Thread {

        private DataInputStream dis;
        private Socket socket;

        public ClientThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            try {
                dis = new DataInputStream(socket.getInputStream());
                String data;
                while ((data = dis.readUTF()) != null) {
                    data = data.trim();
                    Log.e(TAG, "收到数据:" + data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                    dis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private class SendThread extends Thread{

        private DataOutputStream outStream;
        private Socket socket;

        public SendThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            try {
                outStream= new DataOutputStream(socket.getOutputStream());

                for (int i = 0; i < 5; i++) {
                    String msg = "手机时间：" + System.currentTimeMillis();
                    outStream.writeUTF(msg);
                    outStream.flush();
                    Thread.sleep(1000);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    outStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}