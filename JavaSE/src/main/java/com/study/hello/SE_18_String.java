package com.study.hello;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * String
 * - 字符串的字符使用Unicode字符编码，一个字符(不区分字母还是汉字)占两个字节。
 * - String类实现了接口Serializable(String 可以串行化：可以在网络传输)，接口 Comparable(String 对象可以比较大小)。
 * - String类有属性 private final char value[]; 用于存放字符串的内容，value[]的地址值不能更改，value[]里的值可以修改。
 * - String类因为使用频繁，所以使用字符串常量池来存储字符串常量对象，字符串常量对象是用双引号括起来的字符序列。
 * - String类保存的是字符串常量对象，字符串内容的修改实际上是切换不同字符串常量对象。
 * - String对象的内容不可改变，被称为不可变字符串对象。
 * - 只要是以“...”方式写出的字符串对象，会存储到字符串常量池，且相同内容的字符串只存储一份；
 * - 但通过new方式创建字符串对象，每new一次都会产生一个新的对象放在堆内存中。
 */
public class SE_18_String {
    public static void main(String[] args) {
        string();
    }

    /**
     * - String()初始化新创建的String对象，使其表示空字符序列
     * - String(byte[] bytes)根据字节数组的内容，来创建新的String
     * - String(char[] value)根据字符数组的内容，来创建新的String
     * - String(String original)根据字符串的内容，来创建新的String
     */
    public static void string() {
        //value[]指向的地址值不能更改
        final char[] value = {'h', 'e', 'l', 'l', 'o', '!'};
        //final char[] valueNew = {'w', 'o', 'r', 'l', 'd', '!'};
        //value = valueNew;//错误
        //value[]里的值可以修改
        value[5] = '.';
        System.out.println(value);

        //创建一个String对象
        //name指向常量池内容为"张三"的字符串常量对象
        String name = "张三";
        //name指向常量池内容为"张三还在"的字符串常量对象
        name += "还在";
        //name指向常量池内容为"张三还在睡觉"的字符串常量对象
        name += "睡觉";
        //输出："张三还在睡觉"
        System.out.println(name);

        //创建字符串对象
        //方式一：直接使用""定义。(推荐方式)
        //查找常量池是否存在该字符串,如果存在,字符串变量直接指向该字符串常量对象
        //如果不存在,在常量池创建该字符串常量对象,然后将字符串变量指向该字符串常量对象
        String s1 = "abc";
        String s2 = "abc";
        //比较的是常量池字符串的地址
        System.out.println(s1 == s2);   //true
        //方式二：通过String类的构造器创建对象
        //先在堆中创建String对象,对象中维护了private final char value[];
        //查找常量池是否存在该字符串,如果存在,String对象的value[]指向该字符串常量对象的value[]
        //如果不存在,在常量池创建该字符串常量对象,然后将String对象的value[]指向该字符串常量对象的value[]
        String s3 = new String("abc");
        String s4 = new String("abc");
        //比较的是String在堆中的地址
        System.out.println(s3 == s4);   //false
        //intern() 返回对象value[]的地址
        //比较的是String对象在堆中value[]属性中的地址
        System.out.println(s3.intern() == s4.intern());   //true

        //字符串内容比较
        String str1 = new String("abc"); //这行代码创建了2个对象
        String str2 = "abc";//导致这行代码创建了0个对象
        System.out.println(str1 == str2);   //false
        System.out.println(str1.intern() == str2);  //true

        String str3 = "abc";
        String str4 = "ab";
        //字符串变量和变量(常量)拼接流程
        //先创建一个空的StringBuilder()对象,依次执行append()，拼接每一个变量(常量)的字符串内容
        //拼接完后的StringBuilder对象查找常量池是否存在相同内容的字符串,没有则创建该字符串
        //最后StringBuilder对象调用toString(),返回一个堆中新的String对象
        //String对象的value[]指向拼接完后(创建)的字符串常量对象的value[]
        String str5 = str4 + "c";
        System.out.println(str3 == str5);   //false
        System.out.println(str3 == str5.intern());  //true

        String str6 = "abc";
        //Java存在编译优化机制,程序在编译时：“a”+“b”+“c”会直接转成"abc"
        String str7 = "a" + "b" + "c";
        System.out.println(str6 == str7);   //true
    }

