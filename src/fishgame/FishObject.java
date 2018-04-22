package fishgame;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedTransferQueue;

public class FishObject extends GameObject {
    Random rand;            // 방향 생성을 위한 Random 클래스
    Queue<Boolean> queue;   // 이동 가능여부를 체크하기 위한 자료구조

    public FishObject(int startX, int startY, int distance) {
        super(startX, startY, distance);
        rand = new Random();
        queue = new LinkedTransferQueue<>();
    }

    @Override
    public void move() {

        // 이동여부 초기화
        if (queue.isEmpty()) {
            moveInit();
        }
        // 큐에 저장된 내용 빼내기 (true or false)
        boolean flg = queue.poll();

        // true -> 이동
        if (flg) {
            // 1~4 숫자를 통해 방향 설정
            int num = rand.nextInt(4) + 1;
            while (true) {

                // 이동가능한지 체크 : true -> 탈출
                if (isTrue(num)) {
                    break;
                }

                // isTrue메서드가 true가 될때 까지 랜덤을 통해 숫자 생성
                num = rand.nextInt(4) + 1;
            }

            // 이동
            switch (num) {
                case 1:     // 왼쪽
                    x -= 1;
                    break;
                case 2:     // 아래
                    y += 1;
                    break;
                case 3:     // 위
                    y -= 1;
                    break;
                case 4:     // 오른쪽
                    x += 1;
                    break;
            }
        }
    }

    @Override
    public char getShape() {
        return '@';
    }

    // 이동 가능한 위치인지 확인
    private boolean isTrue(int num) {
        int xx = x;
        int yy = y;

        // num : 1~4 숫자에 따른 이동방향 좌표 설정
        switch (num) {
            case 1:     // 왼쪽
                xx -= 1;
                break;
            case 2:     // 아래
                yy += 1;
                break;
            case 3:     // 위
                yy -= 1;
                break;
            case 4:     // 오른쪽
                xx += 1;
                break;
        }
        return xx < 10 && yy < 19 ? true : false;
    }

    // 이동가능여부 초기화
    private void moveInit() {
        int falseNum = 0;   // 대기 수
        int trueNum = 0;    // 이동 수

        while (falseNum <= 3 && trueNum <= 2) {
            // 랜덤을 통해 설정 (짝수 : true, 홀수 : false)
            boolean flg = rand.nextInt(100) % 2 == 0 ? true : false;
            if (flg) {
                if (falseNum < 3) {
                    falseNum++;
                    queue.offer(false); // 큐에 false 저장
                }
            } else {
                if (trueNum < 2) {
                    trueNum++;
                    queue.offer(true);  // 큐에 true 저장
                }
            }
            // 5개가 완성되면 탈출
            if (falseNum == 3 && trueNum == 2) {
                break;
            }
        }
    }
}
