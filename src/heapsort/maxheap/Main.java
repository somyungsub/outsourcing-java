package heapsort.maxheap;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Integer[] arr = { 69, 10, 30, 2, 16, 8, 31, 22 };
//        for (int i = 0; i < arr.length; i++) {
//            heap.insert(arr[i]);
//        }
        Heap heap = new Heap();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String command = scan.next();
            if (command.equals("I")) {
                String data = scan.next();
                heap.insert(Integer.parseInt(data));
            } else if (command.equals("D")) {
                heap.delete();
            } else if (command.equals("P")) {
                System.out.println(heap.sort());
            }
        }
    }
}