    /**
     * - public static final Charset US_ASCII = Charset.forName("US-ASCII");
     * - public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
     * - public static final Charset UTF_8 = Charset.forName("UTF-8");
     * - public static final Charset UTF_16BE = Charset.forName("UTF-16BE");
     * - public static final Charset UTF_16LE = Charset.forName("UTF-16LE");
     * - public static final Charset UTF_16 = Charset.forName("UTF-16");
     */
    public static void stringMethod() {
        String str = "hello world ";
        String str1 = "hello world";
        String str2 = "Hello World";
        //char charAt(int index)返回指定索引处的char值
        System.out.println(str.charAt(0));//h

        //int length()返回字符串长度
        System.out.println(str.length());//12

        //String trim()返回忽略前后空白的字符串副本
        System.out.println(str.trim().length());//11

        //String toLowerCase()将字符串单词全部转换为小写
        System.out.println(str.toLowerCase());

        //String toUpperCase()将字符串单词全部转换为大写
        System.out.println(str.toUpperCase());

        //boolean equals(Object anObject)将指定字符串与指定对象进行比较
        System.out.println(str1.equals(str2));//false

        //boolean equalsIgnoreCase(String anotherString)比较两个字符串,忽略大小写
        System.out.println(str1.equalsIgnoreCase(str2));//true

        //int compareTo(String anotherString)按字典顺序比较两个字符串
        //如果前者大，则返回正数，后者大，则返回负数，如果相等，返回 0
        //(1)如果长度相同，并且每个字符也相同，就返回 0
        System.out.println(str.trim().compareTo(str1));//0
        //(2)如果长度相同或者不相同，但是在进行比较时，可以区分大小，就返回
        System.out.println(str1.compareTo(str2));//32
        System.out.println('a' - 'A');//32
        //(3)如果长度不相同,但相同长度内容相同，就返回 str1.len - str2.len
        System.out.println(str.compareTo(str1)); //1
        System.out.println(str.length() - str1.length()); //1

        //int compareToIgnoreCase(String str)按字典顺序比较两个字符串,忽略大小写
        System.out.println(str1.compareToIgnoreCase(str2));//0

        //byte[] getBytes(String charsetName)使用指定字符集将字符串转换为字节数组
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(bytes));//[104, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100]

        //char[] toCharArray()将字符串转换为字符数组
        char[] chars = str.toCharArray();
        System.out.println(Arrays.toString(chars));//[h, e, l, l, o,  , w, o, r, l, d]

        //boolean contains(CharSequence s)判断是否包含指定的字符系列
        System.out.println(str.contains("hello"));//true

        //boolean endsWith(String suffix)判断是否以指定字符串为后缀结束
        System.out.println(str.endsWith("world"));//true

        //boolean startsWith(String prefix)判断是否以指定字符串为前缀开始
        System.out.println(str.startsWith("hello"));//true

        //boolean startsWith(String prefix, int toffset)判断是否以指定字符串为前缀开始,从指定索引开始搜索
        System.out.println(str.startsWith("world", 6));//true

        //String substring(int beginIndex)截取开始索引到结束的字符子串判
        System.out.println(str.substring(6));//world

        //String substring(int beginIndex, int endIndex)截取开始索引到结束索引的字符子串
        //"hello world" -> [2,9) -> llo wor
        System.out.println(str.substring(2, 9));//llo wor

        //static String format(String format, Object... args)格式化字符串
        //占位符有: %s 字符串 %c 字符 %d 整型 %.2f 浮点型
        String name = "张三";
        int age = 10;
        double score = 66.579;
        char gender = '男';
        //将所有的信息都拼接在一个字符串.
        String info = "我叫" + name + ",性别" + gender + ",今年" + age + "岁,期末考试成绩是" + score + "分！！！";
        System.out.println(info);
        //1. %s , %d , %.2f %c 称为占位符,这些占位符由后面变量来替换
        //2. %s 表示后面由 字符串来替换
        //3. %c 使用 char 类型来替换
        //4. %d 是整数来替换
        //5. %.2f 表示使用小数来替换，替换后，只会保留小数点两位, 并且进行四舍五入的处理
        String formatStr = "我叫%s,性别%c,今年%d岁,期末考试成绩是%.2f分！！！";
        String format = String.format(formatStr, name, gender, age, score);
        System.out.println(format);

