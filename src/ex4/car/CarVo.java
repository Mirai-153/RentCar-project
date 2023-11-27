package ex4.car;

public class CarVo {
	String carNumber;
	String carName;
	String carColor;
	String carSize;
	String carMaker;
	int    carMoney;
	int    carCc;
	
	public CarVo(){}
	public CarVo(String carNumber, String carName, String carColor, String carSize, String carMaker,int carMoney,int carCc) {
		this.carNumber = carNumber;
		this.carName   = carName;
		this.carColor  = carColor;
		this.carSize   = carSize;
		this.carMaker  = carMaker;
		this.carMoney  = carMoney;
		this.carCc     = carCc;
	}

	public int getCarCc() {
		return carCc;
	}

	public void setCarCc(int carCc) {
		this.carCc = carCc;
	}

	public int getCarMoney() {
		return carMoney;
	}

	public void setCarMoney(int carMoney) {
		this.carMoney = carMoney;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public String getCarSize() {
		return carSize;
	}

	public void setCarSize(String carSize) {
		this.carSize = carSize;
	}

	public String getCarMaker() {
		return carMaker;
	}

	public void setCarMaker(String carMaker) {
		this.carMaker = carMaker;
	}
}
