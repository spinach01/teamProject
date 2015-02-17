<%@ page contentType="text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("utf-8"); %>

<%@ include file="./ssi.jsp" %>

<%
//---------------------------------------------------------------
//Fileupload 콤포넌트 관련 코드, 하나의 파일을 업로드 처리
//---------------------------------------------------------------
/*
@param request HttpServletRequest 객체
@param threshold 메모리에 저장할 최대크기
@param max 업로드할 최대 파일크기
@param repositoryPath 업로드 경로  
*/
UploadSaveManager upload = new UploadSaveManager(request, -1, -1, tempDir);

FileItem fileItem = upload.getFileItem("fname");   // 업로드 파일 객체
long fsize = fileItem.getSize();       // 파일 사이즈

String fname = "";         // 업로드 파일명

if(fsize > 0) {
  fname = upload.saveFile(fileItem, upDir);   // 저장 후 파일명 리턴
}

MpnoticeDTO dto = new MpnoticeDTO();
dto.setFname(fname);
dto.setTitle(Utility.getEncodFileUpload12(upload.getParameter("title")));
dto.setContent(Utility.getEncodFileUpload12(upload.getParameter("content")));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 14px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<DIV class="title">공지사항 등록 처리</DIV>

<DIV class='msg'>
<%
if (dao.create(dto) == 1){%>
  글을 등록했습니다.<br><br>
  <input type='button' value='계속 등록' onclick="location.href='./createForm.jsp'">
  <input type='button' value='목록' onclick="location.href='./list_b.jsp'">
  
<%
}else{
%>
  글 등록에 실패했습니다.<br><br>
  <input type='button' value='다시 시도' onclick="history.back();">
  <input type='button' value='목록' onclick="location.href='./list_b.jsp'">

<%} %>
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>





