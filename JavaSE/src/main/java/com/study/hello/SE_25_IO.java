package com.study.hello;

import java.io.*;

/**
 * IO流：读写数据
 * - 输入input：将外部数据读到内存的过程，称之输入，负责读。
 * - 输出output：将内存数据写到外部的过程，称之输出，负责写。
 * <p>
 * IO流分类
 * - 字节流(抽象类)
 * - - InputStream(字节输入流)：以内存为基准，来自磁盘文件/网络中的数据以字节的形式读入到内存中去的流称为字节输入流。
 * - - 文件字节输入流(FileInputStream)(实现类)
 * - - 字节缓冲输入流(BufferedInputStream)(高级流)
 * - - 对象字节输入流(ObjectInputStream)：把磁盘中的对象数据恢复到内存的Java对象中。
 * - OutputStream(字节输出流)：以内存为基准，把内存中的数据以字节写出到磁盘文件或者网络中去的流称为字节输出流。
 * - - 文件字节输出流(FileOutputStream)(实现类)
 * - - 字节缓冲输出流(BufferedOutputStream)(高级流)
 * - - 对象字节输出流(ObjectOutputStream)：把对象数据存入到文件中去。
 * - - 字节打印流(PrintStream)：继承自字节输出流OutputStream，支持写字节数据的方法。
 * - 字符流(抽象类)
 * - - Reader(字符输入流)：以内存为基准，来自磁盘文件/网络中的数据以字符的形式读入到内存中去的流称为字符输入流。
 * - - 文件字符输入流(FileReader)(实现类)
 * - - 字符缓冲输入流(BufferedReader)(高级流)
 * - - 字符输入转换流(InputStreamReader)：可以把原始的字节流按照指定编码转换成字符输入流。
 * - Writer(字符输出流)：以内存为基准，把内存中的数据以字符写出到磁盘文件或者网络介质中去的流称为字符输出流。
 * - - 文件字符输出流(FileWriter)(实现类)
 * - - 字符缓冲输出流(BufferedWriter)(高级流)
 * - - 字符输出转换流(OutputStreamWriter)：可以把字节输出流按照指定编码转换成字符输出流。
 * - - 字符打印流(PrintWriter)：继承自字符输出流Writer，支持写字符数据出去。
 */
public class SE_25_IO {
    public static void main(String[] args) {

    }

    /**
     * 文件字节输入流(FileInputStream)
     * 避免中文字符输出乱码
     * - 每次读取一个字节/字节数组，读取中文字符输出无法避免乱码问题。
     * - 可以定义与文件一样大的字节数组读取，一次性读取完全部字节。
     * - 存在问题：如果文件过大，定义的字节数组可能引起内存溢出。
     * <p>
     * int read() 读取一个字节的数据，返回读取的字节数据，没有数据可读返回-1
     * int read(byte b[]) 读取一个字节数组的数据，保存到字节数组中，返回保存到数组的数据长度，没有数据可读返回-1
     * int read(byte b[], int off, int len) 读取一个字节数组的数据，保存指定区间的数据到字节数组中，返回保存到数组的数据长度，没有数据可读返回-1
     */
    public static void fis() throws IOException {
        String path = "./src/io/Fis.java";
        File file = new File(path);
        //通过文件对象创建字节输入流管道
        FileInputStream fisFile = new FileInputStream(file);
        //通过文件路径创建字节输入流管道
        //FileInputStream fisPath = new FileInputStream(path);

        int len = 0;
        byte[] buf = new byte[8];
        //读取一个字节的数据，返回读取的字节数据，没有数据可读返回-1
        while ((len = fisFile.read()) != -1) {
            System.out.print((char) len);
        }
        //读取一个字节数组的数据，保存到字节数组中，返回保存到数组的数据长度，没有数据可读返回-1
        while ((len = fisFile.read(buf)) != -1) {
            System.out.print(new String(buf, 0, len));
        }
        //读取一个字节数组的数据，保存指定区间的数据到字节数组中，返回保存到数组的数据长度，没有数据可读返回-1
        while ((len = fisFile.read(buf, 0, buf.length)) != -1) {
            System.out.print(new String(buf, 0, buf.length));
        }
        //关闭资源
        fisFile.close();
    }

