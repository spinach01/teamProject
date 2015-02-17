package inventory.project.bbs;

public class ClientdataDTO {
  private int cdatano;
  private String title;
  private String wname;
  private String fname;
  private long filesize;
  private String content;
  private String passwd;
  private int mviewcnt;
  private String rdate;
  private int grpno;
  private int indent;
  private int ansnum;
  private String id;
  
  public int getCdatano() {
    return cdatano;
  }
  public void setCdatano(int cdatano) {
    this.cdatano = cdatano;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getWname() {
    return wname;
  }
  public void setWname(String wname) {
    this.wname = wname;
  }
  public String getFname() {
    return fname;
  }
  public void setFname(String fname) {
    this.fname = fname;
  }
  public long getFilesize() {
    return filesize;
  }
  public void setFilesize(long filesize) {
    this.filesize = filesize;
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
  public int getMviewcnt() {
    return mviewcnt;
  }
  public void setMviewcnt(int mviewcnt) {
    this.mviewcnt = mviewcnt;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
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
    return "ClientdataDTO [cdatano=" + cdatano + ", wname=" + wname
        + ", title=" + title + ", fname=" + fname + ", filesize=" + filesize
        + ", content=" + content + ", passwd=" + passwd + ", mviewcnt="
        + mviewcnt + ", rdate=" + rdate + ", grpno=" + grpno + ", indent="
        + indent + ", ansnum=" + ansnum + ", id=" + id + "]";
  }
}
