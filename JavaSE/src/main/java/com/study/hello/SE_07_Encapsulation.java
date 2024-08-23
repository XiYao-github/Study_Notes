package com.study.hello;

/**
 * 包
 * - 区分相同名字的类，控制访问权限，便于管理类
 * - 包是用来分门别类的管理各种不同程序的，类似于文件夹，建包有利于程序的管理和维护。
 * - 相同包下的类，互相可以直接调用，Java.lang包下的程序不需要导包，可以直接使用。
 * - 不同包下的类，必须先导入对应包才可调用。导包格式：import 包名.类名;
 * - 不同包下的类，假如出现同名类，默认只能导入一个类，另一个类必须带包名访问。
 * <p>
 * 封装
 * - 封装：解决属性和方法属于哪个对象的问题。
 * - 封装步骤：通常将成员变量私有、提供方法进行暴露。
 * - 封装作用：提高业务功能设计的安全性，提高开发效率。
 * <p>
 * 封装思想
 * - 让编程变得很简单，有什么事，找对象，调方法就行。
 * - 例如：String：代表字符串对象、Socket：代表一个网络连接。
 * - 降低我们的学习成本，可以少学，少记，或者说压根不用学，不用记对象的那么多方法，有需要时去找就行。
 * <p>
 * 权限修饰符
 * - 控制成员方法/变量能够被访问的范围，只有默认缺省和public可以修饰类。
 * - 可以修饰成员变量，方法，构造器，内部类，不同权限修饰符修饰的成员能够被访问的范围将受到限制。
 * - 有四种作用范围由小到大(private(本类) -> 缺省(同一个包中) -> protected(同一个包及子类中) -> public(不同包中))
 */
public class SE_07_Encapsulation {

}

/**
 * JavaBean规范(公共实体类)
 * - 设计规范：合理隐藏，合理暴露。
 * - 成员变量使用private修饰。
 * - 提供成员变量对应的set、get公共方法。
 * - 必须提供一个无参构造器，有参数构造器是可写可不写的。
 * - 实体类只负责数据存取，不参与数据处理，实现数据和数据业务处理相分离。
 */
class Bean {
    private String name;

    public Bean() {
    }

    public Bean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
