package www.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

/**
 * �Ķ���s : ���������� ��ü������ ��ü ����
 * @Component, @Controller
 *
 */

@Component
public class DBOpen {

  /**
   * �����ͺ��̽� ���� Ŀ�ؼ� ��带 ���� �մϴ�. 
   * 1: Oracle, Connection Pool ��� ���� 
   * 2: Oracle, DBCP Connection Pool ���, ��� ����, �߰� ���� �ʿ� 
   * 3: MySQL, Connection Pool ��� ���� 
   * 4: MySQL, DBCP Connection Pool ���, ��� ����, �߰� ���� �ʿ� 
   */
  public int connectionMode = 1; //3������ �ϱ�.. �ø���... ��������/cafe 24��(20140507)

  /**
   * �⺻ ������
   */
  public DBOpen() {
    super();
  }

  /**
   * ������
   */
  public DBOpen(int connectionMode) {
    this.connectionMode = connectionMode;
  }

  /**
   * DBMS�� �����Ͽ� Connection ��ü ����
   * 
   * @return
   */
  public Connection getConnection() {
    Connection con = null;
    try {
      if (connectionMode == 1) {// Oracle Pool ��� ����
        /*String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@121.160.41.249:20131:oracle11g2"; //���� cafe 24��(20140507)
        String user = "SO201402_13";  // ��¥�������� ���� cafe 24��(20140507)
        String password = "oracle123";// ��¥�н������ ���� cafe 24��(20140507)
*/
    	String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.0.188:1521:orcl"; //���� cafe 24��(20140507)
        String user = "oh";  // ��¥�������� ���� cafe 24��(20140507)
        String password = "dbsrud";// ��¥�н������ ���� cafe 24��(20140507)
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);
        
      } else if (connectionMode == 2) { // Oracle Pool ���
        String poolName = "jdbc:apache:commons:dbcp:oracle11g2";
        con = DriverManager.getConnection(poolName);
      } else if (connectionMode == 3) { // MySQL Pool ��� ����
        String driver = "org.gjt.mm.mysql.Driver";
        String url = "jdbc:mysql://localhost:3306/javadb?useUnicode=true&characterEncoding=euckr";
        String user = "javauser";
        String password = "1234";

        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);

      } else if (connectionMode == 4) { // MySQL Pool ���
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