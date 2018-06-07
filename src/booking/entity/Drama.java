package booking.entity;

/*
    부모클래스   Art
    자식클래스   Drama
 */
public class Drama extends Art{

public static final int DRAMA_PRICE = 100;  // 연극 가격

    // 연극 생성자 : 연극가격과 예매수량을 부모클래스로 전달
	public Drama( int reservationNum) {
		super(DRAMA_PRICE, reservationNum);
	}

	// Object클래스 toString 메서드 재정의
	@Override
	public String toString() {
		return "연극(가격: 100원) 예매를 " +getReservationNum()+" 장 합니다";
	}
}
