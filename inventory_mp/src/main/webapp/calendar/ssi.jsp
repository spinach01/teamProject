<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.*, java.io.*" %>
<%@ page import="www.utility.Utility"%>
<%@ page import="www.calendar.CalendarDAO, www.calendar.CalendarDTO"%>

<jsp:useBean id="dao" class="www.calendar.CalendarDAO" />
 
<%
// www.bbs.BbsDAO dao = new www.bbs.BbsDAO();
%>

<% 
request.setCharacterEncoding("utf-8");

int currentPage = 0; // 현재 페이지 번호 0 --> 1

if (request.getParameter("currentPage") != null){
  currentPage = Integer.parseInt(request.getParameter("currentPage"));
}

%>




