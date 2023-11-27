package ex4.common.exception;

import java.util.Calendar;

public class RentException extends Exception{
	private String mesg;//전달된 예외 정보를 저장하는 변수
	public RentException(String mesg){
		this.mesg=mesg;
	}
	public String toString(){
		return "예외 메시지는 : " +mesg;
	}
	
	public String getRentExceptionInfo(){
		String errMsg="예외발생 시간: "+showErrTime() +" 예외 내용:"+mesg;
		return errMsg;
	}
	
	//조회한 데이터의 시간 정보를 제공하는 메소드
	public static String showErrTime(){
		String date=null;
		String time=null;
		Calendar cal=Calendar.getInstance();
			
		int hour=cal.get(Calendar.HOUR);
		int min=cal.get(Calendar.MINUTE);
		int sec=cal.get(Calendar.SECOND);
			
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;
		int day=cal.get(Calendar.DATE);
			
		date=year+ "-"+ month + "-"+day;
		time=hour +":"+min+":"+sec;
		return date+ " " +time;
	}
}
