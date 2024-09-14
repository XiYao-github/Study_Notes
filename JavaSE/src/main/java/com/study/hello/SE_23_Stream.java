package com.study.hello;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream流
 * - JDK8中得益于Lambda所带来的函数式编程，引入了一个全新的Stream流概念。
 * - 结合了Lambda表达式，简化集合、数组操作的API。
 * <p>
 * - Stream流式核心思想
 * - 先得到集合或者数组的Stream流(就是一根传送带)。
 * - 把元素放上去。
 * - 然后就用这个Stream流简化的API来方便的操作元素。
 */
public class SE_23_Stream {
    public static void main(String[] args) {
        handle();
        end();
        collect();
    }

    /**
     * 获取Stream流
     * - default Stream<E> stream() 获取集合的Stream流
     * - static <T> Stream<T> stream(T[] array) 获取数组的Stream流
     * - static<T> Stream<T> of(T... values) 获取指定可变参数(数组)的Stream流
     */
    public static void getStream() {
        // 获取List集合的Stream流
        List<String> list = new ArrayList<>();
        Stream<String> streamList = list.stream();

        // 获取Set集合的Stream流
        Set<String> set = new HashSet<>();
        Stream<String> streamSet = set.stream();

        // 获取Map集合的Stream流
        Map<String, String> map = new HashMap();
        Set<String> keys = map.keySet();
        // 获取Map键的Stream流
        Stream<String> streamKey = keys.stream();
        // 获取Map值的Stream流
        Collection<String> values = map.values();
        Stream<String> streamValue = values.stream();

        // 获取数组的Stream流
        String[] arr = {"hello", "hell", "hel", "he", "h"};
        Stream<String> arrStream = Arrays.stream(arr);
        Stream<String> ofStream = Stream.of("hello", "hell", "hel", "he", "h");
    }

    /**
     * Stream(中间操作方法)
     * - 中间方法也称为非终结方法，调用完成后返回新的Stream流可以继续使用，支持链式编程。
     * - 在Stream流中无法直接修改集合、数组中的数据。
     * - static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b) 合并两个流的数据
     * - Stream<T> filter(Predicate<? super T> predicate) 过滤满足条件的数据
     * - Stream<T> limit(long maxSize) 获取前几个元素
     * - Stream<T> skip(long n) 跳过前几个元素
     * - Stream<T> distinct() 去除重复的元素，需要依赖(hashCode和equals方法)
     * - <R> Stream<R> map(Function<? super T, ? extends R> mapper) 映射(转换)
     * - Stream<T> sorted(Comparator<? super T> comparator) 自定义排序
     */
    public static void handle() {
        String[] arr = {"hello", "hell", "hel", "he", "h"};
        Stream<String> arrStream = Arrays.stream(arr);
        Stream<String> ofStream = Stream.of("hello", "hell", "hel", "he", "h");

        // static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)合并两个流的数据
        Stream.concat(arrStream, ofStream).forEach(s -> System.out.print(s + " "));
        System.out.println();

        // Stream<T> filter(Predicate<? super T> predicate)过滤满足条件的数据
        Stream.of("hello", "hell", "hel", "he", "h").filter(s -> s.length() > 3).forEach(s -> System.out.print(s + " "));
        System.out.println();

        // Stream<T> limit(long maxSize)获取前几个元素
        Stream.of("h", "e", "l", "l", "o").limit(3).forEach(s -> System.out.print(s + " "));
        System.out.println();

        // Stream<T> skip(long n)跳过前几个元素
        Stream.of("h", "e", "l", "l", "o").skip(3).forEach(s -> System.out.print(s + " "));
        System.out.println();

        // Stream<T> distinct()去除重复的元素，需要依赖(hashCode和equals方法)
        Stream.of("h", "e", "l", "l", "o").distinct().forEach(s -> System.out.print(s + " "));
        System.out.println();

        // <R> Stream<R> map(Function<? super T, ? extends R> mapper)映射
        Stream.of("h", "e", "l", "l", "o").map(String::toUpperCase).forEach(s -> System.out.print(s + " "));
        System.out.println();

        // Stream<T> sorted()排序
        Stream.of("h", "e", "l", "l", "o").sorted().forEach(s -> System.out.print(s + " "));
        System.out.println();

        // Stream<T> sorted(Comparator<? super T> comparator)自定义排序
        Stream.of("h", "e", "l", "l", "o").sorted((o1, o2) -> o2.charAt(0) - o1.charAt(0)).forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    /**
     * Stream(终结操作方法)
     * - 终结方法后流不可以继续使用，非终结方法会返回新的流，支持链式编程。
     * - 终结操作方法，调用完成后流就无法继续使用了，原因是不会返回Stream了。
     * - void forEachOrdered(Consumer<? super T> action) 遍历操作
     * - long count() 返回元素数量
     * - Optional<T> max(Comparator<? super T> comparator) 返回最大元素
     * - Optional<T> min(Comparator<? super T> comparator) 返回最小元素
     */
    public static void end() {
        // long count()返回元素数量
        System.out.println(Stream.of("h", "e", "l", "l", "o").count());

        // Optional<T> max(Comparator<? super T> comparator)返回最大元素
        System.out.println(Stream.of("hello", "hell", "hel", "he", "h").max((o1, o2) -> o1.length() - o2.length()));

        // Optional<T> min(Comparator<? super T> comparator)返回最小元素
        System.out.println(Stream.of("hello", "hell", "hel", "he", "h").min((o1, o2) -> o1.length() - o2.length()));
    }

    /**
     * Stream(收集操作方法)
     * - 收集Stream流的含义：就是把Stream流操作后的结果数据转回到集合或者数组中去。
     * - Stream流：方便操作集合/数组的手段，操作的结果数据最终要恢复到集合/数组中去。
     * - <R, A> R collect(Collector<? super T, A, R> collector) 收集Stream流
     * <p>
     * Collectors提供收集方法
     * - static <T> Collector<T, ?, List<T>> toList() 将元素收集到List集合
     * - static <T> Collector<T, ?, Set<T>> toSet() 将元素收集到Set集合
     */
    public static void collect() {
        List<String> toList = Stream.of("hello", "hell", "hel", "he", "h").collect(Collectors.toList());
        Set<String> toSet = Stream.of("hello", "hell", "hel", "he", "h").collect(Collectors.toSet());
        System.out.println(toList);
        System.out.println(toSet);
    }

    /**
     * 函数式编程
     */
    public static void functionProgram() {

    }

}
