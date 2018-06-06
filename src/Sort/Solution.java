package Sort;

public class Solution {
    static int divide(int[] a, int start, int end) {
        int base = a[end];
        //选取最后一个数字作为基准
        while (start < end) {
            while (start < end && a[start] < base) {
                start++;
            }
            //此时筛出了第一个a[start]>=base的地方
            //上面的while循环结束时，就说明当前的a[start]的值比基准值大，应与基准值进行交换
            if (start < end) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值右边)，因此右边也要同时向前移动一位
                end--;
            }
            //左一下右一下
            while (start < end && a[end] >= base)
                //从右边开始遍历，如果比基准值大，就继续向左走
                end--;
            //上面的while循环结束时，就说明当前的a[end]的值比基准值小，应与基准值进行交换
            if (start < end) {
                //交换
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值左边)，因此左边也要同时向后移动一位
                start++;
            }
        }
        //当start与end会面的时候结束
        return end;
    }

    public static void sort(int[] a, int start, int end) {
        //如果只有一个元素，就不用再排下去了
        if (start > end) {
            return;
        } else {
            //如果不止一个元素，继续划分两边递归排序下去
            int partition = divide(a, start, end);
            sort(a, start, partition - 1);
            sort(a, partition + 1, end);
        }
    }
}
