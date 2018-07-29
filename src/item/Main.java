package item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int num3 = scanner.nextInt();

        // n 초기화
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= num1; i++) {
            list.add(i);
        }
        // m 초기화
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < num2; i++) {
            list2.add(random.nextInt(num1) + 1);
        }
        System.out.println(list2);
        Map<Integer,Integer> map = new HashMap();
        for (Integer i : list2) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        System.out.println(map);

    }
}