    /**
     * 文件字节输出流(FileOutputStream)
     * void write(int b) 写一个字节出去
     * void write(byte b[]) 写一个字节数组出去
     * void write(byte b[], int off, int len) 写一个字节数组的指定区间出去
     */
    public static void fos() throws IOException {
        String path = "./src/io/copy.txt";
        File file = new File(path);
        //通过文件对象创建字节输出流管道，如果文件不存在，会创建该文件
        FileOutputStream fos_1 = new FileOutputStream(file);
        //通过文件对象创建字节输出流管道，如果文件不存在，会创建该文件，可选择是否追加数据，默认覆盖
        //FileOutputStream fos_2 = new FileOutputStream(file, true);
        //通过文件路径创建字节输出流管道，如果文件不存在，会创建该文件
        //FileOutputStream fos_3 = new FileOutputStream(path);
        //通过文件路径创建字节输出流管道，如果文件不存在，会创建该文件，可选择是否追加数据，默认覆盖
        //FileOutputStream fos_4 = new FileOutputStream(path, true);

        int len_copy = 0;
        byte[] buf_copy = new byte[8];
        //通过文件路径创建字节输入流管道
        FileInputStream fis = new FileInputStream("./src/io/Fis.java");
        FileOutputStream fos = fos_1;
        //读取一个字节的数据，返回读取的字节数据，没有数据可读返回-1
        while ((len_copy = fis.read()) != -1) {
            //写一个字节出去
            fos.write(len_copy);
        }
        //读取一个字节数组的数据，保存到字节数组中，返回保存到数组的数据长度，没有数据可读返回-1
        while ((len_copy = fis.read(buf_copy)) != -1) {
            //写一个字节数组出去，最后一次写出可能存在重复数据,因为存在(len_copy<buf_copy.length)的情况
            fos.write(buf_copy);
        }
        //读取一个字节数组的数据，保存指定区间的数据到字节数组中，返回保存到数组的数据长度，没有数据可读返回-1
        while ((len_copy = fis.read(buf_copy, 0, buf_copy.length)) != -1) {
            //写一个字节数组的指定区间出去
            fos.write(buf_copy, 0, len_copy);
        }
        //关闭资源
        fis.close();
        fos.close();
    }

    /**
     * 文件字符输入流(FileReader)
     * - 每次读取一个字符/字符数组，读取中文字符不会出现乱码(如果代码文件编码一致)。
     * <p>
     * int read() 读取一个字符的数据，返回读取的字符数据，没有数据可读返回-1
     * int read(char cbuf[]) 读取一个字符数组的数据，保存到字符数组中，返回保存到数组的数据长度，没有数据可读返回-1
     */
    public static void fr() throws IOException {
        String path = "./src/io/Fr.java";
        File file = new File(path);
        //通过文件对象创建字符输入流管道
        FileReader frFile = new FileReader(file);
        //通过文件路径创建字符输入流管道
        //FileReader frPath = new FileReader(path);

        int len = 0;
        char[] buf = new char[8];
        //读取一个字符的数据，返回读取的字符数据，没有数据可读返回-1
        while ((len = frFile.read()) != -1) {
            System.out.print((char) len);
        }
        //读取一个字符数组的数据，保存到字符数组中，返回保存到数组的数据长度，没有数据可读返回-1
        while ((len = frFile.read(buf)) != -1) {
            System.out.print(buf);
        }
        //关闭资源
        frFile.close();
    }

