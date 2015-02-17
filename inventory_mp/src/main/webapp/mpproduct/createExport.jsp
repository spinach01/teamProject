<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ page import="inventory.project.mpexport.MpexportDTO" %>
<%@ page import="inventory.project.mp.MpproductDTO" %>
<%
MpproductDTO dto = (MpproductDTO)request.getAttribute("mpproductDTO");
//MpexportDTO mpexDTO = (MpexportDTO)request.getAttribute("mpexportDTO"); 

/* if(request.getAttribute("mpexportexcount") == null){
  request.setAttribute("mpexportexcount", 0);
}

if(request.getAttribute("mpexportexprice") == null){
  request.setAttribute("mpexportexprice", 0);
} 

out.println("mpexDTO.getExcount()>>>>>>>>>>>>>>>:::" + (request.getAttribute("mpexportexcount")));  */

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
  <div class='title'><%=dto.getProductno() %>번 <%=dto.getMpname() %> 상품 출고</div>

  <form name='frm' method='post' action='./createExport.do'>
  
    <input type="hidden" name='productno' value='<%=dto.getProductno() %>'>
    <input type="hidden" name='mpgroupno' value='<%=dto.getMpgroupno() %>'>
    <input type="hidden" name='exportgroupno' value='<%=request.getAttribute("mpexportmpgroupno") %>'>
    <input type="hidden" name='exportmcount' value='<%=request.getAttribute("mpexportmpmcount") %>'>
    <input type="hidden" name='exportmprice' value='<%=request.getAttribute("mpexportmpmprice") %>'>

    <%-- <input type="hidden" name='excount' value='<%=request.getAttribute("mpexportexcount") %>'>
    <input type="hidden" name='exprice' value='<%=request.getAttribute("mpexportexprice") %>'> --%>
    
    <table align="center" border='1' cellspacing='0px' cellpadding='5px'>
      <tr>
        <th>출고수량</th>
        <td><input type='text' name='excount' size='25' value='<%=0%>'></td>
      </tr>
      <tr>
        <th>출고가격</th>
        <td><input type='text' name='exprice' size='25' value='<%=0%>'></td>
      </tr>
      <tr>
        <th>출고처</th>
        <td><input type='text' name='excustumer' size='25' value='본점'></td>
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