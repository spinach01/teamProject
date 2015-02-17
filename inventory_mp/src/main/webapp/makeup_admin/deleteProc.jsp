<%@ page contentType="text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("utf-8"); %>

<%@ include file="./ssi.jsp" %>

<%
String id = request.getParameter("id");

MpadminDTO dto = dao.read(id);

Utility.deleteFile(upDir + "/" + dto.getFname());  // 파일 삭제

int cnt = dao.delete(id);  // 레코드 삭제
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
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

<DIV class="title">회원 삭제 처리</DIV>

<DIV class='msg'>
<%
if (cnt == 1){%>
  회원을 삭제 했습니다.<br><br>
  <input type='button' value='목록' onclick="location.href='./list.jsp'">  
<%
}else{
%>
  회원 삭제에 실패했습니다.<br><br>
  <input type='button' value='다시 시도' onclick="history.back();">
  <input type='button' value='목록' onclick="location.href='./list.jsp'">

<%} %>
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>




