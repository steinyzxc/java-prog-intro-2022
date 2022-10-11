import java.util.*;


class IntList {
    private int size = 0;
    private int[] elements;

    public IntList() {
        elements = new int[0];
    }
    public IntList(int[] a) {
        elements = a.clone();
        size = elements.length;
    }

    public int getInt(int ind) {
        if (ind >= size || ind < 0) {
            throw new IndexOutOfBoundsException("Index " + ind + " out of bounds for length " + size);
        } else {
            return elements[ind];
        }
    }

    public void add(int a) {
        if (elements.length > size) {
            elements[size] = a;
        } else {
            elements = Arrays.copyOf(elements, size * 2 + 1);
            elements[size] = a;
        }
        size++;
    }

    public int getSize() {
        return size;
    }
}