package student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StudentManager {
    public static void main(String[] args) {
        ArrayList<Student> array = new ArrayList<>();
        while (true) {
            System.out.println("----欢迎来到学生管理系统---");
            System.out.println("1 添加学生");
            System.out.println("2 删除学生");
            System.out.println("3 修改学生");
            System.out.println("4 插入学生");
            System.out.println("5 查看所有学生");
            System.out.println("6 学生信息按学号进行排序");
            System.out.println("7 统计学生总人数");
            System.out.println("8 退出");
            System.out.println("请输入你的选择：");

            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            switch (line) {
                case "1" -> {
                    System.out.println("添加学生");
                    addStudent(array);
                }
                case "2" -> {
                    System.out.println("删除学生");
                    deleteStudent(array);
                }
                case "3" -> {
                    System.out.println("修改学生");
                    updateStudent(array);
                }
                case "4" -> {
                    System.out.println("插入学生");
                    InsertStudent(array);
                }
                case "5" -> {
                    System.out.println("查看所有学生");
                    findAllStudent(array);
                }
                case "6" -> {
                    System.out.println("学生信息按学号进行排序");
                    SortStudent(array);
                }
                case "7" -> {
                    System.out.println("统计学生总人数");
                    SumStudent(array);
                }
                case "8" -> {
                    System.out.println("谢谢使用");
                    System.exit(0);//JVM退出
                }
            }
        }
    }
    //添加学生
    public static void addStudent(ArrayList<Student> array) {
        Scanner sc = new Scanner(System.in);
        String sid;
        while (true) {
            System.out.println("请输入学生学号：");
            sid = sc.nextLine();
            boolean flags = isUserd(array, sid);
            if (flags) {
                System.out.println("你输入的学号已经被使用，请重新输入！");
            } else {
                break;
            }
        }
        System.out.println("请输入学生姓名：");
        String name = sc.nextLine();
        System.out.println("请输入学生年龄：");
        String age = sc.nextLine();
        System.out.println("请输入学生居住地：");
        String address = sc.nextLine();

        Student s = new Student();
        s.setSid(sid);
        s.setName(name);
        s.setAge(age);
        s.setAddress(address);
        //将学生对象添加到集合中
        array.add(s);
        System.out.println("添加学生成功！");
    }
    //查看学生
    public static void findAllStudent(ArrayList<Student> array) {
        if (array.size() == 0) {
            System.out.println("无信息，请添加信息再执行");
            return;
        }
        System.out.println("学号\t\t\t姓名\t\t年龄\t\t居住地");
        for (Student s : array) {
            System.out.println(s.getSid() + "\t\t" + s.getName() + "\t" + s.getAge() + "岁\t\t" + s.getAddress());
        }
        System.out.println("学生信息打印成功！");
    }
    //删除学生
    public static void deleteStudent(ArrayList<Student> array) {
        Scanner sc = new Scanner(System.in);
        int index = -1;
        System.out.println("请输入你要删除的学生的学号：");
        String sid = sc.nextLine();
        for (int i = 0; i < array.size(); ++i) {
            Student s = array.get(i);
            //判断两个字符串是否一致
            if (s.getSid().equals(sid)) {
                array.remove(i); //把这个学生对象移除掉
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("该学生不存在，请重新输入！");
        }
        System.out.println("学生信息删除成功！");
    }
    //修改学生
    public static void updateStudent(ArrayList<Student> array) {
        if (array.size() == 0) {
            System.out.println("无信息，请添加信息再执行");
            return;
        }
        Scanner sc = new Scanner(System.in);
        boolean flags;
        String sid;
        while (true) {
            System.out.println("请输入你要修改的学生的学号：");
            sid = sc.nextLine();
            flags = isUserd(array, sid);
            if (flags) {
                System.out.println("该学生不存在，请重新输入！");
            } else {
                break;
            }
        }
        System.out.println("请输入学生新姓名：");
        String name = sc.nextLine();
        System.out.println("请输入学生新年龄：");
        String age = sc.nextLine();
        System.out.println("请输入学生新居住地：");
        String address = sc.nextLine();
        Student temp = new Student();
        temp.setName(name);
        temp.setAge(age);
        temp.setAddress(address);

        for (int i = 0; i < array.size(); ++i) {
            Student s = array.get(i);
            if (s.getSid().equals(sid)) {
                //把i位置的学生对象修改为新的temp学生对象
                array.set(i, temp);
                break;
            }
        }
        System.out.println("学生信息修改成功");
    }
    //插入学生
    public static void InsertStudent(ArrayList<Student> array) {
        Scanner sc = new Scanner(System.in);
        String sid;
        boolean flags;
        while (true) {
            System.out.println("请输入学生学号：");
            sid = sc.nextLine();
            flags = isUserd(array, sid);
            if (flags) {
                System.out.println("你输入的学号已经被使用，请重新输入！");
            } else {
                break;
            }
        }
        System.out.println("请输入学生姓名：");
        String name = sc.nextLine();
        System.out.println("请输入学生年龄：");
        String age = sc.nextLine();
        System.out.println("请输入学生居住地：");
        String address = sc.nextLine();

        Student s = new Student();
        s.setSid(sid);
        s.setName(name);
        s.setAge(age);
        s.setAddress(address);

        flags = true;
        int position = 0; //插入的位置
        //判断插入位置是否有误
        while (flags) {
            System.out.println("请输入插入的位置：");
            position = sc.nextInt();
            if (position >= 1 && position <= array.size() + 1) {
                //进来，说明插入位置无误
                flags = false;
            }
            if(flags==true){
                System.out.println("插入位置有误，请重新输入！");
            }
        }
        int index = position - 1; //插入位置的下标
        //函数重载 喵的
        array.add(index,s);
        System.out.println("插入学生成功！");
    }
    //对学生信息进行排序
    public static void SortStudent(ArrayList<Student>array){
        if (array.size() == 0) {
            System.out.println("无信息，请添加信息再执行");
            return;
        }
        //sort(List<T> list,Comparator<super T>：将集合中的元素按照指定规则排序
        //重写了Comparator方法
        Collections.sort(array, new Comparator<Student>(){
            public int compare(Student o1, Student o2) {
                //return o2.getSid().compareTo(o1.getSid()); //降序
                return o1.getSid().compareTo(o2.getSid());//升序
            }
        });
        findAllStudent(array);
        System.out.println("学生信息按学号进行排序成功！");
    }
    public static  void SumStudent(ArrayList<Student>array){
        if (array.size() == 0) {
            System.out.println("无信息，请添加信息再执行");
            return;
        }
        System.out.println("系统学生总人数为："+array.size()+"!");
    }
    //判断学号是否重复
    public static boolean isUserd(ArrayList<Student> array, String sid) {
        boolean flags = false;
        for (Student temp : array) {
            if (temp.getSid().equals(sid)) {
                flags = true;
                break;
            }
        }
        return flags;
    }

}

