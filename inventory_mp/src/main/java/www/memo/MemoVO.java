package www.memo;

public class MemoVO { // private 변수들은 이 클래스 밖에서 접근 안됨.//source, getter, setter로 할 수 있음 // 오브젝트
  private int memono;
  private String title;
  private String content;
  private String wdate;
  private int viewcnt;
  
  public int getMemono() {
    return memono;
  }
  public void setMemono(int memono) {
    this.memono = memono;
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
    if(content == null)
      content ="";
    
    this.content = content;
  }
  public String getWdate() {
    return wdate;
  }
  public void setWdate(String wdate) {
    this.wdate = wdate;
  }
  public int getViewcnt() {
    return viewcnt;
  }
  public void setViewcnt(int viewcnt) {
    this.viewcnt = viewcnt;
  }  
} // 값을 저장하고 가져오기 위한 메소도일뿐!!!
