<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="./ssi.jsp" %>
<%
  int noticeno = Integer.parseInt(request.getParameter("noticeno"));
  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
* {
	font-family: 돋음체;
	font-size: 14px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<DIV class="title">공지사항 글 삭제</DIV>
 <FORM name='frm' method='POST' action='./deleteProc.jsp'>
   <input type='hidden' name='noticeno' value='<%=noticeno %>'>
   <input type='hidden' name='col' value='<%=col %>'>
   <input type='hidden' name='word' value='<%=word %>'>
   <input type='hidden' name='nowPage' value='<%=nowPage %>'>

 	<DIV class='msg'>
 	글을 삭제하시겠습니까?<br><br>
	삭제가 진행되면 복구 할 수 없습니다.
 		<input type='submit' value='삭제진행'> 
		<input type='button' value='취소' onclick="history.back();">
			
	</DIV>
	 </FORM>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>
