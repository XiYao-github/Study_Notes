package com.study.hello;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * File
 * - File类的对象代表操作系统的文件(文件、文件夹)，File类在java.io.File包下，围绕文件的操作。
 * - File类提供了诸如：创建文件对象(文件)，获取文件信息(大小、修改时间)、删除文件、创建文件夹等功能。
 */
public class SE_24_File {
    public static final String PROPERTY = System.getProperty("user.dir");
    public static final String ABSOLUTE_PATH = PROPERTY + "\\JavaSE\\src\\main\\java\\com\\study\\hello\\file";
    public static final String RELATIVE_PATH = "./JavaSE/src/main/java/com/study/hello/file";

    public static void main(String[] args) throws IOException {
        // System.out.println("项目文件路径：" + PROPERTY);
        // System.out.println("文件绝对路径：" + ABSOLUTE_PATH);
        // System.out.println("文件相对路径：" + RELATIVE_PATH);
        fileErgodic();
    }

    /**
     * 创建文件对象：File对象可以定位文件和文件夹，封装的对象仅仅是一个路径名，这个路径可以是存在的，也可以是不存在的。
     * - File(File parent, String child) 根据父路径文件对象和子路径名字符串创建文件对象
     * - File(String pathname) 根据文件路径创建文件对象
     * - File(String parent, String child) 根据父路径名字符串和子路径名字符串创建文件对象
     */
    public static void newFile() throws IOException {
        // 绝对路径：从盘符开始
        File file = new File(ABSOLUTE_PATH);
        // 相对路径：不带盘符，默认直接到当前工程下的目录寻找文件(“./”表示当前目录，“../”表示上级目录，”/”开头：代表根目录)。
        File src = new File(RELATIVE_PATH);
        // 根据父路径文件对象和子路径名字符串创建文件对象
        System.out.println(new File(file, "a.txt").createNewFile());
        // 根据文件路径创建文件对象
        System.out.println(new File(ABSOLUTE_PATH + "/b.txt").createNewFile());
        // 根据父路径名字符串和子路径名字符串创建文件对象
        System.out.println(new File(ABSOLUTE_PATH, "c.txt").createNewFile());
    }

    /**
     * 文件方法
     */
    public static void fileMethod() throws IOException {
        File src = new File(RELATIVE_PATH);
        //boolean createNewFile()当且仅当具有此名称的文件尚不存在时，创建新的空文件
        System.out.println(new File(src, "a.txt").createNewFile());

        //static File createTempFile(String prefix, String suffix, File directory)
        //指定目录创建新的空文件，使用给定的前缀和后缀字符串生成其名称
        File file = File.createTempFile("abc", ".txt", src);
        System.out.println(file);

        //boolean delete()删除此抽象路径名表示的文件或目录
        System.out.println(file.delete());

        //boolean exists()测试此抽象路径名表示的文件或目录是否存在
        System.out.println(file.exists());
        System.out.println("-----------");

        //boolean mkdir()创建此抽象路径名指定的目录
        System.out.println(new File(RELATIVE_PATH + "/mkdir/").delete());
        System.out.println(new File(RELATIVE_PATH + "/mkdir/").mkdir());

        //boolean mkdirs()创建此抽象路径名指定的目录，包括任何必需但不存在的父目录
        System.out.println(new File(RELATIVE_PATH + "/m/k/d/i/r/s/").delete());
        System.out.println(new File(RELATIVE_PATH + "/m/k/d/i/r/s/").mkdirs());
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
        System.out.println(fileNew.delete());
    }

    /**
     * 文件遍历(目录)
     * - 当文件对象不存在或者代表文件时，返回null，当没有权限访问该文件夹时，返回null，当是一个空文件夹时，返回一个长度为0的数组。
     * - 当文件对象是一个有内容的文件夹时，将里面所有文件和文件夹的路径放在File数组中返回，包含隐藏文件。
     * - String[] list() 返回一个字符串数组，用于命名此抽象路径名表示的目录中的文件和目录
     * - String[] list(FilenameFilter filter) 返回一个字符串数组，用于命名由此抽象路径名表示的目录中的文件和目录，以满足指定的过滤器
     * - File[] listFiles() 返回一个抽象路径名数组，表示此抽象路径名表示的目录中的文件
     * - File[] listFiles(FileFilter filter) 返回一个抽象路径名数组，表示此抽象路径名表示的目录中满足指定过滤器的文件和目录
     * - File[] listFiles(FilenameFilter filter) 返回一个抽象路径名数组，表示此抽象路径名表示的目录中满足指定过滤器的文件和目录
     */
    public static void fileErgodic() throws IOException {
        File filePath = new File(ABSOLUTE_PATH);

        // 文件名称遍历(目录)
        String[] fileNameList = filePath.list();
        for (String fileName : fileNameList) {
            if (fileName.endsWith(".java")) {
                System.out.println("代码文件：" + fileName);
            } else if (fileName.endsWith(".txt")) {
                System.out.println("文本文件：" + fileName);
            } else {
                System.out.println("未能识别：" + fileName);
            }
        }
        System.out.println("----------");

        // 文件名称过滤遍历(目录)
        String[] fileNameFilterList = filePath.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        for (String fileName : fileNameFilterList) {
            System.out.println("文本文件：" + fileName);
        }
        System.out.println("----------");

        // 文件遍历(目录)
        File[] fileList = filePath.listFiles();
        for (File file : fileList) {
            if (file.isDirectory()) {
                System.out.println("目录：" + file.getName());
            } else if (file.isFile()) {
                if (file.getName().endsWith(".java")) {
                    System.out.println("代码文件：" + file.getName());
                } else if (file.getName().endsWith(".txt")) {
                    System.out.println("文本文件：" + file.getName());
                } else {
                    System.out.println("未能识别：" + file.getName());
                }
            } else {
                System.out.println("未能识别：" + file.getName());
            }
        }
        System.out.println("----------");

        // 文件过滤遍历(目录)
        File[] fileFilterList_1 = filePath.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        for (File file : fileFilterList_1) {
            if (file.getName().endsWith(".java")) {
                System.out.println("代码文件：" + file.getName());
            } else if (file.getName().endsWith(".txt")) {
                System.out.println("文本文件：" + file.getName());
            } else {
                System.out.println("未能识别：" + file.getName());
            }
        }
        System.out.println("----------");

        // 文件过滤遍历(目录)
        File[] fileFilterList_2 = filePath.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        for (File file : fileFilterList_2) {
            System.out.println("文本文件：" + file.getName());
        }
    }
}
