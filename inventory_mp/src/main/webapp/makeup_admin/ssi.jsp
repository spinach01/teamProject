<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.ArrayList"%>

<%@ page import="org.apache.commons.fileupload.*" %>

<%@ page import="www.makeup_project.MpadminDTO"%>
<%@ page import="www.makeup_project.ZipcodeDTO"%>
<%@ page import="www.utility.Utility, www.utility.UploadSaveManager"%>

<jsp:useBean id="dao" class="www.makeup_project.MpadminDAO" /> 

<%
request.setCharacterEncoding("utf-8");
%>

<%
// ----------------------------------------------------------------------------------
// 다운로드 관련 부분
// ----------------------------------------------------------------------------------
// 파일 업로드/다운로드 폴더
String upDir = "/makeup_admin/storage";
String downDir = "/makeup_admin/storage";

// 파일 저장용 절대 경로 추출
upDir = application.getRealPath(upDir);
// System.out.println("upDir: " + upDir);

// ServletContext ctx = request.getServletContext();
//ctx.getRealPath(upDir);

// 파일 업로드시 임시 저장 폴더
String tempDir = application.getRealPath("/makeup_admin/temp");
// ----------------------------------------------------------------------------------

%>




