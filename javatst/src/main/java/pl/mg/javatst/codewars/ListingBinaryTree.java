package pl.mg.javatst.codewars;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Sort binary tree by levels kata
 */
public class ListingBinaryTree {
    public static void main(String[] args) {
        List<Integer> result = ListingBinaryTree.treeByLevels(new Node(new Node(null, new Node(null, null, 4), 2),
                new Node(new Node(null, null, 5), new Node(null, null, 6), 3), 1));
        System.out.println(result);

        printLevelOrder(new Node(new Node(null, new Node(null, null, 4), 2),
                new Node(new Node(null, null, 5), new Node(null, null, 6), 3), 1));
    }

    public static List<Integer> treeByLevels(Node node) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> levels = new HashMap<>();
        if (node == null) {
            return result;
        }
        int height = height(node);
        getDeeper(node, height, levels);
        for (Integer kkey : levels.keySet().stream().sorted(Collections.reverseOrder()).collect(Collectors.toList())) {
            result.addAll(levels.get(kkey));
        }
        return result;
    }

    private static void getDeeper(Node node, int height, Map<Integer, List<Integer>> levels) {
        if (node == null) {
            return;
        } else {
            if (levels.containsKey(height)) {
                levels.get(height).add(node.value);
            } else {
                levels.put(height, new ArrayList<>());
                levels.get(height).add(node.value);
            }
            getDeeper(node.left, height - 1, levels);
            getDeeper(node.right, height - 1, levels);
        }
    }

    static void printLevelOrder(Node root) {
        int h = height(root);
        int i;
        for (i = 1; i <= h; i++)
            printCurrentLevel(root, i);
    }


    static int height(Node root) {
        if (root == null)
            return 0;
        else {
            /* compute  height of each subtree */
            int lheight = height(root.left);
            int rheight = height(root.right);

            /* use the larger one */
            if (lheight > rheight)
                return (lheight + 1);
            else
                return (rheight + 1);
        }
    }

    /* Print nodes at the current level */
    static void printCurrentLevel(Node root, int level) {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.value + " ");
        else if (level > 1) {
            printCurrentLevel(root.left, level - 1);
            printCurrentLevel(root.right, level - 1);
        }
    }
}
