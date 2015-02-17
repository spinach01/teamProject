<%@ page contentType="text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("utf-8"); %>

<%@ include file="./ssi.jsp" %>

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

<DIV class="title">공지사항 삭제 처리</DIV>

<DIV class='msg'>
<%
int noticeno = Integer.parseInt(request.getParameter("noticeno"));
MpnoticeDTO dto = dao.read(noticeno);

// 원본 파일 삭제
boolean bol = Utility.deleteFile(upDir + "/" + dto.getFname()); 
if (bol == true){
  out.println(dto.getFname() + " 파일을 삭제 했습니다.<br>");
}else{
  out.println(dto.getFname() + " 파일 삭제에 실패 했습니다.<br>");
}


int cnt = dao.delete(noticeno); // 레코드 삭제
if (cnt == 1){
  out.println("글을 삭제했습니다.<br>");
}else{
  out.println("글 삭제에 실패 했습니다.<br>");
}

// out.println("삭제할 레코드 번호: " + photono);
%>  
<br>

<input type='button' value='목록' onclick="location.href='./list_b.jsp'">
</DIV>



<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>
