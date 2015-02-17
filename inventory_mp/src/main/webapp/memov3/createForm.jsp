<%@ page contentType="text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<DIV class="title">글 등록</DIV>
<br>
<DIV class='content'>
  <DIV style='text-align: center'>
    <FORM name='frn' method='post' action='./createProc.jsp'> 
        
    URL <input type='text' name='title' value='' size='50'><br>

    내용 <TEXTAREA name='content' cols='50' rows='10' style='text-align: left'>

사용예: 아래와 같습니다. 
       <a href='http://www.daum.net'>다음</a>

         </TEXTAREA><br>

    <input type='submit' value='등록'> 
    <input type='button' value='취소' onclick='history.back();'>    
    </FORM>
  </DIV>  
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>