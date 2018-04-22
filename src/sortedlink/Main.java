package sortedlink;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inFile = null;
        try {
            // input.txt 파일 읽어들이기 : 현재디렉토리 (src/sortedlink/)
            inFile = new Scanner(new File("src\\sortedlink\\input.txt"));
        } catch (FileNotFoundException e) {
            // 예외 처리
            System.out.println("Error opening the file: input.txt");
        }

        // Student 클래스 저장할 리스트
        SortedLinkedList<Student> studentList = new SortedLinkedList<>();

        // inFile에서 학생 데이터 읽어 studentList에 저장
        getStudentData(inFile, studentList);

        // 저장된 내용 출력
        System.out.print(studentList.toString());

        // 스트림 닫기
        inFile.close();
    }

    // inFile에서 학생 데이터 읽어 studentList에 저장
    private static void getStudentData(Scanner inFile, SortedLinkedList<Student> studentList) {

        // 다음 읽을 데이터가 있는지 확인
        while (inFile.hasNext()) {
            // 첫번째 라인 : 학생정보 (학번, 이름) -> 공백으로 split
            String[] studentIn = inFile.nextLine().split(" ");

            // 학생이 수강신청한 과목 저장할 리스트
            SortedLinkedList<Course> courseList = new SortedLinkedList<>();

            // 그 다음 읽을 데이터가 있는지 확인
            while (inFile.hasNext()) {
                // 두번째 라인 부터 : 과목정보 (과목명, 이수학점수, 학점) -> 공백으로 split
                String[] subject = inFile.nextLine().split(" ");

                // 만약 subject가 없다면 (다음 학생정보 구분) -> 길이가 0 or 첫번째 데이터가 비어있는 정보 -> 반복문 탈출
                if (subject.length == 0 || subject[0].isEmpty()) {
                    break;
                }

                // 과목정보 리스트에 저장
                Course course = new Course(subject[0], Integer.parseInt(subject[1]), subject[2].charAt(0));
                courseList.insert(course);
            }

            // 학생정보 리스트에 저장
            Student student = new Student(Integer.parseInt(studentIn[0]), studentIn[1], courseList);
            studentList.insert(student);

        }
    }
}
