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
 * @Component : Spring�� �ʿ�� �ڵ����� ��ü�� ������ 
 * @Autowired : Spring�� �ʿ�� �ڵ����� ��ü�� �����Ͽ� ��ü ������ �Ҵ���
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
   * ǰ�� ���.
   * @param dto
   * @return ���� 1, ���� 0
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
      sql.append(" SELECT importno, imdate, imcount, imprice, imclient, productno");
      sql.append(" FROM mpimport");
      sql.append(" ORDER BY importno ASC");   // asc : �������� // ORDER BY id ASC

      pstmt = con.prepareStatement(sql.toString());

      rs = pstmt.executeQuery();

      list = new ArrayList();  // ����� ����
      while (rs.next() == true) {   // ù��°���ڵ�, �ι�°���ڵ�
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
