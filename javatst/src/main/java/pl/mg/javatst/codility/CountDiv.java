package pl.mg.javatst.codility;

/**
 * https://app.codility.com/programmers/lessons/4-counting_elements/
 */
public class CountDiv {

    public static void main(String[] args) {

    }

    public int solution(int A, int B, int K) {
        int offsetForLeftRange = 0;
        if ( A % K == 0) { ++offsetForLeftRange; }

        return  (B/K) - (A /K) + offsetForLeftRange;
    }
}
