package main.solution.sorting;

import java.util.Scanner;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class ComparatorSolution {

    static class Player {
        private int score;
        private String name;

        Player(int score, String name) {
            this.score = score;
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public String getName() {
            return name;
        }
    }

    static class Checker implements Comparator<Player> {

        @Override
        public int compare(Player p1, Player p2) {
            if (p1.getScore() > p2.getScore()) {
                return 1;
            } else if (p1.getScore() < p2.getScore()) {
                return -1;
            } else if (p1.getScore() == p2.getScore()) {
                return p2.getName().compareTo(p1.getName());
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        int numberOfPlayers = Integer.parseInt(myObj.nextLine());  // Read user input
        List<Player> players = new ArrayList<>();
        for (int index = 0; index < numberOfPlayers; index++) {
            Scanner info = new Scanner(System.in);
            String[] values = info.nextLine().split(" ");
            players.add(new Player(Integer.parseInt(values[1]), values[0]));
        }
        players.sort(new Checker());
        for (int index = players.size() - 1; index >= 0; index--) {
            Player player = players.get(index);
            System.out.println(player.getName() + " " + player.getScore());
        }
    }
}