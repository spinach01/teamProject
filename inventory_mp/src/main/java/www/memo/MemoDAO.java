package www.memo; // 보안등의 문제로.. jsp에는 sql관련은 넣지 않음.. 공개될 우려있음

import inventory.project.bbs.ClientbbsDTO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import www.utility.DBOpen;
import www.utility.DBClose;

// 포인트는 메소드임....(중요 패턴 반복)
public class MemoDAO {
  DBOpen dbopen = null; // 필드
  DBClose dbclose = null;
  
  // 생성자. 리턴값이 없음
  public MemoDAO(){
    dbopen = new DBOpen();
    dbclose = new DBClose();    
  }

 // 목록
 public ArrayList list(){ // oop개념...
   Connection con = dbopen.getConnection();  // DBMS 연결
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   ArrayList list = null;
   
   try{
     StringBuffer sqlsb = new StringBuffer();
     sqlsb.append(" SELECT memono, title, content, wdate, viewcnt");
     sqlsb.append(" FROM memo ");
     sqlsb.append(" ORDER BY memono ASC");

     pstmt = con.prepareStatement(sqlsb.toString());

     rs = pstmt.executeQuery();
     
     list = new ArrayList();  // 객체 저장소
     
     while(rs.next() == true){  // 첫번째 레코드로 이동. 다음부터 다음레코드로 이동
//       System.out.println(rs.getInt("memono"));
//       System.out.println(rs.getString("title"));
//       System.out.println(rs.getString("content"));
//       System.out.println(rs.getString("wdate"));
//       System.out.println(rs.getInt("viewcnt"));
//       System.out.println("------------------------------");
       MemoVO vo = new MemoVO();
       
       int memono = rs.getInt("memono");   // DBMS 컬럼 -> Java 변수
       vo.setMemono(memono);
       
       String title = rs.getString("title");
       vo.setTitle(title);
       
       String content = rs.getString("content");
       vo.setContent(content);

       String wdate = rs.getString("wdate");
       vo.setWdate(wdate); 
       
       int viewcnt = rs.getInt("viewcnt");
       vo.setViewcnt(viewcnt);
       
       list.add(vo);  // 만들어진 하나의 객체를 저장소에 추가       
     } // vo값들이 계속 순환하면서 값을 가져와 set함..
     
   }catch(Exception e){
     System.out.println(e.toString());     
   }finally{
     dbclose.close(con, pstmt, rs); // colse를 안하면 buffer가 쌓여서 다운될수 있음    
   }
   return list; // 위에서 colse하기 때문에 rs 리턴 안됨 // 리턴 타입이 맞지 않아 에러=>리턴타입 수정.
 }

 
 
 /**
  * 
  * @param count
  * @return
  */
 public ArrayList list(int count) {
   Connection con = dbopen.getConnection(); // DBMS 연결
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   ArrayList list = null;

   // 10: 페이지당 출력할 레코드 갯수
   int startRow = 1; 
   int endRow = count;
   
   try {
     StringBuffer sql = new StringBuffer();
   
     sql.append(" SELECT memono, title, content, wdate, viewcnt, r");
     sql.append(" FROM(");
     sql.append("      SELECT memono, title, content, wdate, viewcnt, rownum as r");
     sql.append("      FROM (");
     sql.append("           SELECT memono, title, content, wdate, viewcnt");
     sql.append("           FROM memo ");
     sql.append("           ORDER BY memono DESC");
     sql.append("      )");
     sql.append(" )     ");
     sql.append(" WHERE r >= "+startRow+" AND r <= "+endRow);
       
     pstmt = con.prepareStatement(sql.toString());
       
     rs = pstmt.executeQuery();
     list = new ArrayList(); // 객체 저장소

     while (rs.next() == true) { // 첫번째 레코드로 이동, 다음부터 다음레코드로 이동
      MemoVO vo = new MemoVO();
       
      vo.setMemono(rs.getInt("memono"));       
      vo.setTitle(rs.getString("title"));       
      vo.setContent(rs.getString("content"));
      vo.setWdate(rs.getString("wdate"));        
      vo.setViewcnt(rs.getInt("viewcnt"));

       list.add(vo); // 만들어진 하나의 객체를 저장소에 추가
     }

   } catch (Exception e) {
     System.out.println(e.toString());
   } finally {
     dbclose.close(con, pstmt, rs);
   }

   return list;
 }   
 // 조회수 증가....
 public void viewcntUpdate(int memono){
   Connection con = dbopen.getConnection();  // DBMS 연결
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   ArrayList list = null;
   
   try{
     StringBuffer sqlsb = new StringBuffer();
     sqlsb.append(" UPDATE memo");
     sqlsb.append(" SET viewcnt = viewcnt + 1 ");
     sqlsb.append(" WHERE memono = ?");// order은 정렬임../where가 필요함...//1->?
     pstmt = con.prepareStatement(sqlsb.toString());
     pstmt.setInt(1, memono);
     pstmt.executeUpdate();
   }catch(Exception e){
     System.out.println(e.toString());
   }finally{
     dbclose.close(con, pstmt);
   }
 }
 
