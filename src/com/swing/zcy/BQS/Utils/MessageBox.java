package com.swing.zcy.BQS.Utils;

import com.swing.zcy.BQS.BusQuerySystem;
import com.swing.zcy.BQS.DatarPocessing;
import com.swing.zcy.BQS.UI.MainWindow.CenterPages.MyTableModel;

import javax.swing.*;

public class MessageBox {
    public static boolean showConfirmDialog(String information) {
        int result = JOptionPane.showConfirmDialog(null, information, "确认", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean showConfirmDialog(String information, String titleName) {
        int result = JOptionPane.showConfirmDialog(null, information, titleName, JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
    public static void showMessageDialog(String information) {
        JOptionPane.showMessageDialog(null, information, "信息", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void showMessageDialog(String information, String titleName) {
        JOptionPane.showMessageDialog(null, information, titleName, JOptionPane.INFORMATION_MESSAGE);
    }
}
