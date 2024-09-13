package com.study.hello;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

public class SE_27_Properties {
    public static void main(String[] args) {

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
