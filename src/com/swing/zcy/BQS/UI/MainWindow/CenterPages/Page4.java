package com.swing.zcy.BQS.UI.MainWindow.CenterPages;

import com.swing.zcy.BQS.BusQuerySystem;
import com.swing.zcy.BQS.UI.MainWindow.MainWindow;
import com.swing.zcy.BQS.UI.MainWindow.MyColor;
import com.swing.zcy.BQS.UI.MainWindow.PanelCenter;
import com.swing.zcy.BQS.UI.MainWindow.PanelLeft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Page4 extends JPanel{
    public JTable table;
    public JScrollPane jScrollPane;
    public JLabel accountText;
    public JLabel passWordText;
    public JTextField accountField;
    public JPasswordField passwordField;
    public JButton loginBtn;
    public JLabel image;
    public Page4() {
//        this.setBackground(Color.decode("#FEF191")); // 测试代码
        this.setBackground(Color.decode(MyColor.panelCenterBgColor));
        this.setLayout(null);
        // text
        this.intiTextOnField();
        // field
        this.initFields();
        // btn
        this.initButtons();

    }
    private void intiTextOnField() {
        this.accountText = new JLabel("Account");
        this.passWordText = new JLabel("Password");
        this.accountText.setForeground(Color.decode(MyColor.fontColor1));
        this.accountText.setFont(new Font("微软雅黑", Font.BOLD, 13));
        this.passWordText.setForeground(Color.decode(MyColor.fontColor1));
        this.passWordText.setFont(new Font("微软雅黑", Font.BOLD, 13));
        this.add(accountText);
        this.add(passWordText);
//        this.accountText.setBounds(10, 10, 100, 100);
    }
    private void initFields() {
        this.accountField = new JTextField();
        this.passwordField = new JPasswordField();
        this.accountField.setForeground(Color.decode(MyColor.fontAnnotationColor3));
        this.passwordField.setForeground(Color.decode(MyColor.fontAnnotationColor3));
        this.accountField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        this.passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        passwordField.setEchoChar((char)(0)); // 显示明文
        this.accountField.setText("Enter your account");
        this.passwordField.setText("Password");

        // 账号框焦点监听器
        this.accountField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (accountField.getText().equals("Enter your account")) {
                    accountField.setText("");
                    accountField.setForeground(Color.decode(MyColor.fontAnnotationColor1));
                }
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (accountField.getText().isEmpty()) {
                    accountField.setForeground(Color.decode(MyColor.fontAnnotationColor3));
                    accountField.setText("Enter your account");
                }
                super.focusLost(e);
            }
        });
        // 密码框焦点监听器
        this.passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
//                System.out.println("gained");
                if (passwordField.getText().equals("Password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.decode(MyColor.fontAnnotationColor1));
                    passwordField.setEchoChar('•'); // 隐藏明文
                }
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
//                System.out.println("lost");
                if (passwordField.getText().isEmpty()) {
                    passwordField.setForeground(Color.decode(MyColor.fontAnnotationColor3));
                    passwordField.setText("Password");
                    passwordField.setEchoChar((char) (0)); // 显示明文
                }
                super.focusLost(e);
            }
        });
        this.add(accountField);
        this.add(passwordField);
    }

    private  void initButtons() {
        this.loginBtn = new JButton("Log in");
        this.loginBtn.setBackground(Color.decode(MyColor.selectedColor));
        this.loginBtn.setFont(new Font("微软雅黑", Font.BOLD, 16));
        this.loginBtn.setForeground(Color.decode("#ffffff"));
        this.add(loginBtn);
        this.loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("点击");
                if (accountField.getText().equals("Enter your account")) {
                    JOptionPane.showMessageDialog(null, "账号还未填写", "警告", JOptionPane.WARNING_MESSAGE);
//                    System.out.println("账号为空");
                }
                else {
                    if (passwordField.getText().equals("Password")) {
                        JOptionPane.showMessageDialog(null, "密码还未填写", "警告", JOptionPane.WARNING_MESSAGE);
//                        System.out.println("密码为空");
                    }
                    else {
                        String inputAccount = accountField.getText().trim();
                        String inputPassWord = passwordField.getText().trim();
//                        System.out.println(inputAccount + "\n" + inputPassWord); // 测试代码
                        if (inputAccount.equals(BusQuerySystem.ACCOUNT) && inputPassWord.equals(BusQuerySystem.PASSWORD)) {
                            PanelLeft.itemsInBar.addElement("后台管理");
                            PanelLeft.headPortrait.setText("\ue519");
                            PanelLeft.loginIconLabel.setText("\ue7e9");
                            PanelLeft.loginTextLabel.setText("注 销");
//                            PanelLeft.loginTextLabel.setText("Log out");
//                            PanelLeft.loginIconLabel.setForeground(Color.decode(MyColor.fontColor2));
//                            PanelLeft.loginIconLabel.setFont(PanelLeft.iconFont.deriveFont(25f));
//                            PanelLeft.loginTextLabel.setForeground(Color.decode(MyColor.fontColor2));
//                            PanelLeft.loginTextLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));

                            JOptionPane.showMessageDialog(null, "登录成功", "信息", JOptionPane.INFORMATION_MESSAGE);
                            BusQuerySystem.isLogin = true;
//                            System.out.println("登陆成功");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "账号或者密码错误", "错误", JOptionPane.ERROR_MESSAGE);
//                            System.out.println("账号或者密码错误");
                        }
                    }
                }
            }
        });
    }
}
