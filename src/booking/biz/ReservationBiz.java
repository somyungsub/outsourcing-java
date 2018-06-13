package booking.biz;

import booking.entity.Art;
import booking.entity.Drama;
import booking.entity.Movie;
import booking.entity.Musical;

import java.util.ArrayList;
import java.util.List;

/*
    예매관련 로직, 기능을 구현할 인터페이스 구현체

    2가지 기능 구현

    1. 예매내역 저장 (addReservation)
    2. 예매내역 출력 (printReservation)

 */
public class ReservationBiz implements IReservationBiz {

	private int money;  // 잔액
    private List<Art> artList;  // Art를 상속하는 하위 클래스들 관리 자료구조

	// 생성자 : 잔액을 매개변수로 받음
	public ReservationBiz(int money) {
		super();
        artList = new ArrayList<>();
		this.money = money;
	}

	/*
	    예매 저장
	    - 다형성을 활용하여 데이터를 저장 및 출력
	 */
	@Override
	public void addReservation(Art art) {

		System.out.printf("현재 잔액: %d 원 \n" , money);
        System.out.println(art.toString()); // 각 자식클래스에서 오버라이딩 된 toString 호출

        // 현재금액과 예매수 * 각가격을 비교
        if (money >= art.getPrice() * art.getReservationNum()) {    // 현재금액이 > 예매수*가격 (현재예매가 가능한경우 판단)
            // 현재금액 - 예매수 * 가격
            money -= art.getPrice() * art.getReservationNum();

            // 예매수량 만큼 Art 객체 저장
            for (int i = 0; i < art.getReservationNum(); i++) {
                artList.add(art);
            }

        }else { // 예매 불가한 경우
            System.out.println("잔액이 부족하여 영화예매 불가능합니다.");
        }
	}

	/*
	    예매 저장 내용 출력
	 */
	@Override
	public void printReservation() {
		int movieNum = 0;   //  영화 예매수
		int musicNum = 0;   //  뮤지지컬 예매수
		int dramaNum = 0;   //  연극 예매수

        // 저장된 예매수를 판단
		for(Art art : artList){
			if( art instanceof Movie){
				movieNum++;
			}
			else if( art instanceof Drama){
				dramaNum++;
			}
			else if( art instanceof Musical){
				musicNum++;
			}
		}

		// 총 예매한 금액
       int sum = movieNum * Movie.MOVIE_PRICE + musicNum * Musical.MUSICAL_PRICE + dramaNum * Drama.DRAMA_PRICE;
       System.out.println("=====예매 목록=====");
       System.out.printf("영화 예매: %d 개 \n" , movieNum);      // 영화예매수
       System.out.printf("연극 예매: %d 개 \n" , dramaNum);      // 연극예매수
       System.out.printf("뮤지컬 예매: %d 개 \n" , musicNum);    // 뮤지컬예매수
       System.out.println("======================");
       System.out.printf("전체 예매 금액: %d 원 \n" , sum);      // 총 예매한 금액계산
       System.out.printf("남은 금액: %d 원 \n" , money);         // 남은 금액 출력
	}
}
