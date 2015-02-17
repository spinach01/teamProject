<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file ="ssi.jsp" %> 

<%     
int calendarno = Integer.parseInt(request.getParameter("calendarno"));
currentPage = Integer.parseInt(request.getParameter("currentPage"));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> 삭제 </title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 14px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">



</head>

<a href = './images/logo.png'></a>
<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

 <DIV class="title">삭제</DIV>
    
<br><DIV class='msg'>
삭제하시겠습니까? 삭제되면 복구 될 수 없습니다.<br>
<br>
<br>
<FORM name="frmData" action="./deleteProc.jsp" method="POST">
  <input type="hidden" name="currentPage" value="<%=currentPage%>">
  <input type='hidden' name='calendarno' value="<%=calendarno %>" />
   
  <div class='footer_menu'>
    <input type="submit" value="삭제">
    <input type="button" value="취소" onclick="history.back();">
  </div>
  
</FORM>
</DIV>
<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
</html>
<!-- *********************************************** -->












