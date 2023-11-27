package ex4.car;
import java.util.ArrayList;

import ex4.base.Base;
import ex4.common.DataUtil;
public class CarImpl extends Base implements Car{
	String carData;
	CarDAO carDAO = new CarDAOImpl();	
	
	public ArrayList<CarVo> listCarInfo(CarVo vo) throws Exception{
		ArrayList<CarVo> carList=null;		
		try {
			carList = carDAO.listCarInfo(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carList;
	}	

	public void regCarInfo(CarVo vo) throws Exception{carDAO.regCarInfo(vo);}	

	public void modCarInfo(CarVo vo) throws Exception{carDAO.modCarInfo(vo);}	

	public void delCarInfo(CarVo vo) throws Exception{carDAO.delCarInfo(vo);}
	

}
