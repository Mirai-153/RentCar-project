package ex4.reserve;

import java.util.ArrayList;

import ex4.base.Base;
import ex4.common.DataUtil;

public class ReserveImpl extends Base implements Reserve{
	String resData;
	ReserveDAO reserveDAO = new ReserveDAOImpl();	
	//차를 예약하는 메서드
	public String reserveCar(ResVo vo){
		resData="예약번호"+vo.resNo+","+
				"예약 차번호:"+vo.resCarNumber+","+
				"예약회원 ID"+vo.resID+","+
	            "예약등록시간:"+vo.resDate+","+
	            "이용 시작 일자"+vo.useBeginDate+","+
	            "차 반납 일자:"+vo.returnDate;
		
		DataUtil.encodeData(resData);		
		System.out.println("렌터카 예약시간:"+showTime());
		System.out.println("차를 예약합니다.");
		DataUtil.decodeData(resData);
		try {
			reserveDAO.reserveCar(vo);
		} catch (ResException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resData;
	}
	public ArrayList<ResVo> listReserve(ResVo vo){
		ArrayList<ResVo> resList=null;		
		try {
			resList=reserveDAO.listReserve(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resList;
	}
	
	//차 예약정보를 수정하는 메서드
	public void modReserveInfo(ResVo vo){
		resData="예약번호"+vo.resNo+","+
				"예약 차번호:"+vo.resCarNumber+","+
	            "예약 날자:"+vo.resDate+","+
	            "이용 시작 일자"+vo.useBeginDate+","+
	            "차 반납 일자:"+vo.returnDate;
		
		DataUtil.encodeData(resData);	
		System.out.println("렌터카 예약 정보 수정 시간:"+showTime());
		System.out.println("차를 예약합니다.");
		try {
			reserveDAO.modReserveInfo(vo);
		} catch (ResException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//차 예약 정보를 취소하는 메서드
	public void cancelReserveInfo(ResVo vo){
		resData="예약번호"+vo.resNo+","+
				"예약 차번호:"+vo.resCarNumber+","+
	            "예약 날자:"+vo.resDate+","+
	            "이용 시작 일자"+vo.useBeginDate+","+
	            "차 반납 일자:"+vo.returnDate;
		
		DataUtil.decodeData(resData);		
		System.out.println("렌터카 예약 정보 삭제 시간:"+showTime());
		System.out.println("차 예약 정보를 삭제합니다.");
		try {
			reserveDAO.cancelReserveInfo(vo);
		} catch (ResException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

	

