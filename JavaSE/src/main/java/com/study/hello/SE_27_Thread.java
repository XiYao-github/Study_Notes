package com.study.hello;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
        // threadMethod();
        // newThread();
        // synchronizedMethod();
    }

    /**
     * Thread
     * - Java是通过java.lang.Thread类来代表线程的。
     * - 每个线程都有优先权，优先级高的线程优先执行，创建线程的线程和被创建的线程拥有相同优先级。
     * - 每个线程都可能标记为守护进程，并且需要先标记为守护线程，守护线程才能执行。
     * - 按照面向对象的思想，Java虚拟机允许应用程序同时运行多个执行线程，Thread类也提供了实现多线程的方式。
     * <p>
     * Thread构造器
     * - Thread() 创建默认线程对象
     * - Thread(Runnable target) 创建包含可执行对象的线程实例
     * - Thread(Runnable target, String name) 创建包含可执行对象，指定名称的线程对象
     * - Thread(String name) 创建指定名称的线程对象
     */
    public static void threadMethod() {
        Thread thread = new Thread("ThreadTest");
        // static native Thread currentThread()返回当前正在执行的线程对象引用
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread); //Thread[main,5,main]

        // static native void sleep(long millis)当前线程停止执行(休眠)指定毫秒数
        // Thread.sleep(10000);

        // long getId()返回此Thread的标识符
        System.out.println(thread.getId()); //20(随机)

        // String getName()返回此线程的名称
        System.out.println(thread.getName()); //ThreadTest

        // void setName(String name)修改此线程的名称
        thread.setName("TestThread");
        System.out.println(thread.getName()); //TestThread

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

        /*Thread innerStaticThread = new Thread(new Runnable() {
            @Override
            public void run() {
                MySynchronized.staticMethod();
            }
        });
        innerStaticThread.start();*/
        Thread lambdaStaticThread = new Thread(() -> MySynchronized.staticMethod());

        lambdaThread.start();
        lambdaStaticThread.start();
    }

    /**
     * 线程通信
     * - 所谓线程通信就是线程间相互发送数据，线程间共享一个资源即可实现线程通信。
     * - 通过共享一个数据的方式实现，根据共享数据的情况决定自己该怎么做，以及通知其他线程怎么做。
     * - 前提：线程通信通常是在多个线程操作同一个共享资源的时候需要进行通信，且要保证线程安全。
     * - 注意：方法应该使用当前同步锁对象(this)进行调用。
     * <p>
     * Object类等待和唤醒线程的方法
     * - void wait() 让当前线程等待并释放所占锁，直到另一个线程调用notify()方法或notifyAll()方法
     * - void notify() 唤醒正在等待的单个线程
     * - void notifyAll() 唤醒正在等待的所有线程
     * Thread类等待和唤醒线程的方法
     * - static native void sleep(long millis)当前线程停止执行(休眠)指定毫秒数
     * - void join() 等待此线程终止(调用此方法的线程先执行完)
     * <p>
     * 线程状态
     * - 线程的状态：也就是线程从生到死的过程，以及中间经历的各种状态及状态转换，理解线程的状态有利于提升并发编程的理解能力。
     * - Java总共定义了6种状态，6种状态都定义在Thread类的内部枚举类中。
     */
    public static void threadState() {
        // NEW(新建)：线程刚被创建，但是并未启动
        Thread.State aNew = Thread.State.NEW;
        // RUNNABLE(可运行)：线程已经调用了Thread.start()
        // 等待CPU调度，被挂起(就绪状态)
        // 获得CPU资源，在运行(运行状态)
        Thread.State runnable = Thread.State.RUNNABLE;
        // Blocked(锁阻塞)：
        // 线程执行时未竞争到锁对象，等待锁对象进入同步块/方法，
        // 线程调用Object.wait()后重新进入同步块/方法。
        Thread.State blocked = Thread.State.BLOCKED;
        // Waiting(无限等待)：
        // 线程调用了Object.wait()，需要另一个线程对该对象调用Object.notify()/Object.notifyAll()才能够唤醒
        // 其他线程调用了Thread.join()，需要等待指定线程终止才能够唤醒
        Thread.State waiting = Thread.State.WAITING;
        // TimedWaiting(计时等待)：线程调用了Thread.sleep(time)/Object.wait(time)/Thread.join(time)，等待指定时间再次唤醒，但是有超时参数
        Thread.State timedWaiting = Thread.State.TIMED_WAITING;
        // Terminated(被终止)：
        // 已终止线程的线程状态，捕获到异常终止了Thread.run()。
        // 线程已完成执行，执行完Thread.run()正常退出。
        Thread.State terminated = Thread.State.TERMINATED;
    }

    /**
     * 线程池
     * - 线程池就是一个可以复用线程的技术，ExecutorService(接口)代表线程池。
     * - 使用ExecutorService的实现类ThreadPoolExecutor自创建一个线程池对象。
     * <p>
     * 线程池方法
     * - void execute(Runnable command) 执行任务/命令，没有返回值，一般用来执行Runnable任务
     * - <T> Future<T> submit(Callable<T> task) 执行任务，返回未来任务对象获取线程结果，一般拿来执行Callable任务
     * - void shutdown() 等待任务执行完毕后关闭线程池
     * - List<Runnable> shutdownNow() 立刻关闭，停止正在执行的任务，并返回队列中未执行的任务
     * <p>
     * 新任务拒绝策略
     * - ThreadPoolExecutor.AbortPolicy 丢弃任务并抛出RejectedExecutionException异常(默认)
     * - ThreadPoolExecutor.DiscardPolicy 丢弃任务，但是不抛出异常这是不推荐的做法
     * - ThreadPoolExecutor.DiscardOldestPolicy 抛弃队列中等待最久的任务然后把当前任务加入队列中
     * - ThreadPoolExecutor.CallerRunsPolicy 由主线程负责调用任务的run()方法从而绕过线程池直接执行
     */
    public static void threadPool() {
		/*
			public ThreadPoolExecutor(  int corePoolSize,
										int maximumPoolSize,
										long keepAliveTime,
										TimeUnit unit,
										BlockingQueue<Runnable> workQueue,
										ThreadFactory threadFactory,
										RejectedExecutionHandler handler)
			参数一：指定线程池的线程数量(核心线程)：corePoolSize      --------> 不能小于0
			参数二：指定线程池可支持的最大线程数：maximumPoolSize     --------> 最大线程数量 >= 核心线程数量
			参数三：指定临时线程的最大存活时间：keepAliveTime         --------> 不能小于0，临时线程数 = 最大线程数 - 核心线程数
			参数四：指定存活时间的单位(秒、分、时、天)：unit           --------> 时间单位
			参数五：指定任务队列：workQueue                        --------> 不能为null
			参数六：指定用哪个线程工厂创建线程：threadFactory         --------> 不能为null
			参数七：指定线程忙，任务满的时候，新任务来了怎么办：handler  --------> 不能为null
			// 新任务提交时发现核心线程都在忙，任务队列也满了，并且还可以创建临时线程，此时才会创建临时线程。
			// 核心线程和临时线程都在忙，任务队列也满了，新的任务过来的时候才会开始任务拒绝。
		 */
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                3,
                5,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(6),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
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
        System.out.println("实现Runnable接口，重写run方法！！！");
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