package fishgame;

// 추상클래스
public abstract class GameObject {
    protected int distance; // 한 번 이동거리
    protected int x;   // 현재위치 (화면 맵상 x좌표)
    protected int y;   // 현재위치 (화면 맵상 y좌표)

    // 초기위치와 이동거리 설정
    public GameObject(int startX, int startY,int distance) {
        this.distance = distance;
        this.x = startX;
        this.y = startY;
    }

    // x좌표 얻기
    public int getX() {
        return x;
    }

    // y좌표 얻기
    public int getY() {
        return y;
    }

    // 충돌여부 확인 : 이 객체가 객체p와 충돌했으면 true 아니면 false 리턴
    public boolean collide(GameObject p) {
        if (this.x == p.getX() && this.y == p.getY()) {
            return true;
        } else {
            return false;
        }
    }

    // 이동한 후의 새로운 위치로 x,y 변경
    public abstract void move();

    // 객체의 모양을 나타내는 문자리턴
    public abstract char getShape();
}
