<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="inventory.project.mp.MpproductDTO" %>
<%@ page import="inventory.project.mp.MpproductDAO" %>
<%@ page import="inventory.project.group.MpgroupDTO" %>
<%@ page import="www.utility.Utility, www.utility.Paging" %>

<%

MpproductDTO dto = null;
MpproductDTO mpproductDTO = (MpproductDTO)request.getAttribute("MpproductDTO");

MpproductDAO dao = new MpproductDAO();

ArrayList list = (ArrayList)request.getAttribute("list");
ArrayList distintNamelist = (ArrayList)request.getAttribute("searchName");
ArrayList distintFtypelist = (ArrayList)request.getAttribute("searchFtype");

int nowPage = mpproductDTO.getNowPage();
int recordPerPage = mpproductDTO.getRecordPerPage();
int totalRecord=mpproductDTO.getTotalRecord();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
*{
  font-family: 돋움체;
  font-size: 18px;
}
</style>
<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>

<script type="text/javascript">
// 체크박스 삭제
  $(document).ready(function() {
    // 체크박스 전체선택  
    $("#checkAll").click(function() {
      //console.log($('input[name=chkid]'));  
      $('input[name="chkid"]').prop('checked', $(this).is(":checked"));
    });
  });

  //체크박스에 체크 여부 확인
  function del() {
    if ($("input[name=chkid]").is(':checked') == false) {
      alert("삭제할 상품을 체크하세요.");
    } else {
      //체크되어 있을 경우 삭제하기
      $("tr.wrap input:checkbox[name^=chkid]:checked").each(
        function() {
            var productno = $(this).attr("value"); // 체크된 값만을 불러 들인다.
            // alert(value);
            /* if(checked==true){
            $(this).next().remove(); //span내용지우기
            $(this).remove();   //checkbox 지우기
            } */
            //$.post("../mpproduct/deleteProduct.do", cheched,  deleteResponse);
            //jquery에서는 int표현안함
            delProcess(productno);
      });
    };
  }
  
    function delProcess(productno){
        var params = "productno=" +  productno; // 특수 문자 변환 처리
        $.ajaxSetup({ dataType: "text" });   // Ajax 객체 준비
        $.post("./deleteProduct.do", params, delProcessResponse);
    }
      
    function delProcessResponse(data, textStatus){
        // $("#id_panel").html(data.replace(/^\s*|\s*$/g, '')); // HTML 지원, 출력 대상
        alert(data.replace(/^\s*|\s*$/g, ''));
        window.location.reload();
    }

    
</script> 
  
<script type="text/javascript">    
    //검색
    function mpnameLoad(){
     // var mpname = document.frm.col.value;
     // alert(mpname);
     document frm.submit();
    }    
</script>

<script type="text/javascript">
 // 입고 관련
  function windowCheckImport(productno) {
	  var params =productno+"";
	  url = "./createImport.do?productno="+params+"&mpgroupno="+<%=request.getAttribute("mpgroupno")%>;
    wr = window.open(url, "반품", "width=400 ,height=370");
    wr.moveTo((window.screen.width - 550) / 2, (window.screen.height - 450) / 2); 
  }  

  // 출고 관련
  function windowCheckExport(productno) {
    var params =productno+"";
    url = "./createExport.do?productno="+params+"&mpgroupno="+<%=request.getAttribute("mpgroupno")%>;
    wr = window.open(url, "반품", "width=400 ,height=350");
    wr.moveTo((window.screen.width - 550) / 2, (window.screen.height - 450) / 2); 
  } 
 
  // 반품 관련
  function windowCheckReturn(productno) {
    var params =productno+"";
    url = "./createReturn.do?productno="+params+"&mpgroupno="+<%=request.getAttribute("mpgroupno")%>;
    wr = window.open(url, "반품", "width=400 ,height=350");
    wr.moveTo((window.screen.width - 550) / 2, (window.screen.height - 450) / 2); 
  }   

</script>

</head>

