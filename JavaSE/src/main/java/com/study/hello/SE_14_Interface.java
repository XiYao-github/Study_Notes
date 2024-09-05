package com.study.hello;

/**
 * 接口(like-a:它像)
 * - 接口是规范：约束别人必须干什么事情，规范的基本特征是约束和公开。
 * - 接口中的方法：默认会自动加上(public abstract)修饰，不写也有。
 * - 接口中的变量：默认会自动加上(public static final)修饰，不写也有。
 * - 接口被类实现，必须重写接口内全部的抽象方法，否则这个类是一个抽象类。
 * - 接口不能实例化，接口可以被类单实现，也可以被类多实现。
 * - 接口弥补了类单继承的不足，类可以同时实现多个接口。
 * - 接口让程序可以面向接口编程，这样既不用关心实现的细节，也可以灵活方便的切换各种实现。
 */
public class SE_14_Interface {
    public static void main(String[] args) {
        // 实现接口测试
        Animal_Interface animal = new Pig_Interface();
        animal.eat(Animal_Interface.NAME);
        System.out.println();
        // 继承父类实现接口测试
        extendsTest();
        System.out.println();
        // 多实现接口测试
        interfaceTest();
    }

    /**
     * 父类和接口冲突(一个类继承了父类，同时又实现了接口)
     * 1.公有静态常量的冲突：实现类无法继承有冲突的常量
     * 2.公有抽象方法的冲突：实现类必须重写一个有冲突的抽象方法
     * 3.公有默认方法的冲突：优先访问父类
     * 4.公有静态方法的冲突：只会访问父类的静态方法
     * 5.私有方法的冲突：不存在冲突
     */
    public static void extendsTest() {
        ABC abc = new ABC();
        // 公有静态常量，变量同名就会报错，实现类无法继承有冲突的常量
        // System.out.println(ABC.NUM1); //编译报错
        // System.out.println(ABC.NUM2); //编译报错
        // System.out.println(ABC.NUM3); //编译报错
        // System.out.println(ABC.NUM4); //编译报错
        System.out.println(abc.NUM5);
        // 公有抽象方法，访问重写方法
        abc.abstractMethod();
        // 公有默认方法，优先访问父类
        abc.defaultMethod();
        // 公有静态方法，优先访问父类
        ABC.staticMethod();
    }

    /**
     * 多实现接口冲突(一个类实现了多个接口 或者 一个类实现了多继承的接口)
     * 1.公有静态常量的冲突：实现类无法继承有冲突的常量
     * 2.公有抽象方法的冲突：实现类必须重写一个有冲突的抽象方法
     * 3.公有默认方法的冲突：实现类必须重写一次最终版本(选择一个接口)的默认方法
     * 4.公有静态方法的冲突：静态方法是直接属于接口的，只能使用接口名直接访问，不存在冲突
     * 5.私有方法的冲突：不存在冲突
     */
    public static void interfaceTest() {
        AB ab = new AB();
        // 公有静态常量
        // System.out.println(AB.NUM1); //编译报错
        // System.out.println(AB.NUM2); //编译报错
        System.out.println(AB.NUM3);
        System.out.println(AB.NUM4);
        // 公有抽象方法
        ab.abstractMethod();
        // 公有默认方法
        ab.defaultMethod();
    }
}

// 接口(完整定义格式)：
/*修饰符 interface 类名 {
    public static final 数据类型 变量名称 = 初始值;
    (数据类型 变量名称 = 初始值;)
    public abstract 返回值类型 方法名称(形参列表);
    (返回值类型 方法名称(形参列表);)
}*/
interface Animal_Interface {
    // 常量(必须初始化)，访问格式：接口名.属性名
    public static final String NAME = "动物";

    public abstract void eat(String name);
}

/**
 * 实现类
 * - 接口是用来被类实现(implements)的，实现接口的类称为实现类。
 * - 实现类可以理解成接口的子类，所以接口存在多态传递。
 */
class Pig_Interface implements Animal_Interface {
    @Override
    public void eat(String name) {
        System.out.println(name + "正在吃东西！！！");
    }
}

/**
 * 类和接口
 * - 类和类的关系：单继承，一个类只能继承一个直接父类。
 * - 类和接口的关系：多实现，一个类可以实现多个接口。
 * - 接口和接口的关系：多继承，一个接口可以继承多个接口。
 * - 接口多继承：规范合并，整合多个接口为同一个接口，便于子类实现。
 */
interface A_Interface {
    public static final int NUM1 = 10;
    public static final int NUM2 = 20;
    public static final int NUM3 = 30;

    public abstract void abstractMethod();

    public default void defaultMethod() {
        System.out.println("A接口默认方法：defaultMethod");
    }

    public static void staticMethod() {
        System.out.println("A接口静态方法：staticMethod");
    }
}

interface B_Interface {
    public static final int NUM1 = 100;
    public static final int NUM2 = 200;
    public static final int NUM4 = 400;

    public abstract void abstractMethod();

    public default void defaultMethod() {
        System.out.println("B接口默认方法：defaultMethod");
    }

    public static void staticMethod() {
        System.out.println("B接口静态方法：staticMethod");
    }
}

abstract class C_Abstraction {
    public static final int NUM1 = 1000;
    public static final int NUM2 = 2000;
    public static final int NUM3 = 3000;
    public static final int NUM4 = 4000;
    public int NUM5 = 5000;

    public abstract void abstractMethod();

    public void defaultMethod() {
        System.out.println("抽象类默认方法：defaultMethod");
    }

    public static void staticMethod() {
        System.out.println("抽象类静态方法：staticMethod");
    }
}

// 接口多继承
interface C_Interface extends A_Interface, B_Interface {
    @Override
    public void abstractMethod();

    @Override
    public default void defaultMethod() {
        A_Interface.super.defaultMethod();
        System.out.println("C接口默认方法：defaultMethod");
    }
}

// 类单实现
class ABC extends C_Abstraction implements C_Interface {
    @Override
    public void abstractMethod() {
        System.out.println("ABC类抽象方法：abstractMethod");
    }
}

// 类多实现
class AB implements A_Interface, B_Interface {
    @Override
    public void abstractMethod() {
        System.out.println("AB类抽象方法：abstractMethod");
    }

    @Override
    public void defaultMethod() {
        A_Interface.super.defaultMethod();
        System.out.println("AB类默认方法：defaultMethod");
    }
}
