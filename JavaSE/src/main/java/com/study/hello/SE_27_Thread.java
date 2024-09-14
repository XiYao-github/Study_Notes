package com.study.hello;

/**
 * 多线程
 * - 程序是语言编写的指令集合(代码)，进程是运行中的程序(动态过程，存在状态)。
 * - 线程(Thread)是一个程序内部的一条执行路径，main方法的执行就是一条单独的执行路径(主线程)。
 * - 程序中如果只存在一条执行路径，那么这个程序就是单线程的程序。
 * <p>
 * 并发与并行
 * - 正在运行的程序(软件)就是一个独立的进程，线程是属于进程的，多个线程其实是并发与并行同时进行的。
 * - 并发：CPU同时处理线程的数量有限，CPU会轮询为系统的每个线程服务，由于CPU切换的速度很快，给我们的感觉这些线程在同时执行，这就是并发(CPU分时轮询的执行线程)，单核CPU轮询服务实现并发。
 * - 并行：在同一个时刻上，同时有多个线程在被CPU处理并执行(同一个时刻同时在执行)，多核CPU执行任务实现并行。
 * <p>
 */
public class SE_27_Thread {
    public static void main(String[] args) throws InterruptedException {
        newThread();
        threadMethod();
        synchronizedMethod();
    }

    /**
     * Thread
     * - Java是通过java.lang.Thread类来代表线程的。
     * - 每个线程都有优先权，优先级高的线程优先执行，创建线程的线程和被创建的线程拥有相同优先级。
     * - 每个线程都可能标记为守护进程，并且需要先标记为守护线程，守护线程才能执行。
     * - 按照面向对象的思想，Java虚拟机允许应用程序同时运行多个执行线程，Thread类也提供了实现多线程的方式。
     */
    public static void threadMethod() throws InterruptedException {
        Thread thread = new Thread("Test");
        // static native Thread currentThread()返回当前正在执行的线程对象引用
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread); //Thread[main,5,main]

        // static native void sleep(long millis)当前线程停止执行(休眠)指定毫秒数
        Thread.sleep(10000);

        // long getId()返回此Thread的标识符
        System.out.println(thread.getId()); //13

        // String getName()返回此线程的名称
        System.out.println(thread.getName()); //Test

        // void setName(String name)修改此线程的名称
        thread.setName("threadTest");
        System.out.println(thread.getName()); //threadTest

        // int getPriority()返回此线程的优先级(低(1)，中(5)，高(10))
        System.out.println(thread.getPriority()); //5

        // void setPriority(int newPriority)修改此线程的优先级(低(1)，中(5)，高(10))
        thread.setPriority(10);
        System.out.println(thread.getPriority()); //10

        // State getState()返回此线程的状态
        Thread.State state = thread.getState();
        System.out.println(state); //NEW -> 新建状态(NEW) -> 创建线程对象

        // boolean isAlive()测试此线程是否存活
        System.out.println(thread.isAlive()); //true

        // boolean isDaemon()测试此线程是否为守护线程
        System.out.println(thread.isDaemon()); //false

