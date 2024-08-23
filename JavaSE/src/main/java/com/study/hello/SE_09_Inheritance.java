package com.study.hello;

/**
 * 继承
 * - 继承可以解决代码复用，让我们的编程更加靠近人类思维。
 * - Java中提供一个关键字extends，用这个关键字，我们可以让一个类和另一个类建立起父子关系。
 * - 当多个类存在相同的变量/方法时，可以从这些类中抽象出父类，在父类中定义这些相同的属性/方法，所有的子类不需要重新定义这些属性和方法，只需要通过extends来声明继承父类即可。
 * <p>
 * 设计规范
 * - 子类们相同特征(共性属性，共性方法)放在父类中定义。
 * - 子类独有的的属性和行为应该定义在子类自己里面。
 * <p>
 * 特点
 * - 子类可以继承父类的属性和行为，但是子类不能继承父类的构造器。
 * - 子类没有继承父类静态成员，只能共享的去访问父类静态成员，共享并非继承。
 * - 引入继承后，子类对象的创建是由子类和父类共同创建出来的。
 * - 创建子类对象时，先调用父类构造器再调用子类构造器，可以使用父类属性值初始化子类属性值。
 * - Java是单继承模式，一个类只能继承一个直接父类。
 * - Java类不支持多继承，接口支持多继承，但是类支持多层继承。
 * - Java中所有的类都是Object类的子类。
 */
public class SE_09_Inheritance {
    public static void main(String[] args) {
        // Cat,Dog(派生类)，Animal(基类或超类)。
        // 作用：当子类继承父类后，就可以直接使用父类公共的属性和方法了。
        // 好处：提高代码复用性，减少代码冗余，增强类的功能扩展性。
        System.out.println("-----调用狗类无参构造器-----");
        Dog dog = new Dog();
        System.out.println("-----调用狗类有参构造器-----");
        Dog dogName = new Dog("旺财", 3);
        System.out.println();
        System.out.println("-----调用猫类无参构造器-----");
        Cat cat = new Cat();
        System.out.println("-----调用猫类有参构造器-----");
        Cat catName = new Cat("三花", 2);
    }
}

/**
 * this和super
 * - this：代表本类对象的引用；
 * - super：代表父类对象的引用。
 * - this和super都一样，子类没有找到此成员变量/方法，会继续从父类找，追溯到Object类还没找到会报错。
 * - this默认从本类找，super默认从父类开始找。满足：就近原则。
 * - 子类中所有的构造器默认都会先访问父类中无参的构造器，再执行自己。
 * - 子类构造器的第一行语句默认都是super()，不写也存在。
 * - super()和this()都只能放在构造器第一行，因此这两个方法不能共存在一个构造器 。
 */
class Animal {
    String name;
    int age;

    public Animal() {
        System.out.println("动物类无参构造方法初始化...");
    }

    public Animal(String name, int age) {
        System.out.println("动物类有参构造方法初始化...");
        this.name = name;
        this.age = age;
    }
}

class Dog extends Animal {
    public Dog() {
        System.out.println("狗类无参构造方法初始化...");
    }

    public Dog(String name, int age) {
        super(name, age);
        System.out.println("狗类有参构造方法初始化...");
    }
}

class Cat extends Animal {
    public Cat() {
        System.out.println("猫类无参构造方法初始化...");
    }

    public Cat(String name, int age) {
        this();
        System.out.println("猫类有参构造方法初始化...");
        this.name = name;
        this.age = age;
    }
}