package com.study.hello;

/**
 * 继承(is-a:它是)
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
public class SE_08_Inheritance {
    public static void main(String[] args) {
        // Cat,Dog(派生类)，Animal(基类或超类)。
        // 作用：当子类继承父类后，就可以直接使用父类公共的属性和方法了。
        // 好处：提高代码复用性，减少代码冗余，增强类的功能扩展性。
        System.out.println("-----调用狗类无参构造器-----");
        Dog_Inheritance dog = new Dog_Inheritance();
        System.out.println("-----调用狗类有参构造器-----");
        Dog_Inheritance dogName = new Dog_Inheritance("旺财", 3);
        System.out.println("-----调用狗类已重写方法-----");
        dogName.eat();
        System.out.println();
        System.out.println("-----调用猫类无参构造器-----");
        Cat_Inheritance cat = new Cat_Inheritance();
        System.out.println("-----调用猫类有参构造器-----");
        Cat_Inheritance catName = new Cat_Inheritance("三花", 2);
        System.out.println("-----调用猫类已重写方法-----");
        catName.eat();
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
 * - super()和this()都只能放在构造器第一行，因此这两个方法不能共存在一个构造器。
 */
class Animal_Inheritance {
    public String name;
    public int age;

    public Animal_Inheritance() {
        System.out.println("动物类无参构造方法初始化...");
    }

    public Animal_Inheritance(String name, int age) {
        System.out.println("动物类有参构造方法初始化...");
        this.name = name;
        this.age = age;
    }

    /**
     * 方法重写
     * - 子类编写的方法和父类申明的方法(名称、返回类型、形参列表)相同，就说子类方法覆盖了父类的方法。
     * - 子类重写的方法，返回值类型需要相同或者是其子类，权限修饰符必须大于或者等于父类。
     * - @Override注解可以校验重写是否正确，同时可读性好。
     * - 私有方法不能被重写，静态方法不能重写。
     */
    public void eat() {
        System.out.println("动物正在吃饭！！！");
    }
}

class Dog_Inheritance extends Animal_Inheritance {
    public Dog_Inheritance() {
        System.out.println("狗类无参构造方法初始化...");
    }

    public Dog_Inheritance(String name, int age) {
        super(name, age);
        System.out.println("狗类有参构造方法初始化...");
    }

    @Override
    public void eat() {
        System.out.println(this.name + "正在吃狗粮！！！");
    }
}

class Cat_Inheritance extends Animal_Inheritance {
    public Cat_Inheritance() {
        System.out.println("猫类无参构造方法初始化...");
    }

    public Cat_Inheritance(String name, int age) {
        this();
        System.out.println("猫类有参构造方法初始化...");
        this.name = name;
        this.age = age;
    }

    @Override
    public void eat() {
        System.out.println(this.name + "正在吃猫粮！！！");
    }
}