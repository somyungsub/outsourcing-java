package grade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreManager 
{

    ArrayList<Class> classList;
    private Scanner scan;

    public ScoreManager() 
    {
        this.classList = new ArrayList<>();
        scan = new Scanner(System.in);
    }

    public void addClass() 
    {
        String className;
        String classProfessor;
        int classScore;

        System.out.println("과목명을 입력하세요.");
        className = scan.nextLine();
        System.out.println("해당 과목을 가르치는 교수님을 입력하세요.");
        classProfessor = scan.nextLine();
        System.out.println("해당 과목의 점수를 입력하세요.");
        classScore = scan.nextInt();
        
        if(scan.hasNextLine()) 
        {
            scan.nextLine();
        }
        Class newClass = new Class();

        newClass.setClassName(className);
        newClass.setClassProfessor(classProfessor);
        newClass.setClassGrade(classScore);

        classList.add(newClass);
    }

    // 검색에 따라 2개 이상이 나올 수 있으므로 반환형을 리스트로 수정했습니다.
    public List<Class> searchClass(String className)
    {
        List<Class> list = new ArrayList<>();
        for (Class c : classList)
        {
            if (c.getClassName().equals(className))
            {
                list.add(c);
            }
        }
        return list;
    }

    public void deleteClass(String className) 
    {
        // 삭제 시 2건 이상이 발생 할 수 있으므로
        // 저장 할 리스트를 선언 후 -> removeList
        // 해당리스트에 따른 삭제처리를 합니다. -> classList.removeAll(removeList)
        List<Class> removeList = new ArrayList<>();
        for (Class c : classList) {
            if (c.getClassName().equals(className)) {
                removeList.add(c);
            }
        }
        classList.removeAll(removeList);
    }
  
    public void printClassList() 
    {

        if (this.classList.size() == 0)
            System.out.println("리스트에 과목이 존재하지 않습니다");
        else
        {
         for (Class c : this.classList)
             System.out.println("과목 : " + c.className + " // 교수님 : " + c.classProfessor + " // 성적 : " + c.classScore);
        }
    }
}
