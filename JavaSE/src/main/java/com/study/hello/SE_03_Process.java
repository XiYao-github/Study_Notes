package com.study.hello;

import java.util.Random;
import java.util.Scanner;

public class SE_03_Process {

    public static void main(String[] args) {
        nestCirculate();
    }

    /**
     * if-else分支
     * - 利用判断条件来决定执行哪个分支
     * - 注意事项：如果if语句的"{}"中只有一行代码的情况，"{}"可以省略不写(但是不推荐省略)。
     * - 分支推荐：if适合做区间匹配，对结果为boolean类型判断，建议使用if语句，使用范围更广。
     */
    public static void ifElse() {
        /*if (条件判断1) {
            执行代码...;
        } else if (条件判断2) {
            执行代码...;
        }
        ...
        } else {
            执行代码...;
        }*/
        Scanner in = new Scanner(System.in);
        System.out.print("请输入第一条边：");
        int a = in.nextInt();
        System.out.print("请输入第二条边：");
        int b = in.nextInt();
        System.out.print("请输入第三条边：");
        int c = in.nextInt();
        if (a + b > c && a + c > b && b + c > a) {
            System.out.printf("结果：(%d,%d,%d)三条边组成的是三角形", a, b, c);
            if (a == b && a == c) {
                System.out.print(",而且是等边三角形");
            } else if (a == b || a == c) {
                System.out.print(",而且是等腰三角形");
            }
            System.out.println("！！！");
        } else {
            System.out.printf("结果：(%d,%d,%d)三条边组成的不是三角形！！！", a, b, c);
        }
    }

    /**
     * switch分支
     * - 利用表达式的值来进行值匹配选择对应的分支执行，结构清晰，格式良好。
     * - 表达式类型只能是byte、short、int、char。
     * - JDK5开始支持枚举，JDK7开始支持String、不支持double、float、long。
     * - 分支推荐：switch适合做值匹配的分支选择，判断的具体数值不多，建议使用switch语句，格式清晰，性能较好。
     */
    public static void switchCase() {
        /*switch(表达式){
            case 值1:  //case给出的值不允许重复，且只能是常量，不能是变量。
                执行代码...;
                break;  //不要忘记写break，否则会出现穿透现象。
            case 值2:
                执行代码...;
                break;
            ...
            case 值n:
                执行代码...;
                break;
            default:  //如果case没有能匹配的值，最后会执行default。
                执行代码...;
        }*/
        // switch的穿透性
        // switch中没有写break，遇到了这个case会一直往下走，直到遇到break才会跳出，这就是switch的穿透性。
        // 多个值对应case块的操作相同时，可以利用switch的穿透性把相同操作集中处理，这样可以提高开发效率，降低代码重复。
        Scanner in = new Scanner(System.in);
        System.out.print("请输入年份：");
        int year = in.nextInt();
        System.out.print("请输入月份：");
        int month = in.nextInt();
        int day;
        switch (month) {
            case 2:
                if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
                    day = 29;
                } else {
                    day = 28;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;
            default:
                day = 31;
                break;
        }
        System.out.printf("%d年%d月有%d天！！！", year, month, day);
    }

    /**
     * for循环
     * - 循环条件是返回一个布尔值的表达式。
     * <p>
     * 循环嵌套
     * - 循环中又包含循环，外部循环每循环一次，内部循环全部执行完一次。
     */
    public static void forCirculate() {
        /*for(初始化语句;循环判断条件;迭代语句){
            执行代码...;
        }*/
        // 初始化语句和迭代语句可以写到其它地方，但是两边的分号不能省略。
        // 初始化语句和迭代语句可以有多条初始化语句，但要求类型一样，并且中间用逗号隔开。
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                int k = i * j;
                System.out.printf("%d * %d = %d \t", j, i, k);
            }
            System.out.println();
        }
    }

    /**
     * while循环
     * - 循环条件是返回一个布尔值的表达式。
     * - while循环是先判断再执行语句。
     */
    public static void whileCirculate() {
        /*while(循环判断条件){
            执行代码...;
        }*/
        int sum = 0;
        int number = 1;
        while (number <= 100) {
            sum += number;
            number++;
        }
        System.out.println("(1~100)整数值的总和是：" + sum);

    }

    /**
     * do-while循环
     * - 循环条件是返回一个布尔值的表达式。
     * - do-while循环是先执行，再判断，因此它至少执行一次，最后循环条件后有一个分号。
     */
    public static void doWhileCirculate() {
        /*do{
            执行代码...;
        }while (循环判断条件);*/
        Scanner in = new Scanner(System.in);
        System.out.print("请输入一个整数:");
        int number = in.nextInt();
        int count = 0;
        do {
            number /= 10;
            count++;
        } while (number > 0);
        System.out.println("你输入的整数是" + count + "位数");
    }

    /**
     * 死循坏
     * - 一直循环的执行下去，如果没有干预不会停止下来。
     */
    public static void dieCirculate() {
        // for (; ; ) {
        //     System.out.println("HelloWorld");
        // }
        // while (true) {
        //     System.out.println("HelloWorld");
        // }
        // do {
        //     System.out.println("HelloWorld");
        // } while (true);
    }

    /**
     * 三种循环的区别
     * - do...while(第一次先执行后判断)
     * - for循环和while循环(先判断后执行)，循环的执行流程是一模一样的。
     * - 如果已知循环次数建议使用for循环，如果不清楚要循环多少次建议使用while循环。
     * - for循环中，控制循环的变量只在循环中可以使用，while循环中，控制循环的变量在循环后还可以继续使用。
     * <p>
     * 跳转控制语句
     * - break：跳出并结束当前所在循环的执行，只能用于结束所在循环，或者结束所在switch分支的执行。
     * - continue：跳出当前循环的当次执行，继续进入下一次循环，只能在循环中进行使用。
     * - return：跳出当前所在的方法，一般使用在方法内。
     */
    public static void nestCirculate() {
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        System.out.print("开始游戏");
        int randomNumber = random.nextInt(100);
        System.out.println(",随机数已生成！！！");
        int max = 99;
        int min = 0;
        while (true) {
            System.out.printf("请输入一个整数(%d - %d)：", min, max);
            int number = in.nextInt();
            if (number < min || number > max) {
                System.out.println("整数值超出游戏范围！");
                continue;
            }
            if (number > randomNumber) {
                System.out.println("你猜的数大了！");
                if (number < max) {
                    max = number;
                }
            }
            if (number < randomNumber) {
                System.out.println("你猜的数小了！");
                if (number > min) {
                    min = number;
                }
            }
            if (number == randomNumber) {
                System.out.println("你猜对了！！！");
                break;
            }
        }
    }

}
