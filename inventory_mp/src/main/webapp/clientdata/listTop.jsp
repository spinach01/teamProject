<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>

<%
// index.jsp로부터 파라미터 받음
String index_title = request.getParameter("index_title");
int cnt = Integer.parseInt(request.getParameter("cnt"));
ArrayList list = dao.list(cnt); // 글목록 산출    
%> 
<div style="text-align: left; width: 100%; margin-top: 6px">
<fieldset class="fieldset5">
    <legend><b><%=index_title%>&nbsp;[<a href='<%=Utility.getRoot() %>/clientdata/list.jsp'>more...</a>]</b>
    </legend> 

   <% 
   //글이 하나도 없는 경우, Vector에 객체가 하나도 없는 경우   
   if (list.size() == 0) {
     out.println("등록된 글이 없습니다.");
   }else{
     for(int i=0; i< list.size(); i++){
       ClientdataDTO dto = (ClientdataDTO)list.get(i);
       int cdatano = dto.getCdatano();
       String title = Utility.convertChar(dto.getTitle());
       out.println("<img src='"+ Utility.getRoot() +"/clientdata/images/bu3.gif'></img>");
       %>
       <!----------글 읽기----------->
       <a href='<%=Utility.getRoot() %>/clientdata/read.jsp?cdatano=<%=cdatano %>'>
       <%
        if (title.length() > 95){
          out.println(title.substring(0, 95) + "...");
        }else{
          out.println(title);
        }
       %>
       </a>
       <br/>
       <%
     }
   }
%>
</fieldset>     
</div>

