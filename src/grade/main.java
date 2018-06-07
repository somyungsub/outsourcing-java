package grade;

public class main
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		ScoreManager sManager = new ScoreManager();
		
		sManager.addClass();
		sManager.addClass();
		sManager.printClassList();

		System.out.println("===================검색후===================");
		// 검색조건이 2개이상 나올수 있어서 for으로 수행해야합니다.
		for (Class clazz: sManager.searchClass("전자공학프로그래밍")) {
			clazz.printClass();
		}

		System.out.println("===================삭제후===================");
		sManager.deleteClass("전자공학프로그래밍");
		sManager.printClassList();		
	}
}