        // void setDaemon(boolean on)将此线程标记为守护线程
        thread.setDaemon(true);
        System.out.println(thread.isDaemon()); //true
    }

    /**
     * 创建线程
     * - 注意：实现多线程重写run()方法，直接调用run方法会当成普通方法执行，此时相当于还是单线程执行，只有调用start方法才是启动一个新的线程执行。
     * - 继承Thread类和实现Runnable接口创建线程本质上没有区别，最后都是调用start()->start0()方法，只是为了避免了Java单继承的限制。
     * <p>
     * - 方式一
     * - 继承Thread类，重写run方法。
     * - 创建线程对象，调用start()方法启动。
     * <p>
     * - 方式二
     * - 实现Runnable接口，重写run()方法。
     * - 创建任务对象，把任务对象交给Thread线程对象处理，调用线程对象的start()方法启动线程。
     */
    public static void newThread() {
        //方式一：继承Thread类，重写run方法
        MyThread myThread = new MyThread(143);
        myThread.start();

        //方式二：实现Runnable接口，重写run()方法
        MyRunnable myRunnable = new MyRunnable(143);
        Thread threadRunnable = new Thread(myRunnable);
        threadRunnable.start();
    }

    /**
     * 线程安全
     * - 多个线程同时操作同一个共享资源的时候可能会出现业务安全问题，称为线程安全问题。
     * - 线程安全问题出现的原因：存在多线程并发，同时访问共享资源，存在修改共享资源。
     * <p>
     * 线程同步
     * - 线程同步是为了解决线程安全问题，让多个线程实现先后依次访问共享资源，这样就解决了安全问题。
     * - 互斥锁：把共享资源进行上锁，每次只能一个线程进入访问，访问完毕以后解锁，然后其他线程才能进来。
     * - synchronized关键字出现在实例方法上锁住的是对象，即对象锁。
     * - synchronized关键字出现在静态方法上锁住的是类，即类锁。
     * - 对象锁可以有多个，new几个对象就有几个对象锁，但是类锁只有一把。
     * <p>
     * - 同步代码块
     * - 把出现线程安全问题的核心代码给上锁。
     * - 对出现问题的核心代码使用synchronized进行加锁，每次只能一个线程占锁进入访问。
     * - 实例方法使用当前对象(this)作为锁对象，静态方法使用字节码对象(类名.class)作为锁对象。
     * <p>
     * - 同步方法
     * - 把出现线程安全问题的核心方法给上锁。
     * - 同步方法其实底层也是有隐式锁对象的，只是锁的范围是整个方法代码。
     * - 同步代码块锁的范围更小，同步方法锁的范围更大。
     */
    public static void synchronizedMethod() {
        // 多个线程的锁对象为同一个对象(synchronizedTest)
        MySynchronized mySynchronized = new MySynchronized();
        /*Thread innerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mySynchronized.method();
            }
        });
        innerThread.start();*/
        Thread lambdaThread = new Thread(() -> mySynchronized.method());
        lambdaThread.start();

        /*Thread innerStaticThread = new Thread(new Runnable() {
            @Override
            public void run() {
                MySynchronized.staticMethod();
            }
        });
        innerStaticThread.start();*/
        Thread lambdaStaticThread = new Thread(() -> MySynchronized.staticMethod());
        lambdaStaticThread.start();
    }
}

class MyThread extends Thread {
    long minPrime;

    MyThread(long minPrime) {
        this.minPrime = minPrime;
    }

    @Override
    public void run() {
        System.out.println("继承Thread类，重写run方法！！！");
    }
}

class MyRunnable implements Runnable {
    long minPrime;

    MyRunnable(long minPrime) {
        this.minPrime = minPrime;
    }

    @Override
    public void run() {
        System.out.println("实现Runnable接口，重写run()方法！！！");
    }
}

class MySynchronized {
    public int n = 10;
    public static int m = 10;

    // 类锁(同步方法)
    public synchronized static void staticMethod() {
        while (true) {
            if (m > 0) {
                System.out.println(Thread.currentThread().getName() + "：m = " + --m);
            } else {
                System.out.println("结束：m = " + m);
                break;
            }
        }
        // 类锁(同步代码块)
        synchronized (MySynchronized.class) {
            while (true) {
                if (m > 0) {
                    System.out.println(Thread.currentThread().getName() + "：m = " + --m);
                } else {
                    System.out.println("结束：m = " + m);
                    break;
                }
            }
        }
    }

    // 对象锁(同步方法)
    public synchronized void method() {
        while (true) {
            if (n > 0) {
                System.out.println(Thread.currentThread().getName() + "：n = " + --n);
            } else {
                System.out.println("结束：n = " + n);
                break;
            }
        }
        // 对象锁(同步代码块)
        // synchronized (synchronizedTest)
        synchronized (this) {
            while (true) {
                if (n > 0) {
                    System.out.println(Thread.currentThread().getName() + "：n = " + --n);
                } else {
                    System.out.println("结束：n = " + n);
                    break;
                }
            }
        }
    }
}