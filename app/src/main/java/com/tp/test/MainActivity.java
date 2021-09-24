package com.tp.test;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.tp.test.adapter.DesktopCardAdapter;
import com.tp.test.annotation.AutoFragment;
import com.tp.test.annotation.AutoProperties;
import com.tp.test.annotation.BindString;
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

    }

    ArrayList<FragmentDecorator> fragmentDecorators;


    @Override
    protected void onStart() {
        super.onStart();

        List<UsbDevice> usbDevices = getUsbDevices();

        for (int i = 0; i < usbDevices.size(); i++) {
            Log.e(TAG, ">" + usbDevices.get(i).getDeviceName());
            Log.e(TAG, ">" + usbDevices.get(i).getManufacturerName());
            Log.e(TAG, ">" + usbDevices.get(i).getProductName());
            Log.e(TAG, ">" + usbDevices.get(i).getSerialNumber());
            Log.e(TAG, ">" + usbDevices.get(i).getVersion());
            Log.e(TAG, ">" + usbDevices.get(i).getConfigurationCount());
            Log.e(TAG, ">" + usbDevices.get(i).getDeviceClass());
            Log.e(TAG, ">" + usbDevices.get(i).getDeviceProtocol());
            Log.e(TAG, ">" + usbDevices.get(i).getDeviceProtocol());
        }

//        FragmentDecorator component = new ComponentFour(new ComponentThree(new ComponentTwo(new ComponentOne(null))));

//        if(fragmentDecorators != null && fragmentDecorators.size() > 0){
//            for (FragmentDecorator fragmentDecorator : fragmentDecorators) {
//
//            }
//        }

//        BaseFragment fragment = new BaseFragment.Builder(component).create(R.layout.fragment_one);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, fragment).commit();

    }


    private UsbManager usbManager;

    /**
     * 查找本机所有的USB设备
     */
    public List<UsbDevice> getUsbDevices() {
        //1)创建usbManager
        if (usbManager == null)
            usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);

        //2)获取到所有设备 选择出满足的设备
        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        //创建返回数据
        List<UsbDevice> lists = new ArrayList<>();
        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            Log.e(TAG, "vendorID--" + device.getVendorId() + "ProductId--" + device.getProductId());
            lists.add(device);
        }
        return lists;
    }


    private class CommunicationThread extends Thread{

        @Override
        public void run() {
            super.run();

            try {

                Socket socket = new Socket();

                socket.connect(new InetSocketAddress("192.168.1.239",7777),2000);

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