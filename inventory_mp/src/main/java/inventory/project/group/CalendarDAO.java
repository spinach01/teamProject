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
  /** ���̺� �̸� */
  private String table_name = "calendar";

  /** ���� ������, 0 ���������� ����~ */
  private int currentPage = 0;

  /** �������� ����� ���ڵ� �� */
  private int recordPerPage = 100;

  /** ���� ������ �� */
  private int pagePerBlock = 10;

  DBOpen dbopen = null; // �ʵ�
  DBClose dbclose = null;

  /**
   * �⺻ ������
   */
  public CalendarDAO() {
    dbopen = new DBOpen();
    dbclose = new DBClose();
  }

  /**
   * �Ѱ��� �����͸� �߰��մϴ�.
   * 
   * @param con
   *          �����ͺ��̽� ����
   * @param dto
   *          ������ ��ü
   * @return 1: �߰� ����, 2: �߰� ����
   * @throws SQLException
   *           DBMS Error
   */
  public int create(CalendarDTO dto) throws SQLException {
    Connection con = null;
    PreparedStatement pstmt = null; // SQL ���� ��ü
    ResultSet rs = null; // SELECT ��� ���� ��ü
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

    // �߰��� ���ڵ��� 1�� retVal ������ ����
    count = pstmt.executeUpdate();

    dbclose.close(con, pstmt, rs);

    return count; // 1 or 0
  }

  /**
   * �������� �׷��ȣ�� ���ؼ� ��ü ����� �����ɴϴ�.
   * 
   * @param pdsgrpno
   *          �׷��ȣ
   * @return �˻� ���
   * @throws SQLException
   */
  public ArrayList list(HttpServletRequest request, int currentPage) throws SQLException {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StringBuffer sql = null;
    ArrayList list = null; // dto ����� ����
    int limit = currentPage * this.recordPerPage; // ����¡

    con = this.dbopen.getConnection();
    list = new ArrayList();

    sql = new StringBuffer();


    sql.append(" SELECT calendarno, labeldate, label, title, content, cnt, regdate ");
    sql.append(" FROM " + table_name);
    sql.append(" ORDER BY calendarno DESC");

    pstmt = con.prepareStatement(sql.toString());
    
    rs = pstmt.executeQuery(); // SELECT ���� ����

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
   * �������� �׷��ȣ�� ���ؼ� ��ü ����� �����ɴϴ�.
   * 
   * @param pdsgrpno
   *          �׷��ȣ
   * @return �˻� ���
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

    rs = pstmt.executeQuery(); // SELECT ���� ����
    
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
   * �۹�ȣ�� �̿��Ͽ� ���� �����ɴϴ�.
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
    pstmt.setInt(1, calendarno); // ù��° ?ǥ�� �۹�ȣ�� �Ҵ�

    rs = pstmt.executeQuery();

    if (rs.next()) { // ���ڵ� �����͸� ù��° ���ڵ�� �̵�
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
   * ���� �����մϴ�.
   * 
   * @param dto
   *          �� ��ü
   * @return 1: ���������� ����, 0: ���� ����
   */
  public int update(CalendarDTO dto) throws SQLException {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StringBuffer sql = null;
    con = this.dbopen.getConnection();

    int cnt = 0; // ������ ���ڵ� ���� ����

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
   * �ϳ��� �����͸� �����մϴ�.
   * 
   * @return �������� ������ true�� ���д� false�� �����մϴ�.
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

    // ������ ���ڵ��� �� ����
    cnt = pstmt.executeUpdate();

    dbclose.close(con, pstmt);

    return cnt;

  }

  // ----------------------------------------------------------
  // ����¡ ���� �޼ҵ� ����
  // ----------------------------------------------------------

  /**
   * ���� ���ڵ� ��
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

    if (rs.next()) { // ���ڵ� �����͸� ù��° ���ڵ�� �̵�
      cnt = rs.getInt("cnt");
    }

    dbclose.close(con, pstmt, rs);

    return cnt;

  }

  /**
   * ����¡�� ó���ϰ� ��ũ�� �����մϴ�.
   * 
   * @param col
   *          �˻� �÷�
   * @param word
   *          �˻���
   * @param nowPage
   *          ���� ������,���ʰ��� 0��
   * 
   * @return ����¡ ��ũ�� ������ �ִ� ���ڿ�
   */
  public String paging(HttpServletRequest request, int currentPage) throws Exception {
    int pagePerBlock = this.pagePerBlock; // ���� ������ �� �⺻���� 10������

    // �˻� ���ڵ�� ����
    int searchCount = recordCount(request);

    // ��ü ������ ����
    int totalPage = pageCount(searchCount);

    // ��ü �� ����
    int totalBlock = blockCount(totalPage);

    // ���� �� ����
    int nowBlock = nowBlock(currentPage);

    StringBuffer sb = new StringBuffer();

    // ����¡ ����
    if (searchCount > 0) { // ���ڵ尡 �����Ѵٸ�
      sb.append("[<a href='javascript:goPage(0)'>");
      sb.append("<span class='span_box_1'>");
      sb.append("ó�� ������</span></A>&nbsp;&nbsp;");

      // ���� ������ �������� 11������ �̻��� ���
      // ���� 10������ ���
      // 0 Block: 1~10 page
      // 1 Block: 11~20 page
      if (nowBlock > 0) { // 1 �̻������� ������ �̵� ����
        // ���� 10�� ��ũ, ���� ������ �̵�
        // 1 Block --> ((1 - 1) * 10) = 0 Block page 0
        // 2 Block --> ((2 - 1) * 10) = 1 Block page 10
        sb.append("<a href='javascript:goPage("
            + ((nowBlock - 1) * pagePerBlock) + ")'>");
        sb.append("<span class='span_box_1'>");
        sb.append("&lt; ����</span></a>] ");

      }

      // ������ ��� ���
      for (int i = 0; i < pagePerBlock; i++) {
        sb.append("<a href='javascript:goPage("
            + ((nowBlock * pagePerBlock) + i) + ")'>");
        sb.append("<span class='span_box_1'>");
        sb.append((nowBlock * pagePerBlock) + i + 1); // ������ ���
        sb.append("</span></a>&nbsp;");

        // ������ �������̸� ������ ��ȣ ����� ����
        // �������� 0���� ���������� +1���Ͽ� ������ ����������
        // �˻�
        if ((nowBlock * pagePerBlock) + i + 1 == totalPage)
          break;
      }

      // ���� 10�� ���
      // nowBlock�� 0���� ���������� +1���Ͽ� �� �̵����� ����
      if (nowBlock + 1 < totalBlock) {
        // ���� ������ �̵� ��ũ
        sb.append("[<a href='javascript:goPage("
            + ((nowBlock + 1) * pagePerBlock) + ")'>");
        sb.append("<span class='span_box_1'>");
        sb.append("���� &gt;</span></a> ");
      }

      // ������ 0���� ���������� -1�� ��.
      sb.append("[<a href='javascript:goPage(" + (totalPage - 1) + ")'>");
      sb.append("<span class='span_box_1'>");
      sb.append("&nbsp;������ ������</span></A>");

    }

    return sb.toString();
  }

  /**
   * ��ü �������� ����, �ϳ��� �������� ���ڵ尡 1�� �־ �ϳ��� �������� ó���Ǿ�� �մϴ�. ���� 0.1�� 1�� �ڸ��ø��մϴ�.<br>
   * ���ڵ� 1��: 0.1 --> 1�� �ø�<br>
   * ���ڵ� 11��: 1.1 --> 2�� �ø�<br>
   * ���ڵ� 25��: 2.5 --> 3�� �ø�<br>
   * 
   * @param count
   *          �˻��� ���ڵ� ��
   * @return
   */
  public int pageCount(int count) {
    double _count = (double) count; // �������� double�� ��ȯ
    // System.out.println(_count); // 1.0, 11.0

    // 1.0 / 10 --> 0.1 --> 1.0, numPerPage: �������� ���ڵ� ��, 10
    // 11.0 / 10 --> 1.1 --> 2.0
    double retVal = Math.ceil(_count / this.recordPerPage);
    // System.out.println(retVal); // 1.0, 2.0

    return (int) retVal; // �Ҽ��� ©�����, 1, 2
  }

  /**
   * ��ü �� ��
   * 
   * @param count
   *          ������ ��
   * @return
   */
  public int blockCount(int count) {
    double _count = (double) count;

    double retVal = Math.ceil(_count / this.pagePerBlock);

    return (int) (retVal);
  }

  /**
   * �������� ���۷��ڵ��ȣ nowPage: ���� ������ numPerPage: �������� ����� ���ڵ� �� �� ���� ������ ��ȣ * ��������
   * ���ڵ� �� 1 Page = (0 * 10) + 1 --> 1 (rownum ��) 2 Page = (1 * 10) + 1 --> 11 3
   * Page = (2 * 10) + 1 --> 21
   * 
   * @param nowPage
   *          ���� ������
   * @return
   */
  public int beginOfPage(int nowPage) {
    return (nowPage * this.recordPerPage) + 1; // rownum�� �ּҰ� 1

  }

  /**
   * ���� ������ �����մϴ�.
   * 
   * @param nowPage
   *          ���� ������ ��ȣ
   * @return ���� �� ��ȣ
   */
  public int nowBlock(int nowPage) {
    int retVal = nowPage / this.pagePerBlock;
    // ���� �� = ���� ������/ ���� ������ ��
    // 1 --> 1 / 10 --> 0 Block
    // 11 --> 11 / 10 --> 1 Block
    // 15 --> 15 / 10 --> 1 Block
    // 21 --> 21 / 10 --> 2 Block

    return retVal;
  }

  // ----------------------------------------------------------
  // ����¡ ���� �޼ҵ� ��
  // ----------------------------------------------------------

}


