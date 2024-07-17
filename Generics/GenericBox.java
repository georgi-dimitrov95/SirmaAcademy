package Generics;

public class GenericBox<T> {
    private T item;

    public GenericBox() {
        this.item = null;
    }

    public GenericBox(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return item.getClass() + ": " + item;
    }
}
