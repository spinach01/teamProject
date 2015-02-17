package inventory.project.mp;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import www.utility.DBClose;
import www.utility.DBOpen;
import www.utility.Utility;

@Component
public class MpproductDAO {
  @Autowired
  private DBOpen dbopen = null;
  @Autowired
  private DBClose dbclose = null;
    
  public MpproductDAO(){
    System.out.println("MediaDAO auto created...");
  }
  
  /**
   * �̵�� ���
   * 
   * @return
   */
  public ArrayList list(MpproductDTO dto) {
    // System.out.println(">>>>> mpgroupno: " + dto.getMpgroupno());
    // System.out.println(">>>>> colName: " + dto.getColName());
    
    String colName = Utility.checkNull(dto.getColName());
    String colFtype = Utility.checkNull(dto.getColFtype());
    
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL ����
    ResultSet rs = null; // SELECT ��� ����
    StringBuffer sql = null;
    int cnt = 0;
    ArrayList list = null;

    try {
      sql = new StringBuffer();
      
      if (colName.length() == 0 && colFtype.length() == 0){
        sql.append(" SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id");
        sql.append(" FROM mpproduct");
        sql.append(" WHERE mpgroupno = ?");   
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, dto.getMpgroupno()); 
        
      }/*else if(colName.length() != 0 && colFtype.length() == 0){
        sql.append(" SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id");
        sql.append(" FROM mpproduct");
        sql.append(" WHERE (mpgroupno = ?) AND (mpname=?) ");   
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, dto.getMpgroupno()); 
        pstmt.setString(2,  dto.getColName());
        //pstmt.setString(3,  dto.getColFtype());
        
      }else if(colName.length() == 0 && colFtype.length() != 0){
        sql.append(" SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id");
        sql.append(" FROM mpproduct");
        sql.append(" WHERE (mpgroupno = ?) AND (ftype=?) ");   
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, dto.getMpgroupno()); 
        //pstmt.setString(2,  dto.getColName());
        pstmt.setString(3,  dto.getColFtype());
        
      }*/else if(colName.length() != 0 && colFtype.length() != 0){
        sql.append(" SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id");
        sql.append(" FROM mpproduct");
        sql.append(" WHERE (mpgroupno = ?) AND (mpname=? AND ftype=?) ");   
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, dto.getMpgroupno()); 
        pstmt.setString(2,  dto.getColName());
        pstmt.setString(3,  dto.getColFtype());
        
      }else{
        sql.append(" SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id");
        sql.append(" FROM mpproduct");
        sql.append(" WHERE (mpgroupno = ?) AND (mpname=? OR ftype=?) ");   
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, dto.getMpgroupno()); 
        pstmt.setString(2,  dto.getColName());
        pstmt.setString(3,  dto.getColFtype());
        
      }      
      

      rs = pstmt.executeQuery();

      list = new ArrayList();  // ����� ����
      while (rs.next() == true) {   // ù��°���ڵ�, �ι�°���ڵ�
        dto = new MpproductDTO();
        dto.setProductno(rs.getInt("productno"));
        dto.setMpname(rs.getString("mpname"));
        dto.setPrice(rs.getInt("price"));
        dto.setRdate(rs.getString("rdate"));
        dto.setFname(rs.getString("fname"));
        dto.setMcount(rs.getInt("mcount"));
        dto.setContent(rs.getString("content"));
        dto.setVolume(rs.getString("volume"));
        dto.setFtype(rs.getString("ftype"));
        dto.setEtc(rs.getString("etc"));
        dto.setMpgroupno(rs.getInt("mpgroupno"));
        dto.setId(rs.getString("id"));

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
   * �̵�� ��� �˻�
   * 
   * @return
   */
  public ArrayList list(MpproductDTO dto, int nowPage, int recordPerPage) {
    // System.out.println(">>>>> mpgroupno: " + dto.getMpgroupno());
    // System.out.println(">>>>> colName: " + dto.getColName());
    
    String colName = Utility.checkNull(dto.getColName());
    String colFtype = Utility.checkNull(dto.getColFtype());
    
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL ����
    ResultSet rs = null; // SELECT ��� ����
    StringBuffer sql = null;
    int cnt = 0;
    ArrayList list = null;

    // 10: �������� ����� ���ڵ� ���� (���߿��� 15���� �ص� ��...)
    int startRow = ((nowPage - 1) * recordPerPage) + 1;   
    int endRow = nowPage * recordPerPage;         
    
    try {
      sql = new StringBuffer();
      
      if (colName.length() == 0 && colFtype.length() == 0){
        sql.append(" SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id, r");
        sql.append(" FROM(");
        sql.append("     SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id, rownum as r");
        sql.append("     FROM(");        
        sql.append("           SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id");
        sql.append("           FROM mpproduct");
        sql.append("           WHERE mpgroupno = ?");
        sql.append("           ORDER BY productno ASC");
        sql.append("     )");
        sql.append(" )");
        sql.append(" WHERE r >= "+startRow+" AND r <= "+endRow);
        
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, dto.getMpgroupno()); 
        
      }else if(colName.length() != 0 && colFtype.length() != 0){
        sql.append(" SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id, r");
        sql.append(" FROM(");
        sql.append("     SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id, rownum as r");
        sql.append("     FROM(");  
        sql.append("           SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id");
        sql.append("           FROM mpproduct");
        sql.append("           WHERE (mpgroupno = ?) AND (mpname=? AND ftype=?) "); 
        sql.append("           ORDER BY productno ASC");
        sql.append("     )");
        sql.append(" )");
        sql.append(" WHERE r >= "+startRow+" AND r <= "+endRow);
        
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, dto.getMpgroupno()); 
        pstmt.setString(2,  dto.getColName());
        pstmt.setString(3,  dto.getColFtype());
        
      }else{
        sql.append(" SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id, r");
        sql.append(" FROM(");
        sql.append("     SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id, rownum as r");
        sql.append("     FROM(");  
        sql.append("           SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id");
        sql.append("           FROM mpproduct");
        sql.append("           WHERE (mpgroupno = ?) AND (mpname=? OR ftype=?) "); 
        sql.append("           ORDER BY productno ASC");
        sql.append("     )");
        sql.append(" )");
        sql.append(" WHERE r >= "+startRow+" AND r <= "+endRow);
        
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, dto.getMpgroupno()); 
        pstmt.setString(2,  dto.getColName());
        pstmt.setString(3,  dto.getColFtype());
        
      }      
      

      rs = pstmt.executeQuery();

      list = new ArrayList();  // ����� ����
      while (rs.next() == true) {   // ù��°���ڵ�, �ι�°���ڵ�
        dto = new MpproductDTO();
        dto.setProductno(rs.getInt("productno"));
        dto.setMpname(rs.getString("mpname"));
        dto.setPrice(rs.getInt("price"));
        dto.setRdate(rs.getString("rdate"));
        dto.setFname(rs.getString("fname"));
        dto.setMcount(rs.getInt("mcount"));
        dto.setContent(rs.getString("content"));
        dto.setVolume(rs.getString("volume"));
        dto.setFtype(rs.getString("ftype"));
        dto.setEtc(rs.getString("etc"));
        dto.setMpgroupno(rs.getInt("mpgroupno"));
        dto.setId(rs.getString("id"));

        list.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return list;
  }
  
