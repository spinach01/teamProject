<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ page import="inventory.project.mpreturn.MpreturnDTO" %>
<%@ page import="inventory.project.mp.MpproductDTO" %>
<%
MpproductDTO dto = (MpproductDTO)request.getAttribute("mpproductDTO");
//MpreturnDTO mpretDTO = (MpreturnDTO)request.getAttribute("mpreturnDTO"); 

/* if(request.getAttribute("mpreturnretcount") == null){
  request.setAttribute("mpreturnretcount", 0);
} */

//out.println("return page:"+ dto.getProductno());    
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
  <div class='title'><%=dto.getProductno() %>번 <%=dto.getMpname() %> 상품 반품</div>

  <form name='frm' method='post' action='./returncreate.do'>
    <input type="hidden" name='productno' value='<%=dto.getProductno() %>'>
    <input type="hidden" name='mpgroupno' value='<%=dto.getMpgroupno() %>'>
    <input type="hidden" name='returngroupno' value='<%=request.getAttribute("mpreturnmpgroupno") %>'>
    <input type="hidden" name='returnmcount' value='<%=request.getAttribute("mpreturnmpmcount") %>'>
    
    <%-- <input type="hidden" name='retcount' value='<%=request.getAttribute("mpreturnretcount") %>'> --%>
   
    <table align="center" border='1' cellspacing='0px' cellpadding='5px'>
      <tr>
        <th>반품 수량</th>
        <td><input type='text' name='retcount' size='25' value='<%=0%>'></td>
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