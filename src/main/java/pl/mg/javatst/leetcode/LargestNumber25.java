package pl.mg.javatst.leetcode;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Data
@Slf4j
public class LargestNumber25 {


    public static void main(String[] args) {
        int[] input = {3, 30, 34, 5, 9};
    }

    public String largestNumber(int[] list) {
        // Check if the input is correct
        if (list == null || list.length == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        Container containers[] = new Container[list.length];

        int index = 0;

        // Add each element in a Container type
        for (int n : list) {
            containers[index] = new Container(n);
            index++;
        }

        // Sort the array
        Arrays.sort(containers);

        // If the first element is 0, it means we have to simply return 0
        if ("0".equals(containers[0].number)) {
            return "0";
        }

        for (Container n : containers) {
            sb.append(n.number);
        }

        return sb.toString();
    }

    class Container implements Comparable<Container> {
        String number;

        public Container(int number) {
            this.number = String.valueOf(number);
        }

        @Override
        public int compareTo(Container c) {
            String first = this.number + c.number;
            String second = c.number + this.number;

            // Comparison strategy described
            return second.compareTo(first);
        }
    }
}
