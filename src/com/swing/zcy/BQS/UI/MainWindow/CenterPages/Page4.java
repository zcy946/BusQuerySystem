package com.swing.zcy.BQS.UI.MainWindow.CenterPages;

import com.swing.zcy.BQS.UI.MainWindow.MyColor;
import com.swing.zcy.BQS.UI.MainWindow.PanelCenter;

import javax.swing.*;
import java.awt.*;

public class Page4 extends JPanel{
    public JTable table;
    public JScrollPane jScrollPane;
    public JTextField accountField;
    public JPasswordField passwordField;
    public JButton loginBtn;
    public JButton cancelBtn;
    public Page4() {
//        this.setBackground(Color.decode("#FEF191")); // 测试代码
        this.setBackground(Color.decode(MyColor.panelCenterBgColor));
        this.setLayout(null);
        this.accountField = new JTextField();
        this.passwordField = new JPasswordField();
        this.loginBtn = new JButton("登录");
        this.cancelBtn = new JButton("取消");
//        this.accountField =
        this.add(accountField);
        this.add(passwordField);
        this.add(loginBtn);
        this.add(cancelBtn);

    }
}
