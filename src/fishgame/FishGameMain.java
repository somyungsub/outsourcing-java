package fishgame;

import java.util.Scanner;

public class FishGameMain {
    public static void main(String[] args) {
        // 키 표준입력
        Scanner sc = new Scanner(System.in);

        // 10행 20열 게임판 '-' 초기화
        char[][] dash = new char[10][20];
        init(dash);

        // 게임 객체 생성 (Bear, Fish) x,y의 위치는 index : x>=0, y>=0
        GameObject bear = new BearObject(0,0, 0);
        GameObject fish = new FishObject(5,5, 0);

        System.out.println("** Bear의 fish 먹기 게임을 시작합니다. **");
        show(dash, bear, fish); // 초기화면 보여주기
        while (true) {
            System.out.print("왼쪽(a), 아래(s), 위(d), 오른쪽(f) >> ");
            String key = sc.nextLine();

            switch (key) {
                case "a":   // 왼쪽
                    bear.distance = 1;
                    break;
                case "s":   // 아래:
                    bear.distance = 2;
                    break;
                case "d":   // 위
                    bear.distance = 3;
                    break;
                case "f":   // 오른쪽:
                    bear.distance = 4;
                    break;
                default:    // 다른키 입력시 재 입력
                    System.out.println("잘못 입력 하였습니다. 다시 입력해주세요");
                    continue;
            }

            bear.move();    // bear 이동
            fish.move();    // fish 이동
            show(dash, bear, fish); // 이동 후 화면 보여주기

            // 위치가 같으면 게임종료
            if (bear.collide(fish)) {
                System.out.println("Bear Wins!!");
                break;
            }
        }
    }

    // 게임판 초기화
    private static void init(char[][] dash) {
        for (int i = 0; i < dash.length; i++) {
            for (int j = 0; j <dash[i].length; j++) {
                dash[i][j] = '-';
            }
        }
    }

    // 게임판 보여주기
    private static void show(char[][] dash, GameObject bear, GameObject fish) {
        init(dash);
        for (int i = 0; i < dash.length; i++) {
            for (int j = 0; j < dash[i].length; j++) {

                // fish 위치와 같으면 '-'를 fish 모양으로 교체
                if (fish.getX() == j && fish.getY() == i) {
                    dash[i][j] = fish.getShape();
                }

                // Bear 위치와 같으면 '-'를 bear 모양으로 교체
                if (bear.getX() == j && bear.getY() == i) {
                    dash[i][j] = bear.getShape();
                }
                System.out.printf("%c", dash[i][j]);
            }
            System.out.println();
        }
    }
}
