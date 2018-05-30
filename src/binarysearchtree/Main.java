package binarysearchtree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BinarySearchTree tree = new BinarySearchTree();
        while (scan.hasNext()) {
            String command = scan.next();
            if (command.equals("I")) {
                String data = scan.next();
                tree.insert(data);
            } else if (command.equals("D")) {
                String data = scan.next();
                tree.delete(data);
            } else if (command.equals("P")) {
                tree.print();
            }
        }
        scan.close();
    }
}
