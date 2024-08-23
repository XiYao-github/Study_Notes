package com.study.hello;

import java.util.Arrays;

public class SE_05_Method {
    public static void main(String[] args) {
        overload();
    }

    /**
     * 方法
     * - 方法是一种语法结构，它可以把一段代码封装成一个功能，以方便重复调用，以此提高代码的复用性，提高开发效率，让程序逻辑更清晰。
     * <p>
     * 定义方法
     * - 定义方法第一步(非常重要)：方法是否需要申明返回值类型，方法是否需要定义形参列表。
     * - 访问修饰符(作用是控制方法使用的范围)如果不写默认访问(public，protected，默认，private)。
     * - 形参列表可以有多个，甚至可以没有，如果有多个形参，多个形参必须用","隔开，且不能给初始化值。
     * - 方法不调用就不执行，调用时必须严格匹配方法的参数情况，否则报错。
     * - 方法申明了返回值类型，方法内部必须使用return返回对应类型的数据。
     * - 方法返回值类型为void，方法内部不能使用return返回数据，可以直接return返回或者不写。
     * - return语句下面，不能编写代码，因为永远执行不到，属于无效的代码。
     * - return语句用在无返回值的方法中，会立即跳出并结束当前方法的执行。
     */
    public static void method() {
        /*修饰符 返回值类型 方法名(形参列表) {
            执行代码...;
            return 返回值;
        }*/
    }

    /**
     * 参数传递
     * - 实参：在方法内部定义的变量。
     * - 形参：方法定义时的变量，"()"中所声明的参数。
     * - 值传递：在传输实参给方法的形参的时候，并不是传输实参变量本身，而是传输实参变量中存储的值，这就是值传递。
     * - 基本类型和引用类型参数传递都是值传递(形参=实参)。
     * - 基本类型传递的是实参变量中存储的值，不可以通过形参影响实参。
     * - 引用类型传递的是地址(传递也是值传递，但是值是地址)，可以通过形参影响实参！！！
     */
    public static void parameter() {
        int[] ages = {10, 20, 30};
        int age = 18;
        System.out.println("调用方法前引用类型：" + Arrays.toString(ages));
        System.out.println("调用方法前基本类型：" + age);
        // 调用方法
        change(ages, age);
        // 引用类型：可以通过形参影响实参
        System.out.println("调用方法后引用类型：" + Arrays.toString(ages));
        // 基本类型：不可以通过形参影响实参
        System.out.println("调用方法后基本类型：" + age);
    }

    public static void change(int[] ages, int age) {
        ages[0] = 100;
        age = 20;
    }

    /**
     * 可变参数
     * - 可变参数用在形参中可以不接收参数或接收任意多个数据，也可以接收一个数组。
     * - 可变参数在方法内部本质上就是一个数组。
     * - 一个形参列表中可变参数只能有一个，而且可变参数必须放在形参列表的最后面。
     */
    public static void variableParameter() {
        int[] arr = {10, 20, 30, 40, 50};
        sum(0);
        sum(1, 10);
        sum(2, 10, 20);
        sum(3, 10, 20, 30);
        sum(4, 10, 20, 30, 40);
        sum(5, arr);
    }

    public static void sum(int i, int... arr) {
        /*修饰符 返回值类型 方法名(数据类型 形参名,数据类型...可变形参名) {
            执行代码...;
            return 返回值;
        }*/
        System.out.printf("%d个可变参数方法调用：%s\n", i, Arrays.toString(arr));
    }

    /**
     * 方法重载
     * - 同一个类中，多个同名方法的存在，但要求形参列表不一致，那么这些方法就是重载方法。
     * - 方法名称相同，形参列表必须不同，才是方法重载，其他的都不管。
     * - 形参类型、个数、顺序至少有一个不同，不关心形参变量的名称。
     */
    public static void overload() {
        String name = "张三";
        String course = "English";
        study();
        study(name);
        study(name, course);
    }

    public static void study() {
        System.out.println("正在学习！！！");
    }

    public static void study(String name) {
        System.out.println(name + "正在学习！！！");
    }

    public static void study(String name, String course) {
        System.out.println(name + "正在学习" + course + "！！！");
    }

}
