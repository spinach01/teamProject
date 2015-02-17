<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>회원가입</title>

<script type="text/JavaScript"
    src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>

<script type="text/javascript">
// 입력 값 검사후 서버로 전송
function inputCheck(){
    var f = document.frm;   //<FORM>태그 객체
    
    if(f.id.value == ""){
        alert("아이디를 입력해 주세요.");
        //폼이름.input 태그명.커서 셋팅    
        f.id.focus();
        //프로그램 종료, 값을 돌려줌
        return;
    }
    if(f.passwd.value == ""){
        alert("비밀번호를 입력해 주세요.");
        f.passwd.focus();
        return;
    }
    if(f.repasswd.value == ""){
        alert("비밀번호를 확인해 주세요");
        f.repasswd.focus();
        return;
    }
    
    //비밀번호가 일치하는지 검사
    if(f.passwd.value != f.repasswd.value){
        alert("비밀번호가 일치하지 않습니다. 다시 입력해 주세요.");
        f.passwd.focus();
        return;
    }
    
    if(f.mname.value == ""){
        alert("이름을 입력해 주세요.");
        f.mname.focus();
        return;
    }

    if(f.email.value == ""){
        alert("이메일을 입력해 주세요.");
        f.email.focus();
        return;
    }

    if(f.position.value == "0"){
        alert("직급을 선택해 주세요.");
        f.position.focus();
        return;
    }
    // Form onsubmit 이벤트일경우
    // return false;
    
    f.submit();
}

 //--------------------------------------------------------------------
// 중복 아이디를 검사합니다.
  function idCheck(id){ 
	  var params = "id=" + id;   // 특수 문자 변환 처리
	  
	  $.ajaxSetup({ dataType: "text" });   // Ajax 객체 준비
	  $.post("./id_proc.jsp", params, idCheckResponse);

  }

  function idCheckResponse(data, textStatus){
	  $("#id_panel").html(data.replace(/^\s*|\s*$/g, ''));
	                          
  }
  
//--------------------------------------------------------------------
//중복 이메일을 검사합니다.
function emailCheck(email){ 
    var param = "email=" + email;   // 특수 문자 변환 처리
    
    $.ajaxSetup({ dataType: "text" });   // Ajax 객체 준비
    $.post("./email_proc.jsp", param, emailCheckResponse);

  }

  function emailCheckResponse(data, textStatus){
	  $("#email_panel").html(data.replace(/^\s*|\s*$/g, ''));
                            
  }
  
//--------------------------------------------------------------------	  
// 우편번호 검색
function zipCheck(dongli){  
    var params = "dongli=" + dongli;   // 특수 문자 변환 처리
    
    $.ajaxSetup({ dataType: "text" });   // Ajax 객체 준비
    $.post("./zip_proc.jsp", params, zipCheckResponse);

  }

  function zipCheckResponse(data, textStatus){
    $("#zip_panel").html(data.replace(/^\s*|\s*$/g, ''));
                            
  }
  
  function sendAddress(zipcode, sido, gugun, dongli, etc){
	  // 주소 조합
	  var address =sido+" "+gugun+" "+dongli+" "+etc;
	  
	  document.frm.zipcode.value = zipcode;   // 우편번호
	  document.frm.address1.value = address;   // 주소
	  document.frm.address2.focus();               // 상세주소
	  
	  document.getElementById("zip_panel").innerHTML = '';
	       
	}
  
//-------------------------------------------------------------------- 
  // 패스워드를 검사합니다.
  //--------------------------------------------------------------------
function passwdCheck(passwd){ 
    var param = "passwd=" + passwd;   // 특수 문자 변환 처리
    
    $.ajaxSetup({ dataType: "text" });   // Ajax 객체 준비
    $.post("./passwd_proc.jsp", param, passwdCheckResponse);

  }

  function passwdCheckResponse(data, textStatus){
    $("#passwd_panel").html(data.replace(/^\s*|\s*$/g, ''));
                            
  }
  
