package www.makeup_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;






import www.utility.DBClose;
import www.utility.DBOpen;

public class EmployeeDAO {
  DBOpen dbopen = null; // 필드
  DBClose dbclose = null;
  
  /**
   * 생성자, 리턴값이 없음
   */
  public EmployeeDAO() {
    dbopen = new DBOpen();
    dbclose = new DBClose();
  }
  
 
  /**
   * 중복 아이디를 검사합니다.
   * @param id 아이디
   * @return 1: 중복, 0: 중복아님
   */
  public int duplicateId(String id){
    Connection con = dbopen.getConnection();  // DBMS 연결
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int cnt = 0;
    
    try{
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT COUNT(id) as cnt");
      sql.append(" FROM employee ");
      sql.append(" WHERE id=?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, id);
      
      rs = pstmt.executeQuery();
      
      if(rs.next() == true){ //첫번째 레코드로 이동, 다음부터 다음레코드로 이동
        cnt = rs.getInt("cnt");
      }
      
    }catch(Exception e){
      System.out.println(e.toString());
    }finally{
      dbclose.close(con, pstmt, rs);
    }

    return cnt;
  }
  
  /**
   * 이메일을 검사합니다.
   * @param email 이메일
   * @return 1: 중복, 0: 중복아님
   */
  public int duplicateEmail(String email){
    Connection con = dbopen.getConnection();  // DBMS 연결
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int cnt = 0;
    
    try{
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT COUNT(email) as cnt");
      sql.append(" FROM employee ");
      sql.append(" WHERE email=?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, email);
      
      rs = pstmt.executeQuery();
      
      if(rs.next() == true){ //첫번째 레코드로 이동, 다음부터 다음레코드로 이동
        cnt = rs.getInt("cnt");
      }
      
    }catch(Exception e){
      System.out.println(e.toString());
    }finally{
      dbclose.close(con, pstmt, rs);
    }

    return cnt;
  }

  /**
   * 우편번호 목록 출력
   * 
   * @return
   */
  public ArrayList zipcodeList(String dongli) {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL 실행
    ResultSet rs = null; // SELECT 결과 저장
    StringBuffer sql = null;
    int cnt = 0;
    ArrayList list = null;

    try {
      sql = new StringBuffer();
      sql.append(" SELECT zipcode, sido, gugun, dongli, etc");
      sql.append(" FROM zipcode");
      sql.append(" WHERE (dongli LIKE '%' || ? || '%')");
      sql.append(" ORDER BY sido, gugun, dongli");
      
      // ASC 오름차순 정렬임, 생략되어 있음
      // sql.append(" ORDER BY sido ASC, gugun ASC, dongli ASC");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, dongli);

      rs = pstmt.executeQuery();

      list = new ArrayList();
      while (rs.next() == true) {
        ZipcodeDTO dto = new ZipcodeDTO();
        // zipcode, sido, gugun, dongli, etc
        dto.setZipcode(rs.getString("zipcode"));
        dto.setSido(rs.getString("sido"));
        dto.setGugun(rs.getString("gugun"));
        dto.setDongli(rs.getString("dongli"));
        dto.setEtc(rs.getString("etc"));

        list.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace(); // 에러원인이 자세하게 출력되나 해킹의 대상
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return list;
  }

  /**
   * 회원을 가입니다.
   * @param dto
   * @return 성공 1, 실패 0
   */
  public int create(EmployeeDTO dto){
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    int cnt = 0;
    
    try{
      sql.append(" INSERT INTO employee(id, passwd, mname, tel, email, zipcode, ");
      sql.append(" address1, address2, position, mtype, mdate, fname)");
      sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?)");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, dto.getId());
      pstmt.setString(2, dto.getPasswd());
      pstmt.setString(3, dto.getMname());
      pstmt.setString(4, dto.getTel());
      pstmt.setString(5, dto.getEmail());
      pstmt.setString(6, dto.getZipcode());
      pstmt.setString(7, dto.getAddress1());
      pstmt.setString(8, dto.getAddress2());
      pstmt.setString(9, dto.getPosition());
      pstmt.setString(10, dto.getMtype());
      pstmt.setString(11, dto.getFname());
     
      cnt = pstmt.executeUpdate();
      
    }catch(Exception e){
      System.out.println(e.toString());
    }finally{
      dbclose.close(con, pstmt);
    }
    return cnt;
  }

  /**
   * 회원 목록 출력
   * 
   * @return
   */
  public ArrayList list() {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL 실행
    ResultSet rs = null; // SELECT 결과 저장
    StringBuffer sql = null;
    int cnt = 0;
    ArrayList list = null;

    try {
      sql = new StringBuffer();
      sql.append(" SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname");
      sql.append(" FROM employee");
      sql.append(" ORDER BY mdate DESC"); // ASC: 오름 차순 

      pstmt = con.prepareStatement(sql.toString());

      rs = pstmt.executeQuery();

      list = new ArrayList();
      while (rs.next() == true) { // 첫 번째 레코드, 두번째 레코드
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(rs.getString("id"));
        dto.setPasswd(rs.getString("passwd"));
        dto.setMname(rs.getString("mname"));
        dto.setTel(rs.getString("tel"));
        dto.setEmail(rs.getString("email"));
        dto.setZipcode(rs.getString("zipcode"));
        dto.setAddress1(rs.getString("address1"));
        dto.setAddress2(rs.getString("address2"));
        dto.setPosition(rs.getString("position"));
        dto.setMtype(rs.getString("mtype"));
        dto.setMdate(rs.getString("mdate"));
        dto.setFname(rs.getString("fname"));

        list.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return list;
  }

  /**
   * 회원구분 코드를 받아서 해당하는 값(레이블)을 리턴
   * @param key
   * @return
   */
  public String getCodeValue(String key) {
    Hashtable codes = new Hashtable();
    codes.put("A01", "사원");
    codes.put("A03", "대리");
    codes.put("A05", "과장");
    codes.put("A07", "부장");
    codes.put("A09", "차장");
    codes.put("A11", "상무");
    codes.put("A13", "전무");
    codes.put("A15", "부사장");
    codes.put("A17", "사장");
    codes.put("A98", "해당사항없음");
    codes.put("A99", "기타");
    
    Object value = codes.get(key); // A01 ~ A99키에 해당하는 값 추출

    return (String)(value); // 코드값에 해당하는 직업 리턴
  }

  /**
   * 회원 상세 정보
   * 
   * @return
   */
  public EmployeeDTO read(String id) {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL 실행
    ResultSet rs = null; // SELECT 결과 저장
    StringBuffer sql = null;
    int cnt = 0;
    ArrayList list = null;
    EmployeeDTO dto = null;

    try {
      sql = new StringBuffer();
      sql.append(" SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname");
      sql.append(" FROM employee");
      sql.append(" WHERE id=?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, id);

      rs = pstmt.executeQuery();
      rs.next();

      dto = new EmployeeDTO();

      dto.setId(rs.getString("id"));
      dto.setPasswd(rs.getString("passwd"));
      dto.setMname(rs.getString("mname"));
      dto.setTel(rs.getString("tel"));
      dto.setEmail(rs.getString("email"));
      dto.setZipcode(rs.getString("zipcode"));
      dto.setAddress1(rs.getString("address1"));
      dto.setAddress2(rs.getString("address2"));
      dto.setPosition(rs.getString("position"));
      dto.setMtype(rs.getString("mtype"));
      dto.setMdate(rs.getString("mdate"));
      dto.setFname(rs.getString("fname"));

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return dto;
  }

  /**
   * 회원 사진을 변경합니다.
   * @param id 회원 아이디
   * @param fname 파일명
   * @return 변경된 레코드 수 1, 0
   */
  public int updateFile(String id, String fname){
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    int cnt = 0;
    
    try{
      sql.append(" UPDATE employee");
      sql.append(" SET fname = ?");
      sql.append(" WHERE id = ?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, fname);
      pstmt.setString(2, id);
     
      cnt = pstmt.executeUpdate();
      
    }catch(Exception e){
      System.out.println(e.toString());
    }finally{
      dbclose.close(con, pstmt);
    }
    return cnt;
  }

  /**
   * 회원 정보 수정
   * @param dto
   * @return 가입된 회원의 수, 1 또는 0
   */
  public int update(EmployeeDTO dto) {
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null; // SQL 실행
    ResultSet rs = null; // SELECT 결과 저장
    StringBuffer sql = null;
    int cnt = 0;

    try {
      sql = new StringBuffer();
      sql.append(" UPDATE employee");
      sql.append(" SET passwd=?, mname=?, tel=?, email=?, zipcode=?,");
      sql.append("     address1=?, address2=?, position=?, mtype=?");
      sql.append(" WHERE id=?");

      pstmt = con.prepareStatement(sql.toString());

      pstmt.setString(1, dto.getPasswd());
      pstmt.setString(2, dto.getMname());
      pstmt.setString(3, dto.getTel());
      pstmt.setString(4,  dto.getEmail());
      pstmt.setString(5, dto.getZipcode());
      pstmt.setString(6, dto.getAddress1());
      pstmt.setString(7, dto.getAddress2());
      pstmt.setString(8, dto.getPosition());
      pstmt.setString(9, dto.getMtype());
      pstmt.setString(10, dto.getId());

      cnt = pstmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }

    return cnt;
  }

  /**
   * 회원을 삭제합니다.
   * @param id 삭제할 회원 아이디
   * @return 삭제 결과 1, 0
   */
  public int delete(String id){
    Connection con = dbopen.getConnection();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    int cnt = 0;
    
    try{
      sql.append(" DELETE FROM employee");
      sql.append(" WHERE id = ?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, id);
           
      cnt = pstmt.executeUpdate();
      
    }catch(Exception e){
      System.out.println(e.toString());
    }finally{
      dbclose.close(con, pstmt);
    }
    return cnt;
  }
  
  /**
   * 로그인 처리
   * @param id 아이디
   * @param passwd 패스워드
   * @return 1: 일치, 0: 일치하지 않음
   */
  public int loginCheck(String id, String passwd){
    Connection con = dbopen.getConnection();  // DBMS 연결
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int cnt = 0;
    
    try{
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT COUNT(id) as cnt");
      sql.append(" FROM employee ");
      sql.append(" WHERE id=? AND passwd=?");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, id);
      pstmt.setString(2, passwd);
      
      rs = pstmt.executeQuery();
      
      if(rs.next() == true){ //첫번째 레코드로 이동, 다음부터 다음레코드로 이동
        cnt = rs.getInt("cnt");
      }
      
    }catch(Exception e){
      System.out.println(e.toString());
    }finally{
      dbclose.close(con, pstmt, rs);
    }

    return cnt;
  }
  
  /**
   * 목록을 검색합니다.
   * @param col  검색 컬럼
   * @param word 검색어
   
   * @return BbsDTO.java가 저장된 객체 ArrayList
   */
  public ArrayList list(String col, String word) {
    Connection con = dbopen.getConnection(); // DBMS 연결
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList list = null;

    try {
      StringBuffer sql = new StringBuffer();
      
      word = word.trim();    //  문자열 좌우 공백 제거
      
      if (word.length() == 0){    // 검색을 안하는 경우
        sql.append(" SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname");
        sql.append(" FROM employee ");
        sql.append(" ORDER BY mdate DESC");
        pstmt = con.prepareStatement(sql.toString());
        
      }else{      // 검색을 하는 경우
        sql.append(" SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname");
        sql.append(" FROM employee ");
      
        if (col.equals("mtype")){
         sql.append(" WHERE mtype LIKE '%' || ? || '%'");
       }else if(col.equals("mtype")){
         sql.append(" WHERE mtype LIKE '%' || ? || '%'");
       }
       sql.append(" ORDER BY mdate DESC");
       pstmt = con.prepareStatement(sql.toString());
       pstmt.setString(1, word);
     }
     
      rs = pstmt.executeQuery();
      list = new ArrayList(); // 객체 저장소

      while (rs.next() == true) { // 첫번째 레코드로 이동, 다음부터 다음레코드로 이동
        EmployeeDTO dto = new EmployeeDTO();       
        dto.setId(rs.getString("id"));
        dto.setPasswd(rs.getString("passwd"));
        dto.setMname(rs.getString("mname"));
        dto.setTel(rs.getString("tel"));
        dto.setEmail(rs.getString("email"));
        dto.setZipcode(rs.getString("zipcode"));
        dto.setAddress1(rs.getString("address1"));
        dto.setAddress2(rs.getString("address2"));
        dto.setPosition(rs.getString("position"));
        dto.setMtype(rs.getString("mtype"));
        dto.setMdate(rs.getString("mdate"));
        dto.setFname(rs.getString("fname"));

        list.add(dto); // 만들어진 하나의 객체를 저장소에 추가
      }

    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return list;
  }
   
 /**
  * 목록을 검색합니다.
  * 쿼리순서
  *   SELECT
  *   FROM
  *   WHERE
  *   ORDER BY
  * @param col 검색컬럼
  * @param word 검색어
  * @param nowPage 현재 페이지
  * @param recordPerPage  페이지당 레코드 갯수
  * @return BbsDTO.jave가 저장된 객체 ArrayList
  */
  public ArrayList list(String col, String word, int nowPage, int recordPerPage) {
    Connection con = dbopen.getConnection(); // DBMS 연결
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList list = null;
    
    int startRow = ((nowPage-1) * 10) + 1;
    int endRow = nowPage * 10;
    
    /*
     * 1 page: WHERE r >= 1 AND r <= 10
     * 2 page: WHERE r >= 11 AND r <= 20
     * 
     */
    try {
      StringBuffer sql = new StringBuffer();
      
      word = word.trim();    //  문자열 좌우 공백 제거
      
      if (word.length() == 0){    // 검색을 안하는 경우
        sql.append(" SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname, rownum as r");
        sql.append(" FROM (");
        sql.append("     SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname, rownum as r");
        sql.append("     FROM  (");
        sql.append("             SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname");
        sql.append("             FROM employee ");
        sql.append("             ORDER BY mdate DESC");
        sql.append("       )");
        sql.append(" )");
        sql.append(" WHERE r >= "+startRow+" AND r <= "+ endRow);
        pstmt = con.prepareStatement(sql.toString()); 
        
      }else{      // 검색을 하는 경우
        sql.append(" SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname, rownum as r");
        sql.append(" FROM (");
        sql.append("     SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname, rownum as r");
        sql.append("     FROM  (");
        sql.append("             SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname");
        sql.append("             FROM employee ");
        if (col.equals(" mtype")){
         sql.append("            WHERE mtype LIKE '%' || ? || '%'");
       }else if(col.equals("mtype")){
         sql.append("            WHERE mtype LIKE '%' || ? || '%'");  
       }
       sql.append("              ORDER BY mdate DESC");
       sql.append("       )");
       sql.append(" )");
       sql.append(" WHERE r >= "+startRow+" AND r <= "+ endRow );
       
       pstmt = con.prepareStatement(sql.toString());
       pstmt.setString(1, word);
     }
     
      rs = pstmt.executeQuery();
      list = new ArrayList(); // 객체 저장소

      while (rs.next() == true) { // 첫번째 레코드로 이동, 다음부터 다음레코드로 이동
          EmployeeDTO dto = new EmployeeDTO();       
          dto.setId(rs.getString("id"));
          dto.setPasswd(rs.getString("passwd"));
          dto.setMname(rs.getString("mname"));
          dto.setTel(rs.getString("tel"));
          dto.setEmail(rs.getString("email"));
          dto.setZipcode(rs.getString("zipcode"));
          dto.setAddress1(rs.getString("address1"));
          dto.setAddress2(rs.getString("address2"));
          dto.setPosition(rs.getString("position"));
          dto.setMtype(rs.getString("mtype"));
          dto.setMdate(rs.getString("mdate"));
          dto.setFname(rs.getString("fname"));

        list.add(dto); // 만들어진 하나의 객체를 저장소에 추가
      }

    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return list;
  }
 
  /**
   * 
   * 멤버유형이 직원일때 회원 리스트 출력
   * @param col
   * @param word
   * @param nowPage
   * @param recordPerPage
   * @return
   */
  public ArrayList list1(String col, String word, int nowPage, int recordPerPage) {
    Connection con = dbopen.getConnection(); // DBMS 연결
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList list = null;

    EmployeeDTO dto = new EmployeeDTO(); 
    //EmployeeDTO employeeDTO = null;    
    
    int startRow = ((nowPage-1) * 10) + 1;
    int endRow = nowPage * 10;
    
    /*
     * 1 page: WHERE r >= 1 AND r <= 10
     * 2 page: WHERE r >= 11 AND r <= 20
     * 
     */
    try {
      StringBuffer sql = new StringBuffer();
      
      word = word.trim();    //  문자열 좌우 공백 제거
      
      if (word.length() == 0){    // 검색을 안하는 경우
        sql.append(" SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname, rownum as r");
        sql.append(" FROM (");
        sql.append("     SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname, rownum as r");
        sql.append("     FROM  (");
        sql.append("             SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname");
        sql.append("             FROM employee ");
        sql.append("             WHERE mtype=? ");
        sql.append("             ORDER BY mdate DESC");
        sql.append("       )");
        sql.append(" )");
        sql.append(" WHERE r >= "+startRow+" AND r <= "+ endRow);
        pstmt = con.prepareStatement(sql.toString());
        
        String mtype = "회원";
        pstmt.setString(1, mtype);        
        
      }else{      // 검색을 하는 경우
        sql.append(" SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname, rownum as r");
        sql.append(" FROM (");
        sql.append("     SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname, rownum as r");
        sql.append("     FROM  (");
        sql.append("             SELECT id, passwd, mname, tel, email, zipcode, address1, address2, position, mtype, mdate, fname");
        sql.append("             FROM employee ");
        if (col.equals(" mtype")){
         sql.append("            WHERE mtype LIKE '%' || ? || '%'");
       }else if(col.equals("mtype")){
         sql.append("            WHERE mtype LIKE '%' || ? || '%'");  
       }
       sql.append("              ORDER BY mdate DESC");
       sql.append("       )");
       sql.append(" )");
       sql.append(" WHERE r >= "+startRow+" AND r <= "+ endRow );
       
       pstmt = con.prepareStatement(sql.toString());
       pstmt.setString(1, word);
     }
     
      rs = pstmt.executeQuery();
      list = new ArrayList(); // 객체 저장소

      while (rs.next() == true) { // 첫번째 레코드로 이동, 다음부터 다음레코드로 이동
        //EmployeeDTO dto = new EmployeeDTO();
        
          dto.setId(rs.getString("id"));
          dto.setPasswd(rs.getString("passwd"));
          dto.setMname(rs.getString("mname"));
          dto.setTel(rs.getString("tel"));
          dto.setEmail(rs.getString("email"));
          dto.setZipcode(rs.getString("zipcode"));
          dto.setAddress1(rs.getString("address1"));
          dto.setAddress2(rs.getString("address2"));
          dto.setPosition(rs.getString("position"));
          dto.setMtype(rs.getString("mtype"));
          dto.setMdate(rs.getString("mdate"));
          dto.setFname(rs.getString("fname"));
          
          System.out.println("mtype>>>" + dto.getMtype() );

        list.add(dto); // 만들어진 하나의 객체를 저장소에 추가
      }

    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      dbclose.close(con, pstmt, rs);
    }

    return list;
  } 
  
  
  
  public int count(String col, String word) {
	     Connection con = dbopen.getConnection(); // DBMS 연결
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	     int cnt = 0;

	     try {
	       StringBuffer sql = new StringBuffer();
	       
	       word = word.trim();    //  문자열 좌우 공백 제거
	       
	       if (word.length() == 0){    // 검색을 안하는 경우
	         sql.append(" SELECT COUNT(*) as cnt");
	         sql.append(" FROM employee ");
	         pstmt = con.prepareStatement(sql.toString());
	         
	       }else{      // 검색을 하는 경우
	         sql.append(" SELECT COUNT(*) as cnt");
	         sql.append(" FROM employee ");
	       
	         if (col.equals("mtype")){
	          sql.append(" WHERE mtype LIKE '%' || ? || '%'");
	        }else if(col.equals("mtype")){
	          sql.append(" WHERE mtype LIKE '%' || ? || '%'");
	        }else if(col.equals("mtype")){
	          sql.append(" WHERE mtype LIKE '%' || ? || '%'");  
	        }
	        pstmt = con.prepareStatement(sql.toString());
	        pstmt.setString(1, word);
	      }
	      
	       rs = pstmt.executeQuery();
	        
	         if (rs.next() == true) { // 첫번째 레코드로 이동, 다음부터 다음레코드로 이동
	          cnt = rs.getInt("cnt");
	         
	       }

	     } catch (Exception e) {
	       System.out.println(e.toString());
	     } finally {
	       dbclose.close(con, pstmt, rs);
	     }

	     return cnt; 
	   }
	   
  
}