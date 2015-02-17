package inventory.project.bbs;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import inventory.project.bbs.MpnoticeDTO;
import www.utility.DBClose;
import www.utility.DBOpen;

public class MpnoticeDAO {
    DBOpen dbopen = null; // �ʵ�
    DBClose dbclose = null;
    
    /**
     * ������, ���ϰ��� ����
     */
    public MpnoticeDAO() {
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
    public int create(MpnoticeDTO dto) {
      Connection con = dbopen.getConnection(); // DBMS ����
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      ArrayList list = null;
      int cnt = 0;

      try {
        StringBuffer sql = new StringBuffer();
        sql.append(" INSERT INTO mpnotice(noticeno, title, wdate, fname, content, mviewcnt)");
        sql.append(" VALUES((SELECT NVL(MAX(noticeno), 0) + 1 as noticeno FROM mpnotice),");
        sql.append(" ?, sysdate, ?, ?,  0)");

        pstmt = con.prepareStatement(sql.toString());
        pstmt.setString(1, dto.getTitle());
        pstmt.setString(2, dto.getFname());
        pstmt.setString(3, dto.getContent());
      
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
        sql.append(" SELECT noticeno, title, content, wdate, fname, mviewcnt");
        sql.append(" FROM mpnotice ");
        sql.append(" ORDER BY noticeno DESC");

        pstmt = con.prepareStatement(sql.toString());
        rs = pstmt.executeQuery();
        list = new ArrayList(); // ��ü �����

        while (rs.next() == true) { // ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
          MpnoticeDTO dto = new MpnoticeDTO();
          
          
          dto.setNoticeno(rs.getInt("noticeno"));
          dto.setTitle(rs.getString("title"));
          dto.setContent(rs.getString("content"));
          dto.setWdate(rs.getString("wdate"));
          dto.setFname(rs.getString("fname"));
          dto.setMviewcnt(rs.getInt("mviewcnt"));
       

         

          list.add(dto); // ������� �ϳ��� ��ü�� ����ҿ� �߰�
        }

      } catch (Exception e) {
        System.out.println(e.toString());
      } finally {
        dbclose.close(con, pstmt, rs);
      }

      return list;
    }
    
    
  //-----------------------------------------------------------------------------------------------------------------------
    /**
     * ����� �˻��մϴ�.
     * @param col  �˻� �÷�
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
        
        word = word.trim();    //  ���ڿ� �¿� ���� ����
        
        if (word.length() == 0){    // �˻��� ���ϴ� ���
          sql.append(" SELECT noticeno, title, content, wdate, fname, mviewcnt");
          sql.append(" FROM mpnotice ");
          sql.append(" ORDER BY noticeno DESC");
          pstmt = con.prepareStatement(sql.toString());
          
        }else{      // �˻��� �ϴ� ���
          sql.append(" SELECT noticeno, title, content, wdate, fname, mviewcnt");
          sql.append(" FROM mpnotice ");
         if (col.equals("title")){
           sql.append(" WHERE title LIKE '%' || ? || '%'");
         }else if(col.equals("content")){
           sql.append(" WHERE content LIKE '%' || ? || '%'");  
         }
         sql.append(" ORDER BY noticeno DESC");
         pstmt = con.prepareStatement(sql.toString());
         pstmt.setString(1, word);
       }
       
        rs = pstmt.executeQuery();
        list = new ArrayList(); // ��ü �����

        while (rs.next() == true) { // ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
          MpnoticeDTO dto = new MpnoticeDTO();

          dto.setNoticeno(rs.getInt("noticeno"));
          dto.setTitle(rs.getString("title"));
          dto.setContent(rs.getString("content"));
          dto.setWdate(rs.getString("wdate"));
          dto.setFname(rs.getString("fname"));
          dto.setMviewcnt(rs.getInt("mviewcnt"));


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
    * ��������
    *   SELECT
    *   FROM
    *   WHERE
    *   ORDER BY
    * @param col �˻��÷�
    * @param word �˻���
    * @param nowPage ���� ������
    * @param recordPerPage  �������� ���ڵ� ����
    * @return BbsDTO.jave�� ����� ��ü ArrayList
    */
    public ArrayList list(String col, String word, int nowPage, int recordPerPage) {
      Connection con = dbopen.getConnection(); // DBMS ����
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      ArrayList list = null;
      
      int startRow = ((nowPage-1) * 10) + 1;
      int endRow = nowPage * 10;
      
      /*
       * 1 page: WHERE r >= 1 AND r <= 10
       * 2 page: WHERE r >= 11 AND r <= 20
       * 
       */
      try {
        StringBuffer sql = new StringBuffer();
        
        word = word.trim();    //  ���ڿ� �¿� ���� ����
        sql.append(" SELECT noticeno, title, content, wdate, fname, mviewcnt, r");
        sql.append(" FROM(");
        sql.append(" ORDER BY noticeno DESC");
        if (word.length() == 0){    // �˻��� ���ϴ� ���
          sql.append(" SELECT noticeno, title, content, wdate, fname, mviewcnt, rownum, r");
          sql.append(" FROM (");
          sql.append("     SELECT SELECT noticeno, title, content, wdate, fname, mviewcnt, rownum as r");
          sql.append("     FROM  (");
          sql.append("             SELECT SELECT noticeno, title, content, wdate, fname, mviewcnt");
          sql.append("             FROM mpnotice ");
          sql.append("             ORDER BY noticeno DESC");
          sql.append("       )");
          sql.append(" )");
          sql.append(" WHERE r >= "+startRow+" AND r <= "+ endRow);
          pstmt = con.prepareStatement(sql.toString());
          
        }else{      // �˻��� �ϴ� ���
          sql.append(" SELECT noticeno, title, content, wdate, fname, mviewcnt, r");
          sql.append(" FROM (");
          sql.append("     SELECT noticeno, title, content, wdate, fname, mviewcnt, rownum as r");
          sql.append("     FROM  (");
          sql.append("             SELECT noticeno, title, content, wdate, fname, mviewcnt");
          sql.append("             FROM mpnotice ");
         
        }if(col.equals("title")){
           sql.append("            WHERE title LIKE '%' || ? || '%'");
         }else if(col.equals("content")){
           sql.append("            WHERE content LIKE '%' || ? || '%'");  
         }
         sql.append("              ORDER BY noticeno DESC");
         sql.append("       )");
         sql.append(" )");
         sql.append(" WHERE r >= "+startRow+" AND r <= "+ endRow );
         
         pstmt = con.prepareStatement(sql.toString());
         pstmt.setString(1, word);
       
       
        rs = pstmt.executeQuery();
        list = new ArrayList(); // ��ü �����

        while (rs.next() == true) { // ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
          MpnoticeDTO dto = new MpnoticeDTO();

          dto.setNoticeno(rs.getInt("noticeno"));
          dto.setTitle(rs.getString("title"));
          dto.setContent(rs.getString("content"));
          dto.setWdate(rs.getString("wdate"));
          dto.setFname(rs.getString("fname"));
          dto.setMviewcnt(rs.getInt("mviewcnt"));



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
     * �Ҵ�� ���ڵ��� ������ �����մϴ�.
     * @param count ����
     * @return MpnoticeDTO.java�� ����� ��ü ArrayList
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
      
        sql.append(" SELECT noticeno, title, content, wdate, fname, mviewcnt, r");
        sql.append(" FROM (");
        sql.append("     SELECT noticeno, title, content, wdate, fname, mviewcnt, rownum as r");
        sql.append("     FROM  (");
        sql.append("             SELECT noticeno, title, content, wdate, fname, mviewcnt");
        sql.append("             FROM mpnotice ");
        sql.append("             ORDER BY noticeno DESC");
        sql.append("       )");
        sql.append(" )");
        sql.append(" WHERE r >= "+startRow+" AND r <= "+ endRow);
          
        pstmt = con.prepareStatement(sql.toString());
          
        rs = pstmt.executeQuery();
        list = new ArrayList(); // ��ü �����

        while (rs.next() == true) { // ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
          MpnoticeDTO dto = new MpnoticeDTO();

          dto.setNoticeno(rs.getInt("noticeno"));
          dto.setTitle(rs.getString("title"));
          dto.setContent(rs.getString("content"));
          dto.setWdate(rs.getString("wdate"));
          dto.setFname(rs.getString("fname"));
          dto.setMviewcnt(rs.getInt("mviewcnt"));

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
  //-----------------------------------------------------------------------------------------------------------------------
    
    /**
     *  ��ȸ�� ����
     * @param noticeno  ��ȸ���� ������ų �� ��ȣ
     */
      public void viewcntAdd(int noticeno) {
        Connection con = dbopen.getConnection(); // DBMS ����
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList list = null;
        int cnt = 0;

        try{
          StringBuffer sqlsb = new StringBuffer();
          sqlsb.append(" UPDATE mpnotice");
          sqlsb.append(" SET mviewcnt = mviewcnt + 1 ");
          sqlsb.append(" WHERE noticeno=? ");
          
          pstmt = con.prepareStatement(sqlsb.toString());
          
          pstmt.setInt(1, noticeno);
          
          cnt = pstmt.executeUpdate();
          
        }catch(Exception e){
          System.out.println(e.toString());
        }finally{
          dbclose.close(con, pstmt);
        }
      }
      
      /**
       * �Ѱ��� ���� �о�ɴϴ�
       * @param noitceno  �о�� �۹�ȣ
       * @return �Ѱ��� �� ��ü
       */
       
       public MpnoticeDTO read(int noticeno) {
         Connection con = dbopen.getConnection(); // DBMS ����
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         ArrayList list = null;

         MpnoticeDTO dto = new MpnoticeDTO();

         try {
           StringBuffer sql = new StringBuffer();
           sql.append(" SELECT noticeno, title, content, wdate, fname, mviewcnt");
           sql.append(" FROM mpnotice ");
           sql.append(" WHERE noticeno=? ");

           pstmt = con.prepareStatement(sql.toString());
           pstmt.setInt(1, noticeno);

           rs = pstmt.executeQuery();

           if (rs.next() == true) { // ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
             
             dto.setNoticeno(rs.getInt("noticeno"));
             dto.setTitle(rs.getString("title"));
             dto.setContent(rs.getString("content"));
             dto.setWdate(rs.getString("wdate"));
             dto.setFname(rs.getString("fname"));
             dto.setMviewcnt(rs.getInt("mviewcnt"));
             
           }

         } catch (Exception e) {
           System.out.println(e.toString());
         } finally {
           dbclose.close(con, pstmt, rs);
         }
      return dto;
     }
       
       
       
 /*********����*******/
       public int delete(int noticeno) {
         Connection con = dbopen.getConnection(); // DBMS ����
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         ArrayList list = null;
         int cnt = 0;

         try {
           StringBuffer sql = new StringBuffer();
           sql.append(" DELETE FROM mpnotice");
           sql.append(" WHERE noticeno=?");

           pstmt = con.prepareStatement(sql.toString());
           pstmt.setInt(1, noticeno);

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
       public int checkPasswd(int noticeno, String passwd){
         Connection con = dbopen.getConnection();  // DBMS ����
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         
         int cnt = 0;
         
         try{
           StringBuffer sql = new StringBuffer();
           sql.append(" SELECT COUNT(noticeno) as cnt");
           sql.append(" FROM mpnotice ");
           sql.append(" WHERE noticeno=? AND passwd=?");

           pstmt = con.prepareStatement(sql.toString());
           pstmt.setInt(1, noticeno);
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
        * �Ѱ��� ���� �����մϴ�.
        * @param noitceno  ������ �۹�ȣ
        * @return �Ѱ��� �� ��ü
        */
       public int update(MpnoticeDTO dto) {
         Connection con = dbopen.getConnection(); // DBMS ����
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         ArrayList list = null;
         int cnt = 0;

         try {
           StringBuffer sql = new StringBuffer();
           sql.append(" UPDATE mpnotice");
           sql.append(" SET title= ?, content=?, fname=?");
           sql.append(" WHERE noticeno = ?");

           pstmt = con.prepareStatement(sql.toString());
           pstmt.setString(1, dto.getTitle());
           pstmt.setString(2, dto.getContent());
           pstmt.setString(3, dto.getFname());
           pstmt.setInt(4, dto.getNoticeno());


           cnt = pstmt.executeUpdate();
         } catch (Exception e) {
           System.out.println(e.toString());
         } finally {
           dbclose.close(con, pstmt);
         }

         return cnt;
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
             sql.append(" FROM mpnotice ");
             pstmt = con.prepareStatement(sql.toString());
             
           }else{ // �˻��� �ϴ� ���
             sql.append(" SELECT COUNT(*) as cnt");
             sql.append(" FROM mpnotice ");
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


 }

