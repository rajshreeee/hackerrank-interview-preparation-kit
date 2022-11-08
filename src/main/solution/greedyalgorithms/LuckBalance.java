package main.solution.greedyalgorithms;

import java.util.*;
import java.util.stream.Collectors;

public class LuckBalance {
    public static int luckBalance(int k, List<List<Integer>> contests) {
        int luckBalance = 0;

        // Add luck of unimportant contests
        List<List<Integer>> unimportantContests = contests.stream()
                .filter(contest -> contest.get(1).equals(0)).toList();
        Optional<Integer> totalLuckOfUnimportantContests = unimportantContests
                .stream()
                .map(contest -> contest.get(0))
                .reduce(Integer::sum);
        if (totalLuckOfUnimportantContests.isPresent()) {
            luckBalance += totalLuckOfUnimportantContests.get();
        }

        List<List<Integer>> importantContests = contests.stream()
                .filter(contest -> contest.get(1).equals(1)).toList();
        int numberOfContestsToWin = importantContests.size() - k;

        if (numberOfContestsToWin > 0) {
            List<Integer> luckListOfImportantContests = importantContests.stream()
                    .map(contest -> contest.get(0)).sorted().toList();
            List<Integer> minimalLuckList = luckListOfImportantContests.subList(0, numberOfContestsToWin);

            for (Integer integer : minimalLuckList) {
                luckBalance -= integer;
            }

            luckListOfImportantContests = luckListOfImportantContests
                    .subList(numberOfContestsToWin, luckListOfImportantContests.size());
            for (Integer luckListOfImportantContest : luckListOfImportantContests) {
                luckBalance += luckListOfImportantContest;
            }
        } else {
            Optional<Integer> totalLuckOfImportantContests = importantContests
                    .stream()
                    .map(contest -> contest.get(0))
                    .reduce(Integer::sum);
            if (totalLuckOfImportantContests.isPresent()) {
                luckBalance += totalLuckOfImportantContests.get();
            }
        }


        return luckBalance;
    }
}
