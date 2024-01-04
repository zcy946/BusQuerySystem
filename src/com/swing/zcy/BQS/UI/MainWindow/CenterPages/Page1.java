package com.swing.zcy.BQS.UI.MainWindow.CenterPages;

import com.swing.zcy.BQS.BusQuerySystem;
import com.swing.zcy.BQS.UI.MainWindow.PanelLeft;
import com.swing.zcy.BQS.Utils.MessageBox;
import com.swing.zcy.BQS.Utils.MyColor;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.*;
import java.util.List;

public class Page1 extends JPanel {
    public JTable table;
    private MyTableModel myTableModel;
    public JScrollPane scrollPane;
    public JPanel searchBar;
    private JLabel searchIcon;
    public JTextField searchField;
    public JButton searchBtn;
    public JButton showAllRoutesInfo;

    public Page1() {
        this.setLayout(null);
//        this.setBackground(Color.decode("#84A9FF")); // 测试代码
        this.setBackground(Color.decode(MyColor.panelCenterBgColor));
        // 初始化表格
        this.initTable();
        // 初始化控件
        this.initWidgets();
    }

    private void initTable() {
        String[] tempColumnNames = {"                                  提示                                  "};
        List<Object[]> tempDataOfTable = new ArrayList<>();
        tempDataOfTable.add(new Object[]{"输入 [站名] 后 按下 `回车` 或 点击 `搜索` 进行检索"});
        this.myTableModel = new MyTableModel(tempColumnNames, tempDataOfTable, tempColumnNames.length, false);
        this.table = new JTable();
        table.setModel(myTableModel);
        // 不让表格自动设置宽度
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.setRowHeight(30);
        // 设置列宽
        this.setTableColumnWidth();
        // 把table放入scrollPan方便查看
        this.scrollPane = new JScrollPane(this.table);
        this.add(this.scrollPane);
    }

    // 初始化按钮
    private void initWidgets() {
        this.searchBar = new JPanel();
        this.searchBar.setLayout(null);
        this.searchBar.setBackground(Color.decode(MyColor.selectedFontColor));
        this.searchBar.setBorder(BorderFactory.createLineBorder(Color.decode(MyColor.fontColor2)));
        this.add(this.searchBar);

        this.searchIcon = new JLabel("\ue611");
        this.searchIcon.setFont(PanelLeft.iconFont.deriveFont(30f));
        this.searchIcon.setForeground(Color.decode(MyColor.fontColor1));
        this.searchIcon.setBounds(5, 5, 30, 30);
        this.searchIcon.setHorizontalAlignment(SwingConstants.CENTER);
        this.searchBar.add(this.searchIcon);

        this.searchField = new JTextField();
        this.searchField.setBackground(Color.decode(MyColor.selectedFontColor));
        this.searchField.setBounds(5 + 30, 5, 400 - 30 - 10, 30);
        this.searchField.setBorder(BorderFactory.createEmptyBorder());
        this.searchField.setText("Enter the station name");
        this.searchField.setForeground(Color.decode(MyColor.fontAnnotationColor2));
        this.searchField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        // 提示词
        this.searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
//                System.out.println("in");
                if (searchField.getText().trim().equals("Enter the station name")) {
                    searchField.setText("");
                    searchField.setForeground(Color.decode(MyColor.fontColor1));
                    searchField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                }
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
//                System.out.println("out");
                if (searchField.getText().trim().equals("Enter the station name") || searchField.getText().trim().isEmpty()) {
                    searchField.setText("Enter the station name");
                    searchField.setForeground(Color.decode(MyColor.fontAnnotationColor2));
                    searchField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                }
                super.focusLost(e);
            }
        });
        // 按下`Enter`搜索
        this.searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("按下回车");
                search2();
            }
        });
        this.searchBar.add(this.searchField);
        // 按钮-查看线路信息
        this.showAllRoutesInfo = new JButton("查看线路信息");
        this.showAllRoutesInfo.setBackground(Color.decode(MyColor.buttonColor));
        this.showAllRoutesInfo.setForeground(Color.decode(MyColor.selectedFontColor));
        this.showAllRoutesInfo.setFont(new Font("微软雅黑", Font.BOLD, 17));
        // 监听器已迁移到MainWindow
