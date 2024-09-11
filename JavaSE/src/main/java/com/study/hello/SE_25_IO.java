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
    public static final String PROPERTY = System.getProperty("user.dir");
    public static final String ABSOLUTE_PATH = PROPERTY + "\\JavaSE\\src\\main\\java\\com\\study\\hello\\file";
    public static final String RELATIVE_PATH = "./JavaSE/src/main/java/com/study/hello/file";

    public static void main(String[] args) throws IOException {
        // System.out.println("项目文件路径：" + PROPERTY);
        // System.out.println("文件绝对路径：" + ABSOLUTE_PATH);
        // System.out.println("文件相对路径：" + RELATIVE_PATH);
        // fis_fos();
        fr_fw();
    }

    /**
     * 文件字节输入流(FileInputStream)
     * - int read() 读取一个字节的数据，返回读取的字节数据，没有数据可读返回-1
     * - int read(byte b[]) 读取一个字节数组的数据，保存到字节数组中，返回保存到数组的数据长度，没有数据可读返回-1
     * - int read(byte b[], int off, int len) 读取一个字节数组的数据，保存指定区间的数据到字节数组中，返回保存到数组的数据长度，没有数据可读返回-1
     * <p>
     * 文件字节输出流(FileOutputStream)
     * - void write(int b) 写一个字节出去
     * - void write(byte b[]) 写一个字节数组出去
     * - void write(byte b[], int off, int len) 写一个字节数组的指定区间出去
     * <p>
     * 避免中文字符输出乱码
     * - 每次读取一个字节/字节数组，读取中文字符输出无法避免乱码问题。
     * - 解决办法：可以定义与文件一样大的字节数组读取，一次性读取完全部字节。
     * - 存在问题：如果文件过大，定义的字节数组可能引起内存溢出。
     */
    public static void fis_fos() throws IOException {
        // 创建文件字节输入流
        String inputPath = RELATIVE_PATH + "/copy_input.txt";
        File inputFile = new File(inputPath);
        if (!inputFile.exists()) {
            System.out.println("新建文件：" + inputFile.createNewFile());
        }
        // 通过文件对象创建字节输入流管道
        FileInputStream fisFile = new FileInputStream(inputFile);
        // 通过文件路径创建字节输入流管道
        // FileInputStream fisPath = new FileInputStream(inputPath);

        // 创建文件字节输出流
        String outputPath = RELATIVE_PATH + "/copy_output.txt";
        File outputFile = new File(outputPath);
        // 通过文件对象创建字节输出流管道，如果文件不存在，会创建该文件
        FileOutputStream fosFile = new FileOutputStream(outputFile);
        // 通过文件对象创建字节输出流管道，如果文件不存在，会创建该文件，可选择是否追加数据，默认覆盖
        // FileOutputStream fosFileAppend = new FileOutputStream(outputFile, true);
        // 通过文件路径创建字节输出流管道，如果文件不存在，会创建该文件
        // FileOutputStream fosPath = new FileOutputStream(outputPath);
        // 通过文件路径创建字节输出流管道，如果文件不存在，会创建该文件，可选择是否追加数据，默认覆盖
        // FileOutputStream fosPathAppend = new FileOutputStream(outputPath, true);

        // 下面3个读写方式，输入流一次就已经读完了，输入流后续读不到内容
        int len = 0;
        byte[] buf = new byte[8];
        // 读取一个字节的数据，返回读取的字节数据，没有数据可读返回-1
        while ((len = fisFile.read()) != -1) {
            //写一个字节出去
            fosFile.write(len);
            System.out.print((char) len); // 控制台为GBK编码，此处会产生乱码
        }
        // 读取一个字节数组的数据，保存到字节数组中，返回保存到数组的数据长度，没有数据可读返回-1
        while ((len = fisFile.read(buf)) != -1) {
            // 写一个字节数组出去，最后一次写出可能存在重复数据，因为存在(len < buf.length)的情况
            // fos.write(buf);
            fosFile.write(buf, 0, len);
            System.out.print(new String(buf, 0, len));
        }
        // 读取一个字节数组的数据，保存指定区间的数据到字节数组中，返回保存到数组的数据长度，没有数据可读返回-1
        while ((len = fisFile.read(buf, 0, buf.length)) != -1) {
            // 写一个字节数组的指定区间出去，写出读取长度避免存在重复数据
            fosFile.write(buf, 0, len);
            System.out.print(new String(buf, 0, len));
        }
        //关闭资源
        fisFile.close();
        fosFile.close();
    }

    /**
     * 文件字符输入流(FileReader)：每次读取一个字符/字符数组，读取中文字符不会出现乱码(如果代码文件编码一致)。
     * - int read() 读取一个字符的数据，返回读取的字符数据，没有数据可读返回-1
     * - int read(char cbuf[]) 读取一个字符数组的数据，保存到字符数组中，返回保存到数组的数据长度，没有数据可读返回-1
     * <p>
     * 文件字符输出流(FileWriter)
     * - void write(int c) 写一个字符出去
     * - void write(char cbuf[]) 写一个字符数组出去
     * - void write(char cbuf[], int off, int len) 写一个字符数组的指定区间出去
     * - void write(String str) 写一个字符串
     * - void write(String str, int off, int len) 写一个字符串的指定区间出去
     */
    public static void fr_fw() throws IOException {
        // 创建文件字符输入流
        String readerPath = RELATIVE_PATH + "/copy_reader.txt";
        File readerFile = new File(readerPath);
        if (!readerFile.exists()) {
            System.out.println("新建文件：" + readerFile.createNewFile());
        }
        // 通过文件对象创建字符输入流管道
        FileReader frFile = new FileReader(readerFile);
        // 通过文件路径创建字符输入流管道
        // FileReader frPath = new FileReader(readerPath);

        // 创建文件字符输出流
        String writerPath = RELATIVE_PATH + "/copy_writer.txt";
        File writerFile = new File(writerPath);
        // 通过文件对象创建字符输出流管道，如果文件不存在，会创建该文件
        FileWriter fwFile = new FileWriter(writerFile);
        // 通过文件对象创建字符输出流管道，如果文件不存在，会创建该文件，可选择是否追加数据，默认覆盖
        // FileWriter fwFileAppend = new FileWriter(writerFile, true);
        // 通过文件路径创建字符输出流管道，如果文件不存在，会创建该文件
        // FileWriter fwPath = new FileWriter(writerPath);
        // 通过文件路径创建字符输出流管道，如果文件不存在，会创建该文件，可选择是否追加数据，默认覆盖
        // FileWriter fwPathAppend = new FileWriter(writerPath, true);

        // 下面5个读写方式，输入流一次就已经读完了，输入流后续读不到内容
        int len = 0;
        char[] buf = new char[8];
        // 读取一个字符的数据，返回读取的字符数据，没有数据可读返回-1
        while ((len = frFile.read()) != -1) {
            // 写一个字符出去
            fwFile.write(len);
            System.out.print((char) len);
        }
        // 读取一个字符数组的数据，保存到字符数组中，返回保存到数组的数据长度，没有数据可读返回-1
        while ((len = frFile.read(buf)) != -1) {
            // 写一个字符数组出去，最后一次写出可能存在重复数据，因为存在(len < buf.length)的情况
            // fwFile.write(buf);
            fwFile.write(buf, 0, len);
            System.out.print(buf);
        }
        // 读取一个字符数组的数据，保存到字符数组中，返回保存到数组的数据长度，没有数据可读返回-1
        while ((len = frFile.read(buf)) != -1) {
            //写一个字符数组的指定区间出去
            fwFile.write(buf, 0, len);
            System.out.print(buf);
        }
        // 读取一个字符数组的数据，保存到字符数组中，返回保存到数组的数据长度，没有数据可读返回-1
        while ((len = frFile.read(buf)) != -1) {
            //写一个字符串
            String str = new String(buf, 0, len);
            fwFile.write(str);
            System.out.print(str);
        }
        // 读取一个字符数组的数据，保存到字符数组中，返回保存到数组的数据长度，没有数据可读返回-1
        while ((len = frFile.read(buf)) != -1) {
            //写一个字符串的指定区间出去
            String str = new String(buf, 0, len);
            fwFile.write(str, 0, str.length());
            System.out.print(str);
        }
        // FileWriter不使用close()或flush()就无法写入数据
        fwFile.flush();
        // 关闭资源
        frFile.close();
        fwFile.close();
    }


    /**
     * 流的关闭与刷新
     * - flush()刷新流，还可以继续写数据
     * - close()关闭流，释放资源，但是在关闭之前会先刷新流。一旦关闭，就不能再写数据
     * <p>
     * try-catch-finally
     * - finally：放在try-catch后面的，无论是正常执行还是异常执行代码，最后一定要执行，除非JVM退出。
     * - finally代码块是最终一定要执行的，可以在代码执行完毕的最后用于释放资源。
     * <p>
     * try-with-resource
     * - 另类的try-catch在try后面新增语法(资源)，程序执行或者报错会自动释放括号内的(资源)。
     * - 自动释放的资源需要实现Closeable/AutoCloseable接口的类对象
     * <p>
     * 文件拷贝
     * - 任何文件的底层都是字节，只要前后文件格式、编码一致没有任何问题，字节流可以拷贝一切文件数据
     * - (硬盘)							  (内存)							    (硬盘)
     * - 数据源----创建输入流对象----read()->字节数组->write()----创建输出流对象----目的地
     * <p>
     * 字节/字符流使用场景总结
     * - 字节流适合做一切文件数据的拷贝(音频，视频，文本)，但不适合读取中文内容输出。
     * - 字符流适合做文本文件的操作(读，写)。
     */
    public static void try_with_resource() throws IOException {
        /*try {
            //监视可能出现异常的代码！
        } catch (Exception e) {
            //处理异常
        } finally {
            //资源释放
        }*/

        // 自动释放资源、代码简洁(资源都是实现了Closeable/AutoCloseable接口的类对象)
        /*try (定义流对象) {
            //可能出现异常的代码！
        } catch (Exception e) {
            //处理异常
        }*/
    }



    /**
     * 转换流
     * - 字符输入转换流(InputStreamReader)：可以把字节输入流按照指定编码转换成字符输入流。
     * - InputStreamReader(InputStream in)字节输入流(InputStream) -> 字符输入流(Reader)：默认编码(几乎不用)
     * - InputStreamReader(InputStream in, String charsetName)字节输入流(InputStream) -> 字符输入流(Reader)：指定编码(重点)
     * <p>
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
