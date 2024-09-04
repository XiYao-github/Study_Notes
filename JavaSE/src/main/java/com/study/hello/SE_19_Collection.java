package com.study.hello;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 集合
 * - 集合和数组类似，都是容器。
 * - 集合大小可变，类型可以不固定，功能更加强大。
 * - 集合适合做元素个数不能确定，且需要做元素的增删操作的场景。
 * - 集合和泛型都不支持基本数据类型，只能支持引用数据类型。
 * - 集合中存储的是元素对象的地址。
 * <p>
 * 集合类体系结构
 * - Collection单列集合，每个元素(数据)只包含一个值。
 * - Map双列集合，每个元素包含两个值(键值对)。
 */
public class SE_19_Collection {
    public static void main(String[] args) {
        collectionErgodic();
    }

    /**
     * Collection(接口)
     * - Collection是单列集合的祖宗接口，它的功能是全部单列集合都可以继承使用的。
     * - Collection单列集合，每个元素(数据)只包含一个值(key)。
     * - List系列集合：添加的元素是有序、可重复、有索引。
     * - ArrayList、LinkedList：有序、可重复、有索引。
     * - Set系列集合：添加的元素是无序、不重复、无索引。
     * - HashSet：无序、不重复、无索引；
     * - LinkedHashSet：有序、不重复、无索引。
     * - TreeSet：按照大小默认升序排序、不重复、无索引。
     */
    public static void collection() {
        Collection<String> collection = new ArrayList<>();
        //boolean add(E e)添加指定元素
        collection.add("张三");
        collection.add("李四");
        collection.add("王二");
        collection.add("麻子");
        System.out.println(collection);//[张三, 李四, 王二, 麻子]

        //boolean addAll(Collection<? extends E> c)添加指定集合所有元素
        Collection<String> collectionNew = new ArrayList<>();
        collectionNew.addAll(collection);
        System.out.println(collectionNew);//[张三, 李四, 王二, 麻子]

        //void clear()删除所有元素
        //collectionNew.clear();
        //System.out.println(collectionNew);//[]

        //boolean contains(Object o)判断是否存在指定的元素
        System.out.println(collection);//[张三, 李四, 王二, 麻子]
        System.out.println(collection.contains("张三"));//true

        //boolean containsAll(Collection<?> c)判断是否存在指定集合中的所有元素
        System.out.println(collection);//[张三, 李四, 王二, 麻子]
        System.out.println(collectionNew);//[张三, 李四, 王二, 麻子]
        System.out.println(collection.containsAll(collectionNew));//true

        //boolean remove(Object o)删除指定元素
        System.out.println(collection.remove("张三") + " " + collection);//true [李四, 王二, 麻子]

        //boolean removeAll(Collection<?> c)删除指定集合相同的所有元素
        //System.out.println(collectionNew);//[张三, 李四, 王二, 麻子]
        //System.out.println(collection.removeAll(collectionNew) + " " + collection);//true []

        //default boolean removeIf(Predicate<? super E> filter)删除满足条件的所有元素
        System.out.println(collection.removeIf(new Predicate() {
            @Override
            public boolean test(Object o) {
                return "李四".equals(o);
            }
        }) + " " + collection);//true [王二, 麻子]

        //boolean retainAll(Collection<?> c)仅保留指定集合中的元素
        System.out.println(collection);//[王二, 麻子]
        System.out.println(collectionNew);//[张三, 李四, 王二, 麻子]
        System.out.println(collectionNew.retainAll(collection) + " " + collectionNew);//true [王二, 麻子]

        //int size()返回此集合中的元素个数
        System.out.println(collection.size());//2
        System.out.println(collectionNew.size());//2

        //Object[] toArray()返回包含此集合中所有元素的数组
        System.out.println(Arrays.toString(collection.toArray()));//[王二, 麻子]
    }

