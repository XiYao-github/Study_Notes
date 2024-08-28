package com.study.hello;

/**
 * 内部类
 * - 内部类就是定义在一个类里面的类，里面的类可以理解成(寄生)，外部类可以理解成(宿主)。
 * - 内部类最大特点就是可以直接访问外部类的私有属性，并且可以体现类与类之间的包含关系。
 * - 使用场景：当一个事物的内部，还有一个部分需要一个完整的结构进行描述时。
 * - 定义在类的成员位置：(成员内部类，静态内部类)
 * - 定义在类的局部位置(方法中/代码块)：(局部内部类，匿名内部类)
 * - 注意：JDK16之前，成员内部类中不能定义静态成员，JDK16开始也可以定义静态成员了
 */
public class SE_15_Inner {
    public static void main(String[] args) {
        System.out.println("创建外部类对象，再创建成员内部类对象，调用成员内部类成员方法...");
        new Outer().new Inner().study();
        System.out.println();

        System.out.println("创建静态内部类，调用静态内部类成员方法...");
        new Outer.StaticInner().study();
        System.out.println();

        System.out.println("不创建额外对象，直接调用静态内部类静态方法...");
        Outer.StaticInner.sleep();
        System.out.println();

        System.out.println("创建外部类对象，调用包含局部内部类的方法...");
        new Outer().study();
        System.out.println();

        System.out.println("创建外部类对象，调用基于接口的匿名内部类方法...");
        new Outer().interfaceMethod();
        System.out.println();

        System.out.println("创建外部类对象，调用基于抽象类的匿名内部类方法...");
        new Outer().abstractMethod();
    }
}

// 内部类(完整定义格式)：
/*public class Outer {
    // 成员位置的内部类，相当于成员变量
    public class Inner {
    }
    // 局部位置的内部类(方法中/代码块)，相当于局部变量
    public void Method() {
        final class Inner {
        }
    }
}*/
class Outer {
    private String name = "张三";
    private static int age = 18;
    private String course = "English";

    /**
     * 成员内部类
     * - 成员内部类可以直接访问外部类的所有成员，包括私有的，默认遵循就近原则。
     * - 成员内部类定义在外部类成员位置中，并且没有static修饰，不能定义静态方法/属性。
     * - 可以添加任意访问修饰符(public、protected 、默认、private)，因为它的地位相当于成员变量(本质仍然是一个类)。
     * - 外部类和成员内部类的成员重名时，局部内部类还想访问外部类的成员，使用(外部类名.this.成员)去访问。
     * - 外部类访问成员内部类的成员，需要创建成员内部类对象再访问。
     * - 外部其他类访问成员内部类，需要先创建外部类对象，再创建成员内部类对象，然后访问。
     */
    public class Inner {
        // 外部类和局部内部类的成员重名时,默认遵循就近原则
        private String course = "Math";
        // 注意：JDK16之前，成员内部类中不能定义静态成员，JDK 16开始也可以定义静态成员了
        // private static int age = 20;

        public void study() {
            // 成员内部类可以直接访问外部类的成员
            System.out.println(age + "岁的" + name + "正在学习" + course);
            // 如果重名还想访问外部类的成员，使用(外部类名.this.成员)去访问
            System.out.println(age + "岁的" + name + "正在学习" + Outer.this.course);
        }
    }

    /**
     * 静态内部类
     * - 静态内部类可以直接访问外部类的所有静态成员，包括私有的，但不能直接访问外部类的实例成员，需要创建对象访问，默认遵循就近原则。
     * - 静态内部类定义在外部类成员位置中，并且有static修饰，属于外部类本身，能定义静态方法/属性。
     * - 可以添加任意访问修饰符(public、protected 、默认、private)，因为它的地位相当于成员变量(本质仍然是一个类)。
     * - 外部类和静态内部类的成员重名时，静态内部类还想访问外部类的成员，使用(外部类名.成员)去访问。
     * - 外部类访问静态内部类的成员，需要创建静态内部类对象再访问。
     * - 外部其他类访问静态内部类，需要创建外部类对象，然后使用外部类对象直接访问静态内部类。
     * - 外部其他类访问静态内部类的静态方法，可以直接使用(外部类名.静态内部类名.成员)访问。
     */
    static class StaticInner {
        // 外部类和静态内部类的成员重名时,默认遵循就近原则
        private String name = "李四";
        private static String course = "Math";

