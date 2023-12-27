package com.swing.zcy.BQS.UI.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class PanelLeft extends JPanel{
    public static JLabel headPortrait; // 头像
    public JList<String> navigationBar; // 导航栏
    public static DefaultListModel<String> itemsInBar; // 导航栏中的项目
    public JPanel loginPanel; // 退出按钮
    public static Font iconFont; // 字体图标
    private final String fontFilePath = "res/iconFont/iconfont.ttf";
    private DefaultListCellRenderer MyListCellRenderer;
    public static JLabel loginIconLabel;
    public static JLabel loginTextLabel;
    public boolean isLoginClicked;

    public PanelLeft() {
        setLayout(null); // 打破布局，手动设置
        this.setBackground(Color.decode(MyColor.panelLeftBgColor));
        // 字体加载
        iconFont = loadIconFont(this.fontFilePath);
        // 自定义渲染器[皮肤]
        this.loadMyRenderer();
        // 初始化login按钮
        this.isLoginClicked = false;
        ///////////////////////////////////////////////////////////////////////

        // 头像
        headPortrait = new JLabel();
        headPortrait.setFont(iconFont.deriveFont(50f));
        headPortrait.setText("\ue6f3");
//        this.headPortrait.setForeground(Color.decode("#4F4F93"));
        headPortrait.setForeground(Color.decode(MyColor.fontColor1));
        this.add(headPortrait);
        ///////////////////////////////////////////////////////////////////////

        // 导航栏数据添加
        itemsInBar = new DefaultListModel<>();
        itemsInBar.addElement("线路查询");
        itemsInBar.addElement("站点查询");
        itemsInBar.addElement("换乘查询");
        // 导航栏
        this.navigationBar = new JList<>(itemsInBar);
        this.navigationBar.setBackground(Color.decode(MyColor.panelLeftBgColor));
//        this.navigationBar.setBackground(Color.decode("#1e1f22")); // 测试用代码
        this.navigationBar.setSelectedIndex(0); // 设置默认选中项
        this.navigationBar.setCellRenderer(this.MyListCellRenderer); // 选择自己的渲染器
        this.add(this.navigationBar);
        ///////////////////////////////////////////////////////////////////////

        // 退出面板
        this.loginPanel = new JPanel();
//        this.loginPanel.setBackground(Color.decode("#ffd253")); // 测试代码
        this.loginPanel.setBackground(Color.decode(MyColor.panelLeftBgColor));
        this.loginPanel.setLayout(null);
        // loginLabel
        // loginIconLabel
        loginIconLabel = new JLabel("\ue7ea");
        loginIconLabel.setForeground(Color.decode(MyColor.fontColor2));
        loginIconLabel.setFont(iconFont.deriveFont(25f));
        loginIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginIconLabel.setBounds(10, 0, 50, 50);
//        loginIconLabel.setBorder(new LineBorder(Color.BLACK, 2)); // 测试代码
        this.loginPanel.add(loginIconLabel);
        // loginTextLabel
        loginTextLabel = new JLabel("登 入");
//        loginTextLabel = new JLabel("Log in");
        loginTextLabel.setForeground(Color.decode(MyColor.fontColor2));
        loginTextLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        loginTextLabel.setBounds(loginIconLabel.getWidth() + loginIconLabel.getX(), 0, 70, 50);
//        loginTextLabel.setBorder(new LineBorder(Color.BLACK, 2)); // 测试代码
        this.loginPanel.add(loginTextLabel);
        // 监听器
        this.loginPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginIconLabel.setForeground(Color.decode(MyColor.selectedColor));
                loginIconLabel.setFont(iconFont.deriveFont(28f));
                loginTextLabel.setForeground(Color.decode(MyColor.selectedColor));
                loginTextLabel.setFont(new Font("微软雅黑", Font.PLAIN, 17));
                loginTextLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isLoginClicked) {
                    loginIconLabel.setForeground(Color.decode(MyColor.fontColor2));
                    loginIconLabel.setFont(iconFont.deriveFont(25f));
                    loginTextLabel.setForeground(Color.decode(MyColor.fontColor2));
                    loginTextLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                }
                super.mouseExited(e);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                isLoginClicked = true;
                loginIconLabel.setForeground(Color.decode(MyColor.selectedColor));
                loginIconLabel.setFont(iconFont.deriveFont(28f));
                loginTextLabel.setForeground(Color.decode(MyColor.selectedColor));
                loginTextLabel.setFont(new Font("微软雅黑", Font.PLAIN, 17));
                navigationBar.clearSelection(); // 取消导航栏的选中
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
                    renderer.setBackground(Color.decode(MyColor.selectedColor)); // 选中时的背景色
                    renderer.setFont(new Font("微软雅黑", Font.BOLD, 20));
                    // login按钮恢复
                    isLoginClicked = false;
                    loginIconLabel.setForeground(Color.decode(MyColor.fontColor2));
                    loginIconLabel.setFont(iconFont.deriveFont(25f));
                    loginTextLabel.setForeground(Color.decode(MyColor.fontColor2));
                    loginTextLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                } else {
                    renderer.setForeground(Color.decode(MyColor.fontColor2)); // 未选中时的文字颜色
                    renderer.setBackground(Color.decode(MyColor.panelLeftBgColor)); // 未选中时的背景色
                }
                // 移除边框
                if (renderer instanceof JComponent) {
                    ((JComponent) renderer).setBorder(BorderFactory.createEmptyBorder());
                }
                return renderer;
            }
        };
    }
}
