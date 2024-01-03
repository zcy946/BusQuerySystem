package com.swing.zcy.BQS.UI.MainWindow;

import com.swing.zcy.BQS.UI.MainWindow.CenterPages.*;
import com.swing.zcy.BQS.Utils.MyColor;

import javax.swing.*;
import java.awt.*;


public class PanelCenter extends JPanel {
    public CardLayout cardLayout;
    public Page0 page0;
    public Page1 page1;
    public Page2 page2;
    public Page3 page3;
    public Page4 page4; // 登录界面

    public PanelCenter() {
        this.setBackground(Color.decode(MyColor.panelCenterBgColor));
        this.cardLayout = new CardLayout();
        this.setLayout(this.cardLayout);
        this.initPages(); // 初始化各个page
    }

    // 初始化page0-4
    public void initPages() {
        this.page0 = new Page0();
        this.page1 = new Page1();
        this.page2 = new Page2();
        this.page3 = new Page3();
        this.page4 = new Page4();
        this.add(page0, "page0");
        this.add(page1, "page1");
        this.add(page2, "page2");
        this.add(page3, "page3");
        this.add(page4, "page4");
    }

    // 显示特定的界面
    public void setShowPage(int index) {
        this.cardLayout.show(this, String.valueOf("page" + index));
    }
}
