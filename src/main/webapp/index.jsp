<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="javacode.openApiConn" %>
<html>
  <head>
  <style>
    /* 테이블 헤더의 스타일 */
    th {
        color: white; /* 텍스트 색을 흰색으로 설정 */
        background-color: green; /* 배경색을 초록색으로 설정 */
        padding: 10px; /* 내부 여백 설정 */
    }
</style>
    <meta charset="UTF-8" />
    <title>와이파이 정보 구하기</title>
  </head>
  <body>
  <% 
  	openApiConn conn = new openApiConn();

  %>
    <h1> 와이파이 정보 구하기</h1>
    
    <a href = "#"> 홈 </a>
    <span>&nbsp;|&nbsp;</span>
    <a href = "#"> 위치 히스토리 목록 </a>
    <span>&nbsp;|&nbsp;</span>
    <a href = "saveApiServlet"> Open API 와이파이 정보 가져오기 </a>
	
    <p></p>
    

    <form action="#" method="get">
    LAT : <input type="text" name="latX" id="latX" style="display: inline-block;">
    LON : <input type="text" name="latY" id="latY" style="display: inline-block;">

    <button type="button"  onclick="getLocation()" >내 위치 가져오기</button>
    <button type="submit">근처 WIPI 정보보기</button>
    </form>	
    
    
    <table border = "1">
	    <thead>
	    	<tr align="center" bgcolor="white">
		    	<th>거리<br>(Km)</th>
		    	<th>관리번호</th>
		    	<th>자치구</th>
		    	<th>관리번호</th>
		    	<th>와이파이명</th>
		    	<th>도로명주소</th>
		    	<th>상세주소</th>
		    	<th>설치위치<br>(층)</th>
		    	<th>설치유형</th>
		    	<th>설치기관</th>
		    	<th>서비스구분</th>
		    	<th>망종류</th>
		    	<th>설치년도</th>
		    	<th>실내외구분</th>
		    	<th>WIFI접속환경</th>
		    	<th>X좌표</th>
		    	<th>Y좌표</th>
		    	<th>작업일자</th>
	    	</tr>
	    </thead>
    </table>
	<script>
	function getLocation() {
	    if (navigator.geolocation) {
	        navigator.geolocation.getCurrentPosition(showPosition);
	    } else {
	        alert("Geolocation is not supported by this browser.");
	    }
	}
	
	function showPosition(position) {
	    document.getElementById("latX").value = position.coords.latitude;
	    document.getElementById("latY").value = position.coords.longitude;
	}
	</script>

    <%
    	//out.println("ehelo ");
    %>
  </body>
</html>
