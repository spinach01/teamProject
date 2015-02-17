package inventory.project.mpexport;

public class MpexportDTO {
  private int exportno;
  private String exdate;
  private int excount;
  private int exprice;
  private String excustumer;
  private int productno;
  
  private int exportgroupno;
  private int exportmcount;
  private int exportmprice;
  
  
  public int getExportno() {
    return exportno;
  }
  public void setExportno(int exportno) {
    this.exportno = exportno;
  }
  public String getExdate() {
    return exdate;
  }
  public void setExdate(String exdate) {
    this.exdate = exdate;
  }
  public int getExcount() {
    return excount;
  }
  public void setExcount(int excount) {
    this.excount = excount;
  }
  public int getExprice() {
    return exprice;
  }
  public void setExprice(int exprice) {
    this.exprice = exprice;
  }
  public String getExcustumer() {
    return excustumer;
  }
  public void setExcustumer(String excustumer) {
    if(excustumer == null)
       excustumer = "";
    this.excustumer = excustumer;
  }
  public int getProductno() {
    return productno;
  }
  public void setProductno(int productno) {
    this.productno = productno;
  }
  
  
  public int getExportgroupno() {
    return exportgroupno;
  }
  public void setExportgroupno(int exportgroupno) {
    this.exportgroupno = exportgroupno;  
  }
  
  public int getExportmcount() {
    return exportmcount;
  }
  public void setExportmcount(int exportmcount) {
    this.exportmcount = exportmcount;
  }
  
  public int getExportmprice() {
    return exportmprice;
  }
  public void setExportmprice(int exportmprice) {
    this.exportmprice = exportmprice;
  }   
}
