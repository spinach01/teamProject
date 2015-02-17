<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %> 

<%
// 관리자 세션 변수
String s_admin_id = Utility.checkNull(session.getAttribute("s_id2"));
// 사용자 세션 변수
String s_member_id = Utility.checkNull(session.getAttribute("s_id"));

//사용자 타입 세션 변수
String s_member_mtype = Utility.checkNull(session.getAttribute("s_mtype"));

%>

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
int cdatano = Integer.parseInt(request.getParameter("cdatano"));
dao.viewcntAdd(cdatano);        // 조회수 증가
ClientdataDTO dto = dao.read(cdatano); // 번호에 해당하는 레코드 읽기
String fname = dto.getFname();
String rdate = dto.getRdate();
rdate = rdate.substring(0, 19);

// String content = dto.getContent();
// content = content.replaceAll("\r\n", "<BR>");

%>
<DIV class="title">고객지원자료실 글 조회</DIV>

<DIV class="content" style='text-align: center;'>

  <TABLE width='90%' align='center' border='0'>
    <TR>
      <TH width='15%'>번호</TH>
      <TD width='85%' align='left'><%=dto.getCdatano() %></TD>
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
       <TH> 파일명(파일사이즈)</TH>
       <TD align='left'><%=fname %>(<%=dto.getFilesize() %>) 
       <input type='button' value='다운로드' onclick="location.href='<%=Utility.getRoot() %>/download?dir=<%=downDir%>&filename=<%=fname%>'"></TD> 
    </TR>  
    <TR>
      <TH>내용</TH>
      <TD align='left'>
      <%
        String fname1 = Utility.checkNull(dto.getFname());
        if (fname1.length() > 0){ %>
          <img src='./storage/<%=dto.getFname() %>' width='50%' ><br><br><%=Utility.convertChar(dto.getContent()) %>
        <%}else{ %>
           <%=Utility.convertChar(dto.getContent()) %>
           <%} %>
    </TR>
    <TR>
      <TH>조회수</TH> 
      <TD align='left'><%=dto.getMviewcnt() %></TD>
    </TR>
    <TR>
      <TH>등록일</TH>
      <TD align='left'><%=rdate %></TD>
    </TR>
  </TABLE>

</DIV>

<DIV class='bottom'>
  <%
  if ((s_admin_id.equals("") == false) || (s_member_mtype.equals("직원") == true)){%>
  <input type='button' value='수정' 
         onclick="location.href='./updateForm.jsp?cdatano=<%=cdatano %>&col=<%=col %>&word=<%=word %>&nowPage=<%=nowPage %>'">
  <input type='button' value='삭제' 
         onclick="location.href='./deleteForm.jsp?cdatano=<%=cdatano %>&col=<%=col %>&word=<%=word %>&nowPage=<%=nowPage %>'">
  <input type='button' value='답변' 
         onclick="location.href='./replyForm.jsp?cdatano=<%=cdatano %>&grpno=<%=dto.getGrpno() %>&ansnum=<%=dto.getAnsnum() %>&indent=<%=dto.getIndent() %>&nowPage=<%=nowPage %>'">
  <%
  }
  %>
  <input type='button' value='목록' onclick="location.href='./list.jsp?col=<%=col %>&word=<%=word %>&nowPage=<%=nowPage %>'">
</DIV>
<DIV class='bottom'>
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>





