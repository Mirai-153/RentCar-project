package ex4.member;

import java.io.IOException;
import java.util.ArrayList;

import ex4.base.Base;
import ex4.common.exception.RentException;

public class MemberImpl extends Base implements Member{
	
	MemberDAO memDAO = new MemberDAOImpl();	
	//새로운 회원 등록 하는 메서드
	public void regMember(MemberVo vo) throws MemberException, IOException{
		ArrayList<MemberVo> memList=null;		
		try {
			memDAO.regMember(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	//기존 회원 정보 조회하는 메서드
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
	//기존 회원 정보를 수정하는 메서드
	public void modMember(MemberVo vo) throws MemberException{
		try {
			memDAO.modMember(vo);
		} catch (RentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	//기존 회원 정보를 삭제하는 메서드
	public void delMember(MemberVo vo) throws MemberException{
		try {
			memDAO.delMember(vo);
		} catch (RentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
