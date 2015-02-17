<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>

<%
int recordPerPage = 10; 
ArrayList list = dao.list(col, word, nowPage, recordPerPage); // 글목록 산출
int totalRecord = dao.count(col, word);        // 검색된 레코드수  산출
 
%>

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
<title>고객지원자료실</title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 14px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->


<DIV class="title">고객지원 자료실</DIV>

<DIV class='top'>
  <FORM name='frm' method='post' action="./list.jsp">
  <SELECT name='col'>
    <OPTION value='wname' <% if (col.equals("wname")) out.print("selected='selected'"); %>>성 명</OPTION>
    <OPTION value='title' <% if (col.equals("title")) out.print("selected='selected'"); %>>제 목</OPTION>
    <OPTION value='content' <% if (col.equals("content")) out.print("selected='selected'"); %>>내 용</OPTION>
  </SELECT>
  <input type='text' name='word' value='<%=word %>'>
  <input type='submit' value='검색'>
  <input type='button' value='전체 목록' onclick="location.href='./list.jsp'">

  <%
  if ((s_admin_id.equals("") == false) || (s_member_mtype.equals("직원") == true)){%>
  <input type='button' value='등록' onclick="location.href='./createForm.jsp'">
  <%
  }
  %>
  </FORM>
</DIV>

<DIV class="content">
  <TABLE align='center' border='1px' width='100%' class='table' cellpadding='2px' cellspacing='0'>
    <TR>
      <TH>번호</TH>
      <TH>첨부파일</TH>
      <TH>제목</TH>
      <TH>성명</TH>
      <TH>조회</TH>
      <TH>등록일</TH>
      <TH>다운로드</TH>
      <TH>기타</TH>
    </TR>
    <% 
    for(int index=0; index < list.size(); index++){
      ClientdataDTO dto = (ClientdataDTO)list.get(index);
      String rdate = dto.getRdate();
      rdate = rdate.substring(0, 10); // 2014-03-11
    %>
    <TR>
      <TD align='center' width='5%'><%=dto.getCdatano() %></TD>
     <TD align='center' width='5%' height="50px">
     <% 
      String fname2 = Utility.checkNull(dto.getFname());
      if(fname2.length() > 0){ %>
        <img src='./storage/<%=dto.getFname() %>' width='100%' height="50px">
     <%}else{%>
         파일없음
        <%} %>

        </TD>
      <TD>
        <%
        // 답변 차수만큼 들여쓰기
        for(int i=0; i < dto.getIndent(); i++){
          out.println("&nbsp;");
        }
      
        if (dto.getIndent() == 0){
          out.println("<IMG src='./images/bu1.gif'>"); // 답변이 아닐 경우
        }else{
          out.println("<IMG src='./images/bu2.gif'>"); // 답변일 경우
        }
        %>
        <A href='./read.jsp?cdatano=<%=dto.getCdatano() %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage %>'><%=Utility.convertChar(dto.getTitle()) %></A>
      </TD>
      <TD align='center'><%=dto.getWname() %></TD>
      <TD align='center'><%=dto.getMviewcnt() %></TD>
      <TD align='center'><%=rdate %></TD>
       <TD align='center' width='100px'>
       <%
       String fname = Utility.checkNull(dto.getFname());
       if(fname.length() > 0){%>
         <a href='<%=Utility.getRoot() %>/download?dir=<%=downDir %>&filename=<%=dto.getFname() %>'><%=dto.getFname() %></a>
       <%}else{ %>
             -
       <%} %>
     
       </TD>
      <%
      if ((s_admin_id.equals("") == false) || (s_member_mtype.equals("직원") == true)) {%>
      <TD align='center' width = '200px'>
        <input type='button' value='수정' 
               onclick="location.href='./updateForm.jsp?cdatano=<%=dto.getCdatano() %>&nowPage=<%=nowPage %>'">
        <input type='button' value='삭제' 
               onclick="location.href='./deleteForm.jsp?cdatano=<%=dto.getCdatano() %>&nowPage=<%=nowPage %>'">                   
      </TD>
      <%
      }
      %>       
    </TR>    
    <%  
    }
    %> 
  </TABLE> 
</DIV>
 
<DIV class='bottom'>
<%
out.println(new Paging().paging3(totalRecord, nowPage, recordPerPage, col, word));
%>
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>
 


 









