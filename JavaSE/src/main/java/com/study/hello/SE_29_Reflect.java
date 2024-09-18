package com.study.hello;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射
 * - 反射是指对于任何一个Class类，在"运行的时候"都可以直接得到这个类全部成分。
 * - 在运行时，可以直接得到这个类的构造器对象：Constructor
 * - 在运行时，可以直接得到这个类的成员变量对象：Field
 * - 在运行时，可以直接得到这个类的成员方法对象：Method
 * - 这种运行时动态获取类信息以及动态调用类中成分的能力称为Java语言的反射机制。
 * - 优点：可以动态的创建和使用对象，使用灵活，作为框架技术的底层支持(动态代理)。
 * - 缺点：使用反射基本是解释执行，对执行速度有影响。
 * <p>
 * 类加载
 * - 静态加载：编译时加载相关的类，如果没有则报错，依赖性太强。
 * -- 创建对象实例(new)，静态加载
 * -- 创建子类对象实例，父类也会被加载，静态加载
 * -- 使用类的静态成员(属性、方法)时，静态加载
 * - 动态加载：运行时加载需要的类，代码没有运行到需要的类，即使该类不存在也不报错，降低了依赖性。
 * -- 通过反射，动态加载
 * <p>
 * 反射的作用
 * - 可以在运行时得到一个类的全部成分然后操作。
 * - 可以破坏封装性。(很突出)
 * - 可以破坏泛型的约束性。(很突出)
 * - 更重要的用途是适合做Java高级框架，基本上主流框架都会基于反射设计一些通用技术功能。
 * - 绕过编译阶段为集合添加数据
 * - 泛型只能在编译阶段约束集合操作的数据类型，编译成Class文件进入运行阶段的时候，泛型会自动擦除。
 * - 反射是作用在运行时的技术，此时集合已经不存在泛型约束，所以此时集合可以存入其他任意类型的元素的。
 */
public class SE_29_Reflect {
    public static void main(String[] args) throws Exception {
        // classMethod();
        // constructorMethod();
        // fieldMethod();
        methodMethod();
    }

    /**
     * 获取Class对象
     * - Class也是类，因此也继承Object类，Class类对象不是new出来的，而是系统在类加载时创建的。
     * - 每个类的类对象，在内存中只有一份，因此类只加载一次，Class对象存放在堆中。
     * - 类(内部类)，接口，注释，枚举，基本数据类型，数组，void和Class本身都存在类对象。
     * - 反射的核心思想和关键就是：得到编译以后的Class文件对象，如此才可以解析类的全部成分。
     */
    public static void classMethod() throws Exception {
        // 获取Class类对象
        // 方式一：Class.forName("全类名");
        Class<?> aClass_1 = Class.forName("java.lang.String");
        // 方式二：类名.class;
        Class<String> aClass_2 = String.class;
        // 方式三：对象.getClass();
        Class<? extends String> aClass_3 = "String".getClass();
        // 方式四：基本数据类型.class
        Class<Integer> aClass_4 = int.class;
        // 方式五：包装类.TYPE
        Class<Integer> aClass_5 = Integer.TYPE;

        //类对象
        // 类
        Class<HashMap> hashMapClass = HashMap.class;
        // 内部类
        Class<Map.Entry> entryClass = Map.Entry.class;
        // 接口
        Class<Map> mapClass = Map.class;
        // 注解
        Class<Override> overrideClass = Override.class;
        // 枚举
        Class<ElementType> elementTypeClass = ElementType.class;
        // 基本数据类型
        Class<Integer> intClass = int.class;
        // 数组
        Class<int[]> oneClass = int[].class;
        // 二维数组
        Class<int[][]> twoClass = int[][].class;
        // void
        Class<Void> voidClass = void.class;
        // Class
        Class<Class> classClass = Class.class;
    }

