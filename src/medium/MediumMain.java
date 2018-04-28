package medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class MediumMain {

    private static Map<String, Integer> operObj = new HashMap<>();

    public static void main(String[] args) {
        /*
            테스트 케이스
            : 1 * 2 - ( 3 + 4 ) / 5 $
            : 1 * ( 2 * ( 5 - 4 ) * ( 6 * 7 ) ) $
         */

        // 연산 우선순위 정의
        operObj.put("+", 1);
        operObj.put("-", 1);
        operObj.put("*", 2);
        operObj.put("/", 2);

        // 입력1 : 1 * 2 - ( 3 + 4 ) / 5 $
        System.out.println(convertMediumToRear());

        // 입력2 : 1 * ( 2 + ( 5 - 4 ) * ( 6 + 7 ) ) $
        System.out.println(convertMediumToRear());
    }

    /*
        중위->후위로 변환 한다.
     */
    private static String convertMediumToRear() {
        // 1. 입력 값을 받는다.
        Scanner sc = new Scanner(System.in);    // 입력을 위한 스캐너 객체 생성 및 인풋스트림형성
        String s = sc.nextLine();               // 입력받기
        String str = s.replace("$",""); // 입력 값 반환 및 $는 empty(비어있는) 값으로 변환 시킨 후 반환

        // 2. 받은 입력 값을 split메서드를 활용하여 String배열을 만든다.
        String[] strArr = str.split(" ");

        // 중위 -> 후위로 변환
        convert(strArr);
        return "";
    }

    /*
        중위식을 후위식으로 변환하기
        1. 숫자와 연산자 2개를 담는 Stack 구조를 활용
     */
    private static void convert(String[] strArr) {
        Stack<String> numberStack = new Stack<>();  // 숫자를 담는 스택
        Stack<String> operStack = new Stack<>();    // 연산자를 담는 스택
        String temp = "";   // ()연산을 처리하기 위한 변수

        // 중위식 순회
        for (String str : strArr) {

            // 숫자스택이 적어도 2개이상 인 경우 and 현재str변수가 연산자인 경우에 실행
            if (numberStack.size() >= 2 && !(str.matches(".*[0-9].*"))) {

                // 연산자 우선순위 판단을 위해 직전에 Stack쌓인 데이터 pop
                String operPre = operStack.pop();

                // 앞의 연산자가 현재연산자보다 우선순위가 같거나 크면 변환 실행
                if ((")").equals(str)  || !("(").equals(str) && operObj.get(operPre) >= operObj.get(str)) {

                    // ()가 있는 경우 처리
                    if("(".equals(temp)){
                        temp = "";  // (가 있는걸 확인했으므로 다시 초기화
                        operStack.push(operPre); // Stack 재 저장
                    }else{
                        // 나머지는 변환 연산 실행
                        String pp = calculate(numberStack, operPre);

                        // 변환 후 변환된 문자열 저장
                        numberStack.push(pp);
                    }
                }
                else {
                    operStack.push(operPre); // Stack 재 저장
                }
            }

            // 숫자인 경우 숫자 스택에 저장
            if (str.matches(".*[0-9].*")) {
                numberStack.push(str);
            }
            // 연산자 인 경우 연산자 스택에 저장
            else{
                // ()는 저장에서 제외
                if ("()".contains(str)) {
                    temp = str;
                    continue;
                }
                operStack.push(str);
            }
        }

        // 숫자 Stack이 1개일 때 까지 변환 연산
        while (numberStack.size() > 1) {
            numberStack.push(calculate(numberStack, operStack.pop()));
        }

        // 최종적으로 후위식으로 변환된 문자열 반환
        System.out.println(numberStack.pop().replace("  ", " "));
    }

    /*
        후위식 변환 연산
     */
    private static String calculate(Stack<String> numberStack, String operPre) {
        StringBuilder sb = new StringBuilder();
        String pop1 = numberStack.pop();
        String pop2 = numberStack.pop();
        return sb.append(pop2).append(" ").append(pop1).append(" ").append(operPre).append(" ").toString();
    }
}
