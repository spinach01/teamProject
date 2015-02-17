<%@ page contentType="text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 등록</title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 24px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">

</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<DIV class="title">글등록</DIV>

<FORM name='frm' method='post' 
      action='./createProc.jsp'>
      
<DIV class="content">
  <TABLE width='100%'>
    <TR>
      <TH width='15%'>성명</TH>
      <TD width='85%'><input type='text' name='wname' value='' size='50'></TD>
    </TR>
    <TR>
      <TH>제목</TH>
      <TD><input type='text' name='title' value='' size='50'></TD>
    </TR>
    <TR>
      <TH>내용</TH>
      <TD><TEXTAREA name='content' rows='10' cols='60'></TEXTAREA></TD>
    </TR>
    <TR>
      <TH>패스워드</TH>
      <TD><input type='password' name='passwd' value='' size='50'></TD>
    </TR>

  </TABLE>
</DIV>
<DIV class='bottom'>
  <input type='submit' value='등록'>
  <input type='button' value='취소' onclick="location.href='./list.jsp'"> 
</DIV>
</FORM>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>






