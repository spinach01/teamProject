package inventory.project.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import www.utility.DBClose;
import www.utility.DBOpen;

public class ClientdataDAO {
  DBOpen dbopen = null; // �ʵ�
  DBClose dbclose = null;

  /**
   * ������, ���ϰ��� ����
   */
  public ClientdataDAO() {
    dbopen = new DBOpen();
    dbclose = new DBClose();
  }
  /**
   * ���ڵ� ���, ����� ���ڵ� ���� ����: 0 , 1
   * 
   * @param dto
   *          ������ ��ü
   * @return ����� ���ڵ� ��
   */
  public int create(ClientdataDTO dto) {
    Connection con = dbopen.getConnection(); // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList list = null;
    int cnt = 0;

    try {
      StringBuffer sql = new StringBuffer();
      sql.append(" INSERT INTO clientdata(cdatano, wname, title, fname, filesize, content, passwd, mviewcnt, rdate, grpno, indent, ansnum)");
      sql.append(" VALUES((SELECT NVL(MAX(cdatano), 0) + 1 as cdatano FROM clientdata),");
      sql.append(" ?, ?, ?, ?, ?, ?, 0, sysdate, (SELECT NVL(MAX(cdatano), 0) + 1 as cdatano FROM clientdata), 0, 0)");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, dto.getWname());
      pstmt.setString(2, dto.getTitle());
      pstmt.setString(3, dto.getFname());
      pstmt.setLong(4, dto.getFilesize());
      pstmt.setString(5, dto.getContent());
      pstmt.setString(6, dto.getPasswd());

      

      cnt = pstmt.executeUpdate();
    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      dbclose.close(con, pstmt);
    }

