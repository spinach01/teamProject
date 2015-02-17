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
 * @Component : Spring�� �ʿ�� �ڵ����� ��ü�� ������ 
 * @Autowired : Spring�� �ʿ�� �ڵ����� ��ü�� �����Ͽ� ��ü ������ �Ҵ���
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
   * ǰ�� ���.
   * @param dto
   * @return ���� 1, ���� 0
   */
 
  public int create(MpgroupDTO dto){
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    int cnt = 0;
    
    try{
      sql.append(" INSERT INTO mpgroup(mpgroupno, mpgrname)");
      sql.append(" VALUES((SELECT NVL(MAX(mpgroupno), 0)+1 as mpgroupno FROM mpgroup), ?)");
      
      //System.out.println("ǰ���ȣ :" + dto.getTitle()); 
      
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
   * ǰ�� ���
   * 
   * @return
   */
  public ArrayList list() {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL ����
    ResultSet rs = null; // SELECT ��� ����
    StringBuffer sql = null;
    int cnt = 0;
    ArrayList list = null;

    try {
      sql = new StringBuffer();
      sql.append(" SELECT mpgroupno, mpgrname");
      sql.append(" FROM mpgroup");
      sql.append(" ORDER BY mpgroupno ASC");   // asc : �������� // ORDER BY id ASC

      pstmt = con.prepareStatement(sql.toString());

      rs = pstmt.executeQuery();

      list = new ArrayList();  // ����� ����
      while (rs.next() == true) {   // ù��°���ڵ�, �ι�°���ڵ�
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
   * ǰ�� ��� ��ȸ
   * 
   * @return
   */  
  public MpgroupDTO read(int mpgroupno) {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL ����
    ResultSet rs = null; // SELECT ��� ����
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

      if(rs.next() == true) {   // ù��°���ڵ�, �ι�°���ڵ�
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
   * ǰ�� ����
   * @param dto
   * @return  ���� 1, ���� 0
   */
  public int update(MpgroupDTO dto) {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL ����
    ResultSet rs = null; // SELECT ��� ����
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
   * �ڷ� ����
   * @param ������ ��ȣ
   * @return ���� ��� ���� 1, ���� 0
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
