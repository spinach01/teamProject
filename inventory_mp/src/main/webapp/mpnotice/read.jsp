<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="./ssi.jsp" %>

<%
// 관리자 세션 변수
String s_admin_id = Utility.checkNull(session.getAttribute("s_id2"));
// 사용자 세션 변수
String s_member_id = Utility.checkNull(session.getAttribute("s_id"));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 글조회</title>
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
int noticeno = Integer.parseInt(request.getParameter("noticeno"));
dao.viewcntAdd(noticeno);   // 조회수 증가
MpnoticeDTO dto = dao.read(noticeno);   // 번호에 해당하는 레코드 읽기

// String content = dto.getContent();
// content = content.replaceAll("\r\n", "<BR>");
%>


<DIV class="title">공지사항 글 조회</DIV>
<DIV class="content" style='text-align: center'>
     <TABLE width='90%' align='center' border='0'>
     <TR>
        <TH width = '15%'>번호</TH>
        <TD align='left'><%=dto.getNoticeno() %></TD>
      </TR>
       <TR> 
        <TH>제목</TH>
        <TD align='left'><%=dto.getTitle() %></TD>
      </TR>
      <TR> 
        <TH>내용</TH>
        <TD align='left'>
        <%
        String fname = Utility.checkNull(dto.getFname());
        if (fname.length() > 0){ %>
          <img src='./storage/<%=dto.getFname() %>' width='50%' ><br><br><%=Utility.convertChar(dto.getContent()) %>
        <%}else{ %>
           <%=Utility.convertChar(dto.getContent()) %>
           <%} %>
       
          
         </TD> 
        </TR>
      <TR> 
        <TH>등록일</TH>
        <TD align='left'><%=dto.getWdate() %></TD>
      </TR>
      <TR> 
        <TH>조회수</TH>
        <TD align='left'><%=dto.getMviewcnt() %></TD>
      </TR>
    </TABLE>
</DIV>
<DIV class='bottom'>
  <%
  if (s_admin_id.equals("") == false){%>
   <input type='button' value='수정' onclick="location.href='./updateForm.jsp?noticeno=<%=noticeno %>&col=<%=col %>&word=<%=word%>&nowPage=<%=nowPage %>'">
   <input type='button' value='삭제' onclick="location.href='./deleteForm.jsp?noticeno=<%=noticeno %>&col=<%=col %>&word=<%=word%>&nowPage=<%=nowPage %>'">
  <%
  }
  %>
   <input type='button' value='목록' onclick="location.href='./list_b.jsp?col=<%=col %>&word=<%=word%>&nowPage=<%=nowPage %>'">
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>










