package aboutDFS;

import java.util.ArrayList;
import java.util.List;

public class DFS {
    public static List<String> subSets(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        StringBuffer subResult = new StringBuffer();
        DFS(set, 0, subResult, result);
        return result;
    }

    private static void DFS(String set, int level, StringBuffer subResult, List<String> result) {
        if (level == set.length()) {
            result.add(subResult.toString());
            return;
        }
        subResult.append(set.charAt(level));
        DFS(set, level + 1, subResult, result);
        subResult.deleteCharAt(subResult.length() - 1);
        DFS(set, level + 1, subResult, result);
    }

    public static void main(String[] args) {
        System.out.println(subSets("abyyydac").toString());
    }
}
