<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="./ssi.jsp" %> 

<%
String id = request.getParameter("id");

EmployeeDTO dto = dao.read(id);   
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>회원 정보 상세 조회</title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 24px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script type="text/javascript">
  function deleteConfirm(id){
	  var on = confirm(id + " 회원을 삭제 하시겠습니까?");
	  if (on == true){
		  location.href='./deleteProc.jsp?id=' + id; // GET
	  }
  }
</script>

</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<DIV class='title'><%=dto.getMname() %> 회원 정보</DIV>

<DIV class='content'>   
  <table align="center" width="80%" border="0" cellpadding="2" cellspacing="0">
    <tr>
      <td colspan='2' align='center'>
        <img src='./storage/<%=dto.getFname()%>'>
      </td> 
    </tr>  
    <tr>
      <th width="20%">아이디</th>
      <td width="80%"><%=dto.getId() %></td>
    </tr>
    <tr>
      <th>이름</th>
      <td><%=dto.getMname() %></td>
    </tr>
    <tr>
      <th>전화번호</th>
      <td><%=dto.getTel() %></td>
    </tr>          
    <tr> 
      <th>이메일</th>
      <td><%=dto.getEmail() %></td>
    </tr>
    <tr> 
      <th>우편번호</th>
      <td><%=dto.getZipcode() %></td>
    </tr>
    <tr> 
      <th>주소</th>
      <td>
        <%=dto.getAddress1() %> <br>
        <%=Utility.checkNull(dto.getAddress2()) %>
      </td>
    </tr>
    <tr> 
      <th>직급</th>
      <td>
        직 급: <%=dto.getPosition() %> 
        (<%=dao.getCodeValue(dto.getPosition() )%>)</td>
    </tr>
    <tr> 
      <th>날짜</th>
      <td><%=dto.getMdate()%></td>
    </tr>
    <tr> 
      <th>멤버유형</th>
      <td><%=dto.getMtype() %></td>
    </tr>       
  </table>
</DIV>

<DIV class='bottom'>
  <input type="button" value="회원 정보 수정" 
         onclick="location.href='./updateForm.jsp?id=<%=id%>&col=<%=col %>&word=<%=word%>&nowPage=<%=nowPage %>'">
  <input type="button" value="회원 사진 수정" 
         onclick="location.href='./updateFileForm.jsp?id=<%=id%>&col=<%=col %>&word=<%=word%>&nowPage=<%=nowPage %>'">
  <input type="button" value="전체 목록" onclick="location.href='./list.jsp'">
  <input type='button' value='삭제' onclick="deleteConfirm('<%=id%>&col=<%=col %>&word=<%=word%>&nowPage=<%=nowPage %>');">
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>







