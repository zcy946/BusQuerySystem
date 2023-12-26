package com.swing.zcy.BQS.UI.MainWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PanelCenter extends JPanel {
    private String bgColor = "#FEFEFF";
    public CardLayout cardLayout;
    public JPanel page0;
    public JPanel page1;
    public JPanel page2;
    public JPanel page3;
    public JPanel page4; // 登录界面
    public JTable table0;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    public JScrollPane jScrollPane0;
    public JScrollPane jScrollPane1;
    public JScrollPane jScrollPane2;
    public JScrollPane jScrollPane3;

    public PanelCenter() {
        this.setBackground(Color.decode(bgColor)); // 测试代码
        this.cardLayout = new CardLayout();
        this.setLayout(this.cardLayout);
        // page0
        this.initPage0();
        // page1
        this.initPage1();
        // page2
        this.initPage2();
        // page3
        this.initPage3();
        // page4
        this.initPage4();

    }
    public void setShowPage(int index) {
        this.cardLayout.show(this, String.valueOf("page" + index));
    }

    // 初始化page0-4
    public void initPage0() {
        this.page0 = new JPanel();
        this.page0.setLayout(null);
//        this.page0.setBackground(Color.decode("#FFEFE6")); // 测试代码
        this.page0.setBackground(Color.decode(bgColor));

        String[][] testText = {{"文本", "文本"}, {"文本", "文本"}, {"文本", "文本"}, {"文本", "文本"}, {"文本", "文本"}, {"文本", "文本"}, {"文本", "文本"}, {"文本", "文本"}, {"文本", "文本"}, {"文本", "文本"}};
        String[] testTableHeader = {"列", "列", "列", "列", "列", "列", "列", "列", "列", "列", "列", "列", "列", "列", "列", "列", "列", "列", "列", "列"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(testText, testTableHeader);
        this.table0 = new JTable(defaultTableModel);
        this.jScrollPane0 = new JScrollPane(this.table0);
        this.page0.add(this.jScrollPane0);

        this.add(page0, "page0");
    }
    public void initPage1() {
        this.page1 = new JPanel();
        this.page1.setBackground(Color.decode("#84A9FF")); // 测试代码
//        this.page1.setBackground(Color.decode(bgColor));

        this.add(page1, "page1");
    }
    public void initPage2() {
        this.page2 = new JPanel();
        this.page2.setBackground(Color.decode("#C1F486")); // 测试代码
//        this.page2.setBackground(Color.decode(bgColor));
        this.add(page2, "page2");
    }
    public void initPage3() {
        this.page3 = new JPanel();
        this.page3.setBackground(Color.decode("#8CF5FE")); // 测试代码
//        this.page3.setBackground(Color.decode(bgColor));
        this.add(page3, "page3");
    }
    public void initPage4() {
        this.page4 = new JPanel();
        this.page4.setBackground(Color.decode("#FEF191")); // 测试代码
//        this.page4.setBackground(Color.decode(bgColor));
        this.add(page4, "page4");
    }
}
