<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<style type="text/css">
html {
  height: 100%
}

body {
  height: 100%;
  margin: 0px;
  padding: 0px
}

#map_canvas {
  height: 100%
}
</style>
<script type="text/javascript"
  src="http://maps.google.com/maps/api/js?sensor=false&language=ko">
  
</script>

  <script type="text/javascript">jQuery.noConflict();</script>

<script type="text/javascript">
  var map;
  var markersArray = [];
  var coordinates = [];
  var infowindow = new google.maps.InfoWindow();

  // IF 시청, http://www.innisfree.co.kr/Main.do
  var lat1 = "37.568541"; 
  var lng1 = "126.976766";
    
  // 아리따움 롯데면세점, http://www.aritaum.com/main/index
  var lat2 = "37.564786";
  var lng2 = "126.98006299999997";
    
  // 롯데강남 헤라, http://www.hera.co.kr/index.jsp
  var lat3 = "37.497435";
  var lng3 = "127.053359";
    
  // 설화수 을지로, http://www.sulwhasoo.co.kr/main.jsp
  var lat4 = "37.566062";
  var lng4 = "126.979376";

  // 롯데백화점 청량리, http://www.hanyul.co.kr/
  var lat5 = "37.581181";
  var lng5 = "127.047955";
    
  //초기 실행 함수
  function initialize() {

    // 좌표 객체 생성
    var coder1 = new google.maps.LatLng(lat1, lng1);
    var coder2 = new google.maps.LatLng(lat2, lng2);
    var coder3 = new google.maps.LatLng(lat3, lng3);
    var coder4 = new google.maps.LatLng(lat4, lng4);
    var coder5 = new google.maps.LatLng(lat5, lng5);
      
    // 지도 출력 옵션
    var mapOptions = {
      streetViewControl : false,
      mapTypeControl : true, // 지도 출력 종류 선택
      mapTypeId : google.maps.MapTypeId.ROADMAP // 일반 지도
    }
    // 지도를 출력할 DIV 객체 추출
    map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);

    // coordinates 배열에 좌표 객체 저장
    coordinates.push(coder1);
    coordinates.push(coder2);
    coordinates.push(coder3);
    coordinates.push(coder4);
    coordinates.push(coder5);
      
    // 마커를 지도에 묶기(출력)위해서 저장소에 마커의 수만큼 
    // coordinates 배열에 있는 좌표 객체를 저장 
    bounds = new google.maps.LatLngBounds();
    for (var i = 0; i < 5; i++) {
      bounds.extend(coordinates[i]);
    }
    map.fitBounds(bounds); // 마커가 전부 출력되도록 중심점과 사이즈 자동 조절

    addMarker(coder1);
    addMarker(coder2);
    addMarker(coder3);
    addMarker(coder4);
    addMarker(coder5);
  }

  //지도에 마커출력
  function addMarker(latlng) {
    marker = new google.maps.Marker({
      position : latlng,
      map : map
    });
    markersArray.push(marker);

    //지도에 출력된 마커를 클릭했을 경우 이벤트(infoWindow 출력)
    google.maps.event.addListener(marker, 'click', function(event) {
      popInfoWindow(latlng);
    });
  }

  // infoWindow 출력
  /*
  results[0].formatted_address: "275-291 Bedford Ave, Brooklyn, NY 11211, USA",
  results[1].formatted_address: "Williamsburg, NY, USA",
  results[2].formatted_address: "New York 11211, USA",
  results[3].formatted_address: "Kings, New York, USA",
  results[4].formatted_address: "Brooklyn, New York, USA",
  results[5].formatted_address: "New York, New York, USA",
  results[6].formatted_address: "New York, USA",
  results[7].formatted_address: "United States" 
   */
  function popInfoWindow(latlng) {
    var geocoder = new google.maps.Geocoder(); // 주소와 좌표 변환
    map.setCenter(latlng);
    addMarker(latlng); //마커출력
    geocoder.geocode({
      'latLng' : latlng // 좌표 지정
    }, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) { // 구굴 맵 지원 여부
        if (results[0]) {
          var lat = latlng.lat();
          var lng = latlng.lng();
          var cont='';
          
          // alert(latlng.lat() + ", " + latlng.lng());

          if (lat == lat1 && lng == lng1){
            cont = '<div id="content">';
            cont += '<br><div id=="adress">';
            cont += '<b>IF 시청</b><br>';
            cont += "<a href='http://www.innisfree.co.kr/Main.do'>innisfree.co.kr/Main.d</a>";
            cont += '<br></div>';
            cont += "<img src='./images/innisfree01.jpg'>";
            cont += '<p><b>주소 :</b> ' + results[0].formatted_address + '</p>';
            cont += '</div>';
          }else if (lat == lat2 && lng == lng2){
            cont = '<div id="content">';
            cont += '<br><div id=="adress">';
            cont += '<b>아리따움 롯데면세점</b><br>';
            cont += "<a href='http://www.aritaum.com/main/index'>aritaum.com/main/index</a>";
            cont += '<br></div>';
            cont += "<img src='./images/aridaum02.jpg'>";
            cont += '<p><b>주소 :</b> ' + results[0].formatted_address + '</p>';
            cont += '</div>';
          }else if (lat == lat3 && lng == lng3){
            cont = '<div id="content">';
            cont += '<br><div id=="adress">';
            cont += '<b>롯데강남 헤라</b><br>';
            cont += "<a href='http://www.hera.co.kr/index.jsp'>hera.co.kr/index.jsp</a>";
            cont += '<br></div>';
            cont += "<img src='./images/hera01.jpg'>";
            cont += '<p><b>주소 :</b> ' + results[0].formatted_address + '</p>';
            cont += '</div>';
          }else if (lat == lat4 && lng == lng4){
            cont = '<div id="content">';
            cont += '<br><div id=="adress">';
            cont += '<b>설화수 을지로</b><br>';
            cont += "<a href='http://www.sulwhasoo.co.kr/main.jsp'>www.sulwhasoo.co.kr</a>";
            cont += '<br></div>';
            cont += "<img src='./images/sulwhasoo01.jpg'>";
            cont += '<p><b>주소 :</b> ' + results[0].formatted_address + '</p>';
            cont += '</div>';
          }else if (lat == lat5 && lng == lng5){
            cont = '<div id="content">';
            cont += '<br><div id=="adress">';
            cont += '<b>롯데백화점 청량리</b><br>';
            cont += "<a href='http://www.hanyul.co.kr/'>hanyul.co.kr/</a>";
            cont += '<br></div>';
            cont += "<img src='./images/hanyul01.jpg'>";
            cont += '<p><b>주소 :</b> ' + results[0].formatted_address + '</p>';
            cont += '</div>';
          }   
              
          infowindow.setContent(cont);
          infowindow.open(map, marker);
        } else {
          alert("No results found");
        }
      } else {
        alert("Geocoder failed due to: " + status);
      }
    });
  }//택배, 배달, 부동산  ==> jsp로 꾸며야 함.. 오라클 컬럼값, 이미지 파일명, 다 오라클에 있어야함.. 그래서 display됨
   // 주소<-> 좌표 (jeocoding/역지오코딩)서로 서로 알아낼수 있음
  </script>
</head>

<!-- **********************메뉴바************************* -->
<body onload="initialize()">
<jsp:include page="../menubar/top.jsp" flush="true"/>
<!-- ***********************메뉴바 끝************************ -->

  <table width="500" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
      <td width="100%" height="500">
        <div id="map_canvas" style="width: 100%; height: 100%"></div>
      </td>
    </tr>
  </table>
<!-- *********************************************** -->
<jsp:include page="../menubar/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->
</html>