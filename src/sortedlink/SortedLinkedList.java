package sortedlink;


import java.util.ArrayList;
import java.util.List;

public class SortedLinkedList<T> {
    List<T> list;   // 저장할 리스트
    int idx = 0;    // 리스트의 위치 탐색 인덱스

    // 리스트가 비어있는지 확인
    public boolean isEmpty(){
        return list == null || list.isEmpty();
    }

    // 리스트에 객체 저장
    public void insert(T item){

        if (list == null) {
            list = new ArrayList<>();
        }

        // 비어있지 않으면 정렬처리 하여 저장
        if (!isEmpty()) {
            int insertIdx = 0;  // 삽입위치
            int size = size();  // 리스트의 사이즈

            // 학생
            if (item instanceof Student) {
                Student student = (Student) item;
                for (int i = 0; i < size; i++) {
                    Student student2 = (Student) list.get(i);

                    // 비교 후 삽입학생객체의 숫자가 작아지는 위치 확인
                    if (student.compareTo(student2) < 0) {
                        insertIdx = i;
                        break;
                    } else {
                        // 나머지는 마지막 위치
                        insertIdx = size;
                    }
                }
                // 삽입위치에 삽입
                list.add(insertIdx, item);
            }
            // 과목
            else if (item instanceof Course) {
                Course course = (Course) item;
                for (int i = 0; i < size; i++) {
                    Course course2 = (Course) list.get(i);
                    // 비교 후 삽입과목의 과목명의 순서가 작아지는 위치 확인
                    if (course.compareTo(course2) < 0) {
                        insertIdx = i;
                        break;
                    } else {
                        // 나머지는 마지막 위치
                        insertIdx = size;
                    }
                }
                // 삽입위치에 저장
                list.add(insertIdx, item);
            }
        }
        // 비어있다면 그냥 저장
        else{
            list.add(item);
        }
    }

    // 리스트 사이즈 반환
    public int size(){
        return list.size();
    }

    // idx 초기화
    public void reset(){
        idx = 0;
    }

    // 다음 객체가 있는지 확인
    public boolean hasNext() {

        // 리스트사이즈와 idx의 위치 비교
        boolean flg = idx < size();
        if (!flg) {
            reset();
        }
        return flg;
    }

    // 객체 꺼내기
    public T next(){
        if (idx == size()) {
            reset();
        }
        return list.get(idx++);
    }

    // 리스트의 정보 출력
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append(list.get(i).toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
