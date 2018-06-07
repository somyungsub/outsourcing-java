package booking.entity;

/*
    부모클래스   Art
    자식클래스   Musical
 */
public class Musical extends Art {

	public static final int MUSICAL_PRICE = 200;    // 뮤지컬 가격

    // 뮤지컬 생성자 : 뮤지컬가격과 예매수량을 부모클래스로 전달
	public Musical(int reservationNum) {
		super(MUSICAL_PRICE, reservationNum);
	}

	// Object클래스 toString 메서드 재정의
	@Override
	public String toString() {
		return "뮤지컬(가격: 200원) 예매를 " +getReservationNum()+" 장 합니다";
	}

}