    /**
     * 获取Constructor对象
     * - 使用类对象方法获取构造器对象，获取构造器的作用依然是初始化一个对象返回。
     * - 如果是非public的构造器，创建私有构造器对象需要打开权限(暴力反射)再创建对象。
     * <p>
     * Constructor方法
     * - T newInstance(Object ... initargs) 根据指定的参数，调用参数一致的构造器创建对象
     * <p>
     * Class方法
     * - Constructor<T> getConstructor(Class<?>... parameterTypes) 返回指定参数一致的public构造器对象，存在就能拿到
     * - Constructor<?>[] getConstructors() 返回所有public的构造器对象数组，存在就能拿到
     * - Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) 返回指定参数一致的构造器对象，存在就能拿到
     * - Constructor<?>[] getDeclaredConstructors() 返回所有的构造器对象数组，存在就能拿到
     */
    public static void constructorMethod() throws Exception {
        // 获取类对象
        Class<Dog_Reflect> dogClass = Dog_Reflect.class;

        // 获取公开的Constructor
        Constructor<Dog_Reflect> constructor = dogClass.getConstructor(String.class);
        System.out.println(constructor);
        // 使用Constructor方法创建公开构造器对象
        System.out.println(constructor.newInstance("测试"));
        System.out.println("-----------------");

        // 获取所有公开的Constructor
        Constructor<?>[] constructors = dogClass.getConstructors();
        for (Constructor<?> c : constructors) {
            System.out.println(c);
        }
        System.out.println("-----------------");

        // 获取私有的Constructor
        Constructor<Dog_Reflect> declaredConstructor = dogClass.getDeclaredConstructor(String.class, String.class, String.class);
        System.out.println(declaredConstructor);
        // 打开权限(暴力反射)
        declaredConstructor.setAccessible(true);
        // 使用Constructor方法创建私有构造器对象
        System.out.println(declaredConstructor.newInstance("测试1", "测试2", "测试3"));
        System.out.println("-----------------");

        // 获取所有的Constructor
        Constructor<?>[] declaredConstructors = dogClass.getDeclaredConstructors();
        for (Constructor<?> c : declaredConstructors) {
            System.out.println(c);
        }
    }

    /**
     * 获取Field对象
     * - 使用类对象方法获取成员变量对象，获取成员变量的作用依然是在某个对象中取值、赋值。
     * - 如果某成员变量是非public的，使用前需要打开权限(暴力反射)，然后再取值、赋值。
     * <p>
     * Field方法
     * - Object get(Object obj)返回指定对象字段上的值
     * - void set(Object obj, Object value)将指定对象的字段设置为指定的新值
     * <p>
     * Class方法
     * - Field getField(String name)返回指定标识符相同的public变量对象，存在就能拿到(范围：本类、父类、接口)
     * - Field[] getFields()返回所有public的变量对象数组，存在就能拿到(范围：本类、父类、接口)
     * - Field getDeclaredField(String name)返回指定标识符相同的变量对象，存在就能拿到(范围：本类)
     * - Field[] getDeclaredFields()返回所有的变量对象数组，存在就能拿到(范围：本类)
     */
    public static void fieldMethod() throws Exception {
        // 获取类对象
        Class<Dog_Reflect> dogClass = Dog_Reflect.class;
        // 使用类对象，获取构造器，创建对象
        Dog_Reflect dog = dogClass.getConstructor().newInstance();

        // 获取指定公开的Field
        Field field = dogClass.getField("zgb");
        System.out.println(field.get(dog));
        // 修改dog对象的Field
        field.set(dog, "子类公共变量被修改了！！！");
        // 获取修改后Field的值
        System.out.println(field.get(dog));
        System.out.println(dog);
        System.out.println("-------------------------------");

        // 获取所有公开的Field(范围：本类、父类、接口)
        Field[] fields = dogClass.getFields();
        for (Field f : fields) {
            System.out.println(f.getType() + " " + f.getName() + "：" + f.get(dog));
        }
        System.out.println("-------------------------------");

        // 获取指定私有的Field
        Field declaredField = dogClass.getDeclaredField("zsb");
        // 打开权限(暴力反射)
        declaredField.setAccessible(true);
        // 修改dog对象的Field
        declaredField.set(dog, "子类私有变量被修改了！！！");
        // 获取修改后Field的值
        System.out.println(declaredField.get(dog));
        System.out.println(dog);
        System.out.println("-------------------------------");

        // 获取所有的Field(范围：本类)
        Field[] declaredFields = dogClass.getDeclaredFields();
        for (Field f : declaredFields) {
            f.setAccessible(true);
            System.out.println(f.getType() + " " + f.getName() + "：" + f.get(dog));
        }
    }

