<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file ="ssi.jsp" %> 

<%
int calendarno = Integer.parseInt(request.getParameter("calendarno"));

CalendarDTO dto = dao.read(calendarno); 

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> 조회 </title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 14px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">


<script type="text/javascript">
 
  
</script>
</head>

<a href = './images/logo.png'></a>
<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

 <DIV class="title">조회</DIV>
    
<FORM name="frmData" method="POST">
 
  <table align='center' width='100%' cellpadding='0' cellspacing='0'>
    <tr>
      <th width='10%'>날짜</th>
      <td colspan='5' align='left'>
        <input type='text' name='labeldate' value='<%=dto.getLabeldate() %>' size='10'>
        </td>
    </tr>   
    <tr>
      <th>제목</th>
      <td colspan='3' align='left'><input type='text' name='title' value='<%=dto.getTitle() %>' size='100'></td>      
 
    </tr>
    <tr>
      <th>내용</th>
      <td colspan='5' align='left'><TEXTAREA name='content' style="font-size:12; color:#000000;border:1px solid; width: 100%" rows="30"><%=dto.getContent() %></TEXTAREA>
      </td>
    </tr>      
     
  </table>
  
 <br>
    <br>
      <br>
      
    <div class='footer_menu' align="center">  
    <input type="button" value="돌아가기" onclick="location.href='../mpgroup/index.do';">
  </div>
  
</FORM>

<form name='frmList' action='' method='post'>
  <input type="hidden" name="currentPage" value="<%=currentPage%>">
</form>



   <!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="true"/>
</body> 
</html>

<!-- *********************************************** -->











