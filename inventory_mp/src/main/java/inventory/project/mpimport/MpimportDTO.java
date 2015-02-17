package inventory.project.mpimport;

public class MpimportDTO {
  private int importno;
  private String imdate;
  private int imcount;
  private int imprice;
  private String imclient;
  private int productno;
  
  private int importgroupno;
  private int importmcount;
  private int importmprice;
  
  public int getImportno() {
    return importno;
  }
  public void setImportno(int importno) {
    this.importno = importno;
  }
  public String getImdate() {
    return imdate;
  }
  public void setImdate(String imdate) {
    this.imdate = imdate;
  }
  public int getImcount() {
    return imcount;
  }
  public void setImcount(int imcount) {
    this.imcount = imcount;
  }
  public int getImprice() {
    return imprice;
  }
  public void setImprice(int imprice) {
    this.imprice = imprice;
  }
  public String getImclient() {
    return imclient;
  }
  public void setImclient(String imclient) {
    if(imclient == null)
      imclient="";
    
    this.imclient = imclient;
  }
  public int getProductno() {
    return productno;
  }
  public void setProductno(int productno) {
    this.productno = productno;
  }
   
  public int getImportgroupno() {
    return importgroupno;
  }
  public void setImportgroupno(int importgroupno) {
    this.importgroupno = importgroupno;
  }
  public int getImportmcount() {
    return importmcount;
  }
  public void setImportmcount(int importmcount) {
    this.importmcount = importmcount;
  }
  public int getImportmprice() {
    return importmprice;
  }
  public void setImportmprice(int importmprice) {
    this.importmprice = importmprice;
  }  
  
}
