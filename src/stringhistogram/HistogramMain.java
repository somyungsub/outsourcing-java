package stringhistogram;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HistogramMain {
    public static void main(String[] args) {

        System.out.println("영문 텍스트를 입력하고 세미콜론을 입력하세요.");
        String line = readString(); // 입력된 스트링을 저장

        System.out.println("히스토그램을 그립니다.");
        showHistogram(line);        // 히스토그램 처리 (알파벳이 아닌 문자는 제외)
        System.out.println("Test!!");

        System.out.println("Test2!!!");

    }

    /*
        문자를 읽는 행위를 실행하는 메서드 입니다.
        - 영문자와 소문자는 같은 것으로 간주하고, 세미콜론(;)만 있는 라인을 만나면 입력의 끝으로 해석
     */
    private static String readString() {
        StringBuffer sb = new StringBuffer();       // 키 입력을 저장 할 스트링버퍼 생성

        Scanner scanner = new Scanner(System.in);   // 텍스트 입력스트림을 형성하고 키 입력을 받습니다

        // ';'와 엔터키를 입력할 때 까지 입력을 받습니다
        while (true) {
            String line = scanner.nextLine();   // 텍스트 한 라인을 읽는다.

            // ';'만 있는 라인이면 -> 종료
            if (line.length() == 1 && line.charAt(0) == ';') {
                break; // 입력 끝 -> 종료
            }

            sb.append(line);    // 읽은 라인 문자열을 스트링버퍼에 추가한다.
        }

        /*
            여기서부터 테스트 용 입니다.
            계속 타자를 치기 힘들어서 File로 Input을 읽어 들인 후 확인을 했습니다.

            File 부터 catch 블록 까지 삭제 후 위에 주석으로 막아논 부분을
            주석 삭제하시고 정상실행 되는지 확인 하시면 됩니다.
         */
       /* File file = new File("src\\stringhistogram\\input");
        try {
            InputStream is = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String str = null;
            while ((str = br.readLine()) != null) {
                sb.append(str).append(System.lineSeparator());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return sb.toString();   // 스트링버퍼의 문자열을 스트링으로 리턴
    }

    /*
        알파벳 Histogram을 보여줍니다.
     */
    private static void showHistogram(String line) {
        // 입력된 문자열이 없는 경우 예외처리
        if (line == null || line.isEmpty()) {
            System.out.println("입력된 문자열이 없습니다.");
            return;
        }

        // 히스토그램 정보를 얻는다.
        Map<String, Integer> map = getHistogram(line);

        // 저장된 key(알파벳)과 value(알파벳 등장 횟수)에 따라 '-'를 출력한다.
        for (String key : map.keySet()) {
            // 키값 (A,B ... Z) 저장 및 '-'저장을 위한 스트링버퍼
            StringBuffer sb = new StringBuffer(key);
            // 저장된 횟수 만큼 '-'를 저장
            for (int i = 0; i < map.get(key); i++) {
                sb.append("-");
            }
            System.out.println(sb.toString());
        }

    }

    /*
        히스토그램 정보를 얻는다.
        - map : 알파벳 별 '-'의 갯수를 저장하기 위한 자료구조
            -> ex : (A,10), (B,20) ... 저장
     */
    private static Map<String, Integer> getHistogram(String line) {


        Map<String, Integer> map = new HashMap<>();
        /*
            65 ('A') 부터 90 ('Z') 까지 초기화
         */
        for (int i = 65; i <= 90; i++) {
            char ch = (char)i;
            map.put(String.valueOf(ch), 0);
        }

        // 문자 하나씩 확인
        for (int i = 0; i < line.length(); i++) {

            // 문자
            char ch = line.charAt(i);

            // 문자가 알파벳인지 확인
            if (Character.isAlphabetic(ch)) {

                // 문자를 대문자로 변환 (toUpperCase 메서드) : 소문자->대문자, 대문자->대문자로 변환 (ex: a->A, A->A)
                String key = String.valueOf(ch).toUpperCase();

                // map에 Key가 있다면 숫자 +1 증가
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                }
            }
        }
        return map;
    }
}
