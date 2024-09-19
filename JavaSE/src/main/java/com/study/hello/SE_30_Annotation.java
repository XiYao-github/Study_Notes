package com.study.hello;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
 * 内置的注解
 * - Java 定义了一套注解，共有 7 个，3 个在 java.lang 中，剩下 4 个在 java.lang.annotation 中。
 * - 作用在代码的注解是
 * - @Override - 检查该方法是否是重写方法。如果发现其父类，或者是引用的接口中并没有该方法时，会报编译错误。
 * - @Deprecated - 标记过时方法。如果使用该方法，会报编译警告。
 * - @SuppressWarnings - 指示编译器去忽略注解中声明的警告。
 * - 作用在其他注解的注解(或者说 元注解)是:
 * - @Retention - 标识这个注解怎么保存，是只在代码中，还是编入class文件中，或者是在运行时可以通过反射访问。
 * - @Documented - 标记这些注解是否包含在用户文档中。
 * - @Target - 标记这个注解应该是哪种 Java 成员。
 * - @Inherited - 标记这个注解是继承于哪个注解类(默认 注解并没有继承于任何子类)
 * - 从 Java 7 开始，额外添加了 3 个注解:
 * - @SafeVarargs - Java 7 开始支持，忽略任何使用参数为泛型变量的方法或构造函数调用产生的警告。
 * - @FunctionalInterface - Java 8 开始支持，标识一个匿名函数或函数式接口。
 * - @Repeatable - Java 8 开始支持，标识某注解可以在同一个声明上使用多次。
 * <p>
 * 元注解
 * - @Retention 定义了该注解的生命周期，作用就是说明这个注解的存活时间。
 * -- RetentionPolicy.SOURCE: 注解只在源码阶段保留，在编译器完整编译之后，它将被丢弃忽视；例：@Override, @SuppressWarnings
 * -- RetentionPolicy.CLASS: 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中；
 * -- RetentionPolicy.RUNTIME: 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们；
 * - @Target 表示该注解用于什么地方，可以理解为这个注解就被限定了运用的场景。
 * -- ElementType.TYPE: 对类、接口、枚举进行注解；
 * -- ElementType.FIELD: 对属性、成员变量、成员对象（包括 enum 实例）进行注解；
 * -- ElementType.METHOD: 对方法进行注解；
 * -- ElementType.PARAMETER: 对描述参数进行注解；
 * -- ElementType.CONSTRUCTOR: 对构造方法进行注解；
 * -- ElementType.LOCAL_VARIABLE: 对局部变量进行注解；
 * -- ElementType.ANNOTATION_TYPE: 对注解进行注解；
 * -- ElementType.PACKAGE: 对包进行注解；
 * - @Documented 是一个简单的标记注解，表示是否将注解信息添加在 Java 文档，即 Javadoc 中。
 * - @Inherited 指继承。如果一个超类带有 @Inherited 注解，它的子类如果没有被任何注解应用的话，那么这个子类就继承了超类的注解。
 * - @Repeatable 是 Java 8 中加入的，是指可重复的意思。通常使用 @Repeatable 的时候指注解的值可以同时取多个
 */
public class SE_30_Annotation {
}


/**
 * 自定义注解
 * - value属性，如果只有一个value属性的情况下，使用value属性的时候可以省略value名称不写。
 * - 但是如果有多个属性，且多个属性没有默认值，那么value名称是不能省略的。
 * - 注解类型定义为 @interface，所有的注解会自动继承 java.lang.Annotation 这一接口，而且不能再去继承其他的类或接口；
 * - 参数成员只能用 public 或 default 两个关键字修饰；
 * - 参数成员只能用基本类型：byte, short, char, int, long, float, double, boolean，以及 String, Enum, Class, Annotations 等数据类型，以及这些类型的数组；
 * - 要获取类方法和字段的注解信息，必须通过 Java 的反射技术；
 * - 注解也可以不定义成员变量，但这样的注解没有什么卵用；
 * - 自定义注解需要使用元注解进行编写；
 */
// 注解(完整定义格式)：
/*public @interface 注解名称 {
	public 属性类型 属性名() default 默认值;
}*/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
@interface Student {
    // 注解只有成员变量，没有方法，注解的成员变量在注解的定义中以无形参的方法形式来声明，其方法名定义了该成员变量的名字，其返回值定义了该成员变量的类型。
    public String name() default "张三";

    public int age() default 18;

    public double score();
}

/**
 * 注解的解析
 * - 注解的操作中经常需要进行解析，注解的解析就是判断是否存在注解，存在注解就解析出内容。
 * - Annotation：注解的顶级接口，注解都是Annotation类型的对象。
 * - AnnotatedElement：该接口定义了与注解解析相关的解析方法。
 * - Class，Method，Field，Constructor，都实现了AnnotatedElement接口都拥有解析注解的能力。
 * <p>
 * 注解方法
 * - Annotation[] getDeclaredAnnotations() 获得当前对象上使用的所有注解，返回注解数组
 * - T getDeclaredAnnotation(Class<T> annotationClass) 根据注解类型获得对应注解对象
 * - boolean isAnnotationPresent(Class<Annotation> annotationClass) 判断当前对象是否使用了指定的注解，如果使用了则返回true，否则false
 * <p>
 * 解析注解技巧
 * - 注解在哪个成分上，我们就先拿哪个成分对象。
 * - 比如注解作用在类上，则要该类的Class对象，再来拿上面的注解。
 * - 比如注解作用在成员方法上，则要获得该成员方法对应的Method对象，再来拿上面的注解。
 * - 比如注解作用在成员变量上，则要获得该成员变量对应的Field对象，再来拿上面的注解。
 */
@interface Teacher {
    public String name();

    public int age();
}

@Student(name = "李四", age = 20, score = 66.99)
class StudentA {
}

// value属性，如果只有一个value属性的情况下，使用value属性的时候可以省略value名称不写
@Student(score = 99.66)
class StudentB {
}

// 但是如果有多个属性，且多个属性没有默认值，那么value名称是不能省略的
@Teacher(name = "李四", age = 30)
class StudentC {
}