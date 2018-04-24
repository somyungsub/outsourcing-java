package medium;

import java.util.Scanner;
import java.util.Stack;

public class MediumMain {

    public static void main(String[] args) {
        /*
            테스트 케이스
            : 1 * 2 - ( 3 + 4 ) / 5 $
            : 1 * ( 2 * ( 5 - 4 ) * ( 6 * 7 ) ) $
         */

        // 입력1 : 1 * 2 - ( 3 + 4 ) / 5 $
        System.out.println(convertRearToMedium());

        // 입력2 : 1 * ( 2 * ( 5 - 4 ) * ( 6 * 7 ) ) $
        System.out.println(convertRearToMedium());
    }

    /*
        중위->후위로 변환 한다.
     */
    private static String convertRearToMedium() {
        // 1. 입력 값을 받는다.
        String str = getString();

        // 2. 받은 입력 값을 split메서드를 활용하여 String배열을 만든다.
        String[] strArr = str.split(" ");

        //
        calc(strArr);
        return "";
    }

    /*
        1. 입력 값을 받는 메서드
     */
    private static String getString(){
        Scanner sc = new Scanner(System.in);    // 입력을 위한 스캐너 객체 생성 및 인풋스트림형성
        String s = sc.nextLine();               // 입력받기
        sc.close();                             // 입력 받은 후 스트림 닫기
        return s.replace("$","");   // 입력 값 반환 및 $는 empty(비어있는) 값으로 변환 시킨 후 반환
    }

    private static void calc(String[] strArr) {
        Stack<String> operStack = new Stack<>();
        Stack<String> numStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {

            if (str.matches("[0-9]")) {
                numStack.push(str);
            }
            else{
                operStack.push(str);
            }

            if (numStack.size() >= 2 && operStack.size() >= 1) {
                String s1 = numStack.pop();
                String s2 = numStack.pop();
                sb.append(s2).append(" ").append(s1).append(" ").append(operStack.pop()).append(" ");
            }

            if (str.equals("(")) {

            }
        }
    }

    private static void perio(Stack<String> operStack) {

        if (operStack.size() > 1) {
            String perio1 = "+,-";
            String perio2 = "*,/";
            String s1 = operStack.pop();
            String s2 = operStack.pop();
            int num1 = perio1.contains(s1) ? 1 : 2;
            int num2 = perio2.contains(s2) ? 2 : 1;

            // 순서교체
            if (num1 <= num2) {
                operStack.push(s2);
                operStack.push(s1);
            }
            // 그대로 유지
            else{
                operStack.push(s1);
                operStack.push(s2);
            }
        }
    }
}
