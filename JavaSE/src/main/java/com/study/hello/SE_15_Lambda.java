package com.study.hello;

import java.util.function.*;

public class SE_15_Lambda {
    public static void main(String[] args) {
        // lambda();
        // method();
        functional();
    }

    // lambda表达式
    public static void lambda() {
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
        // 例如：msg -> System.out.println("单独参数省略");
        Lambda_Interface multiply = (a, b) -> {
            return a * b;
        };
        // 方法体代码{}只有一条语句，可以省略{}不写，如果这行代码是return语句，return关键字以及return语句后的分号都需要同时省略。
        // 例如：() -> System.out.println("无参数省略");
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
    }

    // 方法引用
    public static void method() {
        // 匿名内部类形式
        Lambda_Interface innerAdd = new Lambda_Interface() {
            @Override
            public int operation(int a, int b) {
                // 调用一个静态方法，并且前后参数的形式一致，就可以使用静态方法引用
                return MethodCitation.add(a, b);
            }
        };
        // Lambda形式
        Lambda_Interface lambdaAdd = (a, b) -> MethodCitation.add(a, b);
        // 方法引用形式 -> 静态方法的引用(类名::静态方法)
        Lambda_Interface methodAdd = MethodCitation::add;


        // 匿名内部类形式
        MethodCitation methodCitation = new MethodCitation();
        Lambda_Interface innerSubtract = new Lambda_Interface() {
            @Override
            public int operation(int a, int b) {
                // 调用一个实例方法，并且前后参数的形式一致，就可以使用实例方法引用
                return methodCitation.subtract(a, b);
            }
        };
        // Lambda形式
        Lambda_Interface lambdaSubtract = (a, b) -> methodCitation.subtract(a, b);
        // 方法引用形式 -> 实例方法的引用(对象名::实例方法)
        Lambda_Interface methodSubtract = methodCitation::subtract;


        // 匿名内部类形式
        Method_Interface innerMultiply = new Method_Interface() {
            @Override
            public int multiply(MethodCitation method, MethodCitation citation, int a, int b) {
                // 调用一个实例方法，由第一个参数的类型对象发起，其他参数为入参的方法调用，则此时就可以使用特定类型(对象)的方法引用。
                return method.multiply(citation, a, b);
                // 因为前两个参数类型一致，所以互换也无问题(以第一个参数的类型为准)
                // return citation.multiply(method, a, b);
            }
        };
        // Lambda形式
        Method_Interface lambdaMultiply = (method, citation, a, b) -> method.multiply(citation, a, b);
        // 方法引用形式 -> 特定类型(对象)方法的引用(类型::方法)：类型指第一个形参的类型，因为调用的方法是形参的对象方法
        Method_Interface methodMultiply = MethodCitation::multiply;


        // 匿名内部类形式
        Object_Interface innerLambda = new Object_Interface() {
            @Override
            public Lambda getLambda() {
                return new Lambda();
            }
        };
        // Lambda形式
        Object_Interface lambdaLambda = () -> new Lambda();
        // 方法引用形式 -> 构造器引用(类名::new)：如果某个Lambda表达式里只是在创建对象，并且前后参数情况一致，就可以使用构造器引用。
        Object_Interface methodLambda = Lambda::new;
        // 创建对象
        Lambda lambda = methodLambda.getLambda();
        // 调用方法
        System.out.println("10 + 5 = " + lambda.operate(10, 5, methodAdd));
        System.out.println("10 - 5 = " + lambda.operate(10, 5, methodSubtract));
        System.out.println("10 * 5 = " + lambda.operate(10, 5, methodMultiply));
    }

