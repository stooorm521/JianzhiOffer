package 线程安全与线程不安全;

public class getInstance {
    class Word{}
    public static void main(String[] args) {
//      Word word = new Word();
//      ClassLoader classLoader = word.getClass().getClassLoader();
        ClassLoader classLoader = Word.class.getClassLoader();
        System.out.println(classLoader); //注：Word.class是由sun.misc.Launcher$AppClassLoader@1372a1a加载器加载的

        try {
            Class<?> c = classLoader.loadClass(args[0]); //1、必须要先加载该类；

            Object o1 = c.newInstance(); //2、然后，才可以使用.newInstance()方法来新建实例对象。
            Object o2 = c.newInstance();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
