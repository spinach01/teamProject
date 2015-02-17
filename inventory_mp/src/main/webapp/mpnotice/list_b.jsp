<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>

<%
ArrayList list = dao.list(col, word); 

%>

<%
// 관리자 세션 변수
String s_admin_id = Utility.checkNull(session.getAttribute("s_id2"));
// 사용자 세션 변수
String s_member_id = Utility.checkNull(session.getAttribute("s_id"));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 16px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<DIV class="title">공지사항</DIV>

<DIV class='top'>
  <%
  if (s_admin_id.equals("") == false){%>
  <FORM name='frm' method='post' action="./list_b.jsp">
  <SELECT name='col'>
    <OPTION value='title' <% if (col.equals("title")) out.print("selected='selected'"); %>>제 목</OPTION>
    <OPTION value='content' <% if (col.equals("content")) out.print("selected='selected'"); %>>내 용</OPTION>
  </SELECT>
  <input type='text' name='word' value='<%=word %>'>
  <input type='submit' value='검색'>
  <input type='button' value='전체 목록' onclick="location.href='./list_b.jsp'">
  <input type='button' value='등록' onclick="location.href='./createForm.jsp'">
  </FORM>
  <%
  }
  %>
</DIV>

<DIV class="content">
  <TABLE align='center' width='100%' class='table' cellpadding='2px' cellspacing='0'>
    <TR>
      <TH>번호</TH>
      <TH>제목</TH>
      <TH>등록일</TH>
      <TH>파일명</TH>
      <TH>조회수</TH>
      <%
      if (s_admin_id.equals("") == false){%>
      <TH>수정/삭제</TH>
      <%
      }
      %>        
    </TR>
    <%
    for(int index=0; index < list.size(); index++){
      MpnoticeDTO dto = (MpnoticeDTO)list.get(index);
      String wdate = dto.getWdate();
      wdate = wdate.substring(0, 10); // 2014-03-11
    %>
    <TR>
      <TD align='center'><%=dto.getNoticeno() %></TD>
      <TD><A href='./read.jsp?noticeno=<%=dto.getNoticeno() %>'><%=Utility.convertChar(dto.getTitle()) %></A></TD>
      <TD align='center'><%=wdate %></TD>
      <TD align='center'><%=dto.getFname() %></TD>
      <TD align='center'><%=dto.getMviewcnt() %></TD>

      <%
      if (s_admin_id.equals("") == false){%>
      <TD align='center'>
        <input type='button' value='수정' onclick="location.href='./updateForm.jsp?noticeno=<%=dto.getNoticeno() %>'">      
        <input type='button' value='삭제' onclick="location.href='./deleteForm.jsp?noticeno=<%=dto.getNoticeno() %>'">
      </TD>
      <%
      }
      %>      
    </TR>  
    <%  
    } 
    %>
  </TABLE>
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>










