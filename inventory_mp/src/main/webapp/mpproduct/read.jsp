<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="inventory.project.mp.MpproductDTO" %>
<%
MpproductDTO dto = (MpproductDTO)request.getAttribute("dto");
%>
<%
  request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
* {
	font-family:돋움체;
	font-size: 14px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

	<div class="title">상품정보페이지</div>
	<form name='frm' method='post' action=''>
	<table class='product_table' border='1' align="center" cellspacing='0px' cellpadding='5px'>
    <tr>
      <td width='330'><img src="./storage/<%=dto.getFname() %>"></td>
      <td width='310'>
        <table align='center'>
          <tr>
            <td width='100'>상품명</td>
            <td width='100'><%=dto.getMpname() %></td>
          </tr>
          <tr>
            <td>수량</td>
            <td><%=dto.getMcount() %></td>
          </tr>
          <tr>
            <td>가격</td>
            <td><%=dto.getPrice() %></td>
          </tr>
          <tr>
            <td>용량</td>
            <td><%=dto.getVolume() %></td>
          </tr>
          <tr>
            <td>피부타입</td>
            <td><%=dto.getFtype() %></td>
          </tr>
        </table>
      </td>
    </tr>
    <tr bordercolor="ddd">
      <!-- 상세정보 -->
      <td colspan="2"><%=dto.getContent() %></td>
    </tr>
	</table>

	<div class='bottom'>
		<input type="button" value="수정" onclick="location.href='./updateProduct.do?productno=<%=dto.getProductno()%>'"> 
		<input type="button"	value='목록' onclick="history.back()">
		<input type="button" value="삭제" onclick="location.href='./deleteProduct.do?productno=<%=dto.getProductno()%>'">
	</div>
	

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>