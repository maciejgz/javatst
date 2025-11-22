package pl.mg.javatst.cert.ocp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.TreeSet;

@AllArgsConstructor
@Data
public class SortedTreeSetTests implements Comparator<SortedTreeSetTests>, Comparable<SortedTreeSetTests> {

    private int num;
    private String text;

    public static void main(String[] args) {
        TreeSet<SortedTreeSetTests> settt = new TreeSet<>();
        settt.add(new SortedTreeSetTests(20, "abc"));
        settt.add(new SortedTreeSetTests(15, "dfe"));
        System.out.println(settt);
    }

    @Override
    public int compare(SortedTreeSetTests o1, SortedTreeSetTests o2) {
        return o1.num - o2.num;
    }

    @Override
    public int compareTo(SortedTreeSetTests o) {
        return text.compareTo(o.getText());
    }
}
