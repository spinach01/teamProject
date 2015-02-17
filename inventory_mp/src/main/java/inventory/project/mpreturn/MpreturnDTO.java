package inventory.project.mpreturn;

public class MpreturnDTO {
  private int returnno;
  private String retdate;
  private int retcount;
  private int productno;
  
  private int returngroupno;  
  private int returnmcount; 
  
  public int getReturnno() {
    return returnno;
  }
  public void setReturnno(int returnno) {
    this.returnno = returnno;
  }
  public String getRetdate() {
    return retdate;
  }
  public void setRetdate(String retdate) {
    this.retdate = retdate;
  }
  public int getRetcount() {
    return retcount;
  }
  public void setRetcount(int retcount) {
    this.retcount = retcount;
  }
  public int getProductno() {
    return productno;
  }
  public void setProductno(int productno) {
    this.productno = productno;
  }
  
  public int getReturngroupno() {
    return returngroupno;
  }
  public void setReturngroupno(int returngroupno) {
    this.returngroupno = returngroupno;
  }
  
  public int getReturnmcount() {
    return returnmcount;
  }
  public void setReturnmcount(int returnmcount) {
    this.returnmcount = returnmcount;
  } 
  
}
