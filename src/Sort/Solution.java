package Sort;

public class Solution {
    int divide(int[] a, int start, int end){
        int base=a[end];
        //选取最后一个数字作为基准
        while(start<end){
            while (start<end&&a[start]<base){
                start++;
            }
            //此时筛出了第一个a[start]>=base的地方
            if(start<end){
                
            }
        }
    }
}
