<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="inventory.project.group.MpgroupDTO" %>

<%
MpgroupDTO dto = (MpgroupDTO)request.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>품목 그룹 삭제</title>
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

<DIV class='title'>품목 그룹 삭제</DIV>

<FORM name='frm' method='POST' action='./delete.do'>
  <input type='hidden' name='mpgroupno' value='<%=dto.getMpgroupno() %>'>
  
  <DIV class='content2'>
    품목 그룹을 삭제 하시겠습니까?<br><br>
    삭제하면 관련 품목 파일도 전부 삭제됩니다.<br><br>  
  </DIV>
  
  <DIV class='bottom'>
    <input type='submit' value='삭제 진행'>
    <input type='button' value='목록' onclick="location.href='./list.do'">
  </DIV>
</FORM>
  
<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="true"/>
</body>
<!-- *********************************************** -->

</html>
