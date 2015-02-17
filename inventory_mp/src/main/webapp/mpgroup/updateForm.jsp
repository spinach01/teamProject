<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="inventory.project.group.MpgroupDTO" %>

<%
MpgroupDTO dto = (MpgroupDTO)request.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>품목 그룹 수정</title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 24px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<!-- *********************************************** -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- *********************************************** -->

<DIV class='title'>품목 그룹 수정</DIV>

<FORM name='frm' method='POST' action='./update.do'>
  <input type='hidden' name='mpgroupno' value='<%=dto.getMpgroupno() %>'>
  
  <TABLE align='center' border='1px' cellspacing='0px' cellpadding='5px' >
    <TR>
      <TH>품목 그룹 제목</TH>
      <TD><INPUT type='text' name='mpgrname' size='50' value='<%=dto.getMpgrname() %>'></TD>    
    </TR>  
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='수정'>
    <input type='button' value='목록' onclick="location.href='./list.do'">
  </DIV>
</FORM>
  
<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="true"/>
</body>
<!-- *********************************************** -->

</html>
