package heapsort.minheap;

public class Heap {
    private int heapSize;
    private int itemHeap[];

    public Heap() {
        heapSize = 0;
        itemHeap = new int[50];
    }

    public void insertHeap(int item) {
        int i = ++heapSize;

        while ((i != 1) && (item > itemHeap[i / 2])) {
            itemHeap[i] = itemHeap[i / 2];
            i /= 2;
        }

        itemHeap[i] = item;
    }

    public int getHeapSize() {
        return this.heapSize;
    }

    public int deleteHeap() {
        int parent, child;
        int item, tmp;
        item = itemHeap[1];
        tmp = itemHeap[heapSize--];
        parent = 1;
        child = 2;

        while (child <= heapSize) {
            if ((child < heapSize) && (itemHeap[child] < itemHeap[child + 1]))
                child++;

            if (tmp >= itemHeap[child])
                break;

            itemHeap[parent] = itemHeap[child];
            parent = child;
            child *= 2;
        }

        itemHeap[parent] = tmp;
        return item;
    }
}
