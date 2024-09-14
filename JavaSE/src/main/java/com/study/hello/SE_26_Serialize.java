package com.study.hello;

import java.io.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

public class SE_26_Serialize {
    public static final String PROPERTY = System.getProperty("user.dir");
    public static final String ABSOLUTE_PATH = PROPERTY + "\\JavaSE\\src\\main\\java\\com\\study\\hello\\file";
    public static final String RELATIVE_PATH = "./JavaSE/src/main/java/com/study/hello/file";

    public static void main(String[] args) throws Exception {
        // serialize();
        // properties();
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
        oos.writeObject(new Animal("动物", null));
        oos.writeObject(new Dog("Dog", 5, "transient", "str"));
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

class Animal implements Serializable {
    private String name;
    private Integer age;

    Animal(String name, Integer age) {
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
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Dog extends Animal {
    private transient String t;
    private static String s = "dog";
    private String str;

    Dog(String name, Integer age) {
        super(name, age);
    }

    public Dog(String name, Integer age, String t, String str) {
        super(name, age);
        this.t = t;
        this.str = str;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + super.getName() + '\'' +
                ", age=" + super.getAge() +
                ", t='" + t + '\'' +
                ", str='" + str + '\'' +
                '}';
    }
}