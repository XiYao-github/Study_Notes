package com.study.hello;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 网络通信
 * - 网络编程可以让程序与网络上的其他设备中的程序进行数据交互。
 * <p>
 * 通信模式
 * - Client-Server(CS)
 * -- Client：需要程序员开发实现，用户需要安装客户端。
 * -- Server：需要程序员开发实现。
 * - Browser/Server(BS)
 * -- Browser：不需要程序员开发实现，用户需要安装浏览器。
 * -- Server：需要程序员开发实现。
 * <p>
 * 三要素
 * - IP地址：设备在网络中的地址，是唯一的标识。
 * - 端口：应用程序在设备中唯一的标识。
 * - 协议：数据在网络中传输的规则，常见的协议有UDP协议和TCP协议。
 */
public class SE_28_Socket {
    /*
         OSI参考模型     TCP/IP参考模型            各层对应                     面向操作
           应用层
           表示层           应用层          HTTP、FTP、DNS、SMTP     应用程序需要关注的：浏览器，邮箱
           会话层
           传输层           传输层                TCP、UDP             选择使用的TCP，UDP协议
           网络层           网络层               IP、ICMP           封装源和目标IP，进行路径选择
         数据链路层
           物理层       数据链路层+物理        物理寻址、比特流                物理设备中传输
     */

    /**
     * 通信协议
     * - 计算机网络中，连接和通信数据的规则被称为网络通信协议。
     * - 网络通信协议参考模型
     * - OSI参考模型：世界互联协议标准，全球通信规范，由于此模型过于理想化，未能在因特网上进行广泛推广。
     * - TCP/IP参考模型(或TCP/IP协议)：事实上的国际标准。
     * <p>
     * TCP(TransmissionControlProtocol)：传输控制协议
     * - 使用TCP协议，必须双方先建立连接，它是一种面向连接，可靠传输的通信协议。
     * - 传输前，采用”三次握手“建立连接，点对点的通信，”四次挥手“断开连接所以是可靠的。
     * - 通信过程有两大进程(客户端，服务端)，连接过程中可进行大数据量的传输。
     * - 连接、发送数据都需要确认，且传输完毕后，还需释放已建立的连接，通信效率较低。
     * - TCP协议通信场景：对信息安全要求较高的场景，例如：文件下载、金融等数据通信。
     * <p>
     * UDP(UserDatagramProtocol)：用户数据报协议
     * - UDP是面向无连接，不可靠传输的通信协议。
     * - 将数据源IP、目的地IP和端口封装成数据包，不需要建立连接。
     * - 速度快，有大小限制一次最多发送64K，数据不安全，易丢失数据。
     * - 发送不管对方是否准备好，接收方收到也不确认，故是不可靠的。
     * - 可以广播发送，发送数据结束时无需释放资源，开销小，速度快。
     * - UDP协议通信场景：语音通话，视频会话等。
     */
    public static void main(String[] args) throws IOException {
        ipMethod();
    }

    /**
     * IP地址
     * - InetAddress：此类表示Internet协议(IP)地址。
     * - IP(InternetProtocol)：全称”互联网协议地址”，是分配给上网设备的唯一标志。
     * - IP分类为IPv4(4个字节)和IPv6(16个字节)，公网地址、私有地址(局域网使用)。
     * - 192.168.开头的就是常见的局域网地址，范围即为192.168.0.0--192.168.255.255。
     * - 本机IP127.0.0.1/localhost：称为回送地址也可称本地回环地址，只会寻找当前所在本机。
     * - ipconfig: 查看本机的ip地址
     * - pring 域名/ip  检测当前电脑与指定的ip是否连通
     * <p>
     * 端口号
     * - 唯一标识正在计算机设备上运行的进程(程序)，被规定为一个16位的二进制，范围是(0~65535)。
     * - 端口类型
     * - 周知端口：(0~1023)，被预先定义的知名应用占用。(如：HTTP占用80，FTP占用21)
     * - 注册端口：(1024~49151)，分配给用户进程或某些应用程序。(如：Tomcat占用8080，MySQL占用3306)
     * - 动态端口：(49152~65535)，之所以称为动态端口，是因为它一般不固定分配某种进程，而是动态分配。
     * - 注意：我们自己开发的程序选择注册端口，且一个设备中不能出现两个程序的端口号一样，否则出错。
     */
    public static void ipMethod() throws IOException {
        // static InetAddress getByName(String host)根据主机名称确定主机的IP地址，参数是域名或者IP地址
        InetAddress localhost = InetAddress.getByName("localhost");
        System.out.println(localhost);

        // String getCanonicalHostName()获取此IP地址的完全限定域名
        System.out.println(localhost.getCanonicalHostName());

        // String getHostAddress()返回文本表示中的IP地址字符串
        System.out.println(localhost.getHostAddress());

        // String getHostName()获取此IP地址的主机名
        System.out.println(localhost.getHostName());

        // byte[] getAddress()返回IP地址字节数组形式
        System.out.println(Arrays.toString(localhost.getAddress()));

        // static InetAddress getLocalHost()返回本地主机的地址对象
        InetAddress host = InetAddress.getLocalHost();
        System.out.println(host);

        // static InetAddress getLoopbackAddress()返回环回地址对象
        InetAddress loopbackAddress = InetAddress.getLoopbackAddress();
        System.out.println(loopbackAddress);

        // boolean isReachable(int timeout)在指定毫秒内连通该IP地址对应的主机，连通返回true
        System.out.println(localhost.isReachable(1000));
    }

}

