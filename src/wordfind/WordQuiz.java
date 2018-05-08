package wordfind;

import java.util.*;

public class WordQuiz {
    public static void main(String[] args) {
        Vector<Word> words = initObject();  //  초기화
        execGame(words);                    //  영단어 찾기 게임실행
    }

    /*
        Word 초기화
        문제에 따라 단어 17개 선정
     */
    private static Vector<Word> initObject() {
        Vector<Word> words = new Vector<>();
        words.add(new Word("painting", "그림"));
        words.add(new Word("bear", "곰"));
        words.add(new Word("baby", "아기"));
        words.add(new Word("error", "오류"));
        words.add(new Word("emotion", "감정"));
        words.add(new Word("doll", "인형"));
        words.add(new Word("view", "보기"));
        words.add(new Word("eye", "눈"));
        words.add(new Word("trading", "거래"));
        words.add(new Word("picture", "사진"));
        words.add(new Word("human", "인간"));
        words.add(new Word("society", "사회"));
        words.add(new Word("statue", "조각상"));
        words.add(new Word("computer", "컴퓨터"));
        words.add(new Word("java", "자바"));
        words.add(new Word("luxury", "명품"));
        words.add(new Word("korea", "한국"));

        return words;

    }

    /*
        영어단어 찾기 게임을 실행한다.
        1. 매개변수로 받은 17개의 영단어에서 4개를 선정한다.
            : Random 클래스를 활용하여 랜덤하게 선정.
        2. 4개 중 1개는 정답, 3개는 오답이다.
            : Map 자료구조를 활용한다.
        3. 각 단어는 중복이 되어서는 안된다.
            : Map의 키 값이 중복 되면 오버라이딩 되는 점을 활용하여 size가 4개인 경우를 체크한다.
        4. 문제의 영단어의 한국어 뜻과 입력된 한국어뜻의 일치여부를 통해 정답유무를 체크한다.
     */
    private static void execGame(Vector<Word> words) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        // 게임 시작  (-1 입력시 반복문 종료)
        while (true) {

            Map<Integer, Word> map = new HashMap<>();       // 정답, 오답 Word를 담을 자료구조
            int rightIdx = random.nextInt(17);     // 정답 Word 인덱스
            // 오답 Word가 3개 일때 까지 반복
            while (map.size() < 3) {
                int wrongIdx = random.nextInt(17); // 오답 Word 인덱스

                // 정답인덱스와 같지 않은 경우만 Map에 저장한다.
                if (rightIdx != wrongIdx) {
                    map.put(wrongIdx, words.get(wrongIdx));
                }
            }

            // 마지막 정답 Word 삽입
            map.put(rightIdx, words.get(rightIdx));

            // 영단어 4개를 리스트에 저장 -> 문제 리스트 랜덤하게 출력시 활용
            List<Word> list = new ArrayList<>();
            for (Map.Entry<Integer, Word> entry : map.entrySet()) {
                list.add(entry.getValue());
            }

            // 문제 실행
            StringBuilder sb = new StringBuilder();
            List<Word> saveList = new ArrayList<>();                    // list가 삭제 되면서 4개의 영단어를 재 저장하기 위한 리스트
            int count = 1;
            // 리스트 사이즈가 0이 될때까지 반복문 실행
            while (!list.isEmpty()) {
                int listIdx = random.nextInt(list.size());  // 리스트의 사이즈 만큼 랜던 출력
                Word word = list.get(listIdx);

                // 문제리스트 저장
                sb.append("(").append(count++).append(")").append(word.getKorName()).append(" ");
                saveList.add(word);     // 랜덤 위치의 리스트 재 저장
                list.remove(listIdx);   // 문제 리스트 저장 후 삭제
            }
            sb.append(" :>");

            // 문제 출력
            while (true) {
                System.out.println(map.get(rightIdx).getEngName() + "?");   // 영어단어
                System.out.print(sb.toString());
                int answer = sc.nextInt();

                // "-1" : 실행종료
                if (answer == -1) {
                    System.out.println("\"명품영어\"를 종료합니다...");
                    return;
                }

                // 5 이상의 0이하의 숫자를 입력 할 경우 (오입력 경우)
                if (answer >= 5 || answer <= 0) {
                    System.out.println("잘못 입력 하였습니다. 다시 입력 해주세요..");
                    continue;
                }

                // 정답
                if (saveList.get(answer - 1).getKorName().equals(map.get(rightIdx).getKorName())) {
                    System.out.println("Excellent !!");
                }
                // 오답
                else {
                    System.out.println("No. !!");
                }

                // 1~4입력 된 경우 (정상 입력 경우) 다음문제 실행
                if (0 < answer && answer < 5) {
                    break;
                }

            }
        }
    }
}
