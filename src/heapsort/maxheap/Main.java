package heapsort.maxheap;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = { 69, 10, 30, 2, 16, 8, 31, 22 };
        Heap heap = new Heap();
        for (int i = 0; i < arr.length; i++) {
            heap.insert(arr[i]);
        }
        System.out.println(heap.sort());

    }
}
