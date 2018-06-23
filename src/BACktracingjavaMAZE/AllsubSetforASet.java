package BACktracingjavaMAZE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllsubSetforASet {
    //字符串的全排列
    // abc bca bac cba..
    public ArrayList<String> permutation(String str) {
        ArrayList<String> ans = new ArrayList<>();//所有排列的可能都在这里
        if (str != null || str.length() > 0) {
            help(0, str.toCharArray(), ans);
            Collections.sort(ans);
        }
        return ans;
    }

    //i指的是起始序号
    public static void help(int i, char[] chars, ArrayList<String> ans) {
        if (i == chars.length - 1) {
            String val = String.valueOf(chars);
            if (!ans.contains(val))
                ans.add(val);
            else {
                for (int j = i; j < chars.length; j++) {
                    swap(i, j, chars);//依次选一个数固定住
                    help(i + 1, chars, ans);//让后面的进行全排列
                    swap(i, j, chars);//恢复原来的模样，回溯关键
                }
            }
        }
    }

    public static void swap(int i, int j, char[] chars) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

//集合的所有子集
//    递归思路:
//
//            * 例如：对于集合{a,b,c}来说，我们如果获得它的所有子集，我们可以分两部分：
//            * 1.获取集合{b,c}的所有子集（问题规模见减小）
//
//            * 2.{b,c}的所有子集中都添加进入a
//* 这两部分的和，恰好是整个集合{a,b,c}的子集

    public static List<String> subSet(String str) {
        List<String> list = new ArrayList<>();
        if (str.length() == 0) {//递归结束条件
            list.add(null);//如果字符串为空，添加一个空集合
            return list;
        }
        //获取str中，除去第一个元素后，子串的所有元素的子集
        List<String> subList = subSet(str.substring(1));
        for (String string : subList) {
            if (string == null)
                list.add("" + str.charAt(0));
            else
                list.add(string + str.charAt(0));
        }
        list.addAll(subList);
        return list;
    }

    public static void main(String[] args) {
        System.out.println(subSet("abcm"));
    }
}
