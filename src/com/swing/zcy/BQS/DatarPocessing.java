package com.swing.zcy.BQS;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatarPocessing {
    private static List<String> preData;
    private static List<Object[]> data = new ArrayList<>();
    private static Charset originalCharset;
    private static int maxColumn;
    public DatarPocessing() {}
    public static List<Object[]> loadDataFromFile() {
        try {
            preData = Files.readAllLines(Paths.get(BusQuerySystem.dataFilePath), Charset.forName("GBK"));
            originalCharset = Charset.forName("GBK");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 获取最大列数
        for (int i = 0; i < preData.size(); i++) {
            List<String> preData1 = Arrays.asList(preData.get(i).split("%"));
            if (preData1.size() > maxColumn) {
                maxColumn = preData1.size();
            }
//            System.out.println(preData1.size());
        }
        // 额外加三列[最后一列数据分为四列]
        maxColumn += 3;
        // 获取数据
        for (int i = 0; i < preData.size(); i++) {
//        for (int i = 0; i < 5; i++) { // 先那五组数据方便查看
            List<String> preData1 = Arrays.asList(preData.get(i).split("%"));
//            System.out.println(preData1); // 查看数据
            Object[] dataOfLine = new Object[maxColumn];
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
            // 添加站点，从dataOfLine下标1到倒数第二个
            for (int k = 1; k < preData1.size() - 1; k++) {
                dataOfLine[k + 4] = preData1.get(k);
            }

            data.add(dataOfLine);
        }
        return data;
    }
    // 保存/更新数据到文件
    public static void saveDatatoFile(List<Object[]> data) {
        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(BusQuerySystem.dataFilePath, false));
            // 使用原始编码格式写入文件
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(BusQuerySystem.dataFilePath), originalCharset));
            for (Object[] rowData: data) {
                StringBuilder lineBuilder = new StringBuilder();
                // 添加线路名
                lineBuilder.append(rowData[0]);
                // 写入站名
                for (int i = 5; i < rowData.length; i++) {
                    if (rowData[i] != null) {
                        lineBuilder.append("%").append(rowData[i]);
                    }
                }
                lineBuilder.append("%");
                // 写入时间1
                if (rowData[2] != null) {
                    lineBuilder.append(rowData[2]);
                }
                // 写入时间2
                if (rowData[3] != null) {
                    lineBuilder.append(" ").append(rowData[3]);
                }
                // 写入票价
                if (rowData[1] != null) {
                    lineBuilder.append(" 票价").append(rowData[1]).append("元");
                }
                // 写入有效卡
                if (rowData[4] != null) {
                    lineBuilder.append(" ").append(rowData[4]).append("卡有效");
                }
                // 添加换行
                lineBuilder.append(System.lineSeparator());
                // 写入当前行
                writer.write(lineBuilder.toString());
            }
            // 关闭写入流
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 从数据库获取数据
    public static List<Object[]> loadDataFromDataBase() {
        List<Object[]> data = new ArrayList<>();
        return data;
    }
    // 提取字符串中的数字
    private static double extractNumber(String input) {
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
    private static String extractLetters(String input) {
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
        // 💖 id 💖 票价 💖 时间1 💖 时间2 💖 有效卡 💖 站点s
        for (int i = 0; i < this.data.size(); i++) {
            System.out.print("No." + (i + 1) + ": ");
            Object[] dataArray = this.data.get(i);
            for (int j = 0; j < dataArray.length; j++) {
                System.out.print("[" + j + "]" + dataArray[j] + ",");
            }
            System.out.println();  // 换行，进入下一行
        }
        // 查看最大列数
        System.out.println("共" + this.maxColumn + "列");
    }
    // 获取最大列数
    public int getMaxColumn() {
        return this.maxColumn;
    }

//    public static void main(String[] args) {
//        loadData test = new loadData();
//    }
}
