<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>

<%
int recordPerPage = 10; 
ArrayList list = dao.list(col, word, nowPage, recordPerPage); // 글목록 산출
int totalRecord = dao.count(col, word);        // 검색된 레코드수  산출
 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>고객게시판</title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 14px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->


<DIV class="title">고객게시판</DIV>

<DIV class='top'>
  <FORM name='frm' method='post' action="./list.jsp">
  <SELECT name='col'>
    <OPTION value='wname' <% if (col.equals("wname")) out.print("selected='selected'"); %>>성 명</OPTION>
    <OPTION value='title' <% if (col.equals("title")) out.print("selected='selected'"); %>>제 목</OPTION>
    <OPTION value='content' <% if (col.equals("content")) out.print("selected='selected'"); %>>내 용</OPTION>
  </SELECT>
  <input type='text' name='word' value='<%=word %>'>
  <input type='submit' value='검색'>
  <input type='button' value='전체 목록' onclick="location.href='./list.jsp'">
  <input type='button' value='등록' onclick="location.href='./createForm.jsp'">
  </FORM>
</DIV>

<DIV class="content">
  <TABLE align='center' border='1px' width='100%' class='table' cellpadding='2px' cellspacing='0'>
    <TR>
      <TH>번호</TH>
      <TH>제목</TH>
      <TH>성명</TH>
      <TH>조회</TH>
      <TH>등록일</TH>
      <TH>기타</TH>
    </TR>
    <%
    for(int index=0; index < list.size(); index++){
      ClientbbsDTO dto = (ClientbbsDTO)list.get(index);
      String wdate = dto.getWdate();
      wdate = wdate.substring(0, 10); // 2014-03-11
    %>
    <TR>
      <TD align='center'><%=dto.getQnano() %></TD>
      <TD>
        <%
        // 답변 차수만큼 들여쓰기
        for(int i=0; i < dto.getIndent(); i++){
          out.println("&nbsp;");
        }
      
        if (dto.getIndent() == 0){
          out.println("<IMG src='./images/bu1.gif'>"); // 답변이 아닐 경우
        }else{
          out.println("<IMG src='./images/bu2.gif'>"); // 답변일 경우
        }
        %>
        <A href='./read.jsp?qnano=<%=dto.getQnano() %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage %>'><%=Utility.convertChar(dto.getTitle()) %></A>
      </TD>
      <TD align='center'><%=dto.getWname() %></TD>
      <TD align='center'><%=dto.getViewcnt() %></TD>
      <TD align='center'><%=wdate %></TD>
      <TD align='center'>
        <input type='button' value='U' 
               onclick="location.href='./updateForm.jsp?qnano=<%=dto.getQnano() %>&nowPage=<%=nowPage %>'">
        <input type='button' value='D' 
               onclick="location.href='./deleteForm.jsp?qnano=<%=dto.getQnano() %>&nowPage=<%=nowPage %>'">               
      </TD>
      
    </TR>  
    <%  
    }
    %>
  </TABLE>
</DIV>
 
<DIV class='bottom'>
<%
out.println(new Paging().paging2(totalRecord, nowPage, recordPerPage, col, word));
%>
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->


</html>
 


 









