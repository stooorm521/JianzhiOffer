package BACktracingjavaMAZE;

import java.util.*;

public class yaoShaizi {

    void test(int n) {
        List<List<Integer>> re = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        backtracing(n, 1, re, tmp);
        System.out.println(re);
        caculate(re);
    }

    void backtracing(int n, int start, List<List<Integer>> container, List<Integer> tmp) {
        if (start > n) {
            container.add(new ArrayList<>(tmp));
            tmp = new ArrayList<>();
            return;
        }
        for (int i = 1; i < 7; i++) {
            tmp.add(i);
            backtracing(n, start + 1, container, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    void caculate(List<List<Integer>> re) {
        List<Double> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < re.size(); i++) {
            int sum = 0;
            for (int j = 0; j < re.get(i).size(); j++) {
                sum += re.get(i).get(j);
            }
            if (!map.containsKey(sum)) map.put(sum, 1);
            else
                map.put(sum, map.get(sum) + 1);
        }
        double sum = 0f;
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            sum = sum + (Integer) it.next();
        }
        Iterator it2 = map.values().iterator();
        while (it2.hasNext()) {
            System.out.println((Integer) it2.next() / sum);
        }
    }


    public static void main(String[] args) {
        new yaoShaizi().test(3);
    }
}
