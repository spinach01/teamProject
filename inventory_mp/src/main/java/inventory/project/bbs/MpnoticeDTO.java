package inventory.project.bbs;

public class MpnoticeDTO {
  private int noticeno;
  private String title;
  private String wdate;
  private String fname;
  private String content;
  private int mviewcnt;
  
  public int getNoticeno() {
    return noticeno;
  }
  public void setNoticeno(int noticeno) {
    this.noticeno = noticeno;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getWdate() {
    return wdate;
  }
  public void setWdate(String wdate) {
    this.wdate = wdate;
  }
  public String getFname() {
    return fname;
  }
  public void setFname(String fname) {
    if(fname == null)
      fname = "-";
    this.fname = fname;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getMviewcnt() {
    return mviewcnt;
  }
  public void setMviewcnt(int mviewcnt) {
    this.mviewcnt = mviewcnt;
  }
  @Override
  public String toString() {
    return "MpnoticeDTO [noticeno=" + noticeno + ", title=" + title
        + ", wdate=" + wdate + ", fname=" + fname + ", content=" + content
        + ", mviewcnt=" + mviewcnt + "]";
  }
 
 
  

  
  
}