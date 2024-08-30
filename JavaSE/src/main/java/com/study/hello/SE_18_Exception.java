package com.study.hello;

/**
 * 异常
 * - 异常是程序在"编译"或者"执行"的过程中可能出现的问题。
 * - 异常一旦出现了，如果没有提前处理，程序就会退出JVM虚拟机而终止。
 * - 注意：语法错误和逻辑错误不算在异常体系中。
 * <p>
 * Throwable
 * - Error：系统级别问题、JVM退出等，代码无法控制。
 * - Exception：java.lang包下，称为异常类，它表示程序本身可以处理的问题。
 * - RuntimeException及其子类：运行时异常，编译阶段不会报错。(空指针异常，数组索引越界异常)
 * - RuntimeException之外所有的异常：编译时异常，编译期必须处理的，否则程序不能通过编译。(日期格式化异常)
 * <p>
 * 编译时异常
 * - 编译时异常就是在编译时出现的异常
 * - Javac.exe：Java文件编译时异常，是在编译成class文件时必须要处理的异常，也称之为受检异常。
 * - 继承自Exception的异常或者其子类，编译阶就报错，必须处理，否则代码不通过。
 * - 编译阶段爆出错误，目的在于提醒不要出错，编译时异常是可遇不可求。
 * <p>
 * 运行时异常
 * - 运行时异常就是在运行时出现的异常
 * - Java.exe：字节码文件运行时异常，在编译成class文件不需要处理，在运行字节码文件时可能出现的异常。
 * - 继承自RuntimeException的异常或者其子类，编译阶段不会报错，运行时可能出现的错误。
 * - 编程逻辑不严谨引起的程序错误，例如：(数组索引越界异常，空指针异常，数学运算异常，类型转换异常，数字转换异常)
 */
public class SE_18_Exception {
    public static void main(String[] args) throws Exception {
        Try_Catch_Finally.method(4);
    }
}

/**
 * 自定义异常
 * - Java无法为这个世界上全部的问题提供异常类。
 * - 如果企业想通过异常的方式来管理自己的某个业务问题，就需要自定义异常类了。
 * - 可以使用异常的机制管理业务问题，如提醒程序员注意。
 * - 同时一旦出现bug，可以用异常的形式清晰地指出出错的地方。
 * - 自定义异常一般都继承RuntimeException，即自定义运行时异常，因为有默认处理方式。
 * <p>
 * 默认异常处理流程
 * - 默认会在出现异常的代码那里自动地创建一个异常对象：ArithmeticException。
 * - 异常会从方法中出现的点这里抛出给调用者，调用者最终抛出给JVM虚拟机。
 * - 虚拟机接收到异常对象后，先在控制台直接输出异常栈信息数据。
 * - 直接从当前执行的异常点干掉当前程序，后续代码没有机会执行了，因为程序已经死亡。
 * - 注意：默认的异常处理机制并不好，一旦真的出现异常，程序立即死亡！
 * <p>
 * 编译时异常处理
 * - throws：出现异常直接抛出去给调用者，调用者也可以继续抛出去，最顶级处理者就是JVM虚拟机。
 * - try-catch-finally：出现异常自己捕获处理，不麻烦别人。
 * - 开发中按照规范来说两者结合是最好的，底层的异常抛出去给最外层，最外层集中捕获处理。
 * <p>
 * 运行时异常的处理
 * - 运行时异常编译阶段不会出错，是运行时才可能出错的，所以编译阶段不处理也可以。
 * - 按照规范建议还是处理，默认throws的处理方式，建议在最外层调用处集中捕获处理即可。
 */
class Try_Catch_Finally {
    /**
     * throws
     * - 用在方法上，可以将方法内部出现的异常抛出去给本方法的调用者处理。
     * - 发生异常的方法自己不处理异常，如果异常最终抛出去给虚拟机将引起程序死亡。
     * - 子类重写父类抛出异常方法时，所抛出的异常类型和父类保持一致或异常其子类，不能扩大异常范围。
     * <p>
     * try-catch-finally
     * - 监视捕获异常，用在方法内部，可以将方法内部出现的异常直接捕获处理。
     * - 发生异常的方法自己独立完成异常地处理，程序可以继续往下执行。
     */
    public static void method(int n) throws Exception {
        try {
            // 如果异常发生了，则异常发生后面的代码不会执行，直接进入到catch的代码块
            // 如果异常没有发生，则顺序执行try的代码块，不会进入到catch的代码块
            // 如果希望不管是否发生异常，都执行某段代码(比如关闭连接，释放资源等)则使用如下代码finally
            if (n == 1) {
                throw new MyException("自定义编译时异常");
            }
            if (n == 2) {
                throw new MyRuntimeException("自定义运行时异常");
            }
            if (n == 3) {
                throw new Exception("编译异常");
            }
        } catch (MyException e) {
            System.out.println("捕获到自定义编译时异常");
        } catch (MyRuntimeException e) {
            System.out.println("捕获到自定义运行时异常");
        } catch (Exception e) {
            // 处理异常，可以有多个catch捕获多种异常，异常类型从小(子类)到大(父类)捕捉
            System.out.println("代码出现异常，已被正常捕获，打印异常栈信息");
        } finally {
            System.out.println("一定会执行的代码");
        }
        System.out.println("没有发生异常或者异常正确被捕获，继续执行其他代码");
    }
}

/**
 * 自定义编译时异常
 * - 定义一个异常类继承Exception。
 * - 重写构造器。
 * - 在出现异常的地方用(throw new 自定义异常对象)抛出。
 * - 作用：编译时异常是编译阶段就报错，提醒更加强烈，一定需要处理！！
 */
class MyException extends Exception {
    public MyException(String message) {
        super(message);
        System.out.println(message);
    }
}

/**
 * 自定义运行时异常
 * - 定义一个异常类继承RuntimeException。
 * - 重写构造器。
 * - 在出现异常的地方用(throw new 自定义异常对象)抛出。
 * - 作用：提醒不强烈，编译阶段不报错！！运行时才可能出现！！
 */
class MyRuntimeException extends RuntimeException {
    public MyRuntimeException(String message) {
        super(message);
        System.out.println(message);
    }
}