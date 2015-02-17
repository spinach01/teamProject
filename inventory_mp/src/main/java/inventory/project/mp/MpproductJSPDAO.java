package inventory.project.mp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import www.utility.DBClose;
import www.utility.DBOpen;

public class MpproductJSPDAO {
	DBOpen dbopen = null; // 필드
	DBClose dbclose = null;
	
	public MpproductJSPDAO(){
		dbopen = new DBOpen();
		dbclose = new DBClose();
	}
	
	
	//지정한 갯수만 목록에 출력합니다.
	 public ArrayList listTop(int count){
		// System.out.println("ddd"+count);
	   Connection con = dbopen.getConnection();  // DBMS 연결
	   System.out.println("dddd");
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   ArrayList list = null;
	   
	   // 10: 페이지당 출력할 레코드 갯수
	   int startRow = 1; 
	   int endRow = count;
	   
	   try{
	     StringBuffer sql = new StringBuffer();
	     
	     sql.append(" SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id, r");
	     sql.append(" FROM(");
	     sql.append("     SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id, rownum as r");
	     sql.append("     FROM(");        
	     sql.append("           SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id");
	     sql.append("           FROM mpproduct");
	     sql.append("           ORDER BY productno ASC");
	     sql.append("     )");
	     sql.append(" )");
	     sql.append(" WHERE r >= "+startRow+" AND r <= "+endRow);
	     
	     pstmt = con.prepareStatement(sql.toString());
	     rs = pstmt.executeQuery();
	     
	     list = new ArrayList();  // 객체 저장소
	     
	     while(rs.next() == true){ //첫번째 레코드로 이동, 다음부터 다음레코드로 이동
	       MpproductDTO dto = new MpproductDTO();
	         
	       dto.setProductno(rs.getInt("productno"));
	       dto.setMpname(rs.getString("mpname"));
	       dto.setPrice(rs.getInt("price"));
	       dto.setRdate(rs.getString("rdate"));
	       dto.setFname(rs.getString("fname"));
	       dto.setMcount(rs.getInt("mcount"));
	       dto.setContent(rs.getString("content"));
	       dto.setVolume(rs.getString("volume"));
	       dto.setFtype(rs.getString("ftype"));
	       dto.setEtc(rs.getString("etc"));
	       dto.setMpgroupno(rs.getInt("mpgroupno"));
	       dto.setId(rs.getString("id"));
	       
	       list.add(dto);
	     }
	     
	   }catch(Exception e){
	     System.out.println(e.toString());
	   }finally{
	     dbclose.close(con, pstmt, rs);
	   }

	   return list;
	 }

}
