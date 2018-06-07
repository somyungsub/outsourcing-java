package booking.test;

import booking.biz.ReservationBiz;
import booking.entity.Drama;
import booking.entity.Movie;
import booking.entity.Musical;

/*
    예매관리시스템 구현
        - 상속, 인터페이스를 활용하여 다형성 개념을 확립하여 자바의 큰 장점을 살린 객체지향설계를 최우선시 함
        - 각 클래스는 기능(로직)구현, 실행부, 데이터부를 각각 역할에 맡게 나누어 설계

    1. 데이터 클래스
        - 부모클래스 : Art
        - 자식클래스 : Drama, Movie, Musical

    2. 실행 로직 구현 인터페이스
        - ReservationBiz (정의)
        - IReservationBiz (구현체) -> 실행

    3. 실행부
        - ReservationTest (this)
        - ReservationBiz 인터페이스를 활용하여 Art 클래스들을 예매하고 예매한내역을 출력합니다.

 */
public class ReservationTest {

	public static void main(String[] args) {

		/*
			실행부
			- ReservationBiz 인터페이스를 활용하여 Art 클래스들을 예매하고 예매한내역을 출력합니다.
		 */
		
		ReservationBiz biz = new ReservationBiz(1000);  // ReservationBiz

		biz.addReservation(new Movie(2) );
		biz.addReservation(new Musical(1));
		biz.addReservation(new Movie(1) );
		biz.addReservation(new Drama(2));
		biz.addReservation(new Musical(2));
		biz.addReservation(new Musical(1));
	
	    biz.printReservation();

	}

}
