package www.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

/**
 * 파란색s : 스프링에서 자체적으로 객체 만듬
 * @Component, @Controller
 *
 */

@Component
public class DBOpen {

  /**
   * 데이터베이스 접속 커넥션 모드를 정의 합니다. 
   * 1: Oracle, Connection Pool 사용 안함 
   * 2: Oracle, DBCP Connection Pool 사용, 고속 지원, 추가 설정 필요 
   * 3: MySQL, Connection Pool 사용 안함 
   * 4: MySQL, DBCP Connection Pool 사용, 고속 지원, 추가 설정 필요 
   */
  public int connectionMode = 1; //3번으로 하기.. 올릴때... 파일질라/cafe 24등(20140507)

  /**
   * 기본 생성자
   */
  public DBOpen() {
    super();
  }

  /**
   * 생성자
   */
  public DBOpen(int connectionMode) {
    this.connectionMode = connectionMode;
  }

  /**
   * DBMS에 연결하여 Connection 객체 리턴
   * 
   * @return
   */
  public Connection getConnection() {
    Connection con = null;
    try {
      if (connectionMode == 1) {// Oracle Pool 사용 안함
        /*String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@121.160.41.249:20131:oracle11g2"; //수정 cafe 24등(20140507)
        String user = "SO201402_13";  // 진짜계정으로 수정 cafe 24등(20140507)
        String password = "oracle123";// 진짜패스워드로 수정 cafe 24등(20140507)
*/
    	String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.0.188:1521:orcl"; //수정 cafe 24등(20140507)
        String user = "oh";  // 진짜계정으로 수정 cafe 24등(20140507)
        String password = "dbsrud";// 진짜패스워드로 수정 cafe 24등(20140507)
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);
        
      } else if (connectionMode == 2) { // Oracle Pool 사용
        String poolName = "jdbc:apache:commons:dbcp:oracle11g2";
        con = DriverManager.getConnection(poolName);
      } else if (connectionMode == 3) { // MySQL Pool 사용 안함
        String driver = "org.gjt.mm.mysql.Driver";
        String url = "jdbc:mysql://localhost:3306/javadb?useUnicode=true&characterEncoding=euckr";
        String user = "javauser";
        String password = "1234";

        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);

      } else if (connectionMode == 4) { // MySQL Pool 사용
        String poolName = "jdbc:apache:commons:dbcp:JDBC_POOL";
        con = DriverManager.getConnection(poolName);
        System.out.println("MySQL Connection Pool mode: " + con.hashCode());
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
    }
    return con;
  }

}