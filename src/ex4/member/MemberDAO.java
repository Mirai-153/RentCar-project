package ex4.member;
import java.util.ArrayList;
import ex4.common.exception.RentException;

public interface MemberDAO {
	public ArrayList<MemberVo> listMember(MemberVo vo) throws RentException; 
	public void regMember(MemberVo vo) throws RentException; 
	public void modMember(MemberVo vo) throws RentException;
	public void delMember(MemberVo vo) throws RentException;
}
