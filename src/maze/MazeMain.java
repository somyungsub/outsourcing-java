package maze;

public class MazeMain {

    public static void main(String[] args) {
        // Maze 클래스 생성 후 실행
        Maze m = new Maze();
        m.readMazeData();
        m.findExit();
    }
}
