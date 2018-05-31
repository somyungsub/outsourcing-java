package heapsort.maxheap;

import java.util.ArrayList;
import java.util.List;

public class Heap {
//    private int heapSize;
    private List<Integer> itemHeap;

    public Heap() {
        itemHeap = new ArrayList<>();
//        heapSize = 0;
    }

    public void reheapUp(Heap self, int idx){
        if (idx > -1) {
            int parent = (idx - 1) / 2;
            if (self.getItemHeap().get(idx) > self.getItemHeap().get(parent)) {
                int temp = self.getItemHeap().get(idx);
                self.getItemHeap().set(idx, self.getItemHeap().get(parent));
                self.getItemHeap().set(parent, temp);
                reheapUp(self, parent);
            }
        }
    }
    public void reheapDown(Heap self, int idx){
        int left = 0;
        int right = 0;
        int large;
        if (idx * 2 + 1 < self.getHeapSize()) {
            left = self.getItemHeap().get(idx * 2 + 1);
            if (idx * 2 + 2 < self.getHeapSize() - 1) {
                right = self.getItemHeap().get(idx * 2 + 2);
            }
            if (left > right) {
                large = idx * 2 + 1;
            } else {
                large = idx * 2 + 2;
            }
            if (self.getItemHeap().get(idx) < self.getItemHeap().get(large)) {
                int temp = self.getItemHeap().get(idx);
                self.getItemHeap().set(idx, self.getItemHeap().get(large));
                self.getItemHeap().set(large, temp);
                reheapDown(self, large);
            }
        }
    }

    public void insert(int num) {
        getItemHeap().add(num);
        reheapUp(this, getHeapSize());
    }

    public int delete(){
        if (getItemHeap().size() == 0) {
            return -1;
        }
        int del = getItemHeap().get(0);
        int lastNum = getItemHeap().remove(getHeapSize());
//        heapSize--;
        getItemHeap().set(0, lastNum);
        reheapDown(this, 0);
        return del;
    }

    public List<Integer> sort() {
        List<Integer> sort = new ArrayList<>();
        int size = getHeapSize();
        for (int i = 0; i < size; i++) {
            sort().add(delete());
        }
        return sort;
    }

    public List<Integer> getItemHeap(){
        return this.itemHeap;
    }

    public int getHeapSize() {
        return this.itemHeap.size();
//        return heapSize;
    }
}
