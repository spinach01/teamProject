package inventory.project.mpreturn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import www.utility.DBClose;
import www.utility.DBOpen;

/**
 * @Component : Spring이 필요시 자동으로 객체를 생성함 
 * @Autowired : Spring이 필요시 자동으로 객체를 생성하여 객체 변수에 할당함
 *
 */
@Component
public class MpreturnDAO {
  @Autowired
  private DBOpen dbopen = null;
  @Autowired
  private DBClose dbclose = null;
  
  public MpreturnDAO(){
    System.out.println("MpreturnDAO auto created...");
  }
  
  /**
   * 품목 등록.
   * @param dto
   * @return 성공 1, 실패 0
   */
 
  public int create(MpreturnDTO dto){
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    int cnt = 0;
    
    try{
      sql.append(" INSERT INTO mpreturn(returnno, retdate, retcount, productno)");
      sql.append(" VALUES((SELECT NVL(MAX(returnno), 0)+1 as returnno FROM mpreturn), ");
      sql.append(" sysdate, ?, ?)");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, dto.getRetcount());
      pstmt.setInt(2, dto.getProductno());
     
      cnt = pstmt.executeUpdate();
      
    }catch(Exception e){
      System.out.println(e.toString());
    }finally{
      dbclose.close(con, pstmt);
    }
    return cnt;
  } 
  
  /**
   * 품목 출력
   * 
   * @return
   */
  public ArrayList list() {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL 실행
    ResultSet rs = null; // SELECT 결과 저장
    StringBuffer sql = null;
    int cnt = 0;
    ArrayList list = null;

    try {
      sql = new StringBuffer();
      sql.append(" SELECT returnno, retdate, retcount, productno");
      sql.append(" FROM mpreturn");
      sql.append(" ORDER BY returnno ASC");   // asc : 오름차순 // ORDER BY id ASC

      pstmt = con.prepareStatement(sql.toString());

      rs = pstmt.executeQuery();

      list = new ArrayList();  // 저장소 선언
      while (rs.next() == true) {   // 첫번째레코드, 두번째레코드
        MpreturnDTO dto = new MpreturnDTO();
        dto.setReturnno(rs.getInt("returnno"));
        dto.setRetdate(rs.getString("retdate"));
        dto.setRetcount(rs.getInt("retcount"));
        dto.setProductno(rs.getInt("productno"));
        
        list.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return list;
  }
  

}
