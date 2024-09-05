package com.study.hello;

/**
 * 抽象类
 * - 抽象类可以理解成不完整的设计图，一般作为父类，让子类来继承。
 * - 当父类知道子类一定要完成某些行为，但是每个子类该行为的实现又不同，于是该父类就把该行为定义成抽象方法的形式，具体实现交给子类去完成。此时这个类就可以声明成抽象类。
 * - abstract是抽象的意思，可以修饰类、成员方法。
 * - abstract修饰类，这个类就是抽象类
 * - abstract修饰方法，这个方法就是抽象方法
 * <p>
 * 抽象类特征
 * - 最重要的特征：得到了抽象方法，失去了创建对象的能力(有得有失)。
 * - 抽象类中不一定有抽象方法，有抽象方法的类一定是就是抽象类。
 * - 抽象方法不能使用private(不能重写)、static(不能重写)、final(不能继承)修饰，因为这些关键字都和方法重写相违背。
 * - 一个类继承了抽象类必须重写完抽象类的全部抽象方法，否则这个类也是一个抽象类。
 * - 抽象类的本质还是类，类的成员(变量、方法、构造器)抽象类都具备。
 */
public class SE_11_Abstraction {
    public static void main(String[] args) {
        Animal_Abstraction animal = new Pig_Abstraction();
        animal.eat("佩奇");

        // 模板方法
        TemplateAbstract anAbstract = new TemplateAbstract();
        anAbstract.template();
    }
}

// 抽象类与抽象方法(完整定义格式)：
/*修饰符 abstract class 类名 {
	修饰符 abstract 返回值类型 方法名称(形参列表);
}*/
abstract class Animal_Abstraction {
    // 抽象方法就是没有实现的方法，只有方法声明，没有方法体。
    public abstract void eat(String name);
}

class Pig_Abstraction extends Animal_Abstraction {
    @Override
    public void eat(String name) {
        System.out.println(name + "正在吃东西！！！");
    }
}

/**
 * 模板方法
 * - 创建多个需要实现的抽象方法
 * - 创建一个固定执行抽象方法的方法，使用final修饰直接给对象使用。
 */
abstract class Template {
    public abstract void eat();

    public abstract void drink();

    public abstract void play();

    public abstract void happy();

    public void template() {
        eat();
        drink();
        play();
        happy();
    }
}

class TemplateAbstract extends Template {
    @Override
    public void eat() {
        System.out.print("吃");
    }

    @Override
    public void drink() {
        System.out.print("喝");
    }

    @Override
    public void play() {
        System.out.print("玩");
    }

    @Override
    public void happy() {
        System.out.print("乐");
    }
}