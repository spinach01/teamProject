<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="inventory.project.group.MpgroupDTO"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="www.utility.Utility" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="shortcut icon" href="./favicon.ico" type="image/x-icon">
<link href="../css/style.css" rel="Stylesheet" type="text/css">


<!-- SlidesJS Required (if responsive): Sets the page width to the device width. -->
  <meta name="viewport" content="width=device-width">
  <!-- End SlidesJS Required -->

  <!-- CSS for slidesjs.com example -->
  <link rel="stylesheet" href="../css/example.css">
  <link rel="stylesheet" href="../css/font-awesome.min.css">
  <!-- End CSS for slidesjs.com example -->

  <!-- SlidesJS Optional: If you'd like to use this design -->
  <style>
    body {
      -webkit-font-smoothing: antialiased;
      font: normal 15px/1.5 "Helvetica Neue", Helvetica, Arial, sans-serif;
      color: #232525;
      padding-top:70px;
    }

    #slides {
      display: none
    }

    #slides .slidesjs-navigation {
      margin-top:5px;
    }

    a.slidesjs-next,
    a.slidesjs-previous,
    a.slidesjs-play,
    a.slidesjs-stop {
      background-image: url(img/btns-next-prev.png);
      background-repeat: no-repeat;
      display:block;
      width:12px;
      height:18px;
      overflow: hidden;
      text-indent: -9999px;
      float: left;
      margin-right:5px;
    }

    a.slidesjs-next {
      margin-right:10px;
      background-position: -12px 0;
    }

    a:hover.slidesjs-next {
      background-position: -12px -18px;
    }

    a.slidesjs-previous {
      background-position: 0 0;
    }

    a:hover.slidesjs-previous {
      background-position: 0 -18px;
    }

    a.slidesjs-play {
      width:15px;
      background-position: -25px 0;
    }

    a:hover.slidesjs-play {
      background-position: -25px -18px;
    }

    a.slidesjs-stop {
      width:18px;
      background-position: -41px 0;
    }

    a:hover.slidesjs-stop {
      background-position: -41px -18px;
    }

    .slidesjs-pagination {
      margin: 7px 0 0;
      float: right;
      list-style: none;
    }

    .slidesjs-pagination li {
      float: left;
      margin: 0 1px;
    }

    .slidesjs-pagination li a {
      display: block;
      width: 13px;
      height: 0;
      padding-top: 13px;
      background-image: url(img/pagination.png);
      background-position: 0 0;
      float: left;
      overflow: hidden;
    }

    .slidesjs-pagination li a.active,
    .slidesjs-pagination li a:hover.active {
      background-position: 0 -13px
    }

    .slidesjs-pagination li a:hover {
      background-position: 0 -26px
    }

    #slides a:link,
    #slides a:visited {
      color: #333
    }

    #slides a:hover,
    #slides a:active {
      color: #9e2020
    }

    .navbar {
      overflow: hidden
    }
  </style>
  <!-- End SlidesJS Optional-->

  <!-- SlidesJS Required: These styles are required if you'd like a responsive slideshow -->
  <style>
    #slides {
      display: none
    }

    .container {
      margin: 0 auto
    }

    /* For tablets & smart phones */
    @media (max-width: 767px) {
      body {
        padding-left: 20px;
        padding-right: 20px;
      }
      .container {
        width: auto
      }
    }

    /* For smartphones */
    @media (max-width: 480px) {
      .container {
        width: auto
      }
    }

    /* For smaller displays like laptops */
    @media (min-width: 768px) and (max-width: 979px) {
      .container {
        width: 30%
      }
    }

    /* For larger displays */
    @media (min-width: 1200px) {
      .container {
        width: 300px
      }
    }
  </style>
  
  
   <!-- SlidesJS Required: Link to jQuery -->
  <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
  <!-- SlidesJS Required: Link to jquery.slides.js -->
  <script src="./js/jquery.slides.min.js"></script>
  <!-- End SlidesJS Required -->
  
  <!-- SlidesJS Required: Initialize SlidesJS with a jQuery doc ready -->
  <script>
    $(function() {
      $('#slides').slidesjs({
        width: 50,
        height: 50,
        play: {
          active: true,
          auto: true,
          interval: 2000,
          swap: true
        }
      });
    });
    
</script>


</head>

