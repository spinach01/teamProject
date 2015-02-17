package www.makeup_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;

import www.utility.DBClose;
import www.utility.DBOpen;

public class MpadminDAO {
  DBOpen dbopen = null; // �ʵ�
  DBClose dbclose = null;
  
  /**
   * ������, ���ϰ��� ����
   */
  public MpadminDAO() {
    dbopen = new DBOpen();
    dbclose = new DBClose();
  }
  
 
  /**
   * �ߺ� ���̵� �˻��մϴ�.
   * @param id ���̵�
   * @return 1: �ߺ�, 0: �ߺ��ƴ�
   */
  public int duplicateId(String id){
    Connection con = dbopen.getConnection();  // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int cnt = 0;
    
    try{
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT COUNT(id) as cnt");
      sql.append(" FROM mpadmin ");
      sql.append(" WHERE id=?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, id);
      
      rs = pstmt.executeQuery();
      
      if(rs.next() == true){ //ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
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
   * �̸����� �˻��մϴ�.
   * @param email �̸���
   * @return 1: �ߺ�, 0: �ߺ��ƴ�
   */
  public int duplicateEmail(String email){
    Connection con = dbopen.getConnection();  // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int cnt = 0;
    
    try{
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT COUNT(email) as cnt");
      sql.append(" FROM mpadmin ");
      sql.append(" WHERE email=?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, email);
      
      rs = pstmt.executeQuery();
      
      if(rs.next() == true){ //ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
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
   * �����ȣ ��� ���
   * 
   * @return
   */
  public ArrayList zipcodeList(String dongli) {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL ����
    ResultSet rs = null; // SELECT ��� ����
    StringBuffer sql = null;
    int cnt = 0;
    ArrayList list = null;

    try {
      sql = new StringBuffer();
      sql.append(" SELECT zipcode, sido, gugun, dongli, etc");
      sql.append(" FROM zipcode");
      sql.append(" WHERE (dongli LIKE '%' || ? || '%')");
      sql.append(" ORDER BY sido, gugun, dongli");
      
      // ASC �������� ������, �����Ǿ� ����
      // sql.append(" ORDER BY sido ASC, gugun ASC, dongli ASC");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, dongli);

      rs = pstmt.executeQuery();

      list = new ArrayList();
      while (rs.next() == true) {
        ZipcodeDTO dto = new ZipcodeDTO();
        // zipcode, sido, gugun, dongli, etc
        dto.setZipcode(rs.getString("zipcode"));
        dto.setSido(rs.getString("sido"));
        dto.setGugun(rs.getString("gugun"));
        dto.setDongli(rs.getString("dongli"));
        dto.setEtc(rs.getString("etc"));

        list.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace(); // ���������� �ڼ��ϰ� ��µǳ� ��ŷ�� ���
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return list;
  }

  /**
   * ȸ���� ���Դϴ�.
   * @param dto
   * @return ���� 1, ���� 0
   */
  public int create(MpadminDTO dto){
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    int cnt = 0;
    
    try{
      sql.append(" INSERT INTO mpadmin(id, passwd, mname, tel, email, zipcode, ");
      sql.append(" address1, address2, position, mpdate, fname)");
      sql.append(" VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?)");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, dto.getId());
      pstmt.setString(2, dto.getPasswd());
      pstmt.setString(3, dto.getMname());
      pstmt.setString(4, dto.getTel());
      pstmt.setString(5, dto.getEmail());
      pstmt.setString(6, dto.getZipcode());
      pstmt.setString(7, dto.getAddress1());
      pstmt.setString(8, dto.getAddress2());
      pstmt.setString(9, dto.getPosition());
      pstmt.setString(10, dto.getFname());
     
      cnt = pstmt.executeUpdate();
      
    }catch(Exception e){
      System.out.println(e.toString());
    }finally{
      dbclose.close(con, pstmt);
    }
    return cnt;
  }

  /**
   * ȸ�� ��� ���
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
      sql.append(" SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mpdate, fname");
      sql.append(" FROM mpadmin");
      sql.append(" ORDER BY mpdate DESC"); // ASC: ���� ���� 

      pstmt = con.prepareStatement(sql.toString());

      rs = pstmt.executeQuery();

      list = new ArrayList();
      while (rs.next() == true) { // ù ��° ���ڵ�, �ι�° ���ڵ�
        MpadminDTO dto = new MpadminDTO();
        dto.setId(rs.getString("id"));
        dto.setPasswd(rs.getString("passwd"));
        dto.setMname(rs.getString("mname"));
        dto.setTel(rs.getString("tel"));
        dto.setEmail(rs.getString("email"));
        dto.setZipcode(rs.getString("zipcode"));
        dto.setAddress1(rs.getString("address1"));
        dto.setAddress2(rs.getString("address2"));
        dto.setPosition(rs.getString("position"));
        dto.setMpdate(rs.getString("mpdate"));
        dto.setFname(rs.getString("fname"));

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
   * ȸ������ �ڵ带 �޾Ƽ� �ش��ϴ� ��(���̺�)�� ����
   * @param key
   * @return
   */
  public String getCodeValue(String key) {
    Hashtable codes = new Hashtable();
    codes.put("A01", "���");
    codes.put("A03", "�븮");
    codes.put("A05", "����");
    codes.put("A07", "����");
    codes.put("A09", "����");
    codes.put("A11", "��");
    codes.put("A13", "����");
    codes.put("A15", "�λ���");
    codes.put("A17", "����");

    
    Object value = codes.get(key); // A01 ~ A99Ű�� �ش��ϴ� �� ����

    return (String)(value); // �ڵ尪�� �ش��ϴ� ���� ����
  }

  /**
   * ȸ�� �� ����
   * 
   * @return
   */
  public MpadminDTO read(String id) {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL ����
    ResultSet rs = null; // SELECT ��� ����
    StringBuffer sql = null;
    int cnt = 0;
    ArrayList list = null;
    MpadminDTO dto = null;

    try {
      sql = new StringBuffer();
      sql.append(" SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mpdate, fname");
      sql.append(" FROM mpadmin");
      sql.append(" WHERE id=?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, id);

      rs = pstmt.executeQuery();
      rs.next();

      dto = new MpadminDTO();

      dto.setId(rs.getString("id"));
      dto.setPasswd(rs.getString("passwd"));
      dto.setMname(rs.getString("mname"));
      dto.setTel(rs.getString("tel"));
      dto.setEmail(rs.getString("email"));
      dto.setZipcode(rs.getString("zipcode"));
      dto.setAddress1(rs.getString("address1"));
      dto.setAddress2(rs.getString("address2"));
      dto.setPosition(rs.getString("position"));
      dto.setMpdate(rs.getString("mpdate"));
      dto.setFname(rs.getString("fname"));

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return dto;
  }

  /**
   * ȸ�� ������ �����մϴ�.
   * @param id ȸ�� ���̵�
   * @param fname ���ϸ�
   * @return ����� ���ڵ� �� 1, 0
   */
  public int updateFile(String id, String fname){
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    int cnt = 0;
    
    try{
      sql.append(" UPDATE mpadmin");
      sql.append(" SET fname = ?");
      sql.append(" WHERE id = ?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, fname);
      pstmt.setString(2, id);
     
      cnt = pstmt.executeUpdate();
      
    }catch(Exception e){
      System.out.println(e.toString());
    }finally{
      dbclose.close(con, pstmt);
    }
    return cnt;
  }

  /**
   * ȸ�� ���� ����
   * @param dto
   * @return ���Ե� ȸ���� ��, 1 �Ǵ� 0
   */
  public int update(MpadminDTO dto) {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL ����
    ResultSet rs = null; // SELECT ��� ����
    StringBuffer sql = null;
    int cnt = 0;

    try {
      sql = new StringBuffer();
      sql.append(" UPDATE mpadmin");
      sql.append(" SET passwd=?, mname=?, tel=?, email=?, zipcode=?,");
      sql.append("     address1=?, address2=?, position=?");
      sql.append(" WHERE id=?");

      pstmt = con.prepareStatement(sql.toString());

      pstmt.setString(1, dto.getPasswd());
      pstmt.setString(2, dto.getMname());
      pstmt.setString(3, dto.getTel());
      pstmt.setString(4,  dto.getEmail());
      pstmt.setString(5, dto.getZipcode());
      pstmt.setString(6, dto.getAddress1());
      pstmt.setString(7, dto.getAddress2());
      pstmt.setString(8, dto.getPosition());
      pstmt.setString(9, dto.getId());

      cnt = pstmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }

    return cnt;
  }

  /**
   * ȸ���� �����մϴ�.
   * @param id ������ ȸ�� ���̵�
   * @return ���� ��� 1, 0
   */
  public int delete(String id){
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    int cnt = 0;
    
    try{
      sql.append(" DELETE FROM mpadmin");
      sql.append(" WHERE id = ?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, id);
           
      cnt = pstmt.executeUpdate();
      
    }catch(Exception e){
      System.out.println(e.toString());
    }finally{
      dbclose.close(con, pstmt);
    }
    return cnt;
  }
  
  /**
   * �α��� ó��
   * @param id ���̵�
   * @param passwd �н�����
   * @return 1: ��ġ, 0: ��ġ���� ����
   */
  public int loginCheck(String id, String passwd){
    Connection con = dbopen.getConnection();  // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int cnt = 0;
    
    try{
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT COUNT(id) as cnt");
      sql.append(" FROM mpadmin ");
      sql.append(" WHERE id=? AND passwd=?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, id);
      pstmt.setString(2, passwd);
      
      rs = pstmt.executeQuery();
      
      if(rs.next() == true){ //ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
        cnt = rs.getInt("cnt");
      }
      
    }catch(Exception e){
      System.out.println(e.toString());
    }finally{
      dbclose.close(con, pstmt, rs);
    }

    return cnt;
  }
}