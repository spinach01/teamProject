<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file ="ssi.jsp" %>

<%
int calendarno = Integer.parseInt(request.getParameter("calendarno"));

CalendarDTO dto = null;

dto = dao.read(calendarno);

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> 수정 </title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 14px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">


<a href = './images/logo.png'></a>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<script type="text/javascript">
  function f_update(){ // 수정
    var f = document.frmData;
    f.action = './updateForm.jsp';
    f.submit();
  }
 
  function f_delete(){  // 삭제
    var f = document.frmData;
    f.action = './deleteForm.jsp';
    f.submit();
  } 
  
  function f_list(){  // 목록 
    var f = document.frmList;
    f.action = './list.jsp';
    f.submit();
  } 

  function f_reply(){  // 목록 
      var f = document.frmData;
      f.action = './replyForm.jsp';
      f.submit();
  }
  
  
</script>
</head>


 <DIV class="title">조회 및 수정</DIV>
    

<!-- action='http://localhost:9090/www_jsp/bbs/updateProc.jsp'  -->
<FORM name="frmData" action="./updateProc.jsp" method="POST">
  <input type='hidden' name='calendarno' value="<%=calendarno %>" />
  <input type="hidden" name="currentPage" value="<%=currentPage%>">
    
  <table align='center' width='100%' cellpadding='0' cellspacing='0'>
    <tr>
      <th width='10%'>출력 날짜</th>
      <td colspan='5' align='left'>
        <input type='text' name='labeldate' value='<%=dto.getLabeldate() %>' size='10'>
        형식: 2013-10-15
        </td>
    </tr>   
    <tr>
      <th>출력 레이블</th>
      <td colspan='5' align='left'>
        <input type='text' name='label' value='<%=dto.getLabel() %>' size='20'>
        형식: 최대 20자
        </td>
    </tr>  
    <tr>
      <th>제목</th>
      <td colspan='3' align='left'><input type='text' name='title' value='<%=dto.getTitle() %>' size='100'></td>      
      </td>
    </tr>
    <tr>
      <th>내용</th>
      <td colspan='5' align='left'><TEXTAREA name='content' style="font-size:12; color:#000000;border:1px solid; width: 100%" rows="30"><%=Utility.convertCharTextArea(dto.getContent()) %></TEXTAREA>
      </td>
    </tr>      
      
  </table>
  
  <br>
    <br>
      <br>
      
    <div class='footer_menu' align="center">  
    <input type="submit" value="저장">
    <input type="button" value="취소(목록)" onclick="f_list();">
    <input type="button" value="삭제" onclick="f_delete()">
  </div>
  
</FORM>

<form name='frmList' action='' method='post'>
  <input type="hidden" name="currentPage" value="<%=currentPage%>">
</form>



<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="true"/>
</body> 
<!-- *********************************************** -->

</html>









