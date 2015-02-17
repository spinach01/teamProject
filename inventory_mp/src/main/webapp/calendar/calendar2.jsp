<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Calendar, java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="www.utility.Utility"%>
<%@ page import="www.calendar.CalendarDAO,www.calendar.CalendarDTO"%> 
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>

<body>

	<%
    Calendar cal = Calendar.getInstance();
    int year = Integer.parseInt(request.getParameter("y"));
    int month = Integer.parseInt(request.getParameter("m"));
      

    
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
	<table  border="0" cellpadding="0" cellspacing="0" width="100%" align='center' style="margin-top: 0px">
    <tr>
      <td align="center"><a
        href="./index.do?y=<%=prevYear%>&m=<%=prevMonth%>">[이전달]</a> 
        <%=year%>년 <%=month+1%>월 <a
        href="./index.do?y=<%=nextYear%>&m=<%=nextMonth%>">[다음달]</a></td>
    </tr>	
		<tr bgcolor="#ffffff">
			<td>
				<table border="1"  class='calendar' cellpadding="0" cellspacing="0" width='100%' align='center'>
					<tr>
						<td class='calendar_td' width='14%' align='center'>일</td>
						<td class='calendar_td' width='14%' align='center'>월</td>
						<td class='calendar_td' width='14%' align='center'>화</td>
						<td class='calendar_td' width='14%' align='center'>수</td>
						<td class='calendar_td' width='14%' align='center'>목</td>
						<td class='calendar_td' width='14%' align='center'>금</td>
						<td class='calendar_td' width='16%' align='center'>토</td>
					</tr>
					<tr>
						<%
    // 시작요일까지 이동
    for (int i=1; i<bgnWeek; i++){
      out.println("<td class='calendar_td'>&nbsp;</td>");
    }

    // 첫날~마지막날까지 처리
    // - 날짜를 하루씩 이동하여 월이 바뀔때까지 그린다
    String str="";
    ArrayList list = null;
    CalendarDAO dao = new CalendarDAO();
    StringBuffer sb = null;
    
    while (cal.get(Calendar.MONTH) == month) {
     
      if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
        out.println("<td class='calendar_td' valign='top'><span style='color:blue;'>" + cal.get(Calendar.DATE) + "</span>");
      }else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
        out.println("<td class='calendar_td' valign='top'><span style='color:red;'>" + cal.get(Calendar.DATE) + "</span>");
      }else{
        out.println("<td class='calendar_td' valign='top'>" + cal.get(Calendar.DATE));
      }
      
      // str = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DATE);
      str = Utility.getDate6(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
      list = dao.listLabel(str);
      
      if (list != null){ 
        sb = new StringBuffer();
        for(int i=0; i<list.size(); i++){
          CalendarDTO dto = (CalendarDTO)list.get(i);
          sb.append("<img src='"+Utility.getRoot()+"/calendar/images/bu5.gif'>");
          sb.append("<a href='../calendar/read.jsp?calendarno="+dto.getCalendarno()+"'>"+dto.getLabel()+"</a><br>");
        }
        
      }
      out.println("<br><span style='color:#00AA00'>" + sb.toString() + "</span></td>");
      
      // 한달의 마지막 날이 아니면서 토요일인 경우 다음줄로 생성
      // System.out.println(cal.getActualMaximum ( Calendar.DAY_OF_MONTH ));
      if ((cal.getActualMaximum ( Calendar.DAY_OF_MONTH ) != cal.get(Calendar.DATE))) {
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){ // 토요일인 경우
          out.println("</tr><tr>");
        }
      }

      // 날짜 증가시키기
      cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)+1);
      // 끝날부터 토요일까지 빈칸으로 처리
      
    }
    System.out.println("cal.get(Calendar.MONTH): " + cal.get(Calendar.MONTH));
    System.out.println("cal.get(Calendar.DATE): " + cal.get(Calendar.DATE));
    System.out.println("cal.get(Calendar.DATE): " + cal.get(Calendar.DATE));
    
    if (cal.get(Calendar.DATE) == 1 && (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
      // 한달의 마지막 날짜 토요일이면 아무일도 안함
    }else{
      for (int i=cal.get(Calendar.DAY_OF_WEEK); i<=7; i++) out.println("<td class='calendar_td'>&nbsp;</td>");
    }

%>
					</tr>
				</table>

			</td>
		</tr>
	</table>
	
</body> 

</html>




