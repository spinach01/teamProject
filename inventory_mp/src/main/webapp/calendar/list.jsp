<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file ="ssi.jsp" %> 

<%
ArrayList list = null;
list = dao.list(request, currentPage);
 
//------------------------------------------------------------
//페이징 관련 
//------------------------------------------------------------
int recordCount = dao.recordCount(request); // 검색된 레코드 갯수
int pageCount = dao.pageCount(recordCount); // 전체 페이지수
//페이징 그룹핑 수, 화면하단에 나열되는 페이지들
int totalGroup = (int)(Math.ceil((double)pageCount/10));
//------------------------------------------------------------

%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>목록</title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 14px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script type="text/javascript">
  function f_update(calendarno){
    var f = document.frmData;
    f.action = './updateForm.jsp';
    f.calendarno.value = calendarno;
    f.submit();
  }
 
  function f_delete(calendarno){
    var f = document.frmData;
    f.action = './deleteForm.jsp';
    f.calendarno.value = calendarno;
    f.submit();
  } 
  
  function f_read(calendarno){
    var f = document.frmData;
    f.action = './read.jsp';
    f.calendarno.value = calendarno;
    f.submit();
  }
  
  function f_reply(calendarno){  // 목록 
      var f = document.frmData;
      f.action = './replyForm.jsp';
      f.calendarno.value = calendarno;
      f.submit();
  }

  function goPage(currentPage){
	    var f = document.frmData;
	    
	    f.action = "./list.jsp";
	    f.currentPage.value = currentPage;
	    
	    f.submit();
	}
  

</script>

</head>

 
<a href = './images/logo.png'></a>
<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

 
 <DIV class="title">일정 목록</DIV>


<div width="100%" align="center">
    등록된 글 : <%=recordCount%>
    <!-- 현재 페이지는 0부터 시작임으로 1페이지를 더한 후 출력 -->
    <% 
    if (recordCount == 0){ 
      out.println("(0  /  0 Pages)");
    }else{
      // nowPage는 0부터 시작
      out.println("(" + (currentPage+1) + " / " + pageCount + " Pages)");
    }
    %> 
    <input type='button' value='등록' 
      onclick="location.href='./createForm.jsp'" />
</div>    

<table width="98%" border="0" align='center' cellpadding='0' cellspacing='0'>
    <tr bgcolor="#AAAAAA">
        <th width='5%'>번호</th>
        <th width='10%'>레이블 날짜</th>
        <th width='10%'>레이블</th>
        <th width='45%'>제목</th>
        <th width='5%'>조회</th>        
        <th width='10%'>등록일</th>
        <th width='10%'>기타</th>
    </tr>

<%
int size = list.size();  // 저장된 객체의 수
    
for(int i=0; i<size; i++){
  Object object = list.get(i); // object 타입은 사용 못함.
  CalendarDTO dto = (CalendarDTO)object;
      
  int calendarno = dto.getCalendarno();
  String labeldate = dto.getLabeldate();
  String label = dto.getLabel();
  String title = dto.getTitle();
  int cnt = dto.getCnt();
  String regdate = dto.getRegdate();
%> 
  <tr bgcolor="#EEEEEE" 
      onMouseOver="this.style.backgroundColor='#ffffff'" 
      onMouseOut="this.style.backgroundColor='#EEEEEE'"> 
<%out.println("<td class='td_1'>" + calendarno + "</td>");
  
  out.println("<td align='left' class='td_1'>");
  out.println("<img src='./images/bu1.gif'>&nbsp;" + labeldate + "</a>");
  out.println("</td>");
  
  out.println("<td class='td_1' align='left'><a href='javascript:f_update(" + calendarno + ")'>" + label + "</a></td>");
  out.println("<td class='td_1' align='left'><a href='javascript:f_update(" + calendarno + ")'>" + title + "</a>");
  // 2013-02-07 형식으로 추출
  String date = dto.getRegdate().substring(0, 10);
  // 이틀 전날것까지 새글 처리
  if (Utility.getTimeNew(date, 2)){
    out.println("<img src='./images/new.gif'>");
  }
  out.println("</td>");
  out.println("<td class='td_1'>" + cnt + "</td>");
  out.println("<td class='td_1'>" + regdate.substring(0, 10) + "</td>");
%>

<%  
  out.println("  <td class='td_1'>");
  out.println("    <input type='button' value='U' onclick='f_update("+calendarno+")' title='수정'>");
  out.println("    <input type='button' value='D' onclick='f_delete("+calendarno+")' title='삭제'>");
  out.println("    <input type='button' value='R' onclick='f_reply("+calendarno+")' title='답변'>");
  out.println("  </td>");
  out.println("</tr>");
}

%>    
    </table>

<div align="center" class="paging">
    <%
    //------------------------------------------------------------
    // Page List 출력
    //------------------------------------------------------------
    // 페이지 목록을 출력할 빈을 호출합니다.
    // pdsgrpno: 그룹 코드 
    // col     : 검색 컬럼
    // word    : 검색어
    // nowPage : 현재 페이지
    out.print(dao.paging(request, currentPage)); 
    //------------------------------------------------------------
    %> 
</div>

<form name='frmData' action='' method='post'>
  <input type='hidden' name='calendarno' value='' />
  <input type="hidden" name="currentPage" value="<%=currentPage%>">
</form>


   <!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="true"/>
</body> 
</html>

<!-- *********************************************** -->







  
    
    
    
    
    
    
    
    