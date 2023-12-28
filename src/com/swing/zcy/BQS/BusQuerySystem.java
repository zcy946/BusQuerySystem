package com.swing.zcy.BQS;

import com.swing.zcy.BQS.UI.MainWindow.MainWindow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BusQuerySystem {
    public static String accountFilePath = "res/accounts.txt";
    public static String dataFilePath = "res/gongjiao.txt";
    public static String ACCOUNT;
    public static String PASSWORD;
    public static boolean isLogin;
    public static List<Object[]> data;
    public static int maxCapacity;
    private List<Bus> buses;
    public BusQuerySystem() {
        // 初始化数据[默认从文件读取]
        this.loadData();
        // 读取账号密码文档
        this.loadAccounts();
        // 初始化buses
        this.intiBuses();
        //加载界面
        MainWindow mainWindow = new MainWindow();

    }
    // 读取数据[默认从文件读取]
    private void loadData() {
        LoadData loadData = new LoadData();

        data = loadData.loadDataFromFile();
        this.maxCapacity = loadData.getMaxColumn();
        System.out.println("数据初始化完毕");
//        loadData.showData(); // 测试代码 显示数据
    }
    // 读取账号密码文档
    private void loadAccounts() {
        List<String> preAccounts;
        List<String> accounts = new ArrayList<>();
        try {
            preAccounts = Files.readAllLines(Paths.get(accountFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < preAccounts.size(); i++) {
            accounts.add(i, preAccounts.get(i).split(":")[1].trim()); // 截取账号密码
        }
//        System.out.println("账号: " + accounts.get(0) + "\n" + "密码: " + accounts.get(1));
        ACCOUNT = accounts.get(0);
        PASSWORD = accounts.get(1);
        // 初始化登陆状态
        isLogin = false;
        System.out.println("账号初始化完毕");
    }
    // 初始化每一个bus对象
    private void intiBuses() {
        this.buses = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            Bus bus = new Bus();
            Object[] object = data.get(i);
//            System.out.println("No." + (i + 1) + ": " + object[0]); // 测试代码
//            System.out.println("No." + (i + 1) + ": " + object[1]); // 测试代码
//            System.out.println("No." + (i + 1) + ": " + object[2]); // 测试代码
//            System.out.println("No." + (i + 1) + ": " + object[3]); // 测试代码
//            System.out.println("No." + (i + 1) + ": " + object[4]); // 测试代码
//            System.out.println("No." + (i + 1) + ": " + object[5]); // 测试代码
            // 💖 id 💖 票价 💖 时间1 💖 时间2 💖 有效卡 💖 站点s
            bus.setRouteID(String.valueOf(object[0])); // 💖 id
            bus.setPrice((Double) object[1]); // 💖 票价
            bus.setServiceTime1(String.valueOf(object[2]));// 💖 时间1
            bus.setServiceTime2(String.valueOf(object[3]));// 💖 时间2
            bus.setAvailableCards(String.valueOf(object[4]));// 💖 有效卡
            String[] stations = new String[maxCapacity - 5];
            for (int j = 5; j < maxCapacity; j++) {
                // 💖 站点s
                stations[j - 5] = String.valueOf(object[j]);
            }
            bus.setStations(stations);
            this.buses.add(bus);
        }
        System.out.println("buses初始化完毕");

    }
}
