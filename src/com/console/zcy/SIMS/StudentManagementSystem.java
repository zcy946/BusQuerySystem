package com.console.zcy.SIMS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StudentManagementSystem {
    Scanner scanner = new Scanner(System.in);
    private final List<Student> studentList;

    public StudentManagementSystem() {
        this.studentList = new ArrayList<>();
    }

    // 菜单
    public void showMenu() {
        System.out.print("""

                █▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█
                █    ▄▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄   ▄▄       ▄▄   ▄▄▄▄▄▄▄▄▄▄▄    █
                █   ▐░░░░░░░░░░░▌ ▐░░░░░░░░░░░▌ ▐░░▌     ▐░░▌ ▐░░░░░░░░░░░▌   █
                █   ▐░█▀▀▀▀▀▀▀▀▀   ▀▀▀▀█░█▀▀▀▀  ▐░▌░▌   ▐░▐░▌ ▐░█▀▀▀▀▀▀▀▀▀    █
                █   ▐░▌                ▐░▌      ▐░▌▐░▌ ▐░▌▐░▌ ▐░▌             █
                █   ▐░█▄▄▄▄▄▄▄▄▄       ▐░▌      ▐░▌ ▐░▐░▌ ▐░▌ ▐░█▄▄▄▄▄▄▄▄▄    █
                █   ▐░░░░░░░░░░░▌      ▐░▌      ▐░▌  ▐░▌  ▐░▌ ▐░░░░░░░░░░░▌   █
                █    ▀▀▀▀▀▀▀▀▀█░▌      ▐░▌      ▐░▌   ▀   ▐░▌  ▀▀▀▀▀▀▀▀▀█░▌   █
                █             ▐░▌      ▐░▌      ▐░▌       ▐░▌           ▐░▌   █
                █    ▄▄▄▄▄▄▄▄▄█░▌  ▄▄▄▄█░█▄▄▄▄  ▐░▌       ▐░▌  ▄▄▄▄▄▄▄▄▄█░▌   █
                █   ▐░░░░░░░░░░░▌ ▐░░░░░░░░░░░▌ ▐░▌       ▐░▌ ▐░░░░░░░░░░░▌   █
                █    ▀▀▀▀▀▀▀▀▀▀▀   ▀▀▀▀▀▀▀▀▀▀▀   ▀         ▀   ▀▀▀▀▀▀▀▀▀▀▀    █
                ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\s
                """);
        System.out.println("\n1. 查看学生信息");
        System.out.println("2. 添加学生信息");
        System.out.println("3. 修改学生信息");
        System.out.println("4. 删除学生信息");
        System.out.println("5. 查看学生构造方法");
        System.out.println("6. 退出系统");
        System.out.println("请输入您的功能对应的编号:");
    }

    // 查看所有学生信息
    public void findAllStudent() {
        if (!this.studentList.isEmpty()) {
            int count = 0;
            for (Student students : this.studentList) {
                System.out.println(count + " |" +
                        " 学号: " + students.getID() +
                        " 姓名:" + students.getName() +
                        " 年龄: " + students.getAge() +
                        " 地址: " + students.getAddress());
                count++;
            }
        } else {
            showError();
            System.out.println("列表为空");
        }
    }

    // 添加学生
    public void addStudent(int numberOfAdded) {
        for (int i = 0; i < numberOfAdded; i++) {
            // 接收信息
            Student temStu = new Student();
            System.out.println("请输入第" + (i + 1) + "个学生的信息");
            System.out.print("学号: ");
            if (scanner.hasNextInt()) {
                temStu.setID(scanner.nextInt());
            } else {
                System.out.println("请输入正确的格式!");
            }
            System.out.print("姓名: ");
            temStu.setName(scanner.next());
            System.out.print("年龄: ");
            temStu.setAge(scanner.nextInt());
            System.out.print("地址: ");
            temStu.setAddress(scanner.next());
            // 存入信息
            this.studentList.add(temStu);
            showOK();
        }
    }

//    public void addStudent(int id, String name, Integer age, String address) {
//        this.studentList.add(new Student(id, name, age, address));
//    }
//
//    public void addStudent(int id, String name, Integer age) {
//        this.studentList.add(new Student(id, name, age));
//    }
//
//    public void addStudent(int id, String name) {
//        this.studentList.add(new Student(id, name));
//    }
//    public void addStudent(Student student) {
//        this.studentList.add(student);
//    }

    // 删除学生信息
    public void deleteStudent(int stuID) {
        int index = this.searchStudent(stuID);
        if (index == -1) {
            showError();
            System.out.println("未找到该学生");
        } else {
            this.studentList.remove(index);
            showOK();
        }
    }

    public void deleteStudent(String stuName) {
        int index = this.searchStudent(stuName);
        if (index == -1) {
            showError();
            System.out.println("未找到该学生");
        } else {
            this.studentList.remove(index);
            showOK();
        }
    }


    // 修改学生信息
    public void updateStudent(int id) {
        int index = this.searchStudent(id);
        if (index == -1) {
            showError();
            System.out.println("未找到该学生");
        } else {
            showOK();
            Student student = this.studentList.get(index);
            System.out.println("旧信息为: ");
            System.out.println("学号: " + student.getID() + " 姓名: " + student.getName() + " 年龄: " + student.getAge() + " 地址: " + student.getAddress());
            System.out.print("请输入新的信息\n" +
                    "学号: ");
            student.setID(scanner.nextInt());
            System.out.print("\n姓名: ");
            student.setName(scanner.next());
            System.out.print("年龄: ");
            student.setAge(scanner.nextInt());
            System.out.print("地址: ");
            student.setAddress(scanner.next());
            showOK();
        }
    }
    public  void updateStudent(String name) {
        int index = this.searchStudent(name);
        if (index == -1) {
            showError();
            System.out.println("未找到该学生");
        } else {
            showOK();
            Student student = this.studentList.get(index);
            System.out.println("旧信息为: ");
            System.out.println("学号: " + student.getID() + " 姓名: " + student.getName() + " 年龄: " + student.getAge() + " 地址: " + student.getAddress());
            System.out.print("请输入新的信息\n" +
                    "学号: ");
            student.setID(scanner.nextInt());
            System.out.print("姓名: ");
            student.setName(scanner.next());
            System.out.print("年龄: ");
            student.setAge(scanner.nextInt());
            System.out.print("地址: ");
            student.setAddress(scanner.next());
            showOK();
        }
    }
    // 查看学生构造方法
    public void showCreateStudent() {
        System.out.println("""
                学号 int [not null]
                姓名 String [not null]
                年龄 Integer
                地址 String""");
    }

    // 查找学生
    public int searchStudent(int id) {
        Student student = new Student();
        Iterator<Student> iterator = this.studentList.iterator();
        int index = 0;
        boolean found = false;
        while (iterator.hasNext()) {
            student = iterator.next();
            if (student.getID() == id) {
                found = true;
                break;
            }
            index++;
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    public int searchStudent(String name) {
        Student student = new Student();
        Iterator<Student> iterator = this.studentList.iterator();
        int index = 0;
        boolean found = false;
        while (iterator.hasNext()) {
            student = iterator.next();
            if (student.getName().equals(name)) {
                found = true;
                break;
            }
            index++;
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }
    // 成功图标
    static private void showOK() {
        System.out.println("""

                ╔═╗╦╔═
                ║ ║╠╩╗
                ╚═╝╩ ╩
                """);
    }
    // 失败图标
    static private void showError() {
        System.out.println("""

                ╔═╗╦═╗╦═╗╔═╗╦═╗
                ║╣ ╠╦╝╠╦╝║ ║╠╦╝
                ╚═╝╩╚═╩╚═╚═╝╩╚═
                """);
    }
}
