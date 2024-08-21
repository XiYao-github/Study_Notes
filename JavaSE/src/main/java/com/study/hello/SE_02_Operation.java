package com.study.hello;

public class SE_02_Operation {
    static double d;
    static int i;
    static int j;
    static int k;
    static int age;
    static boolean bool;

    public static void main(String[] args) {
        relational();
    }

    /**
     * 算数运算符
     * 加(+)：参考小学一年级
     * 减(-)：参考小学一年级
     * 乘(*)：参考小学一年级，与“×”相同
     * 除(/)：参考小学一年级，与“÷”相同，注意：Java中两个整数相除结果还是整数
     * 取余(%)：获取的是两个数据做除法的余数，等价：a % b = a - a / b * b
     * 自增(++)：变量自身的值加1
     * 自减(--)：变量自身的值减1
     */
    public static void arithmetic() {
        // 除法(/)：Java中两个整数相除结果还是整数
        // 从数学来看是2.5, 代码中是2
        i = 10 / 4; //2
        System.out.println("10 / 4 = " + i);
        // 但是浮点数参与除法运算结果就不影响
        d = 10.0 / 4; //2.5
        System.out.println("10.0 / 4 = " + d);
        // 还是两个整数相除,但是结果给了浮点数
        d = 10 / 4; //2.0
        // 原因是计算时还是整数，计算完成才赋值给浮点数
        System.out.println("10 / 4 = " + d);

        // 取余(%)
        // (%)的本质看一个公式 => a % b = a - a / b * b
        // (-10) % 3 = (-10) - (-10) / 3 * 3 = (-10) + 9 = -1
        // 10 % (-3) = 10 - 10 / (-3) * (-3) = 10 - 9 = 1
        // (-10) % (-3) = (-10) - (-10) / (-3) * (-3) = (-10) + 9 = -1
        i = 10 % 3;
        System.out.println("10 % 3 = " + i); //1
        i = -10 % 3;
        System.out.println("-10 % 3 = " + i); //-1
        i = 10 % -3;
        System.out.println("10 % -3 = " + i); //1
        i = -10 % -3;
        System.out.println("-10 % -3 = " + i); //-1

        //自增(++)单独使用,自减(--)同理
        i = 10;
        j = 10;
        i++; //自增 等价于 i = i + 1; => i = 11
        ++j; //自增 等价于 j = j + j; => j = 11
        System.out.printf("i = 10; => i++ =%d;\n", i); //11
        System.out.printf("j = 10; => ++j =%d;\n", i); //11

        //自增(++)表达式内使用,自减(--)同理
        i = 10;
        j = 10;
        // 前++：先自增后赋值, 等价于
        // i = i + 1;
        // k = i;
        k = ++i;
        System.out.printf("i = 10; k = ++i; => i = %d; k = %d;\n", i, k);
        // 后++：先赋值后自增, 等价于
        // k = j;
        // j = j + 1;
        k = j++;
        System.out.printf("j = 10; k = ++j; => j = %d; k = %d;\n", j, k);
    }

    /**
     * 算数运算符
     * 关系运算符的结果都是boolean型，也就是要么是true，要么是false。
     * 关系表达式经常用在分支结构的条件中或循环结构的条件中。
     * 关系运算符组成的表达式，我们称为关系表达式。
     * 等于(==)：a==b，判断a和b的值是否相等，成立为true，不成立为false
     * 不等于(!=)：a!=b，判断a和b的值是否不相等，成立为true，不成立为false
     * 大于(>)：a>b，判断a是否大于b，成立为true，不成立为false
     * 大于等于(>=)：a>=b，判断a是否大于等于b，成立为true，不成立为false
     * 小于(<)：a<b，判断a是否小于b，成立为true，不成立为false
     * 小于等于(<=)：a<=b，判断a是否小于等于b，成立为true，不成立为false
     * 比较对象(instanceof)：左侧变量所指对象，是右侧类或接口(class/interface)的一个对象，成立为true，不成立为false
     */
    public static void relational() {
        //判断是否满足条件的，返回true和false
        int age = 19;
        System.out.println(age >= 18);

        //以下代码是另一种写法形式
        boolean bool = age >= 18;
        System.out.println(bool);

        //如果运算符左侧变量所指的对象，是操作符右侧类或接口(class/interface)的一个对象，那么结果为真。
        String str = "instanceof";
        boolean result = str instanceof String;
        //由于str是String类型，所以返回真
        System.out.println(result);
    }
}
