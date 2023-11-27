package ex4.reserve;

import java.util.ArrayList;

public interface Reserve {
	public String reserveCar(ResVo vo) throws ResException;   //���� �����ϴ� �޼���
	public ArrayList<ResVo> listReserve(ResVo vo) throws ResException;//���� ���������� ��ȸ�ϴ� �޼�Ʈ,
	public void modReserveInfo(ResVo vo) throws ResException;   //�� ���������� �����ϴ� �޼���
	public void cancelReserveInfo(ResVo vo) throws ResException;  //�� ���� ������ ����ϴ� �޼���
}
