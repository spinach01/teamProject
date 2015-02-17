<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="./auth.jsp" %> 
<%@ include file="./ssi.jsp" %>

<%
ArrayList list = dao.list(); 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>직원 목록</title>
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

<DIV class="title">직원 목록</DIV>
<DIV class="top">
  <input type='button' value='관리자 등록' onclick="location.href='./createForm.jsp'">
  <input type='button' value='회원 목록' onclick="location.href='../makeup_project/list.jsp'">
  
</DIV>
<DIV class="content">
<%
for(int index = 0; index<list.size(); index++){
  MpadminDTO dto = (MpadminDTO)list.get(index);
%>
  <TABLE width='100%' align='center' cellspacing="0" cellpadding="0" border="1px" style='border-collapse: collapse; margin-bottom: 5px; border-color: #AAAAAA; border: 1px #AAAAAA solid'>
    <TR>
      <TD rowspan='6' width='20%' align='center'><img src='./storage/<%=dto.getFname() %>' width='200px'></TD>
      <TD width='15%'>성명</TD>
      <TD width='65%'><A href='./read.jsp?id=<%=dto.getId() %>'><%=dto.getMname() %></A></TD>
    </TR>
    <TR>
      <TD>아이디</TD>
      <TD><%=dto.getId() %></TD>
    </TR>
    <TR>
      <TD>전화번호</TD>
      <TD><%=dto.getTel() %></TD>
    </TR>
    <TR>
      <TD>전자우편</TD>
      <TD><%=dto.getEmail() %></TD>
    </TR>
    <TR>
      <TD >주소</TD>
      <TD><%=dto.getAddress1() %> <%=Utility.checkNull(dto.getAddress2()) %></TD>
    </TR>
    <TR>
      <TD>직급</TD>
      <TD><%=dao.getCodeValue(dto.getPosition() )%></TD>
    </TR>
  </TABLE>
<%
}
%>
</DIV>

<DIV class="bottom"></DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>