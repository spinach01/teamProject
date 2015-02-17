<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="./ssi.jsp" %>  
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 등록 </title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<Script Language='JavaScript'>
function checkData(f){ // f == document.frmData 객체
    var msg; // 에러 메세지
    var str; // 임시 문자열 저장 변수
      
    // 제목 앞뒤의 공백 제거
    str = f.title.value.replace(/^\s*|\s*$/g,'');
    if (str.length == 0){
        msg = '안내\n\n제목을 입력해 주십시오'; 
        window.alert(msg);
        f.title.focus();
        return false;
    }
    
    // 내용 앞뒤의 공백 제거
    str = f.content.value.replace(/^\s*|\s*$/g,''); 

    // 이름의 길이를 비교
    if (str.length == 0){
        msg = '안내\n\n내용을 입력해 주십시오'; 
        window.alert(msg);
        f.content.focus();
        return false;
    }
    
    return true;
}
</Script>
</head>

<a href = './images/logo.png'></a>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

   
   
 <DIV class="title">등록</DIV>
    
<FORM name="frmData" 
      action="./createProc.jsp" 
      method="POST"
      onSubmit="return checkData(this)">
  
  <table align='center' width='100%' cellpadding='0' cellspacing='0'>
    <tr>
      <th width='10%'>출력 날짜</th>
      <td colspan='5' align='left'>
        <input type='text' name='labeldate' value='2014-  -' size='10'>
        형식: 2014-12-25
        </td>
    </tr>  
    <tr>
      <th>출력 레이블</th>
      <td colspan='5' align='left'>
        <input type='text' name='label' value='' size='20'>
        형식: 최대 20자
        </td>
    </tr>  
    <tr>
      <th>제목</th>
      <td colspan='3' align='left'><input type='text' name='title' value='' size='100'></td>      
      </td>
    </tr>
    <tr>
      <th>내용</th>
      <td colspan='5' align='left'><TEXTAREA name='content' style="font-size:12; color:#000000;border:1px solid; width: 100%" rows="30"></TEXTAREA>
      </td>
    </tr>      
     
  </table>
  

   <br>
    <br>
      <br>
      
    <div class='footer_menu' align="center">  
    <input type="submit" value="저장">
    <input type="button" value="취소(목록)" onclick="history.back();">
  </div>
  
</FORM>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>











