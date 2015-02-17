<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="inventory.project.bbs.*,www.utility.*" %>
<%@ page import="www.utility.Utility,www.utility.Paging" %>
<%@ page import="inventory.project.bbs.ClientdataDAO,inventory.project.bbs.ClientdataDTO" %>
<%@ page import="org.apache.commons.fileupload.*" %>



<% request.setCharacterEncoding("UTF-8"); %>

<%
ClientdataDAO dao = new ClientdataDAO();

String col =  Utility.checkNull(request.getParameter("col"));
String word = Utility.checkNull(request.getParameter("word"));

//----------------------------------------------------------------------------------
//다운로드 관련 부분
//----------------------------------------------------------------------------------
//파일 업로드/다운로드 폴더
String upDir = "/clientdata/storage";
String downDir = "/clientdata/storage";

//파일 저장용 절대 경로 추출
upDir = application.getRealPath(upDir);
//System.out.println("upDir: " + upDir);

//ServletContext ctx = request.getServletContext();
//ctx.getRealPath(upDir);

//파일 업로드시 임시 저장 폴더
String tempDir = application.getRealPath("/clientdata/temp");
//----------------------------------------------------------------------------------


//--------------------------------------------------------------------
//페이징 관련 부분
//--------------------------------------------------------------------
int nowPage= 1; //시작 페이지 번호는 1부터
//최초 nowPage(현재 페이지)는 0부터 시작
if (request.getParameter("nowPage") != null) {
nowPage= Integer.parseInt(request.getParameter("nowPage")); 
}


%>