//������ ������ ��Ͽ� ����մϴ�.
 public ArrayList listTop(int count){
	// System.out.println("ddd"+count);
   Connection con = dbopen.getConnection();  // DBMS ����
   System.out.println("dddd");
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   ArrayList list = null;
   
   // 10: �������� ����� ���ڵ� ����
   int startRow = 1; 
   int endRow = count;
   
   try{
     StringBuffer sql = new StringBuffer();
     
     sql.append(" SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id, r");
     sql.append(" FROM(");
     sql.append("     SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id, rownum as r");
     sql.append("     FROM(");        
     sql.append("           SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id");
     sql.append("           FROM mpproduct");
     sql.append("           ORDER BY productno ASC");
     sql.append("     )");
     sql.append(" )");
     sql.append(" WHERE r >= "+startRow+" AND r <= "+endRow);
     
     pstmt = con.prepareStatement(sql.toString());
     rs = pstmt.executeQuery();
     
     list = new ArrayList();  // ��ü �����
     
     while(rs.next() == true){ //ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
       MpproductDTO dto = new MpproductDTO();
         
       dto.setProductno(rs.getInt("productno"));
       dto.setMpname(rs.getString("mpname"));
       dto.setPrice(rs.getInt("price"));
       dto.setRdate(rs.getString("rdate"));
       dto.setFname(rs.getString("fname"));
       dto.setMcount(rs.getInt("mcount"));
       dto.setContent(rs.getString("content"));
       dto.setVolume(rs.getString("volume"));
       dto.setFtype(rs.getString("ftype"));
       dto.setEtc(rs.getString("etc"));
       dto.setMpgroupno(rs.getInt("mpgroupno"));
       dto.setId(rs.getString("id"));
       
       list.add(dto);
     }
     
   }catch(Exception e){
     System.out.println(e.toString());
   }finally{
     dbclose.close(con, pstmt, rs);
   }

   return list;
 }
  
  
