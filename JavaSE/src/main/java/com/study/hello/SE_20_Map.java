package com.study.hello;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * 哈希表
 * - JDK8之前的，底层使用数组+链表组成
 * - JDK8开始后，底层采用数组+链表+红黑树组成。
 * <p>
 * 哈希值
 * - JDK根据对象的地址，按照某种规则算出来的int类型的数值。
 * - 同一个对象多次调用hashCode()方法返回的哈希值是相同的。
 * - 默认情况下，不同对象的哈希值是不同的。
 * <p>
 * HashMap创建过程
 * - 创建默认对象时，初始化加载因子为0.75，数组Node<K,V>[] table为null。
 * - 第一次添加元素时，Node<K,V>[] table数组第一次扩容为默认的16。
 * - 根据Node<K,V>键的哈希值和数组长度求余计算出存储索引(哈希算法)。
 * - Hashtable调用key的hashcode获取哈希值，HashMap对key的hashcode进行了二次hash。
 * - 判断索引位置是否为null，如果为null表示没有元素，将Node<K,V>直接存储在索引位置。
 * - 如果不为null表示存在元素，Node<K,V>的键调用equals()方法和索引位置链表上所有Node<K,V>的键比较(哈希值和内容)。
 * - 如果存在相同Node<K,V>的键会替换Node<K,V>的值，如果不存在相同Node<K,V>的键将Node<K,V>挂载到索引位置的链表上。
 * - JDK7中新元素占据老元素位置，指向老元素(头插法)，JDK8中新元素挂载到老元素下面(尾插法)。
 * - 链表挂载元素过多时，查询性能会降低，JDK8中链表长度超过8且数组长度大于等于64就会自动转换为红黑树。
 * - 数组存储元素为16*0.75=12时，数组会自动扩容，每次扩容为当前容量的2倍。
 * - 注意：如果希望集合认为2个内容一样的对象是重复的，必须重写对象的hashCode()和equals()方法。
 */
public class SE_20_Map {
    public static void main(String[] args) {

    }

