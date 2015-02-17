<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file ="ssi.jsp" %> 

<%
int calendarno = Integer.parseInt(request.getParameter("calendarno"));
String msg = "";
int passwd_cnt = 0;
int cnt = 0;
 
cnt = dao.delete(calendarno);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>삭제 처리</title>
<script type="text/javascript">
  function j_list(){  // 목록 
    var f = document.frmData;
    f.action = './list.jsp';
    f.submit();
  } 

  
</script>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>


<a href = './images/logo.png'></a>
<!-- *********************************************** -->
<body style="margin: 0px">
<jsp:include page="../menubar/top.jsp" flush="false"/>
<!-- *********************************************** -->

<h2>
<%
if (cnt == 1){
  // out.println("삭제 했습니다.<br><br>");
  response.sendRedirect("./list.jsp");
}else{
  out.println("삭제 실패했습니다.");
  out.println("<br>" + msg);
  out.println("<br><br> <input type='button' value='다시 시도' onclick='history.back()'>");
}
%>
<input type='button' value='목록' onclick="j_list();">

</h2>

<form name='frmData' action='' method='post'>
  <input type="hidden" name="currentPage" value="<%=currentPage%>">
</form>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
</html>
<!-- *********************************************** -->




