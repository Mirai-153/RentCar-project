package ex4.reserve;

import java.util.ArrayList;

import ex4.reserve.ResException;
import ex4.reserve.ResVo;

public interface ReserveDAO {
	public String reserveCar(ResVo vo) throws ResException; //ȸ�� ��� �޼���
	public ArrayList<ResVo> listReserve(ResVo vo) throws ResException; //���� ��ȸ �޼���
	public void modReserveInfo(ResVo vo) throws ResException;//���� ȸ�� ������ �����ϴ� �޼���
	public void cancelReserveInfo(ResVo vo) throws ResException;//���� ȸ�� ������ �����ϴ� �޼���
}
