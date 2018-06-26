package Graph;

import java.util.*;

public class Solution {
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

    class GraphNode {
        public int key;
        public List<GraphNode> neighbors;

        public GraphNode(int key) {
            this.key = key;
            this.neighbors = new ArrayList<>();
        }
    }

    public class Bipartite {


        public boolean isBipartite(List<GraphNode> graph) {
            if (graph == null || graph.size() <= 1) {
                return true;
            }

            HashMap<GraphNode, Integer> visited = new HashMap<>();
            for (GraphNode node : graph) {
                if (!BFS(node, visited)) {
                    return false;
                }
            }
            return true;
        }

        private boolean BFS(GraphNode node, HashMap<GraphNode, Integer> visited) {
            if (visited.containsKey(node)) {
                return true;
            }
            visited.put(node, 0);
            Queue<GraphNode> queue = new LinkedList<>();
            queue.offer(node);

            while (!queue.isEmpty()) {
                GraphNode curr = queue.poll();
                Integer currGroup = visited.get(curr);
                Integer belong = currGroup == 0 ? 1 : 0;
                for (GraphNode neig : curr.neighbors) {
                    if (!visited.containsKey(neig)) {
                        visited.put(neig, belong);
                        queue.offer(neig);
                    } else {
                        if (visited.get(neig).equals(currGroup)) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }


        public static List<List<Integer>> combinations(int target, int[] coins) {
            List<List<Integer>> result = new ArrayList<>();
            if (coins == null || coins.length == 0) {
                return result;
            }
            DFS1(target, coins, 0, new ArrayList<>(), result);
            return result;
        }

        private static void DFS1(int remain, int[] coins, int level, List<Integer> solution, List<List<Integer>> result) {
            if (level == coins.length - 1) {
                if (remain % coins[coins.length - 1] == 0) {
                    solution.add(remain / coins[coins.length - 1]);
                    result.add(new ArrayList<>(solution));
                    solution.remove(solution.size() - 1);
                }
                return;
            }
            for (int i = 0; i <= remain / coins[level]; i++) {
                solution.add(i);
                DFS1(remain - i * coins[level], coins, level + 1, solution, result);
                solution.remove(solution.size() - 1);
            }
        }

        public static void main(String[] args) {
            System.out.println(Arrays.toString(combinations(0, new int[]{2, 1}).toArray()));
        }

        public static void main(String[] args) {

        }

    }
