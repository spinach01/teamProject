<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>

<%!
public void delay(int second){
  try{
    second = second * 1000;
    Thread.sleep(second);
  }catch(Exception e){
        
  }
}
%>

<%
delay(2); // 시각적 효과를 위해 2초정도 대기
//---------------------------------------------------------------
// Fileupload 콤포넌트 관련 코드, 하나의 파일을 업로드 처리
//---------------------------------------------------------------
String param1 = "";         //일반 폼 태그 
FileItem fileItem = null;   //업로드 파일 객체
String filename = "";       //업로드 파일명
UploadSaveManager upload = new UploadSaveManager(request, -1, -1, tempDir);

//기존 이미지의 삭제
Utility.deleteFile(upDir + "/" + upload.getParameter("fname"));  // 파일 삭제

fileItem = upload.getFileItem("file1");
    
if (fileItem != null){
  long filesize = fileItem.getSize(); // 파일 사이즈
        
  if(filesize > 0) {
    filename = UploadSaveManager.saveFile(fileItem, upDir);
%>
    <IMG src='./storage/<%=filename %>' width='200px'>
    <INPUT type='hidden' name='fname' value='<%=filename %>'>
<%            
  }  
}
%>















