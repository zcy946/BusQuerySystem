package com.swing.zcy.BQS.UI.MainWindow.CenterPages;

import com.swing.zcy.BQS.BusQuerySystem;
import com.swing.zcy.BQS.Utils.MessageBox;
import com.swing.zcy.BQS.Utils.MyColor;
import com.swing.zcy.BQS.UI.MainWindow.PanelLeft;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;

public class Page0 extends JPanel {
    public JTable table;
    private MyTableModel myTableModel;
    public JScrollPane scrollPane;
    public JPanel searchBar;
    private JLabel searchIcon;
    public JTextField searchField;
    public JButton searchBtn;
    public JButton showAllRoutes;

    public Page0() {
        this.setLayout(null);
//        this.page0.setBackground(Color.decode("#FFEFE6")); // 测试代码
        this.setBackground(Color.decode(MyColor.panelCenterBgColor));
        // 初始化表格
        this.initTable();
        // 初始化控件
        this.initWidgets();
    }

    private void initTable() {
        this.myTableModel = new MyTableModel(false);

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
        this.searchBar.add(this.searchIcon);

        this.searchField = new JTextField();
        this.searchField.setBackground(Color.decode(MyColor.selectedFontColor));
        this.searchField.setBounds(5 + 30, 5, 400 - 30 - 10, 30);
        this.searchField.setBorder(BorderFactory.createEmptyBorder());
        this.searchField.setText("Enter the route name");
        this.searchField.setForeground(Color.decode(MyColor.fontAnnotationColor2));
        this.searchField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        // 提示词
        this.searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
//                System.out.println("in");
                if (searchField.getText().equals("Enter the route name")) {
                    searchField.setText("");
                    searchField.setForeground(Color.decode(MyColor.fontColor1));
                    searchField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                }
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
//                System.out.println("out");
                if (searchField.getText().equals("Enter the route name") || searchField.getText().isEmpty()) {
                    searchField.setText("Enter the route name");
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
                search();
            }
        });
        this.searchBar.add(this.searchField);
        // 按钮-显示所有线路信息
        this.showAllRoutes = new JButton("显示所有线路信息");
        this.showAllRoutes.setBackground(Color.decode(MyColor.buttonColor));
        this.showAllRoutes.setForeground(Color.decode(MyColor.selectedFontColor));
        this.showAllRoutes.setFont(new Font("微软雅黑", Font.BOLD, 17));
        this.showAllRoutes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("点击");
                // 清表
                myTableModel.clearTableData();
                // 获取结果
                boolean isContains = false;
                int count = 0;
                for (var bus : BusQuerySystem.buses) {
                    Object[] dataOfTable = bus.getAllInformation();
                    myTableModel.addRow(dataOfTable);
                    count++;
                }
                // 设置单元格宽度
                setTableColumnWidth();
                System.out.println("已显示所有线路信息，共" + count + "条");
            }
        });
        this.add(showAllRoutes);
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
    private void search() {
        String searchedRouteId = searchField.getText();
        if (searchedRouteId.isEmpty()) {
            MessageBox.showMessageDialog("请输入要检索的内容", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("检索线路: " + searchedRouteId);
            // 清表
            myTableModel.clearTableData();
            // 获取结果
            boolean isContains = false;
            int count = 0;
            for (var bus : BusQuerySystem.buses) {
                if (bus.getRouteID().contains(searchedRouteId)) {
                    isContains = true;
                    Object[] dataOfTable = bus.getAllInformation();
                    myTableModel.addRow(dataOfTable);
                    count++;
//                            System.out.println("结果: " + Arrays.toString(dataOfTable));
                }
            }
            System.out.println("查询到" + count + "条信息");
            if (!isContains) {
                MessageBox.showMessageDialog("未查询到线路名为 `" + searchedRouteId + "` 的相关信息");
            }
            // 设置单元格宽度
            setTableColumnWidth();
        }
    }
}
