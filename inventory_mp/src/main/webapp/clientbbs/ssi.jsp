<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.ArrayList" %>

<%@ page import="www.utility.Utility, www.utility.Paging" %>

<%@ page import="inventory.project.bbs.ClientbbsDAO,inventory.project.bbs.ClientbbsDTO" %>

<% request.setCharacterEncoding("utf-8"); %>

<%
ClientbbsDAO dao = new ClientbbsDAO();

String col = Utility.checkNull(request.getParameter("col"));
String word = Utility.checkNull(request.getParameter("word"));

//--------------------------------------------------------------------
//페이징 관련 부분
//--------------------------------------------------------------------
int nowPage= 1; //시작 페이지 번호는 1부터
//최초 nowPage(현재 페이지)는 0부터 시작
if (request.getParameter("nowPage") != null) {
  nowPage= Integer.parseInt(request.getParameter("nowPage")); 
}
%>

