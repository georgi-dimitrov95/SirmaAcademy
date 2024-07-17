package Generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GenericSwap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int line = Integer.parseInt(sc.nextLine().trim());
            list.add(line);
        }

        list = swap(list, sc.nextInt(), sc.nextInt());

        for (Integer num : list) {
            System.out.println(num.getClass() + ": " + num);
        }
    }

    public static <E> ArrayList<E> swap(ArrayList<E> list, int first, int second) {
        Collections.swap(list, first, second);
        return list;
    }
}