    /**
     * Collection遍历
     */
    public static void collectionErgodic() {
        Collection<String> collection = new ArrayList<>();
        collection.add("张三");
        collection.add("李四");
        collection.add("王二");
        collection.add("麻子");
        // 使用迭代器方法遍历集合，迭代器在Java中的代表是Iterator，迭代器是集合的专用的遍历方式。
        // 获取迭代器对象，返回此集合中元素的迭代器，该迭代器对象默认指向当前集合的0索引
        Iterator<String> iterator = collection.iterator();
        // 调用iterator.next()方法之前必须调用iterator.hasNext()判断是否还有元素
        // 如果不判断,迭代器取元素越界会出现 NoSuchElementException异常
        while (iterator.hasNext()) {
            String next = iterator.next();
            if ("张三".equals(next)) {
                // 调用iterator.remove()从底层集合中移除此迭代器返回的最后一个元素
                iterator.remove();
            }
        }
        System.out.println(collection); //[李四, 王二, 麻子]

        // 增强for循环就是简化的迭代器iterator，源码调用迭代器方法遍历
        for (String str : collection) {
            // 修改变量(str)的值不会影响到集合中的元素，因为算是一个复制品，所以循环通常只能遍历数组
            System.out.println(str);
            if ("李四".equals(str)) {
                str = "法外狂徒";
            }
        }
        System.out.println(collection); //[李四, 王二, 麻子]

        // 得益于JDK8开始的新技术Lambda表达式，提供了一种更简单、更直接的遍历集合的方式。
        //调用方法实现接口遍历
        collection.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        //lambda表达式
        collection.forEach(s -> System.out.println(s));
        //简化形式
        collection.forEach(System.out::println);
    }

    /**
     * List(接口)
     * - List集合因为支持索引，所以多了很多索引操作的独特API，其他Collection的功能List也都继承了。
     * - ArrayList、Vector、LinkedList：有序，可重复，有索引。
     * - 有序：存储和取出的元素顺序一致。
     * - 有索引：可以通过索引操作元素。
     * - 可重复：存储的元素可以重复。
     */
    public static void list() {
        Collection<String> collection = new ArrayList<>();
        collection.add("张三");
        collection.add("李四");
        collection.add("王二");
        collection.add("麻子");
        List<String> list = new ArrayList<>();

        //void add(int index, E element)添加指定元素到指定位置
        list.add(0, "张三");
        System.out.println(list);//[张三]

        //boolean addAll(int index, Collection<? extends E> c)添加指定集合所有元素到指定位置
        System.out.println(list.addAll(1, collection));//true
        System.out.println(list);//[张三, 张三, 李四, 王二, 麻子]

        //E get(int index)返回指定位置的元素
        System.out.println(list.get(2));//李四
        System.out.println(list);//[张三, 张三, 李四, 王二, 麻子]

        //int indexOf(Object o)返回指定元素第一次出现的位置
        System.out.println(list.indexOf("张三"));//0
        System.out.println(list);//[张三, 张三, 李四, 王二, 麻子]

        //int lastIndexOf(Object o)返回指定元素最后一次出现的位置
        System.out.println(list.lastIndexOf("张三"));//1
        System.out.println(list);//[张三, 张三, 李四, 王二, 麻子]

        //E remove(int index)删除指定位置的元素
        System.out.println("删除：" + list.remove(1));//删除：张三
        System.out.println(list);//[张三, 李四, 王二, 麻子]

        // E set(int index, E element)将指定位置的元素替换为指定元素
        System.out.println("替换：" + list.set(0, "法外狂徒"));//替换：张三
        System.out.println(list);//[法外狂徒, 李四, 王二, 麻子]

        //List<E> subList(int fromIndex, int toIndex)返回起始位置到结束位置的所有元素
        List<String> listNew = list.subList(1, 3);//[李四, 王二]
        System.out.println(listNew);
    }

