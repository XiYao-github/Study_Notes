package com.study.hello;

import java.io.*;
import java.util.Date;

/**
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
public class SE_26_Serialize {
    public static void main(String[] args) throws Exception {
        //提供序列化功能
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/io/Object.txt"));
        //提供序反列化功能
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/io/Object.txt"));

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