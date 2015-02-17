<%@ page contentType="text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("utf-8"); %>

<%@ include file="./ssi.jsp" %>

<%
String id = request.getParameter("id");
EmployeeDTO dto = dao.read(id);   // 조회 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사진 등록</title>
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

<DIV class="title">이미지 변경</DIV>

<FORM name='frm' method='post' 
      action='./updateFileProc.jsp'
      enctype='multipart/form-data'>
      <input type='hidden' name='id' value='<%=id %>'> 
      
<DIV class="content">
  <TABLE width='70%' align='center'>
    <TR>
      <TH width='30%'>원본 파일</TH>
      <TD width='70%'>
        <IMG src='./storage/<%=dto.getFname()%>' border='0' width='200px'>
        등록된 파일명: <%=dto.getFname() %>
      </TD>
    </TR>
    <TR>
      <TH width='20%'>변경할 파일 선택</TH>
      <TD width='80%'>
        <input type='file' name='fname' value='' size='40'>
      </TD>
    </TR>
  </TABLE>
</DIV>

<DIV class='bottom'>
  <input type='submit' value='등록'>
  <input type='button' value='취소' onclick="history.back();"> 
</DIV>

</FORM>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>






