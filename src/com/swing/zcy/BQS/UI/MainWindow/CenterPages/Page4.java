package com.swing.zcy.BQS.UI.MainWindow.CenterPages;

import com.swing.zcy.BQS.UI.MainWindow.PanelCenter;

import javax.swing.*;
import java.awt.*;

public class Page4 extends JPanel{
    public JTable table;
    public JScrollPane jScrollPane;
    public Page4() {
//        this.setBackground(Color.decode("#FEF191")); // 测试代码
        this.setBackground(Color.decode(PanelCenter.bgColor));
        this.setLayout(null);
        JEditorPane account = new JEditorPane();
        JEditorPane passWord = new JEditorPane();
        JButton login = new JButton("登录");
        JButton cancel = new JButton("取消");
        this.add(account);
        this.add(passWord);
        this.add(login);
        this.add(cancel);
    }
}
