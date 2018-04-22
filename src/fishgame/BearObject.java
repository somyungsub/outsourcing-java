package fishgame;

public class BearObject extends GameObject {

    public BearObject(int startX, int startY, int distance) {
        super(startX, startY, distance);
    }

    @Override
    public void move() {
        // 거리 -> 방향이동
        switch (distance) {
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

        // index 기반이므로 x가 0보다 작아지면 0으로 초기화
        if (x < 0) {
            x = 0;
        }

        // 현재 게임판이 10행 20열이므로 x의최대는 20이다. index기반이므로 최대인 19를 넘으면 19로초기화
        if (x > 19) {
            x = 19;
        }
        // index 기반이므로 y가 0보다 작아지면 0으로 초기화
        if (y < 0) {
            y = 0;
        }
        // 현재 게임판이 10행 20열이므로 y의 최대는 10이다. index기반이므로 최대인 9를 넘으면 9로초기화
        if (y > 9) {
            y = 9;
        }
    }

    @Override
    public char getShape() {
        return 'B';
    }
}
