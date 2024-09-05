package com.study.hello;

/**
 * final
 * - final关键字是最终的意思，可以修饰(类、方法、变量)，也可以修饰形参。
 * - 修饰类：表明该类是最终类(包装类)，不能被继承。
 * - 修饰方法：表明该方法是最终方法，不能被重写，不能修饰构造器。
 * - 修饰变量：表示该变量第一次赋值后，不能再次赋值(有且仅能被赋值一次)。
 * <p>
 * 常量
 * - 常量是使用了public static final修饰的成员变量，必须有初始化值，而且执行的过程中其值不能被改变。
 * - 常量名的命名规范：英文单词全部大写，多个单词下划线连接起来。
 * - 常量的作用：通常用来记录系统的配置数据，可以不加载类直接使用的属性。
 * - 在编译阶段会进行"宏替换"：把使用常量的地方全部替换成真实的字面量，维护系统容易，可读性更好。
 */
public class SE_10_Final {
    public static void main(String[] args) {
        // final修饰的类，不能继承，但是可以实例化对象
        A_Final a = new A_Final("TEST_5");
        a.study();
        System.out.println();

        // 不是final类，但是含有final方法，方法虽然不能重写，但是可以被继承
        C_Final c = new C_Final();
        c.study();
        System.out.println();

        // final修饰是引用类型，地址值不能发生改变，地址指向的对象内容可以发生变化
        String str = "原始的内容";
        str = "修改后的内容";
        System.out.println(str);
        System.out.println();

        // 修饰属性时static和final可以搭配使用，效率更高(编译器做了优化处理)，可以不加载类直接使用属性。
        // 调用D类的TEST_1属性并没有加载D类的静态代码块，可见没有加载类信息(编译器做了优化处理)。
        System.out.println(D_Final.TEST_1);
    }
}

/**
 * 注意细节
 * - 如果是final修饰的属性，必须赋初值，并且不能修改，初始化的位置可以在定义时，构造器中，代码块中。
 * - 如果使用构造器初始化final修饰的属性，就不能编写其他构造器，不然存在无法初始化变量的情况。
 * - 如果是static final修饰的属性，初始化的位置只能是定义时和静态代码块中，不能在构造器中赋值。
 * - 如果是final修饰的类，不能继承，但是可以实例化对象，基础类型包装类和String类都是final类。
 * - 如果类不是final类，但是含有final方法，则该方法虽然不能重写，但是可以被继承。
 * - 如果是final修饰的类，方法可以不加final修饰，因为类不能被继承，方法也就不能被重写。
 * - 修饰属性时static和final可以搭配使用，效率更高，使用属性时可以不加载类(底层编译器做了优化处理)。
 * - final修饰是基本类型，那么变量存储的数据值不能发生改变。
 * - final修饰是引用类型，那么变量存储的地址值不能发生改变，但是地址指向的对象内容是可以发生变化的。
 */
final class A_Final {
    // 定义时赋初值
    private static final String TEST_1 = "TEST_1";
    private final String TEST_2 = "TEST_2";

    private static final String TEST_3;
    private final String TEST_4;
    private final String TEST_5;

    // 静态代码块赋初值
    static {
        TEST_3 = "TEST_3";
    }

    // 代码块赋初值
    {
        TEST_4 = "TEST_4";
    }

    // 使用构造器赋值，就不能写其他构造器，不然存在无法初始化变量的情况
    public A_Final(String variable) {
        TEST_5 = variable;
    }

    public final void study() {
        System.out.println("正在学习！！！");
    }
}

class B_Final {
    public final void study() {
        System.out.println("正在学习！！！");
    }
}

class C_Final extends B_Final {
    static {
        System.out.println("静态代码块被加载！！！");
    }
}

class D_Final {
    public static final String TEST_1 = "D_TEST_1";

    static {
        System.out.println("静态代码块被加载！！！");
    }
}