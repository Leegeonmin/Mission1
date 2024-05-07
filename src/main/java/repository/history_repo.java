package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javacode.history_dao;

public class history_repo {
	 String driver = "org.mariadb.jdbc.Driver";
	    Connection con;
	    PreparedStatement pstmt;
	    ResultSet rs;
	 
	    public history_repo() {
	 
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
	    
	    
	    // db 조회 
	    public ArrayList<history_dao> findHistoryInfoAll() {
	        ArrayList<history_dao> daoList = new ArrayList<history_dao>();
	        try {
	            if(con == null) {
	                System.out.println("데이터베이스 연결 실패");
	                return new ArrayList<history_dao>(); // 연결 실패 시 메서드 종료
	            }
	            String query = "SELECT * FROM history where use_yn = 1"; // 쿼리 작성
	            pstmt = con.prepareStatement(query); // 쿼리를 PreparedStatement에 설정
	            rs = pstmt.executeQuery(); // 쿼리 실행 및 결과 저장
	            // 결과 반복문을 통해 출력
	            while(rs.next()) {
	            	history_dao dao = new history_dao();
	            	dao.setReg_date(rs.getTimestamp("reg_date"));
	            	dao.setLatX(rs.getDouble("latX"));
	            	dao.setLonY(rs.getDouble("lonY"));
	            	dao.setUse_YN(rs.getInt("use_YN"));
	            	dao.setId(rs.getInt("id"));
	            	daoList.add(dao);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return daoList;
	    }
	    
	    // db 삽입 
	    public void insertHistoryInfo(double latX, double lonY) {
	    	String query = "INSERT INTO history (latX, lonY, reg_date, use_YN) " +
	                "VALUES (?, ?, ?, ?)";
			 try {
			     // PreparedStatement 객체 생성
			     pstmt = con.prepareStatement(query);
			         pstmt.setDouble(1, latX);
			         pstmt.setDouble(2,lonY);
			         pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			         pstmt.setInt(4, 1); // 사용 여부

			         // 쿼리 실행
			         pstmt.executeUpdate();
			     
			     
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
	    
	    public void deleteHistoryInfo(int id) {
	        String query = "update history set use_yn = 0 WHERE id = ?  ";
	        try {
	            // PreparedStatement 객체 생성
	            pstmt = con.prepareStatement(query);
	            pstmt.setInt(1, id);

	            // 쿼리 실행
	            int rowsAffected = pstmt.executeUpdate();
	            
	            if (rowsAffected > 0) {
	                System.out.println("ID " + id + "에 해당하는 히스토리 정보 삭제 완료");
	            } else {
	                System.out.println("ID " + id + "에 해당하는 히스토리 정보가 존재하지 않습니다.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // DB 연결 닫기
	            try {
	                if (pstmt != null) {
	                    pstmt.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
