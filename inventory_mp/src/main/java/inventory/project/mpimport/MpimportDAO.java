package inventory.project.mpimport;

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
public class MpimportDAO {
  @Autowired
  private DBOpen dbopen = null;
  @Autowired
  private DBClose dbclose = null;
  
  public MpimportDAO(){
    System.out.println("MpimportDAO auto created...");
  }
  
  /**
   * 품목 등록.
   * @param dto
   * @return 성공 1, 실패 0
   */
 
  public int create(MpimportDTO dto){
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    int cnt = 0;
    
    try{
      sql.append(" INSERT INTO mpimport(importno, imdate, imcount, imprice, imclient, productno)");
      sql.append(" VALUES((SELECT NVL(MAX(importno), 0)+1 as importno FROM mpimport), ");
      sql.append(" sysdate, ?, ?, ?, ?)");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, dto.getImcount());
      pstmt.setInt(2,  dto.getImprice());
      pstmt.setString(3, dto.getImclient());
      pstmt.setInt(4, dto.getProductno());
     
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
      sql.append(" SELECT importno, imdate, imcount, imprice, imclient, productno");
      sql.append(" FROM mpimport");
      sql.append(" ORDER BY importno ASC");   // asc : 오름차순 // ORDER BY id ASC

      pstmt = con.prepareStatement(sql.toString());

      rs = pstmt.executeQuery();

      list = new ArrayList();  // 저장소 선언
      while (rs.next() == true) {   // 첫번째레코드, 두번째레코드
        MpimportDTO dto = new MpimportDTO();
        dto.setImportno(rs.getInt("importno"));
        dto.setImdate(rs.getString("imdate"));
        dto.setImcount(rs.getInt("imcount"));
        dto.setImprice(rs.getInt("imprice"));
        dto.setImclient(rs.getString("imclient"));
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
