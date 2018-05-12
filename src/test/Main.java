package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("src\\test\\test.txt");
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
        String str = "";
        String save = "";
        Map<Integer, String> map = new HashMap<>();
        int count = 0;
        while ((str = br.readLine()) != null) {
            if (str.equals(";")) {
                map.put(count++, save);
                save = "";
            }
            save += str;
        }
        map.put(count, save);
        for (Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getValue().length());
        }
    }
}
