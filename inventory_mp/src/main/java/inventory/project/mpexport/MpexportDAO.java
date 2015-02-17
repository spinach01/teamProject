package inventory.project.mpexport;

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
public class MpexportDAO {
  @Autowired
  private DBOpen dbopen = null;
  @Autowired
  private DBClose dbclose = null;
  
  public MpexportDAO(){
    System.out.println("MpexportDAO auto created...");
  }
  
  /**
   * 품목 등록.
   * @param dto
   * @return 성공 1, 실패 0
   */
 
  public int create(MpexportDTO dto){
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    int cnt = 0;
    
    try{
      sql.append(" INSERT INTO mpexport(exportno, exdate, excount, exprice, excustumer, productno)");
      sql.append(" VALUES((SELECT NVL(MAX(exportno), 0)+1 as exportno FROM mpexport), ");
      sql.append(" sysdate, ?, ?, ?, ?)");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, dto.getExcount());
      pstmt.setInt(2,  dto.getExprice());
      pstmt.setString(3, dto.getExcustumer());
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
      sql.append(" SELECT exportno, exdate, excount, exprice, excustumer, productno");
      sql.append(" FROM mpexport");
      sql.append(" ORDER BY exportno ASC");   // asc : 오름차순 // ORDER BY id ASC

      pstmt = con.prepareStatement(sql.toString());

      rs = pstmt.executeQuery();

      list = new ArrayList();  // 저장소 선언
      while (rs.next() == true) {   // 첫번째레코드, 두번째레코드
        MpexportDTO dto = new MpexportDTO();
        dto.setExportno(rs.getInt("exportno"));
        dto.setExdate(rs.getString("exdate"));
        dto.setExcount(rs.getInt("excount"));
        dto.setExprice(rs.getInt("exprice"));
        dto.setExcustumer(rs.getString("excustumer"));
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
