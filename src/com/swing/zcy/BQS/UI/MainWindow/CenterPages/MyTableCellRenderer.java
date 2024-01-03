package com.swing.zcy.BQS.UI.MainWindow.CenterPages;

import com.swing.zcy.BQS.Utils.MyColor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MyTableCellRenderer extends DefaultTableCellRenderer {
    // 重写tableCell渲染器
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // 获取表格中的渲染控件

        if (isSelected) {
            component.setBackground(Color.decode(MyColor.selectedColor));
            component.setForeground(Color.decode(MyColor.selectedFontColor));
            component.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        }
        else {
            if (row % 2 != 0) {
                component.setBackground(Color.decode(MyColor.tableCellBgColor));
            }
            else {
                component.setBackground(Color.decode(MyColor.selectedFontColor));
            }
            component.setForeground(Color.decode(MyColor.fontColor1));
            component.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        }
        return component;
    }
}
