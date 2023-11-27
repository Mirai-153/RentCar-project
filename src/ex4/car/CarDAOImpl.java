package ex4.car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;

public class CarDAOImpl implements CarDAO {
	private static final String     driver = "oracle.jdbc.driver.OracleDriver"; // ����� DB���� ���� �����͸� �����Ѵ�.
	private static final String     url    = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String     user   = "scott";
	private static final String     pwd    = "tiger";
	private              Connection con;
	private              Statement  stmt;
	private              ResultSet  rs;

	public void regCarInfo(CarVo vo) throws Exception {
		String carNumber;
		String carName;
		String carColor;
		String carSize;
		String carMaker;
		int    carMoney;
		int    carCc;
		carNumber = vo.getCarNumber();
		carName   = vo.getCarName();
		carColor  = vo.getCarColor();
		carSize   = vo.getCarSize();
		carMaker  = vo.getCarMaker();
		carMoney  = vo.getCarMoney();
		carCc     = vo.getCarCc();
		connDB(); // DB�� �����ϴ� �޼���
		String query = "INSERT INTO Rentcar6_Car(carNumber,carName,carColor,carSize,carMaker,carMoney," 
		             + "carCc) VALUES ('" + carNumber + "','" + carName + "','" + carColor 
		             + "','" + carSize + "','" + carMaker + "',"+carMoney+","+carCc+")";
		System.out.println(query);
		stmt.executeUpdate(query);
	}

	public ArrayList<CarVo> listCarInfo(CarVo vo) throws Exception {
		ArrayList<CarVo> carList = new ArrayList<CarVo>();
		String search ;
		int Index =SearchCarDialog.deptIndex;
		
		connDB(); // DB�� �����ϴ� �޼���
		//String query = "select * from Rentcar6_Car where carNumber= "+"'a'";
		String query = "select * from Rentcar6_Car ";		
		
		if (Index == 0 && !SearchCarDialog.btnSearchchk) {
			search = vo.getCarNumber();
			query  = query + "where carNumber = "+"'"+search+"'";
		
		}else if (Index == 1) {
			search = vo.getCarNumber();
			if(search.equals("")) {
				query  = query + "where carNumber = "+"'"+search+"'";
			}else {
				query  = query + "where carNumber LIKE "+"'"+"%"+search+"%"+"'";
			}
			
		}else if(Index == 2) {
			search = vo.getCarName();
			if(search.equals("")) {
				query  = query + "where carName = "+"'"+search+"'";
			}else {
				query  = query + "where carName LIKE "+"'"+"%"+search+"%"+"'";
			}
			
		}else if(Index == 3) {
			search = vo.getCarMaker();
			if(search.equals("")) {
				query  = query + "where carMaker = "+"'"+search+"'";
			}else {
				query  = query + "where carMaker LIKE "+"'"+"%"+search+"%"+"'";
			}	
		}
	
		System.out.println(query);
		ResultSet rs = stmt.executeQuery(query); 
		while (rs.next()) { 
			String carNumber = rs.getString("carNumber");
			String carName   = rs.getString("carName");
			String carColor  = rs.getString("carColor");
			String carSize   = rs.getString("carSize");
			String carMaker  = rs.getString("carMaker");
			int    carMoney  = rs.getInt("carMoney");
			int    carCc     = rs.getInt("carCc");
			CarVo carData = new CarVo();
			carData.setCarNumber(carNumber);
			carData.setCarName(carName);
			carData.setCarColor(carColor);
			carData.setCarSize(carSize);
			carData.setCarMaker(carMaker);
			carData.setCarMoney(carMoney);
			carData.setCarCc(carCc);
			carList.add(carData);
		}
		rs.close();
		stmt.close();
		con.close();
		return carList;
	}
	public void modCarInfo(CarVo vo) throws Exception {
		String carNumber;
	    String carName;
	    String carColor;
	    String carSize;
	    String carMaker;
	    int carMoney;
	    int carCc;
	    carNumber = vo.getCarNumber();
	    carName = vo.getCarName();
	    carColor = vo.getCarColor();
	    carSize = vo.getCarSize();
	    carMaker = vo.getCarMaker();
	    carMoney = vo.getCarMoney();
	    carCc = vo.getCarCc();
		connDB(); // DB 연결
	    String query = "UPDATE Rentcar6_Car SET ";
	    if (carName != null && !carName.equals("")) {
	        query += "carName='" + carName + "', ";
	    }
	    if (carColor != null && !carColor.equals("")) {
	        query += "carColor='" + carColor + "', ";
	    }
	    if (carMaker != null && !carMaker.equals("")) {
	        query += "carMaker='" + carMaker + "', ";
	    }
	    if (carSize != null && !carSize.equals("")) {
	    	query += "carSize='"+ carSize + "', ";
	    }
	    if (carMoney > 0) {
	        query += "carMoney=" + carMoney + ", ";
	    }
	    if (carCc > 0) {
	    	query += "carCc=" + carCc + ", ";
	    }
	    query = query.substring(0, query.lastIndexOf(",")); // 마지막에 추가된 콤마(,) 제거
	    query += " WHERE carNumber='" + carNumber + "'";
	    System.out.println(query);
	    stmt.executeUpdate(query);
	    stmt.close();
	    con.close();
	}
	public void delCarInfo(CarVo vo) throws Exception {
		String carNumber;
		carNumber = vo.getCarNumber();
		connDB(); 
		String query = "DELETE FROM Rentcar6_Car WHERE "
				     + "carNumber="+"'"+carNumber+"'";
		System.out.println(query);
		stmt.executeUpdate(query);
		
		stmt.close();
		con.close();		
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
