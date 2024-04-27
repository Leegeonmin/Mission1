package javacode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class dbcon {
    String driver = "org.mariadb.jdbc.Driver";
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
 
    public dbcon() {
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
    
    public static void main(String[] args){
        dbcon dbcon    = new dbcon();
    }
}