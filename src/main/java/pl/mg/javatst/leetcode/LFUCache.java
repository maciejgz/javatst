package pl.mg.javatst.leetcode;

import java.util.HashMap;

public class LFUCache {

    private HashMap<Integer, Node> byKey = new HashMap<>();
    private HashMap<Integer, FrequencyNode> byFreq = new HashMap<>();

    static void main() {

    }



    private class Node {
        int key;
        int value;
        int frequency = 1;
        Node prev, next;

    }

    private class FrequencyNode {
        int frequency;

        FrequencyNode prev, next;
        Node itemsHead, itemsTail;
    }
}
