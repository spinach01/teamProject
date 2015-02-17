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
public class DBClose {
  public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
    try {
      try {
        if (rs != null) {
          rs.close();
          rs = null;
        }
      } catch (Exception e) {
      }

      try {
        if (pstmt != null) {
          pstmt.close();
          pstmt = null;
        }
      } catch (Exception e) {
      }

      try {
        if (con != null) {
          con.close();
          con = null;
        }
      } catch (Exception e) {
      }

    } catch (Exception e) {
    }
  }

  public void close(Connection con, PreparedStatement pstmt) {
    try {
      try {
        if (pstmt != null) {
          pstmt.close();
          pstmt = null;
        }
      } catch (Exception e) {
      }

      try {
        if (con != null) {
          con.close();
          con = null;
        }
      } catch (Exception e) {
      }

    } catch (Exception e) {
    }
  }

  // 접속자가 많으면 문제 일어날 수 있으므로 static 제거(static 쓰면 synchronized를 추가함)

}