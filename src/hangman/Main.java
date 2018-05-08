package hangman;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Vector<String> words = new Vector<>();
        try {
            // word파일 읽기
            Scanner scanner = new Scanner(new FileReader("src\\hangman\\words.txt"));

            // words(벡터객체)에 word저장
            while (scanner.hasNext()) { // 파일 끝까지 반복하여 읽는다.
                String word = scanner.nextLine();   // 한 라인단위로 읽는다. word에 \n은 없음
                words.add(word);    // 단어를 벡터에 저장한다.
            }

            // 저장된 words 객체에서 랜덤으로 1개 빼내기
            Random rand = new Random();
            String word = words.get(rand.nextInt(words.size()));
            int count = 0;
            while (count < 2) {

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
