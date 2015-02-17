<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="./ssi.jsp" %>

<% 
String passwd = request.getParameter("passwd");

if (passwd.length() < 4 ){ %>   
  <span style='color: #FF0000; font-weight: bold; background-color: #FFFF00'>패스워드를 4자이상 해주세요.</span>
<%  
}else{%>
  <span style='color: #00CC00; font-weight: bold;'>사용 가능한 패스워드 입니다.</span>
<%
}
%>


