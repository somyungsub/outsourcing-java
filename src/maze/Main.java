package maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        // 파일 경로 (input, maze) : 경로는 파일위치에 따라 변경됩니다. 현재디렉토리는 srd/maze/ 입니다.
        String inputPath = "src\\maze\\input.txt";
        String mazePath = "src\\maze\\maze.txt";
        String str = null;  // 파일데이터 임시 저장

        try {

            // input 파일생성
            File file = new File(inputPath);
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));

            // maze 파일생성
            File file2 = new File(mazePath);
            FileInputStream fis2 = new FileInputStream(file2);
            BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2, "UTF-8"));

            /*
                maze 첫줄 읽기 : 게임판의 행,렬 크기
                구분자는 " " (공백)으로 String 객체배열 생성 -> 12 20은 12와 20의 String객체로 나뉘어짐
             */
            String[] strArr = br2.readLine().split(" ");

            // 게임판 크기 생성
            char[][] maze = new char[Integer.parseInt(strArr[0])][Integer.parseInt(strArr[1])];
            int size = 0;

            // 초기화(12행 20열의 char배열)
            while ((str = br2.readLine()) != null) {
                for (int i = 0; i < str.length(); i++) {
                    maze[size][i] = str.charAt(i);
                }
                size++;
            }

            // 게임 실행
            while ((str = br.readLine()) != null) {
                String[] startXY = str.split(" ");  // 시작점 위치 (구분자 : 공백) : 행, 열의 위치로 설정됨
                char[][] copyMaze = deepCopy(maze);         // 게임판 초기화 복사하여 게임판 재 설정
                mazeFindGame(startXY, copyMaze);               // 게임실행 후 미로탈출여부 확인
            }

            // 파일 닫기
            br.close();
            br2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        게임판 초기화 내용 복사
        -> 게임 재실행을 위한 작업
      */
    private static char[][] deepCopy(char[][] maze) {
        // 배열이 비어있으면 null 리턴
        if (maze == null) {
            return null;
        }

        // 복사할 배열 생성
        char[][] copyMaze = new char[maze.length][maze[0].length];

        // maze배열 -> copyMaze에 복사
        for (int i = 0; i < maze.length; i++) {
            System.arraycopy(maze[i],0, copyMaze[i],0, maze[i].length);
        }

        // 복사된 copyMaze 리턴하여 사용
        return copyMaze;
    }


    /*
        미로찾기 실행 (요구사항을 수행)
            1. stack (백트랙킹)을 활용하여 미로탈출 여부 판단 -> 출력
            2. 미로탈출 시 경로 저장
            3. 경로의 최단경로 구현
            4. 최단경로 출력
     */
    private static void mazeFindGame(String [] startXY, char[][] maze){

        /*
            0 = 길 / 1 = 벽 / 현위치 = '+'
         */

        int startX = Integer.parseInt(startXY[0]) - 1;   // 행
        int startY = Integer.parseInt(startXY[1]) - 1;   // 열
        Stack<List<Integer>> stack = new Stack<>();
        List<Integer> stackList = null;

        int rowSize = maze.length;      // 행 사이즈 : 12
        int colSize = maze[0].length;   // 열 사이즈 : 20
        boolean success = false;        // 성공여부 체크 플래그 : 미로탈출 시 로직에서 true로 변경됨

        // 최초 현위치
        if (maze[startX][startY] == '0') {
            maze[startX][startY] = '+';
        }

        /*
            총경로 - 불필요 경로 = 최단경로 완성
         */
        int count = 0;  // 불필요한 경로 체크 플래그
        List<List<Integer>> pathList = new ArrayList<>();   // 총 경로저장
        List<List<Integer>> pathList2 = new ArrayList<>();  // 불필요 경로 저장
        while (true){

            // 스택에 경로 저장
            if (!stack.isEmpty()) {
                List<Integer> popList = stack.pop();
                startX = popList.get(0); // 현재 위치 재설정  row
                startY = popList.get(1); // 현재 위치 재설정  col
                pathList.add(popList);              // 총 경로 저장

                // 현위치
                if (maze[startX][startY] == '0') {
                    maze[startX][startY] = '+';
                }

                // 탈출위치
                if (maze[startX][startY] == 'E') {
                    success = true;
                }

            }

            count = 0;

            // 위 확인
            if (startX - 1 >= 0 && (maze[startX-1][startY] == '0' || maze[startX-1][startY] == 'E')) {
                stackList = new ArrayList<>();
                stackList.add(startX - 1);
                stackList.add(startY);
                stack.push(stackList);
                count++;
            }

            // 오른쪽 확인
            if (startY + 1 < colSize && (maze[startX][startY+1] == '0' || maze[startX][startY+1] == 'E')) {
                stackList = new ArrayList<>();
                stackList.add(startX);
                stackList.add(startY + 1);
                stack.push(stackList);
                count++;
            }

            // 왼쪽 확인
            if (startY - 1 >= 0 && (maze[startX][startY-1] == '0' || maze[startX][startY-1] == 'E')) {
                stackList = new ArrayList<>();
                stackList.add(startX);
                stackList.add(startY - 1);
                stack.push(stackList);
                count++;
            }

            // 아래 확인
            if (startX + 1 < rowSize && (maze[startX+1][startY] == '0' || maze[startX+1][startY] == 'E')) {
                stackList = new ArrayList<>();
                stackList.add(startX + 1);
                stackList.add(startY);
                stack.push(stackList);
                count++;
            }

            // 최단경로 아닌 경로 저장
            if (count == 0 && !success) {
                List<Integer> list2 = new ArrayList<>();
                list2.add(startX);
                list2.add(startY);
                pathList2.add(list2);
            }

            // 갈곳이 없거나 미로탈출 성공 시
            if(stack.isEmpty() ||  success){

                // 갈곳이 없는 경우 (경로수행 x)
                if (!success) {
//                    System.out.println("탈출실패 시작점 : (" + startXY[0] + "," + startXY[1] + ")");
                    return;
                }

                /*
                    미로 탈출한 경우
                        1. 길경로 확인
                        2. 최단경로 찾기
                        3. 최단경로에서 같은 row열의 이동 찾기
                        4. 길 최종확인 후 문자출력
                 */

                // 길경로 확인
                pathList.removeAll(pathList2);  // 총 경로 - 불필요한 경로 => 최단경로
                Collections.reverse(pathList);  // 알고리즘 편의상 역순 정렬하여 작업순 마지막에서 다시 역순 재 정렬

                // 최단 경로 찾기
                List<String> path = pathFind(pathList, startXY);

                // 재 역순 정렬
                Collections.reverse(path);

                // 같은 row 복사 후 path 완성
                path = copyPath(path);

                // 길최종확인 후 문자출력
                showPath(path, startXY);

                return ;
            }
        }
    }

    /*
        최단경로를 찾는다.
            1. 같은 row별 분리작업
            2. 분리 된 row리스트에서 최단경로 꼭지점 찾기
     */
    private static List<String> pathFind(List<List<Integer>> pathList, String[] startXY) {
        List<List<Integer>> list = new ArrayList<>();
        List<List<Integer>> list2 = new ArrayList<>();

        // 경로를 row별로 분리
        int tempRow = 0;
        for (List<Integer> path : pathList) {
            int row = path.get(0);

            // 다음 row가 같지 않다면
            if (tempRow != row){
                list.add(path);
            }
            // 다음 row가 같다면
            else{
                list2.add(path);
            }
            tempRow = row;
        }

        /*
            row별 경로를 저장
                Map (key : Integer, value : row별 좌표리스트) -> 작업을 위한 자료구조
         */

        Map<Integer, List<List<Integer>>> map = new TreeMap<>();
        int sizeCount = 0;  // 탐색효율을 높이고 중복저장 발생을 막기위한 변수
        for (int i = 0; i < list.size(); i++) {
            List<List<Integer>> save = new ArrayList<>();
            List<Integer> l1 = list.get(i);

            // 최초 첫번째 좌표저장
            save.add(l1);

            // row별 좌표리스트를 저장하기 위한 작업
            for (int j = sizeCount; j < list2.size(); j++) {
                List<Integer> l2 = list2.get(j);
                if(l1.get(0).compareTo(l2.get(0)) == 0){
                    sizeCount++;    // 같은 row의 갯수만큼 증가
                    save.add(l2);   // 같은 row 저장
                }
                // 같지 않다면 바로 탈출
                else{
                    break;
                }
            }

            // col의 내림차순 정렬
            if(save.size() > 1){
                for (int j = 0; j < save.size(); j++) {
                    List<Integer> ll1 = save.get(j);
                    for (int k = j; k < save.size(); k++) {
                        List<Integer> ll2 = save.get(k);
                        // 현재 row의 col 순서를 비교 : 내림차순이 아닌 경우 스왑
                        if(ll1.get(1).compareTo(ll2.get(1)) < 0){
                            save.set(j, ll2);
                            save.set(k, ll1);
                        }
                    }
                }
            }

            // 최종 Map에 row리스트 저장
            map.put(i, save);
        }

        // 최단경로 꼭지점 찾기
        List<String> path = new ArrayList<>();
        for (int i = 0; i < map.size()-1; i++) {
            List<List<Integer>> first = map.get(i);
            List<List<Integer>> second = map.get(i+1);

            for (int j = 0; j < first.size(); j++) {
                List<Integer> l1 = first.get(j);
                boolean flg = false;
                for (int k = 0; k < second.size(); k++) {
                    List<Integer> l2 = second.get(k);
                    if (l1.get(1) == l2.get(1)) {
                        if(!path.contains(l1.get(0) + " " + l1.get(1))){
                            path.add(l1.get(0) + " " + l1.get(1));
                        }
                        path.add(l2.get(0) + " " + l2.get(1));
                        flg = true;
                        break;
                    }
                }
                if (flg) {
                    break;
                }
            }
        }

        // 시작점 저장
        path.add((Integer.parseInt(startXY[0]) - 1) + " " + (Integer.parseInt(startXY[1]) - 1));
        return path;
    }

    /*
        최단경로에서 중복된 row의 이동을 위해 저장작업을 하여 최종 경로를 완성한다.
     */
    private static List<String> copyPath(List<String> path){
        List<String> copyPath = new ArrayList<>(path);

        // 길 중복 row 저장
        for (int i = 0; i < path.size() - 1; i++) {
            String[] s1 = path.get(i).split(" ");
            String[] s2 = path.get(i + 1).split(" ");

            // 같은 row -> 2개 이상 인 경우 오른쪽, 왼쪽 판단
            if(s1[0].equals(s2[0])){
                for (int j = 0; j <  Integer.parseInt(s2[1]) - Integer.parseInt(s1[1]) - 1; j++) {
                    String s = s1[0] + " " + (Integer.parseInt(s1[1]) + j + 1);
                    copyPath.add(i + j + 1, s);
                }
            }
        }
        return copyPath;
    }

    /*
        현재 좌표와 다음 좌표를 비교하여 경로를 출력한다.
     */
    private static void showPath(List<String > path, String[] startXY){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size() - 1; i++) {
            String[] s1 = path.get(i).split(" ");
            String[] s2 = path.get(i + 1).split(" ");

            // 아래 (D)
            if(Integer.parseInt(s1[0]) < Integer.parseInt(s2[0])){
                sb.append("D");
            }

            // 위 (U)
            if(Integer.parseInt(s1[0]) > Integer.parseInt(s2[0])){
                sb.append("U");
            }

            // 오른쪽 (R)
            if(Integer.parseInt(s1[1]) < Integer.parseInt(s2[1])){
                sb.append("R");
            }

            // 왼쪽 (L)
            if(Integer.parseInt(s1[1]) > Integer.parseInt(s2[1])){
                sb.append("L");
            }
        }

        // 최종 경로 출력
        System.out.println("미로탈출 시작점 : (" + startXY[0] +  "," + startXY[1] + ")");
        System.out.println("경로 : " + sb.toString());
    }

}
