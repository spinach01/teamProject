<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="inventory.project.mp.MpproductJSPDAO"%>
<%@ page import="inventory.project.mp.MpproductDTO"%>
<%@ page import="www.utility.Utility"%>

<%
	MpproductJSPDAO dao = new MpproductJSPDAO();
	// index.jsp로부터 파라미터 받음
	String index_title = request.getParameter("index_title");
	int cnt = Integer.parseInt(request.getParameter("cnt"));
	System.out.println(cnt);

	//ArrayList list = (ArrayList)request.getAttribute("list");
	ArrayList list = dao.listTop(cnt); // 글목록 산출
%>
<div style="text-align: left; width: 100%; margin-top: 6px">
	<fieldset class="fieldset5">
		<legend>
			<b><%=index_title%>&nbsp;[<a
				href='<%=Utility.getRoot()%>/mpproduct/list.jsp'>more...</a>]</b>
		</legend>

		<SPAN style="text-align: center; width: 100%;"><center> <%
 	//이미지가 하나도 없는 경우   
 	if (list.size() == 0) {
 		out.println("등록된 이미지가 없습니다.");
 	} else {
 		for (int index = 0; index < list.size(); index++) {
 			MpproductDTO dto = (MpproductDTO) list.get(index);
 %> <A href='../mpproduct/storage/<%=dto.getFname()%>'> <IMG
				src="../mpproduct/storage/<%=dto.getFname()%>" width='100px' height='100px'
				border='0' >
		</A> <%
 	}
 	}
 %>
		</center></SPAN>
	</fieldset>
</div>

