package inventory.project.group;
 
import inventory.project.group.CalendarDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import www.utility.DBClose;
import www.utility.DBOpen;
import www.utility.Utility;

public class CalendarDAO {
  /** 테이블 이름 */
  private String table_name = "calendar";

  /** 현재 페이지, 0 페이지부터 시작~ */
  private int currentPage = 0;

  /** 페이지당 출력할 레코드 수 */
  private int recordPerPage = 100;

  /** 블럭당 페이지 수 */
  private int pagePerBlock = 10;

  DBOpen dbopen = null; // 필드
  DBClose dbclose = null;

  /**
   * 기본 생성자
   */
  public CalendarDAO() {
    dbopen = new DBOpen();
    dbclose = new DBClose();
  }

  /**
   * 한건의 데이터를 추가합니다.
   * 
   * @param con
   *          데이터베이스 연결
   * @param dto
   *          저장할 객체
   * @return 1: 추가 성공, 2: 추가 실패
   * @throws SQLException
   *           DBMS Error
   */
  public int create(CalendarDTO dto) throws SQLException {
    Connection con = null;
    PreparedStatement pstmt = null; // SQL 실행 객체
    ResultSet rs = null; // SELECT 결과 저장 객체
    int count = 0;

    con = this.dbopen.getConnection();

    // INSERT INTO bbsgrp(pdsgrpno, title, grade)
    // VALUES(bbsgrp_seq.nextval, 'java', 'N');
    StringBuffer sql = new StringBuffer();

    // MySQL
    sql.append(" INSERT INTO " + table_name + "(calendarno, labeldate, label, title, content, cnt, regdate)");
    sql.append(" VALUES((SELECT NVL(MAX(calendarno), 0)+1 as calendarno FROM calendar),?, ?, ?, ?, 0, sysdate)");

    pstmt = con.prepareStatement(sql.toString());
    pstmt.setString(1, dto.getLabeldate());
    pstmt.setString(2, dto.getLabel());
    pstmt.setString(3, dto.getTitle());
    pstmt.setString(4, dto.getContent());

    // 추가한 레코드의 1이 retVal 변수에 저장
    count = pstmt.executeUpdate();

    dbclose.close(con, pstmt, rs);

    return count; // 1 or 0
  }

  /**
   * 페이지와 그룹번호에 의해서 전체 목록을 가져옵니다.
   * 
   * @param pdsgrpno
   *          그룹번호
   * @return 검색 목록
   * @throws SQLException
   */
  public ArrayList list(HttpServletRequest request, int currentPage) throws SQLException {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StringBuffer sql = null;
    ArrayList list = null; // dto 목록을 저장
    int limit = currentPage * this.recordPerPage; // 페이징

    con = this.dbopen.getConnection();
    list = new ArrayList();

    sql = new StringBuffer();


    sql.append(" SELECT calendarno, labeldate, label, title, content, cnt, regdate ");
    sql.append(" FROM " + table_name);
    sql.append(" ORDER BY calendarno DESC");

    pstmt = con.prepareStatement(sql.toString());
    
    rs = pstmt.executeQuery(); // SELECT 쿼리 실행

    while (rs.next() == true) {
      CalendarDTO dto = new CalendarDTO();

      dto.setCalendarno(rs.getInt("calendarno"));// DBMS --> JAVA
      dto.setLabeldate(rs.getString("labeldate"));
      dto.setLabel(rs.getString("label"));
      dto.setTitle(rs.getString("title"));
      dto.setContent(rs.getString("content"));
      dto.setCnt(rs.getInt("cnt"));
      dto.setRegdate(rs.getString("regdate"));

      list.add(dto);

    }

    dbclose.close(con, pstmt, rs);

    return list;
  }

  /**
   * 페이지와 그룹번호에 의해서 전체 목록을 가져옵니다.
   * 
   * @param pdsgrpno
   *          그룹번호
   * @return 검색 목록
   * @throws SQLException
   */
  public ArrayList listLabel(String date) throws SQLException {
    Connection con = this.dbopen.getConnection();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StringBuffer sql = new StringBuffer();
    ArrayList list = new ArrayList();

    sql.append(" SELECT calendarno, labeldate, label");
    sql.append(" FROM " + table_name);
    sql.append(" WHERE labeldate=?");  // 2013-10-15
    
    pstmt = con.prepareStatement(sql.toString());
    pstmt.setString(1, date);

    rs = pstmt.executeQuery(); // SELECT 쿼리 실행
    
    while (rs.next() == true) {
      CalendarDTO dto = new CalendarDTO();

      dto.setCalendarno(rs.getInt("calendarno"));// DBMS --> JAVA
      dto.setLabeldate(rs.getString("labeldate"));
      dto.setLabel(rs.getString("label"));

      list.add(dto);

    }

    dbclose.close(con, pstmt, rs);

    return list;
  }
  
