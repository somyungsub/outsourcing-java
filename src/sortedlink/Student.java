package sortedlink;

public class Student implements Comparable<Student> {
    private int number;	    // 학번을 저장하는 인스턴스 변수
    private String name; 	// 학생 이름을 저장하는 인스턴스 변수
    private SortedLinkedList<Course> courseList; // 수강하는 과목을 정렬 : 리스트에 저장

    // 생성자
    public Student(int number, String name, SortedLinkedList<Course> courseList) {
        this.number = number;
        this.name = name;
        this.courseList = courseList;
    }

    @Override
    public int compareTo(Student other) {
        // 현재 객체와 학번비교
        return number > other.number ? 1 : -1;
    }

    // 학생이 수강하는 과목의 총 학점 수를 반환하는 메소드 : 수강하는 모든 과목의 학점 수를 합하여 반환
    public int getTotalCredits() {
        int sum = 0;
        while (courseList.hasNext()) {
            Course course = courseList.next();
            sum += course.getCredit();
        }

        return sum;

    }

    // 학생정보 출력
    public String toString(){

        // 수강과목 정보 출력 저장
        StringBuilder sb = new StringBuilder();
        while (courseList.hasNext()) {
            Course course = courseList.next();
            sb.append("\t\t").append(course.toString()).append(System.lineSeparator());
        }
        return  number + "\t\t" + name + "\t\t" + "total\t" + "credits : " + getTotalCredits()
                + System.lineSeparator()
                + sb.toString();
    }
}
