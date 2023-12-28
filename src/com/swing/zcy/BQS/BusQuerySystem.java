package com.swing.zcy.BQS;

import com.swing.zcy.BQS.UI.MainWindow.MainWindow;

import java.io.File;
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
    private List<Bus> Buses;
    public BusQuerySystem() {
        // 初始化数据[默认从文件读取]
        LoadData loadData = new LoadData();
        data = loadData.loadDataFromFile();
        loadData.showData(); // 测试代码显示数据

        // 读取账号密码文档
        this.loadAccounts();
        // 初始化登陆状态
        isLogin = false;
        //加载界面
        MainWindow mainWindow = new MainWindow();

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
    }
    private void intiBuses() {

    }
}
