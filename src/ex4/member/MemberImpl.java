package ex4.member;

import java.io.IOException;
import java.util.ArrayList;

import ex4.base.Base;
import ex4.common.exception.RentException;

public class MemberImpl extends Base implements Member{
	
	MemberDAO memDAO = new MemberDAOImpl();	
	//���ο� ȸ�� ��� �ϴ� �޼���
	public void regMember(MemberVo vo) throws MemberException, IOException{
		ArrayList<MemberVo> memList=null;		
		try {
			memDAO.regMember(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	//���� ȸ�� ���� ��ȸ�ϴ� �޼���
	public ArrayList<MemberVo> listMember(MemberVo vo) throws MemberException, IOException{
		ArrayList<MemberVo> memList=null;		
		try {
			memList=memDAO.listMember(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memList;
	}	
	//���� ȸ�� ������ �����ϴ� �޼���
	public void modMember(MemberVo vo) throws MemberException{
		try {
			memDAO.modMember(vo);
		} catch (RentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	//���� ȸ�� ������ �����ϴ� �޼���
	public void delMember(MemberVo vo) throws MemberException{
		try {
			memDAO.delMember(vo);
		} catch (RentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
