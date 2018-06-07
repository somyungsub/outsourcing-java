package booking.entity;

/*
    부모클래스   Art
    자식클래스   Movie
 */
public class Movie extends Art {

	public static final int MOVIE_PRICE = 50;   // 영화가격

    // 영화 생성자 : 영화가격과 예매수량을 부모클래스로 전달
	public Movie(int reservationNum) {
		super(MOVIE_PRICE, reservationNum);
	}

    // Object클래스 toString 메서드 재정의
	@Override
	public String toString() {
		return "영화(가격: 50원) 예매를 " + getReservationNum() + " 장 합니다";
	}

}
