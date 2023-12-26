package com.console.zcy.SIMS;

import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem SIMS = new StudentManagementSystem();
        while (true) {
            SIMS.showMenu();
            int userInput = scanner.nextInt();
            if (userInput == 1) { // 查看学生信息
                SIMS.findAllStudent();
            } else if (userInput == 2) { // 添加学生信息
                System.out.print("请输入要添加的学生数量: ");
                SIMS.addStudent(scanner.nextInt());
            } else if (userInput == 3) { // 修改学生信息
                System.out.println("1. 通过学号查找" +
                        "\n2. 通过姓名查找");
                int searchOption = scanner.nextInt();
                if (searchOption == 1) {
//                    System.out.println("by ID");
                    System.out.print("请输入要查找学生学号: ");
                    if (scanner.hasNextInt()) {
                        SIMS.updateStudent(scanner.nextInt());
                    } else {
                        System.out.println("请输入正确的学号");
                    }
                } else if (searchOption == 2) {
//                    System.out.println("by Name");
                    System.out.print("请输入要查找学生姓名: ");
                    SIMS.updateStudent(scanner.next());
                } else {
                    System.out.println("请输入正确的选项");
                }
            } else if (userInput == 4) { // 删除学生信息
                System.out.println("1. 通过学号查找" +
                        "\n2. 通过姓名查找");
                int searchOption = scanner.nextInt();
                if (searchOption == 1) {
                    System.out.print("请输入要查找学生学号: ");
                    if (scanner.hasNextInt()) {
                        SIMS.deleteStudent(scanner.nextInt());
                    } else {
                        System.out.println("请输入正确的学号");
                    }
                } else if (searchOption == 2) {
                    System.out.print("请输入要查找学生姓名: ");
                    SIMS.deleteStudent(scanner.next());
                } else {
                    System.out.println("请输入正确的选项");
                }
            } else if (userInput == 5) { // 查看学生构造方法
                SIMS.showCreateStudent();
            } else {
                System.exit(0); // 退出系统
            }
            scanner.nextLine();
            System.out.println("按 Enter 键继续...");
            scanner.nextLine();
        }
    }
}
