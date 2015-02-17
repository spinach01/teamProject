<%@ page contentType="text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("utf-8"); %>

<% int memono = Integer.parseInt(request.getParameter("memono")); 
// list->read(에서 memono가 있음)->delete(memono있음)
%>

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
.content{
  border: 1px;
  border-style: solid;
  width: 80%;
  border-color: #AAAAAA;
  padding: 10px;
  background: #DDDDDD;
  margin: 0 auto;
}<%//class 속성%>
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<DIV class="title"> 삭제</DIV>

  <DIV class='msg'>
    <DIV style='text-align: center'>
      정말로 삭제 하시겠습니까?<BR>
      삭제되면 복구할 수 없습니다.<BR><BR>
  
      <form name='frn' method='post' action='./deleteProc.jsp'>
        <input type='hidden' name='memono' value='<%=memono %>'><% // name 대소문자 구별함.. 주의 %>
       
        <input type='submit' value='삭제 진행'><%// deleteProc로 넘어감 // 대소문자 구별, 이름이 잘못되면 버퍼(해쉬)때문에 완전히 지웠다가 다시씀%>
        <input type='button' value='취소' onclick='javascript:history.back();'><%// button은 이전으로 취소, location으로 해서 주소이동도 할수 있음.. %>
      </form>
    </DIV> 
 </DIV>   
 
<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>