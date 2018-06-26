package BACktracingjavaMAZE;

import java.util.ArrayList;
import java.util.List;

public class backTrackingtest {
    //打印到a的全排列

    void backtracking(int a, int start, List<Integer> tmp) {
        if (start > a) {
//            System.out.println(tmp);
            process(tmp);
            tmp = new ArrayList<>();
            return;
        }
        for (int i = 1; i < a; i++) {
            tmp.add(i);
            backtracking(a, start + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }


    int o1 = 0;
    int o2 = 0;
    int o3 = 0;
    int o4 = 0;
    int o5 = 0;
    int o6 = 0;

    void process(List<Integer> tmp) {
        for (int i = 0; i < tmp.size(); i++) {
            int f = tmp.get(i);
            if (f == 1) o1++;
            else if (f == 2) o2++;
            else if (f == 3) o3++;
            else if (f == 4) o4++;
            else if (f == 5) o5++;
            else if (f == 6) o6++;
        }
    }

    public static void main(String[] args) {
        backTrackingtest a = new backTrackingtest();
        a.backtracking(7, 1, new ArrayList<>());
        System.out.println(a.o1);
        System.out.println(a.o2);
        System.out.println(a.o3);
        System.out.println(a.o4);
        System.out.println(a.o5);
        System.out.println(a.o6);
    }
}
