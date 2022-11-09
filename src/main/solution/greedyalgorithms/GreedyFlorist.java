package main.solution.greedyalgorithms;

import java.util.Arrays;

public class GreedyFlorist {
    /*
    * The problem description is not good enough. The florist has 'n' flowers to sell
    * with the prices listed in 'c' array. There are 'k' friends who want to buy the
    * flowers. They want to buy all the flowers of the 'c' array. The challenge is to
    * try to minimize the total purchase cost of the flowers for the friends as the
    * greedy florist has imposed the multiplier rule on his customers. Frankly, I don't
    * see why you'd go to this florist.
    *
    * n -> number of flowers in c array
    * c -> prices of the flowers
    * k -> number of friends
    *
    * Suppose we have,
    * n = 3
    * c = [1,4,5]
    * k = 2
    *
    * Each friend must buy the most expensive flower first as they don't want the
    * higher multiplier applied in them.
    * So, friend 1 buys 5. -> total cost = (0+1)*5 = 5
    *  friend 2 buys 4. -> total cost = (0+1)*4 = 4
    * Now, one of them has to buy the remaining flower, total cost += (1+1)*1 = 11
    *
    * */
    static int getMinimumCost(int k, int[] c) {
        if (k >= c.length) {
            return sumFlowersPriceAccordingToFloristRule(0, c);
        }

        int totalPrice = 0;
        int x = c.length / k;
        int y = c.length % k;

        int maxRange = c.length;
        int minRange = c.length - k;
        Arrays.sort(c);
        for(int index = 0; index < x; index++) {
            int [] subArray = Arrays.copyOfRange(c, minRange, maxRange);
            totalPrice += sumFlowersPriceAccordingToFloristRule(index, subArray);
            maxRange = minRange;
            minRange = maxRange - k;
        }

        int[] subArray = Arrays.copyOfRange(c,0, y);
        totalPrice += sumFlowersPriceAccordingToFloristRule(x, subArray);

        return totalPrice;
    }

    private static int sumFlowersPriceAccordingToFloristRule(int multiplier,
                                                             int[] priceList) {
        int totalPrice = 0;
        for (int price: priceList) {
            totalPrice += price * (multiplier + 1);
        }
        return totalPrice;
    }
}