//        this.showAllRoutesInfo.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                System.out.println("点击");
//                // 清表
//                myTableModel.clearTableData();
//                // 获取结果
//                boolean isContains = false;
//                int count = 0;
//                for (var bus : BusQuerySystem.buses) {
//                    Object[] dataOfTable = bus.getAllInformation();
//                    myTableModel.addRow(dataOfTable);
//                    count++;
//                }
//                // 设置单元格宽度
//                setTableColumnWidth();
//                System.out.println("已显示所有线路信息，共" + count + "条");
//
//            }
//        });
        this.add(showAllRoutesInfo);
        // 按钮-查找
        this.searchBtn = new JButton("搜索");
        this.searchBtn.setBackground(Color.decode(MyColor.buttonColor));
        this.searchBtn.setForeground(Color.decode(MyColor.selectedFontColor));
        this.searchBtn.setFont(new Font("微软雅黑", Font.BOLD, 17));
        this.searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("点击");
                search2();
            }
        });
        this.add(this.searchBtn);
    }

    private void setTableColumnWidth() {
        // 根据表格内容动态设置列宽
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            int maxWidth = 0;
            // 把每一列的渲染器都设置为自定义的渲染器
            columnModel.getColumn(i).setCellRenderer(new MyTableCellRenderer());
            // 考虑列名的长度
            TableColumn column = columnModel.getColumn(i);
            String columnName = column.getHeaderValue().toString();
            maxWidth = Math.max(maxWidth, columnName.length() * 17);
            // 考虑每个单元格的内容长度
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, i);
                Object value = table.getValueAt(row, i);
                Component cellRendererComponent = cellRenderer.getTableCellRendererComponent(table, value, false, false, row, i);
                maxWidth = Math.max(maxWidth, cellRendererComponent.getPreferredSize().width);
            }
            // 表头设置表头的渲染器
            table.getTableHeader().setBackground(Color.decode(MyColor.fontColor2));
            table.getTableHeader().setForeground(Color.decode(MyColor.selectedFontColor));
            table.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 14));
            table.getTableHeader().setReorderingAllowed(false); // 禁止用户拖动列
            // 设置每列的宽度
            columnModel.getColumn(i).setPreferredWidth(maxWidth + table.getIntercellSpacing().width + 12);
        }
    }

    // 搜索功能
    // 1.0 废案不适用
//    private void search() {
//        String searchedStationName = searchField.getText().trim();
//        if (searchedStationName.isEmpty() || searchedStationName.equals("Enter the station name")) {
//            MessageBox.showMessageDialog("请输入要检索的内容", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            System.out.println("检索站点: " + searchedStationName);
//            // 清表
//            myTableModel.clearTableData();
//            // 重设列名
//            String[] columnNames = new String[BusQuerySystem.maxCapacity];
//            columnNames[0] = "包含此站点的线路名";
//            for (int i = 1; i < columnNames.length; i++) {
//                columnNames[i] = "站点" + i;
//            }
//            myTableModel.setColumnNames(columnNames);
//            // 获取结果
//            boolean isContains = false;
//            int count = 0;
//            for (var bus : BusQuerySystem.buses) {
//                String[] stations = bus.getStations();
//                List<String> fitStations = new ArrayList<>();
//                for (int i = 0; i < stations.length; i++) {
//                    if (stations[i].contains(searchedStationName)) {
//                        isContains = true;
//                        if (!fitStations.contains(bus.getRouteID())) {
//                            fitStations.add(bus.getRouteID());
//                        }
//                        fitStations.add(stations[i]);
//                        count++;
//                    }
//                }
//                if (!fitStations.isEmpty()) {
//                    myTableModel.addRow(fitStations.toArray(new Object[0]));
//                }
//            }
//            System.out.println("查询到" + count + "条信息");
//            if (!isContains) {
//                MessageBox.showMessageDialog("未查询到含有站点为 `" + searchedStationName + "` 的相关线路信息");
//            }
//            // 设置单元格宽度
//            setTableColumnWidth();
//        }
//    }
    // 2.0 废案 重复显示
