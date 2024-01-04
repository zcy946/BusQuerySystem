package com.swing.zcy.BQS.UI.MainWindow;

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
                    panelLeft.loginPanel.setBounds(-7, getHeight() - 100, panelLeft.getWidth(), 50);
                }
                panelCenter.setBounds(panelLeft.getWidth(), 0, getWidth() - panelLeft.getWidth(), getHeight());

                panelCenter.page0.scrollPane.setBounds(20, 150, panelCenter.getWidth() - 55, panelCenter.getHeight() - 205);
                panelCenter.page0.searchBar.setBounds(panelCenter.page0.scrollPane.getX(), panelCenter.page0.scrollPane.getY() - 40 - 20, 400, 40);
                panelCenter.page0.showAllRoutesInfo.setBounds(panelCenter.page0.scrollPane.getX() + panelCenter.page0.scrollPane.getWidth() - 180, panelCenter.page0.scrollPane.getY() - 40 - 20, 180, 40);
                panelCenter.page0.searchBtn.setBounds(panelCenter.page0.scrollPane.getX() + panelCenter.page0.scrollPane.getWidth() - 180 - 100 - 8, panelCenter.page0.scrollPane.getY() - 40 - 20, 100, 40);

                panelCenter.page1.scrollPane.setBounds(20, 150, panelCenter.getWidth() - 55, panelCenter.getHeight() - 205);
                panelCenter.page1.searchBar.setBounds(panelCenter.page0.scrollPane.getX(), panelCenter.page0.scrollPane.getY() - 40 - 20, 400, 40);
                panelCenter.page1.showAllRoutesInfo.setBounds(panelCenter.page0.scrollPane.getX() + panelCenter.page0.scrollPane.getWidth() - 180, panelCenter.page0.scrollPane.getY() - 40 - 20, 180, 40);
                panelCenter.page1.searchBtn.setBounds(panelCenter.page0.scrollPane.getX() + panelCenter.page0.scrollPane.getWidth() - 180 - 100 - 8, panelCenter.page0.scrollPane.getY() - 40 - 20, 100, 40);

                panelCenter.page2.scrollPane.setBounds(20, 150, panelCenter.getWidth() - 55, panelCenter.getHeight() - 205);
                panelCenter.page2.searchBar.setBounds(panelCenter.page0.scrollPane.getX(), panelCenter.page0.scrollPane.getY() - 40 - 20, 300, 40);
                panelCenter.page2.exchangeLabel.setBounds(panelCenter.page2.searchBar.getX() + panelCenter.page2.searchBar.getWidth() + 8, panelCenter.page2.searchBar.getY(), 40, 40);
                panelCenter.page2.searchBtn.setBounds(panelCenter.page0.scrollPane.getX() + panelCenter.page0.scrollPane.getWidth() - 180 - 100 - 8, panelCenter.page0.scrollPane.getY() - 40 - 20, 100, 40);
                panelCenter.page2.searchBar2.setBounds(panelCenter.page2.exchangeLabel.getX() + panelCenter.page2.exchangeLabel.getWidth() + 8, panelCenter.page0.scrollPane.getY() - 40 - 20, 300, 40);

                panelCenter.page3.scrollPane.setBounds(20, 150, panelCenter.getWidth() - 55, panelCenter.getHeight() - 205);
                panelCenter.page3.addNewDataBtn.setBounds(panelCenter.page3.scrollPane.getX() + panelCenter.page3.scrollPane.getWidth() - 100 * 3 - 8 * 2, panelCenter.page3.scrollPane.getY() - 60, 100, 38);
                panelCenter.page3.deleteBtn.setBounds(panelCenter.page3.scrollPane.getX() + panelCenter.page3.scrollPane.getWidth() - 100 * 2 - 8, panelCenter.page3.scrollPane.getY() - 60, 100, 38);
                panelCenter.page3.saveBtn.setBounds(panelCenter.page3.scrollPane.getX() + panelCenter.page3.scrollPane.getWidth() - 100, panelCenter.page3.scrollPane.getY() - 60, 100, 38);

                panelCenter.page4.accountField.setBounds(panelCenter.getWidth() / 9, (int)(panelLeft.getWidth() * 1.7), 230, 35);
                panelCenter.page4.passwordField.setBounds(panelCenter.page4.accountField.getX(), panelCenter.page4.accountField.getY() + 75, 230, 35);
                panelCenter.page4.accountText.setBounds(panelCenter.page4.accountField.getX(), panelCenter.page4.accountField.getY() - 60, 100, 100);
                panelCenter.page4.passWordText.setBounds(panelCenter.page4.accountField.getX(), panelCenter.page4.passwordField.getY() - 60, 100, 100);
                panelCenter.page4.loginBtn.setBounds(panelCenter.page4.accountField.getX(), panelCenter.page4.passwordField.getY() + 110, 230, 40);
                panelCenter.page4.imagePanel.setBounds(panelCenter.page4.accountField.getX() + panelCenter.page4.accountField.getWidth() + panelCenter.getWidth() / 15, panelCenter.getWidth() / 15, panelCenter.getHeight() - 2 * panelCenter.getWidth() / 10, panelCenter.getHeight() - 2 * panelCenter.getWidth() / 10);

                panelCenter.page0.repaint();
                panelCenter.page1.repaint();
                panelCenter.page2.repaint();
                panelCenter.page3.repaint();
                panelCenter.page4.repaint();
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
//                    System.out.println("Selected: " + panelLeft.navigationBar.getSelectedValue() +
//                            " [" + panelLeft.navigationBar.getSelectedIndex() + "]");
                    panelCenter.setShowPage(panelLeft.navigationBar.getSelectedIndex());
                }
            }
        });
        // 监听器[切换到登录界面]
        this.panelLeft.loginPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                System.out.println("Selected: 换乘查询 [4]");
                panelCenter.setShowPage(4);
                super.mouseClicked(e);
            }
        });
    }
}
