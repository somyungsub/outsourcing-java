package grade;

public class Class {

	String className;
	String classProfessor;
	int classScore;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassProfessor() {
		return classProfessor;
	}
	public void setClassProfessor(String classProfessor) {
		this.classProfessor = classProfessor;
	}
	public int getClassGrade() {
		return classScore;
	}
	public void setClassGrade(int classScore) {
		this.classScore = classScore;
	}
	
	public void printClass() {
		System.out.println("과목 : " + this.className + " // 교수님 : " + this.classProfessor + " // 성적 : " + this.classScore);
	}
}
