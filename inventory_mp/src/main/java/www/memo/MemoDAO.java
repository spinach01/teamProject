package www.memo; // ���ȵ��� ������.. jsp���� sql������ ���� ����.. ������ �������

import inventory.project.bbs.ClientbbsDTO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import www.utility.DBOpen;
import www.utility.DBClose;

// ����Ʈ�� �޼ҵ���....(�߿� ���� �ݺ�)
public class MemoDAO {
  DBOpen dbopen = null; // �ʵ�
  DBClose dbclose = null;
  
  // ������. ���ϰ��� ����
  public MemoDAO(){
    dbopen = new DBOpen();
    dbclose = new DBClose();    
  }

 // ���
 public ArrayList list(){ // oop����...
   Connection con = dbopen.getConnection();  // DBMS ����
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
     
     list = new ArrayList();  // ��ü �����
     
     while(rs.next() == true){  // ù��° ���ڵ�� �̵�. �������� �������ڵ�� �̵�
//       System.out.println(rs.getInt("memono"));
//       System.out.println(rs.getString("title"));
//       System.out.println(rs.getString("content"));
//       System.out.println(rs.getString("wdate"));
//       System.out.println(rs.getInt("viewcnt"));
//       System.out.println("------------------------------");
       MemoVO vo = new MemoVO();
       
       int memono = rs.getInt("memono");   // DBMS �÷� -> Java ����
       vo.setMemono(memono);
       
       String title = rs.getString("title");
       vo.setTitle(title);
       
       String content = rs.getString("content");
       vo.setContent(content);

       String wdate = rs.getString("wdate");
       vo.setWdate(wdate); 
       
       int viewcnt = rs.getInt("viewcnt");
       vo.setViewcnt(viewcnt);
       
       list.add(vo);  // ������� �ϳ��� ��ü�� ����ҿ� �߰�       
     } // vo������ ��� ��ȯ�ϸ鼭 ���� ������ set��..
     
   }catch(Exception e){
     System.out.println(e.toString());     
   }finally{
     dbclose.close(con, pstmt, rs); // colse�� ���ϸ� buffer�� �׿��� �ٿ�ɼ� ����    
   }
   return list; // ������ colse�ϱ� ������ rs ���� �ȵ� // ���� Ÿ���� ���� �ʾ� ����=>����Ÿ�� ����.
 }

 
 
 /**
  * 
  * @param count
  * @return
  */
 public ArrayList list(int count) {
   Connection con = dbopen.getConnection(); // DBMS ����
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   ArrayList list = null;

   // 10: �������� ����� ���ڵ� ����
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
     list = new ArrayList(); // ��ü �����

     while (rs.next() == true) { // ù��° ���ڵ�� �̵�, �������� �������ڵ�� �̵�
      MemoVO vo = new MemoVO();
       
      vo.setMemono(rs.getInt("memono"));       
      vo.setTitle(rs.getString("title"));       
      vo.setContent(rs.getString("content"));
      vo.setWdate(rs.getString("wdate"));        
      vo.setViewcnt(rs.getInt("viewcnt"));

       list.add(vo); // ������� �ϳ��� ��ü�� ����ҿ� �߰�
     }

   } catch (Exception e) {
     System.out.println(e.toString());
   } finally {
     dbclose.close(con, pstmt, rs);
   }

   return list;
 }   
 // ��ȸ�� ����....
 public void viewcntUpdate(int memono){
   Connection con = dbopen.getConnection();  // DBMS ����
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   ArrayList list = null;
   
   try{
     StringBuffer sqlsb = new StringBuffer();
     sqlsb.append(" UPDATE memo");
     sqlsb.append(" SET viewcnt = viewcnt + 1 ");
     sqlsb.append(" WHERE memono = ?");// order�� ������../where�� �ʿ���...//1->?
     pstmt = con.prepareStatement(sqlsb.toString());
     pstmt.setInt(1, memono);
     pstmt.executeUpdate();
   }catch(Exception e){
     System.out.println(e.toString());
   }finally{
     dbclose.close(con, pstmt);
   }
 }
 
 // ��ȸ
 public MemoVO read(int memono){
   Connection con = dbopen.getConnection();  // DBMS ����
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   ArrayList list = null;
   MemoVO vo = null;
   
   try{
     StringBuffer sqlsb = new StringBuffer();
     sqlsb.append(" SELECT memono, title, content, wdate, viewcnt");
     sqlsb.append(" FROM memo ");
     sqlsb.append(" WHERE memono = ?");// order�� ������../where�� �ʿ���...//1->?
     pstmt = con.prepareStatement(sqlsb.toString());
     pstmt.setInt(1, memono);
     
     rs = pstmt.executeQuery();
     if(rs.next() == true){ // ù��° ���ڵ�� ���ٸ�?
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
 
 // ���, ����� ���ڵ� ���� ����: 0, 1
 // public int create(String title, String content){
 public int create(MemoVO vo){   
   Connection con = dbopen.getConnection();  // DBMS ����
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
 
 // �޸� ����
 public int update(MemoVO vo){  // ����, �޼ҵ��, ������ �޴��� ���Ӿ��� ���� 
   Connection con = dbopen.getConnection();  // DBMS ����
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

     cnt = pstmt.executeUpdate(); // ������Ʈ�� ���� ����
   }catch(Exception e){
     System.out.println(e.toString());
   }finally{
     dbclose.close(con, pstmt);
   }
   
   return cnt;
 } 
 
 // ����
 public int delete(int memono){
   Connection con = dbopen.getConnection();  // DBMS ����
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
     
     cnt = pstmt.executeUpdate(); // ������Ʈ�� ���� ����
   }catch(Exception e){
     System.out.println(e.toString());
   }finally{
     dbclose.close(con, pstmt);
   }
   
   return cnt;
 }
  

}// ����Ʈ�� �޼ҵ尣 �ְ� �޴� ���踦 �ٽ� �ѹ� ������...����� open/close��....