//--------------------------------------------------------------------
// 패스워드 동일여부를 검사합니다.
  //--------------------------------------------------------------------

	function passwdEqualCheck(passwd, repasswd){ 
	    var param = "passwd=" + passwd + "&repasswd=" + repasswd;   // 특수 문자 변환 처리
	    
	    $.ajaxSetup({ dataType: "text" });   // Ajax 객체 준비
	    $.post("./passwd_Equal_proc.jsp", param, passwdEqualCheckResponse);

	  }

	  function passwdEqualCheckResponse(data, textStatus){
	    $("#repasswd_panel").html(data.replace(/^\s*|\s*$/g, ''));
	                            
	  }	
	
	  
//--------------------------------------------------------------------
</script>

<link href="../css/style.css" rel="Stylesheet" type="text/css">

</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<form name="frm" method="post" 
      action="./createProc.jsp"enctype='multipart/form-data'>
      <br>
      <div style='text-align:center'>
         
          회원 가입 (* 필수 입력)
      </div>
      <br>
      
      <table  width="1000" border="1" cellspacing="0" cellpadding="2"  align="center">
          <tr> 
            <th width="20%">사진</th>
            <td width="53%"><input type='file' name='fname' value=''></td>
            <td>사진은 jpg, png로 올려주세요.</td>
           </tr>
           <tr>
            <th>*아이디</th>
            <td> 
                <input type="text" name="id" size="15" value='user1'>
                <input type="button" value="ID중복확인" 
                       onclick="idCheck(document.frm.id.value)">
                <DIV id = 'id_panel'></DIV>
            </td>
            <td>아이디를 적어 주세요.</td>
          </tr>
         <tr> 
            <th>*패스워드</th>
            <td> <input type="password" name="passwd" size="15" value='1234' 
                  onkeyup="passwdCheck(document.frm.passwd.value)">
                 <DIV id = 'passwd_panel'></DIV>
            </td>
            <td>패스워드를 적어주세요.
            
            </td>
          </tr>
          <tr> 
            <th>*패스워드 확인</th>
            <td> <input type="password" name="repasswd" size="15" value='1234' 
                   onkeyup="passwdEqualCheck(frm.passwd.value,frm.repasswd.value)"> 
                 <DIV id = 'repasswd_panel'></DIV>
            </td>
            <td>패스워드를 확인합니다.
            </td>
          </tr>
          <tr> 
            <th>*이름</th>
            <td> <input type="text" name="mname" size="15" value='왕눈이'> </td>
            <td>고객실명을 적어주세요.</td>
          </tr>
          <tr> 
            <th>전화번호</th>
            <td><input type="text" name="tel" value="123-1234"></td>
            <td>&nbsp;</td>
          </tr>          
          <tr> 
            <th>이메일</th>
            <td>
                <input type="text" name="email" size="27"  value='email@mail.com'>
                <input type="button" value="Email 중복확인" 
                        onclick="emailCheck(document.frm.email.value)">
              <DIV id = 'email_panel'></DIV>
            
            </td>
            <td>이메일을 적어주세요.</td>
          </tr>
          <tr>  
            <th>우편번호</th>
            <td> 
                 <input type="text" name="zipcode" size="10" value='111-111'>
                 &nbsp;&nbsp;동이름 : 
                 <input type="text" name="dongli" size='10'>
                 <input type="button" name="btnPost" value="우편번호찾기" 
                       onclick="zipCheck(document.frm.dongli.value)">
                  <DIV id = 'zip_panel'></DIV>
                       
            </td>
            <td>우편번호를 검색 하세요.</td>
          </tr>
          <tr>  
            <th>주소</th>
            <td>
            <input type="text" name="address1" size="45" value='인천시 남동구 간석4동'><br/>
            <input type="text" name="address2" size="45" value=''>
            </td>
            <td>주소를 적어 주세요.</td>
          </tr>
          <tr>  
            <th>직급</th>
            <td>
                <select name='position'>
                    <option value="0">선택하세요.</option>
                    <option value="A01">사원</option>
                    <option value="A03">대리</option>
                    <option value="A05">과장</option>
                    <option value="A07">차장</option>
                    <option value="A08">부장</option>
                    <option value="A11">상무</option>
                    <option value="A13">전무</option>
                    <option value="A15">부사장</option>
                    <option value="A17">사장</option>
                    
             
                  </select>
              </td>
            <td>직급을 선택 하세요.</td>
          </tr>
      </table>
    <div style="text-align: center">
        <input type="button" value="회원가입" 
               onclick="inputCheck()" > 
        <input type="reset" value="다시쓰기">
    </div>
</form>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>




