<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="www.utility.Utility" %>

<%
// 로그인한 회원인지 검사하여 정상적인 로그인이 아니면 로그인 페이지로 이동
//-------------------------------------------------------------------------
String auth_id= (String)session.getAttribute("s_id2");
if (auth_id == null){
  // 정상적인 로그인이 안된경우
  response.sendRedirect(Utility.getRoot() +  "/makeup_admin/loginForm.jsp");
  return;  // 더이상 jsp 페이지 처리 안함.
} 
%>

