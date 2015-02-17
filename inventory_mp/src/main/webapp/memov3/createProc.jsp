<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>

<%
String title = request.getParameter("title");
String content = request.getParameter("content");

MemoVO vo = new MemoVO();
vo.setTitle(title);
vo.setContent(content);

int cnt = dao.create(vo);
//int cnt = dao.create(title, content); // 컬럼 30개 정도면 개발 안됨.. 모아서 객체를 만들어 전달..
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>등록 처리</title>

<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 24px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

<DIV class="title"> 등록 처리</DIV>

<DIV class='msg'>
<h2>
<%
if (cnt == 1){
    out.println("등록했습니다.");
}else{
    out.println("등록 실패했습니다.");
}
%>

[<a href='./list.jsp'>목록으로 가기</a>]
</h2>
</DIV>

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>
