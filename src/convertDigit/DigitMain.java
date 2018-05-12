package convertDigit;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class DigitMain {
    static Logger logger = Logger.getLogger(DigitMain.class);


    public static void main(String[] args) throws Exception {
        // 로거 설정
//        String pattern = "[%d{yyyy-MM-dd HH:mm:ss}] %-5p [%l] - %m%n";
//        String datePattern = ".yyyy-MM-dd";
//        PatternLayout layout = new PatternLayout(pattern);
//        DailyRollingFileAppender appender = new DailyRollingFileAppender(layout, "C:\\IntelliJWork\\log.txt", datePattern);
//        logger.addAppender(appender);
//        logger.setLevel(Level.DEBUG);

        // 아래부터 프로세스
        Properties props = new Properties();
        String path = "src\\convertDigit\\checkword5.properties";
        FileInputStream fis = new FileInputStream(new File(path));
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        props.load(isr);

        String filePath = "src\\convertDigit\\「이지폰 서비스」이용 약관.txt";
        String filePath2 = "src\\convertDigit\\「이지폰 서비스」이용 약관_save.txt";
//        String filePath = "src\\convertDigit\\Shinhan BNPP 자산운용사 위험등급 변경.txt";
//        String filePath2 = "src\\convertDigit\\Shinhan BNPP 자산운용사 위험등급 변경_save.txt";
        FileInputStream fis2 = new FileInputStream(filePath);
        FileOutputStream fos = new FileOutputStream(filePath2);

        BufferedReader br = new BufferedReader(new InputStreamReader(fis2, "utf-8"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));
        String s = null;
        Set<Object> set = props.keySet();


        int idx = 0;
        int resultCode = 0;
        while ((s = br.readLine()) != null) {
            List<Map<String, String>> chLogList = new ArrayList<>();
            List<Map<String, String>> digitLogList = new ArrayList<>();
            idx++;
            // 특수문자 변환
            for (Object obj : set) {
                String key = obj.toString();
                if (s.contains(key.trim())) {
                    Map<String, String> map = new HashMap<>();
                    map.put(key, props.get(key).toString());
                    chLogList.add(map);
                }
                s = s.replace(key, props.get(key).toString());
            }

            // 숫자 변환
            String number = "";
            String save = s.toString();
            int size = s.length();
            String preStr = "";
            for (int i = 0; i < size; i++) {
                char ch = s.charAt(i);
                if (Character.isDigit(ch)) {
                    number += s.charAt(i);
                    continue;
                } else if(!number.isEmpty()){
                    resultCode = 1;
                    String chgNum = !preStr.isEmpty() && "점".equals(preStr)
                                    ? changeNumber2(number) : changeNumber(number);
                    Map<String, String> map = new HashMap<>();
                    map.put(number, chgNum);
                    digitLogList.add(map);
                    save = save.replaceFirst(number, chgNum);
                    number = "";
                }
                preStr = String.valueOf(ch);
            }
            // if 마지막 데이터 숫자 then 변환
            boolean lastNumFlg = Pattern.matches("^[0-9]*$", number);
            if(!number.isEmpty() && lastNumFlg){
                Map<String, String> map = new HashMap<>();
                String chgNum = !preStr.isEmpty() && "점".equals(preStr)
                                ? changeNumber2(number) : changeNumber(number);
                map.put(number, chgNum);
                digitLogList.add(map);
                save = save.replaceFirst(number, chgNum);
            }
            // 특수문자 변환 로그
            if (!chLogList.isEmpty()) {
                showLog(chLogList, idx,"특수문자");
            }
            // 숫자 변환 로그
            if (!digitLogList.isEmpty()) {
                showLog(digitLogList, idx,"숫자");
            }

            bw.write(save);
            bw.write(System.lineSeparator());
        }
        br.close();
        bw.close();
        isr.close();
    }

    // 로그
    public static void showLog(List<Map<String, String>> list, int lineIdx, String caseStr) {
        for (Map<String, String> map: list) {
            StringBuilder sb = new StringBuilder();
            for (String key : map.keySet()) {
                sb.append("[").append(caseStr).append(" 변환 Line Number : ").append(lineIdx).append("] ")
                        .append("(AS-IS) : ").append(key).append(" (TO-BE) : ").append(map.get(key));
            }
//            logger.debug(sb.toString());
            System.out.println(sb.toString());
        }
    }
    // 숫자를 한글로 바꾸기 위한 메서드2 (소수점)
    public static String changeNumber2(String number) {
        StringBuilder sb = new StringBuilder();
        String[] kr1 = {"영", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"};
        int size = number.length(); // 숫자 길이
        for (int i = 0; i < size; i++) {
            int s = Integer.parseInt(String.valueOf(number.charAt(i)));
            sb.append(kr1[s]);
        }
        return sb.toString();
    }

    // 숫자를 한글로 바꾸기 위한 메소드
    public static String changeNumber(String number) {

        StringBuilder sb = new StringBuilder();

        String[] kr1 = {"", "이", "삼", "사", "오", "육", "칠", "팔", "구"};
        String[] kr2 = {"", "만", "억", "조", "경"};
//        String[] kr2 = {"", "만", "억", "조", "경","해", "양", "구", "간", "정", "재", "극", "항하사", "아승기","나유타", "불가사의", "무량대수"};
        String[] kr3 = {"천","","십","백"};

        int size = number.length(); // 숫자 길이

        // 소수점 앞자리 0인 경우 예외처리
        if (size == 1 && "0".equals(number)) {
            return "영";
        }

        // 0으로 시작하는 경우 or 단위기준 넘는 경우
        if (size > 0 && number.charAt(0) == '0' || number.length() > (kr2.length * 4)) {
            return changeNumber2(number);
        }

        for (int i = 0; i < size; i++) {

            // 0 인경우  -> 문자열 불필요
            if (Integer.parseInt(String.valueOf(number.charAt(i))) <= 0) {
                continue;
            }
            int reminder = (size - i) % 4;  // kr3 기준
            int mock = (size - i) / 4;      // kr2 기준
            int idx = Integer.parseInt(String.valueOf(number.charAt(i))) - 1; // kr1 기준

            /*
                a = 단위 앞의 숫자 (ex 이, 삼, 사 ... 1인 경우 x)
                b = 단위 기준      (ex ~만, ~억, ~천 등등)
             */
            if(kr2.length < mock){
                logger.debug("에러 : 단위 표현이 불가능한 숫자단위 입니다.");
            }
            String a = kr1[idx];
            String b = reminder == 1 ? kr2[mock] : kr3[reminder];

            /*
                억 이상 단위 기준 : 1인 경우 -> 일 o (ex 일억, 일조)
                억 미만 단위 기준 : 1인 경우 -> 일 x (ex 만, 천 ...) - 일만, 일천 x
             */
            if (a.isEmpty() && (b.isEmpty() || (size > 8 && reminder == 1))) {
                sb.append("일").append(b);
            } else {
                sb.append(a).append(b);
            }

        }
        return sb.toString();
    }
}
