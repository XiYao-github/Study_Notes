package com.study.hello;

/**
 * 泛型
 * - 泛型又称参数化类型，用来解决数据类型的安全性问题，可以在编译阶段约束只能操作某种数据类型。
 * - 注意：集合和泛型都只能支持引用数据类型，不支持基本数据类型，所以集合中存储的元素都认为是对象。
 * - 泛型的本质：把具体的数据类型(引用数据类型)作为参数传给类型变量。
 * <p>
 * 泛型的好处
 * - 统一数据类型，编译阶段类型就能确定下来，提高了安全性。
 * - 统一数据类型，减少了类型转换的次数，提高了代码效率。
 * - 把运行时期的问题提前到了编译期间，避免了强制类型转换可能出现的异常。
 * - 泛型确定类型后，可传入类型为确定类型和及其子类，如果没有指定类型，默认为 Object 类型。
 * <p>
 * 泛型的擦除
 * - 泛型是工作在编译阶段的，一旦程序编译成class文件，class文件中就不存在泛型了，这就是泛型擦除。
 * - 泛型不支持基本数据类型，只能支持对象类型（引用数据类型）。
 * <p>
 * 泛型通配符
 * <E>：Element (在集合中使用，因为集合中存放的是元素)
 * <T>：Type（Java 类）
 * <K>：Key（键）
 * <V>：Value（值）
 * <N>：Number（数值类型）
 * <?>：表示不确定的java类型，可以接受任意的类型
 * <? extends A>：表示可以接受的必须是A或者A的子类(泛型上限)
 * <? super A>：表示可以接受的必须是A或者A的夫类(泛型下限)
 */
public class SE_20_Generics {
    public static void main(String[] args) {
        GenericsClass<String> stringUser = new GenericsClass<>("str");
        stringUser.method("str");
        stringUser.genericsMethod(100);
        GenericsClass<Integer> integerUser = new GenericsClass<>(100);
        integerUser.method(100);
        stringUser.genericsMethod("str");
        GenericsClass<Double> doubleUser = new GenericsClass<>(99.99);
        doubleUser.method(99.99);
        doubleUser.genericsMethod(100);
    }
}

/**
 * 泛型类
 * - 属性和方法都可以使用泛型，泛型标识符可以有多个，一般是单个大写字母，常见地如(E、T、K、V、N)等。
 * - 泛型数组不能初始化，因为数组不能确定类型，所有无法在内存开辟空间初始化，只能定义泛型数组。
 * - 静态方法中不能使用类的泛型，静态是和类相关的，在类加载时，对象还没有创建，JVM无法完成初始化。
 * - 泛型类的类型，会在创建对象的时候确定，如果没有指定类型，默认为 Object 类型。
 * - 作用：编译阶段可以指定数据类型，类似于集合的作用。
 * - 原理：把出现泛型变量的地方全部替换成传输的真实数据类型。
 */
// 泛型类(完整定义格式)：
/*修饰符 class 类名<泛型变量> {
}*/
class GenericsClass<T> implements GenericsInterface<T> {
    private T t;
    private T[] arr;

    public GenericsClass(T t) {
        this.t = t;
    }

    /**
     * 泛型方法
     * - 泛型方法可以定义在普通类和泛型类中，当泛型方法被调用时，类型就会确定。
     * - 修饰符后面声明了<泛型变量>才是泛型方法，不然只是方法使用了泛型。
     * - 作用：方法中可以使用泛型接收一切实际类型的参数，方法更具备通用性。
     * - 原理：把出现泛型变量的地方全部替换成传输的真实数据类型。
     */
    // 泛型方法(完整定义格式)：
    /*修饰符 <泛型变量> 方法返回值 方法名称(形参列表) {
    }*/
    // 是泛型方法，定义了泛型，调用方法时确定方法的泛型类型
    public <R> R genericsMethod(R r) {
        System.out.println("genericsMethod:" + r.getClass());
        return r;
    }

    // 不是泛型方法，只是方法使用了类定义的泛型
    @Override
    public T method(T t) {
        System.out.println("method:" + t.getClass());
        return t;
    }
}

/**
 * 泛型接口
 * - 接口中属性无法使用泛型，因为接口属性都是(static final)修饰的常量，但是定义的抽象方法可以使用泛型。
 * - 泛型接口的类型，在继承接口或者实现接口时确定，如果没有指定类型，默认为 Object 类型。
 * - 作用：泛型接口可以让实现类选择当前功能需要操作的数据类型。
 * - 原理：实现类可以在实现接口的时候传入自己操作的数据类型，这样重写的方法都将是针对于该类型的操作。
 */
// 泛型接口(完整定义格式)：
/*修饰符 interface 接口名称 <泛型变量> {
}*/
interface GenericsInterface<T> {
    public T method(T t);
}