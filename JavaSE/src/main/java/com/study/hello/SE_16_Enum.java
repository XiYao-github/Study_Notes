package com.study.hello;

/**
 * 枚举
 * - 枚举是Java中的一种特殊类型，是为了做信息的标志和信息的分类。
 * - 枚举是一组常量的集合，只读，不需要修改，枚举类相当于是多例模式。
 * - 枚举类都是继承了枚举类型：java.lang.Enum，枚举都是最终类，不可以被继承。
 * - 构造器都是私有的，枚举对外不能创建对象，枚举类的第一行默认都是罗列枚举对象的名称的。
 */
public class SE_16_Enum {
    public static void main(String[] args) {
        System.out.println("创建自定义枚举，打印所有枚举常量...");
        System.out.println(Custom_Season.SPRING);
        System.out.println(Custom_Season.SUMMER);
        System.out.println(Custom_Season.AUTUMN);
        System.out.println(Custom_Season.WINTER);
        System.out.println();
        System.out.println("创建(enum)枚举，打印所有枚举常量...");
        System.out.println(Season.SPRING);
        System.out.println(Season.SUMMER);
        System.out.println(Season.AUTUMN);
        System.out.println(Season.WINTER);
    }
}

/**
 * 自定义枚举
 * - 将构造器私有化，目的防止直接new。
 * - 去掉set方法，防止属性被修改。
 * - 创建固定的对象，可以加入(static final)修饰符，实现底层优化。
 */
class Custom_Season {
    public static final Custom_Season SPRING = new Custom_Season("春天", "温暖");
    public static final Custom_Season SUMMER = new Custom_Season("夏天", "炎热");
    public static final Custom_Season AUTUMN = new Custom_Season("秋天", "凉爽");
    public static final Custom_Season WINTER = new Custom_Season("冬天", "寒冷");
    private String name;
    private String desc;

    private Custom_Season() {
    }

    private Custom_Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}

/**
 * enum关键字
 * - 使用enum关键字后，就不能再继承其它类了，因为enum会隐式继Enum，而Java是单继承机制。
 * - 使用enum关键字的类，运行阶段会被加上final修饰变成最终类，不可以被继承。
 * - 使用enum关键字的枚举类，仍然是一个类，所以还是可以实现接口。
 */
enum Season {
    // 使用关键字 enum 替代 class,可以直接使用 SPRING("春天", "温暖")定义常量(对象)
    // 等价 public static final Season SPRING = new Season("春天", "温暖");
    // 解读：其实是编译器根据实参列表调用构造器创建常量，常量名(实参列表)
    // 如果使用 enum 来实现枚举，要求将定义常量对象，写在其他代码前面，如果有多个常量(对象),使用(,)间隔即可
    SPRING("春天", "温暖"),
    AUTUMN("秋天", "凉爽"),
    SUMMER("夏天", "炎热"),
    WINTER("冬天", "寒冷");
    // 如果我们使用的是无参构造器，创建常量对象，则可以省略 ()
    // SPRING, WINTER, AUTUMN, SUMMER;
    private String name;
    private String desc;

    private Season() {
    }

    private Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}