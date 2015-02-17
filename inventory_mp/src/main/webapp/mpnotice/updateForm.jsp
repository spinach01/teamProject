<%@ page contentType="text/html; charset=UTF-8" %>


<%@ include file="./ssi.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 등록</title>
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

<%
int noticeno = Integer.parseInt(request.getParameter("noticeno"));
MpnoticeDTO dto = dao.read(noticeno);
String title = dto.getTitle();
//title = title.replaceAll("'", "&apos;");
title = Utility.convertChar(title);
%>

<DIV class="title">수정</DIV>

<FORM name='frm' method='post' action='./updateProc.jsp' enctype='multipart/form-data'>
<input type='hidden' name='noticeno' value='<%=dto.getNoticeno() %>'>

<DIV class="content">
   <TABLE width='100%'>
    <TR>
      <TH>제목</TH>
      <TD><input type='text' name='title' value='<%=title %>' size='50'></TD>
    </TR>
    <TR>
      <TH width='20%'>파일</TH>
      <TD width='80%'>
        <img src='./storage/<%=dto.getFname()%>' border='0' width='150px'><br>
         등록된 파일명 : <%=dto.getFname() %> <br>
           <input type='file' name='fname' value='' size='40'>
      </TD>
    </TR>
    <TR>
      <TH>내용</TH>
      <TD><TEXTAREA name='content' rows='10' cols='100'><%=dto.getContent() %></TEXTAREA></TD>
    </TR>
    

  </TABLE>
</DIV>
<DIV class='bottom'>
  <input type='submit' value='등록' >
  <input type='button' value='취소' onclick="location.href='./list_b.jsp'"> 
</DIV>
</FORM>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>