<!-- **********************메뉴바************************* -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->
<div id="container">
<div id="w1000lay">
<DIV class='title'><%=request.getAttribute("mpgrname") %> 목록</DIV>

<DIV class='top'>

  <FORM name='frm' method='GET' action='./list.do'>
    <input type='hidden' name='mpgroupno' value='<%=request.getAttribute("mpgroupno")%>'>
    
    <select name='colName' onchange="mpnameLoad()"> 
      <option value=""> 선택 </option>
    <%
    for(int i=0; i<distintNamelist.size(); i++){
      dto = (MpproductDTO)distintNamelist.get(i);
    %>
       <option value="<%=dto.getMpname()%>"><%=dto.getMpname()%></option> 
    <% 
      }
    %>
    </select>
  
    <!-- <input type='submit' value='검색' > -->

    <select name='colFtype' onchange="mpnameLoad()"> 
    <!-- <select name='colFtype' > -->
    <option value=""> 선택 </option>
    <%
    for(int i=0; i<distintFtypelist.size(); i++){
      dto = (MpproductDTO)distintFtypelist.get(i);
      //int productno = dto.getProductno();
    %>
       <option value="<%=dto.getFtype()%>"> <%=dto.getFtype() %> </option>
    <%    
      }
    %>
    </select>
  
    <input type='submit' value='검색' >
    <!-- <input type='button' value='검색' onclick="document.frm.submit()"> -->
    
    <input type="button" value="물품등록" 
         onclick="location.href='./createProduct.do?mpgroupno=<%=request.getAttribute("mpgroupno")%>'">
  </FORM>
</DIV>
</div></div>

<div>
  <TABLE class='product_table' align="center">
    <TR>
      <TH><input type="checkbox" id="checkAll" name="checkAll"  class="chk" /></TH>
      <TH>화장품번호</TH>
      <TH>수정</TH>
      <TH>사진</TH>
      <TH>화장품명</TH>
      <TH>가격</TH>
      <TH>수량</TH>
      <TH>피부타입</TH>
      <TH>비고</TH>
      <TH>관리옵션</TH>
      <TH>그룹번호</TH>
    </TR>
    <%
    //ArrayList list = (ArrayList)request.getAttribute("list");
    for(int i=0; i<list.size(); i++){
      dto = (MpproductDTO)list.get(i);
      int productno = dto.getProductno();      
    %>
    <TR class='wrap'>
      <TD><input type="checkbox" name='chkid' class="chk"
        id='delCheck<%=dto.getProductno()%>' value="<%=dto.getProductno()%>"></TD>
      <TD><%=dto.getProductno()%></TD>
      <TD>
        <input type='button' value='수정' onclick="location.href='./updateProduct.do?productno=<%=productno %>'">
      </TD>
      <TD>
      
      <img src="./storage/<%=dto.getFname() %>" width="90" height="70" >
     
      </TD>
      <TD><A href='../mpproduct/read.do?productno=<%=productno %>'><%=dto.getMpname() %></A></TD>
      <TD><%=dto.getPrice() %></TD>
      <TD><%=dto.getMcount() %></TD>
      <TD><%=dto.getFtype() %></TD>
      <TD><%=dto.getEtc() %></TD>  
      <TD> 
        <input type='button' value='입고' onclick="windowCheckImport(<%=productno%>)">
        <input type='button' value='출고' onclick="windowCheckExport(<%=productno%>)">
        <input type='button' value='반품' onclick="windowCheckReturn(<%=productno%>)">
      </TD>
      <TD><%=dto.getMpgroupno()%></TD>   
    </TR>    
    <%    
    }
    %>
  </TABLE></div>

  <DIV class='bottom'>
    <input type='button' value='홈' onclick="location.href='../mpgroup/index.do'">
    <input type="button" value="선택삭제" onclick="del()">
    <% 
      out.println(new Paging().paging4(totalRecord, nowPage, recordPerPage, dto.getMpgroupno(), mpproductDTO));
    %>    
  </DIV>
  

<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->

</html>