    return cnt;
    }

  
  /**
   * ���
   * 
   * @return ���ڵ带 DTO�� ��ȯ�� ����
   */
  public ArrayList list() {
    Connection con = dbopen.getConnection(); // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList list = null;

    try {
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT cdatano, wname, title, fname, filesize, content, passwd, mviewcnt, rdate, grpno, indent, ansnum");
      sql.append(" FROM clientdata ");
      sql.append(" ORDER BY cdatano DESC, ansnum ASC");
      
      pstmt = con.prepareStatement(sql.toString());
      rs = pstmt.executeQuery();
      list = new ArrayList(); // ��ü �����

      while (rs.next() == true) { // ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
        ClientdataDTO dto = new ClientdataDTO();

        dto.setCdatano(rs.getInt("cdatano"));
        dto.setWname(rs.getString("wname"));
        dto.setTitle(rs.getString("title"));
        dto.setFname(rs.getString("fname"));
        dto.setFilesize(rs.getLong("filesize"));
        dto.setContent(rs.getString("content"));
        dto.setPasswd(rs.getString("passwd"));
        dto.setMviewcnt(rs.getInt("mviewcnt"));
        dto.setRdate(rs.getString("rdate"));
        dto.setGrpno(rs.getInt("grpno"));
        dto.setIndent(rs.getInt("indent"));
        dto.setAnsnum(rs.getInt("ansnum"));

        list.add(dto); // ������� �ϳ��� ��ü�� ����ҿ� �߰�
      }

    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return list;
  }

  /**
   * ����� �˻��մϴ�.
   * @param col �˻� �÷�
   * @param word �˻���
   * @return BbsDTO.java�� ����� ��ü ArrayList
   */
  public ArrayList list(String col, String word) {
    Connection con = dbopen.getConnection(); // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList list = null;

    try {
      StringBuffer sql = new StringBuffer();
      
      word = word.trim(); // ���ڿ� �¿� ���� ����
      
      if (word.length() == 0){ // �˻��� ���ϴ� ���
        sql.append(" SELECT cdatano, wname, title, fname, filesize, content, passwd, mviewcnt, rdate, grpno, indent, ansnum");
        sql.append(" FROM clientdata ");
        sql.append(" ORDER BY cdatano DESC, ansnum ASC");
        pstmt = con.prepareStatement(sql.toString());
        
      }else{ // �˻��� �ϴ� ���
        sql.append(" SELECT cdatano, wname, title, fname, filesize, content, passwd, mviewcnt, rdate, grpno, indent, ansnum");
        sql.append(" FROM clientdata ");
        if (col.equals("wname")){
          sql.append(" WHERE wname LIKE '%' || ? || '%'");
        }else if (col.equals("title")){
          sql.append(" WHERE title LIKE '%' || ? || '%'");
        }else if (col.equals("content")){
          sql.append(" WHERE content LIKE '%' || ? || '%'");
        }
        sql.append(" ORDER BY cdatano DESC, ansnum ASC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setString(1, word);
      }
      
      rs = pstmt.executeQuery();
      list = new ArrayList(); // ��ü �����

      while (rs.next() == true) { // ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
        ClientdataDTO dto = new ClientdataDTO();

        dto.setCdatano(rs.getInt("cdatano"));
        dto.setWname(rs.getString("wname"));
        dto.setTitle(rs.getString("title"));
        dto.setFname(rs.getString("fname"));
        dto.setFilesize(rs.getLong("filesize"));
        dto.setContent(rs.getString("content"));
        dto.setPasswd(rs.getString("passwd"));
        dto.setMviewcnt(rs.getInt("mviewcnt"));
        dto.setRdate(rs.getString("rdate"));
        dto.setGrpno(rs.getInt("grpno"));
        dto.setIndent(rs.getInt("indent"));
        dto.setAnsnum(rs.getInt("ansnum"));
        
        list.add(dto); // ������� �ϳ��� ��ü�� ����ҿ� �߰�
      }

    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return list;
  }

  /**
   * ����� �˻��ϰ� �������� �����Ͽ� ���� �մϴ�. nowPage�� 1���� ����
   * <pre>
   * ����(SQL) ��� ����, �Ʒ��� ������ �ٲ�� �ȵ�.
   *   SELECT
   *   FROM
   *   WHERE
   *   ORDER BY
   * 
   * </pre>
   * @param col �˻� �÷�
   * @param word �˻���
   * @param nowPage ���� ������
   * @param recordPerPage �������� ���ڵ� ����
   * @return BbsDTO.java�� ����� ��ü ArrayList
   */
  public ArrayList list(String col, String word, int nowPage, int recordPerPage) {
    Connection con = dbopen.getConnection(); // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList list = null;

    // 10: �������� ����� ���ڵ� ����
    int startRow = ((nowPage-1) * recordPerPage) + 1; // (0 * 10) + 1 = 1, 11, 21
    int endRow = nowPage * recordPerPage;             // 1 * 10 = 10, 20, 30
    
    /*
     1 page: WHERE r >= 1 AND r <= 10;
     2 page: WHERE r >= 11 AND r <= 20;
     3 page: WHERE r >= 21 AND r <= 30;
     */
    
    
    try {
      StringBuffer sql = new StringBuffer();
      
      word = word.trim(); // ���ڿ� �¿� ���� ����
      
      if (word.length() == 0){ // �˻��� ���ϴ� ���
        sql.append(" SELECT cdatano, wname, title, fname, filesize, content, passwd, mviewcnt, rdate, grpno, indent, ansnum, r");
        sql.append(" FROM(");
        sql.append("      SELECT cdatano, wname, title, fname, filesize, content, passwd, mviewcnt, rdate, grpno, indent, ansnum, rownum as r");
        sql.append("      FROM (");
        sql.append("           SELECT cdatano, wname, title, fname, filesize, content, passwd, mviewcnt, rdate, grpno, indent, ansnum");
        sql.append("           FROM clientdata ");
        sql.append("           ORDER BY grpno DESC, ansnum ASC");
        sql.append("      )");
        sql.append(" )     ");
        sql.append(" WHERE r >= "+startRow+" AND r <= "+endRow);
        
        pstmt = con.prepareStatement(sql.toString());
        
      }else{ // �˻��� �ϴ� ���
        sql.append(" SELECT cdatano, wname, title, fname, filesize, content, passwd, mviewcnt, rdate, grpno, indent, ansnum, r");
        sql.append(" FROM(");
        sql.append("      SELECT cdatano, wname, title, fname, filesize, content, passwd, mviewcnt, rdate, grpno, indent, ansnum, rownum as r");
        sql.append("      FROM (");
        sql.append("           SELECT cdatano, wname, title, fname, filesize, content, passwd, mviewcnt, rdate, grpno, indent, ansnum");
        sql.append("           FROM clientdata ");
        if (col.equals("wname")){
          sql.append("         WHERE wname LIKE '%' || ? || '%'");
        }else if (col.equals("title")){
          sql.append("         WHERE title LIKE '%' || ? || '%'");
        }else if (col.equals("content")){
          sql.append("         WHERE content LIKE '%' || ? || '%'");
        }
        sql.append("           ORDER BY grpno DESC, ansnum ASC");
        sql.append("      )");
        sql.append(" )     ");
        sql.append(" WHERE r >= "+startRow+" AND r <= "+endRow);
        
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setString(1, word);
      }
      
      rs = pstmt.executeQuery();
      list = new ArrayList(); // ��ü �����

      while (rs.next() == true) { // ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
        ClientdataDTO dto = new ClientdataDTO();

        dto.setCdatano(rs.getInt("cdatano"));
        dto.setWname(rs.getString("wname"));
        dto.setTitle(rs.getString("title"));
        dto.setFname(rs.getString("fname"));
        dto.setFilesize(rs.getLong("filesize"));
        dto.setContent(rs.getString("content"));
        dto.setPasswd(rs.getString("passwd"));
        dto.setMviewcnt(rs.getInt("mviewcnt"));
        dto.setRdate(rs.getString("rdate"));
        dto.setGrpno(rs.getInt("grpno"));
        dto.setIndent(rs.getInt("indent"));
        dto.setAnsnum(rs.getInt("ansnum"));

        list.add(dto); // ������� �ϳ��� ��ü�� ����ҿ� �߰�
      }

    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return list;
  }
  /**
   * �˻��� ���ڵ� ������ ���� 
   * @param col �˻� �÷�
   * @param word �˻���
   * @return �˻��� ���ڵ� ����
   */
  public int count(String col, String word) {
    Connection con = dbopen.getConnection(); // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int cnt = 0;
    
    try {
      StringBuffer sql = new StringBuffer();
      
      word = word.trim(); // ���ڿ� �¿� ���� ����
      
      if (word.length() == 0){ // �˻��� ���ϴ� ���
        sql.append(" SELECT COUNT(*) as cnt");
        sql.append(" FROM clientdata ");
        pstmt = con.prepareStatement(sql.toString());
        
      }else{ // �˻��� �ϴ� ���
        sql.append(" SELECT COUNT(*) as cnt");
        sql.append(" FROM clientdata ");
        if (col.equals("wname")){
          sql.append(" WHERE wname LIKE '%' || ? || '%'");
        }else if (col.equals("title")){
          sql.append(" WHERE title LIKE '%' || ? || '%'");
        }else if (col.equals("content")){
          sql.append(" WHERE content LIKE '%' || ? || '%'");
        }
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setString(1, word);
      }
      
      rs = pstmt.executeQuery();

      if (rs.next() == true) { // ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
        cnt = rs.getInt("cnt");
      }

    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return cnt;
  }
  
  /**
   * �Ҵ�� ���ڵ��� ������ �����մϴ�.
   * @param count ����
   * @return BbsDTO.java�� ����� ��ü ArrayList
   */
  public ArrayList list(int count) {
    Connection con = dbopen.getConnection(); // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList list = null;

    // 10: �������� ����� ���ڵ� ����
    int startRow = 1; 
    int endRow = count;
    
    try {
      StringBuffer sql = new StringBuffer();
    
      sql.append(" SELECT cdatano, wname, title, fname, filesize, content, passwd, mviewcnt, rdate, grpno, indent, ansnum, r");
      sql.append(" FROM(");
      sql.append("      SELECT cdatano, wname, title, fname, filesize, content, passwd, mviewcnt, rdate, grpno, indent, ansnum, rownum as r");
      sql.append("      FROM (");
      sql.append("           SELECT cdatano, wname, title, fname, filesize, content, passwd, mviewcnt, rdate, grpno, indent, ansnum");
      sql.append("           FROM clientdata ");
      sql.append("           ORDER BY cdatano DESC");
      sql.append("      )");
      sql.append(" )     ");
      sql.append(" WHERE r >= "+startRow+" AND r <= "+endRow);
        
      pstmt = con.prepareStatement(sql.toString());
        
      rs = pstmt.executeQuery();
      list = new ArrayList(); // ��ü �����

      while (rs.next() == true) { // ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
        ClientdataDTO dto = new ClientdataDTO();

        dto.setCdatano(rs.getInt("cdatano"));
        dto.setWname(rs.getString("wname"));
        dto.setTitle(rs.getString("title"));
        dto.setFname(rs.getString("fname"));
        dto.setFilesize(rs.getLong("filesize"));
        dto.setContent(rs.getString("content"));
        dto.setPasswd(rs.getString("passwd"));
        dto.setMviewcnt(rs.getInt("mviewcnt"));
        dto.setRdate(rs.getString("rdate"));
        dto.setGrpno(rs.getInt("grpno"));
        dto.setIndent(rs.getInt("indent"));
        dto.setAnsnum(rs.getInt("ansnum"));

        list.add(dto); // ������� �ϳ��� ��ü�� ����ҿ� �߰�
      }

    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return list;
  }     
  
  
  /**
   * ��ȸ�� ����
   * @param bbsno ��ȸ���� ������ų �� ��ȣ
   */
  public void viewcntAdd(int cdatano){
    Connection con = dbopen.getConnection();  // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList list = null;
    int cnt = 0;
    
    try{
      StringBuffer sql = new StringBuffer();
      sql.append(" UPDATE clientdata");
      sql.append(" SET mviewcnt = mviewcnt + 1");
      sql.append(" WHERE cdatano=?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, cdatano);

      pstmt.executeUpdate();
    }catch(Exception e){
      System.out.println(e.toString());
    }finally{
      dbclose.close(con, pstmt);
    }
    
  }  

  /**
   * �Ѱ��� ���� �о�ɴϴ�.
   * @param bbsno �о�� �� ��ȣ
   * @return �Ѱ��� �� ��ü
   */
  public ClientdataDTO read(int cdatano){
    Connection con = dbopen.getConnection();  // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    ClientdataDTO dto = new ClientdataDTO();
    
    try{
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT cdatano, wname, title, fname, filesize, content, passwd, mviewcnt, rdate, grpno, indent, ansnum");
      sql.append(" FROM clientdata");
      sql.append(" WHERE cdatano = ?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, cdatano);
      
      rs = pstmt.executeQuery();
       
      if(rs.next() == true){ //ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
        
        
        dto.setCdatano(rs.getInt("cdatano"));
        dto.setWname(rs.getString("wname"));
        dto.setTitle(rs.getString("title"));
        dto.setFname(rs.getString("fname"));
        dto.setFilesize(rs.getLong("filesize"));
        dto.setContent(rs.getString("content"));
        dto.setPasswd(rs.getString("passwd"));
        dto.setMviewcnt(rs.getInt("mviewcnt"));
        dto.setRdate(rs.getString("rdate"));
        dto.setGrpno(rs.getInt("grpno"));
        dto.setIndent(rs.getInt("indent"));
        dto.setAnsnum(rs.getInt("ansnum"));
      }
      
    }catch(Exception e){
      System.out.println(e.toString());
    }finally{
      dbclose.close(con, pstmt, rs);
    }
 
    return dto;
  }

  /**
   * �Ѱ��� ���� �����մϴ�.
   * @param noitceno  ������ �۹�ȣ
   * @return �Ѱ��� �� ��ü
   */
  public int update(ClientdataDTO dto) {
    Connection con = dbopen.getConnection(); // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList list = null;
    int cnt = 0;

    try {
      StringBuffer sql = new StringBuffer();
      sql.append(" UPDATE clientdata");
      sql.append(" SET title= ?, content=?, fname=?, filesize=? ");
      sql.append(" WHERE cdatano = ?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, dto.getTitle());
      pstmt.setString(2, dto.getContent());
      pstmt.setString(3, dto.getFname());
      pstmt.setLong(4, dto.getFilesize());
      pstmt.setInt(5, dto.getCdatano());


      cnt = pstmt.executeUpdate();
    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      dbclose.close(con, pstmt);
    }

    return cnt;
  }
  
  /**
   * �н����带 �˻��մϴ�.
   * @param bbsno �� ��ȣ
   * @param passwd �н�����
   * @return ��ġ�ϴ� ���ڵ��� ���� 0 �Ǵ� 1
   */
  public int checkPasswd(int cdatano, String passwd){
    Connection con = dbopen.getConnection();  // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    int cnt = 0;
    
    try{
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT COUNT(cdatano) as cnt");
      sql.append(" FROM clientdata ");
      sql.append(" WHERE cdatano=? AND passwd=?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, cdatano);
      pstmt.setString(2, passwd);
      
      rs = pstmt.executeQuery();
      
      if(rs.next() == true){ // ù��° ���ڵ�� �̵�
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
   * �亯 ���, ����� ���ڵ� ���� ����: 0 , 1
   * 
   * @param dto
   *          ������ ��ü
   * @return ����� ���ڵ� ��
   */
  public int reply(ClientdataDTO dto) {
    Connection con = dbopen.getConnection(); // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList list = null;
    int cnt = 0;

    try {
      StringBuffer sql = new StringBuffer();
      sql.append(" INSERT INTO clientdata(cdatano, wname, title, fname, filesize, content, passwd, mviewcnt, rdate, grpno, indent, ansnum)");
      sql.append(" VALUES((SELECT NVL(MAX(cdatano), 0) + 1 as cdatano FROM clientdata),");
      sql.append(" ?, ?, ?, ?, ?, ?, 0, sysdate, ? , ?, ?)");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, dto.getWname());
      pstmt.setString(2, dto.getTitle());
      pstmt.setString(3, dto.getFname());
      pstmt.setLong(4, dto.getFilesize());
      pstmt.setString(5, dto.getContent());
      pstmt.setString(6, dto.getPasswd());
      pstmt.setInt(7, dto.getGrpno());
      pstmt.setInt(8, dto.getIndent());
      pstmt.setInt(9, dto.getAnsnum());

      cnt = pstmt.executeUpdate();
    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      dbclose.close(con, pstmt);
    }

    return cnt;
  }

  /**
   * �亯 ���� ����
   * @param grpno �׷� ��ȣ
   * @param ansnum �亯 ����
   */
  public void addAnsnum(int cdatano, int ansnum){
    Connection con = dbopen.getConnection();  // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList list = null;
    int cnt = 0;
    
    try{
      StringBuffer sql = new StringBuffer();
      sql.append(" UPDATE clientdata");
      sql.append(" SET ansnum = ansnum + 1");
      sql.append(" WHERE cdatano=? AND ansnum > ?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, cdatano);
      pstmt.setInt(2, ansnum);

      pstmt.executeUpdate();
    }catch(Exception e){
      System.out.println(e.toString());
    }finally{
      dbclose.close(con, pstmt);
    }
    
  }  

  /*********����*******/
  public int delete(int cdatano) {
    Connection con = dbopen.getConnection(); // DBMS ����
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList list = null;
    int cnt = 0;

    try {
      StringBuffer sql = new StringBuffer();
      sql.append(" DELETE FROM clientdata");
      sql.append(" WHERE cdatano=?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, cdatano);

      cnt = pstmt.executeUpdate();
    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      dbclose.close(con, pstmt);
    }

    return cnt;
  }
  
}
