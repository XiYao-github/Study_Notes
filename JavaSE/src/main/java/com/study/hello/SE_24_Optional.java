package com.study.hello;

/**
 * Optional
 * - Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
 * - Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。
 * - Optional 提供很多有用的方法，这样我们就不用显式进行空值检测。
 * - Optional 类的引入很好的解决空指针异常。
 */
public class SE_24_Optional {
    public static void main(String[] args) {

    }

    /**
     * Optional方法
     * - static <T> Optional<T> empty() 返回空的Optional实例。
     * - boolean equals(Object obj) 判断其他对象是否等于Optional。
     * - static <T> Optional<T> of(T value) 返回一个指定非null值的Optional。of方法通过工厂方法创建Optional类。如果传入参数为null，否则抛出异常：NoSuchElementException。因此不经常用。
     * - static <T> Optional<T> ofNullable(T value) 如果为非空，返回Optional描述的指定值，否则返回空的Optional。
     * - T get() 如果在这个Optional中包含这个值，返回值，否则抛出异常：NoSuchElementException。因此也不经常用。
     * - boolean isPresent() 如果值存在则方法会返回true，否则返回 false。
     * - void ifPresent(Consumer<? super T> consumer) 如果值存在则使用该值调用consumer，否则不做任何事情。
     * - Optional<T> filter(Predicate<? super T> predicate) 如果值存在，并且这个值匹配给定的predicate，返回一个Optional用以描述这个值，否则返回一个空的Optional。
     * - Optional<U> map(Function<? super T, ? extends U> mapper) 如果有值，则对其执行调用映射函数得到返回值。如果返回值不为null，则创建包含映射返回值的Optional作为map方法返回值，否则返回空Optional。
     * - Optional<U> flatMap(Function<? super T, Optional<U>> mapper) 如果值存在，返回基于Optional包含的映射方法的值，否则返回一个空的Optional
     * - T orElse(T other) 如果存在该值，返回值，否则返回other。
     * - T orElseGet(Supplier<? extends T> other) orElseGet与orElse方法类似，区别在于得到的默认值。如果存在该值，返回值，否则触发other，并返回other调用的结果。
     * - <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) 如果存在该值，返回包含的值，否则抛出由Supplier继承的异常
     */
    public static void method() {

    }

}
