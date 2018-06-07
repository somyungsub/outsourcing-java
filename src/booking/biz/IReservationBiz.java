package booking.biz;

import booking.entity.Art;

/*
    예매관련 로직, 기능을 구현할 인터페이스 정의
 */
public interface IReservationBiz {

	public void addReservation(Art art);	// 예매할 클래스 저장 (Drama, Movie, Musical)
	public void printReservation();			// 저장된 내용 출력

}
