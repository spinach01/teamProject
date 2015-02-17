<%@ page contentType="text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("utf-8"); %>

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
   
// 이미지 레코드 번호
int cdatano = Integer.parseInt(upload.getParameter("cdatano"));
String passwd = upload.getParameter("passwd");
ClientdataDTO dto= dao.read(cdatano);

FileItem fileItem = upload.getFileItem("fname");   // 업로드 파일 객체

// System.out.println(">>>>> fileItem: " + fileItem);

String fname = "";         // 업로드 파일명

String title = Utility.getEncodFileUpload12(upload.getParameter("title"));
String content = Utility.getEncodFileUpload12(upload.getParameter("content"));

if (fileItem.getSize() > 0){
  
  // 기존 파일 삭제
  Utility.deleteFile(upDir + "/" + dto.getFname());
  
  // 새로운 파일 등록
  long filesize = fileItem.getSize();            // 새로운 원판 파일 사이즈 
  fname = upload.saveFile(fileItem, upDir);
}else{
  long filesize = dto.getFilesize();
  fname = dto.getFname();
}

dto = new ClientdataDTO();
dto.setCdatano(cdatano);
dto.setTitle(title);
dto.setContent(content);
dto.setFname(fname);
dto.setPasswd(passwd);
  

int passwd_cnt = 0; // 패스워드 일치 여부
int cnt = 0;        // 수정 처리 여부

passwd_cnt = dao.checkPasswd(cdatano, passwd);

// 패스워드 검사
if (passwd_cnt == 1){
  // 수정 처리
  cnt = dao.update(dto);
}
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

<DIV class="title">고객지원자료실 수정 처리</DIV>
<DIV class='msg'>
<%
if (passwd_cnt == 1 && cnt == 1){%>
  글을 수정했습니다.<br><br>
  <input type='button' value='수정 확인' 
         onclick="location.href='./read.jsp?cdatano=<%=cdatano %>&col=<%=col %>&word=<%=word %>&nowPage=<%=nowPage %>'">
  <input type='button' value='목록' onclick="location.href='./list.jsp?col=<%=col %>&word=<%=word %>&nowPage=<%=nowPage %>'">
  
<%
}else if(passwd_cnt == 1 && cnt == 0){
%>
  패스워드는 일치했으나 글 수정에 실패했습니다.<br><br>
  <input type='button' value='다시 시도' onclick="history.back();">
  <input type='button' value='목록' onclick="location.href='./list.jsp?col=<%=col %>&word=<%=word %>&nowPage=<%=nowPage %>'">

<%
}else{ 
  response.sendRedirect("./passwdError.jsp");
  
}
%>
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>



