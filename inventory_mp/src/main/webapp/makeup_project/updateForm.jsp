<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>

<%
String id = request.getParameter("id");

EmployeeDTO dto = dao.read(id);   
String position = dto.getPosition();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>회원정보 수정</title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 24px;
}
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css">

<script type="text/JavaScript"
        src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
        
<script language="javascript">
  // 입력 값 검사후 서버로 전송
  function inputCheck(){
      var f = document.frm;
      
    if(f.passwd.value == ""){
        alert("비밀번호를 입력해 주세요.");
        f.passwd.focus();
        return false;
    }
    if(f.repasswd.value == ""){
        alert("비밀번호를 확인해 주세요");
        f.repasswd.focus();
        return false;
    }
    
    //비밀번호가 일치하는지 검사
    if(f.passwd.value != f.repasswd.value){
        alert("비밀번호가 일치하지 않습니다. 다시 입력해 주세요.");
        f.passwd.focus();
        return false;
    }
    
    if(f.mname.value == ""){
        alert("이름을 입력해 주세요.");
        f.mname.focus();
        return false;
    }

    if(f.position.value == "0"){
        alert("직원 직급을 선택해 주세요.");
        f.position.focus();
        return false;
    }
    
    if(f.mtype.value == "0"){
        alert("멤버 유형을 선택해 주세요.");
        f.mtype.focus();
        return false;
    }    
    // Form onsubmit 이벤트일경우
    // return false;
    
    return true;
  }

  // -------------------------------------------------------------------------
  // 중복 이메일을 검사합니다.
  // -------------------------------------------------------------------------
  function emailCheck(email){
    var params = "email=" +  email;   // 특수 문자 변환 처리
    $.ajaxSetup({ dataType: "text"}); // ajax 객체 준비
    $.post('./email_proc.jsp', params, emailCheckResponse);
  }

  function emailCheckResponse(){
    $("email_panel").html(data.replace(/^\s*|\s*$/g, ''));
  }
  // -------------------------------------------------------------------------


  // -------------------------------------------------------------------------
  // 우편번호 검색
  // -------------------------------------------------------------------------
  function zipCheck(dongli){
    var params = "dongli=" +  dongli; // 특수 문자 변환 처리
    $.ajaxSetup({dataType: "text"});  // ajax 객체 준비
    $.post('./zip_proc.jsp', params, zipCheckResponse);
  }
  
  function zipCheckResponse(){
	  $("zip_panel").html(data.replace(/^\s*|\s*$/g, ''));
  }
  
  function sendAddress(zipcode, sido, gugun, dongli, etc){
    // 주소 조합
    var address =sido+" "+gugun+" "+dongli+" "+etc;
      
    document.frm.zipcode.value = zipcode;  // 우편번호
    document.frm.address1.value = address; // 주소
    document.frm.address2.focus();         // 상세 주소
         
    document.getElementById("zip_panel").innerHTML = '';  // DIV
  }
  // -------------------------------------------------------------------------
  
  // -------------------------------------------------------------------------
  // 패스워드를 검사합니다.
  // -------------------------------------------------------------------------
  function passwdCheck(passwd){
    var params = "passwd=" +  encodeURIComponent(passwd); // 특수 문자 변환 처리
    ajax.onreadystatechange = passwdCheckResponse;
    ajax.open('POST', './passwd_proc.jsp?' + params, true);
    ajax.send(params);
  }
  
  function passwdCheckResponse(){
    if (ajax.readyState == 4 && ajax.status == 200){
      var str = ajax.responseText.replace(/^\s*|\s*$/g, ''); // 좌우 공백 제거
      // alert('응답: ' + str);
      document.getElementById("passwd_panel").innerHTML = str;
    }
  }
  // -------------------------------------------------------------------------
  
</script>

