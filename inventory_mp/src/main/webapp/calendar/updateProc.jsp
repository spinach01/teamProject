<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file ="ssi.jsp" %>

<%
int calendarno = Integer.parseInt(request.getParameter("calendarno"));

String labeldate = request.getParameter("labeldate");
String label = request.getParameter("label");
String title = request.getParameter("title");
String content = request.getParameter("content");

CalendarDTO dto = new CalendarDTO();
dto.setCalendarno(calendarno);
dto.setLabeldate(labeldate);
dto.setLabel(label);
dto.setTitle(title);
dto.setContent(content);

int cnt = dao.update(dto);

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>변경 처리</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<a href = './images/logo.png'></a>
<!-- *********************************************** -->
<body style="margin: 0px">
<jsp:include page="../menubar/top.jsp" flush="false"/>
<!-- *********************************************** -->


<h2>
<%
if (cnt == 1){
    // out.println("등록했습니다.");
    response.sendRedirect("./list.jsp");
}else{
    out.println("변경 실패했습니다.");
    out.println("<br><br> <input type='button' value='다시 시도' onclick='history.back()'>");
}
%>
</h2>
<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
</html>
<!-- *********************************************** -->




