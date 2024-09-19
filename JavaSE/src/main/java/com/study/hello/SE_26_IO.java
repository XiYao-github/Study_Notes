package com.study.hello;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

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
public class SE_26_IO {
    public static final String PROPERTY = System.getProperty("user.dir");
    public static final String ABSOLUTE_PATH = PROPERTY + "\\JavaSE\\src\\main\\java\\com\\study\\hello\\file";
    public static final String RELATIVE_PATH = "./JavaSE/src/main/java/com/study/hello/file";

    public static void main(String[] args) throws IOException {
        // System.out.println("项目文件路径：" + PROPERTY);
        // System.out.println("文件绝对路径：" + ABSOLUTE_PATH);
        // System.out.println("文件相对路径：" + RELATIVE_PATH);
        // fis_fos();
        // bis_bos();
        // fr_fw();
        // br_bw();
        // ps_pw();
        // isr_osw();
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
     * - 文件方法：File.length() 获取文件长度创建字节数组，但是文件很大(long)，字节数组很小(int)，只能用于小文件。
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
            System.out.print((char) len); // 汉字存储需要3个字节，此时将一个字节转换为字符，会产生乱码
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
    public static void try_with_resource() {
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
     * 缓冲流
     * - 缓冲流也称为高效流(高级流)，字节流和字符流可以称为原始流(低级流)。
     * - 作用：缓冲流自带缓冲区(8KB)、可以提高原始字节流、字符流读写数据的性能。
     * <p>
     * 字节缓冲流
     * - 字节缓冲输入流(BufferedInputStream)：直接从缓冲池读取数据，所以性能较好。
     * - 字节缓冲输出流(BufferedOutputStream)：数据就直接写入到缓冲池中去，写数据性能极高了。
     */
    public static void bis_bos() throws IOException {
        // 通过文件路径创建字节输入流管道
        FileInputStream fis = new FileInputStream(RELATIVE_PATH + "/copy_input.txt");
        // 创建默认字节缓冲输入流,默认：byte buf[] = new byte[8192]
        BufferedInputStream bis = new BufferedInputStream(fis);
        //创建指定缓冲区大小的字节缓冲输入流
        // BufferedInputStream bisSize = new BufferedInputStream(fis, 1024);

        // 通过文件路径创建字节输出流管道
        FileOutputStream fos = new FileOutputStream(RELATIVE_PATH + "/copy_output.txt");
        // 创建默认字节缓冲输出流,默认：byte buf[] = new byte[8192]
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        // 创建指定缓冲区大小的字节缓冲输出流
        // BufferedOutputStream bosSize = new BufferedOutputStream(fos, 1024);

        System.out.println("字节流：" + byteStream(fis, fos));
        System.out.println("缓冲流：" + byteBufferStream(bis, bos));
        // 字节缓冲流和字节流使用字节数组读取相差不大
        byte[] buf = new byte[1024];
        System.out.println("字节流(数组)：" + byteArrayStream(fis, fos, buf));

        // 关闭资源，先关闭外部流，再关内部流
        bos.close();
        fos.close();
        bis.close();
        fis.close();
    }

    private static long byteBufferStream(BufferedInputStream bis, BufferedOutputStream bos) throws IOException {
        long start = System.currentTimeMillis();
        int len;
        while ((len = bis.read()) != -1) {
            bos.write(len);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long byteStream(FileInputStream fis, FileOutputStream fos) throws IOException {
        long start = System.currentTimeMillis();
        int len;
        while ((len = fis.read()) != -1) {
            fos.write(len);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long byteArrayStream(FileInputStream fis, FileOutputStream fos, byte[] buf) throws IOException {
        long start = System.currentTimeMillis();
        int len;
        while ((len = fis.read(buf, 0, buf.length)) != -1) {
            fos.write(buf, 0, len);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    /**
     * 字符缓冲流
     * - 字符缓冲输入流(BufferedReader)：提高字符输入流读取数据的性能，除此之外多了按照行读取数据的功能。
     * - 新增方法：String readLine() 读取一行文字，不包含终止符，没有数据可读返回null。
     * <p>
     * - 字符缓冲输出流(BufferedWriter)：提高字符输出流写取数据的性能，除此之外多了换行功能。
     * - 新增方法：void newLine() 写入一行行分隔符，分隔符由系统决定，不同系统分隔符不同。
     */
    public static void br_bw() throws IOException {
		/*
			// FileWriter不使用close()或flush()就无法写入数据
			// 疑惑：下列代码没有刷新和关闭也写进去了
			FileReader fr = new FileReader("./src/io/a.txt");
			FileWriter fw = new FileWriter("./src/io/a_copy.txt",true);
			int len;
			while ((len = fr.read()) != -1) {
				fw.write(len);
			}
		 */

        // 通过文件路径创建字符输入流管道
        FileReader fr = new FileReader(RELATIVE_PATH + "/copy_reader.txt");
        // 创建默认字符缓冲输入流,默认：char cb[] = new char[8192]
        BufferedReader br = new BufferedReader(fr);
        // 创建指定缓冲区大小的字符缓冲输入流
        BufferedReader brSize = new BufferedReader(fr, 1024);

        // 通过文件路径创建字符输出流管道
        FileWriter fw = new FileWriter(RELATIVE_PATH + "/copy_writer.txt");
        // 创建默认字符缓冲输出流,默认：char cb[] = new char[8192]
        BufferedWriter bw = new BufferedWriter(fw);
        // 创建指定缓冲区大小的字符缓冲输出流
        BufferedWriter bwSize = new BufferedWriter(fw, 1024);

        System.out.println("字符流：" + charStream(fr, fw));
        System.out.println("缓冲流：" + charBufferStream(br, bw));
        // 字符缓冲流和字符流使用字符数组读取相差不大
        char[] ch = new char[1024];
        System.out.println("字符流(数组)：" + charArrayStream(fr, fw, ch));

        // 关闭资源，先关闭外部流，再关内部流
        bw.close();
        fw.close();
        br.close();
        fr.close();
    }

    private static long charBufferStream(BufferedReader br, BufferedWriter bw) throws IOException {
        long start = System.currentTimeMillis();
        String line;
        // String readLine() 读取一行文字，不包含终止符，没有数据可读返回null
        while ((line = br.readLine()) != null) {
            bw.write(line);
            // void newLine() 写入一行行分隔符，分隔符由系统决定，不同系统分隔符不同
            bw.newLine();
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long charStream(FileReader fr, FileWriter fw) throws IOException {
        long start = System.currentTimeMillis();
        int len;
        while ((len = fr.read()) != -1) {
            fw.write(len);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long charArrayStream(FileReader fr, FileWriter fw, char[] ch) throws IOException {
        long start = System.currentTimeMillis();
        int len;
        while ((len = fr.read(ch)) != -1) {
            fw.write(ch, 0, len);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    /**
     * 打印流
     * 字节打印流(PrintStream)：继承自字节输出流OutputStream，支持写字节数据的方法。
     * - void print(Object obj) 打印任意类型的数据出去
     * - void println(Object x) 打印任意类型的数据出去并换行
     * <p>
     * 字符打印流(PrintWriter)：继承自字符输出流Writer，支持写字符数据出去。
     * - void print(Object obj) 打印任意类型的数据出去
     * - void println(Object x) 打印任意类型的数据出去并换行
     */
    public static void ps_pw() throws IOException {
        /*String path = RELATIVE_PATH + "/print.txt";
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);
        FileWriter fw = new FileWriter(file);

        // 打印流直接通向文件对象
        PrintStream ps_1 = new PrintStream(file);
        // 打印流直接通向文件对象，指定字符集
        PrintStream ps_2 = new PrintStream(file, "UTF-8");
        // 打印流直接通向字节输出流管道
        PrintStream ps_3 = new PrintStream(fos);
        // 打印流直接通向字节输出流管道，可选择是否自动刷新
        PrintStream ps_4 = new PrintStream(fos, true);
        // 打印流直接通向字节输出流管道，可选择是否自动刷新，指定字符集
        PrintStream ps_5 = new PrintStream(fos, true, "UTF-8");
        // 打印流直接通向文件路径
        PrintStream ps_6 = new PrintStream(path);
        // 打印流直接通向文件路径，指定字符集
        PrintStream ps_7 = new PrintStream(path, "UTF-8");


        // 打印流直接通向文件对象
        // 高级流是不能追加写入的，追加写入需要低级流fos添加可追加属性
        // PrintWriter pw = new PrintWriter(new FileOutputStream(file, true), true);
        PrintWriter pw_1 = new PrintWriter(file);
        // 打印流直接通向文件对象，指定字符集
        PrintWriter pw_2 = new PrintWriter(file, "UTF-8");
        // 打印流直接通向字节输出流管道
        PrintWriter pw_3 = new PrintWriter(fos);
        // 打印流直接通向字节输出流管道，可选择是否自动刷新
        PrintWriter pw_4 = new PrintWriter(fos, true);
        // 打印流直接通向文件路径
        PrintWriter pw_5 = new PrintWriter(path);
        // 打印流直接通向文件路径，指定字符集
        PrintWriter pw_6 = new PrintWriter(path, "UTF-8");
        // 打印流直接通向字符输出流管道
        PrintWriter pw_7 = new PrintWriter(fw);
        // 打印流直接通向字符输出流管道，可选择是否自动刷新
        PrintWriter pw_8 = new PrintWriter(fw, true);*/

        // 打印输出
        PrintStream ps = new PrintStream(System.out, true);
        ps.printf("我叫%s，性别%c，今年%d岁，期末考试成绩是%.2f分！！！", "张三", '男', 18, 66.579);
        ps.println();
        PrintWriter pw = new PrintWriter(ps, true);
        pw.printf("我叫%s，性别%c，今年%d岁，期末考试成绩是%.2f分！！！", "李四", '男', 20, 99.579);
        pw.println();
        // 关闭资源
        pw.close();
        ps.close();
    }

    /**
     * 转换流
     * - 字符输入转换流(InputStreamReader)：可以把字节输入流按照指定编码转换成字符输入流。
     * - InputStreamReader(InputStream in) 字节输入流(InputStream) -> 字符输入流(Reader)：默认编码(几乎不用)
     * - InputStreamReader(InputStream in, String charsetName) 字节输入流(InputStream) -> 字符输入流(Reader)：指定编码(重点)
     * <p>
     * - 字符输出转换流(OutputStreamWriter)：可以把字节输出流按照指定编码转换成字符输出流。
     * - OutputStreamWriter(OutputStream out) 字节输出流(OutputStream) -> 字符输入流(Writer)：默认编码(几乎不用)
     * - OutputStreamWriter(OutputStream out, String charsetName) 字节输出流(OutputStream) -> 字符输入流(Writer)：指定编码(重点)
     */
    public static void isr_osw() throws IOException {
        // 标准输入输出
        // System.in -> InputStream -> 键盘
        // System.out -> PrintStream -> FilterOutputStream-> OutputStream -> 显示器
        // public final static InputStream in = null;
        // public final static PrintStream out = null;
        // public final static PrintStream err = null;
        // 使用示例
        // byte[] buf = new byte[1024];
        // int len = System.in.read(buf);
        // String str = new String(buf, 0, len, StandardCharsets.UTF_8);
        // System.out.print(str);

        // 字节输入流(InputStream) -> 字符输入流(Reader)：默认编码(几乎不用)
        // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // 字节输入流(InputStream) -> 字符输入流(Reader)：指定编码(重点)
        BufferedReader inCharset = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        // 字节输出流(OutputStream) -> 字符输入流(Writer)：默认编码(几乎不用)
        // BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        // BufferedWriter err = new BufferedWriter(new OutputStreamWriter(System.err));
        // 字节输出流(OutputStream) -> 字符输入流(Writer)：指定编码(重点)
        BufferedWriter outCharset = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        // 读取键盘输入的一行内容，输出到显示器上
        String line = inCharset.readLine();
        outCharset.write(line);
        outCharset.flush();
        // 关闭资源
        outCharset.close();
        inCharset.close();
    }

    /**
     * 序列化
     * 对象字节输出流(ObjectOutputStream)
     * - 将内存中的Java对象存储到磁盘文件中，称为对象序列化，对象必须实现序列化接口java.io.Serializable。
     * - 可以通过序列化实现对象的持久存储，如果是网络套接字流，则可以在另一个主机或另一个进程中重新构建对象。
     * - 序列化对象时，要求对象的属性都需要实现序列化，除非有transient/static修饰字段。
     * - 序列化具有继承性，如果父类实现了序列化，则它的子类也默认实现了序列化。
     * - ObjectOutputStream(OutputStream out) 提供序列化功能
     * - final void writeObject(Object obj) 序列化方法(保存对象数据的类型和值)：将指定对象写到对象字节输出流的文件中
     * <p>
     * 对象字节输入流(ObjectInputStream)
     * - 将存储到磁盘文件中的对象数据恢复成内存中的Java对象，称为对象反序列化，对象必须实现序列化接口java.io.Serializable。
     * - 反序列化对象时，要求读取顺序和写入顺序保存一致，读取时，需要将它们转换为预期的类型。
     * - 反序列化对象时，忽略有transient/static修饰的字段，忽略的对象分配内存初始化为NULL。
     * - ObjectInputStream(InputStream in) 提供序反列化功能
     * - final Object readObject() 反序列化方法(恢复对象数据的类型和值)：从对象字节输入流的文件中读取一个对象数据恢复成内存中的对象
     */
    public static void serialize() throws Exception {
        // 创建文件字节输入流
        String path = RELATIVE_PATH + "/object.txt";
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("新建文件：" + file.createNewFile());
        }
        // 提供序列化功能：(对象)->(文件)
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        // 提供序反列化功能：(文件)->(对象)
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

        //将对象写入流中
        oos.writeInt(12345);
        oos.writeObject("Today");
        oos.writeObject(new Date());
        oos.writeObject(new Animal_Serialize("动物", null));
        oos.writeObject(new Dog_Serialize("Dog_Serialize", 5, "transient", "str"));
        //从流中读取对象
        System.out.println(ois.readInt());
        System.out.println(ois.readObject());
        System.out.println(ois.readObject());
        System.out.println(ois.readObject());
        System.out.println(ois.readObject());

        //关闭资源
        ois.close();
        oos.close();
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

class Animal_Serialize implements Serializable {
    private String name;
    private Integer age;

    Animal_Serialize(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Animal_Serialize{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Dog_Serialize extends Animal_Serialize {
    private transient String t;
    private static String s = "dog";
    private String str;

    Dog_Serialize(String name, Integer age) {
        super(name, age);
    }

    public Dog_Serialize(String name, Integer age, String t, String str) {
        super(name, age);
        this.t = t;
        this.str = str;
    }

    @Override
    public String toString() {
        return "Dog_Serialize{" +
                "name='" + super.getName() + '\'' +
                ", age=" + super.getAge() +
                ", t='" + t + '\'' +
                ", str='" + str + '\'' +
                '}';
    }
}