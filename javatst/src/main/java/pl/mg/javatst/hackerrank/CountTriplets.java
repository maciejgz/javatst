package pl.mg.javatst.hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountTriplets {

    public static void main(String[] args) {
        List<Long> ls = new ArrayList<>();
        ls.add(1L);
        ls.add(2L);
        ls.add(2L);
        ls.add(4L);
        System.out.println(countTriplets(ls, 2));
    }

    public static long powerN(long number, long power) {
        if (power == 0) return 1;
        long result = number;
        while (power > 1) {
            result *= number;
            power--;
        }
        return result;
    }

    static long countTriplets(List<Long> arr, long r) {
        long cnt = 0;
        Map<Long, Long> map = new HashMap<>();
        Map<Long, Long> rMap = new HashMap<>();
        for (long n : arr) {
            if (n % r == 0) {
                long pre = n / r;
                Long cnt2 = rMap.get(pre);
                if (cnt2 != null) cnt += cnt2;

                Long cnt1 = map.get(pre);
                if (cnt1 != null) rMap.put(n, rMap.getOrDefault(n, 0L) + cnt1);
            }
            map.put(n, map.getOrDefault(n, 0L) + 1);
        }
        return cnt;
    }


    static long countTripletsOld(List<Long> arr, long r) {
        long result = 0L;
        for (int i = 0; i < arr.size() - 2; i++) {
            for (int j = i + 1; j < arr.size() - 1; j++) {
                if (arr.get(j) == arr.get(i) * r) {
                    for (int k = j + 1; k < arr.size(); k++) {
                        if (arr.get(k) == arr.get(j) * r) {
                            result++;
                        }
                    }
                }
            }
        }
        return result;
    }
}
