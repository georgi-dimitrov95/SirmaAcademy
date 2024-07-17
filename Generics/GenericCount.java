package Generics;

import java.util.ArrayList;
import java.util.Scanner;

public class GenericCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        ArrayList<Double> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Double line = Double.parseDouble(sc.nextLine().trim());
            list.add(line);
        }

        System.out.println(count(list, Double.parseDouble(sc.nextLine().trim())));
    }

    public static <E extends Comparable> int count(ArrayList<E> list, E comparisonElement) {
        int greaterCount = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).compareTo(comparisonElement) > 0) {
                greaterCount++;
            }
        }
        return greaterCount;
    }
}
