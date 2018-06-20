package _longestMaxSumSubString;

public class Solution {
//1, -2, 3, 10, -4, 7, 2, -5

    public static int returnMAx(int[] a) {
        int max = Integer.MIN_VALUE;
        int tmp = a[0];
        for (int i = 1; i < a.length; i++) {
            if ((tmp + a[i] < a[i])) tmp = a[i];
            else
                tmp = tmp + a[i];
            max = Math.max(tmp, max);
        }
        return max;
    }

    public static int returnMAx2(int[] a) {
        int re = returnMAxtmp(a, a.length - 1, new int[a.length]);
        return re;
    }

    public static int returnMAxtmp(int[] a, int length, int[] tmp) {
        if (length == 0 || returnMAxtmp(a, length - 1, tmp) <= 0) {
            tmp[length] = a[length];
            return tmp[length];
        }
        else{
            tmp[length] = tmp[length-1]+a[length];
            return tmp[length];
        }
    }

    public static void main(String[] args) {
        int[] a = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.print(returnMAx2(a));
    }
}
