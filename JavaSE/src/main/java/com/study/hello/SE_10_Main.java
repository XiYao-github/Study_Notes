package com.study.hello;

public class SE_10_Main {

    /**
     * main方法
     * - java虚拟机需要调用类的main()方法，所以该方法的访问权限必须是public。
     * - java虚拟机在执行main()方法时不必创建对象，所以该方法必须是static。
     * - main()方法可以接收String类型的数组参数，该数组中保存执行java命令时传递给类的参数。
     * - main()方法可以直接调用所在类的静态方法或静态变量。
     *
     * @param args 命令参数
     */
    public static void main(String[] args) {
        // 类什么时候被加载(重要)
        // 1.创建父类对象实例(new)，会加载(1,3,4)
        // 2.创建子类对象实例，父类也会被加载(1,2,3,4,5,6)
        // 3.使用类的静态成员(属性,方法)时，加载静态属性/方法(1,2)，属于类加载，不是创建对象

        // 创建一个对象，在一个类调用顺序(重要)
        // 注意：属性/代码块优先级一样，按定义顺序初始化
        // 1.父类的静态属性/代码块初始化
        // 2.子类的静态属性/代码块初始化
        // 3.父类的普通属性/代码块初始化
        // 4.父类的构造方法
        // 5.子类的普通属性/代码块初始化
        // 6.子类的构造方法
        // Parent parent = new Parent(); //调用：1,3,4
        // Sub sub = new Sub(); //调用：1,2,3,4,5,6
        Sub.study(); //调用：1,2
    }
}

class Parent {
    private static String name;
    private String type;

    static {
        name = "父类";
        System.out.println("1." + name + "的静态属性/代码块初始化！！！");
    }

    {
        type = "超类";
        System.out.println("3." + type + "的普通属性/代码块初始化！！！");
    }

    public Parent() {
        //super();
        //会在super()后隐含的调用本类代码块
        System.out.printf("4.%s(%s)的构造方法初始化！！！\n", name, type);
    }
}

class Sub extends Parent {
    private static String name;
    private String type;

    static {
        name = "子类";
        System.out.println("2." + name + "的静态属性/代码块初始化！！！");
    }

    {
        type = "基类";
        System.out.println("5." + type + "的普通属性/代码块初始化！！！");
    }

    public Sub() {
        //super();
        //会在super()后隐含的调用本类代码块
        System.out.printf("6.%s(%s)的构造方法初始化！！！\n", name, type);
    }

    public static void study() {
        System.out.println("调用" + name + "静态方法！！！");
    }
}