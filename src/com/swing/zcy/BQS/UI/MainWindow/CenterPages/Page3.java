package com.swing.zcy.BQS.UI.MainWindow.CenterPages;

import com.swing.zcy.BQS.BusQuerySystem;
import com.swing.zcy.BQS.UI.MainWindow.MyColor;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class Page3 extends JPanel{
    public JTable table;
    public JScrollPane scrollPane;
    public JButton updateBtn;
    public JButton deleteBtn;
    public JButton saveBtn;
    public Page3() {
//        this.setBackground(Color.decode("#8CF5FE")); // 测试代码
        this.setBackground(Color.decode(MyColor.panelCenterBgColor));
        this.setLayout(null);
        // 初始化按钮
        this.initButtons();
        // 初始化表格
        this.initTable();
    }
    private void initTable() {
        this.table = new JTable(new MyTableModel()); // 创建抽象数据模型表格
        // 根据表格内容动态设置列宽
        TableColumnModel columnModel = this.table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            int maxWidth = 0;
            // 把每一列的渲染器都设置为自定义的渲染器
            columnModel.getColumn(i).setCellRenderer(new MyTableCellRenderer());
            // 考虑列名的长度
            TableColumn column = columnModel.getColumn(i);
            String columnName = column.getHeaderValue().toString();
            maxWidth = Math.max(maxWidth, columnName.length() * 17);
            // 考虑每个单元格的内容长度
            for (int row = 0; row < this.table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = this.table.getCellRenderer(row, i);
                Object value = this.table.getValueAt(row, i);
                Component cellRendererComponent = cellRenderer.getTableCellRendererComponent(this.table, value, false, false, row, i);
                maxWidth = Math.max(maxWidth, cellRendererComponent.getPreferredSize().width);
            }
            // 表头设置表头的渲染器
//            this.table.getTableHeader().setDefaultRenderer(new MyHeaderRenderer());
            this.table.getTableHeader().setBackground(Color.decode(MyColor.fontColor2));
            this.table.getTableHeader().setForeground(Color.decode(MyColor.selectedFontColor));
            this.table.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 14));
            this.table.getTableHeader().setReorderingAllowed(false); // 禁止用户拖动列
            // 设置每列的宽度
            columnModel.getColumn(i).setPreferredWidth(maxWidth + this.table.getIntercellSpacing().width + 12);
        }

        // 不让表格自动设置宽度
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.setRowHeight(30);
        // 把table放入scrollPan方便查看
        this.scrollPane = new JScrollPane(this.table);
        this.add(this.scrollPane);
    }
    // 初始化按钮
    private void initButtons() {
        this.updateBtn = new JButton("更新");
        this.updateBtn.setBackground(Color.decode(MyColor.buttonColor));
        this.updateBtn.setForeground(Color.decode(MyColor.selectedFontColor));
        this.updateBtn.setFont(new Font("微软雅黑", Font.BOLD, 17));
        this.add(this.updateBtn);
        this.deleteBtn = new JButton("删除");
        this.deleteBtn.setBackground(Color.decode(MyColor.buttonColor));
        this.deleteBtn.setForeground(Color.decode(MyColor.selectedFontColor));
        this.deleteBtn.setFont(new Font("微软雅黑", Font.BOLD, 17));
        this.deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 废案-单独删除一行
//                int selectedRowIndex = table.getSelectedRow();
//                System.out.println(selectedRowIndex);
//                MyTableModel tempMyTableModel = (MyTableModel)table.getModel();
//                tempMyTableModel.removeRow(selectedRowIndex); // 先获取数据模型然后删除对应的行
//                System.out.println("已删除线路: " + tempMyTableModel.getValueAt(selectedRowIndex, 0));

                int[] selectedRowIndices = table.getSelectedRows();
//                for (var i : selectedRowIndices) {
//                    System.out.println(i); // 测试代码
//                }
                MyTableModel tempMyTableModel = (MyTableModel)table.getModel(); // 获取数据模
                System.out.print("已删除线路: [");
                for (var index : selectedRowIndices) {
                    System.out.print(" " + tempMyTableModel.getValueAt(index, 0));
                }
                System.out.println(" ]");
                tempMyTableModel.removeRows(selectedRowIndices); // 先获取数据模型然后删除对应的行
                // 🪢这里调用更新数据的方法
                BusQuerySystem.data = tempMyTableModel.getAllData(); // 更新BusQuerySystem.data中的数据
                // 🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩
                // 在BusQuerySystem再写一个Update方法，在此调用 同步更新文件
//                // 测试代码，查看表中数据
//                for (int i = 0; i < BusQuerySystem.data.size(); i++) {
//                    System.out.println(Arrays.toString(BusQuerySystem.data.get(i)));
//                }

            }
        });
        this.add(this.deleteBtn);
        this.saveBtn = new JButton("保存");
        this.saveBtn.setBackground(Color.decode(MyColor.buttonColor));
        this.saveBtn.setForeground(Color.decode(MyColor.selectedFontColor));
        this.saveBtn.setFont(new Font("微软雅黑", Font.BOLD, 17));
        this.add(this.saveBtn);

    }
}
