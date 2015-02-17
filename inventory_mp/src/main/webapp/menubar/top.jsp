<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ page import="www.utility.Utility" %>

<%
// 관리자 세션 변수
String s_admin_id = Utility.checkNull(session.getAttribute("s_id2"));
// 사용자 세션 변수
String s_member_id = Utility.checkNull(session.getAttribute("s_id"));

//사용자 타입 세션 변수
String s_member_mtype = Utility.checkNull(session.getAttribute("s_mtype"));

//out.println("mtype::"+ s_member_mtype );

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>insert title</title>
  <link type="text/css" href="<%=Utility.getRoot() %>/menubar/menu.css" rel="stylesheet" />
  <div style="display:none"><a href="http://apycom.com/"></a></div>
  <script type="text/javascript" src="<%=Utility.getRoot() %>/menubar/jquery.js"></script>
  <script type="text/javascript" src="<%=Utility.getRoot() %>/menubar/menu.js"></script>
  <script type="text/javascript">jQuery.noConflict();</script>
</head>
<!-- 상단 메뉴 -->
<TABLE width=1000px align="center" cellspacing="0" cellpadding="0" border="0" 
           style= 'border-collapse: collapse; margin-bottom: 5px; '>
           <tr><td>
<div id="logo"><img src="<%=Utility.getRoot() %>/menubar/images/logo.png" ></div>
  <div id="menu">
    <ul class="menu">
      <li><a href='<%=Utility.getRoot() %>/mpgroup/index.do' class="parent"><span>Home</span></a></li>

      <%
      if (s_member_id.equals("") == true){%>
        <li><a href='<%=Utility.getRoot() %>/makeup_project/loginFormCookie.jsp'><span>로그인</span></a></li>
      <%
      }else{%>
      <%
      if (s_member_mtype.equals("직원") == true){%> 
        <li><a href="#" class="parent"><span>회원 관리</span></a>
        <div><ul>
          <li><a href='<%=Utility.getRoot() %>/makeup_project/logout.jsp'><span>로그아웃</span></a></li>
          <%-- <li><a href='<%=Utility.getRoot() %>/makeup_project/updateForm.jsp'><span>직원 정보 수정</span></a></li> --%>
          <li><a href='<%=Utility.getRoot() %>/makeup_project/list_a.jsp'><span>회원 목록</span></a></li>          
        </ul></div>  
      <%      
      }else{%> 
        <li><a href="#" class="parent"><span>회원 관리</span></a>
        <div><ul>
          <li><a href='<%=Utility.getRoot() %>/makeup_project/logout.jsp'><span>로그아웃</span></a></li>
          <%-- <li><a href='<%=Utility.getRoot() %>/makeup_project/updateForm.jsp'><span>회원 정보 수정</span></a></li> --%>
        </ul></div> 
       <%      
      }%>     
      
        <li><a href="#"><span>품목 검색</span></a>
          <div><ul>
            <li><a href='<%=Utility.getRoot() %>/mpproduct/list.do?mpgroupno=1'><span>기초 라인</span></a></li>
            <li><a href='<%=Utility.getRoot() %>/mpproduct/list.do?mpgroupno=2'><span>색조 라인</span></a></li>
          </ul></div>
        </li> 
      
        <li><a href='<%=Utility.getRoot() %>/mpnotice/list_b.jsp'><span>공지사항</span></a></li>
        <li><a href="#"><span>Help</span></a>
          <div><ul>
            <li><a href='<%=Utility.getRoot() %>/clientbbs/list.jsp'><span>고객 게시판</span></a></li>
            <li><a href='<%=Utility.getRoot() %>/clientdata/list.jsp'><span>고객 지원 자료실</span></a></li>
          </ul></div>
        </li>       
        <li><a href='<%=Utility.getRoot() %>/maps/geo.jsp'><span>Contacts</span></a></li>      
      <%      
      }%> 

 
      <%
      if (s_admin_id.equals("") == true){%>
        <li><a href='<%=Utility.getRoot() %>/makeup_admin/loginFormCookie2.jsp'><span>관리자</span></a></li>
      <%
      }else if(s_admin_id.equals("admin") == true){%> 
        <li><a href="#" class="parent"><span>관리자 관리</span></a>
        <div><ul>
          <li><a href='<%=Utility.getRoot() %>/makeup_admin/logout.jsp'><span>관리자 로그아웃</span></a></li>
          <li><a href='<%=Utility.getRoot() %>/makeup_admin/list.jsp'><span>관리자 목록</span></a></li>
          <%-- <li><a href='<%=Utility.getRoot() %>/makeup_admin/createForm.jsp'><span>관리자 가입</span></a></li> --%>
        </ul></div>
        </li>        
        
        <li><a href="#" class="parent"><span>회원 관리</span></a>
        <div><ul>
          <li><a href='<%=Utility.getRoot() %>/makeup_project/list.jsp'><span>회원 목록</span></a></li>
          <%-- <li><a href='<%=Utility.getRoot() %>/makeup_project/createForm.jsp'><span>회원 가입</span></a></li> --%>
        </ul></div>        

        <li><a href='<%=Utility.getRoot() %>/mpgroup/list.do'><span>품목 목록</span></a></li>

        <li><a href="#"><span>품목 검색</span></a>
          <div><ul>
            <li><a href='<%=Utility.getRoot() %>/mpproduct/list.do?mpgroupno=1'><span>기초 라인</span></a></li>
            <li><a href='<%=Utility.getRoot() %>/mpproduct/list.do?mpgroupno=2'><span>색조 라인</span></a></li>
          </ul></div>
        </li> 
      
        <li><a href='<%=Utility.getRoot() %>/mpnotice/list_b.jsp'><span>공지사항</span></a></li>
        <li><a href="#"><span>Help</span></a>
          <div><ul>
            <li><a href='<%=Utility.getRoot() %>/clientbbs/list.jsp'><span>고객 게시판</span></a></li>
            <li><a href='<%=Utility.getRoot() %>/clientdata/list.jsp'><span>고객 지원 자료실</span></a></li>
          </ul></div>
        </li>       
        <li><a href='<%=Utility.getRoot() %>/maps/geo.jsp'><span>Contacts</span></a></li>  

        <li><a href='<%=Utility.getRoot() %>/calendar/list.jsp'><span>일정관리</span></a></li> 
        <li><a href='<%=Utility.getRoot() %>/memov3/list.jsp'><span>URL관리</span></a></li>        
      <%
      } else{}%>  
    </ul>
  </div>
  </td></tr></TABLE>
<!-- 상단 메뉴 끝 -->

<!-- 내용 시작 -->
<div style="width: 100%; padding-top: 10px;">
