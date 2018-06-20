package _24Points;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

class TreeNode {
    TreeNode left = null;
    TreeNode right = null;
    String operater = "";
    int key = 0;

    public TreeNode() {
        this.left = null;
        this.right = null;
    }

    public TreeNode(int key) {
        this.left = null;
        this.right = null;
        this.key = key;
    }

    public TreeNode(int key, String operater) {
        this.key = key;
        this.operater = operater;
        this.left = null;
        this.right = null;
    }

    public void set_expression(TreeNode left, TreeNode right, String operater) {
        this.left = left;
        this.right = right;
        this.operater = operater;

    }
}

public class Solution {

    public List<TreeNode> buildTree(TreeNode left, TreeNode right) {
        List<TreeNode> treeList = new ArrayList<>();
        TreeNode node1 = new TreeNode();
        node1.set_expression(left, right, "+");
        treeList.add(node1);
        TreeNode node2 = new TreeNode();
        node2.set_expression(left, right, "-");
        treeList.add(node2);
        TreeNode node3 = new TreeNode();
        node2.set_expression(left, right, "*");
        treeList.add(node3);
        if (right.key != 0) {
            TreeNode node4 = new TreeNode();
            node2.set_expression(left, right, "/");
            treeList.add(node4);
        }
        return treeList;
    }

    public List<TreeNode> buildAllTrees(List<Integer> input) {
        if (input.size() == 1) {
            TreeNode a = new TreeNode(input.get(0));
            List<TreeNode> c = new ArrayList<>();
            c.add(a);
            return c;
        }
        List<TreeNode> treeList = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            List<Integer> left = new ArrayList<>();
            List<Integer> right = new ArrayList<>();
            for (int j = 0; j < input.size(); j++) {
                if (j < i)
                    left.add(input.get(j));
                else
                    right.add(input.get(j));
            }
            List<TreeNode> leftTrees = buildAllTrees(left);
            List<TreeNode> rightTrees = buildAllTrees(right);

            for (int j = 0; j < leftTrees.size(); j++) {
                for (int k = 0; k < rightTrees.size(); k++) {
                    List<TreeNode> Combined = buildTree(leftTrees.get(j), rightTrees.get(k));
                    for (int m = 0; m < Combined.size(); m++) {
                        treeList.add(Combined.get(m));
                    }
                }
            }
        }
        return treeList;
    }



}
