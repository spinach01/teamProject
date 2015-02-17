<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
*{
  font-family: ;
  font-size: 14px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<%
int qnano = Integer.parseInt(request.getParameter("qnano"));
dao.viewcntAdd(qnano);        // 조회수 증가
ClientbbsDTO dto = dao.read(qnano); // 번호에 해당하는 레코드 읽기

// String content = dto.getContent();
// content = content.replaceAll("\r\n", "<BR>");

%>
<DIV class="title">글 조회</DIV>

<DIV class="content" style='text-align: center;'>

  <TABLE width='90%' align='center' border='0'>
    <TR>
      <TH width='15%'>번호</TH>
      <TD width='85%' align='left'><%=dto.getQnano() %></TD>
    </TR>
    <TR>
      <TH>성명</TH>
      <TD align='left'><%=dto.getWname() %></TD>
    </TR>
    <TR>
      <TH>제목</TH>
      <TD align='left'><%=Utility.convertChar(dto.getTitle()) %></TD>
    </TR>
    <TR>
      <TH>내용</TH>
      <TD align='left'><%=Utility.convertChar(dto.getContent()) %></TD>
    </TR>
    <TR>
      <TH>조회수</TH>
      <TD align='left'><%=dto.getViewcnt() %></TD>
    </TR>
    <TR>
      <TH>등록일</TH>
      <TD align='left'><%=dto.getWdate() %></TD>
    </TR>
  </TABLE>

</DIV>

<DIV class='bottom'>
  <input type='button' value='수정' 
         onclick="location.href='./updateForm.jsp?qnano=<%=qnano %>&col=<%=col %>&word=<%=word %>&nowPage=<%=nowPage %>'">
  <input type='button' value='삭제' 
         onclick="location.href='./deleteForm.jsp?qnano=<%=qnano %>&col=<%=col %>&word=<%=word %>&nowPage=<%=nowPage %>'">
  <input type='button' value='목록' onclick="location.href='./list.jsp?col=<%=col %>&word=<%=word %>&nowPage=<%=nowPage %>'">
  <input type='button' value='답변' 
         onclick="location.href='./replyForm.jsp?qnano=<%=qnano %>&grpno=<%=dto.getGrpno() %>&ansnum=<%=dto.getAnsnum() %>&indent=<%=dto.getIndent() %>&nowPage=<%=nowPage %>'">

</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>





