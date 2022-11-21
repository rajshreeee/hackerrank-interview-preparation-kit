package main.solution.search;

import java.util.*;

public class IceCreamParlor {
    public static void whatFlavors(List<Integer> costs, int money) {
        // storing initial indices of cost in map as the original indices will be lost when sorting it
        HashMap<Integer, List<Integer>> costIndices = new HashMap<>();
        for (int index = 0; index < costs.size(); index++) {
            Integer cost = costs.get(index);
            List<Integer> indices = costIndices.containsKey(cost)
                    ? costIndices.get(cost)
                    : new ArrayList<>();
            indices.add(index);
            costIndices.put(cost, indices);
        }
        // sorting to apply binary search
        Collections.sort(costs);
        for (int index = 0; index < costs.size(); index++) {
            int currentFlavorCost = costs.get(index);
            int complementaryFlavorCost = money - currentFlavorCost;
            if (complementaryFlavorCost <= 0) {
                continue;
            }
            int indexOfComplementaryFlavorCost = Collections.binarySearch(costs, complementaryFlavorCost);
            if (indexOfComplementaryFlavorCost <= -1) {
                continue;
            }

            // Finding the actual indices from hashmap
            List<Integer> currentFlavorCostIndices = costIndices.get(currentFlavorCost);
            int currentIndex = currentFlavorCostIndices.get(0) + 1; //add 1 as results are to be displayed in 1 based index
            currentFlavorCostIndices.remove(0);

            List<Integer> complementaryFlavorCostIndices = costIndices.get(complementaryFlavorCost);
            int complementaryIndex = complementaryFlavorCostIndices.get(0) + 1;

            int smallerIndex = complementaryIndex < currentIndex ? complementaryIndex : currentIndex;
            int largerIndex = complementaryIndex > currentIndex ? complementaryIndex : currentIndex;

            System.out.println(smallerIndex+ " " + largerIndex);
            break;
        }
    }
}
