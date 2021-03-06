<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>

<%
//---------------------------------------------------------------
// Fileupload 콤포넌트 관련 코드, 하나의 파일을 업로드 처리
//---------------------------------------------------------------
/*
 @param request HttpServletRequest 객체
 @param threshold 메모리에 저장할 최대크기
 @param max 업로드할 최대 파일크기
 @param repositoryPath 업로드 경로
*/
UploadSaveManager upload = new UploadSaveManager(request, -1, -1, tempDir);

String id = upload.getParameter("id");

FileItem fileItem = upload.getFileItem("fname");   // 업로드 파일 객체

String fname = "";         // 업로드 파일명

MpadminDTO dto = dao.read(id);

Utility.deleteFile(upDir + "/" + dto.getFname());  // 원판 삭제

fname = upload.saveFile(fileItem, upDir);   // 새로운 원판 이미지 업로드
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
*{
  font-family: ;
  font-size: 24px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<DIV class="title">이미지 수정 처리</DIV>

<DIV class='msg'>
<%
if (dao.updateFile(id, fname) == 1){%>
  이미지를 등록했습니다.<br><br>
  <input type='button' value='조회' 
         onclick="location.href='./read.jsp?id=<%=id %>'">
  
  <input type='button' value='목록' 
         onclick="location.href='./list.jsp'">  
<%
}else{
%>
  파일 등록에 실패했습니다.<br><br>
  <input type='button' value='다시 시도' onclick="history.back();">
  <input type='button' value='목록' onclick="location.href='./list.jsp'">

<%} %>
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>




