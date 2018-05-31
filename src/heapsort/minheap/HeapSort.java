package heapsort.minheap;

public class HeapSort {
    public static void heapSort(int[] arr) {
        Heap heap = new Heap();

        for (int i = 0; i < arr.length; i++) {
            heap.insertHeap(arr[i]);
        }

        for (int i = arr.length - 1; i >= 0; --i) {
            arr[i] = heap.deleteHeap();

        }
        System.out.println("힙 정렬 :");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb.toString());
//        SelectionSort.printArr(arr);
    }


}
