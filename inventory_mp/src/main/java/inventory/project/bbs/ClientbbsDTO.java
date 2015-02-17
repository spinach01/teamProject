package inventory.project.bbs;

public class ClientbbsDTO {
  private int qnano;
  private String wname;
  private String title;
  private String content;
  private String passwd;
  private int viewcnt;
  private String wdate;
  private int grpno;
  private int indent;
  private int ansnum;
  private String id;
  
  public int getQnano() {
    return qnano;
  }
  public void setQnano(int qnano) {
    this.qnano = qnano;
  }
  public String getWname() {
    return wname;
  }
  public void setWname(String wname) {
    this.wname = wname;
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
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  public int getViewcnt() {
    return viewcnt;
  }
  public void setViewcnt(int viewcnt) {
    this.viewcnt = viewcnt;
  }
  public String getWdate() {
    return wdate;
  }
  public void setWdate(String wdate) {
    this.wdate = wdate;
  }
  public int getGrpno() {
    return grpno;
  }
  public void setGrpno(int grpno) {
    this.grpno = grpno;
  }
  public int getIndent() {
    return indent;
  }
  public void setIndent(int indent) {
    this.indent = indent;
  }
  public int getAnsnum() {
    return ansnum;
  }
  public void setAnsnum(int ansnum) {
    this.ansnum = ansnum;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  
  @Override
  public String toString() {
    return "ClientbbsDTO [qnano=" + qnano + ", wname=" + wname + ", title="
        + title + ", content=" + content + ", passwd=" + passwd + ", viewcnt="
        + viewcnt + ", wdate=" + wdate + ", grpno=" + grpno + ", indent="
        + indent + ", ansnum=" + ansnum + ", id=" + id + "]";
  }  
}
