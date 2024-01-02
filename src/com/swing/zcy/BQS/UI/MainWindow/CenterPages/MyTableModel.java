package com.swing.zcy.BQS.UI.MainWindow.CenterPages;

import com.swing.zcy.BQS.BusQuerySystem;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
public class MyTableModel extends AbstractTableModel {
    private List<Object[]> dataOfTable;
    private String[] columnNames;
    public MyTableModel() {
        // 加载数据
        this.loadData();
    }

    // 设置行数
    @Override
    public int getRowCount() {
        return this.dataOfTable.size();
    }

    // 设置行数
    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    // 设置获取表格中指定位置的值的方法
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.dataOfTable.get(rowIndex)[columnIndex];
    }

    // 设置列名
    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }

    // 获取列的数据类型
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Object value = getValueAt(0, columnIndex);
        return value != null ? value.getClass() : Object.class;
    }

    // 设置是否可编辑
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;  // 全都可以编辑
    }

    // 设置表格数据获取方式
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        this.dataOfTable.get(rowIndex)[columnIndex] = aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    // 废案-删除单行
    public void removeRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < this.dataOfTable.size()) { // 除了表头都能删除
            this.dataOfTable.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex); // 单独删除一行
        }
    }

    // 删除行
    public void removeRows(int[] rowIndices) {
        // 必须倒着删，防止索引变化
//        for (int index : rowIndices) {
//            this.dataOfTable.remove(index);
//        }
        for (int i = rowIndices.length - 1; i >= 0; i--) {
            int index = rowIndices[i];
            this.dataOfTable.remove(index);
        }
        fireTableRowsDeleted(rowIndices[0], rowIndices[rowIndices.length - 1]);
    }

    // 获取表中数据
    public List<Object[]> getAllData() {
        return new ArrayList<>(this.dataOfTable);
    }


    // 加载数据
    private void loadData() {
        this.dataOfTable = BusQuerySystem.data;
        this.columnNames = new String[BusQuerySystem.maxCapacity];
        this.columnNames[0] = "线路名";
        this.columnNames[1] = "票价";
        this.columnNames[2] = "运营时间1";
        this.columnNames[3] = "运营时间2";
        this.columnNames[4] = "有效卡类型";
        for (int i = 5; i < BusQuerySystem.maxCapacity; i++) {
            this.columnNames[i] = "站点" + (i - 4);
        }
    }
}