<!-- *********************************************** -->
<body leftmargin="0" topmargin="0">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- *********************************************** --> 
 <br>
   <!-- ********************품목목록*************************** -->  
  <%
    ArrayList list = (ArrayList) request.getAttribute("index");
  %>
  <TABLE width=1000px align="center" cellspacing="0" cellpadding="0" border="0" 
           style= 'border-collapse: collapse; margin-bottom: 5px; '>
  <FORM name='frm' method='GET' action='../mpproduct/list.do'>
      <TR>
        <td width="70%"></td>
        <th>품목</th>
        <td>
          <select name='mpgroupno'>
            <%
              for (int i = 0; i < list.size(); i++) {
                MpgroupDTO dto = (MpgroupDTO) list.get(i);
            %>
            <option value="<%=dto.getMpgroupno()%>">
              <%=dto.getMpgrname()%> /<%=dto.getMpgroupno()%>
            </option>

            <%
              }
            %>
          </select>
          <input type='submit' value='검색'>
          <input type='button' value='품목목록' onclick="location.href='./list.do'">
        </td>
      </tr>
  </FORM>
  </TABLE><br><br>
  <!-- ********************품목목록 끝*************************** --> 

  <!-- ********************슬라이드 시작*************************** -->
  <TABLE width=1000px align="center" cellspacing="0" cellpadding="0" border="0" 
           style= 'border-collapse: collapse; margin-bottom: 5px; '>
  <TR>  
   <TD rowspan=4 align="center">
       <td width='50%' >

  <!-- 슬라이드시작 -->
  <div class="container">
    <div id="slides">
      <img src="./images/product01.jpg" alt="1">
      <img src="./images/product02.jpg" alt="2">
      <img src="./images/product03.jpg" alt="3">
      <img src="./images/product04.jpg" alt="4">
    </div>
  </div>
  </td>
 
  <!-- ********************슬라이드 끝*************************** -->  

 <!-- ************************달력시작*********************** -->
   
<TD>
<%
Calendar cal = Calendar.getInstance();
int year = request.getParameter("y") == null ? cal.get(Calendar.YEAR) : Integer.parseInt(request.getParameter("y"));
int month = request.getParameter("m") == null ? cal.get(Calendar.MONTH) : (Integer.parseInt(request.getParameter("m")) - 1);
%>

<DIV style='text-align: center;'>
  <jsp:include page="/calendar/calendar2.jsp" flush="false" >
    <jsp:param name='y' value='<%=year%>'/>
    <jsp:param name='m' value='<%=month%>'/>
  </jsp:include>
        
</DIV>
</TD>
</TR>
 </TABLE><br>
 <!-- ************************달력끝*********************** -->
 
  <!-- ************************미니게시판 시작*********************** -->
 <TABLE width=1000px align="center" cellspacing="0" cellpadding="0" border="0" 
           style= 'border-collapse: collapse; margin-bottom: 5px;'>
 <tr>
  <td width="50%">
    <div>
      <jsp:include page="../mpnotice/listTop.jsp" flush="false">
        <jsp:param value="공지사항" name="index_title"/>
        <jsp:param value="5" name="cnt"/>
      </jsp:include>
    </div>
    <div>
      <jsp:include page="../clientbbs/listTop.jsp" flush="false">
        <jsp:param value="고객 게시판" name="index_title"/>
        <jsp:param value="5" name="cnt"/>
      </jsp:include>
    </div>
  </td>
  <td>
    <div>
      <jsp:include page="../clientdata/listTop.jsp" flush="false">
        <jsp:param value="고객 자료실" name="index_title"/>
        <jsp:param value="5" name="cnt"/>
      </jsp:include>
    </div>
    <div>
      <jsp:include page="../memov3/listTop.jsp" flush="false">
        <jsp:param value="URL" name="index_title"/>
        <jsp:param value="5" name="cnt"/>
      </jsp:include>
    </div>
  </td>
 </tr>
 <tr>
 <td colspan="2">
 <div>
  <jsp:include page="../mpproduct/listTop.jsp" flush="false">
    <jsp:param value="신상품" name="index_title"/>
    <jsp:param value="7" name="cnt"/>
  </jsp:include>
  
 </div></td>
 </tr>
 </TABLE>
 <!-- ************************미니게시판 끝*********************** -->
 
 
<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="true"/>
</body>
<!-- *********************************************** -->

</html>
