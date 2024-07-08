import java.util.ArrayDeque;

public class Jar<E> {
    private ArrayDeque<E> jar;

    public Jar() {
        this.jar = new ArrayDeque<E>();
    }

    public ArrayDeque<E> getJar() {
        return jar;
    }

    public void add(E element) {
        this.jar.push(element);
    }

    public E remove() {
        return this.jar.pop();
    }
}
