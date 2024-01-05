package com.swing.zcy.BQS.MyDatabase;

import com.swing.zcy.BQS.BusQuerySystem;
import com.swing.zcy.BQS.Utils.MessageBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.swing.zcy.BQS.BusQuerySystem.userConfigFilePath;

public class MyDatabase {
    public static String url;
    public static String username;
    public static String password;
    public static void getTableState() {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            String showTablesSQL = "show tables;";
            ResultSet resultSet = statement.executeQuery(showTablesSQL);
            while (resultSet.next()) {
                String tableName = resultSet.getString(1); // 获取表名
                System.out.println("已拥有表: " + tableName);
                BusQuerySystem.haveTable = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cteateTable() {
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

        // 插入数据
        StringBuilder insertValuesSQL = new StringBuilder();
        insertValuesSQL.append("CREATE TABLE Buses (" +
                "routeID VARCHAR(50) PRIMARY KEY, " +
                "price DECIMAL(3, 2) NOT NULL, " +
                "serviceTime1 VARCHAR(100) NOT NULL, " +
                "serviceTime2 VARCHAR(100), " +
                "availableCards VARCHAR(10), ");
        for (int i = 5; i < BusQuerySystem.maxCapacity; i++) {
            insertValuesSQL.append("station").append(i - 4).append(" VARCHAR(200), ");
        }
        insertValuesSQL.delete(insertValuesSQL.length() - 2, insertValuesSQL.length()); // 移除最后一个逗号和空格
        insertValuesSQL.append(");");

        System.out.println(insertValuesSQL); // 测试代码
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            // 执行创建表的SQL语句
            statement.executeUpdate(insertValuesSQL.toString());
            System.out.println("数据插入成功");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
