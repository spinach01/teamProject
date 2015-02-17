<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="./ssi.jsp" %>

<%
String id = request.getParameter("id");
String passwd = request.getParameter("passwd");
String mname = request.getParameter("mname");
String tel = request.getParameter("tel");
String email = request.getParameter("email");
String zipcode = request.getParameter("zipcode");
String address1 = request.getParameter("address1");
String address2 = request.getParameter("address2");
String position = request.getParameter("position");
String mtype = request.getParameter("mtype");

EmployeeDTO dto = new EmployeeDTO();
dto.setId(id);
dto.setPasswd(passwd);
dto.setMname(mname);
dto.setTel(tel);
dto.setEmail(email);
dto.setZipcode(zipcode);
dto.setAddress1(address1);
dto.setAddress2(address2);
dto.setPosition(position);
dto.setMtype(mtype);

int cnt= dao.update(dto); 
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 24px;
}
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<DIV class='title'>회원 정보 수정 처리</DIV>

<DIV class='msg'> 

  <%
  if(cnt == 1){
  %>
    회원 정보를 변경했습니다.<br>
    로그인해보세요~~~<br>
    [<a href='./read.jsp?id=<%=id%>'>변경한 회원정보 확인</a>]
    [<a href='./list.jsp'>회원목록</a>]
  <% 
  }else{
  %>    
    회원 정보 변경에 실패 했습니다.<br><br>
    [<a href='javascript:history.back()'>재시도</a>]
    [<a href='./list.jsp'>회원 목록</a>]<br>
  <%
  }
  %>
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>









