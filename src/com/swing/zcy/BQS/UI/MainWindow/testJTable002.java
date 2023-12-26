package com.swing.zcy.BQS.UI.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class testJTable002 {
    public testJTable002() {
        JFrame f = new JFrame();
        Object[][] playerInfo = {
                {"阿呆", new Integer(66), new Integer(32), new Integer(98), new Boolean(false)},
                {"阿呆", new Integer(82), new Integer(69), new Integer(128), new Boolean(true)},
        };
        String[] Names = {"1", "2", "2", "2", "2"};
        JTable table = new JTable(playerInfo, Names);
        table.setPreferredScrollableViewportSize(new Dimension(550, 30));
        JScrollPane scrollPane = new JScrollPane(table); // JScrollPane会自动取得Column Header
        f.add(scrollPane, BorderLayout.CENTER);
//        // 或者手动显示表头
//        f.add(table,BorderLayout.CENTER);
//        f.add(table.getTableHeader(),BorderLayout.NORTH);
        f.setTitle("Simple Table");
        f.pack();
        f.show();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        testJTable002 b = new testJTable002();
    }
}