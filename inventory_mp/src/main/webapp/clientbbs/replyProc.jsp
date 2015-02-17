<%@ page contentType="text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("utf-8"); %>

<%@ include file="./ssi.jsp" %>

<%
int qnano = Integer.parseInt(request.getParameter("qnano"));
int grpno = Integer.parseInt(request.getParameter("grpno"));
int indent = Integer.parseInt(request.getParameter("indent"));
int ansnum = Integer.parseInt(request.getParameter("ansnum"));

//답변순서(ansnum)가 현재 글보다 큰 글들의 ansnum 값을 1씩증가
dao.addAnsnum(qnano, ansnum);

// 새로 등록되는 답변이 현재 글 바로뒤에 위치 되도록 1을 증가 
ansnum = ansnum + 1;

// 현재 글의 답변임으로 indent 값을 1 증가
indent = indent + 1;

ClientbbsDTO dto = new ClientbbsDTO();
dto.setQnano(qnano);
dto.setWname(request.getParameter("wname"));
dto.setTitle(request.getParameter("title"));
dto.setContent(request.getParameter("content"));
dto.setPasswd(request.getParameter("passwd"));
dto.setGrpno(grpno);
dto.setIndent(indent);
dto.setAnsnum(ansnum);
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

<DIV class="title">답변 등록 처리</DIV>

<DIV class='msg'>
<%
if (dao.reply(dto) == 1){%>
  답변을 등록했습니다.<br><br>
  <input type='button' value='계속 답변 등록' onclick="location.href='./replyForm.jsp?qnano=<%=qnano %>&col=<%=col %>&word=<%=word %>&nowPage=<%=nowPage %>'">
  <input type='button' value='목록' onclick="location.href='./list.jsp?col=<%=col %>&word=<%=word %>&nowPage=<%=nowPage %>'">
  
<%
}else{
%>
  답변 등록에 실패했습니다.<br><br>
  <input type='button' value='다시 시도' onclick="history.back();">
  <input type='button' value='목록' onclick="location.href='./list.jsp?col=<%=col %>&word=<%=word %>&nowPage=<%=nowPage %>'">

<%} %>
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>