/**
 * TCP通信
 * - TCP是一种面向连接，安全、可靠的传输数据的协议。
 * - 传输前，采用“三次握手”方式，点对点通信，是可靠的。
 * - 通信过程有两大进程(客户端，服务端)，连接过程中可进行大数据量的传输。
 * <p>
 * Socket(客户端)
 * - 该类实现客户端套接字，套接字是两台机器之间通信的端点。
 * 构造器
 * - Socket() 创建一个未连接的套接字，需要使用connect()绑定
 * - Socket(String host, int port) 创建流套接字并将其连接到指定主机上的指定端口号
 * - Socket(InetAddress address, int port) 创建流套接字并将其连接到指定IP地址处的指定端口号
 * 方法
 * - InputStream getInputStream() 获得字节输入流对象(收)
 * - OutputStream getOutputStream() 获得字节输出流对象(发)
 */
class SocketClient {
    public static void main(String[] args) throws Exception {
        // 创建客户端链接到指定IP和端口的服务端
        Socket socket = new Socket(InetAddress.getLocalHost(), 7777);
        System.out.println("客户端成功连接到服务器！！！");
        // 启动一个守护线程接收消息
        Thread thread = new Thread(() -> receive(socket));
        thread.setDaemon(true);
        thread.start();
        // 主线程发送消息
        send(socket);
    }

