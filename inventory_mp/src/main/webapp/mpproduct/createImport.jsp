<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ page import="inventory.project.mpimport.MpimportDTO" %>
<%@ page import="inventory.project.mp.MpproductDTO" %>
<%
MpproductDTO dto = (MpproductDTO)request.getAttribute("mpproductDTO");
//MpimportDTO mpimDTO = (MpimportDTO)request.getAttribute("mpimportDTO"); 

/*  if(request.getAttribute("mpimportimcount") == null){
  request.setAttribute("mpimportimcount", 0);
}

if(request.getAttribute("mpimportimprice") == null){
  request.setAttribute("mpimportimprice", 0);
} 

out.println("mpexDTO.getImcount()>>>>>>>>>>>>>>>:::" + (request.getAttribute("mpimportimcount")));  */
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
* {
	font-family:;
	font-size: 14px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script type="text/javascript">  
  function windowcolse() {
    window.opener.location.reload();
    window.close();
  }
</script>

</head>
<body>
  <div class='title'><%=dto.getProductno() %>번 <%=dto.getMpname() %> 상품 입고</div>

  <form name='frm' method='post' action='./createImport.do'>
    <input type="hidden" name='productno' value='<%=dto.getProductno() %>'>
    <input type="hidden" name='mpgroupno' value='<%=dto.getMpgroupno() %>'>
    <input type="hidden" name='importgroupno' value='<%=request.getAttribute("mpimportmpgroupno") %>'>
    <input type="hidden" name='importmcount' value='<%=request.getAttribute("mpimportmpmcount") %>'>
    <input type="hidden" name='importmprice' value='<%=request.getAttribute("mpimportmpmprice") %>'>

<%--     <input type="hidden" name='importmcount' value='<%=request.getAttribute("mpimportimcount") %>'>
    <input type="hidden" name='importmprice' value='<%=request.getAttribute("mpimportimprice") %>'> --%>
    
    <table align="center" border='1' cellspacing='0px' cellpadding='5px'>
      <tr>
        <th>입고수량</th>
        <td><input type='text' name='imcount' size='25' value='<%=0%>'></td>
      </tr>
      <tr>
        <th>입고가격</th>
        <td><input type='text' name='imprice' size='25' value='<%=0%>'></td>
      </tr>
      <tr>
        <th>구매처</th>
        <td><input type='text' name='imclient' size='25' value='지점'></td>
      </tr>
    </table>

    <div class='bottom'>
      <input type="submit" value='등록'> 
      <input type="button" value='닫기' 
             onclick="windowcolse()">
    </div>
  </form>
</body>
</html>