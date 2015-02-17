<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="./ssi.jsp" %>

<% 
String passwd = request.getParameter("passwd");
String repasswd = request.getParameter("repasswd");

if (passwd.equals(repasswd) == false ){ %>   
  <span style='color: #FF0000; font-weight: bold; background-color: #FFFF00'>입력된 두개의 패스워드가 서로 일치하지 않습니다.</span>
<%  
}else{%>
  <span style='color: #00CC00; font-weight: bold;'>입력된 두개의 패스워드가 서로 일치합니다.</span>
<%
}
%>