/**
 * ��ǰ�� �ߺ� ����
 * @return
 */
  public ArrayList distictNamelist() {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL ����
    ResultSet rs = null; // SELECT ��� ����
    StringBuffer sql = null;
    int cnt = 0;
    ArrayList list = null;

    try {
      sql = new StringBuffer();
      sql.append(" SELECT DISTINCT mpname ");
      sql.append(" FROM mpproduct");

      pstmt = con.prepareStatement(sql.toString());

      rs = pstmt.executeQuery();

      list = new ArrayList();  // ����� ����
      while (rs.next() == true) {   // ù��°���ڵ�, �ι�°���ڵ�
        MpproductDTO dto = new MpproductDTO();
        dto.setMpname(rs.getString("mpname"));

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
   * ��ǰ�Ǻ�Ÿ�� �ߺ� ����
   * @return
   */
    public ArrayList distictFtylelist() {
      Connection con = dbopen.getConnection();
      PreparedStatement pstmt = null; // SQL ����
      ResultSet rs = null; // SELECT ��� ����
      StringBuffer sql = null;
      int cnt = 0;
      ArrayList list = null;

      try {
        sql = new StringBuffer();
        sql.append(" SELECT DISTINCT ftype ");
        sql.append(" FROM mpproduct");

        pstmt = con.prepareStatement(sql.toString());

        rs = pstmt.executeQuery();

        list = new ArrayList();  // ����� ����
        while (rs.next() == true) {   // ù��°���ڵ�, �ι�°���ڵ�
          MpproductDTO dto = new MpproductDTO();
          dto.setFtype(rs.getString("ftype"));

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
   * �˻��� ���ڵ� ������ ����
   * @param col  �˻� �÷�
   * @param word �˻���
   * @return �˻��� ���ڵ� ����
   */
  public int count(MpproductDTO dto){ 
    Connection con = dbopen.getConnection();  // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    int cnt = 0;
    
    try{
      StringBuffer sql= new StringBuffer();
      
      String colName = Utility.checkNull(dto.getColName());
      String colFtype = Utility.checkNull(dto.getColFtype());      
      
      if (colName.length() == 0 && colFtype.length() == 0){
        sql.append(" SELECT COUNT(*) as cnt");
        sql.append(" FROM mpproduct ");
        sql.append(" WHERE mpgroupno = ?");
        
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, dto.getMpgroupno()); 
        
      }else if(colName.length() != 0 && colFtype.length() != 0){
        sql.append(" SELECT COUNT(*) as cnt");
        sql.append(" FROM mpproduct ");
        sql.append(" WHERE (mpgroupno = ?) AND (mpname=? AND ftype=?) "); 
        
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, dto.getMpgroupno()); 
        pstmt.setString(2,  dto.getColName());
        pstmt.setString(3,  dto.getColFtype());
        
      }else{
        sql.append(" SELECT COUNT(*) as cnt");
        sql.append(" FROM mpproduct ");
        sql.append(" WHERE (mpgroupno = ?) AND (mpname=? OR ftype=?) "); 
        
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, dto.getMpgroupno()); 
        pstmt.setString(2,  dto.getColName());
        pstmt.setString(3,  dto.getColFtype());
        
      }  
     
      rs = pstmt.executeQuery();      
            
      if(rs.next() == true){  // ù��° ���ڵ�� �̵�. �������� �������ڵ�� �̵�
        cnt = rs.getInt("cnt");     
      } 
      
    }catch(Exception e){
      System.out.println(e.toString());     
    }finally{
      dbclose.close(con, pstmt, rs);    
    }
    return cnt; 
  } 
  
  /**
   * ��ǰ ���
   * 
   * @return
   */
  public int create(MpproductDTO dto) {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL ����
    ResultSet rs = null; // SELECT ��� ����
    StringBuffer sql = null;
    int cnt = 0;
    ArrayList list = null;

    try {
      sql = new StringBuffer();
      sql.append(" INSERT INTO mpproduct(productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)");
      sql.append(" VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), ");
      sql.append(" ?, ?, sysdate, ?, ?, ?, ?, ?, ?, ?, ?)");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, dto.getMpname());
      pstmt.setInt(2, dto.getPrice());
      pstmt.setString(3, dto.getFname());
      pstmt.setInt(4, dto.getMcount());
      pstmt.setString(5, dto.getContent());
      pstmt.setString(6, dto.getVolume());
      pstmt.setString(7, dto.getFtype());
      pstmt.setString(8, dto.getEtc());
      pstmt.setInt(9, dto.getMpgroupno());
      pstmt.setString(10, dto.getId());
      
      cnt = pstmt.executeUpdate();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return cnt;
  }
  
  /**
   * ��ǰ��ȸ
   * 
   * @return
   */
  public MpproductDTO read(int productno) {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL ����
    ResultSet rs = null; // SELECT ��� ����
    StringBuffer sql = null;
    int cnt = 0;
    MpproductDTO dto = null;

    try {
      sql = new StringBuffer();
      sql.append(" SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id");
      sql.append(" FROM mpproduct");
      sql.append(" WHERE productno = ?");   // asc : �������� // ORDER BY id ASC

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, productno);
      rs = pstmt.executeQuery();

      while (rs.next() == true) {   // ù��°���ڵ�, �ι�°���ڵ�
        dto = new MpproductDTO();
        dto.setProductno(rs.getInt("productno"));
        dto.setMpname(rs.getString("mpname"));
        dto.setPrice(rs.getInt("price"));
        dto.setRdate(rs.getString("rdate"));
        dto.setFname(rs.getString("fname"));
        dto.setMcount(rs.getInt("mcount"));
        dto.setContent(rs.getString("content"));
        dto.setVolume(rs.getString("volume"));
        dto.setFtype(rs.getString("ftype"));
        dto.setEtc(rs.getString("etc"));
        dto.setMpgroupno(rs.getInt("mpgroupno"));
        dto.setId(rs.getString("id"));

      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return dto;
  }
  
  /**
   * ��ǰ ����
   * 
   * @return
   */
  public int update(MpproductDTO dto) {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL ����
    ResultSet rs = null; // SELECT ��� ����
    StringBuffer sql = null;
    int cnt = 0;
    ArrayList list = null;

    try {
      sql = new StringBuffer();
      sql.append(" UPDATE mpproduct");
      sql.append(" SET mpname=?, fname=?, price=?, volume=?, ftype=?, content=?, mcount=?");
      sql.append(" WHERE productno=?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, dto.getMpname());
      pstmt.setString(2, dto.getFname());
      pstmt.setInt(3, dto.getPrice());
      pstmt.setString(4, dto.getVolume());
      pstmt.setString(5, dto.getFtype());
      pstmt.setString(6, dto.getContent());
      pstmt.setInt(7, dto.getMcount());
      pstmt.setInt(8, dto.getProductno());
      
      cnt = pstmt.executeUpdate();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return cnt;
  }
  
  /**
   * ��ǰ ����
   * 
   * @return
   */
  public int delete(int productno) {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL ����
    ResultSet rs = null; // SELECT ��� ����
    StringBuffer sql = null;
    int cnt = 0;

    try {
      sql = new StringBuffer();
      sql.append(" DELETE FROM mpproduct");
      sql.append(" WHERE productno=?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, productno);
      
      cnt = pstmt.executeUpdate();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return cnt;
  }
  
}
 

