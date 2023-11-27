package ex4.reserve;

public class ResVo {
	 String resNo; 
	 String resCarNumber;
	 String resID;
	 String resDate;
	 String useBeginDate;
	 String returnDate;
	 
	public ResVo(String resNo,String resCarNumber,String resID, String resDate, String useBeginDate, String returnDate) {
		this.resNo =resNo;
		this.resCarNumber = resCarNumber;
		this.resID = resID;
		this.resDate = resDate;
		this.useBeginDate = useBeginDate;
		this.returnDate = returnDate;
	}
	public ResVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getResNo() {
		return resNo;
	}
	public void setResNo(String resNo) {
		this.resNo = resNo;
	}
	

	public String getResCarNumber() {
		return resCarNumber;
	}

	public void setResCarNumber(String resCarNumber) {
		this.resCarNumber = resCarNumber;
	}

	public String getResID() {
		return resID;
	}

	public void setResID(String resID) {
		this.resID = resID;
	}

	public String getResDate() {
		return resDate;
	}

	public void setResDate(String resDate) {
		this.resDate = resDate;
	}

	public String getUseBeginDate() {
		return useBeginDate;
	}

	public void setUseBeginDate(String useBeginDate) {
		this.useBeginDate = useBeginDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
}