    private static void send(Socket socket) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))
        ) {
            String line;
            while (true) {
                line = in.readLine();
                if ("exit".equalsIgnoreCase(line)) {
                    break;
                }
                out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭socket资源,断开服务器连接
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void receive(Socket socket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8)), true)
        ) {
            String line;
            while ((line = in.readLine()) != null) {
                out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭socket资源,断开服务器连接
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * ServerSocket(服务端)
 * - 该类实现服务器套接字，服务器套接字等待通过网络进入的请求，它根据该请求执行某些操作，然后可能将结果返回给请求者。
 * - 服务器套接字的实际工作由"SocketImpl"类的实例执行，应用程序可以更改创建套接字实现的套接字工厂，以配置自身以创建适合本地防火墙的套接字。
 * 构造器
 * - ServerSocket() 创建没有指定端口的服务器，需要使用bind()绑定
 * - ServerSocket(int port) 创建指定端口的服务器
 * 方法
 * - Socket accept() 侦听对此套接字的连接并接受它
 */
class SocketServer {
    public static void main(String[] args) throws Exception {
        // 创建指定端口的服务端
        ServerSocket serverSocket = new ServerSocket(7777);
        System.out.println("服务端启动成功，等待客户端连接！！！");
        while (true) {
            // 等待客户端链接
            Socket socket = serverSocket.accept();
            System.out.println(socket.getPort() + "成功连接！！！");
            // 启动一个线程接收信息
            new Thread(new ServerTasks(socket)).start();
        }
    }
}

class ServerTasks implements Runnable {
    private final Socket socket;

    public ServerTasks(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)), true)
        ) {
            String line;
            while ((line = in.readLine()) != null) {
                out.println("服务器成功接收消息！！！");
                System.out.println(socket.getPort() + "发送消息:" + line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭socket资源，断开客户端连接
                System.out.println(socket.getPort() + "断开连接！！！");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * UDP通信
 * - UDP是一种无连接、不可靠传输的协议。
 * - 将数据源IP、目的地IP、端口、数据封装成数据包，大小限制在64KB内，直接发送出去即可。
 * <p>
 * DatagramPacket(数据包)
 * - 该类表示数据报包，数据报分组用于实现无连接分组传递服务。
 * - 每个消息仅根据该数据包中包含的信息从一台机器路由到另一台机器。
 * - 发送多个数据包可能会以不同方式路由，并且接收时顺序可能任何，无法保证数据包传输。
 * 构造器
 * - DatagramPacket(byte buf[], int length) 创建数据包，数据but[]，长度length
 * - DatagramPacket(byte buf[], int length, InetAddress address, int port) 数据but[]，长度length，发送到指定IP和端口
 * - DatagramPacket(byte buf[], int offset, int length) 数据but[]，偏移量offset，长度length
 * - DatagramPacket(byte buf[], int offset, int length, InetAddress address, int port) 数据but[]，偏移量offset，长度length，发送到指定IP和端口
 * - DatagramPacket(byte buf[], int length, SocketAddress address) 数据but[]，长度length，发送到指定address(IP和端口)
 * - DatagramPacket(byte buf[], int offset, int length, SocketAddress address) 数据but[]，偏移量offset，长度length，发送到指定address(IP和端口)
 * 方法
 * - InetAddress getAddress() 返回发送此数据报或从中接收数据报的计算机的IP地址
 * - byte[] getData() 返回数据缓冲区
 * - int getLength() 返回要发送的数据的长度或接收的数据的长度
 * - int getOffset() 返回要发送的数据的偏移量或接收数据的偏移量
 * - int getPort() 返回发送此数据报或从中接收数据报的远程主机上的端口号
 * - SocketAddress getSocketAddress() 获取此数据包发送到或来自的远程主机的SocketAddress（通常是IP地址+端口号）
 * - void setAddress(InetAddress iaddr) 设置要将此数据报发送到的计算机的IP地址
 * - void setData(byte[] buf) 设置此数据包的数据缓冲区
 * - void setData(byte[] buf, int offset, int length) 设置此数据包的数据缓冲区
 * - void setLength(int length) 设置此数据包的长度
 * - void setPort(int iport) 设置要将此数据报发送到的远程主机上的端口号
 * - void setSocketAddress(SocketAddress address) 设置要将此数据报发送到的远程主机的SocketAddress（通常是IP地址+端口号）
 */
class PacketSend {
    public static void main(String[] args) throws Exception {
        // 创建收/发数据包对象
        DatagramSocket datagramSocket = new DatagramSocket(8888);
        System.out.println("启动成功，等待发送消息！！！");
        // 发送消息
        send(datagramSocket);
        // 关闭资源
        datagramSocket.close();
    }

    private static void send(DatagramSocket datagramSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))
        ) {
            String line;
            while (true) {
                System.out.print("发送消息：");
                line = in.readLine();
                if ("exit".equalsIgnoreCase(line)) {
                    break;
                } else {
                    // 创建数据包对象
                    byte[] but = line.getBytes(StandardCharsets.UTF_8);
                    DatagramPacket datagramPacket = new DatagramPacket(but, but.length, InetAddress.getLocalHost(), 9999);
                    // 广播地址发送
                    // DatagramPacket datagramPacket = new DatagramPacket(but, but.length, InetAddress.getByName("255.255.255.255"), 9999);
                    // 发送数据报包
                    datagramSocket.send(datagramPacket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * DatagramSocket(收/发数据包)
 * - 此类表示用于发送和接收数据报包的套接字，数据报套接字是分组传送服务的发送或接收点。
 * - 数据报套接字上发送或接收的每个数据包都是单独寻址和路由的，发送多个数据包可能会以不同方式路由，并且接收时顺序可能任何。
 * 构造器
 * - DatagramSocket() 创建收/发数据包对象，系统会随机分配可用端口号
 * - DatagramSocket(int port) 创建收/发数据包对象，并指定端口号
 * - DatagramSocket(int port, InetAddress laddr) 创建收/发数据包对象，并指定IP和端口号
 * - DatagramSocket(SocketAddress bindaddr) 创建收/发数据包对象，并指定address(IP和端口号)
 * 方法
 * - void receive(DatagramPacket p) 接收数据报包
 * - void send(DatagramPacket p) 发送数据报包
 */
class PacketReceive {
    public static void main(String[] args) throws Exception {
        // 创建收/发数据包对象
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        System.out.println("启动成功，等待接收消息！！！");
        // 接收数据包
        receive(datagramSocket);
        // 关闭资源
        datagramSocket.close();
    }

    private static void receive(DatagramSocket datagramSocket) throws IOException {
        // 创建字节数组，用于存储接收的数据
        byte[] but = new byte[1024 * 64];
        // 创建数据包对象，接收数据
        DatagramPacket datagramPacket = new DatagramPacket(but, but.length);
        while (true) {
            // 接收数据报包
            datagramSocket.receive(datagramPacket);
            // 拆包，接收多少就倒出多少，获取本次数据包接收了多少数据。
            int length = datagramPacket.getLength();
            byte[] data = datagramPacket.getData();
            String str = new String(data, 0, length);
            System.out.println("接收到" + datagramPacket.getSocketAddress() + "发送的消息：" + str);
        }
    }
}