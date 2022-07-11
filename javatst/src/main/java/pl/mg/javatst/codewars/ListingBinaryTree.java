package pl.mg.javatst.codewars;

import java.util.ArrayList;
import java.util.List;

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
        if (node == null) {
            return result;
        }
        result.add(1);
        return result;
    }

    static void printLevelOrder(Node root) {
        int h = height(root);
        int i;
        for (i = 1; i <= h; i++)
            printCurrentLevel(root, i);
    }

    /* Compute the "height" of a tree -- the number of
    nodes along the longest path from the root node
    down to the farthest leaf node.*/
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
