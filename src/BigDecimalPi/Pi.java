package BigDecimalPi;


import InputOutput.Solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.math.*;

/**
 * Title:π 圆周率的计算
 * Description:使用java BigDecimal完成圆周率(Chudnovsky公式)的计算。可以指定计算精度和计算参数。
 * Copyright: Copyright (c) 2004
 * <a href="http://www.jason314.com/palgorithm.htm">圆周率的计算方法#Chudnovsky公式</a>
 * <a href="http://www.oursci.org/magazine/200301/030126.htm">圆周率π的计算历程</a>
 *
 * @author aliyunzixun@xxx.com
 * @version 1.0
 */

public class Pi {

    private BigDecimal
            result;

    public Pi() {

    }

    public void run(int n, int scale) {
        if (System.getProperty("debug") != null) {
            System.out.println("n=" + n);
        }

        BigDecimal upper = new BigDecimal(426880.0000 * Math.sqrt(10005.0000));
        // ROUND_HALF_EVEN的意思就是，将这个BigDecimal小数点后保留x位
        // 四舍五入的方式为向最接近数字方向舍入的舍入 模式，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。
        // 看来以后要用bigDecimal还必须要用它这个scale的功能来保证精度，不然还是跟double一样会出现悲剧！

       /* Because
        BigDecimal aa = new BigDecimal(135.95);
        BigDecimal bb=new BigDecimal("100");
        System.out.println(aa.multiply(bb));


        BigDecimal test = new BigDecimal(4.015);
        BigDecimal test1 = new BigDecimal(100);
        System.out.println(test.multiply(test1));
        13594.99999999999886313162278383970260620117187500
        401.49999999999996802557689079549163579940795898437500
        */

        upper = upper.setScale(scale, BigDecimal.ROUND_HALF_EVEN);

        if (System.getProperty("debug") != null) {
            System.out.println("upper=" + upper);
        }

        BigDecimal downer = BigDecimal.valueOf(0);

        for (int i = 0; i <= n; i++) {
            BigDecimal d0 = factorial(6 * i).multiply(new BigDecimal(545140134 * i + 13591409));
            BigDecimal d1 = factorial(i);
            BigDecimal d2 = pow(d1, 3);
            BigDecimal d3 = factorial(3 * i);
            BigDecimal d4 = pow(-640320, 3 * i);
            BigDecimal d5 = d2.multiply(d3);
            BigDecimal d6 = d4.multiply(d5);
            BigDecimal d = d0.divide(d6, BigDecimal.ROUND_HALF_EVEN);
            downer = downer.add(d);

        }

        if (System.getProperty("debug") != null) {
            System.out.println("downer=" + downer);
        }

        if (System.getProperty("debug") != null) {
            System.out.println("upper.scale = " + upper.scale());
            System.out.println("downer.scale = " + downer.scale());
        }

        result = upper.divide(downer, BigDecimal.ROUND_HALF_EVEN);

        if (System.getProperty("debug") != null) {
            System.out.println("result.scale = " + result.scale());
        }

    }

    public BigDecimal getResult() {

        return result;
    }

    public static BigDecimal pow(double val, int power) {
        return pow(new BigDecimal(val), power);
    }

    public static BigDecimal pow(BigDecimal val, int power) {
        BigDecimal value = BigDecimal.valueOf(1);
        for (int i = 1; i <= power; i++) {
            value = value.multiply(val);
        }
        return value;
    }

    public static BigDecimal factorial(int n) {
        BigDecimal value = BigDecimal.valueOf(1);
        for (int i = 1; i <= n; i++) {
            value = value.multiply(BigDecimal.valueOf(i));
        }
        return value;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("usage: " + "java nz-baby.Pi " + " 参数(0-无穷大) 精度(1-4294967296)");
            System.out.println("usage: " + "java nz-baby.Pi " + " n(0- ) scale(1-4294967296)");
            System.exit(-1);
        }

        int n = Integer.parseInt(args[0]);
        int scale = Integer.parseInt(args[1]);

        System.out.println("palgorithm n=" + n + " scale=" + scale);
        System.out.println("start:" + new Date(System.currentTimeMillis()));
        Pi p = new Pi();
        p.run(n, scale);
        System.out.println("end:" + new Date(System.currentTimeMillis()));
//        System.out.println("result=" + p.getResult());
        try {
            File file = new File("5.txt");
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(p.getResult());// 往文件里写入字符串
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
