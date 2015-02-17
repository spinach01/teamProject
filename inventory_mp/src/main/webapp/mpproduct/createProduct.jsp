<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ page import="inventory.project.mp.MpproductDTO" %>
<%
//MpproductDTO dto = (MpproductDTO)request.getAttribute("dto");

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
<body>
  <div class='title'>상품 등록</div>

  <form name='frm' method='post' action='./createProduct.do' enctype="multipart/form-data">
    <input type="hidden" name='mpgroupno' value='<%=request.getAttribute("mpgroupno") %>'>
    <input type="hidden" name='mcount' value='<%=request.getAttribute("mcount") %>'>
    <table align="center" border='1' cellspacing='0px' cellpadding='5px'>
      <tr>
        <th>그룹번호</th>
        <td><input name='mpgroupno' value='<%=request.getAttribute("mpgroupno") %>'></td>
      </tr>
      <tr>
        <th>상품명</th>
        <td><input type='text' name='mpname' size='50' value='상품명'></td>
      </tr>
      <tr>
        <th>상품사진</th>
        <td>
          상품사진을 등록하세요.<br>
          <input type='file' name='fnameMF' size='50'>
        </td>
      </tr>
      <tr>
        <th>재고</th>
        <td><input type='text' name='mcount' size='50' value=''></td>
      </tr>
      <tr>
        <th>가격</th>
        <td><input type='text' name='price' size='50' value=''></td>
      </tr>
      <tr>
        <th>용량</th>
        <td><input type='text' name='volume' size='50' value='용량'></td>
      </tr>
      <tr>
        <th>피부타입</th>
        <td><input type='text' name='ftype' size='50' value='피부타입'></td>
      </tr>
      <tr>
        <th>상세정보</th>
        <td><TEXTAREA name='content' rows='10' cols='60'>상세정보를 입력하세요.</TEXTAREA></td>
      </tr>
      <tr>
        <th>비고</th>
        <td><input type='text' name='etc' size='50' value='검토'></td>
      </tr>
      <tr>
        <th>아이디</th>
        <td><input type='text' name='id' size='50' value='user1'></td>
      </tr>
    </table>

    <div class='bottom'>
      <input type="submit" value='등록'> 
      <input type="button" value='목록' onclick="location.href='./list.do?mpgroupno=<%=request.getAttribute("mpgroupno")%>'">
    </div>
  </form>
</body>
</html>