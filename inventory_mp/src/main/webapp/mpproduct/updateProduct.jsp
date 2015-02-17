<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="inventory.project.mp.MpproductDTO" %>
<%
MpproductDTO dto = (MpproductDTO)request.getAttribute("dto");
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
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

  <div class='title'>상품 수정</div>

  <form name='frm' method='post' action='./updateProduct.do' enctype="multipart/form-data">
    <input type="hidden" name='mpgroupno' value='<%=dto.getMpgroupno() %>'>
    <input type="hidden" name='productno' value='<%=dto.getProductno() %>'>
    
    <table align="center" border='1' cellspacing='0px' cellpadding='5px'>
      <tr>
        <th>상품명</th>
        <td><input type='text' name='mpname' size='50' value='<%=dto.getMpname() %>'></td>
      </tr>
      <tr>
        <th>상품사진</th>
        <td>
          등록된 파일명: <%=dto.getFname() %><br>
          <input type='file' name='fnameMF' size='50'>
        </td>
      </tr>
      <tr>
        <th>수량</th>
        <td><input type='text' name='mcount' size='50' value='<%=dto.getMcount() %>'></td>
      </tr>      
      <tr>
        <th>가격</th>
        <td><input type='text' name='price' size='50' value='<%=dto.getPrice() %>'></td>
      </tr>
      <tr>
        <th>용량</th>
        <td><input type='text' name='volume' size='50' value='<%=dto.getVolume() %>'></td>
      </tr>
      <tr>
        <th>피부타입</th>
        <td><input type='text' name='ftype' size='50' value='<%=dto.getFtype() %>'></td>
      </tr>
      <tr>
        <th>상세정보</th>
        <td><TEXTAREA name='content' rows='10' cols='60'><%=dto.getContent() %></TEXTAREA></td>
      </tr>
    </table>

    <div class='bottom'>
      <input type="submit" value='수정'> 
      <input type="button" value='목록' onclick="history.back()">
    </div>
  </form>
  
<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>