    /**
     * 获取Method对象
     * - 使用类对象方法获取成员方法对象，获取成员方法的作用依然是在某个对象中进行执行此方法。
     * - 如果某成员方法是非public的，调用前需要打开权限(暴力反射)，然后再触发执行。
     * <p>
     * Method方法
     * - Object invoke(Object obj, Object... args)
     * - 参数一：被调用方法的对象
     * - 参数二：调用方法时传递的参数(如果没有就不写)
     * - 返回值：方法的返回值(如果没有就不写)
     * <p>
     * Class方法
     * - Method getMethod(String name, Class<?>... parameterTypes) 返回指定标识符相同的public方法对象，存在就能拿到(范围：本类、父类)
     * - Method[] getMethods() 返回所有public的方法对象数组，存在就能拿到(范围：本类、父类)
     * - Method getDeclaredMethod(String name, Class<?>... parameterTypes) 返回指定标识符相同的方法对象，存在就能拿到(范围：本类)
     * - Method[] getDeclaredMethods() 返回所有的方法对象数组，存在就能拿到(范围：本类)
     */
    public static void methodMethod() throws Exception {
        // 获取类对象
        Class<Dog_Reflect> dogClass = Dog_Reflect.class;
        // 使用类对象，获取构造器，创建对象
        Dog_Reflect dog = dogClass.getConstructor().newInstance();

        // 获取指定公开的Method(范围：本类、父类)
        Method method = dogClass.getMethod("zgf");
        // 调用指定对象的Method
        method.invoke(dog);
        System.out.println("----------------------------------");

        // 获取所有公开的Method(范围：本类、父类)
        Method[] methods = dogClass.getMethods();
        for (Method m : methods) {
            System.out.println(m.getDeclaringClass() + "\t" + m);
        }
        System.out.println("----------------------------------");

        // 获取指定私有的Method(范围：本类)
        Method declaredMethod = dogClass.getDeclaredMethod("zsf");
        // 打开权限(暴力反射)
        declaredMethod.setAccessible(true);
        // 调用指定对象私有的Method
        declaredMethod.invoke(dog);
        System.out.println("----------------------------------");

        // 获取所有的Method(范围：本类)
        Method[] declaredMethods = dogClass.getDeclaredMethods();
        for (Method m : declaredMethods) {
            m.setAccessible(true);
            System.out.println(m.getDeclaringClass() + "\t" + m);
            System.out.print(m.getName() + "：");
            m.invoke(dog);
        }
    }
}

class Dog_Reflect extends Animal_Reflect implements Activity_Reflect {
    public String zgb = "子类公共变量";
    public static String zgjb = "子类公共静态变量";
    public final String zgc = "子类公共常量";
    public static final String zgjc = "子类公共静态常量";
    private String zsb = "子类私有变量";
    private static String zsjb = "子类私有静态变量";
    private final String zsc = "子类私有常量";
    private static final String zsjc = "子类私有静态常量";

    public Dog_Reflect() {
    }

    public Dog_Reflect(String zgb) {
        this.zgb = zgb;
    }

    private Dog_Reflect(String zgb, String zsb) {
        this.zgb = zgb;
        this.zsb = zsb;
    }

    private Dog_Reflect(String fgb, String zgb, String zsb) {
        super(fgb);
        this.zgb = zgb;
        this.zsb = zsb;
    }

    public void zgf() {
        System.out.println("子类公共方法");
    }

    public static void zgjf() {
        System.out.println("子类公共静态方法");
    }

    public final void zgzf() {
        System.out.println("子类公共最终方法");
    }

    public static final void zgjzf() {
        System.out.println("子类公共静态最终方法");
    }

    private void zsf() {
        System.out.println("子类私有方法");
    }

    private static void zsjf() {
        System.out.println("子类私有静态方法");
    }

    private final void zszf() {
        System.out.println("子类私有最终方法");
    }

    private static final void zsjzf() {
        System.out.println("子类私有静态最终方法");
    }

    @Override
    public void cx() {
        System.out.println("重写抽象方法！！！");
    }

    @Override
    public void sx() {
        System.out.println("实现接口方法！！！");
    }

    @Override
    public String toString() {
        return "Dog_Reflect{" +
                "zgb='" + zgb + '\'' +
                ", zsb='" + zsb + '\'' +
                '}';
    }
}

abstract class Animal_Reflect {
    public String fgb = "父类公共变量";
    public static String fgjb = "父类公共静态变量";
    public final String fgc = "父类公共常量";
    public static final String fgjc = "父类公共静态常量";
    private String fsb = "父类私有变量";
    private static String fsjb = "父类私有静态变量";
    private final String fsc = "父类私有常量";
    private static final String fsjc = "父类私有静态常量";

    public Animal_Reflect() {
    }

    public Animal_Reflect(String fgb) {
        this.fgb = fgb;
    }

    private Animal_Reflect(String fgb, String fsb) {
        this.fgb = fgb;
        this.fsb = fsb;
    }

    public void fgf() {
        System.out.println("父类公共方法");
    }

    public static void fgjf() {
        System.out.println("父类公共静态方法");
    }

    public final void fgzf() {
        System.out.println("父类公共最终方法");
    }

    public static final void fgjzf() {
        System.out.println("父类公共静态最终方法");
    }

    private void fsf() {
        System.out.println("父类私有方法");
    }

    private static void fsjf() {
        System.out.println("父类私有静态方法");
    }

    private final void fszf() {
        System.out.println("父类私有最终方法");
    }

    private static final void fsjzf() {
        System.out.println("父类私有静态最终方法");
    }

    public abstract void cx();

    @Override
    public String toString() {
        return "Animal_Reflect{" +
                "fgb='" + fgb + '\'' +
                ", fsb='" + fsb + '\'' +
                '}';
    }
}

interface Activity_Reflect {
    String jgjc = "接口公共静态常量";

    void sx();
}
