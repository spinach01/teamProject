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

  <div class="title">삭제</div>
  <div class='content2'>
     상품명: <%=dto.getMpname() %><br>
     상품이미지: <%=dto.getFname() %><br>
     삭제하시면 복구할 수 없습니다.
  </div>
  <form name='frm' method='post' action='./deleteProduct.do'>
  <input type="hidden" name='mpgroupno' value='<%=dto.getMpgroupno() %>'>
  <input type="hidden" name='productno' value='<%=dto.getProductno() %>'>
  <div class='bottom'>
    <input type="submit" value='삭제진행'> 
    <input type="button"  value='삭제취소' onclick="history.back();">
  </div>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>