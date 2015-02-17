<%@ page contentType="text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("utf-8"); %>

<%@ include file="./ssi.jsp" %>

<%
int qnano = Integer.parseInt(request.getParameter("qnano"));
String passwd = request.getParameter("passwd");

ClientbbsDTO dto = new ClientbbsDTO();
dto.setQnano(qnano);
dto.setWname(request.getParameter("wname"));
dto.setTitle(request.getParameter("title"));
dto.setContent(request.getParameter("content"));
dto.setPasswd(passwd);

int passwd_cnt = 0; // 패스워드 일치 여부
int cnt = 0;        // 수정 처리 여부

passwd_cnt = dao.checkPasswd(qnano, passwd);

// 패스워드 검사
if (passwd_cnt == 1){
  // 수정 처리
  cnt = dao.update(dto);
}

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

<DIV class="title">수정 처리</DIV>

<DIV class='msg'>
<%
if (passwd_cnt == 1 && cnt == 1){%>
  글을 수정했습니다.<br><br>
  <input type='button' value='수정 확인' 
         onclick="location.href='./read.jsp?qnano=<%=qnano %>&col=<%=col %>&word=<%=word %>&nowPage=<%=nowPage %>'">
  <input type='button' value='목록' onclick="location.href='./list.jsp?col=<%=col %>&word=<%=word %>&nowPage=<%=nowPage %>'">
  
<%
}else if(passwd_cnt == 1 && cnt == 0){
%>
  패스워드는 일치했으나 글 수정에 실패했습니다.<br><br>
  <input type='button' value='다시 시도' onclick="history.back();">
  <input type='button' value='목록' onclick="location.href='./list.jsp?col=<%=col %>&word=<%=word %>&nowPage=<%=nowPage %>'">

<%
}else{ 
  response.sendRedirect("./passwdError.jsp");
  
}
%>
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>