  /**
   * 글번호를 이용하여 글을 가져옵니다.
   */
  public CalendarDTO read(int calendarno) throws SQLException {
    Connection con;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StringBuffer sql = null;
    con = this.dbopen.getConnection();

    CalendarDTO dto = null;

    sql = new StringBuffer();

    sql.append(" SELECT calendarno, labeldate, label, title, content, cnt, regdate ");
    sql.append(" FROM " + table_name);
    sql.append(" WHERE calendarno=?");
    
    pstmt = con.prepareStatement(sql.toString());
    pstmt.setInt(1, calendarno); // 첫번째 ?표로 글번호를 할당

    rs = pstmt.executeQuery();

    if (rs.next()) { // 레코드 포인터를 첫번째 레코드로 이동
      dto = new CalendarDTO();

      dto.setCalendarno(rs.getInt("calendarno"));// DBMS --> JAVA
      dto.setLabeldate(rs.getString("labeldate"));
      dto.setLabel(rs.getString("label"));
      dto.setTitle(rs.getString("title"));
      dto.setContent(rs.getString("content"));
      dto.setCnt(rs.getInt("cnt"));
      dto.setRegdate(rs.getString("regdate"));

    }

    dbclose.close(con, pstmt, rs);

    return dto;
  }

  /**
   * 글을 수정합니다.
   * 
   * @param dto
   *          글 객체
   * @return 1: 성공적으로 수정, 0: 수정 실패
   */
  public int update(CalendarDTO dto) throws SQLException {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StringBuffer sql = null;
    con = this.dbopen.getConnection();

    int cnt = 0; // 수정한 레코드 갯수 저장

    sql = new StringBuffer();
    sql.append(" UPDATE " + table_name);
    sql.append(" SET labeldate=?, label=?, title=?, content=?");
    sql.append(" WHERE calendarno = ?");

    pstmt = con.prepareStatement(sql.toString());
    pstmt.setString(1, dto.getLabeldate());
    pstmt.setString(2, dto.getLabel());
    pstmt.setString(3, dto.getTitle());
    pstmt.setString(4, dto.getContent());
    pstmt.setInt(5, dto.getCalendarno());

    cnt = pstmt.executeUpdate();

    dbclose.close(con, pstmt);

    return cnt;
  }

  /**
   * 하나의 데이터를 삭제합니다.
   * 
   * @return 정상적인 삭제는 true를 실패는 false를 리턴합니다.
   */
  public int delete(int calendarno) throws SQLException {
    Connection con = null;
    PreparedStatement pstmt = null;
    StringBuffer sql = null;
    con = this.dbopen.getConnection();

    int cnt = 0;

    sql = new StringBuffer();
    sql.append(" DELETE FROM " + table_name);
    sql.append(" WHERE calendarno = ?");

    pstmt = con.prepareStatement(sql.toString());
    pstmt.setInt(1, calendarno);

    // 삭제한 레코드의 수 저장
    cnt = pstmt.executeUpdate();

    dbclose.close(con, pstmt);

    return cnt;

  }

  // ----------------------------------------------------------
  // 페이징 관련 메소드 시작
  // ----------------------------------------------------------

  /**
   * 현재 페코드 수
   * 
   * @param pdsgrpno
   * @return
   * @throws Exception
   */
  public int recordCount(HttpServletRequest request) throws Exception {
    Connection con;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StringBuffer sql = null;
    con = this.dbopen.getConnection();
    int cnt = 0;

    sql = new StringBuffer();

    sql.append(" SELECT COUNT(*) as cnt");
    sql.append(" FROM " + table_name);
    
    pstmt = con.prepareStatement(sql.toString());

    rs = pstmt.executeQuery();

    if (rs.next()) { // 레코드 포인터를 첫번째 레코드로 이동
      cnt = rs.getInt("cnt");
    }

    dbclose.close(con, pstmt, rs);

    return cnt;

  }