</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<form name="frm" method="post" action="./updateProc.jsp"
      onsubmit="return inputCheck()">
      
      <input type='hidden' name='id' value='<%=dto.getId()%>'>
      <br>
      <div style='text-align:center'>
          회원 정보 수정 (* 필수 입력)
      </div>
      <br>
      
      <table  width="90%" border="1" cellspacing="0" cellpadding="2"  align="center">
      <!-- cellspacing = cell과 cell과의 간격(cell의 바깥의간격), cellpadding= cell과 내용과의 관격  -->
          <tr> 
            <th width="20%">*아이디</th>
            <td colspan='2'> 
               <%=dto.getId() %>
            </td>
          </tr>
         <tr> 
            <th>*패스워드</th>
            <td>
              <input type="password" name="passwd" size="15" value='1234'
                     onkeyup="passwdCheck(document.frm.passwd.value)">
              <DIV id='passwd_panel'></DIV> 
            </td>
            <td>패스워드를 적어주세요.</td>
          </tr>
          <tr> 
            <th>*패스워드 확인</th>
            <td> <input type="password" name="repasswd" size="15" value=''> </td>
            <td>패스워드를 확인합니다.</td>
          </tr>
          <tr> 
            <th>*이름</th>
            <td> <input type="text" name="mname" size="15" value='<%=dto.getMname()%>'> </td>
            <td>고객실명을 적어주세요.</td>
          </tr>
          <tr> 
            <th>전화번호</th>
            <td><input type="text" name="tel" value="<%=dto.getTel()%>"></td>
            <td>&nbsp;</td>
          </tr>          
          <tr> 
            <th>이메일</th>
            <td>
               <input type="text" name="email" size="27"  value='<%=dto.getEmail() %>'>
                <input type="button" value="Email 중복확인" 
                        onclick="emailCheck(document.frm.email.value)">
                <DIV id='email_panel'></DIV>
            </td>
            <td>이메일을 적어주세요.</td>
          </tr>
          <tr>  
            <th>우편번호</th>
            <td> 
              <input type="text" name="zipcode" size="10" value='111-111'>
                     &nbsp;&nbsp;&nbsp;동이름:
              <input type='text' name='dongli' size='10'>
              <input type="button" name="btnPost" value="우편번호찾기" 
                     onclick="zipCheck(document.frm.dongli.value)">
              <DIV id="zip_panel"></DIV>
                       
            </td>
            <td>우편번호를 검색 하세요.</td>
          </tr>
          <tr>  
            <th>주소</th>
            <td>
            <input type="text" name="address1" size="45" value='<%=dto.getAddress1()%>'><br/>
            <input type="text" name="address2" size="45" value='<%=Utility.checkNull(dto.getAddress2())%>'>
            </td>
            <td>주소를 적어 주세요.</td>
          </tr>
          <tr>  
            <th>직원 직급</th>
            <td>
              <SELECT name='position'>
                <OPTION value='A01' <% if (position.equals("A01")) out.println("selected='selected'"); %>>사원</OPTION>              
                <OPTION value='A03' <% if (position.equals("A03")) out.println("selected='selected'"); %>>대리</OPTION>              
                <OPTION value='A05' <% if (position.equals("A05")) out.println("selected='selected'"); %>>과장</OPTION>
                <OPTION value='A07' <% if (position.equals("A07")) out.println("selected='selected'"); %>>차장</OPTION>
                <OPTION value='A09' <% if (position.equals("A09")) out.println("selected='selected'"); %>>부장</OPTION>
                <OPTION value='A11' <% if (position.equals("A11")) out.println("selected='selected'"); %>>상장</OPTION>
                <OPTION value='A13' <% if (position.equals("A13")) out.println("selected='selected'"); %>>전무</OPTION>
                <OPTION value='A15' <% if (position.equals("A15")) out.println("selected='selected'"); %>>부사장</OPTION>
                <OPTION value='A17' <% if (position.equals("A17")) out.println("selected='selected'"); %>>사장</OPTION>              
                <OPTION value='A98' <% if (position.equals("A98")) out.println("selected='selected'"); %>>해당사항없음</OPTION>
                <OPTION value='A99' <% if (position.equals("A99")) out.println("selected='selected'"); %>>기타</OPTION>
              </SELECT>              
            </td>
            <td>직원 직급을 선택 하세요.</td>
          </tr>
          <tr> 
            <th>멤버 유형</th>
            <td> 
              <%-- <input type="text" name="mtype" size="15" value='<%=dto.getMtype() %>'>  --%>
              <input type="radio" name="mtype" value="직원">직원
              <input type="radio" name="mtype" value="회원">회원           
            </td>
            <td>멤버 유형을 적어주세요.</td>
          </tr>          
      </table>
    <div style="text-align: center">
        <input type="submit" value="회원 정보 수정" > 
    </div>
</form>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>






