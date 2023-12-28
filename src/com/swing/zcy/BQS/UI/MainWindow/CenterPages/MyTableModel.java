package com.swing.zcy.BQS.UI.MainWindow.CenterPages;

import com.swing.zcy.BQS.BusQuerySystem;

import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.Charset;
public class MyTableModel extends AbstractTableModel {
    private List<Object> data;
    private List<String> preData;
    public MyTableModel() {
        // 加载数据
        this.loadData();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
    private void loadData() {
        try {
            this.preData = Files.readAllLines(Paths.get(BusQuerySystem.dataFilePath), Charset.forName("GBK"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < preData.size(); i++) {
            List<String> preData1;
//            preData1.add(preData.get(i).strip("%")[])

//            Object[] dataOfLine = new Object[];
//            this.data.add(dataOfLine);
        }
//        for (String i : this.preData) { // 测试代码 显示所有数据
//            System.out.println(i);
//        }
    }
}
