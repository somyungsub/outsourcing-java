package hangman;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    private static Random rand = new Random();
    private static Vector<String> words = new Vector<>();   // 저장된 words 객체에서 랜덤으로 1개 빼내기

    public static void main(String[] args) throws FileNotFoundException {
        wordRead(); // word 초기화 및 저장
        gameExec(); // 게임 실행
    }

    /*
        words.txt에서 word를 읽어 words 객체(벡터)에 저장
     */
    private static void wordRead() throws FileNotFoundException {
        // word파일 읽기
        Scanner scanner = new Scanner(new FileReader("src\\hangman\\words.txt"));

        // words(벡터객체)에 word저장
        while (scanner.hasNext()) {             // 파일 끝까지 반복하여 읽는다.
            String word = scanner.nextLine();   // 한 라인단위로 읽는다. word에 \n은 없음
            words.add(word);                    // 단어를 벡터에 저장한다.
        }
    }

    /*
        퀴즈게임 실행
        1. 원래단어와 퀴즈에 쓸 단어를 생성
        2. 퀴즈단어는 원래단어에서 "-"가 2개 바뀐 단언
        3. 퀴즈단어의 "-"위치의 값은 원래단어의 값을 활용하여 대답한 값과 비교.
        4. 비교를 통해 맞으면 "-"를 대답한 값으로 변경
        5. "-" 가 다 변경되면 : 정답 / 5번 틀린 경우 실패
     */
    private static void gameExec() {
        System.out.println("지금부터 게임을 시작합니다.");
        Scanner sc = new Scanner(System.in);
        int failCount = 5;

        // 게임실행
        while (true) {
            String word = words.get(rand.nextInt(words.size()));    // 원래 word
            String quiz = makeWordQuiz(word);                       // "-"가 2개 교체된 문제를위한 word
            StringBuilder sb = new StringBuilder(quiz);

            // 5번의 대답 기회
            for (int i = failCount; i > 0 ; i--) {

                System.out.println(sb.toString());  // 문제 출력
                System.out.print(">>");
                String answer = sc.nextLine();      // 입력 값 받기

                // quiz단어의 "-" 부분과 대답부분 확인
                for (int j = 0; j < quiz.length(); j++) {
                    char ch = quiz.charAt(j);
                    if (ch == '-') {
                        char successCh = word.charAt(j);        // 원래 단어의 위치 값 확인
                        if (successCh == answer.charAt(0)) {    // 대답한 값과 원래단어의 값이 일치하는지 확인
                            sb.setCharAt(j, successCh);         // 맞을 경우 변경
                        }
                    }
                }

                // "-" 가 없는 경우 전부 맞췄다고 판단
                if (!sb.toString().contains("-")) {
                    System.out.println(sb.toString());
                    break;
                }

                // 마지막까지 틀린 경우 (5번 실패)
                if (i == 1 ) {
                    System.out.println("5번 실패 하였습니다.");
                    System.out.println(word);
                    break;
                }
            }

            // 다음 단어를 진행할건지 판단
            String answer;
            System.out.print("Next(y/n)?");
            while (!"y,n".contains((answer = sc.nextLine().toLowerCase()))) {
                System.out.println("다시 입력해주세요 잘못 입력하셨습니다. Next(y/n)");
            }
            if ("n".equals(answer)) {
                break;
            }
        }
    }

    /*
       저장된 word 중 랜덤으로 선정하여 문제만들기
    */
//    private static String makeWordQuiz(String word) {
//        // 문제 생성 : "-" 2개 만들기
//        int count = 0;  // 2개확인을 위한 flag
//        StringBuilder sb = new StringBuilder(word);
//        while (count < 2) {
//            int idx = rand.nextInt(word.length());   // "-"로 변경할 랜덤위치의 인덱스 값
//            char ch = sb.toString().charAt(idx);
//            if (ch != '-') {
//                sb.setCharAt(idx, '-');         // "-"로 변경하기
//                count++;
//            }
//        }
//        return sb.toString();
//    }
    private static String makeWordQuiz(String word) {
        // 문제 생성 : "-" 2개이상, 동일한 경우 3개이상
        String changeWord = word;
        while (true) {
            int idx = rand.nextInt(word.length());   // "-"로 변경할 랜덤위치의 인덱스 값
            char ch = word.charAt(idx);
            changeWord = changeWord.replace(ch, '-');

            int count = 0;  // 2개확인을 위한 flag
            for (int i = 0; i < changeWord.length(); i++) {
                if (changeWord.charAt(i) == '-') {
                    count++;
                }
            }
            if (count >= 2) {
                break;
            }
        }
        return changeWord;
    }
}
