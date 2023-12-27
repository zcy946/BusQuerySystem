package com.swing.zcy.BQS.UI.MainWindow;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {
    public JPanel mainPanel;
    public PanelLeft panelLeft;
    public final PanelCenter panelCenter;
    public MainWindow() {
        try {
            UIManager.setLookAndFeel( new FlatLightLaf()); // 浅色皮肤1
//            UIManager.setLookAndFeel( new FlatIntelliJLaf()); // 浅色皮肤2
//            UIManager.setLookAndFeel( new FlatDarkLaf()); // 深色皮肤1
//            UIManager.setLookAndFeel( new FlatDarculaLaf()); // 深色皮肤2
        } catch( Exception ex ) {
            System.err.println( "皮肤加载失败" );
        }
        this.setTitle("公交查询系统");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 点击关闭时关闭
        this.setMinimumSize(new Dimension(1400, 850));
        this.setSize(1400, 850);
        this.setLocationRelativeTo(null); // 居中
        BufferedImage iconImage = null;
        try {
            iconImage = ImageIO.read(new File("res/icon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setIconImage(iconImage); // 设置窗口图标

        // 主面板
        this.mainPanel = new JPanel();
        this.mainPanel.setBackground(Color.decode("#fefeff"));
        this.mainPanel.setLayout(null);
        // 左侧面盘
        this.panelLeft = new PanelLeft();
        this.mainPanel.add(this.panelLeft);
        // 中心面板
        this.panelCenter = new PanelCenter();
        this.mainPanel.add(this.panelCenter);

        this.add(mainPanel);
        this.initListener();
        setVisible(true);
    }
    private void initListener() {
        // 监听器[自适应布局]
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (panelLeft.getWidth() < 250) {
                    panelLeft.setBounds(0, 0, getWidth() / 8, getHeight());
                    panelLeft.headPortrait.setBounds((panelLeft.getWidth() - 50) / 2, (panelLeft.getWidth() - 100) / 2, 100, 100);
                    panelLeft.navigationBar.setBounds(0, panelLeft.getWidth(), panelLeft.getWidth(), panelLeft.getHeight() - panelLeft.getWidth() - 150);
                    panelLeft.loginPanel.setBounds(-10, getHeight() - 100, panelLeft.getWidth(), 50);
                }
                panelCenter.setBounds(panelLeft.getWidth(), 0, getWidth() - panelLeft.getWidth(), getHeight());
                panelCenter.page0.repaint();
                panelCenter.page1.repaint();
                panelCenter.page2.repaint();
                panelCenter.page3.repaint();
                panelCenter.page4.repaint();

//                panelCenter.jScrollPane0.setBounds(20, 150, panelCenter.getWidth() - 55, panelCenter.getHeight() - 205);
                panelCenter.page0.jScrollPane.setBounds(20, 150, panelCenter.getWidth() - 55, panelCenter.getHeight() - 205);
//                panelCenter.jScrollPane0.repaint();
//                System.out.println("getWidth(): " + getWidth() +
//                        "\ngetHeight(): " + getHeight() +
//                        "\npanelLeft.getWidth(): " + panelLeft.getWidth() +
//                        "\npanelLeft.getHeight(): " + panelLeft.getHeight() +
//                        "\n------------------------------------------------------------------");
//                System.out.println(panelCenter.page0.getWidth());
//                System.out.println(panelCenter.page1.getWidth());
//                System.out.println(panelCenter.page2.getWidth());
//                System.out.println(panelCenter.page3.getWidth());
//                System.out.println(panelCenter.page4.getWidth()); // 测试代码
                super.componentResized(e);
            }
        });
        //监听器[切换card]
        this.panelLeft.navigationBar.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // 用户完成选择时触发的代码
                    System.out.println("Selected: " + panelLeft.navigationBar.getSelectedValue() +
                            " [" + panelLeft.navigationBar.getSelectedIndex() + "]");
                    panelCenter.setShowPage(panelLeft.navigationBar.getSelectedIndex());
                }
            }
        });
        // 监听器[切换到登录界面]
        this.panelLeft.loginPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Selected: 换乘查询 [4]");
                panelCenter.setShowPage(4);
                super.mouseClicked(e);
            }
        });
    }
}
