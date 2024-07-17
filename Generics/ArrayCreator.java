package Generics;

import java.lang.reflect.Array;

public class ArrayCreator {

    public static <E> E[] create(int length, E item) {
        E[] array = (E[]) Array.newInstance(item.getClass(), length);
        for (int i = 0; i < length; i++) {
            array[i] = item;
        }
        return array;
    }

    public static <E> E[] create(Class<E> clazz, int length, E item) {
        E[] array = (E[]) Array.newInstance(clazz, length);
        for (int i = 0; i < length; i++) {
            array[i] = item;
        }
        return array;
    }
}