        public void study() {
            // 静态内部类可以直接访问外部类的静态成员，默认遵循就近原则。
            System.out.println(age + "岁的" + name + "正在学习" + course);
            // 静态内部类不能直接访问外部类的实例成员，需要创建外部类对象访问
            // 如果重名还想访问外部类的成员，使用(外部类名.成员)去访问
            System.out.println(age + "岁的" + new Outer().name + "正在学习" + new Outer().course);
        }

        public static void sleep() {
            // 静态内部类的静态方法访问静态内部类的实例变量，需要创建静态内部类对象访问
            System.out.println(age + "岁的" + new StaticInner().name + "正在睡觉" + new Outer().course);
        }
    }

    public void study() {
        /**
         * 局部内部类
         * - 局部内部类可以直接访问外部类的所有成员，包括私有的，默认遵循就近原则。
         * - 局部内部类定义在外部类方法和代码块中，作用域仅仅在定义它的方法或代码块中，不能定义静态方法/属性。
         * - 不能添加访问修饰符，但是可以使用final修饰，因为它的地位相当于局部变量(本质仍然是一个类) 。
         * - 外部类和局部内部类的成员重名时，局部内部类还想访问外部类的成员，使用(外部类名.this.成员)去访问。
         * - 外部类访问局部内部类的成员，需要在局部内部类的作用域内，创建对象再访问。
         * - 外部其他类不能访问局部内部类，因为局部内部类地位相当于局部变量。
         */
        final class Inner {
            // 外部类和局部内部类的成员重名时，默认遵循就近原则
            private String course = "Math";

            public void study() {
                // 局部内部类可以直接访问外部类的成员
                System.out.println(age + "岁的" + name + "正在学习" + course);
                // 如果重名还想访问外部类的成员，使用(外部类名.this.成员)去访问
                System.out.println(age + "岁的" + name + "正在学习" + Outer.this.course);
            }

        }
        // 外部类在方法中，可以创建Inner对象，然后调用方法即可
        Inner inner = new Inner();
        inner.study();
    }

    /**
     * 匿名内部类
     * - 匿名内部类是一个没有名字的内部类，同时也代表一个对象，最终目的是为了简化代码编写。
     * - 匿名内部类的对象类型，相当于是当前new的那个类型的子类类型，但是运行类型还是匿名内部类。
     * - 匿名内部类可以作为一个对象，当做实参直接传递给方法，简洁高效，外部其他类不能访问匿名内部类。
     * - 匿名内部类通常是在开发中调用别人的方法时，别人需要我们写的时候才会定义出来使用。
     */
    public void interfaceMethod() {
        // 基于接口的匿名内部类
        // action编译类型：Outer_Interface
        // action运行类型：Outer$1
        Outer_Interface outerInterface = new Outer_Interface() {
            @Override
            public void sleep() {
                // 可以直接访问外部类的所有成员,包含私有的
                System.out.println(name + "正在睡觉！！！");
                // 如果重名还想访问外部类的成员，使用(外部类名.this.成员)去访问
                System.out.println(Outer.this.name + "正在睡觉！！！");
            }
        };
        outerInterface.sleep();
        System.out.println(outerInterface.getClass());
    }

    public void abstractMethod() {
        // 基于抽象类的匿名内部类
        // animal编译类型：Outer_Abstract
        // animal运行类型：Outer$2
        Outer_Abstract outerAbstract = new Outer_Abstract() {
            @Override
            public void eat() {
                // 可以直接访问外部类的所有成员,包含私有的
                System.out.println(name + "正在吃饭！！！");
                // 如果重名还想访问外部类的成员，使用(外部类名.this.成员)去访问
                System.out.println(Outer.this.name + "正在吃饭！！！");
            }
        };
        outerAbstract.eat();
        System.out.println(outerAbstract.getClass());
    }

    //接口
    interface Outer_Interface {
        public static final String name = "动物";

        public void sleep();
    }

    //抽象类
    abstract class Outer_Abstract {
        public static final String name = "大动物";

        public abstract void eat();
    }
}