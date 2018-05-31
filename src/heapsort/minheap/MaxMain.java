package heapsort.minheap;

import java.util.PriorityQueue;

public class MaxMain {

    public static void main(String[] args) throws Exception {
//        Integer[] array = { 30, 50, 7, 40, 88, 15, 44, 55, 22, 33, 77, 99, 11, 66, 1, 85 };
//        array = heapSort(array);
//        System.out.println(Arrays.toString(array));

        int[] array = { 30, 50, 7,40, 88, 15, 44, 55, 22, 33, 77, 99, 11, 66, 1, 85 };
        MaxMain heapSort= new MaxMain();
        heapSort.sort(array);
        System.out.println(java.util.Arrays.toString(array));
    }

    public static <E extends Comparable<? super E>> E[] heapSort(E[] array) {
        PriorityQueue<E> heap = new PriorityQueue<E>(array.length);
        // heap add
        for (E e : array) {
            heap.add(e);
        }

        // heap remove
        for (int i = 0; i < array.length; i++) {
            array[i] = heap.remove();
        }
        return array;
    }

    public  static void reheap (int a[], int length, int i) throws Exception {
        boolean done = false;
        int T = a[i];
        int parent = i;
        int child = 2*(i+1)-1;
        while ((child < length) && (!done)) {
            if (child < length - 1)
                if (a[child] < a[child + 1])  child += 1;
            if (T >= a[child]) done = true;
            else {
                a[parent] = a[child];
                parent = child;
                child = 2*(parent+1)-1;
            }
        }
        a[parent] = T;
    }

    public static void sort(int a[]) throws Exception {
        // Make theinput into a heap
        for (int i = a.length-1; i >= 0; i--)
            reheap (a, a.length, i);
        // Sort theheap
        for (int i = a.length-1; i > 0; i--) {
            int T = a[i];
            a[i] = a[0];
            a[0] = T;
            reheap (a, i, 0);
        }
    }
}
