package Generics;

import java.util.Scanner;

public class TestGenericBox {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < n; i++) {
            Integer num = Integer.parseInt(sc.nextLine().trim());
            GenericBox<Integer> box = new GenericBox<>(num);
            System.out.println(box.toString());
        }
    }
}
