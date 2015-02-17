<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="./ssi.jsp" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML> 
<HEAD>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<TITLE>로그인</TITLE>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 14px;
}
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css">

</HEAD>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<div style="width: 100%; height: 800px; text-align: center">

    <div style="text-align: center; text-decoration: underline; font-size: 24px">
        로그인
    </div>

    <form name="frm" method="post" action="./loginProc.jsp">
  
    <table align="center">
      <tr> 
        <th>아이디</th>
        <td>
            <input type="text" name="id" value='user1' size='20'>
        </td>
      </tr>
      <tr> 
        <th>패스워드</th>
        <td><input type="password" name="passwd" value='1234' size='20'> </td>
      </tr>
    </table>

    <input type='submit' value='로그인'>
    <input type='button' value='회원 가입'
           onclick="location.href='./agreement.jsp'">
  
   </form>
</div>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</HTML>