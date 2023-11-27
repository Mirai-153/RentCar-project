package ex4.member;

import java.io.IOException;
import java.util.ArrayList;

import ex4.common.exception.RentException;

public interface Member {
	public ArrayList<MemberVo> listMember(MemberVo vo) throws MemberException,IOException;
	public void regMember(MemberVo vo) throws MemberException, IOException; 
	public void modMember(MemberVo vo) throws MemberException;
	public void delMember(MemberVo vo) throws MemberException;

}
