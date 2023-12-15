<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/jQuery.js"></script>
<script type="text/javascript" src="jQuery.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d4cf295e1ef740aa56814756b19ae57d"></script>
<script type="text/javascript">
var map;
var marker;
var roadviewClient = new kakao.maps.RoadviewClient();
var roadview;

//지도 로드뷰 마커 옮기기
function move(lat, lng){
	//지도 옮기기 
	var moveLatLng = new kakao.maps.LatLng(lat, lng);   
	map.panTo(moveLatLng);
	
	//마커 옮기기
	marker.setPosition(moveLatLng); 
	
    //로드뷰 옮기기
    roadviewClient.getNearestPanoId(moveLatLng, 50, function(panoId) {
            roadview.setPanoId(panoId);
       });//로드뷰 클라이언트 
}

$(function(){
	//현재 위치받기 
	//받은 위치가 g 
	navigator.geolocation.getCurrentPosition(function(g){
		//위도 경도 좌표 체계 
		var lat = g.coords.latitude; //위도
		var lng = g.coords.longitude; //경도
		
	//현재위치
	var curLoc = new kakao.maps.LatLng(lat, lng);
	
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: curLoc, //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};

	map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	
	//로드뷰 
	var roadviewContainer = document.getElementById('roadview'); //로드뷰를 표시할 div
	roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
	


    roadviewClient.getNearestPanoId(curLoc, 50, function(panoId) {
            roadview.setPanoId(panoId);
       });//로드뷰 클라이언트 
       
    //마커 
	marker = new kakao.maps.Marker({
	    map: map,
	    position: curLoc
	}); //마커
	
	}); //네비게이터 
	
	//지역명 쳤을 때
	$("#locSearch").keyup(function(e){
		//if (e.keyCode == 13) {
			var locSearch = $("#locSearch").val();
			  $.ajax({
				  url : "https://dapi.kakao.com/v2/local/search/address.json",
				  
				  beforeSend : function(req) { 
		          req.setRequestHeader("Authorization", "KakaoAK 8409f378657a97bed0bc15baec3c4f12");
			      },
			      
				  data : { query : locSearch }, 
            
				  success : function(k){ 
				 //받아오는 거 성공하면 불러지는 함수 
				 // 입력한 거 똑바로 뜨는지 확인 alert(JSON.stringify(z));
					 move(k.documents[0].y,  k.documents[0].x);
		     }// 석세스 
		  });//에이젝스
	//	} //이프문 
	});//키보드 
	
	
	//검색어 쳤을 때 
	$("#search").keyup(function(e){
		//if (e.keyCode == 13) {
			var search = $("#search").val();
			//지도 중심의 경도와 위도
			// map.getCenter().getLat();
			// map.getCenter().getLng();
			  $.ajax({
				  url : "https://dapi.kakao.com/v2/local/search/keyword.json",
				  
				  beforeSend : function(req) { 
		          req.setRequestHeader("Authorization", "KakaoAK 8409f378657a97bed0bc15baec3c4f12");
			      },
			      
				  data : { query : search, 
					  x : map.getCenter().getLng(), 
					  y : map.getCenter().getLat(), 
					  radius: 5000}, 
            
				  success : function(z){ 
				 //받아오는 거 성공하면 불러지는 함수 
				 // 입력한 거 똑바로 뜨는지 확인 alert(JSON.stringify(z));
				 $("#result").empty();
				 
 				$.each(z.documents, function(i, a){
 					 
 			    var place = $("<td></td>").text(a.place_name);
 			    var addr =  $("<td></td>").text(a.address_name);
 			    var phone = $("<td></td>").text(a.phone);
 			    
 			  var tr1 = $("<tr></tr>").attr("onclick", "move("+a.y+", "+a.x+");").attr("class", "mapTr1").append(place);
 			  var tr2 = $("<tr></tr>").attr("onclick", "move("+a.y+", "+a.x+");").attr("class", "mapTr2").append(addr);
 			  var tr3 = $("<tr></tr>").attr("onclick", "move("+a.y+", "+a.x+");").attr("class", "mapTr2").append(phone);
 			    
 			  var table = $("<table></table>").attr("border", "1").attr("id", "tt").append(tr1, tr2, tr3);
 			  
 			  $("#result").append(table);
	         }); //반복문 
		     }// 석세스 
		  });//에이젝스
		//} //이프문 
	});//키보드 
}); //메인
</script>
</head>
<body>
<table border="1" id="mapTable">
<tr>
<td>지역</td>
<td><input id="locSearch" placeholder="지역"></td>
</tr>
<tr>
<td>검색</td>
<td><input id="search" placeholder="검색"></td>
</tr>
</table>
<div id="map" style="width:500px; height:400px;" ></div>
<div id="roadview" style="width:500px;height:400px;"></div>
<div id="result"></div>
</body>
</html>