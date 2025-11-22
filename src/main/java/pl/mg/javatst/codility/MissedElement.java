package pl.mg.javatst.codility;

/**
 * done
 *
 */
public class MissedElement {

    public static void main(String[] args){
        MissedElement missed = new MissedElement();
    }

    public int solution(int[] A) {
        int value = ((1 + A.length+1) / 2) * (A.length+1);
        for (int i : A) {
            value = value - i;
        }
        return value;
    }
}
