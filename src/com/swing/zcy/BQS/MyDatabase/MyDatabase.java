package com.swing.zcy.BQS.MyDatabase;

import com.swing.zcy.BQS.BusQuerySystem;
import com.swing.zcy.BQS.Utils.MessageBox;

import java.sql.*;

public class MyDatabase {
    public static String url;
    public static String username;
    public static String password;
    public static void getTableState() {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            String showTablesSQL = "show tables;";
            ResultSet resultSet = statement.executeQuery(showTablesSQL);
            if (resultSet.next()) {
                String tableName = resultSet.getString(1); // 获取表名
                System.out.println("已拥有表: " + tableName);
                BusQuerySystem.haveTable = true;
            }
            else {
                BusQuerySystem.haveTable = false;
            }
        } catch (SQLException e) {
            MessageBox.showMessageDialog("数据库配置信息有误");
            e.printStackTrace();
        }
    }

    public static void createTable() {
        // 创建空表
        StringBuilder createTableSQL = new StringBuilder("CREATE TABLE Buses (" +
                "routeID VARCHAR(50) PRIMARY KEY, " +
                "price DECIMAL(3, 2) NOT NULL, " +
                "serviceTime1 VARCHAR(100) NOT NULL, " +
                "serviceTime2 VARCHAR(100), " +
                "availableCards VARCHAR(10), ");
        for (int i = 5; i < BusQuerySystem.maxCapacity; i++) {
            createTableSQL.append("station").append(i - 4).append(" VARCHAR(200), ");
        }
        createTableSQL.delete(createTableSQL.length() - 2, createTableSQL.length()); // 移除最后一个逗号和空格
        createTableSQL.append(");");

        System.out.println(createTableSQL); // 测试代码
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            // 执行创建表的SQL语句
            statement.executeUpdate(createTableSQL.toString());
            System.out.println("空表创建成功");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int count = 0;
        // 插入数据
        for (var bus : BusQuerySystem.buses) {
            StringBuilder insertValuesSQL = new StringBuilder();
            StringBuilder insertValuesSQLPara = new StringBuilder();
            insertValuesSQLPara.append("insert into buses(");
            StringBuilder insertValuesSQLValue = new StringBuilder();
            insertValuesSQLValue.append(") values(");
            insertValuesSQLPara.append( "routeID, ");
            insertValuesSQLValue.append("'" + bus.getRouteID() + "'").append(", "); // 线路名
            if (bus.getPrice() != null) {
                insertValuesSQLPara.append("price, "); // 价格
                insertValuesSQLValue.append(bus.getPrice()).append(", "); // 价格
            } else {
                insertValuesSQLPara.append("price, ");
                insertValuesSQLValue.append("0, ");
            }
            if (!bus.getServiceTime1().isEmpty()) {
                insertValuesSQLPara.append("serviceTime1, "); // 运营时间1
                insertValuesSQLValue.append("'" + bus.getServiceTime1() + "'").append(", "); // 运营时间1
            }
            if (!bus.getServiceTime2().isEmpty()) {
                insertValuesSQLPara.append("serviceTime2, "); // 运营时间2
                insertValuesSQLValue.append("'" + bus.getServiceTime2() + "'").append(", "); // 运营时间2
            }
            if (!bus.getAvailableCards().isEmpty()) {
                insertValuesSQLPara.append("availableCards, "); // 有效卡类型
                insertValuesSQLValue.append("'" + bus.getAvailableCards() + "'").append(", "); // 有效卡类型
            }
            for (int i = 0; i < bus.getStations().length; i++) {
                if (!bus.getStations()[i].isEmpty()) {
                    insertValuesSQLPara.append("station").append(i + 1).append(", "); // 站点
                    insertValuesSQLValue.append("'" + bus.getStations()[i] + "'").append(", "); // 站点
                }
            }
            insertValuesSQLPara.delete(insertValuesSQLPara.length() - 2, insertValuesSQLPara.length()); // 移除最后一个逗号和空格
            insertValuesSQLValue.delete(insertValuesSQLValue.length() - 2, insertValuesSQLValue.length()); // 移除最后一个逗号和空格
            insertValuesSQLValue.append(");");
            insertValuesSQL.append(insertValuesSQLPara).append(insertValuesSQLValue);
            count++;
//            System.out.println(insertValuesSQL); // 测试代码 展示插入语句
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(insertValuesSQL.toString());
                System.out.println("第" + count + "条数据插入成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(insertValuesSQL); // 测试代码
    }
}