 // 조회
 public MemoVO read(int memono){
   Connection con = dbopen.getConnection();  // DBMS 연결
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   ArrayList list = null;
   MemoVO vo = null;
   
   try{
     StringBuffer sqlsb = new StringBuffer();
     sqlsb.append(" SELECT memono, title, content, wdate, viewcnt");
     sqlsb.append(" FROM memo ");
     sqlsb.append(" WHERE memono = ?");// order은 정렬임../where가 필요함...//1->?
     pstmt = con.prepareStatement(sqlsb.toString());
     pstmt.setInt(1, memono);
     
     rs = pstmt.executeQuery();
     if(rs.next() == true){ // 첫번째 레코드로 갔다면?
       vo = new MemoVO();
       vo.setMemono(rs.getInt("memono"));
       vo.setTitle(rs.getString("title"));
       vo.setContent(rs.getString("content"));
       vo.setWdate(rs.getString("wdate"));
       vo.setViewcnt(rs.getInt("viewcnt"));
     }
   
   }catch(Exception e){
     System.out.println(e.toString());
   }finally{
     dbclose.close(con, pstmt, rs);
   }
   
   return vo; 
 }
 
 // 등록, 등록한 레코드 갯수 리턴: 0, 1
 // public int create(String title, String content){
 public int create(MemoVO vo){   
   Connection con = dbopen.getConnection();  // DBMS 연결
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   ArrayList list = null;
   int cnt = 0;
   
   try{
     StringBuffer sqlsb = new StringBuffer();
     sqlsb.append(" INSERT INTO memo(memono, title, content, wdate)");
     sqlsb.append(" VALUES(memo_seq.nextval,? ,? , sysdate)");

     pstmt = con.prepareStatement(sqlsb.toString());
     //pstmt.setString(1, title);
     pstmt.setString(1, vo.getTitle());
     //pstmt.setString(2, content);
     pstmt.setString(2, vo.getContent());

     cnt = pstmt.executeUpdate(); 
   }catch(Exception e){
     System.out.println(e.toString());
   }finally{
     dbclose.close(con, pstmt);
   }
   
   return cnt;
 }
 
 // 메모 수정
 public int update(MemoVO vo){  // 리턴, 메소드명, 무엇을 받느지 끊임없이 생각 
   Connection con = dbopen.getConnection();  // DBMS 연결
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   ArrayList list = null;
   int cnt = 0;
   
   try{
     StringBuffer sqlsb = new StringBuffer();
     sqlsb.append(" UPDATE memo");
     sqlsb.append(" SET title=?, content=?");
     sqlsb.append(" WHERE memono=?");

     pstmt = con.prepareStatement(sqlsb.toString());
     pstmt.setString(1, vo.getTitle());
     pstmt.setString(2, vo.getContent());
     pstmt.setInt(3, vo.getMemono());

     cnt = pstmt.executeUpdate(); // 업데이트한 갯수 리턴
   }catch(Exception e){
     System.out.println(e.toString());
   }finally{
     dbclose.close(con, pstmt);
   }
   
   return cnt;
 } 
 
 // 삭제
 public int delete(int memono){
   Connection con = dbopen.getConnection();  // DBMS 연결
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   ArrayList list = null;
   int cnt = 0;
   
   try{
     StringBuffer sqlsb = new StringBuffer();
     sqlsb.append(" DELETE FROM memo");
     sqlsb.append(" WHERE memono=?");
     
     pstmt = con.prepareStatement(sqlsb.toString());
     pstmt.setInt(1, memono);
     
     cnt = pstmt.executeUpdate(); // 업데이트한 갯수 리턴
   }catch(Exception e){
     System.out.println(e.toString());
   }finally{
     dbclose.close(con, pstmt);
   }
   
   return cnt;
 }
  

}// 리스트등 메소드간 주고 받는 관계를 다시 한번 공부함...빈즈와 open/close함....
