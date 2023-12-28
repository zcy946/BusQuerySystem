package com.swing.zcy.BQS.UI.MainWindow.CenterPages;

import javax.swing.*;
import java.awt.*;

public class Page3 extends JPanel{
    private MyTableModel myTableModel;
    public JTable table;
    public JScrollPane jScrollPane;
    public Page3() {
        this.setBackground(Color.decode("#8CF5FE")); // 测试代码
//        this.setBackground(Color.decode(PanelCenter.bgColor));
        this.myTableModel = new MyTableModel();
    }
}