    // Java8四大内置核心函数式接口
    public static void functional() {
        JavaInterface javaInterface = new JavaInterface();
        Lambda lambda = new Lambda("张三", 20);

        // 消费型接口(无返回值，有去无回)，执行对象方法
        javaInterface.consumer(lambda, new Consumer<Lambda>() {
            @Override
            public void accept(Lambda consumerLambda) {
                System.out.println(consumerLambda);
            }
        });
        System.out.println("---------------------------");

        // 供给型接口，返回一个对象
        Lambda supplier = javaInterface.supplier(new Supplier<Lambda>() {
            @Override
            public Lambda get() {
                return new Lambda("李四", 18);
            }
        });
        System.out.println(supplier);
        System.out.println("---------------------------");

        // 函数型接口，转换对象
        javaInterface.function(lambda, new Function<Lambda, Lambda>() {
            @Override
            public Lambda apply(Lambda functionLambda) {
                functionLambda.setName("王二");
                functionLambda.setAge(16);
                return functionLambda;
            }
        });
        System.out.println(lambda);
        System.out.println("---------------------------");

        // 断言型接口，检验对象合法性
        boolean predicate = javaInterface.predicate(lambda, new Predicate<Lambda>() {
            @Override
            public boolean test(Lambda predicateLambda) {
                return "王二".equals(predicateLambda.name);
            }
        });
        System.out.println(predicate);
    }
}

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
class Lambda {
    public String name;
    public Integer age;

    public Lambda() {
    }