//    private void search1() {
//        String searchedStationName = searchField.getText().trim();
//        if (searchedStationName.isEmpty() || searchedStationName.equals("Enter the station name")) {
//            MessageBox.showMessageDialog("请输入要检索的内容", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            System.out.println("检索站点: " + searchedStationName);
//            // 清表
//            myTableModel.clearTableData();
//            // 重设列名
//            String[] columnNames = new String[BusQuerySystem.maxCapacity];
//            columnNames[0] = "站点名";
//            for (int i = 1; i < columnNames.length; i++) {
//                columnNames[i] = "线路" + i;
//            }
//            myTableModel.setColumnNames(columnNames);
//            // 获取结果
//            boolean isContains = false;
//            int count = 0;
//            for (var bus : BusQuerySystem.buses) {
//                String[] stations = bus.getStations();
//                List<String> fitStations = new ArrayList<>();
//                for (int i = 0; i < stations.length; i++) {
//                    if (stations[i].contains(searchedStationName)) {
//                        isContains = true;
//                        fitStations.add(stations[i]);
//                        if (!fitStations.contains(bus.getRouteID())) {
//                            fitStations.add(bus.getRouteID());
//                        }
//                        count++;
//                        myTableModel.addRow(fitStations.toArray(new Object[0]));
//                    }
//                }
//            }
//            System.out.println("查询到" + count + "条信息");
//            if (!isContains) {
//                MessageBox.showMessageDialog("未查询到含有站点为 `" + searchedStationName + "` 的相关线路信息");
//            }
//            // 设置单元格宽度
//            setTableColumnWidth();
//        }
//    }
    private void search2() {
        String searchedStationName = searchField.getText().trim();
        if (searchedStationName.isEmpty() || searchedStationName.equals("Enter the station name")) {
            MessageBox.showMessageDialog("请输入要检索的内容", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (!BusQuerySystem.buses.isEmpty()) {
                System.out.println("检索站点: " + searchedStationName);
                // 清表
                myTableModel.clearTableData();
                // 重设列名
                String[] columnNames = new String[BusQuerySystem.buses.size() / 2]; // 最大容量直接设置为路线总数(每个路线都有来回两个)
                columnNames[0] = "站点名";
                for (int i = 1; i < columnNames.length; i++) {
                    columnNames[i] = "线路" + i;
                }
                myTableModel.setColumnNames(columnNames);
                // 获取结果
                boolean isContains = false;
                int stationCount = 0;
                int routeCount = 0;
                Map<String, List<String>> stationRoutesMap = new HashMap<>(); // 用map更简单过滤重复
                for (var bus : BusQuerySystem.buses) {
                    String[] stations = bus.getStations();
                    for (int i = 0; i < stations.length; i++) {
                        if (stations[i].contains(searchedStationName)) {
                            isContains = true;
                            String stationName = stations[i];
                            String routeName = bus.getRouteID();
                            // 查看是否已存该键值对，不存在就创建，存在直接添加
                            if (!stationRoutesMap.containsKey(stationName)) {
                                stationRoutesMap.put(stationName, new ArrayList<>());
                                stationRoutesMap.get(stationName).add(stationName); // 把值的第一个位置放置站点名，用于显示数据
                                stationCount++;
                            }
                            stationRoutesMap.get(stationName).add(routeName); // 将路线名添加到站点对应的列表中
                            routeCount++;
                        }
                    }
                }
                // 获取stationRoutesMap键值对的值部分
                for (List<String> routeList : stationRoutesMap.values()) {
                    myTableModel.addRow(routeList.toArray(new Object[0])); // 写入表格
                }
                myTableModel.setColumnNames(columnNames);

                System.out.println("查询到" + stationCount + "条站点信息，" + routeCount + "条线路信息");
                if (!isContains) {
                    MessageBox.showMessageDialog("未查询到含有站点为 `" + searchedStationName + "` 的相关线路信息");
                }
                // 设置单元格宽度
                setTableColumnWidth();
            }
            else {
                MessageBox.showMessageDialog("请检查数据源是否配置成功");
            }
        }
    }


}