    /**
     * List遍历
     * - Collection遍历方式List都可以使用，并且还可以使用for循环遍历(因为List集合存在索引)
     * <p>
     * 遍历集合并且删除元素的异常问题
     * - 迭代器遍历集合，直接使用集合的方法删除元素出现异常(指针移动了，但元素减少了)，建议使用迭代器的方法删除元素。
     * - forEach遍历集合，直接用集合的方法删除元素出现异常，同迭代器。
     * - lambda表达式遍历集合，直接用集合的方法删除元素出现异常，同forEach。
     * - for循环遍历集合，直接用集合的方法删除元素没有异常，但是会误删数据(从前往后遍历)，建议使用索引删除元素或者(从后往前遍历)。
     */
    public static void listErgodic() {
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王二");
        list.add("麻子");

        //for循环(因为List集合存在索引)
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    /**
     * ArrayList(实现类)
     * - ArrayList底层是基于可变数组实现的，根据索引定位元素快，增删需要做元素的移位操作。
     * - ArrayList是线程不安全的(执行效率高)，在多线程情况下，不建议使用ArrayList。
     * - ArrayList可以添加null值，并且可以添加多个null，存在值相同时，优先使用索引小的值。
     * - ArrayList维护一个Object[] elementData对象数组，用于存放添加到集合的值。
     * - 默认创建ArrayList集合，Object[] elementData对象数组初始容量为0，也可以指定容量创建ArrayList集合。
     * - 默认创建ArrayList集合第一次添加元素时，Object[] elementData对象数组扩容为10，用完扩容，后续扩容为当前容量的1.5倍。
     * - 扩容实际是给Object[] elementData对象数组重新赋值，elementData = Arrays.copyOf(elementData, newCapacity);
     */
    public static void arrayList() {
        Collection<String> list = new ArrayList<>();
        //创建默认ArrayList集合
        ArrayList<String> arrayList = new ArrayList();
        //指定长度创建ArrayList集合
        ArrayList<String> arrayList_1 = new ArrayList(10);
        //使用集合创建ArrayList集合
        ArrayList<String> arrayList_2 = new ArrayList(list);
    }

    /**
     * Vector(实现类)
     * - Vector基本等同于ArrayList底层也是基于可变数组实现的，根据索引定位元素快，增删需要做元素的移位操作。
     * - Vector是线程安全的(执行效率不高)，在多线程情况下，建议使用Vector。
     * - Vector可以添加null值，并且可以添加多个null，存在值相同时，优先使用索引小的值。
     * - Vector维护一个Object[] elementData对象数组，用于存放添加到集合的值。
     * - 默认创建Vector集合，Object[] elementData对象数组初始容量默认为10，也可以指定容量创建Vector集合，用完扩容，后续扩容为当前容量的2倍。
     * - 扩容实际是给Object[] elementData对象数组重新赋值，elementData = Arrays.copyOf(elementData, newCapacity);
     */
    public static void vector() {
        Collection<String> list = new Vector<>();
        //创建默认Vector集合
        Vector<String> vector = new Vector();
        //指定长度创建Vector集合
        Vector<String> vector_1 = new Vector(10);
        //使用集合创建Vector集合
        Vector<String> vector_2 = new Vector(list);
    }

    /**
     * LinkedList(实现类)
     * - LinkedList底层数据结构是双链表，查询慢，首尾操作的速度是极快的，所以多了很多首尾操作的特有API。
     * - LinkedList是线程不安全的，在多线程情况下，不建议使用LinekdList。
     * - LinkedList可以添加null值，并且可以添加多个null，存在值相同时，优先使用索引小的值。
     * - LinkedList有两个属性：first(指向链表首节点)、last(指向链表尾节点)。
     * - LinkedList每个节点(Node对象：静态内部类)有三个属性：prev(指向上一个节点)、item(当前节点数据)、next(指向下一个节点)。
     */
    public static void linkedList() {
        List<String> list = new LinkedList();
        list.add("张三");
        list.add("李四");
        list.add("王二");
        list.add("麻子");

        //创建默认LinkedList集合
        LinkedList<String> linkedList = new LinkedList();
        //按照集合顺序创建LinkedList集合
        LinkedList<String> linkedListNew = new LinkedList(list);
        System.out.println(linkedListNew);//[张三, 李四, 王二, 麻子]

        //LinkedList方法
        //void addFirst(E e)在此列表的开头插入指定的元素
        linkedListNew.addFirst("法外狂徒");
        System.out.println(linkedListNew);//[法外狂徒, 张三, 李四, 王二, 麻子]

        //void addLast(E e) 将指定的元素追加到此列表的末尾
        linkedListNew.addLast("赵六");
        System.out.println(linkedListNew);//[法外狂徒, 张三, 李四, 王二, 麻子, 赵六]

        //E getFirst()返回此列表中的第一个元素
        System.out.println(linkedListNew.getFirst());//法外狂徒
        System.out.println(linkedListNew);//[法外狂徒, 张三, 李四, 王二, 麻子, 赵六]

        //E getLast()返回此列表中的最后一个元素
        System.out.println(linkedListNew.getLast());//赵六
        System.out.println(linkedListNew);//[法外狂徒, 张三, 李四, 王二, 麻子]

        //E removeFirst()从此列表中删除并返回第一个元素
        System.out.println(linkedListNew.removeFirst());//法外狂徒
        System.out.println(linkedListNew);//[张三, 李四, 王二, 麻子, 赵六]

        //E removeLast()从此列表中删除并返回最后一个元素
        System.out.println(linkedListNew.removeLast());//赵六
        System.out.println(linkedListNew);//[张三, 李四, 王二, 麻子]

        //E pop()弹出此列表所代表的堆栈中的元素
        System.out.println(linkedListNew.pop());//张三
        System.out.println(linkedListNew);//[李四, 王二, 麻子]

        //void push(E e)将元素推送到此列表所表示的堆栈上
        linkedListNew.push("法外狂徒");
        System.out.println(linkedListNew);//[法外狂徒, 李四, 王二, 麻子]
    }

    /**
     * Set(接口)
     * - Set系列集合：添加的元素是无序、不重复、无索引。
     * - HashSet：无序、不重复、无索引。
     * - LinkedHashSet：有序、不重复、无索引。
     * - TreeSet：排序、不重复、无索引。
     * - 无序：存取顺序不一致。
     * - 不重复：可以去除重复。
     * - 无索引：没有带索引的方法，所以不能使用普通for循环遍历，也不能通过索引来获取元素。
     */
    public static void set() {

    }

    /**
     * HashSet(实现类)
     * - HashSet：无序、不重复、无索引。
     * - HashSet底层采取哈希表存储数据，本质上是HashMap，哈希表是一种对于增删改查数据性能都较好的结构。
     * - HashSet是线程不安全的，在多线程情况下，不建议使用HashSet。
     * - HashSet可以添加null值，但只能添加一个null值，哈希表的相同元素哈希值一样。
     * - HashSet存储元素是无序的，但取出元素顺序是有序的，哈希表的索引是根据元素的哈希值确定的。
     */
    public static void hashSet() {
        Collection<String> set = new HashSet<>();
        //创建默认HashSet集合
        HashSet<String> hashSet = new HashSet();
        //指定初始容量创建HashSet集合
        HashSet<String> hashSet_1 = new HashSet(16);
        //指定初始容量和加载因子创建HashSet集合
        HashSet<String> hashSet_2 = new HashSet(16, 0.75f);
        //使用集合创建HashSet集合
        HashSet<String> hashSet_3 = new HashSet(set);
    }

    /**
     * LinkedHashSet(实现类)
     * - LinkedHashSet：有序、不重复、无索引。
     * - LinkedHashSet底层采取哈希表+双向链表存储数据，本质上是LinkedHashMap。
     * - LinkedHashSet是线程不安全的，在多线程情况下，不建议使用LinkedHashSet。
     * - LinkedHashSet可以添加null值，但只能添加一个null值，哈希表的相同元素哈希值一样。
     * - 有序：这里的有序指的是保证存储和取出的元素顺序一致。
     * - 原理：底层数据结构是依然哈希表，只是每个元素又额外的多了一个双向链表的机制记录存储的顺序。
     */
    public static void linkedHashSet() {
        Collection<String> set = new LinkedHashSet<>();
        //创建默认LinkedHashSet集合
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet();
        //指定初始容量创建LinkedHashSet集合
        LinkedHashSet<String> linkedHashSet_1 = new LinkedHashSet(16);
        //指定初始容量和加载因子创建LinkedHashSet集合
        LinkedHashSet<String> linkedHashSet_2 = new LinkedHashSet(16, 0.75f);
        //使用集合创建LinkedHashSet集合
        LinkedHashSet<String> linkedHashSet_3 = new LinkedHashSet(set);
    }

    /**
     * TreeSet(实现类)
     * - TreeSet：排序、不重复、无索引。
     * - TreeSet底层基于红黑树的数据结构实现排序的，增删改查性能都较好，TreeMap跟TreeSet底层原理是一样的。
     * - TreeSet是线程不安全的，在多线程情况下，不建议使用TreeSet。
     * - TreeSet不可以添加null值，因为null值不能排序。
     * - 注意：TreeSet集合是一定要排序的，默认按照元素的大小升序(从小到大)排序，也可以自定义元素的排序规则。
     * <p>
     * TreeSet默认排序规则
     * - 对于数值类型：Integer，Double，官方默认按照大小进行升序排序。
     * - 对于字符串类型：默认按照首字符的编号升序排序。
     * - 对于自定义类型如Student对象，TreeSet无法直接排序，需要实现Comparable接口。
     * - 方式一：自定义类型(如学生类)实现Comparable接口重写里面的compareTo方法来定制比较规则。
     * - 方式二：TreeSet集合有参数构造器，可以设置Comparator接口对应的比较器对象，来定制比较规则。
     * <p>
     * TreeSet排序返回值规则
     * - 如果认为第一个元素大于第二个元素返回正整数即可。
     * - 如果认为第一个元素小于第二个元素返回负整数即可。
     * - 如果认为第一个元素等于第二个元素返回0即可，此时TreeSet集合只会保留一个元素，认为两者重复。
     * - 注意：如果TreeSet集合存储的对象有实现比较规则，集合也自带比较器，默认使用集合自带的比较器排序。
     */
    public static void treeSet() {
        Collection<String> set = new TreeSet<>();
        //创建默认TreeSet集合
        TreeSet<String> treeSet = new TreeSet();
        //使用集合创建TreeSet集合,默认排序规则
        TreeSet<String> treeSet_1 = new TreeSet(set);
        //创建TreeSet集合并且指定排序规则
        TreeSet<String> treeSet_2 = new TreeSet((o1, o2) -> 0);
        //使用指定集合创建新的TreeSet集合,使用指定集合的排序规则
        TreeSet<String> treeSet_3 = new TreeSet(treeSet);
    }

    /**
     * Collections：java.utils.Collections：是集合工具类，Collections并不属于集合，是用来操作集合的工具类
     */
    public static void collections() {
        List<String> list = new ArrayList();
        //static <T> boolean addAll(Collection<? super T> c, T... elements)将所有指定元素添加到指定集合
        Collections.addAll(list, "张三", "李四", "王二", "麻子", "赵六", "钱七");
        System.out.println("Collections.addAll：" + list);

        //static <T> void copy(List<? super T> dest, List<? extends T> src)复制List集合所有元素
        Vector<String> vector = new Vector();
        vector.add("法外狂徒");
        //是替换不是追加,所以需要 src.Size() > dest.size()
        Collections.copy(list, vector);
        System.out.println("list：" + list);
        System.out.println("vector：" + vector);

        //static boolean disjoint(Collection<T> c1, Collection<T> c2)如果两个指定集合没有共同元素，则返回true
        System.out.println(Collections.disjoint(list, vector));
        System.out.println("list：" + list);
        System.out.println("vector：" + vector);

        //static <T> boolean replaceAll(List<T> list, T oldVal, T newVal)替换List集合所有指定值
        System.out.println("list替换前：" + list);
        System.out.println(Collections.replaceAll(list, "法外狂徒", "张三"));
        System.out.println("list替换后：" + list);

        //static void reverse(List<?> list)反转List集合元素的顺序
        Collections.reverse(list);
        System.out.println("list反转后：" + list);

        //static void shuffle(List<?> list)打乱List集合元素的顺序
        Collections.shuffle(list);
        System.out.println("list打乱后：" + list);

        //static <T extends Comparable<? super T>> void sort(List<T> list)List集合默认规则排序
        Collections.sort(list);
        System.out.println("list默认排序后：" + list);

        //static <T> void sort(List<T> list, Comparator<? super T> c)List集合自定义规则排序
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.hashCode() - o1.hashCode();
            }
        });
        System.out.println("list自定义排序后：" + list);

        //static void swap(List<?> list, int i, int j)交换List集合指定位置的元素
        Collections.swap(list, 0, 5);
        System.out.println("list交换后：" + list);
    }
}


