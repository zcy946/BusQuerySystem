package com.swing.zcy.BQS.UI.MainWindow;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class PanelLeft extends JPanel{
    public JLabel headPortrait; // 头像
    public JList<String> navigationBar; // 导航栏
    public DefaultListModel<String> itemsInBar; // 导航栏中的项目
    public JPanel loginPanel; // 退出按钮
    private Font iconFont; // 字体图标
    private final String fontFilePath = "res/iconFont/iconfont.ttf";
    private DefaultListCellRenderer MyListCellRenderer;
    private final String bgColor = "#F4F4FD";
    private JLabel loginIconLabel;
    private JLabel loginTextLabel;
    private boolean isLoginClicked;

    public PanelLeft() {
        setLayout(null); // 打破布局，手动设置
        this.setBackground(Color.decode(bgColor));
        // 字体加载
        this.iconFont = this.loadIconFont(this.fontFilePath);
        // 自定义渲染器[皮肤]
        this.loadMyRenderer();
        // 初始化login按钮
        this.isLoginClicked = false;
        ///////////////////////////////////////////////////////////////////////

        // 头像
        this.headPortrait = new JLabel();
        this.headPortrait.setFont(iconFont.deriveFont(50f));
        this.headPortrait.setText("\ue6f3");
        this.headPortrait.setForeground(Color.decode("#4F4F93"));
        this.add(this.headPortrait);
        ///////////////////////////////////////////////////////////////////////

        // 导航栏数据添加
        this.itemsInBar = new DefaultListModel<>();
        this.itemsInBar.addElement("线路查询");
        this.itemsInBar.addElement("站点查询");
        this.itemsInBar.addElement("换乘查询");
        // 导航栏
        this.navigationBar = new JList<>(this.itemsInBar);
        this.navigationBar.setBackground(Color.decode(bgColor));
//        this.navigationBar.setBackground(Color.decode("#1e1f22")); // 测试用代码
        this.navigationBar.setSelectedIndex(0); // 设置默认选中项
        this.navigationBar.setCellRenderer(this.MyListCellRenderer); // 选择自己的渲染器
        this.add(this.navigationBar);
        ///////////////////////////////////////////////////////////////////////

        // 退出面板
        this.loginPanel = new JPanel();
//        this.loginPanel.setBackground(Color.decode("#ffd253")); // 测试代码
        this.loginPanel.setBackground(Color.decode(this.bgColor));
        this.loginPanel.setLayout(null);
        // loginLabel
        // loginIconLabel
        this.loginIconLabel = new JLabel("\ue606");
        loginIconLabel.setForeground(Color.decode("#7C7CB7"));
        loginIconLabel.setFont(this.iconFont.deriveFont(25f));
        loginIconLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        loginIconLabel.setBounds(10, 0, 50, 50);
//        loginIconLabel.setBorder(new LineBorder(Color.BLACK, 2)); // 测试代码
        this.loginPanel.add(loginIconLabel);
        // loginTextLabel
        this.loginTextLabel = new JLabel("Log in");
        loginTextLabel.setForeground(Color.decode("#7C7CB7"));
        loginTextLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        loginTextLabel.setBounds(loginIconLabel.getWidth() + loginIconLabel.getX(), 0, 70, 50);
//        loginTextLabel.setBorder(new LineBorder(Color.BLACK, 2)); // 测试代码
        this.loginPanel.add(loginTextLabel);
        // 监听器
        this.loginPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginIconLabel.setForeground(Color.decode("#ee4d38"));
                loginIconLabel.setFont(iconFont.deriveFont(28f));
                loginTextLabel.setForeground(Color.decode("#ee4d38"));
                loginTextLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
                loginTextLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isLoginClicked) {
                    loginIconLabel.setForeground(Color.decode("#7C7CB7"));
                    loginIconLabel.setFont(iconFont.deriveFont(25f));
                    loginTextLabel.setForeground(Color.decode("#7C7CB7"));
                    loginTextLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
                }
                super.mouseExited(e);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                isLoginClicked = true;
                loginIconLabel.setForeground(Color.decode("#ee4d38"));
                loginIconLabel.setFont(iconFont.deriveFont(28f));
                loginTextLabel.setForeground(Color.decode("#ee4d38"));
                loginTextLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
            }
        });
        this.add(this.loginPanel);
    }
    // 字体加载器
    private static Font loadIconFont(String fontFilePath) {
        File fontFile = new File(fontFilePath);
        Font newFont;
        try {
            newFont = Font.createFont(Font.TRUETYPE_FONT, fontFile); // 加载TureType类型的字体
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        return newFont;
    }
    // 自定义渲染器
    private void loadMyRenderer() {
        this.MyListCellRenderer = new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {

                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); // 获取旧的listCell渲染器
                renderer.setPreferredSize(new Dimension(100, 50)); // 设置新的cell宽高

                renderer.setFont(new Font("微软雅黑", Font.PLAIN, 18)); // 设置新的字体

                if (renderer instanceof JLabel) {
                    ((JLabel) renderer).setHorizontalAlignment(SwingConstants.CENTER); // 设置文字水平居中
                }
                // 根据是否选中设置不同的样式
                if (isSelected) {
                    renderer.setForeground(Color.decode("#ffffff")); // 选中时的文字颜色
                    renderer.setBackground(Color.decode("#ee4d38")); // 选中时的背景色
                    renderer.setFont(new Font("微软雅黑", Font.BOLD, 20));
                    // login按钮恢复
                    isLoginClicked = false;
                    loginIconLabel.setForeground(Color.decode("#7C7CB7"));
                    loginIconLabel.setFont(iconFont.deriveFont(25f));
                    loginTextLabel.setForeground(Color.decode("#7C7CB7"));
                    loginTextLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
                } else {
                    renderer.setForeground(Color.decode("#7C7CB7")); // 未选中时的文字颜色
                    renderer.setBackground(Color.decode("#F8F8FF")); // 未选中时的背景色
                }
                return renderer;
            }
        };
    }
}
