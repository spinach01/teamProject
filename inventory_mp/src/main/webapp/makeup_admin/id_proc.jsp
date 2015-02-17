<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="./ssi.jsp" %>

<% 
String id = request.getParameter("id");

int cnt = dao.duplicateId(id); // 아이디 중복확인, 0, 1 

if (cnt == 1){ %>   
  <span style='color: #FF0000; font-weight: bold;'>중복됩니다. 다시 입력해주세요.</span>
<%  
}else{%>
  <span style='color: #00CC00; font-weight: bold;'>중복 아님, 사용 가능 합니다.</span>
<%
}
%>


