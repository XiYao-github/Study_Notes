package com.study.hello;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SE_19_Date {
    public static void main(String[] args) throws ParseException {
        localDate();
    }

    /**
     * Date
     * - Date类代表当前所在系统的日期时间信息。
     * SimpleDateFormat
     * - 代表简单日期格式化，可以用来把日期时间格式化成为我们想要的形式。
     */
    public static void date() throws ParseException {
        //创建Date对象
        System.out.println(new Date());
        System.out.println(new Date(System.currentTimeMillis()));
        //Date常用方法
        Date date = new Date();
        Date beforeDate = new Date(System.currentTimeMillis() + 100);
        Date afterDate = new Date(System.currentTimeMillis() - 100);
        //毫秒值设置为当前Date对象
        date.setTime(System.currentTimeMillis());
        //获取当前Date对象的毫秒值
        System.out.println(date.getTime());
        System.out.println(System.currentTimeMillis());
        //当前Date日期是否在指定日期之后
        System.out.println(date.after(afterDate));//true
        System.out.println(date.after(beforeDate));//false
        //当前Date日期是否在指定日期之前
        System.out.println(date.before(afterDate));//true
        System.out.println(date.before(beforeDate));//false

        /*
        G       纪元标记                    AD
        y       四位年份                    2001
        M       月份                       July or 07
        d       一个月的日期                 10
        h       A.M./P.M. (1~12)格式小时    12
        H       一天中的小时 (0~23)          22
        m       分钟数                      30
        s       秒数                        55
        S       毫秒数                      234
        E       星期几                      Tuesday
        D       一年中的日子                 360
        F       一个月中第几周的周几           2 (second Wed. in July)
        w       一年中第几周                 40
        W       一个月中第几周                1
        a       A.M./P.M. 标记              PM
        k       一天中的小时(1~24)            24
        K       A.M./P.M. (0~11)格式小时     10
        z       时区                        Eastern Standard Time
        '       文字定界符                   Delimiter
        "       单引号                      `
         */
        //创建简单日期格式化对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy 年 MM 月 dd 日 E hh:mm:ss");
        //日期格式化
        System.out.println(sdf.format(new Date()));
        System.out.println(sdf.format(System.currentTimeMillis()));
        //解析格式化日
        String format = sdf.format(new Date());
        Date parse = sdf.parse(format);
        System.out.println(parse);
        System.out.println(sdf.parse(sdf.format(System.currentTimeMillis())));
    }

    /**
     * Instant
     * - JDK8获取时间戳特别简单，且功能更丰富。
     * - Instant类由一个静态的工厂方法now()可以返回当前时间戳。
     */
    public static void instant() {
        Instant instant = Instant.now();
        System.out.println("当前时间戳是：" + instant);
        //时间戳是包含日期和时间的,与java.util.Date很类似,事实上Instant就是类似JDK8以前的Date。
        Date dateNow = Date.from(instant);
        System.out.println("当前时间是：" + dateNow);
        //Instant和Date这两个类可以进行转换。
        instant = dateNow.toInstant();
        System.out.println("当前时间戳是：" + instant);
    }

    /**
     * 日期时间
     * - LocalDate：日期。
     * - LocalTime：时间。
     * - LocalDateTime：日期时间。
     * - DateTimeFormatter：用于做日期与时间格式化和解析
     * - 新增的API严格区分了时刻、本地日期、本地时间，并且，对日期和时间进行运算更加方便。
     * - 新API的类型几乎全部是不变类型(和String的使用类似)，可以放心使用不必担心被修改。
     */
    public static void localDate() {
        //创建日期时间
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = LocalDate.of(1999, 2, 4);
        LocalTime localTime1 = LocalTime.now();
        LocalTime localTime2 = LocalTime.of(18, 32, 50);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(LocalDateTime.of(1999, 2, 4, 18, 32, 50));
        System.out.println(LocalDateTime.of(localDate2, localTime2));
        System.out.println("---------------------------");
        //获取日期信息
        System.out.println("年=" + localDateTime.getYear());
        System.out.println("月=" + localDateTime.getMonthValue());
        System.out.println("月=" + localDateTime.getMonth());
        System.out.println("日=" + localDateTime.getDayOfMonth());
        System.out.println("日=" + localDateTime.getDayOfWeek());
        System.out.println("时=" + localDateTime.getHour());
        System.out.println("分=" + localDateTime.getMinute());
        System.out.println("秒=" + localDateTime.getSecond());
        System.out.println("---------------------------");
        //转换时间类型
        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println(localDate);
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println(localTime);
        System.out.println("---------------------------");
        //修改日期信息
        LocalDate now = LocalDate.now();
        LocalDate beforeDate = now.plusDays(1);
        LocalDate afterDate = now.minusDays(1);
        //检查此日期时间是否在指定日期时间之后
        System.out.println(now.isAfter(afterDate));//true
        System.out.println(now.isAfter(beforeDate));//false
        //检查此日期时间是否在指定日期时间之前
        System.out.println(now.isBefore(afterDate));//false
        System.out.println(now.isBefore(beforeDate));//true
        System.out.println("---------------------------");
        //创建日期时间格式化对象
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("yyyy 年 MM 月 dd 日 E");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("hh:mm:ss");
        DateTimeFormatter dtfDateTime = DateTimeFormatter.ofPattern("yyyy 年 MM 月 dd 日 E hh:mm:ss");
        //日期格式化
        System.out.println(localDate1.format(dtfDate));
        System.out.println(dtfDate.format(localDate1));
        System.out.println(localTime1.format(dtfTime));
        System.out.println(dtfTime.format(localTime1));
        System.out.println(localDateTime.format(dtfDateTime));
        System.out.println(dtfDateTime.format(localDateTime));
    }
}
