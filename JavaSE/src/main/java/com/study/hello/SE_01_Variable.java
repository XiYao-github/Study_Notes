package com.study.hello;

public class SE_01_Variable {

    public static void main(String[] args) {
        variableTypeConvert();
    }

    /**
     * 变量相当于内存中一个存储空间，里面可以存储一个数据，存储的数据可以变化
     */
    public static void variable() {
        // 定义变量：数据类型 变量名称 = 初始值;
        int age = 18;
        // 变量必须先声明才能使用
        // 变量的类型和存放的数据类型需要一致
        // 变量定义的时候可以没有初始值，但是使用前必须有初始值
        // 变量存在访问范围一般为“{}”内，同一个范围内，多个变量的名字不能一样。
        // 变量定义时可以不给赋初始值，但是在使用时，变量里必须有值
    }

    /**
     * Java语言自己用到的变量，赋予了特殊含义，所有字母都为小写，我们称之为关键字。
     */
    public static void keyword() {
        // 基础数据类型：
        // boolean、byte、char、short、int、long、float、double、
        // 流程：
        // if、else、switch、case、
        // try、catch、finally、
        // do、while、for、continue、break、return
        // 修饰：
        // public、protected、private、default、
        // class、abstract、interface、enum、
        // extends、implements、static、final、
        // volatile、synchronized
        // 依赖、异常、判断：
        // package、import、this、super、throws、throw、new、void
        // assert、instanceof
        // 罕见(忽略不计)
        // const、goto、native、strictfp、transient
    }

    /**
     * 标志符就是名字，变量、方法、类等命名时使用的字符序列称为标识符。
     */
    public static void identifier() {
        // 基本组成：由数字、字母、下划线(_) 和美元符($) 等组成
        // 强制要求：不能以数字开头、不能用关键字做为名字、且是区分大小写的
        // 包名：多单词组成时，所有字母都小写：aaa.bbb.ccc
        // 类/接口名：多单词组成时，所有单词的首字母大写：XxxYyyZzz
        // 变量/方法名：多单词组成时，第一个单词首字母小写，第二个单词开始首字母大写：xxxYyyZzz
        // 常量名：所有字母都大写，多单词组成时，每个单词用下划线连接：XXX_YYY_ZZZ
    }


    static boolean bool;
    static byte by;
    static char ch;
    static double d;
    static float f;
    static int i;
    static long l;
    static short sh;
    static String str;

    /**
     * 变量就是申请内存来存储值。当创建变量的时候，需要在内存中申请空间。
     * 内存管理系统根据变量的类型为变量分配存储空间(字节)，分配的内存空间(字节)只能用来储存该类型数据。
     */
    public static void variableType() {
        System.out.println("基本类型：byte 二进制位数：" + Byte.SIZE + "\t默认值-Byte :" + by);
        System.out.println("基本类型：char 二进制位数：" + Character.SIZE + "\t默认值-Character:('u0000')" + ch);
        System.out.println("基本类型：short 二进制位数：" + Short.SIZE + "\t默认值-Short :" + sh);
        System.out.println("基本类型：int 二进制位数：" + Integer.SIZE + "\t默认值-Integer :" + i);
        System.out.println("基本类型：long 二进制位数：" + Long.SIZE + "\t默认值-Long :" + l);
        System.out.println("基本类型：float 二进制位数：" + Float.SIZE + "\t默认值-Float :" + f);
        System.out.println("基本类型：double 二进制位数：" + Double.SIZE + "\t默认值-Double :" + d);
        System.out.println("默认值-Bool :" + bool);
        System.out.println("默认值-String :" + str);
    }

    /**
     * 数据类型转换，存在不同类型的变量赋值给其他类型的变量，不同类型的变量会先转化为同一类型，然后进行运算。
     * 转换规则从低级到高级。
     * 低  ------------------------------------>  高
     * byte,short,char—> int —> long—> float —> double
     * 注意：byte,short,char运算时会转换成int类型参与运算
     */
    public static void variableTypeConvert() {
        // 自动类型转换
        // 类型范围小的变量，可以直接赋值给类型范围大的变量。
        by = 97;
        // byte自动类型转换为int
        i = by;
        System.out.println("byte自动类型转换为int(小->大)：" + i);

        // 强制类型转换
        // 类型范围大的数据或者变量，不能直接赋值给类型范围小的变量，会报错！必须进行强制类型转换才可以。
        i = 98;
        // int强制类型转换为byte
        // 强制类型转换格式：数据类型 变量 = (数据类型) 其他类型的数据变量
        by = (byte) i;
        System.out.println("int强制类型转换为byte(大->小)：" + by);

        d = 99.5;
        // double强制类型转换为int
        // 注意：强制类型转换可能造成数据(丢失)溢出；
        // 注意：浮点型强转成整型，直接丢掉小数部分，保留整数部分返回。
        i = (int) d;
        System.out.println("double强制类型转换为int(大->小)：" + i);

        // 表达式自动类型转换
        // 在表达式运算中，小范围的类型会自动转换成大范围的类型运算。
        // 表达式的最终数据类型由表达式中的最高类型决定，最高数据类型是什么，其结果数据的类型就是什么。
        // 注意：byte,short,char运算时会转换成int类型参与运算
        by = 50;
        sh = 50;
        //错误形式
        // by = by + sh;  =>  byte = int + int
        //需要强制类型转换
        // byte = (byte) (int + int);
        // by = (byte) (by + sh);
        //正确形式
        i = by + sh;
        System.out.println("byte和short运算中自动转换为int(小->大)：" + i);

        // String类型转换
        // 基本数据类型转String类型：基本类型的值 + ""
        // String类型转基本数据类型，通过调用基本类型包装类方法转换
        // 转换方法：(基本类型的包装类).parse(基本类型)(String类型);
        by = Byte.parseByte("97");
        sh = Short.parseShort("98");
        i = Integer.parseInt("99");
        l = Long.parseLong("100");
        f = Float.parseFloat("10.01");
        d = Double.parseDouble("11.11");
        // String转换为char：因为 String => char[],所有直接对数组取值
        str = "hello";
        ch = str.charAt(0);
        System.out.printf("%d--%d--%d--%d--%4.2f--%4.2f--%c--%s--%%", by, sh, i, l, f, d, ch, str);
    }
}