    public Lambda(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Lambda{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

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

    // 调用接口方法
    public int operate(int a, int b, Method_Interface methodInterface) {
        MethodCitation methodCitation = new MethodCitation();
        return methodInterface.multiply(methodCitation, methodCitation, a, b);
    }
}

//接口
@FunctionalInterface
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

/**
 * 方法引用
 * - Lambda是用来简化匿名代码的书写格式的，而方法引用是用来进一步简化Lambda表达式的，它简化的更加过分。
 * - Java为了简化Lambda表达式的写法，将Lambda表达式的方法体封装成对应类型方法，随后直接进行方法调用，但是把参数给省略了。
 * - 方法引用对应的方法参数需要和简化前Lambda表达式的参数一致，这样就可以使用方法引用。
 * - 静态方法的引用(类名::静态方法)：如果某个Lambda表达式里只是调用一个静态方法，并且前后参数的形式一致，就可以使用静态方法引用。
 * - 实例方法的引用(对象名::实例方法)：如果某个Lambda表达式里只是调用一个实例方法，并且前后参数的形式一致，就可以使用实例方法引用。
 * - 特定类型(对象)方法的引用(类型::方法)：如果某个Lambda表达式里只是调用一个实例方法，并且前面参数列表中的第一个参数是作为方法的主调，后面的所有参数都是作为该实例方法的入参的，则此时就可以使用特定类型(对象)的方法引用。
 * - 构造器引用(类名::new)：如果某个Lambda表达式里只是在创建对象，并且前后参数情况一致，就可以使用构造器引用。
 */
class MethodCitation {
    public static int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(MethodCitation method, int a, int b) {
        return this.hashCode() - method.hashCode() + a * b;
    }
}

//接口
@FunctionalInterface
interface Method_Interface {
    int multiply(MethodCitation method, MethodCitation citation, int a, int b);
}

//接口
@FunctionalInterface
interface Object_Interface {
    Lambda getLambda();
}

/**
 * Java8四大内置核心函数式接口
 */
class JavaInterface {

    /**
     * 消费型接口(无返回值，有去无回)，执行对象方法
     */
    public void consumer(Lambda lambda, Consumer<Lambda> consumer) {
        consumer.accept(lambda);
    }

    /**
     * 供给型接口，返回一个对象
     */
    public Lambda supplier(Supplier<Lambda> supplier) {
        return supplier.get();
    }

    /**
     * 函数型接口，转换对象
     */
    public Lambda function(Lambda lambda, Function<Lambda, Lambda> function) {
        return function.apply(lambda);
    }

    /**
     * 断言型接口，检验对象合法性
     */
    public boolean predicate(Lambda lambda, Predicate<Lambda> predicate) {
        return predicate.test(lambda);
    }

    // BiConsumer<T, U>            // 代表了一个接受两个输入参数的操作，并且不返回任何结果
    // BiFunction<T, U, R>         // 代表了一个接受两个输入参数的方法，并且返回一个结果
    // BinaryOperator<T>           // 代表了一个作用于于两个同类型操作符的操作，并且返回了操作符同类型的结果
    // BiPredicate<T, U>           // 代表了一个两个参数的boolean值方法
    // BooleanSupplier             // 代表了boolean值结果的提供方
    // Consumer<T>                 // 代表了接受一个输入参数并且无返回的操作
    // DoubleBinaryOperator        // 代表了作用于两个double值操作符的操作，并且返回了一个double值的结果。
    // DoubleConsumer              // 代表一个接受double值参数的操作，并且不返回结果。
    // DoubleFunction<R>           // 代表接受一个double值参数的方法，并且返回结果
    // DoublePredicate             // 代表一个拥有double值参数的boolean值方法
    // DoubleSupplier              // 代表一个double值结构的提供方
    // DoubleToIntFunction         // 接受一个double类型输入，返回一个int类型结果。
    // DoubleToLongFunction        // 接受一个double类型输入，返回一个long类型结果
    // DoubleUnaryOperator         // 接受一个参数同为类型double, 返回值类型也为double 。
    // Function<T, R>              // 接受一个输入参数，返回一个结果。
    // IntBinaryOperator           // 接受两个参数同为类型int, 返回值类型也为int 。
    // IntConsumer                 // 接受一个int类型的输入参数，无返回值 。
    // IntFunction<R>              // 接受一个int类型输入参数，返回一个结果 。
    // IntPredicate                // 接受一个int输入参数，返回一个布尔值的结果。
    // IntSupplier                 // 无参数，返回一个int类型结果。
    // IntToDoubleFunction         // 接受一个int类型输入，返回一个double类型结果 。
    // IntToLongFunction           // 接受一个int类型输入，返回一个long类型结果。
    // IntUnaryOperator            // 接受一个参数同为类型int, 返回值类型也为int 。
    // LongBinaryOperator          // 接受两个参数同为类型long, 返回值类型也为long。
    // LongConsumer                // 接受一个long类型的输入参数，无返回值。
    // LongFunction<R>             // 接受一个long类型输入参数，返回一个结果。
    // LongPredicate               // 接受一个long输入参数，返回一个布尔值类型结果。
    // LongSupplier                // 无参数，返回一个结果long类型的值。
    // LongToDoubleFunction        // 接受一个long类型输入，返回一个double类型结果。
    // LongToIntFunction           // 接受一个long类型输入，返回一个int类型结果。
    // LongUnaryOperator           // 接受一个参数同为类型long, 返回值类型也为long。
    // ObjDoubleConsumer<T>        // 接受一个object类型和一个double类型的输入参数，无返回值。
    // ObjIntConsumer<T>           // 接受一个object类型和一个int类型的输入参数，无返回值。
    // ObjLongConsumer<T>          // 接受一个object类型和一个long类型的输入参数，无返回值。
    // Predicate<T>                // 接受一个输入参数，返回一个布尔值结果。
    // Supplier<T>                 // 无参数，返回一个结果。
    // ToDoubleBiFunction<T, U>    // 接受两个输入参数，返回一个double类型结果
    // ToDoubleFunction<T>         // 接受一个输入参数，返回一个double类型结果
    // ToIntBiFunction<T, U>       // 接受两个输入参数，返回一个int类型结果。
    // ToIntFunction<T>            // 接受一个输入参数，返回一个int类型结果。
    // ToLongBiFunction<T, U>      // 接受两个输入参数，返回一个long类型结果。
    // ToLongFunction<T>           // 接受一个输入参数，返回一个long类型结果。
    // UnaryOperator<T>            // 接受一个参数为类型T, 返回值类型也为T。
}
