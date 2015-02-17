package www.makeup_project;

public class EmployeeDTO {
  private String id;
  private String passwd;
  private String mname;
  private String tel;
  private String email;
  private String zipcode;
  private String address1;
  private String address2;
  private String position;
  private String mdate;
  private String fname;
  private String mtype;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  public String getMname() {
    return mname;
  }
  public void setMname(String mname) {
    this.mname = mname;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getZipcode() {
    return zipcode;
  }
  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }
  public String getAddress1() {
    return address1;
  }
  public void setAddress1(String address1) {
    this.address1 = address1;
  }
  public String getAddress2() {
    return address2;
  }
  public void setAddress2(String address2) {
    this.address2 = address2;
  }
  public String getPosition() {
    return position;
  }
  public void setPosition(String position) {
    this.position = position;
  }
  public String getMdate() {
    return mdate;
  }
  public void setMdate(String mdate) {
    this.mdate = mdate;
  }
  public String getFname() {
    return fname;
  }
  public void setFname(String fname) {
    this.fname = fname;
  }
  public String getMtype() {
    return mtype;
  }
  public void setMtype(String mtype) {
    this.mtype = mtype;
  }
  
  @Override
  public String toString() {
    return "EmployeeDTO [id=" + id + ", passwd=" + passwd + ", mname=" + mname
        + ", tel=" + tel + ", email=" + email + ", zipcode=" + zipcode
        + ", address1=" + address1 + ", address2=" + address2 + ", position="
        + position + ", mdate=" + mdate + ", fname=" + fname + ", mtype="
        + mtype + "]";
  }  
}
