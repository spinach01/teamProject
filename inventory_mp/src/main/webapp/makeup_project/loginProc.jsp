<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>로그인 처리</title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 14px;
}
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<DIV class='title'>알림</div>

<DIV class='msg'>
<%
String id = request.getParameter("id");         //아이디
String passwd = request.getParameter("passwd"); //패스워드

int cnt = dao.loginCheck(id, passwd);

if (cnt == 1){
    // ---------------------------------------------
    // session내부 객체에 session 변수 저장
    // ---------------------------------------------
    // s_id session 변수 생성
    // session 변수는 서버 메모리에 저장되는 변수
    // 사용자가 접속을 종료하거나 로그아웃하면 서버에서 소멸.
    session.setAttribute("s_id", id); 
    session.setAttribute("s_passwd", passwd); 
    // ---------------------------------------------
        
    response.sendRedirect("../mpgroup/index.do" ); // 홈페이지로 이동.  
}else{
%>
  아이디와 패스워드가 일치하지 않습니다.<br><br>
  <input type='button' value='다시시도' onclick="history.back();">
<%      
}
%>

</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>