    /**
     * Map(接口)
     * - Map集合是一种双列集合，每个元素包含两个数据，也被称为“键值对集合”。
     * - Map集合的每个元素的格式：key=value(键值对元素)。
     * - Map集合的特点都是由键决定的，键是无序，不重复的，无索引的，值不做要求(可以重复)。
     * - Map集合添加重复的键值对，会覆盖原来的键值对，等同于修改(K不会替换，V会替换)。
     * - Map集合
     * - HashMap：元素按照键是无序，不重复，无索引，值不做要求。(与Map体系一致)
     * - - LinkedHashMap：元素按照键是有序，不重复，无索引，值不做要求。
     * - HashTable
     * - - Properties：代表的是一个属性文件，可以把自己对象中的键值对信息存入到一个属性文件中去。
     * - - 属性文件：后缀是.properties结尾的文件，里面的内容都是key=value，后续做系统配置信息的。
     * - TreeMap：元素按照键是排序，不重复，无索引的，值不做要求。
     */
    public static void map() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "张三");
        map.put(2, "李四");
        map.put(3, "王二");
        map.put(4, "麻子");
        Map<Integer, String> mapNew = new HashMap<>();
        mapNew.put(0, "法外狂徒");
        mapNew.put(1, "张三");
        mapNew.put(2, "李四");
        mapNew.put(3, "王二");
        mapNew.put(4, "麻子");
        mapNew.put(5, "赵六");

        //Map方法
        //void clear()删除集合所有键值对
        //map.clear();
        //System.out.println(map);//{}

        //boolean containsKey(Object key)判断是否包含指定的键
        System.out.println(map.containsKey(1) + " " + map);//true {1=张三, 2=李四, 3=王二, 4=麻子}

        //boolean containsValue(Object value)判断是否包含指定的值(一个或多个)
        System.out.println(map.containsValue("张三") + " " + map);//true {1=张三, 2=李四, 3=王二, 4=麻子}

        //V get(Object key)返回指定键映射的值,否则返回 null
        System.out.println(map.get(1) + " " + map);//张三 {1=张三, 2=李四, 3=王二, 4=麻子}

        //boolean isEmpty()判断集合是否存在键值对
        System.out.println(map.isEmpty() + " " + map);//false {1=张三, 2=李四, 3=王二, 4=麻子}

        //V put(K key, V value)添加指定键值对元素
        System.out.println(map.put(5, "赵六") + " " + map);//null {1=张三, 2=李四, 3=王二, 4=麻子, 5=赵六}
        System.out.println(map.put(5, "赵六") + " " + map);//赵六 {1=张三, 2=李四, 3=王二, 4=麻子, 5=赵六}

        //void putAll(Map<? extends K, ? extends V> m)添加指定集合所有键值对元素
        System.out.println("map：" + map);//map：{1=张三, 2=李四, 3=王二, 4=麻子, 5=赵六}
        System.out.println("mapNew：" + mapNew);//mapNew：{0=法外狂徒, 1=张三, 2=李四, 3=王二, 4=麻子, 5=赵六}
        map.putAll(mapNew);
        System.out.println("map：" + map);//map：{0=法外狂徒, 1=张三, 2=李四, 3=王二, 4=麻子, 5=赵六}

        //V remove(Object key)删除指定键
        System.out.println(map.remove(5) + " " + map);//赵六 {0=法外狂徒, 1=张三, 2=李四, 3=王二, 4=麻子}

        //int size()返回集合所有键值的数量
        System.out.println(map.size() + " " + map);//5 {0=法外狂徒, 1=张三, 2=李四, 3=王二, 4=麻子}

        //Set<K> keySet()返回一个Set集合,包含所有键
        System.out.println("map.keySet：" + map.keySet());//map.keySet：[0, 1, 2, 3, 4]
        System.out.println("map：" + map);//map：{0=法外狂徒, 1=张三, 2=李四, 3=王二, 4=麻子}

        //Collection<V> values()返回一个Collection集合,包含所有值
        System.out.println("map.values：" + map.values());//map.values：[法外狂徒, 张三, 李四, 王二, 麻子]
        System.out.println("map：" + map);//map：{0=法外狂徒, 1=张三, 2=李四, 3=王二, 4=麻子}

        //Set<Map.Entry<K, V>> entrySet()返回一个Set集合，包含所有键值对
        System.out.println("map.entrySet：" + map.entrySet());//map.entrySet：[0=法外狂徒, 1=张三, 2=李四, 3=王二, 4=麻子]
        System.out.println("map：" + map);//map：{0=法外狂徒, 1=张三, 2=李四, 3=王二, 4=麻子}
    }

    /**
     * Map遍历
     * - 键找值的方式遍历：先获取Map集合全部的键，再根据遍历键找值。
     * - 键值对的方式遍历，把“键值对“看成一个整体，难度较大。
     * - JDK8开始之后的新技术：Lambda表达式。
     */
    public static void mapErgodic() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "张三");
        map.put(2, "李四");
        map.put(3, "王二");
        map.put(4, "麻子");
        // 键找值遍历
        // 先获取Map集合的全部键的Set集合
        // 遍历键的Set集合,然后通过键提取对应值
        // Set<K> keySet(); 返回一个Set集合，包含所有键
        // Collection<V> values(); 返回一个Collection集合，包含所有值
        // V get(Object key); 返回指定键映射的值，否则返回 null
        Set<Integer> keySet = map.keySet();
        // 迭代器
        Iterator<Integer> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            System.out.print(key + "=" + map.get(key) + " ");
        }
        System.out.println();
        // 增强 for
        for (Object key : keySet) {
            System.out.print(key + "=" + map.get(key) + " ");
        }
        System.out.println();

        // 键值对遍历
        // 先把Map集合转换成Set集合，Set集合中每个元素都是键值对实体类型了
        // 遍历Set集合，然后提取键以及提取值
        // Set<Map.Entry<K, V>> entrySet(); 返回一个Set集合，包含所有键值对
        // K getKey(); 获得键
        // V getValue(); 获取值
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        // 迭代器
        Iterator<Map.Entry<Integer, String>> entryIterator = entrySet.iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Integer, String> entry = entryIterator.next();
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
        System.out.println();
        // 增强 for
        for (Map.Entry<Integer, String> entry : entrySet) {
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
        System.out.println();

        // Lambda表达式
        // 得益于JDK8开始的新技术Lambda表达式，提供了一种更简单、更直接的遍历集合的方式。
        // default void forEach(BiConsumer<? super K, ? super V> action); 结合lambda遍历Map集合
        // 调用方法实现接口遍历
        map.forEach(new BiConsumer<Integer, String>() {
            @Override
            public void accept(Integer integer, String s) {
                System.out.print(integer + "=" + s + " ");
            }
        });
        System.out.println();
        // Lambda表达式
        map.forEach((integer, s) -> System.out.print(integer + "=" + s + " "));
        System.out.println();
    }

    /**
     * HashMap(实现类)
     * - HashMap由键决定的：无序、不重复、无索引。
     * - HashMap底层采取哈希表存储数据，键和值都可以为null。
     * - HashMap是线程不安全的，在多线程情况下，不建议使用HashMap。
     * - HashMap存储的键是无序的，但取出键的顺序是有序的，HashMap对key的hashcode进行了二次hash确定索引值。
     * - HashMap底层维护Node<K,V>[] table数组，Node是HashMap的静态内部类，实现了Map.Entry<K,V>(Map集合的一个内部接口)。
     * - HashMap中的Key和Value都会封装到Node<K,V>实现单链表结构，将每条单链表存储到Node<K,V>[] table数组中。
     * - JDK8中Node<K,V>链表长度超过8，Node<K,V>[] table数组长度大于等于64，就会将Node<K,V>单链表转换为TreeNode<K,V>红黑树。
     * - HashMap底层维护Set<Map.Entry<K,V>> entrySet集合，内容指向Node<K,V>对象的Key和Value，方便管理和调用。
     * - HashMap依赖hashCode方法和equals方法保证键的唯一，如果键要存储的是自定义对象，需要重写hashCode和equals方法。
     * - 默认创建HashMap集合，Node<K,V>[] table数组为null，初始化加载因子为0.75，扩容规则：(当前数组长度 << 1)。
     * - 默认创建HashMap集合第一次添加元素时，Node<K,V>[] table数组第一次默认扩容为16 。
     * - 触发扩容：数组存储元素为(数组长度*加载因子)时，Node<K,V>链表长度超过8且Node<K,V>[] table数组长度小于等于64时。
     * <p>
     * HashMap与HashSet
     * - HashMap跟HashSet底层原理是一模一样的，都是哈希表结构。
     * - Map集合的每个元素都包含键值，Set集合每个元素只要键数据，不要值数据(默认值为空的Object)。
     */
    public static void hashMap() {
        //创建默认HashMap集合
        HashMap<Integer, String> hashMap = new HashMap<>();
        //指定初始容量创建HashMap集合
        HashMap<Integer, String> hashMap_1 = new HashMap<>(16);
        //指定初始容量和加载因子创建HashMap集合
        HashMap<Integer, String> hashMap_2 = new HashMap<>(16, 0.75f);
        //使用集合创建HashMap集合
        HashMap<Integer, String> hashMap_3 = new HashMap<>(hashMap);
    }

    /**
     * LinkedHashMap(实现类)
     * - LinkedHashMap由键决定：有序、不重复、无索引。
     * - LinkedHashMap底层采取哈希表+双向链表存储数据，键和值都可以为null。
     * - LinkedHashMap是线程不安全的，在多线程情况下，不建议使用LinkedHashMap。
     * - LinkedHashMap内部存在静态内部类Entry用于封装Node对象并且实现双链表结构，Entry继承了HashMap.Node(HashMap集合的一个静态内部类)。
     * - LinkedHashMap会将Key和Value封装到Node实现单链表，然后将Node对象封装到Entry实现双链表并记录存储顺序，最后将Entry对象存储到父类HashMap的Node<K,V>[] table数组中。
     * - Entry对象有两个属性：before(指向上一个节点)、after(指向下一个节点)用于记录存储顺序。
     * - LinkedHashMap有两个属性：LinkedHashMap.Entry<K,V> head(指向双链表首节点)、LinkedHashMap.Entry<K,V> tail(指向双链表尾节点)操作链表。
     * - 有序：这里的有序指的是保证存储和取出的元素顺序一致。
     * - 原理：底层数据结构是依然哈希表，只是每个键值对元素又额外的多了一个双链表的机制记录存储的顺序。
     */
    public static void linkedHashMap() {
        //创建默认LinkedHashMap集合
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        //指定初始容量创建LinkedHashMap集合
        LinkedHashMap<Integer, String> linkedHashMap_1 = new LinkedHashMap<>(16);
        //指定初始容量和加载因子创建LinkedHashMap集合
        LinkedHashMap<Integer, String> linkedHashMap_2 = new LinkedHashMap<>(16, 0.75f);
        //指定初始容量和加载因子和排序模式创建LinkedHashMap集合
        LinkedHashMap<Integer, String> linkedHashMap_3 = new LinkedHashMap<>(16, 0.75f, false);
        //使用集合创建LinkedHashMap集合
        LinkedHashMap<Integer, String> linkedHashMap_4 = new LinkedHashMap<>(linkedHashMap);
    }

    /**
     * HashTable(实现类)
     * - HashTable由键决定的：无序、不重复、无索引。
     * - HashTable底层采取哈希表存储数据，键和值都不能为null。
     * - HashTable是线程安全的，在多线程情况下，建议使用HashTable。
     * - HashTable存储的键是无序的，但取出键的顺序是有序的，Hashtable调用key的hashcode获取哈希值确定索引值。
     * - HashTable底层维护Entry<?,?>[] table数组，Entry是HashTable的静态内部类，实现了Map.Entry<K,V>(Map集合的一个内部接口)。
     * - HashTable中的Key和Value都会封装到Entry<?,?>实现单链表结构，将每条单链表存储到Entry<?,?>[] table数组中。
     * - HashTable底层维护Set<Map.Entry<K,V>> entrySet集合，内容指向Entry<?,?>对象的Key和Value，方便管理和调用。
     * - HashTable依赖hashCode方法和equals方法保证键的唯一，如果键要存储的是自定义对象，需要重写hashCode和equals方法。
     * - 默认创建HashTable集合，Entry<?,?>[] table数组长度默认为11，初始化加载因子为0.75。
     * - 触发扩容：数组存储元素为(数组长度*加载因子)时，扩容规则：(当前数组长度 << 1) + 1。
     */
    public static void hashTable() {
        //创建默认Hashtable集合
        Hashtable<Integer, String> hashtable = new Hashtable<>();
        //指定初始容量创建Hashtable集合
        Hashtable<Integer, String> hashtable_1 = new Hashtable<>(11);
        //指定初始容量和加载因子创建Hashtable集合
        Hashtable<Integer, String> hashtable_2 = new Hashtable<>(11, 0.75f);
        //使用集合创建Hashtable集合
        Hashtable<Integer, String> hashtable_3 = new Hashtable<>(hashtable);
    }

    /**
     * Properties(实现类)
     * - Properties类表示一组持久的属性，一般保存到流中或从流中加载(xxx.properties文件)。
     * - Properties类是线程安全的，多个线程可以共享单个Properties对象无需外部同步。
     * - 属性列表中的每个键及其对应的值都是一个字符串，Properties的键和值都不能为null。
     * - 属性列表可以包含另一个属性列表作为其默认值，如果在原始属性列表中找不到属性键，则搜索第二个属性列表。
     */
    public static void properties() {
        //创建默认Hashtable集合
        Properties properties = new Properties();
        //使用指定集合创建Hashtable集合
        Properties propertiesNew = new Properties(properties);
    }

    /**
     * TreeMap(实现类)
     * - TreeMap由键决定特性：排序、不重复、无索引。
     * - TreeMap底层基于红黑树的数据结构实现排序的，增删改查性能都较好，TreeMap跟TreeSet底层原理是一样的。
     * - TreeMap是线程不安全的，在多线程情况下，不建议使用TreeMap。
     * - TreeSet的键不可以添加null，因为null不能排序，值不做要求。
     * - 注意：TreeMap集合是一定要排序的，默认按照键的大小升序(从小到大)排序，只能对键排序，也可以自定义键的排序规则。
     * <p>
     * TreeMap默认排序规则
     * - 对于数值类型：Integer，Double，官方默认按照大小进行升序排序。
     * - 对于字符串类型：默认按照首字符的编号升序排序。
     * - 对于自定义类型如Student对象，TreeMap无法直接排序，需要实现Comparable接口。
     * - 方式一：类实现Comparable接口，重写比较规则。
     * - 方式二：集合自定义Comparator比较器对象，重写比较规则。
     * <p>
     * TreeMap排序返回值规则
     * - 如果认为第一个键大于第二个键返回正整数即可。
     * - 如果认为第一个键小于第二个键返回负整数即可。
     * - 如果认为第一个键等于第二个键返回0即可，此时TreeMap集合认为两者重复，会替换键的值。
     * - 注意：如果TreeMap集合存储的对象有实现比较规则，集合也自带比较器，默认使用集合自带的比较器排序。
     */
    public static void treeMap() {
        //创建默认TreeMap集合
        TreeMap<Integer, String> treeMap = new TreeMap();
        //使用集合创建TreeMap集合,默认排序规则
        TreeMap<Integer, String> treeMap_1 = new TreeMap(treeMap);
        //创建TreeMap集合并且指定排序规则
        TreeMap<Integer, String> treeMap_2 = new TreeMap((o1, o2) -> 0);
        //使用指定集合创建新的TreeMap集合,使用指定集合的排序规则
        TreeMap<Integer, String> TreeMap_3 = new TreeMap(treeMap);
    }
}
