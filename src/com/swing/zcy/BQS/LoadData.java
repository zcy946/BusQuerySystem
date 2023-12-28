package com.swing.zcy.BQS;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadData {
    private List<String> preData;
    private List<Object[]> data = new ArrayList<>();
    private int maxColumn;
    public LoadData() {
//        this.loadDataFromFile();
    }
    public List<Object[]> loadDataFromFile() {
        try {
            this.preData = Files.readAllLines(Paths.get(BusQuerySystem.dataFilePath), Charset.forName("GBK"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 获取最大列数
        for (int i = 0; i < preData.size(); i++) {
            List<String> preData1 = Arrays.asList(preData.get(i).split("%"));
            if (preData1.size() > this.maxColumn) {
                this.maxColumn = preData1.size();
            }
//            System.out.println(preData1.size());
        }
        // 额外加三列[最后一列数据分为四列]
        this.maxColumn += 3;
        // 获取数据
        for (int i = 0; i < preData.size(); i++) {
//        for (int i = 0; i < 5; i++) { // 先那五组数据方便查看
            List<String> preData1 = Arrays.asList(preData.get(i).split("%"));
//            System.out.println(preData1); // 查看数据
            Object[] dataOfLine = new Object[this.maxColumn];
            // 获取线路名
//            System.out.println(preData1.get(0));
            dataOfLine[0] = preData1.get(0); // 0❤️ 站名
//            System.out.println(dataOfLine.get(0)); // 查看数据

            // 获取运营时间、票价、和卡号
            String[] lastData = preData1.get(preData1.size() - 1).split(" "); // 以空格对时间票价和有效卡进行分割
//            System.out.println(Arrays.toString(lastData)); // 测试代码

            // 找规律 并按照[票价 时间1 时间2 有效卡] 存储
            if ((lastData.length >= 2 &&lastData[1].isEmpty()) || lastData.length == 2) {
//                System.out.println(Arrays.toString(lastData));
                // 得出结论 第二个为空或者只有两个元素的数组`第一个是时间``最后一个是票价`
//                System.out.println(lastData[lastData.length - 1]); // 杂数据
                // 分离数字
                dataOfLine[1] = extractNumber(lastData[lastData.length - 1]); // 1❤️ 票价
//                System.out.println(extractNumber(lastData[lastData.length - 1]));
                dataOfLine[2] = (lastData[0]); // 2❤️ 时间1
                dataOfLine[3] = null; // 3❤️ 时间2
                dataOfLine[4] = null; // 3❤️ 有效卡
            }
            if (lastData.length == 3 && !lastData[1].isEmpty()) {
//                System.out.println(Arrays.toString(lastData));
                // 得出结论 第二个不为空且只有三个元素的数组`第一个是时间``第二个是票价``第三个是有效卡`
                dataOfLine[1] = extractNumber(lastData[1]); // 1❤️ 票价
                dataOfLine[2] = lastData[0]; // 2❤️ 时间1
                dataOfLine[3] = null; // 3❤️ 时间2
                dataOfLine[4] = extractLetters(lastData[2]); // 3❤️ 有效卡
            }
            if (lastData.length == 4) {
//                System.out.println(Arrays.toString(lastData));
//                // 得出结论 只有四个元素的数组`第一个是时间1``第二个是时间2``第三个是票价``第四个是有效卡`
                dataOfLine[1] = extractNumber(lastData[2]); // 1❤️ 票价
                dataOfLine[2] = lastData[0]; // 2❤️ 时间1
                dataOfLine[3] = lastData[1]; // 3❤️ 时间2
                dataOfLine[4] = extractLetters(lastData[3]); // 3❤️ 有效卡
            }
            // 添加站点
            for (int j = 1; j < this.maxColumn - 5 && (5 + j) < dataOfLine.length && j < preData1.size() - 1; j++) { // 下标为0的读过 下标为preData1.size() - 1也读过
                if (!preData1.get(j).isEmpty()) {
                    dataOfLine[4 + j] = preData1.get(j);
                }
            }
            this.data.add(dataOfLine);
        }
        return this.data;
    }
    // 提取字符串中的数字
    private double extractNumber(String input) {
        // 使用正则表达式匹配数字部分
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(input);

        // 查找匹配的数字
        if (matcher.find()) {
            // 将匹配到的字符串转换为 double 类型
            return Double.parseDouble(matcher.group());
        } else {
            // 如果没有匹配到数字，则返回默认值
            return 0.0;
        }
    }
    // 提取字符串中的字母
    private String extractLetters(String input) {
        // 使用正则表达式匹配字母部分
        Pattern pattern = Pattern.compile("[A-Za-z]+");
        Matcher matcher = pattern.matcher(input);

        // 查找匹配的字母
        if (matcher.find()) {
            return matcher.group();
        } else {
            return ""; // 如果没有匹配到字母，返回空字符串
        }
    }
    // 查看数据
    public void showData() {
        for (int i = 0; i < this.data.size(); i++) {
            System.out.print("No." + (i + 1) + ": ");
            Object[] dataArray = this.data.get(i);
            for (int j = 0; j < dataArray.length; j++) {
                System.out.print("[" + j + "]" + dataArray[j] + ",");
            }
            System.out.println();  // 换行，进入下一行
        }
        // 查看最大列数
        System.out.println("共" + this.maxColumn + "行");
    }

//    public static void main(String[] args) {
//        loadData test = new loadData();
//    }
}
