package inventory.project.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import www.utility.DBClose;
import www.utility.DBOpen;


public class ClientbbsDAO {
  DBOpen dbopen = null; // 필드
  DBClose dbclose = null;

  /**
   * 생성자, 리턴값이 없음
   */
  public ClientbbsDAO() {
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
public int create(ClientbbsDTO dto) {
  Connection con = dbopen.getConnection(); // DBMS 연결
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  ArrayList list = null;
  int cnt = 0;

  try {
    StringBuffer sql = new StringBuffer();
    sql.append(" INSERT INTO clientbbs(qnano, wname, title, content, passwd, viewcnt, wdate, grpno, indent, ansnum)");
    sql.append(" VALUES((SELECT NVL(MAX(qnano), 0) + 1 as qnano FROM clientbbs),");
    sql.append(" ?, ?, ?, ?, 0, sysdate, (SELECT NVL(MAX(qnano), 0) + 1 as grpno FROM clientbbs), 0, 0)");

    pstmt = con.prepareStatement(sql.toString());
    pstmt.setString(1, dto.getWname());
    pstmt.setString(2, dto.getTitle());
    pstmt.setString(3, dto.getContent());
    pstmt.setString(4, dto.getPasswd());

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
    sql.append(" SELECT qnano, wname, title, content, passwd, viewcnt, wdate, grpno, indent, ansnum");
    sql.append(" FROM clientbbs ");
    // sql.append(" ORDER BY qnano DESC");
    sql.append(" ORDER BY qnano DESC, ansnum ASC");
    
    pstmt = con.prepareStatement(sql.toString());
    rs = pstmt.executeQuery();
    list = new ArrayList(); // 객체 저장소

    while (rs.next() == true) { // 첫번째 레코드로 이동, 다음부터 다음레코드로 이동
      ClientbbsDTO dto = new ClientbbsDTO();

      dto.setQnano(rs.getInt("qnano"));
      dto.setWname(rs.getString("wname"));
      dto.setTitle(rs.getString("title"));
      dto.setContent(rs.getString("content"));
      dto.setPasswd(rs.getString("passwd"));
      dto.setViewcnt(rs.getInt("viewcnt"));
      dto.setWdate(rs.getString("wdate"));
      dto.setGrpno(rs.getInt("grpno"));
      dto.setIndent(rs.getInt("indent"));
      dto.setAnsnum(rs.getInt("ansnum"));

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
 * @param col 검색 컬럼
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
    
    word = word.trim(); // 문자열 좌우 공백 제거
    
    if (word.length() == 0){ // 검색을 안하는 경우
      sql.append(" SELECT qnano, wname, title, content, passwd, viewcnt, wdate, grpno, indent, ansnum");
      sql.append(" FROM clientbbs ");
      sql.append(" ORDER BY qnano DESC, ansnum ASC");
      pstmt = con.prepareStatement(sql.toString());
      
    }else{ // 검색을 하는 경우
      sql.append(" SELECT qnano, wname, title, content, passwd, viewcnt, wdate, grpno, indent, ansnum");
      sql.append(" FROM clientbbs ");
      if (col.equals("wname")){
        sql.append(" WHERE wname LIKE '%' || ? || '%'");
      }else if (col.equals("title")){
        sql.append(" WHERE title LIKE '%' || ? || '%'");
      }else if (col.equals("content")){
        sql.append(" WHERE content LIKE '%' || ? || '%'");
      }
      sql.append(" ORDER BY qnano DESC, ansnum ASC");
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, word);
    }
    
    rs = pstmt.executeQuery();
    list = new ArrayList(); // 객체 저장소

    while (rs.next() == true) { // 첫번째 레코드로 이동, 다음부터 다음레코드로 이동
      ClientbbsDTO dto = new ClientbbsDTO();

      dto.setQnano(rs.getInt("qnano"));
      dto.setWname(rs.getString("wname"));
      dto.setTitle(rs.getString("title"));
      dto.setContent(rs.getString("content"));
      dto.setPasswd(rs.getString("passwd"));
      dto.setViewcnt(rs.getInt("viewcnt"));
      dto.setWdate(rs.getString("wdate"));
      dto.setGrpno(rs.getInt("grpno"));
      dto.setIndent(rs.getInt("indent"));
      dto.setAnsnum(rs.getInt("ansnum"));

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
 * 목록을 검색하고 페이지를 적용하여 리턴 합니다. nowPage는 1부터 시작
 * <pre>
 * 쿼리(SQL) 기술 순서, 아래의 순서가 바뀌면 안됨.
 *   SELECT
 *   FROM
 *   WHERE
 *   ORDER BY
 * 
 * </pre>
 * @param col 검색 컬럼
 * @param word 검색어
 * @param nowPage 현재 페이지
 * @param recordPerPage 페이지당 레코드 갯수
 * @return BbsDTO.java가 저장된 객체 ArrayList
 */
public ArrayList list(String col, String word, int nowPage, int recordPerPage) {
  Connection con = dbopen.getConnection(); // DBMS 연결
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  ArrayList list = null;

  // 10: 페이지당 출력할 레코드 갯수
  int startRow = ((nowPage-1) * recordPerPage) + 1; // (0 * 10) + 1 = 1, 11, 21
  int endRow = nowPage * recordPerPage;             // 1 * 10 = 10, 20, 30
  
  /*
   1 page: WHERE r >= 1 AND r <= 10;
   2 page: WHERE r >= 11 AND r <= 20;
   3 page: WHERE r >= 21 AND r <= 30;
   */
  
  
  try {
    StringBuffer sql = new StringBuffer();
    
    word = word.trim(); // 문자열 좌우 공백 제거
    
    if (word.length() == 0){ // 검색을 안하는 경우
      sql.append(" SELECT qnano, wname, title, content, passwd, viewcnt, wdate, grpno, indent, ansnum, r");
      sql.append(" FROM(");
      sql.append("      SELECT qnano, wname, title, content, passwd, viewcnt, wdate, grpno, indent, ansnum, rownum as r");
      sql.append("      FROM (");
      sql.append("           SELECT qnano, wname, title, content, passwd, viewcnt, wdate, grpno, indent, ansnum");
      sql.append("           FROM clientbbs ");
      sql.append("           ORDER BY grpno DESC, ansnum ASC");
      sql.append("      )");
      sql.append(" )     ");
      sql.append(" WHERE r >= "+startRow+" AND r <= "+endRow);
      
      pstmt = con.prepareStatement(sql.toString());
      
    }else{ // 검색을 하는 경우
      sql.append(" SELECT qnano, wname, title, content, passwd, viewcnt, wdate, grpno, indent, ansnum, r");
      sql.append(" FROM(");
      sql.append("      SELECT qnano, wname, title, content, passwd, viewcnt, wdate, grpno, indent, ansnum, rownum as r");
      sql.append("      FROM (");
      sql.append("           SELECT qnano, wname, title, content, passwd, viewcnt, wdate, grpno, indent, ansnum");
      sql.append("           FROM clientbbs ");
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
    list = new ArrayList(); // 객체 저장소

    while (rs.next() == true) { // 첫번째 레코드로 이동, 다음부터 다음레코드로 이동
      ClientbbsDTO dto = new ClientbbsDTO();

      dto.setQnano(rs.getInt("qnano"));
      dto.setWname(rs.getString("wname"));
      dto.setTitle(rs.getString("title"));
      dto.setContent(rs.getString("content"));
      dto.setPasswd(rs.getString("passwd"));
      dto.setViewcnt(rs.getInt("viewcnt"));
      dto.setWdate(rs.getString("wdate"));
      dto.setGrpno(rs.getInt("grpno"));
      dto.setIndent(rs.getInt("indent"));
      dto.setAnsnum(rs.getInt("ansnum"));

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
 * @return BbsDTO.java가 저장된 객체 ArrayList
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
  
    sql.append(" SELECT qnano, wname, title, content, passwd, viewcnt, wdate, grpno, indent, ansnum, r");
    sql.append(" FROM(");
    sql.append("      SELECT qnano, wname, title, content, passwd, viewcnt, wdate, grpno, indent, ansnum, rownum as r");
    sql.append("      FROM (");
    sql.append("           SELECT qnano, wname, title, content, passwd, viewcnt, wdate, grpno, indent, ansnum");
    sql.append("           FROM clientbbs ");
    sql.append("           ORDER BY qnano DESC");
    sql.append("      )");
    sql.append(" )     ");
    sql.append(" WHERE r >= "+startRow+" AND r <= "+endRow);
      
    pstmt = con.prepareStatement(sql.toString());
      
    rs = pstmt.executeQuery();
    list = new ArrayList(); // 객체 저장소

    while (rs.next() == true) { // 첫번째 레코드로 이동, 다음부터 다음레코드로 이동
      ClientbbsDTO dto = new ClientbbsDTO();

      dto.setQnano(rs.getInt("qnano"));
      dto.setWname(rs.getString("wname"));
      dto.setTitle(rs.getString("title"));
      dto.setContent(rs.getString("content"));
      dto.setPasswd(rs.getString("passwd"));
      dto.setViewcnt(rs.getInt("viewcnt"));
      dto.setWdate(rs.getString("wdate"));
      dto.setGrpno(rs.getInt("grpno"));
      dto.setIndent(rs.getInt("indent"));
      dto.setAnsnum(rs.getInt("ansnum"));

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
 * 조회수 증가
 * @param bbsno 조회수를 증가시킬 글 번호
 */
public void viewcntAdd(int qnano){
  Connection con = dbopen.getConnection();  // DBMS 연결
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  ArrayList list = null;
  int cnt = 0;
  
  try{
    StringBuffer sql = new StringBuffer();
    sql.append(" UPDATE clientbbs");
    sql.append(" SET viewcnt = viewcnt + 1");
    sql.append(" WHERE qnano=?");

    pstmt = con.prepareStatement(sql.toString());
    pstmt.setInt(1, qnano);

    pstmt.executeUpdate();
  }catch(Exception e){
    System.out.println(e.toString());
  }finally{
    dbclose.close(con, pstmt);
  }
  
}  

/**
 * 한건의 글을 읽어옵니다.
 * @param bbsno 읽어올 글 번호
 * @return 한건의 글 객체
 */
public ClientbbsDTO read(int qnano){
  Connection con = dbopen.getConnection();  // DBMS 연결
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  
  ClientbbsDTO dto = new ClientbbsDTO();
  
  try{
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT qnano, wname, title, content, passwd, viewcnt, wdate, grpno, indent, ansnum");
    sql.append(" FROM clientbbs");
    sql.append(" WHERE qnano = ?");

    pstmt = con.prepareStatement(sql.toString());
    pstmt.setInt(1, qnano);
    
    rs = pstmt.executeQuery();
    
    if(rs.next() == true){ //첫번째 레코드로 이동, 다음부터 다음레코드로 이동
      dto.setQnano(rs.getInt("qnano"));   // DBMS -> JAVA
      dto.setWname(rs.getString("wname")); 
      dto.setTitle(rs.getString("title"));
      dto.setContent(rs.getString("content"));
      dto.setPasswd(rs.getString("passwd"));
      dto.setViewcnt(rs.getInt("viewcnt"));
      dto.setWdate(rs.getString("wdate"));
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
 * 패스워드를 검사합니다.
 * @param bbsno 글 번호
 * @param passwd 패스워드
 * @return 일치하는 레코드의 갯수 0 또는 1
 */
public int checkPasswd(int qnano, String passwd){
  Connection con = dbopen.getConnection();  // DBMS 연결
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  
  int cnt = 0;
  
  try{
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT COUNT(qnano) as cnt");
    sql.append(" FROM clientbbs ");
    sql.append(" WHERE qnano=? AND passwd=?");

    pstmt = con.prepareStatement(sql.toString());
    pstmt.setInt(1, qnano);
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

public int update(ClientbbsDTO dto) {
  Connection con = dbopen.getConnection(); // DBMS 연결
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  ArrayList list = null;
  int cnt = 0;

  try {
    StringBuffer sql = new StringBuffer();
    sql.append(" UPDATE clientbbs");
    sql.append(" SET wname=?, title=?, content=?");
    sql.append(" WHERE qnano = ?");

    pstmt = con.prepareStatement(sql.toString());
    pstmt.setString(1, dto.getWname());
    pstmt.setString(2, dto.getTitle());
    pstmt.setString(3, dto.getContent());
    pstmt.setInt(4, dto.getQnano());

    cnt = pstmt.executeUpdate();
  } catch (Exception e) {
    System.out.println(e.toString());
  } finally {
    dbclose.close(con, pstmt);
  }

  return cnt;
}

public int delete(int qnano) {
  Connection con = dbopen.getConnection(); // DBMS 연결
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  ArrayList list = null;
  int cnt = 0;

  try {
    StringBuffer sql = new StringBuffer();
    sql.append(" DELETE FROM clientbbs");
    sql.append(" WHERE qnano = ?");

    pstmt = con.prepareStatement(sql.toString());
    pstmt.setInt(1, qnano);

    cnt = pstmt.executeUpdate();
  } catch (Exception e) {
    System.out.println(e.toString());
  } finally {
    dbclose.close(con, pstmt);
  }

  return cnt;
}


/**
 * 답변 등록, 등록한 레코드 갯수 리턴: 0 , 1
 * 
 * @param dto
 *          저장할 객체
 * @return 저장된 레코드 수
 */
public int reply(ClientbbsDTO dto) {
  Connection con = dbopen.getConnection(); // DBMS 연결
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  ArrayList list = null;
  int cnt = 0;

  try {
    StringBuffer sql = new StringBuffer();
    sql.append(" INSERT INTO clientbbs(qnano, wname, title, content, passwd, viewcnt, wdate, grpno, indent, ansnum)");
    sql.append(" VALUES((SELECT NVL(MAX(qnano), 0) + 1 as qnano FROM clientbbs),");
    sql.append(" ?, ?, ?, ?, 0, sysdate, ?, ?, ?)");

    pstmt = con.prepareStatement(sql.toString());
    pstmt.setString(1, dto.getWname());
    pstmt.setString(2, dto.getTitle());
    pstmt.setString(3, dto.getContent());
    pstmt.setString(4, dto.getPasswd());
    pstmt.setInt(5, dto.getGrpno()); // 글 그룹 번호 지정
    pstmt.setInt(6, dto.getIndent());
    pstmt.setInt(7, dto.getAnsnum());

    cnt = pstmt.executeUpdate();
  } catch (Exception e) {
    System.out.println(e.toString());
  } finally {
    dbclose.close(con, pstmt);
  }

  return cnt;
}

/**
 * 답변 순서 증가
 * @param grpno 그룹 번호
 * @param ansnum 답변 순서
 */
public void addAnsnum(int qnano, int ansnum){
  Connection con = dbopen.getConnection();  // DBMS 연결
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  ArrayList list = null;
  int cnt = 0;
  
  try{
    StringBuffer sql = new StringBuffer();
    sql.append(" UPDATE clientbbs");
    sql.append(" SET ansnum = ansnum + 1");
    sql.append(" WHERE qnano=? AND ansnum > ?");

    pstmt = con.prepareStatement(sql.toString());
    pstmt.setInt(1, qnano);
    pstmt.setInt(2, ansnum);

    pstmt.executeUpdate();
  }catch(Exception e){
    System.out.println(e.toString());
  }finally{
    dbclose.close(con, pstmt);
  }
  
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
      sql.append(" FROM clientbbs ");
      pstmt = con.prepareStatement(sql.toString());
      
    }else{ // 검색을 하는 경우
      sql.append(" SELECT COUNT(*) as cnt");
      sql.append(" FROM clientbbs ");
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
























