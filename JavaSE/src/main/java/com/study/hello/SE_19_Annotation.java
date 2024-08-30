package com.study.hello;

/**
 * 注解
 * - Java注解(Annotation)又称Java标注，也被称为元数据(Metadata)，JDK5引入的一种注释机制。
 * - Java语言中的包、类、构造器、方法、属性、参数等都可以被注解进行标注，然后进行特殊处理。
 * - 和注释一样，注解不影响程序逻辑，但注解可以被编译或运行，相当于嵌入在代码中的补充信息。
 * - 在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。
 * - 在JavaEE中注解占据了更重要的角色，例如用来配置应用程序的任何切面，代替javaEE旧版中所遗留的繁冗代码和XML配置等。
 * - @Override：限定某个方法，是重写父类方法，该注解只能用于修饰方法
 * - @Deprecated：用于表示某个程序元素(类，方法等)已过时
 * - @SuppressWarnings：抑制编译器警告，通常放置语句，方法，类上
 * <p>
 * 注解的解析
 * - 注解的操作中经常需要进行解析，注解的解析就是判断是否存在注解，存在注解就解析出内容。
 * - Annotation：注解的顶级接口，注解都是Annotation类型的对象。
 * - AnnotatedElement：该接口定义了与注解解析相关的解析方法。
 * - Class，Method，Field，Constructor，都实现了AnnotatedElement接口都拥有解析注解的能力。
 * <p>
 * 解析注解技巧
 * - 注解在哪个成分上，我们就先拿哪个成分对象。
 * - 比如注解作用在类上，则要该类的Class对象，再来拿上面的注解。
 * - 比如注解作用在成员方法上，则要获得该成员方法对应的Method对象，再来拿上面的注解。
 * - 比如注解作用在成员变量上，则要获得该成员变量对应的Field对象，再来拿上面的注解。
 */
public class SE_19_Annotation {
}


/**
 * 自定义注解
 * - value属性，如果只有一个value属性的情况下，使用value属性的时候可以省略value名称不写。
 * - 但是如果有多个属性，且多个属性没有默认值，那么value名称是不能省略的。
 */
// 注解(完整定义格式)：
/*public @interface 注解名称 {
	public 属性类型 属性名() default 默认值;
}*/
@interface Student {
    public String name() default "张三";

    public int age() default 18;

    public double score();
}

@interface Teacher {
    public String name();

    public int age();
}

@Student(name = "李四", age = 20, score = 66.99)
class StudentA {
}

//value属性，如果只有一个value属性的情况下，使用value属性的时候可以省略value名称不写
@Student(score = 99.66)
class StudentB {
}

//但是如果有多个属性，且多个属性没有默认值，那么value名称是不能省略的
@Teacher(name = "李四", age = 30)
class StudentC {
}