    /**
     * 文件字符输出流(FileWriter)
     * <p>
     * void write(int c) 写一个字符出去
     * void write(char cbuf[]) 写一个字符数组出去
     * void write(char cbuf[], int off, int len) 写一个字符数组的指定区间出去
     * void write(String str) 写一个字符串
     * void write(String str, int off, int len) 写一个字符串的指定区间出去
     */
    public static void fw() throws IOException {
        String path = "./src/io/copy.txt";
        File file = new File(path);
        //通过文件对象创建字符输出流管道，如果文件不存在，会创建该文件
        FileWriter fw_1 = new FileWriter(file);
        //通过文件对象创建字符输出流管道，如果文件不存在，会创建该文件，可选择是否追加数据，默认覆盖
        //FileWriter fw_2 = new FileWriter(file, true);
        //通过文件路径创建字符输出流管道，如果文件不存在，会创建该文件
        //FileWriter fw_3 = new FileWriter(path);
        //通过文件路径创建字符输出流管道，如果文件不存在，会创建该文件，可选择是否追加数据，默认覆盖
        //FileWriter fw_4 = new FileWriter(path, true);

        int len_copy = 0;
        char[] buf_copy = new char[8];
        //通过文件路径创建字符输入流管道
        FileReader fr = new FileReader("./src/io/Fr.java");
        FileWriter fw = fw_1;
        //读取一个字符的数据，返回读取的字符数据，没有数据可读返回-1
        while ((len_copy = fr.read()) != -1) {
            //写一个字符出去
            fw.write(len_copy);
        }
        //读取一个字符数组的数据，保存到字符数组中，返回保存到数组的数据长度，没有数据可读返回-1
        while ((len_copy = fr.read(buf_copy)) != -1) {
            //写一个字符数组出去，最后一次写出可能存在重复数据，因为存在(len_copy<buf_copy.length)的情况
            fw.write(buf_copy);
        }
        //读取一个字符数组的数据，保存到字符数组中，返回保存到数组的数据长度，没有数据可读返回-1
        while ((len_copy = fr.read(buf_copy)) != -1) {
            //写一个字符数组的指定区间出去
            fw.write(buf_copy, 0, len_copy);
        }
        //读取一个字符数组的数据，保存到字符数组中，返回保存到数组的数据长度，没有数据可读返回-1
        while ((len_copy = fr.read(buf_copy)) != -1) {
            //写一个字符串
            fw.write(new String(buf_copy, 0, len_copy));
        }
        //读取一个字符数组的数据，保存到字符数组中，返回保存到数组的数据长度，没有数据可读返回-1
        while ((len_copy = fr.read(buf_copy)) != -1) {
            String str = new String(buf_copy, 0, len_copy);
            //写一个字符串的指定区间出去
            fw.write(str, 0, str.length());
        }
        //FileWriter不使用close()或flush()就无法写入数据
        fw.flush();
        //关闭资源
        fw.close();
        fr.close();
    }

    /**
     * 转换流
     * - 字符输入转换流(InputStreamReader)：可以把字节输入流按照指定编码转换成字符输入流。
     * - InputStreamReader(InputStream in)字节输入流(InputStream) -> 字符输入流(Reader)：默认编码(几乎不用)
     * - InputStreamReader(InputStream in, String charsetName)字节输入流(InputStream) -> 字符输入流(Reader)：指定编码(重点)
     *
     * - 字符输出转换流(OutputStreamWriter)：可以把字节输出流按照指定编码转换成字符输出流。
     * - OutputStreamWriter(OutputStream out)字节输出流(OutputStream) -> 字符输入流(Writer)：默认编码(几乎不用)
     * - OutputStreamWriter(OutputStream out, String charsetName)字节输出流(OutputStream) -> 字符输入流(Writer)：指定编码(重点)
     */
    public static void isr_osw() throws IOException {
        //标准输入输出
        //System.in -> InputStream -> 键盘
        //System.out -> PrintStream -> FilterOutputStream-> OutputStream -> 显示器
        //public final static InputStream in = null;
        //public final static PrintStream out = null;
        //public final static PrintStream err = null;

        //字节输入流(InputStream) -> 字符输入流(Reader)：默认编码(几乎不用)
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //字节输入流(InputStream) -> 字符输入流(Reader)：指定编码(重点)
        BufferedReader inCharset = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        //字节输出流(OutputStream) -> 字符输入流(Writer)：默认编码(几乎不用)
        //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        //BufferedWriter err = new BufferedWriter(new OutputStreamWriter(System.err));
        //字节输出流(OutputStream) -> 字符输入流(Writer)：指定编码(重点)
        BufferedWriter outCharset = new BufferedWriter(new OutputStreamWriter(System.out, "UTF-8"));
        //读取键盘输入的一行内容，输出到显示器上
        outCharset.write(inCharset.readLine());//hello world!!!
        outCharset.flush();

        //关闭资源
        outCharset.close();
        inCharset.close();
    }

}
