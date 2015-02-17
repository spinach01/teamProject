<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="inventory.project.group.MpgroupDTO" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
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

<DIV class='title'>품목 목록</DIV>

<FORM name='frm' method='POST' action=''>
  <TABLE align='center' border='1px' cellspacing='0px' cellpadding='5px'>

    <TR>
      <TH>품목번호</TH>
      <TH>품목명</TH>
      <TH>기타</TH>
    </TR>
    <%
    ArrayList list = (ArrayList)request.getAttribute("list");
    for(int i=0; i<list.size(); i++){
      MpgroupDTO dto = (MpgroupDTO)list.get(i);
      int mpgroupno = dto.getMpgroupno();
    %>
    <TR>
      <TD><%=mpgroupno%></TD>
      <TD><A href='../mpproduct/list.do?mpgroupno=<%=mpgroupno %>'><%=dto.getMpgrname() %></A></TD>
      <TD>
        <input type='button' value='수정' onclick="location.href='./update.do?mpgroupno=<%=mpgroupno %>'">
        <input type='button' value='삭제' onclick="location.href='./delete.do?mpgroupno=<%=mpgroupno %>'">
        <input type='button' value='검토' onclick="location.href='../maps/geo.jsp'">
      </TD>    
    </TR>    
    <%    
    }
    %>
  </TABLE>

  <DIV class='bottom'>
    <input type='button' value='등록' onclick="location.href='./create.do'">
    <input type='button' value='품목검색' onclick="location.href='./index.do'">
  </DIV>
</FORM> 
  
<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="true"/>
</body>
<!-- *********************************************** -->

</html>