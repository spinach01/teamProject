package inventory.project.bbs;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import inventory.project.bbs.MpnoticeDTO;
import www.utility.DBClose;
import www.utility.DBOpen;

public class MpnoticeDAO {
    DBOpen dbopen = null; // 필드
    DBClose dbclose = null;
    
    /**
     * 생성자, 리턴값이 없음
     */
    public MpnoticeDAO() {
      dbopen = new DBOpen();
      dbclose = new DBClose();
    }
    
    /**
     * 레코드 등록, 등록한 레코드 갯수 리턴: 0 , 1
     * 
     * @param dto
     *          저장할 객체
     * @return 저장된 레코드 수
     */
    public int create(MpnoticeDTO dto) {
      Connection con = dbopen.getConnection(); // DBMS 연결
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
     * 목록
     * 
     * @return 레코드를 DTO로 변환한 집합
     */
    public ArrayList list() {
      Connection con = dbopen.getConnection(); // DBMS 연결
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
        list = new ArrayList(); // 객체 저장소

        while (rs.next() == true) { // 첫번째 레코드로 이동, 다음부터 다음레코드로 이동
          MpnoticeDTO dto = new MpnoticeDTO();
          
          
          dto.setNoticeno(rs.getInt("noticeno"));
          dto.setTitle(rs.getString("title"));
          dto.setContent(rs.getString("content"));
          dto.setWdate(rs.getString("wdate"));
          dto.setFname(rs.getString("fname"));
          dto.setMviewcnt(rs.getInt("mviewcnt"));
       

         

          list.add(dto); // 만들어진 하나의 객체를 저장소에 추가
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
     * 목록을 검색합니다.
     * @param col  검색 컬럼
     * @param word 검색어
     
     * @return BbsDTO.java가 저장된 객체 ArrayList
     */
    public ArrayList list(String col, String word) {
      Connection con = dbopen.getConnection(); // DBMS 연결
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      ArrayList list = null;

      try {
        StringBuffer sql = new StringBuffer();
        
        word = word.trim();    //  문자열 좌우 공백 제거
        
        if (word.length() == 0){    // 검색을 안하는 경우
          sql.append(" SELECT noticeno, title, content, wdate, fname, mviewcnt");
          sql.append(" FROM mpnotice ");
          sql.append(" ORDER BY noticeno DESC");
          pstmt = con.prepareStatement(sql.toString());
          
        }else{      // 검색을 하는 경우
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
        list = new ArrayList(); // 객체 저장소

        while (rs.next() == true) { // 첫번째 레코드로 이동, 다음부터 다음레코드로 이동
          MpnoticeDTO dto = new MpnoticeDTO();

          dto.setNoticeno(rs.getInt("noticeno"));
          dto.setTitle(rs.getString("title"));
          dto.setContent(rs.getString("content"));
          dto.setWdate(rs.getString("wdate"));
          dto.setFname(rs.getString("fname"));
          dto.setMviewcnt(rs.getInt("mviewcnt"));


          list.add(dto); // 만들어진 하나의 객체를 저장소에 추가
        }

      } catch (Exception e) {
        System.out.println(e.toString());
      } finally {
        dbclose.close(con, pstmt, rs);
      }

      return list;
}
     
       
   /**
    * 목록을 검색합니다.
    * 쿼리순서
    *   SELECT
    *   FROM
    *   WHERE
    *   ORDER BY
    * @param col 검색컬럼
    * @param word 검색어
    * @param nowPage 현재 페이지
    * @param recordPerPage  페이지당 레코드 갯수
    * @return BbsDTO.jave가 저장된 객체 ArrayList
    */
    public ArrayList list(String col, String word, int nowPage, int recordPerPage) {
      Connection con = dbopen.getConnection(); // DBMS 연결
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
        
        word = word.trim();    //  문자열 좌우 공백 제거
        sql.append(" SELECT noticeno, title, content, wdate, fname, mviewcnt, r");
        sql.append(" FROM(");
        sql.append(" ORDER BY noticeno DESC");
        if (word.length() == 0){    // 검색을 안하는 경우
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
          
        }else{      // 검색을 하는 경우
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
        list = new ArrayList(); // 객체 저장소

        while (rs.next() == true) { // 첫번째 레코드로 이동, 다음부터 다음레코드로 이동
          MpnoticeDTO dto = new MpnoticeDTO();

          dto.setNoticeno(rs.getInt("noticeno"));
          dto.setTitle(rs.getString("title"));
          dto.setContent(rs.getString("content"));
          dto.setWdate(rs.getString("wdate"));
          dto.setFname(rs.getString("fname"));
          dto.setMviewcnt(rs.getInt("mviewcnt"));



          list.add(dto); // 만들어진 하나의 객체를 저장소에 추가
        }

        } catch (Exception e) {
        System.out.println(e.toString());
      } finally {
        dbclose.close(con, pstmt, rs);
      }

      return list;
    }
    
    /**
     * 할당된 레코드의 갯수를 리턴합니다.
     * @param count 갯수
     * @return MpnoticeDTO.java가 저장된 객체 ArrayList
     */
    public ArrayList list(int count) {
      Connection con = dbopen.getConnection(); // DBMS 연결
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      ArrayList list = null;

      // 10: 페이지당 출력할 레코드 갯수
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
        list = new ArrayList(); // 객체 저장소

        while (rs.next() == true) { // 첫번째 레코드로 이동, 다음부터 다음레코드로 이동
          MpnoticeDTO dto = new MpnoticeDTO();

          dto.setNoticeno(rs.getInt("noticeno"));
          dto.setTitle(rs.getString("title"));
          dto.setContent(rs.getString("content"));
          dto.setWdate(rs.getString("wdate"));
          dto.setFname(rs.getString("fname"));
          dto.setMviewcnt(rs.getInt("mviewcnt"));

          list.add(dto); // 만들어진 하나의 객체를 저장소에 추가
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
     *  조회수 증가
     * @param noticeno  조회수를 증가시킬 글 번호
     */
      public void viewcntAdd(int noticeno) {
        Connection con = dbopen.getConnection(); // DBMS 연결
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
       * 한건의 글을 읽어옵니다
       * @param noitceno  읽어올 글번호
       * @return 한건의 글 객체
       */
       
       public MpnoticeDTO read(int noticeno) {
         Connection con = dbopen.getConnection(); // DBMS 연결
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

           if (rs.next() == true) { // 첫번째 레코드로 이동, 다음부터 다음레코드로 이동
             
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
       
       
       
 /*********삭제*******/
       public int delete(int noticeno) {
         Connection con = dbopen.getConnection(); // DBMS 연결
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
        * 패스워드를 검사합니다.
        * @param bbsno 글 번호
        * @param passwd 패스워드
        * @return 일치하는 레코드의 갯수 0 또는 1
        */
       public int checkPasswd(int noticeno, String passwd){
         Connection con = dbopen.getConnection();  // DBMS 연결
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
           
           if(rs.next() == true){ // 첫번째 레코드로 이동
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
        * 한건의 글을 수정합니다.
        * @param noitceno  수정할 글번호
        * @return 한건의 글 객체
        */
       public int update(MpnoticeDTO dto) {
         Connection con = dbopen.getConnection(); // DBMS 연결
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
        * 검색된 레코드 갯수를 리턴 
        * @param col 검색 컬럼
        * @param word 검색어
        * @return 검색된 레코드 갯수
        */
       public int count(String col, String word) {
         Connection con = dbopen.getConnection(); // DBMS 연결
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         int cnt = 0;
         
         try {
           StringBuffer sql = new StringBuffer();
           
           word = word.trim(); // 문자열 좌우 공백 제거
           
           if (word.length() == 0){ // 검색을 안하는 경우
             sql.append(" SELECT COUNT(*) as cnt");
             sql.append(" FROM mpnotice ");
             pstmt = con.prepareStatement(sql.toString());
             
           }else{ // 검색을 하는 경우
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

           if (rs.next() == true) { // 첫번째 레코드로 이동, 다음부터 다음레코드로 이동
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

