package lambdaExpression;

public class Solution {
    void lambda() {
//        (String s)->s.length();
//        ()->{System.out.println("aaa");};
//        x->x*x;
        new Thread(() -> System.out.println("aaa")).start();
        //装箱与拆箱 boxing
        Integer I = 10;//=Integer.valueOf()
        int i = I;//===I.intValue()
        Object[] array = {"aaa", 1};
        //Enum
    }

    enum Light {Red, yellow, Green}
    //= class Light extends java.lang.Enum

    enum Direction {
        EAST("东", 1),
        SOUTH("西", 3),
        WEST("东", 2),
        NORTH("东", 2);

        public String getDesc() {
            return desc;
        }

        public int getNum() {
            return num;
        }

        private String desc;
        private int num;

        private Direction(String desc, int num) {
            this.desc = desc;
            this.num = num;
        }

    }
//annotation 注解

    public static void main(String[] args) {
        Light light = Light.Red;
    }
}
