package ex4.car;

import java.util.ArrayList;

public interface CarDAO {
	public ArrayList<CarVo> listCarInfo(CarVo vo) throws Exception;
	public void regCarInfo(CarVo vo) throws Exception;
	public void modCarInfo(CarVo vo) throws Exception;
	public void delCarInfo(CarVo vo) throws Exception;
}
