package inventory.project.group;

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
public class MpgroupDAO {
  @Autowired
  private DBOpen dbopen = null;
  @Autowired
  private DBClose dbclose = null;
  
  public MpgroupDAO(){
    System.out.println("MpgropDAO auto created...");
  }
  
  /**
   * 품목 등록.
   * @param dto
   * @return 성공 1, 실패 0
   */
 
  public int create(MpgroupDTO dto){
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    int cnt = 0;
    
    try{
      sql.append(" INSERT INTO mpgroup(mpgroupno, mpgrname)");
      sql.append(" VALUES((SELECT NVL(MAX(mpgroupno), 0)+1 as mpgroupno FROM mpgroup), ?)");
      
      //System.out.println("품목번호 :" + dto.getTitle()); 
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, dto.getMpgrname());

     
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
      sql.append(" SELECT mpgroupno, mpgrname");
      sql.append(" FROM mpgroup");
      sql.append(" ORDER BY mpgroupno ASC");   // asc : 오름차순 // ORDER BY id ASC

      pstmt = con.prepareStatement(sql.toString());

      rs = pstmt.executeQuery();

      list = new ArrayList();  // 저장소 선언
      while (rs.next() == true) {   // 첫번째레코드, 두번째레코드
        MpgroupDTO dto = new MpgroupDTO();
        dto.setMpgroupno(rs.getInt("mpgroupno"));
        dto.setMpgrname(rs.getString("mpgrname"));
        
        list.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return list;
  }

  /**
   * 품목 목록 조회
   * 
   * @return
   */  
  public MpgroupDTO read(int mpgroupno) {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL 실행
    ResultSet rs = null; // SELECT 결과 저장
    StringBuffer sql = null;
    int cnt = 0;
    MpgroupDTO dto = null;

    try {
      sql = new StringBuffer();
      sql.append(" SELECT mpgroupno, mpgrname");
      sql.append(" FROM mpgroup");
      sql.append(" WHERE mpgroupno=?");  

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, mpgroupno);
      
      rs = pstmt.executeQuery();

      if(rs.next() == true) {   // 첫번째레코드, 두번째레코드
        dto = new MpgroupDTO();
        dto.setMpgroupno(rs.getInt("mpgroupno"));
        dto.setMpgrname(rs.getString("mpgrname"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return dto;
  }
  
  /**
   * 품목 수정
   * @param dto
   * @return  성공 1, 실패 0
   */
  public int update(MpgroupDTO dto) {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL 실행
    ResultSet rs = null; // SELECT 결과 저장
    StringBuffer sql = null;
    int cnt = 0;

    try {
      sql = new StringBuffer();
      sql.append(" UPDATE mpgroup");
      sql.append(" SET mpgrname=?");
      sql.append(" WHERE mpgroupno=?");

      pstmt = con.prepareStatement(sql.toString());

      pstmt.setString(1, dto.getMpgrname());
      pstmt.setInt(2, dto.getMpgroupno());

      cnt = pstmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }

    return cnt;
  }  
  
  /**
   * 자료 삭제
   * @param 삭제할 번호
   * @return 삭제 결과 성공 1, 실패 0
   */
  public int delete(int mpgroupno){
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    int cnt = 0;
    
    try{
      sql.append(" DELETE FROM mpgroup");
      sql.append(" WHERE mpgroupno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, mpgroupno);
     
      cnt = pstmt.executeUpdate();
      
    }catch(Exception e){
      System.out.println(e.toString());
    }finally{
      dbclose.close(con, pstmt);
    }
    return cnt;
  } 
  
}
