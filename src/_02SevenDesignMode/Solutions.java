package _02SevenDesignMode;

public class Solutions {
        /**
         * 单例模式，饿汉式，线程安全
         */
        public static class Singleton {
            //INSTANCE为实力化对象
            // 单例模式根据实例化对象时机的不同分为两种：一种是饿汉式单例，一种是懒汉式单例。
            // 饿汉式单例在单例类被加载时候，就实例化一个对象交给自己的引用；而懒汉式在调用取得实例方法的时候才会实例化对象。代码如下：
            private final static Singleton INSTANCE = new Singleton();

            private Singleton() {

            }

            public static Singleton getInstance() {
                return INSTANCE;
            }
        }

        /**
         * 单例模式，懒汉式，线程不安全
         */
        public static class Singleton2 {
            private static Singleton2 instance = null;

            private Singleton2() {

            }

            public static Singleton2 getInstance() {
                if (instance == null) {
                    instance = new Singleton2();
                }

                return instance;
            }
        }


        /**
         * 单例模式，懒汉式，线程安全，多线程环境下效率不高
         */
        public static class Singleton3 {
            private static Singleton3 instance = null;

            private Singleton3() {

            }

            public static synchronized Singleton3 getInstance() {
                if (instance == null) {
                    instance = new Singleton3();
                }

                return instance;
            }
        }

        /**
         * 单例模式，懒汉式，变种，线程安全
         */
        public static class Singleton4 {
            private static Singleton4 instance = null;

            static {
                instance = new Singleton4();
            }

            private Singleton4() {

            }

            public static Singleton4 getInstance() {
                return instance;
            }
        }

        /**
         * 单例模式，使用静态内部类，线程安全【推荐】
         */
        public static class Singleton5 {
            private final static class SingletonHolder {
                private static final Singleton5 INSTANCE = new Singleton5();
            }

            private Singleton5() {

            }

            public static Singleton5 getInstance() {
                return SingletonHolder.INSTANCE;
            }
        }

        /**
         * 静态内部类，使用枚举方式，线程安全【推荐】
         */
        public enum Singleton6 {
            INSTANCE;

            public void whateverMethod() {

            }
        }

        /**
         * 静态内部类，使用双重校验锁，线程安全【推荐】
         */
        public static class Singleton7 {
            private volatile static Singleton7 instance = null;

            private Singleton7() {

            }

            public static Singleton7 getInstance() {
                if (instance == null) {
                    synchronized (Singleton7.class) {
                        if (instance == null) {
                            instance = new Singleton7();
                        }
                    }
                }

                return instance;
            }
        }

        public static void main(String[] args) {
            System.out.println(Singleton.getInstance() == Singleton.getInstance());
            System.out.println(Singleton2.getInstance() == Singleton2.getInstance());
            System.out.println(Singleton3.getInstance() == Singleton3.getInstance());
            System.out.println(Singleton4.getInstance() == Singleton4.getInstance());
            System.out.println(Singleton5.getInstance() == Singleton5.getInstance());
            System.out.println(Singleton6.INSTANCE == Singleton6.INSTANCE);
            System.out.println(Singleton7.getInstance() == Singleton7.getInstance());
        }

    }

