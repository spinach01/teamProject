package inventory.project.group;

public class CalendarDTO {
/*
  calendarno  INT          NOT NULL   AUTO_INCREMENT PRIMARY KEY,
  labeldate   VARCHAR(10)  NOT NULL, -- 출력할 날짜 2013-10-20 
  label       VARCHAR(20)  NOT NULL, -- 달력에 출력될 레이블
  title       VARCHAR(100) NOT NULL, -- 제목(*)
  content     TEXT          NOT NULL, -- 글 내용
  cnt         INT           DEFAULT 0, -- 조회수
  regdate     DATETIME      NOT NULL  -- 등록 날짜  
 */

  /** 글 번호 */
  private int calendarno;
  /** 레이블 출력 날짜 */
  private String labeldate;
  /** 레이블 */
  private String label;  
  /** 제목 */
  private String title;
  /** 내용 */
  private String content;
  /** 조회수 */
  private int cnt;
  /** 글 등록일 */
  private String regdate;
  
  public int getCalendarno() {
    return calendarno;
  }
  public void setCalendarno(int calendarno) {
    this.calendarno = calendarno;
  }
  public String getLabeldate() {
    return labeldate;
  }
  public void setLabeldate(String labeldate) {
    this.labeldate = labeldate;
  }
  public String getLabel() {
    return label;
  }
  public void setLabel(String label) {
    this.label = label;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  public String getRegdate() {
    return regdate;
  }
  public void setRegdate(String regdate) {
    this.regdate = regdate;
  }
  
}



