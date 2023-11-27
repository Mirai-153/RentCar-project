package ex4.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ex4.car.SearchCarDialog;
import ex4.common.exception.RentException;

public class MemberDAOImpl implements MemberDAO {
	private static final String     driver = "oracle.jdbc.driver.OracleDriver"; 
	private static final String     url    = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String     user   = "scott";
	private static final String     pwd    = "tiger";
	private              Connection con;
	private              Statement  stmt;
	private              ResultSet  rs;

	public void regMember(MemberVo vo) throws RentException {
		String id;
		String password;
		String name;
		String address;
		String phonenum;
		id       = vo.getId();
		password = vo.getPassword();
		name     = vo.getName();
		address  = vo.getAddress();
		phonenum = vo.getPhoneNum();
		connDB(); 
		String query = "INSERT INTO Rent_Member(id,password,name,address,phonenum) VALUES ('" 
		             + id       + "','"
				     + password + "','" 
		             + name     + "','" 
				     + address  + "','" 
				     + phonenum + "')";
		System.out.println(query);

		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<MemberVo> listMember(MemberVo vo) throws RentException {
		ArrayList<MemberVo> memList = new ArrayList<MemberVo>();
		String search;
		int    Index = SearchMemDialog.deptIndex;
		connDB(); 
		String query = "select * from Rent_Member ";
		
		if (Index == 0 && !SearchMemDialog.btnSearchchk) {
			search = vo.getId();
			query  = query + "where id = "+"'"+search+"'";
		
		}else if(Index == 1) {
			search=vo.getId();
			if(search.equals("")) {
				query = query + "where id = "     + "'" +search + "'";
			}else {
				query = query + "where id LIKE "  + "'" + "%"   + search + "%" + "'";
			}
			
		}else if(Index == 2) {
			search=vo.getName();
			if(search.equals("")) {
				query  = query + "where name = "+"'"+search+"'";
			}else {
				query = query + "where name LIKE "     + "'" + "%" + search + "%" + "'";
			}
			
		}else if(Index == 3) {
			search=vo.getAddress();
			if(search.equals("")) {
				query  = query + "where address = "+"'"+search+"'";
			}else {
				query = query + "where address LIKE " + "'" + "%" + search + "%" + "'";
			}
			
		}else if(Index == 4) {
			search=vo.getPhoneNum();
			if(search.equals("")) {
				query  = query + "where phonenum = "+"'"+search+"'";
			}else {
				query = query + "where phonenum LIKE " + "'" + "%" + search + "%" + "'";
			}		
		
			
		}else {
			System.out.println(query);
		}
		System.out.println(query);
		ResultSet rs;
		
		try {
			rs = stmt.executeQuery(query);
		
		while (rs.next()) { 
			String memID     = rs.getString("id");
			String memPass   = rs.getString("password");
			String memName   = rs.getString("name");
			String memAdd    = rs.getString("address");
			String memPhone  = rs.getString("phonenum");
			MemberVo memData = new MemberVo();
			memData.setId(memID);
			memData.setPassword(memPass);
			memData.setName(memName);
			memData.setAddress(memAdd);
			memData.setPhoneNum(memPhone);
			memList.add(memData);
		}

		rs.close();
		stmt.close();
		con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

	public void modMember(MemberVo vo) throws MemberException {
		String id;
		String password;
		String name;
		String address;
		String phoneNum;
		id = vo.getId();
		password = vo.getPassword();
		name = vo.getName();
		address = vo.getAddress();
		phoneNum = vo.getPhoneNum();
		connDB(); // DB�� �����ϴ� �޼���
		String query = "UPDATE Rent_Member SET ";
		 if (password != null && !password.equals("")) {
		        query += "password='" + password + "', ";
		    }
		 if (name != null && !name.equals("")) {
			  query += "name='" + name + "', ";
		    }
		 if (address != null && !address.equals("")) {
			 query += "address='" + address + "', ";
		 }
		 if (phoneNum != null && !phoneNum.equals("")) {
			 query += "phoneNum='" + phoneNum + "', ";
		 }
		 query = query.substring(0, query.lastIndexOf(",")); // 마지막에 추가된 콤마(,) 제거
		 query += " WHERE id='" + id + "'";
		System.out.println(query);
		try {
			stmt.executeUpdate(query);
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void delMember(MemberVo vo) throws MemberException {
		String id;

		id = vo.getId();
		
		connDB(); 
		String query = "DELETE FROM Rent_Member WHERE "
				+ "id="+"'"+id+"'";
		System.out.println(query);
		try {
			stmt.executeUpdate(query);
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void connDB() {
		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공");
			stmt = con.createStatement();
			System.out.println("Statement 생성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
