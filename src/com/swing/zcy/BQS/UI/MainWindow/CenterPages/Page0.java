package com.swing.zcy.BQS.UI.MainWindow.CenterPages;

import com.swing.zcy.BQS.UI.MainWindow.MyColor;
import com.swing.zcy.BQS.UI.MainWindow.PanelCenter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Page0 extends JPanel {
    public JTable table;
    public JScrollPane jScrollPane;
    public Page0() {
        this.setLayout(null);
//        this.page0.setBackground(Color.decode("#FFEFE6")); // 测试代码
        this.setBackground(Color.decode(MyColor.panelCenterBgColor));

    }
}
