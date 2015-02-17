<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="./ssi.jsp" %>

<%
String dongli = request.getParameter("dongli");
// System.out.println("dongli: " + dongli);

if (dongli.length() == 0){
  out.println("<span style='color: #FF0000; font-weight: bold;'>※ 동리를 입력해주세요.</span>");
  
}else{
  
  ArrayList list = dao.zipcodeList(dongli);
  
  if (list.size() == 0){ 
    out.println("<span style='color: #FF0000; font-weight: bold;'>※ 검색된 결과가 없습니다.</span>");
  }else{
    out.println("※ 아래 우편번호를 클릭하면 자동으로 입력됩니다.<br><br>");

    for(int i=0; i < list.size(); i++){
      ZipcodeDTO dto = (ZipcodeDTO)list.get(i);
       
      String zipcode = dto.getZipcode();
      String sido = dto.getSido().trim();      // 공백 제거
      String gugun = dto.getGugun().trim();    // 공백 제거
      String _dongli = dto.getDongli().trim(); // 공백제거
      String etc = dto.getEtc().trim();        // 공백 제거
  %>
      <a href="javascript:sendAddress('<%=zipcode%>','<%=sido%>','<%=gugun%>','<%=_dongli%>','<%=etc%>')">
        <%=zipcode%> <%=sido%> <%=gugun%> <%=_dongli%> <%=etc%>
      </a>
      <br>
<%  
    }       
  }
}
%>

