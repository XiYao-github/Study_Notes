package com.study.hello;

/**
 * 多态
 * - 方法或对象具有多种形态，是面向对象的第三大特征，多态是建立在封装和继承基础之上的。
 * - 多态的前提：有继承/实现关系，有父类引用指向子类对象，存在方法重写(多态侧重行为多态)。
 * - 注意事项：多态是对象，行为的多态，Java中的属性(成员变量)不谈多态。
 * - 定义方法可以使用父类型作为参数，该方法就可以接收这父类的一切子类对象，体现出多态的扩展性与便利。
 * <p>
 * 访问特点
 * - 一个对象的编译类型和运行类型可以不一致。
 * - 编译类型在定义对象时，就确定了，不能改变，运行类型是可以改变的。
 * - 方法调用：编译看左边，运行看右边。
 * - 变量调用：编译看左边，运行也看左边。(注意)
 */
public class SE_13_Polymorphism {
    public static void main(String[] args) {
        // 多态的常见形式：父类类型 对象名称 = new 子类构造器;
        // Animal_Polymorphism animal = new Dog_Polymorphism();

        /**
         * 向上转型(从子到父)
         * - 父类类型 对象名称 = new 子类构造器;
         * - 可以调用父类中的所有成员(需遵守访问权限)
         * - 不能调用子类的特有的成员，因为在编译阶段，能调用哪些成员，是由编译类型来决定的
         * - 最终运行效果看子类(运行类型)的具体实现， 即调用方法时，按照从子类(运行类型)开始查找方法调用
         */
        Animal_Polymorphism animal = new Dog_Polymorphism();
        // 变量调用：编译看左边，运行也看左边(注意)
        System.out.println(animal.name);
        // 方法调用：编译看左边，运行看右边
        System.out.println(animal.sleep());
        // 无法调用子类独有的功能
        // animal.eat();

        /**
         * 从父到子(必须进行强制类型转换，否则报错)
         * - 子类类型 对象名称 =（子类类型）父类引用;
         * - 有继承关系/实现就可以进行强制转换，编译无问题
         * - 作用：可以解决多态下的劣势，可以实现调用子类独有的功能
         * - Java建议强转转换前使用instanceof判断当前对象的真实类型，再进行强制转换
         */
        if (animal instanceof Dog_Polymorphism) {
            Dog_Polymorphism dog = (Dog_Polymorphism) animal;
            // 可以调用子类独有的功能
            dog.eat();
        }
        // 运行时，如果发现强制转换后的类型不是对象真实类型则报错(ClassCastException)
        // Cat_Polymorphism cat = (Cat_Polymorphism) animal;

        /**
         * 动态绑定
         * - 当调用对象方法时，该方法会和该对象的内存地址/运行类型绑定。
         * - 当调用对象属性时，没有动态绑定机制，哪里声明，那里使用。
         */
        Animal_Polymorphism dog = new Dog_Polymorphism();
        Animal_Polymorphism cat = new Cat_Polymorphism();
        //调用重写方法，运行类型为Dog，在Dog类中运行
        //运行方法：调用Dog类中的getAge()方法，使用Dog类中的age变量
        System.out.println(dog.Age_1()); //3+3
        //调用父类方法，运行类型为Dog，在Animal类中运行
        //运行方法：调用Dog类中的getAge()方法，使用Animal类中的age变量
        System.out.println(dog.Age_2()); //3+5
        //调用父类方法，运行类型为Cat，在Animal类中运行
        //运行方法：调用Cat类中的getAge()方法
        System.out.println(cat.Age_1()); //2+2
        //调用父类方法，运行类型为Cat，在Animal类中运行
        //运行方法：调用Cat类中的getAge()方法，使用Animal类中的age变量
        System.out.println(cat.Age_2()); //2+5
    }
}

class Animal_Polymorphism {
    public String name = "动物";
    public int age = 5;

    public int getAge() {
        return age;
    }

    public int Age_1() {
        return getAge() + 2;
    }

    public int Age_2() {
        return getAge() + age;
    }


    public String sleep() {
        return this.name + "正在睡觉！！！";
    }
}

class Dog_Polymorphism extends Animal_Polymorphism {
    private String name = "狗";
    private int age = 3;

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public int Age_1() {
        return getAge() + age;
    }

    @Override
    public String sleep() {
        return this.name + "正在睡觉！！！";
    }

    public String eat() {
        return this.name + "正在吃狗粮！！！";
    }
}

class Cat_Polymorphism extends Animal_Polymorphism {
    private String name = "猫";
    private int age = 2;

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String sleep() {
        return this.name + "正在睡觉！！！";
    }

    public String eat() {
        return this.name + "正在吃猫粮！！！";
    }
}