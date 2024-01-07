package com.swing.zcy.BQS;

import com.swing.zcy.BQS.MyDatabase.MyDatabase;
import com.swing.zcy.BQS.UI.MainWindow.MainWindow;
import com.swing.zcy.BQS.Utils.MessageBox;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusQuerySystem {
    public static String settingConfigPath = "res/setting.txt";
    public static String accountFilePath = "res/accounts.txt";
    public static String dataFilePath = "res/gongjiao.txt";
    public static String userConfigFilePath = "res/dataBaseConfig.txt";
    public static String ACCOUNT;
    public static String PASSWORD;
    public static boolean isLogin;
    public static boolean isDataChanged;
    private DataProcessing dataProcessing;
    public static List<Object[]> data;
    public static int maxCapacity;
    public static List<Bus> buses;
    public static boolean haveTable;
    public static int dataSources;
    public BusQuerySystem() {
        // 读取数据库配置文档
        this.initDatabaseConfig();
        // 初始化设置配置
        this.initSettingConfig();
        // 初始化数据[默认从文件读取]
        this.loadData();
        // 读取账号密码文档
        this.loadAccounts();
        // 初始化buses
        intiBuses();
        //加载界面
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
    // 读取配置文件
    private void initSettingConfig() {
        List<String> preAccounts;
        try {
            preAccounts = Files.readAllLines(Paths.get(settingConfigPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataSources = Integer.parseInt(preAccounts.get(0).split(":")[1].trim());
    }
    // 读取数据[默认从文件读取]
    private void loadData() {
        this.dataProcessing = new DataProcessing();
        if (BusQuerySystem.dataSources == 2) {
            data = DataProcessing.loadDataFromDataBase();
        }
        else {
            data = DataProcessing.loadDataFromFile();
        }
        if (data.isEmpty()) {
            MessageBox.showMessageDialog("未检测到数据源");
        }
        else {
            maxCapacity = dataProcessing.getMaxColumn();
            System.out.println("数据初始化完毕");
//            dataProcessing.showData(); // 测试代码 显示数据
        }
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
    // 读取数据库配置文件
    private void initDatabaseConfig() {
        List<String> userConfig;
        List<String> dataBaseConfig = new ArrayList<>();
        try {
            userConfig = Files.readAllLines(Paths.get(userConfigFilePath));
        } catch (IOException e) {
            MessageBox.showMessageDialog("数据库配置文件有误");
            throw new RuntimeException(e);
        }
        for (int i = 0; i < userConfig.size(); i++) {
            dataBaseConfig.add(userConfig.get(i).split(":")[1].trim());
        }
        if(dataBaseConfig.isEmpty()) {
            MessageBox.showMessageDialog("未检测到数据库配置信息");
            System.exit(-1);
        }
        else {
            MyDatabase.url = "jdbc:mysql://" + dataBaseConfig.get(2) + ":" + dataBaseConfig.get(3) + "/"+ dataBaseConfig.get(4);
            MyDatabase.username = dataBaseConfig.get(0);
            MyDatabase.password = dataBaseConfig.get(1);
            System.out.println("数据库配置信息加载完毕");
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }}
    // 初始化每一个bus对象
    private static void intiBuses() {
        buses = new ArrayList<>();
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
//            bus.setPrice((Double) object[1]); // 💖 票价
            if (object[1] instanceof BigDecimal) {
                bus.setPrice(((BigDecimal) object[1]).doubleValue()); // 数据库重用的是decimal类型的数据
            }
            else {
                bus.setPrice((Double) object[1]);
            }
            bus.setServiceTime1(String.valueOf(object[2]));// 💖 时间1
            bus.setServiceTime2(String.valueOf(object[3]));// 💖 时间2
            bus.setAvailableCards(String.valueOf(object[4]));// 💖 有效卡
            String[] stations = new String[maxCapacity - 5];
            for (int j = 5; j < maxCapacity; j++) {
                // 💖 站点s
                stations[j - 5] = String.valueOf(object[j]);
            }
            bus.setStations(stations);
            buses.add(bus);
        }
        System.out.println("buses初始化完毕");

    }
    // 返回包含站点的所有路线
    public static List<Bus> getAllContainStationRoutes(String searchedStation) {
        List<Bus> result = new ArrayList<>();
        for (Bus bus : buses) {
            if (Arrays.stream(bus.getStations()).toList().contains(searchedStation)) {
                result.add(bus);
            }
        }
        return result;
    }

    // 重新从文件读取数据
    public static void reloadDataFromFile() {
        // 从文件中重新加载数据
        List<Object[]> data = DataProcessing.loadDataFromFile();
        if (data != null) {
            BusQuerySystem.data = data;
            intiBuses();
        } else {
            System.out.println("文件读取失败");
        }
    }
    // 重新从数据库读取数据
    public static void reloadDataFromDatabase() {
        // 从数据库中重新加载数据
        List<Object[]> data = DataProcessing.loadDataFromDataBase();
        if (data != null) {
            BusQuerySystem.data = data;
            intiBuses();
        } else {
            System.out.println("数据库读取失败");
        }
    }
}