  /**
   * 페이징을 처리하고 링크를 생성합니다.
   * 
   * @param col
   *          검색 컬럼
   * @param word
   *          검색어
   * @param nowPage
   *          현재 페이지,최초값은 0임
   * 
   * @return 페이징 링크를 가지고 있는 문자열
   */
  public String paging(HttpServletRequest request, int currentPage) throws Exception {
    int pagePerBlock = this.pagePerBlock; // 블럭당 페이지 수 기본값은 10페이지

    // 검색 레코드수 산출
    int searchCount = recordCount(request);

    // 전체 페이지 산출
    int totalPage = pageCount(searchCount);

    // 전체 블럭 산출
    int totalBlock = blockCount(totalPage);

    // 현재 블럭 산출
    int nowBlock = nowBlock(currentPage);

    StringBuffer sb = new StringBuffer();

    // 페이징 시작
    if (searchCount > 0) { // 레코드가 존재한다면
      sb.append("[<a href='javascript:goPage(0)'>");
      sb.append("<span class='span_box_1'>");
      sb.append("처음 페이지</span></A>&nbsp;&nbsp;");

      // 현재 나열된 페이지가 11페이지 이상일 경우
      // 이전 10페이지 출력
      // 0 Block: 1~10 page
      // 1 Block: 11~20 page
      if (nowBlock > 0) { // 1 이상임으로 이전블럭 이동 가능
        // 이전 10개 링크, 이전 블럭으로 이동
        // 1 Block --> ((1 - 1) * 10) = 0 Block page 0
        // 2 Block --> ((2 - 1) * 10) = 1 Block page 10
        sb.append("<a href='javascript:goPage("
            + ((nowBlock - 1) * pagePerBlock) + ")'>");
        sb.append("<span class='span_box_1'>");
        sb.append("&lt; 이전</span></a>] ");

      }

      // 페이지 목록 출력
      for (int i = 0; i < pagePerBlock; i++) {
        sb.append("<a href='javascript:goPage("
            + ((nowBlock * pagePerBlock) + i) + ")'>");
        sb.append("<span class='span_box_1'>");
        sb.append((nowBlock * pagePerBlock) + i + 1); // 페이지 출력
        sb.append("</span></a>&nbsp;");

        // 마지막 페이지이면 페이지 번호 출력을 종료
        // 페이지는 0부터 시작임으로 +1을하여 마지막 페이지인지
        // 검사
        if ((nowBlock * pagePerBlock) + i + 1 == totalPage)
          break;
      }

      // 다음 10개 출력
      // nowBlock은 0부터 시작임으로 +1을하여 블럭 이동여부 결정
      if (nowBlock + 1 < totalBlock) {
        // 다음 블럭으로 이동 링크
        sb.append("[<a href='javascript:goPage("
            + ((nowBlock + 1) * pagePerBlock) + ")'>");
        sb.append("<span class='span_box_1'>");
        sb.append("다음 &gt;</span></a> ");
      }

      // 페이지 0부터 시작임으로 -1을 함.
      sb.append("[<a href='javascript:goPage(" + (totalPage - 1) + ")'>");
      sb.append("<span class='span_box_1'>");
      sb.append("&nbsp;마지막 페이지</span></A>");

    }

    return sb.toString();
  }

  /**
   * 전체 페이지수 산출, 하나의 페이지는 레코드가 1개 있어도 하나의 페이지로 처리되어야 합니다. 따라서 0.1을 1로 자리올림합니다.<br>
   * 레코드 1개: 0.1 --> 1로 올림<br>
   * 레코드 11개: 1.1 --> 2로 올림<br>
   * 레코드 25개: 2.5 --> 3로 올림<br>
   * 
   * @param count
   *          검색된 레코드 수
   * @return
   */
  public int pageCount(int count) {
    double _count = (double) count; // 정수형을 double로 변환
    // System.out.println(_count); // 1.0, 11.0

    // 1.0 / 10 --> 0.1 --> 1.0, numPerPage: 페이지당 레코드 수, 10
    // 11.0 / 10 --> 1.1 --> 2.0
    double retVal = Math.ceil(_count / this.recordPerPage);
    // System.out.println(retVal); // 1.0, 2.0

    return (int) retVal; // 소수점 짤라버림, 1, 2
  }

  /**
   * 전체 블럭 수
   * 
   * @param count
   *          페이지 수
   * @return
   */
  public int blockCount(int count) {
    double _count = (double) count;

    double retVal = Math.ceil(_count / this.pagePerBlock);

    return (int) (retVal);
  }

  /**
   * 페이지의 시작레코드번호 nowPage: 현재 페이지 numPerPage: 페이지당 출력할 레코드 수 ★ 현재 페이지 번호 * 페이지당
   * 레코드 수 1 Page = (0 * 10) + 1 --> 1 (rownum 값) 2 Page = (1 * 10) + 1 --> 11 3
   * Page = (2 * 10) + 1 --> 21
   * 
   * @param nowPage
   *          현재 페이지
   * @return
   */
  public int beginOfPage(int nowPage) {
    return (nowPage * this.recordPerPage) + 1; // rownum은 최소값 1

  }

  /**
   * 현재 블럭수를 리턴합니다.
   * 
   * @param nowPage
   *          현재 페이지 번호
   * @return 현재 블럭 번호
   */
  public int nowBlock(int nowPage) {
    int retVal = nowPage / this.pagePerBlock;
    // 현재 블럭 = 현제 페이지/ 블러당 페이지 수
    // 1 --> 1 / 10 --> 0 Block
    // 11 --> 11 / 10 --> 1 Block
    // 15 --> 15 / 10 --> 1 Block
    // 21 --> 21 / 10 --> 2 Block

    return retVal;
  }

  // ----------------------------------------------------------
  // 페이징 관련 메소드 끝
  // ----------------------------------------------------------

}


