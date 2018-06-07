package booking.entity;

/*
    부모클래스

 */
public class Art {

	private int price;          // 가격
	private int reservationNum; // 예매수량

    // 생성자 : 가격, 예매수량
    public Art(int price , int reservationNum) {
        super();
        this.price = price;
        this.reservationNum = reservationNum;
    }

    // 예매수량 반환
	public int getReservationNum() {
		return reservationNum;
	}

	// 예매수량 셋팅
	public void setReservationNum(int reservationNum) {
		this.reservationNum = reservationNum;
	}

	// 가격 반환
	public int getPrice() {
		return price;
	}

	// 가격 셋팅
	public void setPrice(int price) {
		this.price = price;
	}
	
}
