package pl.mg.javatst.codewars;

import java.util.*;
import java.util.stream.Collectors;

public class TopWords {

    public static void main(String[] args) {
        top3("  //wont won't won't ");
    }

    public static List<String> top3(String s) {
        String[] words = s.replaceAll("[^a-zA-Z0-9']", " ").replaceAll("\n", " ").replaceAll("\r\n|\n", " ").split(" ");
        HashMap<String, Integer> counters = new HashMap<>();
        List<String> result = Arrays.stream(words).filter(word -> !word.isBlank() && !word.matches("(')+")).collect(Collectors.toList());
        System.out.println(result.size());
        for (String wrd : result) {
            if (counters.containsKey(wrd.toLowerCase())) {
                counters.put(wrd.toLowerCase(), counters.get(wrd.toLowerCase()) + 1);
            } else {
                counters.put(wrd.toLowerCase(), 1);
            }
        }

        HashMap<String, Integer> sortedByValue = sortByValue(counters);

        List<String> wordddd = new ArrayList<>();
        for (String s1 : sortedByValue.keySet()) {
            wordddd.add(s1);
            if (wordddd.size() >= 3) {
                break;
            }
        }

        return wordddd;
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());

        }
        return temp;
    }
}
