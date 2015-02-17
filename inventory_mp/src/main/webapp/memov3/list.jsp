<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 16px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<DIV class="title">URL 목록</DIV>

  <DIV class="content">    
    <TABLE width= '70%' border= '1' cellspacing= '0px' cellpadding= '5px' align= 'center'>
      <tr><INPUT type='button' value='등록'
               onclick="location.href='./createForm.jsp'"></tr>
      <TR>
        <TH width='10%'>번호</TH>
        <TH width='50%'>URL</TH>
        <TH width='30%'>날짜</TH>
        <TH width='10%'>기타</TH>
      </TR>
<%

ArrayList list = dao.list();
        
for(int i=0; i< list.size(); i++){
  Object obj = list.get(i);  // 저장소에 저장된 객체 추출(저장소 추출역할만.)
  MemoVO vo = (MemoVO)obj;   // Object는 사용이 불가능하므로 원래의 타입으로 객체 형변환

  String wdate = vo.getWdate(); 
  int memono = vo.getMemono();
%>
      <TR>
      <TD align= 'center'><%=memono %></TD>
      <TD><%=vo.getTitle() %></TD>
      <TD align= 'center'><%=wdate.substring(0,16) %></TD>
      <TD align= 'center'>
        <INPUT type='button' value='등록'
               onclick="location.href='./createForm.jsp'"> <%//jsp 스크립트 사용, <a href=?? >(앵커태크)를 쓰지않고 onclick사용 %>
        <INPUT type='button' value='삭제'
               onclick="location.href='./deleteForm.jsp?memono=<%=memono%>'">  <%// Get방식으로 보냄 %>    
      </TD>
    </TR>
<% } %>    
  </TABLE>
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>

