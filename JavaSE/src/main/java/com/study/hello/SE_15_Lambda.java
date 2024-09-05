package com.study.hello;

/**
 * Lambda表达式
 * - Lambda表达式是JDK8开始新增的一种语法形式
 * - 作用：简化函数式接口的匿名内部类的代码写法。
 * - 注意：Lambda表达式只能简化函数式接口的匿名内部类。
 * <p>
 * 函数式接口(函数式编程)
 * - 只有一个抽象方法的接口就是函数式接口。
 * - 注意：将来我们见到的大部分函数式接口，上面都可能会有一个@FunctionalInterface的注解，有该注解的接口就必定是函数式接口。
 * - lambda表达式是函数式编程的一种体现，它允许将函数当作参数传递给方法，或者将函数作为返回值，这种支持使得Java在函数式编程方面更为灵活，能够更好地处理集合操作、并行计算等任务。
 */
public class SE_15_Lambda {
    public static void main(String[] args) {
        Lambda lambda = new Lambda();
        // 基于接口的匿名内部类实现
        Lambda_Interface add = new Lambda_Interface() {
            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        };
        /**
         * Lambda表达式语法：基于接口(函数式接口)的匿名内部类实现可以使用Lambda表达式编写。
         * - parameters 是参数列表，expression 或 { statements; } 是Lambda表达式的主体。如果只有一个参数，可以省略括号；如果没有参数，也需要空括号。
         * - (parameters) -> expression
         * - (parameters) -> { statements; }
         *
         * Lambda表达式的省略写法
         * - 参数类型可以省略不写，如果只有一个参数，()也可以省略
         * - 方法体代码只有一行代码，可以省略大括号不写，同时要省略分号！！！如果这行代码是return语句，也必须去掉return不写。
         */
        Lambda_Interface subtract = (int a, int b) -> {
            return a - b;
        };
        // 参数类型可以省略不写，如果只有一个参数，()也可以省略
        Lambda_Interface multiply = (a, b) -> {
            return a * b;
        };
        // 方法体代码只有一行代码，可以省略大括号不写，同时要省略分号！！！如果这行代码是return语句，也必须去掉return不写。
        Lambda_Interface divide = (a, b) -> a / b;
        // 调用方法
        System.out.println("10 + 5 = " + lambda.operate(10, 5, add));
        System.out.println("10 - 5 = " + lambda.operate(10, 5, subtract));
        System.out.println("10 x 5 = " + lambda.operate(10, 5, multiply));
        System.out.println("10 / 5 = " + lambda.operate(10, 5, divide));

        /**
         * 变量作用域
         * - lambda表达式只能引用标记了final的外层局部变量，这就是说不能在lambda内部修改定义在域外的局部变量，否则会编译错误。
         * - lambda表达式的局部变量可以不用声明为final，但是必须不可被后面的代码修改(即隐性的具有final的语义)。
         * - lambda表达式当中不允许声明一个与局部变量同名的参数或者局部变量。
         */
        final String hello = "hello";
        String world = "world"; // lambda表达式的局部变量可以不用声明为final
        // String message = "message"; //lambda表达式当中不允许声明一个与局部变量同名的参数或者局部变量。
        lambda.say(hello, message -> System.out.println(hello + message));
        lambda.say(world, message -> System.out.println(world + message));
        // world = "hello world"; // 不可被后面的代码修改(即隐性的具有final的语义)。

        /**
         * 方法引用
         */
        // Lambda.staticMethod(Lambda::new);
        // Arrays.asList(new String[] {"a", "c", "b"}).stream().forEach(Test::println);
    }
}

/**
 * 方法引用
 * - 静态方法的引用(类名::静态方法)：如果某个Lambda表达式里只是调用一个静态方法，并且前后参数的形式一致，就可以使用静态方法引用。
 * - 实例方法的引用(对象名::实例方法)：如果某个Lambda表达式里只是调用一个实例方法，并且前后参数的形式一致，就可以使用实例方法引用。
 * - 特定类型方法的引用(类型::方法)：如果某个Lambda表达式里只是调用一个实例方法，并且前面参数列表中的第一个参数是作为方法的主调，后面的所有参数都是作为该实例方法的入参的，则此时就可以使用特定类型的方法引用。
 * - 构造器引用(类名::new)：如果某个Lambda表达式里只是在创建对象，并且前后参数情况一致，就可以使用构造器引用。
 */
class Lambda {
    private String name;
    private Integer age;

    public static void staticMethod(Lambda lambda) {
        System.out.println(lambda);
    }

    public void method(Lambda lambda) {
        System.out.println(lambda);
    }

    // 调用接口方法
    public int operate(int a, int b, Lambda_Interface lambdaInterface) {
        return lambdaInterface.operation(a, b);
    }

    // 调用接口方法
    public void say(String str, Lambda_Interface_Out lambdaInterfaceOut) {
        lambdaInterfaceOut.say(str);
    }
}

//接口
interface Lambda_Interface {
    // 接口只能存在一个抽象方法，存在多个抽象方法不是函数式接口，也不能使用Lambda表达式编写接口实现。
    int operation(int a, int b);
}

//接口
@FunctionalInterface
interface Lambda_Interface_Out {
    void say(String message);

    default void defaultMethod() {
        System.out.println("默认方法");
    }

    static void staticMethod() {
        System.out.println("静态方法");
    }
}