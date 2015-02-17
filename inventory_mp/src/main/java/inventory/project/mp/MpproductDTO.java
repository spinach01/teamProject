package inventory.project.mp;

import org.springframework.web.multipart.MultipartFile;

public class MpproductDTO {
  private int productno;
  private String mpname;
  private int price;
  private String rdate;
  private String fname;
  private int mcount;
  private String content;
  private String volume;
  private String ftype;
  private String etc;
  private int mpgroupno;
  private String id;
  
  private String col;
  private String colName;  // 검색
  private String colFtype; // 검색
  
  private int nowPage;
  private int recordPerPage;
  private int totalRecord;
  
  private String chkid;
  
  /** 스프링 파일 객체*/
  /** <INPUT type='file' name='fnameMF' size='50'> */
  /** 자동으로 들어가기에 MF로 해야함.. 요령 방법임..*/
  private MultipartFile fnameMF;  
  /** 파일 처리하는 방법이 다름 : multipart file 추가: 파일에 한해서~~ */
  /*************************************/

  public int getProductno() {
    return productno;
  }

  public void setProductno(int productno) {
    this.productno = productno;
  }

  public String getMpname() {
    return mpname;
  }

  public void setMpname(String mpname) {
    this.mpname = mpname;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public int getMcount() {
    return mcount;
  }

  public void setMcount(int mcount) {
    this.mcount = mcount;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getVolume() {
    return volume;
  }

  public void setVolume(String volume) {
    this.volume = volume;
  }

  public String getFtype() {
    return ftype;
  }

  public void setFtype(String ftype) {
    this.ftype = ftype;
  }

  public String getEtc() {
    return etc;
  }

  public void setEtc(String etc) {
    if(etc == null)
      etc = "";
    this.etc = etc;
  }

  public int getMpgroupno() {
    return mpgroupno;
  }

  public void setMpgroupno(int mpgroupno) {
    this.mpgroupno = mpgroupno;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public MultipartFile getFnameMF() {
    return fnameMF;
  }

  public void setFnameMF(MultipartFile fnameMF) {
    this.fnameMF = fnameMF;
  }

  public String getCol() {
    return col;
  }

  public void setCol(String col) {
    this.col = col;
  }

  public String getColName() {
    return colName;
  }

  public void setColName(String colName) {
    this.colName = colName;
  }

  public String getColFtype() {
    return colFtype;
  }

  public void setColFtype(String colFtype) {
    this.colFtype = colFtype;
  }

  public int getNowPage() {
    return nowPage;
  }

  public void setNowPage(int nowPage) {    
    this.nowPage = nowPage;
  }

  public int getRecordPerPage() {
    return recordPerPage;
  }

  public void setRecordPerPage(int recordPerPage) {
    this.recordPerPage = recordPerPage;
  }

  public int getTotalRecord() {
    return totalRecord;
  }

  public void setTotalRecord(int totalRecord) {
    this.totalRecord = totalRecord;
  }
  
  public String getChkid() {
    return chkid;
  }

  public void setChkid(String chkid) {
    this.chkid = chkid;
  }
    

}
