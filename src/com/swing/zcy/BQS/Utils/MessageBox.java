package com.swing.zcy.BQS.Utils;

import com.swing.zcy.BQS.BusQuerySystem;
import com.swing.zcy.BQS.DatarPocessing;
import com.swing.zcy.BQS.UI.MainWindow.CenterPages.MyTableModel;

import javax.swing.*;

public class MessageBox {
    public static boolean showConfirmDialog(String information) {
        int result = JOptionPane.showConfirmDialog(null, information, "确认", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
    public static void showMessageDialog(String information) {
        JOptionPane.showMessageDialog(null, information, "信息", JOptionPane.ERROR_MESSAGE);
    }
    public static void showMessageDialog(String information, int jOptionPaneNum) {
        JOptionPane.showMessageDialog(null, information, "信息", jOptionPaneNum);
    }
}
