package pl.mg.javatsts.leetcode;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * You are given equations in the format A / B = k, where A and B are variables represented as strings, and k is
 * a real number (floating-point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 * <p>
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there
 * is no contradiction.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 */
@Data
public class EvaluateDivision27 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashSet<String> set = new HashSet<>();
        for (List<String> equation : equations) {
            set.add(equation.get(0));
            set.add(equation.get(1));
        }
        UnionFind uf = new UnionFind(set);
        for (int i = 0; i < equations.size(); i++) {
            uf.union(equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }

        double[] rst = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String aFather = uf.compressedFind(queries.get(i).get(0));
            String bFather = uf.compressedFind(queries.get(i).get(1));
            if (aFather.equals("#") || bFather.equals("#") || !aFather.equals(bFather)) {
                rst[i] = -1.0;
            } else {
                rst[i] = uf.rank.get(queries.get(i).get(0)) / uf.rank.get(queries.get(i).get(1));
            }
        }
        return rst;
    }

    static class UnionFind {
        HashMap<String, String> parent;
        HashMap<String, Double> rank;

        UnionFind(HashSet<String> set) {
            this.parent = new HashMap<>();
            this.rank = new HashMap<>();

            for (String str : set) {
                parent.put(str, str);
                rank.put(str, 1.0);
            }
        }

        public String compressedFind(String value) {
            // Can't find the value
            if (!parent.containsKey(value)) {
                return "#";
            }
            if (!value.equals(parent.get(value))) {
                String pre = parent.get(value);
                parent.put(value, compressedFind(parent.get(value)));
                rank.put(value, rank.get(pre) * rank.get(value));
            }
            return parent.get(value);
        }

        public void union(String a, String b, double quotient) {
            String aFather = compressedFind(a);
            String bFather = compressedFind(b);
            if (aFather.equals(bFather)) {
                return;
            }
            parent.put(aFather, bFather);
            rank.put(aFather, quotient * rank.get(b) / rank.get(a));
        }
    }

}
