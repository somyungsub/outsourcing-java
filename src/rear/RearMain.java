package rear;

import java.util.Scanner;
import java.util.Stack;

public class RearMain {
    public static void main(String[] args) {
        /*
            테스트케이스
            : 2 3 4 * + 4 2 / - $
            : 2 5 * 4 / 3 2 * - 5 + $
         */

        // 입력1 : 2 3 4 * + 4 2 / - $
        System.out.println(getRearCalc());

        // 입력2 : 2 5 * 4 / 3 2 * - 5 + $
        System.out.println(getRearCalc());
    }

    /*
        후위계산을 실행한다.
         1. 입력 값을 받는다.
         2. 받은 입력 값을 split메서드(구분자 : 공백)를 이용하여 String배열을 만든다.
         3. 계산을 위한 Stack<String> 객체를 활용한다.
         4. Stack<String> 객체에 입력 값 String배열을 을 순서대로 저장 하되 숫자가 아닌 경우 계산을 수행한다.
         5. 마지막 계산된 값(결과값)을 반환한다.
     */
    private static double getRearCalc(){
        // 1. 입력 값을 받는다.
        String str = getString();

        // 2. 받은 입력 값을 split메서드를 활용하여 String배열을 만든다.
        String[] strArr = str.split(" ");

        // 3. 계산을 위한 Stack 객체를 활용한다.
        Stack<String> stack = new Stack<>();

        // 4. Stack객체에 순서대로 저장 하되 숫자가 아닌 경우 계산을 수행한다.
        for (String s : strArr) {
            // 입력된 값을 순서대로 스택에 저장
            stack.push(s);

            // 숫자가 아닌 경우 (연산자 인 경우)
            if(!s.matches("[0-9]")){    // 정규식 : [0-9]는 문자열이 숫자로만 구성된 것인지 판단을 합니다.

                // 계산을 수행한다.
                calcuate(stack, stack.pop());
            }
        }

        // 5. 최종 계산된 결과 값을 반환한다.
       return Double.parseDouble(stack.pop());
    }

    /*
        1. 입력 값을 받는 메서드
     */
    private static String getString(){
        Scanner sc = new Scanner(System.in);    // 입력을 위한 스캐너 객체 생성 및 인풋스트림형성
        String s = sc.nextLine();               // 입력받기
        return s.replace("$","");   // 입력 값 반환 및 $는 empty(비어있는) 값으로 변환 시킨 후 반환
    }

    /*
        4. Stack 객체의 값과 연산자를 활용 하여 계산을 수행한다.

        @param : stack      : num1, num2를 pop메서드(값 빼내기)를 통해 숫자 2개를 얻음
        * 주의 스택은 LIPO이므로 처음 꺼낸 숫자 -> 마지막 숫자임을 유의
        * ex) 2 3 4 * 에서 스택에서는 * 4 3 2순으로 빼냄 - > num1 : 4, num2: 3 이고 계산시 3 * 4가 되어야 한다.

        @param : operator   : 해당 연산자에 따라 연산 수행
     */
    private static void calcuate(Stack<String> stack, String operator){
        double result = 0.0;
        /*
            ex 2 3 -> num1 : 3, num2 : 2
         */
        double num1 = Double.parseDouble(stack.pop());  // 뒤 숫자
        double num2 = Double.parseDouble(stack.pop());  // 앞 숫자

        // 연산시 바꿔서 연산 해줘야 함
        switch (operator) {
            case "+":
                result = num2 + num1;
                break;
            case "-":
                result = num2 - num1;
                break;
            case "*":
                result = num2 * num1;
                break;
            case "/":
                result = num2 / num1;
                break;
            default:
                break;
        }

        // 계산된 결과 값을 stack에 저장
        stack.push(String.valueOf(result));
    }
}
