package inventory.project.group;

public class MpgroupDTO {
  private int mpgroupno; 
  private String mpgrname;
  
  public int getMpgroupno() {
    return mpgroupno;
  }
  public void setMpgroupno(int mpgroupno) {
    this.mpgroupno = mpgroupno;
  }
  public String getMpgrname() {
    return mpgrname;
  }
  public void setMpgrname(String mpgrname) {
    this.mpgrname = mpgrname;
  }
  
  
  @Override
  public String toString() {
    return "MpgroupDTO [mpgroupno=" + mpgroupno + ", mpgrname=" + mpgrname
        + "]";
  }
}
