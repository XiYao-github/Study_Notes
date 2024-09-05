package com.study.hello;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * File
 * - File类的对象代表操作系统的文件(文件、文件夹)，File类在java.io.File包下，围绕文件的操作。
 * - File类提供了诸如：创建文件对象(文件)，获取文件信息(大小、修改时间)、删除文件、创建文件夹等功能。
 */
public class SE_24_File {
    public static void main(String[] args) {

    }

    /**
     * 创建文件对象：File对象可以定位文件和文件夹，封装的对象仅仅是一个路径名，这个路径可以是存在的，也可以是不存在的。
     */
    public static void newFile() throws IOException {
        //绝对路径：从盘符开始
        File file = new File("C:/Users/XiYao/IdeaProjects/JavaSE/src/io");
        //相对路径：不带盘符，默认直接到当前工程下的目录寻找文件。
        File src = new File("./src/io");
        //根据父路径文件对象和子路径名字符串创建文件对象
        new File(file, "a.txt").createNewFile();
        //根据文件路径创建文件对象
        new File("C:/Users/XiYao/IdeaProjects/JavaSE/src/io/b.txt").createNewFile();
        //根据父路径名字符串和子路径名字符串创建文件对象
        new File("./src/io", "c.txt").createNewFile();
    }

    /**
     * 文件方法
     */
    public static void fileMethod() throws IOException {
        File src = new File("./src/io");
        //boolean createNewFile()当且仅当具有此名称的文件尚不存在时，创建新的空文件
        System.out.println(new File(src, "a.txt").createNewFile());

        //static File createTempFile(String prefix, String suffix, File directory)
        //指定目录创建新的空文件，使用给定的前缀和后缀字符串生成其名称，注意：(prefix.length() < 3)
        File file = File.createTempFile("123", ".txt", src);
        System.out.println(file);

        //boolean delete()删除此抽象路径名表示的文件或目录
        System.out.println(file.delete());

        //boolean exists()测试此抽象路径名表示的文件或目录是否存在
        System.out.println(file.exists());
        System.out.println("-----------");

        //boolean mkdir()创建此抽象路径名指定的目录
        System.out.println(new File("./src/io/mkdir/").delete());
        System.out.println(new File("./src/io/mkdir/").mkdir());

        //boolean mkdirs()创建此抽象路径名指定的目录，包括任何必需但不存在的父目录
        System.out.println(new File("./src/io/m/k/d/i/r/s/").delete());
        System.out.println(new File("./src/io/m/k/d/i/r/s/").mkdirs());
        System.out.println("-----------");

        //long lastModified()返回上次修改此抽象路径名表示的文件的时间
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date(src.lastModified())));

        //long length()返回此抽象路径名表示的文件的长度
        System.out.println(src.length());
        System.out.println("---------------------------------------------");

        //File getAbsoluteFile()返回此抽象路径名的绝对形式
        File fileNew = File.createTempFile("test", ".txt", src);
        File absoluteFile = fileNew.getAbsoluteFile();
        System.out.println(absoluteFile);

        //String getAbsolutePath()返回此抽象路径名的绝对路径名字符串
        System.out.println(fileNew.getAbsolutePath());

        //String getName()返回此抽象路径名表示的文件或目录的名称
        System.out.println(fileNew.getName());

        //String getParent()返回此抽象路径名父项的路径名字符串，如果此路径名未指定父目录，则返回 null
        System.out.println(fileNew.getParent());

        //File getParentFile()返回此抽象路径名父项的抽象路径名，如果此路径名未指定父目录，则返回 null
        File parentFile = fileNew.getParentFile();
        System.out.println(parentFile);

        //String getPath()将此抽象路径名转换为路径名字符串
        System.out.println(fileNew.getPath());
        System.out.println("-----------");

        //boolean isAbsolute()测试此抽象路径名是否为绝对路径
        System.out.println(fileNew.isAbsolute());

        //boolean isDirectory()测试此抽象路径名表示的文件是否为目录
        System.out.println(fileNew.isDirectory());

        //boolean isFile()测试此抽象路径名表示的文件是否为普通文件
        System.out.println(fileNew.isFile());

        //boolean isHidden()测试此抽象路径名指定的文件是否为隐藏文件
        System.out.println(fileNew.isHidden());
        fileNew.delete();
    }

    /**
     * 文件遍历(目录)
     * - 当文件对象不存在或者代表文件时，返回null，当没有权限访问该文件夹时，返回null。
     * - 当文件对象是一个空文件夹时，返回一个长度为0的数组。
     * - 当文件对象是一个有内容的文件夹时，将里面所有文件和文件夹的路径放在File数组中返回。
     * - 当文件对象是一个有隐藏文件的文件夹时，将里面所有文件和文件夹的路径放在File数组中返回，包含隐藏文件。
     */
    public static void fileErgodic() throws IOException {
        File file = new File("C:/Users/XiYao/IdeaProjects/JavaSE/src/io");

        //File遍历(目录)
        String[] list_1 = file.list();
        for (String s : list_1) {
            if (s.endsWith(".java")) {
                System.out.println("代码：" + s);
            } else if (s.endsWith(".txt")) {
                System.out.println("文件：" + s);
            } else {
                System.out.println("目录：" + s);
            }
        }
        System.out.println("----------");

        String[] list_2 = file.list((dir, name) -> name.endsWith(".java"));
        for (String s : list_2) {
            System.out.println("代码：" + s);
        }
        System.out.println("----------");

        File[] files_1 = file.listFiles();
        for (File f : files_1) {
            if (f.isDirectory()) {
                System.out.println("目录：" + f.getName());
            } else if (f.isFile()) {
                if (f.getName().endsWith(".java")) {
                    System.out.println("代码：" + f.getName());
                } else {
                    System.out.println("文件：" + f.getName());
                }
            } else {
                System.out.println("未能识别");
            }
        }
        System.out.println("----------");

        File[] files_2 = file.listFiles(pathname -> pathname.isFile());
        for (File f : files_2) {
            if (f.getName().endsWith(".java")) {
                System.out.println("代码：" + f.getName());
            } else {
                System.out.println("文件：" + f.getName());
            }
        }
        System.out.println("----------");

        File[] files_3 = file.listFiles((dir, name) -> name.endsWith(".java"));
        for (File f : files_3) {
            System.out.println("代码：" + f.getName());
        }
    }
}
