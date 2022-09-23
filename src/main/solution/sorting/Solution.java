package main.solution.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {

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

    static class Checker implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            Player p1 = (Player) o1;
            Player p2 = (Player) o2;

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
      Player p1 = new Player(10, "Asad");
      Player p2 = new Player(20, "Birat");
      Player p3 = new Player(30, "Sujan");
      Player p4 = new Player(30, "Zayn");
      List<Player> players = Arrays.asList(p3, p2, p4, p1);
      players.sort(new Checker());
    }
}