        System.out.println("-------------------");
        String string = "hello hello";
        //int indexOf(int ch)返回指定字符第一次出现的索引
        System.out.println(string.indexOf('o'));//4

        //int indexOf(int ch, int fromIndex)返回指定字符第一次出现的索引,从指定的索引开始搜索
        System.out.println(string.indexOf('o', string.indexOf('o') + 1));//10

        //int indexOf(String str)返回指定字符串第一次出现的索引
        System.out.println(string.indexOf("llo"));//2

        //int indexOf(String str, int fromIndex)返回指定字符串第一次出现的索引,从指定的索引开始搜索
        System.out.println(string.indexOf("llo", string.indexOf("llo") + 1));//8

        //int lastIndexOf(int ch)返回指定字符最后一次出现的索引
        System.out.println(string.lastIndexOf('o'));//10

        //int lastIndexOf(int ch, int fromIndex)返回指定字符最后一次出现的索引,从指定的索引开始反向搜索
        System.out.println(string.lastIndexOf('o', string.lastIndexOf('o') - 1));//4

        //int lastIndexOf(String str)返回指定字符串最后一次出现的索引
        System.out.println(string.lastIndexOf("llo"));//8

        //int lastIndexOf(String str, int fromIndex)返回指定字符串最后一次出现的索引,从指定的索引开始反向搜索
        System.out.println(string.lastIndexOf("llo", string.lastIndexOf("llo") - 1));//2
    }

    /**
     * - public boolean matches(String regex)断是否匹配指定的正则表达式
     * - public String replaceAll(String regex, String replacement)替换满足正则表达式的所有字符为replacement
     * - public String replaceFirst(String regex, String replacement)替换满足正则表达式的第一个字符为replacement
     * - public String[] split(String regex)根据正则表达式拆分
     * - public String[] split(String regex, int limit)根据正则表达式拆分，从指定的索引开始拆分
     */
    public static void regex() {
        /*
                正则表达式
        ()      标记一个子表达式的开始和结束位置
        (?i)	忽略后面字符的大小写
        (?i)abc	忽略abc的大小写
        []      匹配括号内的任意一个字符
        [abc]   匹配abc之间的任意字母
        [^abc]  匹配abc之外的任何字符
        [a-z]   匹配a-z之间的全部字母
        &&      交集：表示逻辑或操作，不能写单个的&，例如[a-z&&m-p]
        |       并集：写在方括号外面表示并集，可以匹配多个模式中的任意一个，例如(cat|dog)，同时匹配"cat"或"dog"
        .       匹配任意字符(除了换行符)
        \       匹配特殊字符本身
        \d	    匹配一个数字：[0-9]
        \D	    匹配一个非数字： [^0-9]
        \s	    匹配一个空白字符：[ (空格)\f(换页)\n(换行)\r(回车)\t(制表符)\v(垂直制表符)]
        \S	    匹配一个非空白字符：[^ \f\n\r\t\v]
        \w	    匹配一个(字母、数字、下划线):[A-Za-z0-9_]
        \W	    匹配一个非(字母、数字、下划线):[^A-Za-z0-9_]

        限定符(配合匹配多个字符)
        ?       匹配零次或一次
        *       匹配零次或多次
        +       匹配一次或多次
        {n}     匹配正好n次
        {n,}    匹配至少n次
        {n,m}   匹配至少n次且不超过m次

        定位符
        ^   匹配输入字符串开始的位置
        $   匹配输入字符串结尾的位置
        \b  匹配一个单词边界部分：例如，'er\b' 可以匹配"never" 中的 'er'，但不能匹配 "verb" 中的 'er'
        \B  匹配一个单词非边界部分：例如，'er\B' 能匹配 "verb" 中的 'er'，但不能匹配 "never" 中的 'er'

        运算符优先级(相同优先级的从左到右进行运算，不同优先级的运算先高后低)
        \	                                转义符
        (), (?:), (?=), []	                圆括号和方括号
        *, +, ?, {n}, {n,}, {n,m}          	限定符
        ^, $, \任何元字符、任何字符	            定位点和序列（即：位置和顺序）
        |	                                替换，"或"操作，字符具有高于替换运算符的优先级，使得"m|food"匹配"m"或"food"。若要匹配"mood"或"food"，请使用括号创建子表达式，从而产生"(m|f)ood"。
        运算符优先级从高到低的顺序
        转义符号： \ 是用于转义其他特殊字符的转义符号。它具有最高的优先级。 示例：\d、\. 等，其中 \d 匹配数字，\. 匹配点号。
        括号： 圆括号 () 用于创建子表达式，具有高于其他运算符的优先级。 示例：(abc)+ 匹配 "abc" 一次或多次。
        量词： 量词指定前面的元素可以重复的次数。 示例：a* 匹配零个或多个 "a"。
        字符类： 字符类使用方括号 [] 表示，用于匹配括号内的任意字符。 示例：[aeiou] 匹配任何一个元音字母。
        断言： 断言是用于检查字符串中特定位置的条件的元素。 示例：^ 表示行的开头，$ 表示行的结尾。
        连接： 连接在没有其他运算符的情况下表示字符之间的简单连接。 示例：abc 匹配 "abc"。
        管道： 管道符号 | 表示"或"关系，用于在多个模式之间选择一个。 示例：cat|dog 匹配 "cat" 或 "dog"。

        反向引用：略
        修饰符：略
        */
    }

    /**
     * StringBuffer/StringBuilder
     * - StringBuffer/StringBuilder的直接父类是AbstractStringBuilder，实现了Serializable，即对象可以串行化。
     * - AbstractStringBuilder声明属性char[] value; 用于存放字符串的内容，不是final，存放在内存的堆中。
     * - StringBuffer字符内容存储在父类声明的char[] value，里面的值可以变化(增加/删除)，可以直接修改内容，不用更换地址。
     * - StringBuffer/StringBuilder是一个 final 类，不能被继承。
     * <p>
     * AbstractStringBuilder
     * - StringBuffer和StringBuilder方法基本相同，看线程情况分别使用。
     * - StringBuilder的方法，没有做互斥的处理，即没有synchronized关键字，线程不安全，但性能更好。
     * <p>
     * 字符串总结
     * - StringBuilder和StringBuffer非常类似，均代表可变字符序列，而且方法也一样。
     * - String：不可变字符序列，效率低，但是复用率高(一个实例被多个对象引用)，推荐修改次数少的情况使用。
     * - StringBuffer：可变字符序列、效率较高(增删)、线程安全，推荐多线程情况使用。
     * - StringBuilder：可变字符序列、效率最高、线程不安全，推荐单线程情况使用。
     */
    public static void str() {
        StringBuffer stringBuffer = new StringBuffer("hello");

        //StringBuffer append(String str)追加指定字符串
        System.out.println(stringBuffer.append(",world").append("!?!"));//hello,world!?!

        //StringBuffer delete(int start, int end)删除指定索引 start 到 end 之前的所有字符
        //索引从0开始，删除索引[11, 14)的字符 -> "!?!"
        System.out.println(stringBuffer.delete(11, 14));//hello,world

        //StringBuffer deleteCharAt(int index)删除指定索引处的字符
        //索引从0开始，删除索引 5 的字符 -> ","
        System.out.println(stringBuffer.deleteCharAt(5));//helloworld

        //StringBuffer insert(int offset, String str)指定索引处插入指定字符串，其余内容后移
        System.out.println(stringBuffer.insert(5, " "));//hello world

        //void setCharAt(int index, char ch)指定索引处字符，替换为指定字符
        stringBuffer.setCharAt(0, 'H');
        System.out.println(stringBuffer);//Hello world

        //StringBuffer replace(int start, int end, String str)指定索引 start 到 end 之前的字符子串，替换为指定字符串
        //索引从0开始，替换索引[6, 7)的内容 = "w" -> "W"
        System.out.println(stringBuffer.replace(6, 7, "W"));//Hello World

        //StringBuffer reverse()字符串反转
        System.out.println(stringBuffer.reverse());//dlroW olleH
    }
}
