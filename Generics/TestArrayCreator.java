package Generics;

public class TestArrayCreator {
    public static void main(String[] args) {
        String[] stringArray = ArrayCreator.create(2, "Hello");
        for (String str : stringArray) {
            System.out.println(str);
            System.out.println(str.getClass());
        }

        System.out.println("-".repeat(10));

        Integer[] intArray = ArrayCreator.create(Integer.class, 2, 42);
        for (Integer num : intArray) {
            System.out.println(num);
            System.out.println(num.getClass());
        }
    }
}
