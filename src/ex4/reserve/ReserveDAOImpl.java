package ex4.reserve;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ex4.member.MemberVo;
import ex4.member.SearchMemDialog;

public class ReserveDAOImpl implements ReserveDAO{
	private static final String     driver = "oracle.jdbc.driver.OracleDriver"; // ����� DB���� ���� �����͸� �����Ѵ�.
	private static final String     url    = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String     user   = "scott";
	private static final String     pwd    = "tiger";
	
	private              Connection con;
	private              Statement  stmt;
	private              ResultSet  rs;
	
    SimpleDateFormat setToDayDate = new SimpleDateFormat("yyyyMMdd");
    Calendar         cResDate     = Calendar.getInstance();
    String           strToday     = setToDayDate.format(cResDate.getTime());
    
	public String reserveCar(ResVo vo) throws ResException{
		 String resNo;
		 String resCarNumber;
		 String resID;
		 String resDate;
		 String useBeginDate;
		 String returnDate;

		 resNo        = vo.getResNo();
		 resCarNumber = vo.getResCarNumber();
		 resID        = vo.getResID();
//		 resDate      = vo.getResDate();
		 resDate      = strToday;
		 useBeginDate = vo.getUseBeginDate();
		 returnDate   = vo.getReturnDate();
		 
		 connDB(); 
		 String query = "INSERT INTO Rent_Res(resNo,resCarNumber,resID,resDate,useBeginDate,returnDate)"
                 + " VALUES(CONCAT('',LPAD(resNoplus.nextVAL,5,'0')),'" 
				 + resCarNumber + "','" 
                 + resID        + "'," 
                 + "SYSDATE"    + ",'" 
                 + useBeginDate + "','"
                 + returnDate   + "')";

			try {
				stmt.executeUpdate(query);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return query;
		}
	public ArrayList<ResVo> listReserve(ResVo vo) throws ResException{
		ArrayList<ResVo> carList = new ArrayList<ResVo>();
		String search;
		int    Index = SearchResDialog.deptIndex;
		
		connDB();
		
		String query = "select * from Rent_Res ";
		
		if (Index == 0 && !SearchResDialog.btnSearchchk) {
			search = vo.getResNo();
			query  = query + "where resNo = " + "'" + search + "'";
		
		}else if(Index==1) {
			search=vo.getResNo();			
			if(search.equals("")) {				
				query  = query + "where resNo = "    + "'" + search + "'";
			}else {				
				query = query  + "where resNo LIKE " + "'" + "%" + search + "%" + "'" + " ORDER BY resNo ASC";
			}
			
		}else if(Index==2) {
			search=vo.getResCarNumber();
			if(search.equals("")) {
				query = query + "where resCarNumber = "    + "'" + search + "'";
			}else {
				query = query + "where resCarNumber LIKE " + "'" + "%" + search + "%" + "'" + " ORDER BY resNo ASC";
			}
			
		}else if(Index == 3) {
			search=vo.getResID();
			if(search.equals("")) {
				query = query + "where resID = "    + "'" + search + "'";
			}else {
				query = query + "where resID LIKE " + "'" + "%" + search + "%" + "'" + " ORDER BY resNo ASC";
			}
			
		}else if(Index == 4) {
			search=vo.getResDate();
			if(search.equals("")) {
				query = query + "where resDate = "    + "'" + search + "'";
			}else {
				query = query + "where resDate LIKE " + "'" + "%" + search + "%" + "'" + " ORDER BY resNo ASC";
			}
			
		}else {
			query  = query  + " ORDER BY resNo ASC";			
		}
		
		ResultSet rs;
		
		try {
			rs = stmt.executeQuery(query);
		
		while (rs.next()) { 
			 String resNo        = rs.getString("resno");
			 String resCarNumber = rs.getString("rescarnumber");
			 String resID        = rs.getString("resid");
			 String resDate      = rs.getString("resdate");
			 String useBeginDate = rs.getString("usebegindate");
			 String returnDate   = rs.getString("returndate");
			 ResVo ResData = new ResVo();
			 
			 ResData.setResNo(resNo);
			 ResData.setResCarNumber(resCarNumber);
			 ResData.setResID(resID);
			 ResData.setResDate(resDate);
			 ResData.setUseBeginDate(useBeginDate);
			 ResData.setReturnDate(returnDate);
			 carList.add(ResData);
		}

		rs.close();
		stmt.close();
		con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return carList;
	}
	public void modReserveInfo(ResVo vo) throws ResException{
		 String resNo;
		 String resCarNumber;
		 String resID;
		 String resDate;
		 String useBeginDate;
		 String returnDate;
		 resNo        = vo.getResNo();
		 resCarNumber = vo.getResCarNumber();
		 resID        = vo.getResID();
		 resDate      = vo.getResDate();
		 useBeginDate = vo.getUseBeginDate();
		 returnDate   = vo.getReturnDate();
		 connDB(); 
		 String query ="UPDATE Rent_Res SET ";
		 if (resCarNumber != null && !resCarNumber.equals("")) {
		        query += "resCarNumber='" + resCarNumber + "', ";
		    }
		 if (resID != null && !resID.equals("")) {
			  query += "resID='"       + resID        + "', ";
		    }
		 if (resDate != null && !resDate.equals("")) {
			 query += "resDate='"      + resDate      + "', ";
		 }
		 if (useBeginDate != null && !useBeginDate.equals("")) {
			 query += "useBeginDate='" + useBeginDate + "', ";
		 }
		 if (returnDate != null && !returnDate.equals("")) {
			 query += "returnDate='"   + returnDate   + "', ";
		 }
		 query = query.substring(0, query.lastIndexOf(",")); // 마지막에 추가된 콤마(,) 제거
		     query += " WHERE resNo='" + resNo        + "'";
		System.out.println(query);
			try {
				stmt.executeUpdate(query);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void cancelReserveInfo(ResVo vo) throws ResException{
		String resNo;

		resNo = vo.getResNo();
		
		connDB(); 
		String query = "DELETE FROM Rent_Res WHERE "
				+ "resNo=" + resNo;
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
