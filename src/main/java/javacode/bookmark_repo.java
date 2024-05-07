package javacode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class bookmark_repo {
	 String driver = "org.mariadb.jdbc.Driver";
	    Connection con;
	    PreparedStatement pstmt;
	    ResultSet rs;
	 
	    public bookmark_repo() {
	 
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
	    
	    
	    // 단건 조회
	    public bookmark_dao findById(int id) {
	        bookmark_dao dao = new bookmark_dao();
	        try {
	            if(con == null) {
	                System.out.println("데이터베이스 연결 실패");
	                return new bookmark_dao(); // 연결 실패 시 메서드 종료
	            }
	            String query = "SELECT * FROM bookmark where use_yn = 1 and id = ?"; // 쿼리 작성
	            pstmt = con.prepareStatement(query); // 쿼리를 PreparedStatement에 설정
	            pstmt.setInt(1, id);
	            rs = pstmt.executeQuery(); // 쿼리 실행 및 결과 저장
	            // 결과 반복문을 통해 출력
	            if(rs.next()) {
	            	dao.setId(rs.getInt("id"));
	            	dao.setWifi_id(rs.getInt("wifi_id"));
	            	dao.setName(rs.getString("name"));
	            	dao.setSeq(rs.getInt("seq"));
	            	dao.setWifi_name(rs.getString("wifi_name"));
	            	dao.setReg_date(rs.getTimestamp("reg_date"));
	            	dao.setUp_date(rs.getTimestamp("up_date"));
	            	dao.setUse_YN(rs.getInt("use_YN"));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return dao;
	    }
	    
	    // 업데이트
	    public void update(String name, int seq,int id) {
	    	String updateQuery = "UPDATE bookmark SET name = ?, seq = ?, up_date = ? WHERE id = ?";
	        try {
	            // PreparedStatement 객체 생성
	            pstmt = con.prepareStatement(updateQuery);
	            pstmt.setString(1, name);
	            pstmt.setInt(2, seq);
	            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
	            pstmt.setInt(4, id);

	            // 쿼리 실행
	            int rowsAffected = pstmt.executeUpdate();
	            
	            if (rowsAffected > 0) {
	                System.out.println("ID " + id + "에 해당하는 히스토리 정보 업데이트 완료");
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
	    // db 조회 
	    public ArrayList<bookmark_dao> findBookmarkInfoAll() {
	        ArrayList<bookmark_dao> daoList = new ArrayList<bookmark_dao>();
	        try {
	            if(con == null) {
	                System.out.println("데이터베이스 연결 실패");
	                return new ArrayList<bookmark_dao>(); // 연결 실패 시 메서드 종료
	            }
	            String query = "SELECT * FROM bookmark where use_yn = 1"; // 쿼리 작성
	            pstmt = con.prepareStatement(query); // 쿼리를 PreparedStatement에 설정
	            rs = pstmt.executeQuery(); // 쿼리 실행 및 결과 저장
	            // 결과 반복문을 통해 출력
	            while(rs.next()) {
	            	bookmark_dao dao = new bookmark_dao();
	            	dao.setId(rs.getInt("id"));
	            	dao.setWifi_id(rs.getInt("wifi_id"));
	            	dao.setName(rs.getString("name"));
	            	dao.setSeq(rs.getInt("seq"));
	            	dao.setWifi_name(rs.getString("wifi_name"));
	            	dao.setReg_date(rs.getTimestamp("reg_date"));
	            	dao.setUp_date(rs.getTimestamp("up_date"));
	            	dao.setUse_YN(rs.getInt("use_YN"));
	            	daoList.add(dao);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return daoList;
	    }
	    // 와이파이 매칭된 북마크 조회
	    public ArrayList<bookmark_dao> findMatchedBookmarkInfoAll() {
	        ArrayList<bookmark_dao> daoList = new ArrayList<bookmark_dao>();
	        try {
	            if(con == null) {
	                System.out.println("데이터베이스 연결 실패");
	                return new ArrayList<bookmark_dao>(); // 연결 실패 시 메서드 종료
	            }
	            String query = "SELECT * FROM bookmark where use_yn = 1 and wifi_id <> 0"; // 쿼리 작성
	            pstmt = con.prepareStatement(query); // 쿼리를 PreparedStatement에 설정
	            rs = pstmt.executeQuery(); // 쿼리 실행 및 결과 저장
	            // 결과 반복문을 통해 출력
	            while(rs.next()) {
	            	bookmark_dao dao = new bookmark_dao();
	            	dao.setId(rs.getInt("id"));
	            	dao.setWifi_id(rs.getInt("wifi_id"));
	            	dao.setName(rs.getString("name"));
	            	dao.setSeq(rs.getInt("seq"));
	            	dao.setWifi_name(rs.getString("wifi_name"));
	            	dao.setReg_date(rs.getTimestamp("reg_date"));
	            	dao.setUp_date(rs.getTimestamp("up_date"));
	            	dao.setUse_YN(rs.getInt("use_YN"));
	            	daoList.add(dao);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return daoList;
	    }
	    
	    // db 삽입 
	    public void insertBookmarkInfo(String name, int seq) {
	    	String query = "INSERT INTO bookmark (name, seq, reg_date, use_YN, wifi_id) " +
	                "VALUES (?, ?, ?, ?, ?)";
			 try {
			     // PreparedStatement 객체 생성
			     pstmt = con.prepareStatement(query);
			         pstmt.setString(1, name);
			         pstmt.setInt(2,seq);
			         pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			         pstmt.setInt(4, 1); // 사용 여부
			         pstmt.setInt(5, 0); // 사용 여부

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
	    
	    // wifi 매칭
	    public void updateWiifiInfo(int wifi_id, String wifi_name,int bookmark_id) {
	    	String updateQuery = "UPDATE bookmark SET wifi_id = ?, wifi_name = ? WHERE id = ?";
	        try {
	            // PreparedStatement 객체 생성
	            pstmt = con.prepareStatement(updateQuery);
	            pstmt.setInt(1, wifi_id);
	            pstmt.setString(2, wifi_name);
	            pstmt.setInt(3, bookmark_id);

	            // 쿼리 실행
	            int rowsAffected = pstmt.executeUpdate();
	            
	            if (rowsAffected > 0) {
	                System.out.println("ID " + bookmark_id + "에 해당하는 와이파이 정보 업데이트 완료");
	            } else {
	                System.out.println("ID " + bookmark_id + "에 해당하는 와이파이 정보가 존재하지 않습니다.");
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
	    public void deleteBookmarkyInfo(int id) {
	        String query = "update bookmark set use_yn = 0 WHERE id = ?  ";
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
