<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="./ssi.jsp" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>로그 아웃</title>
</head>
<body>
<%
// 각각의 session변수만 삭제됩니다.  
// session.removeAttribute("idKey");
// session.removeAttribute("s_id");
// session.removeAttribute("s_mlevel");
// session.removeAttribute("s_mname");
// session.removeAttribute("s_passwd");

// 모든 세션변수가 전부 삭제됩니다.
session.invalidate();
response.sendRedirect("../mpgroup/index.do");
%>

