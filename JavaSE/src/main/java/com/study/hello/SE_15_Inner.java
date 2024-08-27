package com.study.hello;

/**
 * 内部类
 * - 内部类就是定义在一个类里面的类，里面的类可以理解成(寄生)，外部类可以理解成(宿主)。
 * - 内部类最大特点就是可以直接访问外部类的私有属性，并且可以体现类与类之间的包含关系。
 * - 使用场景：当一个事物的内部，还有一个部分需要一个完整的结构进行描述时。
 * - 定义在类的成员位置：(成员内部类，静态内部类)
 * - 定义在类的局部位置(方法中/代码块)：(局部内部类，匿名内部类)
 * - 注意：JDK16之前，成员内部类中不能定义静态成员，JDK 16开始也可以定义静态成员了
 */
public class SE_15_Inner {
    public static void main(String[] args) {

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
     * - 注意: 成员内部类定义在外部类成员位置中,并且没有static修饰,不能定义静态方法/属性
     * - 可以添加任意访问修饰符(public、protected 、默认、private),因为它的地位相当于成员变量
     */
    public class Inner {
        // 外部类和局部内部类的成员重名时,默认遵循就近原则
        private String course = "Math";
        // 注意：JDK16之前，成员内部类中不能定义静态成员，JDK 16开始也可以定义静态成员了
        // private static String course = "Math";

        public void study() {
            // 成员内部类可以直接访问外部类的成员
            System.out.println(age + "岁的" + name + "正在学习" + course);
            // 如果重名还想访问外部类的成员，使用(外部类名.this.成员)去访问
            System.out.println(age + "岁的" + name + "正在学习" + Outer.this.course);
        }
    }

    /**
     * 静态内部类
     * - 注意: 静态内部类定义在外部类成员位置中，并且有static修饰，属于外部类本身，能定义静态方法/属性
     * - 可以添加任意访问修饰符(public、protected 、默认、private)，因为它的地位相当于成员变量(本质仍然是一个类)
     */
    static class StaticInner {
        //外部类和静态内部类的成员重名时,默认遵循就近原则
        private String name = "李四";
        private static String course = "Math";

        public void study() {
            //静态内部类可以直接访问外部类的静态成员
            System.out.println(age + "岁的" + name + "正在学习" + course);
            //静态内部类不能直接访问外部类的实例成员，需要创建外部类对象访问
            System.out.println(age + "岁的" + new Outer().name + "正在学习" + course);
            //如果重名还想访问外部类的成员，使用(外部类名.成员)去访问
            System.out.println(age + "岁的" + new Outer().name + "正在学习" + new Outer().course);
        }

        public static void sleep() {
            //静态内部类的静态方法访问实例变量，需要创建静态内部类对象访问
            System.out.println(age + "岁的" + new StaticInner().name + "正在睡觉" + new Outer().course);
        }
    }

    /**
     * 局部内部类
     * - 局部内部类可以直接访问外部类的所有成员，包括私有的，默认遵循就近原则。
     * - 局部内部类定义在外部类方法和代码块中，作用域仅仅在定义它的方法或代码块中，不能定义静态方法/属性。
     * - 不能添加访问修饰符，但是可以使用final修饰，因为它的地位相当于局部变量(本质仍然是一个类) 。
     * - 外部类和局部内部类的成员重名时，局部内部类还想访问外部类的成员，使用(外部类名.this.成员)去访问。
     * - 外部类访问局部内部类的成员，需要在局部内部类的作用域内，创建对象再访问。
     * - 外部其他类不能访问局部内部类，因为局部内部类地位相当于局部变量。
     */
    public void study() {
        //局部内部类是定义在外部类的局部位置，通常在方法和代码块中
        //不能添加访问修饰符，但是可以使用final修饰
        //作用域：仅仅在定义它的方法或代码块中,不能定义静态方法/属性
        final class Inner { // 局部内部类(本质仍然是一个类)
            //外部类和局部内部类的成员重名时，默认遵循就近原则
            private String course = "Math";

            public void study() {
                //局部内部类可以直接访问外部类的成员
                System.out.println(age + "岁的" + name + "正在学习" + course);
                //如果重名还想访问外部类的成员，使用(外部类名.this.成员)去访问
                System.out.println(age + "岁的" + name + "正在学习" + Outer.this.course);
            }
        }
        //外部类在方法中，可以创建Inner对象，然后调用方法即可
        Inner inner = new Inner();
        inner.study();
    }
}