<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Calendar"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

	<%
    Calendar cal = Calendar.getInstance();
    int year = request.getParameter("y") == null ? cal.get(Calendar.YEAR) : Integer.parseInt(request.getParameter("y"));
    int month = request.getParameter("m") == null ? cal.get(Calendar.MONTH) : (Integer.parseInt(request.getParameter("m")) - 1);

    // 시작요일 확인
    // - Calendar MONTH는 0-11까지임
    cal.set(year, month, 1);
    int bgnWeek = cal.get(Calendar.DAY_OF_WEEK);

    // 다음/이전월 계산
    // - MONTH 계산시 표기월로 계산하기 때문에 +1을 한 상태에서 계산함
    int prevYear = year;
    int prevMonth = (month + 1) - 1;
    int nextYear = year;
    int nextMonth = (month  + 1) + 1;

    // 1월인 경우 이전년 12월로 지정
    if (prevMonth < 1) {
        prevYear--;
        prevMonth = 12;
    }

    // 12월인 경우 다음년 1월로 지정
    if (nextMonth > 12) {
        nextYear++;
        nextMonth = 1;
    }
%>
	<table border="0" cellpadding="0" cellspacing="0" width="80%" align='center'>
		<tr>
			<td align="center"><a
				href="./calendar.jsp?y=<%=prevYear%>&m=<%=prevMonth%>">◁</a> <%=year%>년
				<%=month+1%>월 <a
				href="./calendar.jsp?y=<%=nextYear%>&m=<%=nextMonth%>">▷</a></td>
		</tr>
		<tr>
			<td>

				<table border="1" cellpadding="0" cellspacing="0" width='100%' height='250px' align='center'>
					<tr>
						<td width='14%' align='center'>일</td>
						<td width='14%' align='center'>월</td>
						<td width='14%' align='center'>화</td>
						<td width='14%' align='center'>수</td>
						<td width='14%' align='center'>목</td>
						<td width='14%' align='center'>금</td>
						<td width='16%' align='center'>토</td>
					</tr>
					<tr height='14%'>
						<%
    // 시작요일까지 이동
    for (int i=1; i<bgnWeek; i++){
      out.println("<td>&nbsp;</td>");
    }

    // 첫날~마지막날까지 처리
    // - 날짜를 하루씩 이동하여 월이 바뀔때까지 그린다
    while (cal.get(Calendar.MONTH) == month) {
        
      if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
        out.println("<td valign='top'><span style='color:blue;'>" + cal.get(Calendar.DATE) + "</span></td>");
      }else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
        out.println("<td valign='top'><span style='color:red;'>" + cal.get(Calendar.DATE) + "</span></td>");
      }else{
        out.println("<td valign='top'>" + cal.get(Calendar.DATE) + "</td>");
      }
      // 토요일인 경우 다음줄로 생성
      if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
        out.println("</tr><tr>");
      }

      // 날짜 증가시키기
      cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)+1);
    }

    // 끝날부터 토요일까지 빈칸으로 처리
    for (int i=cal.get(Calendar.DAY_OF_WEEK); i<=7; i++) out.println("<td>&nbsp;</td>");
%>
					</tr>
				</table>

			</td>
		</tr>
	</table>
	
<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>




