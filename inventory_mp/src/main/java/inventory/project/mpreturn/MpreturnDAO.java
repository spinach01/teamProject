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
 * @Component : Spring�� �ʿ�� �ڵ����� ��ü�� ������ 
 * @Autowired : Spring�� �ʿ�� �ڵ����� ��ü�� �����Ͽ� ��ü ������ �Ҵ���
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
   * ǰ�� ���.
   * @param dto
   * @return ���� 1, ���� 0
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
      sql.append(" SELECT returnno, retdate, retcount, productno");
      sql.append(" FROM mpreturn");
      sql.append(" ORDER BY returnno ASC");   // asc : �������� // ORDER BY id ASC

      pstmt = con.prepareStatement(sql.toString());

      rs = pstmt.executeQuery();

      list = new ArrayList();  // ����� ����
      while (rs.next() == true) {   // ù��°���ڵ�, �ι�°���ڵ�
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
