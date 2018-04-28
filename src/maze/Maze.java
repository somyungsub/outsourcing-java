package maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Maze {
    int sizeX, sizeY;   // 게임판 사이즈
    int startX, startY; // S (시작점)
    int endX, endY;     // F (도착점)
    int [][] maze;      // 벽(1), 길(0)

    // 데이터
    public void readMazeData() {
        Scanner scan = new Scanner(System.in);

        sizeY = scan.nextInt();     // 9 (행)
        sizeX = scan.nextInt();     // 8 (열)

        startY = scan.nextInt();    // 1 (행)
        startX = scan.nextInt();    // 1 (열)

        endY = scan.nextInt();      // 6 (행)
        endX = scan.nextInt();      // 6 (열)

        maze = new int[sizeY][sizeX];   // 8행 9열

        // 게임판 초기화
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                maze[y][x] = scan.nextInt();
            }
        }
        scan.close();
    }

    // 작성할것
    public void findExit() {
        char[][] gameRound = new char[sizeY][sizeX];
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                gameRound[y][x] = String.valueOf(maze[y][x]).charAt(0);
            }
        }

        int startY = this.startY;   // 행
        int startX = this.startX;   // 열

        gameRound[startY][startX] = 'S'; // 시작점 표시
        gameRound[endY][endX] = 'F';     // 도착점 표시

        // 시작
        Stack<List<Integer>> stack = new Stack<>();
        List<Integer> stackList = null;
        boolean success = false;        // 성공여부 체크 플래그 : 미로탈출 시 로직에서 true로 변경됨

        while (true){

            // 스택에 경로 저장
            if (!stack.isEmpty()) {
                List<Integer> popList = stack.pop();
                startY = popList.get(0); // 현재 위치 재설정  row
                startX = popList.get(1); // 현재 위치 재설정  col
//                pathList.add(popList);              // 총 경로 저장

                // 현위치
                if (gameRound[startY][startX] == '0') {
                    gameRound[startY][startX] = '*';
                }

                // 탈출위치
                if (gameRound[startY][startX] == 'F') {
                    success = true;
                }

            }

            // 위 확인
            if (startY - 1 >= 0 && (gameRound[startY-1][startX] == '0' || gameRound[startY-1][startX] == 'F')) {
                stackList = new ArrayList<>();
                stackList.add(startY - 1);
                stackList.add(startX);
                stack.push(stackList);
            }

            // 왼쪽 확인
            if (startX - 1 >= 0 && (gameRound[startY][startX-1] == '0' || gameRound[startY][startX-1] == 'F')) {
                stackList = new ArrayList<>();
                stackList.add(startY);
                stackList.add(startX - 1);
                stack.push(stackList);
            }

            // 오른쪽 확인
            if (startX + 1 < this.sizeX && (gameRound[startY][startX+1] == '0' || gameRound[startY][startX+1] == 'F')) {
                stackList = new ArrayList<>();
                stackList.add(startY);
                stackList.add(startX + 1);
                stack.push(stackList);
            }

            // 아래 확인
            if (startY + 1 < this.sizeY && (gameRound[startY+1][startX] == '0' || gameRound[startY+1][startX] == 'F')) {
                stackList = new ArrayList<>();
                stackList.add(startY + 1);
                stackList.add(startX);
                stack.push(stackList);
            }


            // 갈곳이 없거나 미로탈출 성공 시
            if(stack.isEmpty() || success){

                // 갈곳이 없는 경우 (경로수행 x)
                if (!success) {
                    System.out.println("탈출실패");
                    return;
                }

                /*
                    미로 탈출한 경우
                        1. 길경로 확인
                        2. 최단경로 찾기
                        3. 최단경로에서 같은 row열의 이동 찾기
                        4. 길 최종확인 후 문자출력
                 */

                // 화면보기
                for (int y = 0; y < this.sizeY; y++) {
                    StringBuilder sb = new StringBuilder();
                    for (int x = 0; x < this.sizeX; x++) {
                        sb.append(gameRound[y][x]).append(" ");
                    }
                    System.out.println(sb.replace(sb.length()-1,sb.length(),""));
                }
                return ;
            }
        }
    }
}
