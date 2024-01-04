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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page2 extends JPanel {
    public JTable table;
    private MyTableModel myTableModel;
    public JScrollPane scrollPane;
    public JPanel searchBar;
    private JLabel searchIcon;
    public JTextField searchField;
    public JPanel searchBar2;
    private JLabel searchIcon2;
    public JTextField searchField2;
    public JButton searchBtn;
    public JLabel exchangeLabel;
    public JButton showAllRoutesInfo;
    public Page2() {
//        this.setBackground(Color.decode("#C1F486")); // 测试代码
           this.setBackground(Color.decode(MyColor.panelCenterBgColor));
           this.setLayout(null);
        // 初始化表格
        this.initTable();
        // 初始化控件
        this.initWidgets();
    }
    private void initTable() {
        String[] tempColumnNames = {"                                  提示                                  "};
        List<Object[]> tempDataOfTable = new ArrayList<>();
        tempDataOfTable.add(new Object[]{"输入 [起点] 和 [终点] 后 按下 `回车` 或 点击`搜索` 进行搜索"});
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

        this.searchIcon = new JLabel("\ue7a5");
        this.searchIcon.setFont(PanelLeft.iconFont.deriveFont(30f));
        this.searchIcon.setForeground(Color.decode(MyColor.pointStart));
        this.searchIcon.setBounds(5, 5, 30, 30);
        this.searchIcon.setHorizontalAlignment(SwingConstants.CENTER);
        this.searchBar.add(this.searchIcon);

        this.searchField = new JTextField();
        this.searchField.setBackground(Color.decode(MyColor.selectedFontColor));
        this.searchField.setBounds(5 + 30, 5, 300 - 30 - 10, 30);
        this.searchField.setBorder(BorderFactory.createEmptyBorder());
        this.searchField.setText("输入起点");
        this.searchField.setForeground(Color.decode(MyColor.fontAnnotationColor2));
        this.searchField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        // 提示词
        this.searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
//                System.out.println("in");
                if (searchField.getText().equals("输入起点")) {
                    searchField.setText("");
                    searchField.setForeground(Color.decode(MyColor.fontColor1));
                    searchField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                }
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
//                System.out.println("out");
                if (searchField.getText().equals("输入起点") || searchField.getText().isEmpty()) {
                    searchField.setText("输入起点");
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
                if (searchField.getText().equals("输入起点") || searchField.getText().isEmpty()) {
                    MessageBox.showMessageDialog("请输入起点", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    if (searchField2.getText().equals("输入终点") || searchField2.getText().isEmpty()) {
                        MessageBox.showMessageDialog("请输入终点", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        search();
                    }
                }
            }
        });
        this.searchBar.add(this.searchField);

        // 交换按钮
        this.exchangeLabel = new JLabel("\uec8d");
        this.exchangeLabel.setFont(PanelLeft.iconFont.deriveFont(25f));
        this.exchangeLabel.setForeground(Color.decode(MyColor.fontColor1));
        this.exchangeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(exchangeLabel);

        this.searchBar2 = new JPanel();
        this.searchBar2.setLayout(null);
        this.searchBar2.setBackground(Color.decode(MyColor.selectedFontColor));
        this.searchBar2.setBorder(BorderFactory.createLineBorder(Color.decode(MyColor.fontColor2)));
        this.add(this.searchBar2);

        this.searchIcon2 = new JLabel("\ue7a5");
        this.searchIcon2.setFont(PanelLeft.iconFont.deriveFont(30f));
        this.searchIcon2.setForeground(Color.decode(MyColor.pointEnd));
        this.searchIcon2.setBounds(5, 5, 30, 30);
        this.searchIcon2.setHorizontalAlignment(SwingConstants.CENTER);
        this.searchBar2.add(this.searchIcon2);

        this.searchField2 = new JTextField();
        this.searchField2.setBackground(Color.decode(MyColor.selectedFontColor));
        this.searchField2.setBounds(5 + 30, 5, 300 - 30 - 10, 30);
        this.searchField2.setBorder(BorderFactory.createEmptyBorder());
        this.searchField2.setText("输入终点");
        this.searchField2.setForeground(Color.decode(MyColor.fontAnnotationColor2));
        this.searchField2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        // 提示词
        this.searchField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
//                System.out.println("in");
                if (searchField2.getText().equals("输入终点")) {
                    searchField2.setText("");
                    searchField2.setForeground(Color.decode(MyColor.fontColor1));
                    searchField2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                }
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
//                System.out.println("out");
                if (searchField2.getText().equals("输入终点") || searchField2.getText().isEmpty()) {
                    searchField2.setText("输入终点");
                    searchField2.setForeground(Color.decode(MyColor.fontAnnotationColor2));
                    searchField2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                }
                super.focusLost(e);
            }
        });
        // 按下`Enter`搜索
        this.searchField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchField.getText().equals("输入起点") || searchField.getText().isEmpty()) {
                    MessageBox.showMessageDialog("请输入起点", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    if (searchField2.getText().equals("输入终点") || searchField2.getText().isEmpty()) {
                        MessageBox.showMessageDialog("请输入终点", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        search();
                    }
                }
            }
        });
        this.searchBar2.add(this.searchField2);

        // 按钮-查找
        this.searchBtn = new JButton("搜索");
        this.searchBtn.setBackground(Color.decode(MyColor.buttonColor));
        this.searchBtn.setForeground(Color.decode(MyColor.selectedFontColor));
        this.searchBtn.setFont(new Font("微软雅黑", Font.BOLD, 17));
        this.searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("点击");
                search();
            }
        });
        this.add(this.searchBtn);
        // 按钮-查看线路信息
        this.showAllRoutesInfo = new JButton("查看线路信息");
        this.showAllRoutesInfo.setBackground(Color.decode(MyColor.buttonColor));
        this.showAllRoutesInfo.setForeground(Color.decode(MyColor.selectedFontColor));
        this.showAllRoutesInfo.setFont(new Font("微软雅黑", Font.BOLD, 17));
        this.add(showAllRoutesInfo);
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
    private void search() {
        String searchedStartStationName = searchField.getText();
        String searchedEndStationName = searchField2.getText();
        System.out.println("检索起点: " + searchedStartStationName + " 终点: " + searchedEndStationName);
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
        Map<String, java.util.List<String>> stationRoutesMap = new HashMap<>(); // 用map更简单过滤重复
        for (var bus : BusQuerySystem.buses) {
            String[] stations = bus.getStations();
            for (int i = 0; i < stations.length; i++) {
                if (stations[i].contains(searchedStartStationName)) {
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
            MessageBox.showMessageDialog("未查询到起点为 `" + searchedStartStationName + "` 终点为 `" + searchedEndStationName + "` 的相关线路信息");
        }
        // 设置单元格宽度
        setTableColumnWidth();

    }
}
