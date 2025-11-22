package pl.mg.javatst.leetcode;

import org.apache.commons.collections4.list.TreeList;

import java.util.*;

/**
 * https://leetcode.com/problems/reconstruct-itinerary/
 */
public class ReconstructItinerary {

    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> results = new ArrayList<>();
        String destination = "JFK";

        Map<String, List<String>> destinations = new HashMap<>();

        for (List<String> ticket : tickets) {
            String arrivalAirport = ticket.get(0);
            String destinationAirport = ticket.get(1);
            if (destinations.containsKey(arrivalAirport)) {
                destinations.get(arrivalAirport).add(destinationAirport);
                Collections.sort(destinations.get(arrivalAirport));
            } else {
                List<String> dest = new ArrayList<>();
                dest.add(destinationAirport);
                Collections.sort(dest);
                destinations.put(arrivalAirport, dest);
            }
        }

        for (int i = 0; i < tickets.size(); i++) {
            results.add(destinations.get(destination).get(0));
            destination = destinations.get(destination).get(0);
            destinations.get(destination).remove(0);
        }

        return results;

    }

}
