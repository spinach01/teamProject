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

<!-- *********************************************** -->
<body leftmargin="0" topmargin="0">
<!-- *********************************************** -->

<DIV class='title'>알림</div>

<DIV class='msg'>
<%
String id = request.getParameter("id");         //아이디
String passwd = request.getParameter("passwd"); //패스워드
String mtype = request.getParameter("mtype");   //맴버유형

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

    EmployeeDTO dto = dao.read(id);
    mtype = dto.getMtype();
    session.setAttribute("s_mtype", mtype); 
    //out.println("mtype::"+ dto.getMtype() );
    
    // ---------------------------------------------
    
     // ---------------------------------------------
     // Cookie 저장, Checkbox는 선택하지 않으면 null
     // ---------------------------------------------
     Cookie cookie = null;
     
    String c_id = request.getParameter("c_id");   // Y가들어옴...
    if (c_id != null){ 
      cookie = new Cookie("c_id", "Y");    // 쿠키 유지
      cookie.setMaxAge(60);
      response.addCookie(cookie);
      
      cookie = new Cookie("c_id_val", id);    // 쿠키 유지
      cookie.setMaxAge(60);
      response.addCookie(cookie);
    }else{    
      cookie = new Cookie("c_id", "");   //삭제 목적
      cookie.setMaxAge(0);
      response.addCookie(cookie);
      
      cookie = new Cookie("c_id_val", "");   //삭제 목적
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }
     
    
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
</body>
<!-- *********************************************** -->
</html>


