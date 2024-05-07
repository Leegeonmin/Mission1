package javacode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
 
public class wifi_repo {
    String driver = "org.mariadb.jdbc.Driver";
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
 
    public wifi_repo() {
 
         try {
            Class.forName(driver);
            con = DriverManager.getConnection(
                    "jdbc:mariadb://127.0.0.1:3306/zerobase",
                    "root",
                    "root");
            if( con != null ) {
                System.out.println("DB 접속 성공");
            }

        } catch (ClassNotFoundException e) { 
            System.out.println("드라이버 로드 실패");
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
    }
    
    public wifi_dao findById(int id) {
        wifi_dao dao = new wifi_dao();
    	 try {
             if(con == null) {
                 System.out.println("데이터베이스 연결 실패");
                 return dao; // 연결 실패 시 메서드 종료
             }
             String query = "SELECT * FROM wifi_info where id = ? and use_YN = 1"; // 쿼리 작성
             pstmt = con.prepareStatement(query); // 쿼리를 PreparedStatement에 설정
             pstmt.setInt(1, id);
             rs = pstmt.executeQuery(); // 쿼리 실행 및 결과 저장
             if(rs.next()) { 
	         	dao.setId(rs.getInt("id"));
	         	dao.setWifi_id(rs.getString("wifi_id"));
	         	dao.setDistrict(rs.getString("district"));
	         	dao.setName(rs.getString("name"));
	         	dao.setStreet_address(rs.getString("street_address"));
	         	dao.setDetail_address(rs.getString("detail_address"));
	         	dao.setFloor(rs.getString("floor"));
	         	dao.setType(rs.getString("type"));
	         	dao.setAgency(rs.getString("agency"));
	         	dao.setService_type(rs.getString("service_type"));
	         	dao.setNetwork_type(rs.getString("network_type"));
	         	dao.setInstallation_year(rs.getString("installation_year"));
	         	dao.setIs_indoor(rs.getInt("is_indoor"));
	         	dao.setNetwork_setting(rs.getString("network_setting"));
	         	dao.setLatX(rs.getDouble("latX"));
	         	dao.setLonY(rs.getDouble("lonY"));
	         	dao.setReg_date(rs.getTimestamp("reg_date"));
	         	dao.setUse_YN(rs.getInt("use_YN"));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
    	 return dao;
    }
    // db 조회 
    public ArrayList<wifi_dao> findWifiInfoAll() {
        ArrayList<wifi_dao> daoList = new ArrayList<wifi_dao>();
        try {
            if(con == null) {
                System.out.println("데이터베이스 연결 실패");
                return new ArrayList<wifi_dao>(); // 연결 실패 시 메서드 종료
            }
            String query = "SELECT * FROM wifi_info where use_yn= 1"; // 쿼리 작성
            pstmt = con.prepareStatement(query); // 쿼리를 PreparedStatement에 설정
            rs = pstmt.executeQuery(); // 쿼리 실행 및 결과 저장
            // 결과 반복문을 통해 출력
            while(rs.next()) {
            	wifi_dao dao = new wifi_dao();
            	dao.setId(rs.getInt("id"));
            	dao.setWifi_id(rs.getString("wifi_id"));
            	dao.setDistrict(rs.getString("district"));
            	dao.setName(rs.getString("name"));
            	dao.setStreet_address(rs.getString("street_address"));
            	dao.setDetail_address(rs.getString("detail_address"));
            	dao.setFloor(rs.getString("floor"));
            	dao.setType(rs.getString("type"));
            	dao.setAgency(rs.getString("agency"));
            	dao.setService_type(rs.getString("service_type"));
            	dao.setNetwork_type(rs.getString("network_type"));
            	dao.setInstallation_year(rs.getString("installation_year"));
            	dao.setIs_indoor(rs.getInt("is_indoor"));
            	dao.setNetwork_setting(rs.getString("network_setting"));
            	dao.setLatX(rs.getDouble("latX"));
            	dao.setLonY(rs.getDouble("lonY"));
            	dao.setReg_date(rs.getTimestamp("reg_date"));
            	dao.setUse_YN(rs.getInt("use_YN"));
            	
            	daoList.add(dao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return daoList;
    }
    
    // db 삽입 
    public void insertWifiInfo(ArrayList<wifi_dto> dtos) {
    	String query = "INSERT INTO wifi_info (wifi_id, district, name, street_address, detail_address, floor, type, agency, service_type, network_type, installation_year, is_indoor, network_setting, latX, lonY, reg_date, use_YN) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 try {
		     // PreparedStatement 객체 생성
		     pstmt = con.prepareStatement(query);
		     
		     // 각 dto 객체의 정보를 데이터베이스에 삽입
		     for (wifi_dto dto : dtos) {
		         pstmt.setString(1, dto.getxSwifiMgrNo());
		         pstmt.setString(2, dto.getxSwifiWrdocf());
		         pstmt.setString(3, dto.getxSwifiMainNm());
		         pstmt.setString(4, dto.getxSwifiAdres1());
		         pstmt.setString(5, dto.getxSwifiAdres2());
		         pstmt.setString(6, dto.getxSwifiInstlFloor());
		         pstmt.setString(7, dto.getxSwifiInstlTy());
		         pstmt.setString(8, dto.getxSwifiInstlMby());
		         pstmt.setString(9, dto.getxSwifiSvcSe());
		         pstmt.setString(10, dto.getxSwifiCmcwr());
		         pstmt.setString(11, dto.getxSwifiCnstcYear());
		         pstmt.setInt(12, dto.getxSwifiInoutDoor().equals("실내") ? 1 : 0);
		         pstmt.setString(13, dto.getxSwifiRemars3());
		         pstmt.setDouble(14, Double.parseDouble(dto.getLat()));
		         pstmt.setDouble(15, Double.parseDouble(dto.getLnt()));
		         pstmt.setTimestamp(16, Timestamp.valueOf(dto.getWorkDttm()));
		         pstmt.setInt(17, 1); // 사용 여부
		        
		         // 쿼리 실행
		         pstmt.executeUpdate();
		     }
		     
		     System.out.println("데이터 삽입 완료");
		 } catch (SQLException e) {
		     e.printStackTrace();
		 }finally {
		        // DB 연결 닫기
		        try {
		            if (pstmt != null) {
		                pstmt.close();
		            }
		            if (con != null) {
		                con.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
    	
    }
		public static void main(String[] args){
		    wifi_repo test  = new wifi_repo();
		    test.findWifiInfoAll();
		}
}