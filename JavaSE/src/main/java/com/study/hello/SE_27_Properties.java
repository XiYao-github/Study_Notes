package com.study.hello;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

public class SE_27_Properties {
    public static void main(String[] args) {

    }

    /**
     * PrintStream(字节打印流)
     * PrintWriter(字符打印流)
     */
    public static void ps_pw() throws IOException {
		/*String path = "./src/io/Print.txt";
		File file = new File(path);
		FileOutputStream fos = new FileOutputStream(file);
		FileWriter fw = new FileWriter(file);

		//打印流直接通向文件对象
		PrintStream ps_1 = new PrintStream(file);
		//打印流直接通向文件对象，指定字符集
		PrintStream ps_2 = new PrintStream(file, "UTF-8");
		//打印流直接通向字节输出流管道
		PrintStream ps_3 = new PrintStream(fos);
		//打印流直接通向字节输出流管道，可选择是否自动刷新
		PrintStream ps_4 = new PrintStream(fos, true);
		//打印流直接通向字节输出流管道，可选择是否自动刷新，指定字符集
		PrintStream ps_5 = new PrintStream(fos, true, "UTF-8");
		//打印流直接通向文件路径
		PrintStream ps_6 = new PrintStream(path);
		//打印流直接通向文件路径，指定字符集
		PrintStream ps_7 = new PrintStream(path, "UTF-8");


		//打印流直接通向文件对象
		PrintWriter pw_1 = new PrintWriter(file);
		//打印流直接通向文件对象，指定字符集
		PrintWriter pw_2 = new PrintWriter(file, "UTF-8");
		//打印流直接通向字节输出流管道
		PrintWriter pw_3 = new PrintWriter(fos);
		//打印流直接通向字节输出流管道，可选择是否自动刷新
		PrintWriter pw_4 = new PrintWriter(fos, true);
		//打印流直接通向文件路径
		PrintWriter pw_5 = new PrintWriter(path);
		//打印流直接通向文件路径，指定字符集
		PrintWriter pw_6 = new PrintWriter(path, "UTF-8");
		//打印流直接通向字符输出流管道
		PrintWriter pw_7 = new PrintWriter(fw);
		//打印流直接通向字符输出流管道，可选择是否自动刷新
		PrintWriter pw_8 = new PrintWriter(fw, true);*/

        //打印输出
        PrintStream ps = new PrintStream(System.out, true);
        PrintWriter pw = new PrintWriter(ps, true);
        ps.printf("我叫%s,性别%c,今年%d岁,期末考试成绩是%.2f分！！！", "张三", '男', 18, 66.579);
        ps.println();
        pw.printf("我叫%s,性别%c,今年%d岁,期末考试成绩是%.2f分！！！", "李四", '男', 20, 99.579);
        //关闭资源
        pw.close();
        ps.close();
    }

    /**
     * Properties
     * - Properties代表的是一个属性文件，可以把自己对象中的键值对信息存入到一个属性文件中去。
     * - 属性文件：后缀是.properties结尾的文件，里面的内容都是key=value，后续做系统配置信息的。
     */
    public static void properties() throws IOException {
        //创建一个没有默认值的空属性列表
        Properties properties = new Properties();
        //创建具有指定默认值的空属性列表
        Properties propertiesNew = new Properties(properties);
        //从字节输入流中读取元素(键值对)
        properties.load(new FileInputStream("./src/io/mysql.properties"));
        //从字符输入流中读取元素(键值对)
        properties.load(new FileReader("./src/io/mysql.properties"));
        //搜索指定键的值，没有返回null
        System.out.println(properties.getProperty("key"));
        //搜索指定键的值，没有返回指定默认值
        System.out.println(properties.getProperty("KK", "VV"));
        //将元素(键值对)打印到字节打印流中
        PrintStream ps = new PrintStream(System.out, true);
        properties.list(ps);
        //将元素(键值对)打印到字符打印流中
        PrintWriter pw = new PrintWriter(System.out, true);
        properties.list(pw);
        //调用父类Hashtable的put()，存在相同键就替换值，不存在就添加元素(键值对)
        properties.setProperty("name", "张三");//中文保存的是对于的unicode编码
        //将元素(键值对)写入到字节输出中，comments：注释
        properties.store(new FileOutputStream("./src/io/mysql.properties"), "注释");//中文保存的是对于的unicode编码
        properties.store(new FileWriter("./src/io/mysql.properties"), "注释");//中文保存的是对于的unicode编码

        //返回此属性列表中所有键的枚举
        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }

        //返回此属性列表中所有键的枚举Set集合
        Set<String> set = properties.stringPropertyNames();
        for (String s : set) {
            System.out.println(s + "=" + properties.get(s));
        }
    }
}
