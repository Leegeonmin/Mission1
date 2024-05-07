<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javacode.DistanceWifi"%>
<%
ArrayList<DistanceWifi> wifiList = request.getAttribute("wifiList") != null ? (ArrayList<DistanceWifi>)request.getAttribute("wifiList"): new ArrayList<DistanceWifi>();

%>
<%
java.text.DecimalFormat df = new java.text.DecimalFormat("#.####");
%>
<!DOCTYPE html>
<html>
  <head>
  <style>
    /* 테이블 헤더의 스타일 */
    th {
        color: white; /* 텍스트 색을 흰색으로 설정 */
        background-color: green; /* 배경색을 초록색으로 설정 */
        padding: 10px; /* 내부 여백 설정 */
    }
    /* 테이블 크기 조정 */
    table {
        width: 100%; /* 테이블 너비를 화면 전체로 설정 */
        border-collapse: collapse; /* 테이블 셀 경계를 합치기 */
    }
    th, td {
        padding: 8px; /* 셀 내부 여백 설정 */
        text-align: left; /* 셀 텍스트 정렬 왼쪽으로 설정 */
        border: 1px solid black; /* 테두리 설정 */
    }
    tr:nth-child(odd) {
  		background : lightgrey;
	}
</style>
    <meta charset="UTF-8" />
    <title>와이파이 정보 구하기</title>
  </head>
  <body>

    <h1> 와이파이 정보 구하기</h1>
    
    <a href = "index.jsp"> 홈 </a>
    <span>&nbsp;|&nbsp;</span>
    <a href = "history.jsp"> 위치 히스토리 목록 </a>
    <span>&nbsp;|&nbsp;</span>
    <a href = "save_wifi_servlet"> Open API 와이파이 정보 가져오기 </a>
	<span>&nbsp;|&nbsp;</span>
    <a href = "bookmark.jsp"> 북마크 보기 </a>
    <span>&nbsp;|&nbsp;</span>
    <a href = "bookmark_group.jsp"> 북마크 그룹 관리 </a>
    <p></p>
    

    <form action="get_wifi_sevlet" method="get">
    LAT : <input type="text" placeholder="0" name="latX" id="latX" style="display: inline-block;">
    LON : <input type="text" placeholder="0" name="lonY" id="lonY" style="display: inline-block;">

    <button type="button"  onclick="getLocation()" >내 위치 가져오기</button>
    <button type="submit">근처 WIPI 정보보기</button>
    </form>	
    
    <p></p>
    
    <table border = "1">
	    <thead>
	    	<tr align="center" bgcolor="white">
		    	<th>거리<br>(Km)</th>
		    	<th>관리번호</th>
		    	<th>자치구</th>
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
	    		<tbody>
	    	<%if(wifiList.size() == 0) {%>

			    <tr>
			        <td colspan="17" style="text-align: center; font-weight: bold;">위치 정보를 입력한 후에 조회해 주세요</td>
			    </tr>

	    	<% }%>
			<%for(DistanceWifi ele:wifiList) {
				 String formattedDistance = df.format(ele.getDistance());
			%>
				<tr>
					<td><%= formattedDistance%></td>
					<td><%= ele.getWifi().getWifi_id() %></td>		
					<td><%= ele.getWifi().getDistrict() %></td>		
					<td>
						<a href="wifi_detail.jsp?id=<%= ele.getWifi().getId() %>&latX=<%= request.getParameter("latX") %>&lonY=<%= request.getParameter("lonY") %>">다음 페이지로 이동</a>
					</td>
					<td><%= ele.getWifi().getStreet_address() %></td>
					<td><%= ele.getWifi().getDetail_address() %></td>
					<td><%= ele.getWifi().getFloor() %></td>
					<td><%= ele.getWifi().getType() %></td>
					<td><%= ele.getWifi().getAgency() %></td>
					<td><%= ele.getWifi().getService_type() %></td>
					<td><%= ele.getWifi().getNetwork_type() %></td>
					<td><%= ele.getWifi().getInstallation_year() %></td>
					<td><%= ele.getWifi().getIs_indoor() %></td>
					<td><%= ele.getWifi().getNetwork_setting() %></td>
					<td><%= ele.getWifi().getLatX() %></td>
					<td><%= ele.getWifi().getLonY() %></td>
					<td><%= ele.getWifi().getReg_date() %></td>
				</tr>
	      	<%}%>
		</tbody>
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
	    document.getElementById("lonY").value = position.coords.longitude;
	}
	</script>

  </body>
</html>
