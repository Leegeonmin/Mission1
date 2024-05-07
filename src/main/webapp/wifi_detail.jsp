<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="javacode.wifi_dao"%>
<%@page import="repository.wifi_repo"%>
<%@page import="javacode.wifi_with_distance"%>
<%@page import="repository.bookmark_repo" %>
<%@page import="javacode.bookmark_dao" %>
<%@page import="java.util.ArrayList"%>
<%@page import="javacode.Util"%>
<%
int idParam = Integer.parseInt(request.getParameter("id"));
	double latX = Double.parseDouble(request.getParameter("latX"));
	double lonY = Double.parseDouble(request.getParameter("lonY"));
	wifi_repo wifi_repo = new wifi_repo();
	wifi_dao wifi = wifi_repo.findById(idParam);
	double distance = Util.calculateDistance(latX, lonY, wifi.getLatX(), wifi.getLonY());
	wifi_with_distance wifi_info = new wifi_with_distance(wifi, distance);
	
	bookmark_repo bookmark_repo = new bookmark_repo();
	ArrayList<bookmark_dao> selectBoxList = bookmark_repo.findBookmarkInfoAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 상세 페이지</title>
  <style>
    /* 테이블 헤더의 스타일 */
    th {
        color: white; /* 텍스트 색을 흰색으로 설정 */
        background-color: green; /* 배경색을 초록색으로 설정 */
        padding: 0px; /* 내부 여백 설정 */
    }
    /* 테이블 크기 조정 */
    table {
        border-collapse: collapse; /* 테이블 셀 경계를 합치기 */
        width :50%;
    }
    th, td {
        padding: 8px; /* 셀 내부 여백 설정 */
        text-align: left; /* 셀 텍스트 정렬 왼쪽으로 설정 */
        border: 1px solid black; /* 테두리 설정 */
    }
    tr:nth-child(odd) {
  		background : lightgrey;
	}
	tr td{

		text-align: center;
	}
</style>
</head>
<body>
    <h1> 와이파이 정보 구하기</h1>
    
    <a href = "index.jsp"> 홈 </a>
    <span>&nbsp;|&nbsp;</span>
    <a href = "history.jsp"> 위치 히스토리 목록 </a>
    <span>&nbsp;|&nbsp;</span>
    <a href = "save_wifi_servlet"> Open API 와이파이 정보 가져오기 </a>
	<span>&nbsp;|&nbsp;</span>
    <a href = "matched_bookmark.jsp"> 북마크 보기 </a>
    <span>&nbsp;|&nbsp;</span>
    <a href = "bookmark.jsp"> 북마크 그룹 관리 </a>
    <p></p>
    
	    
	<form action="insert_matched_bookmark_servlet" method="get">
	    <select id="bookmark_id" name="bookmark_id">
	        <option value="">북마크 그룹 이름 선택</option>
	        <% for (bookmark_dao bookmark : selectBoxList) { %>
	            <option value="<%= bookmark.getId() %>"><%= bookmark.getName() %></option>
	        <% } %>
	    </select> 
	    <input type="hidden" name="wifi_id" value="<%= idParam %>">
	    <input type="hidden" name="wifi_name" value="<%= wifi_info.getWifi().getName() %>">
	    <button type="submit">북마크 추가하기</button>
	</form>

    
    <table border = "1">
    	<tr>
    		<td>거리(Km)</td>
    		<td><%= wifi_info.getDistance() %></td>
    	</tr>
    	<tr>
    		<td>관리번호</td>
    		<td><%= wifi_info.getWifi().getWifi_id() %></td>
    	</tr>
    	<tr>
    		<td>자치구</td>
    		<td><%= wifi_info.getWifi().getDistrict()%></td>
    	</tr>
    	<tr>
    		<td>와이파이명</td>
    		<td><%= wifi_info.getWifi().getName() %></td>
    	</tr>
    	<tr>
    		<td>도로명주소</td>
    		<td><%= wifi_info.getWifi().getStreet_address() %></td>
    	</tr>
    	<tr>
    		<td>상세주소</td>
    		<td><%= wifi_info.getWifi().getDetail_address()%></td>
    	</tr>
    	<tr>
    		<td>설치위치(층)</td>
    		<td><%=wifi_info.getWifi().getFloor() %></td>
    	</tr>
    	<tr>
    		<td>설치유형</td>
    		<td><%=wifi_info.getWifi().getType() %></td>
    	</tr>
    	<tr>
    		<td>설치기관</td>
    		<td><%= wifi_info.getWifi().getAgency()%></td>
    	</tr>
    	<tr>
    		<td>서비스구분</td>
    		<td><%= wifi_info.getWifi().getService_type()%></td>
    	</tr>
    	<tr>
    		<td>망종류</td>
    		<td><%= wifi_info.getWifi().getNetwork_type()%></td>
    	</tr>
    	<tr>
    		<td>설치년도</td>
    		<td><%= wifi_info.getWifi().getInstallation_year()%></td>
    	</tr>
    	<tr>
    		<td>실내외구분</td>
    		<td><%= wifi_info.getWifi().getIs_indoor()%></td>
    	</tr>
    	<tr>
    		<td>WIFI접속환경</td>
      		<td><%= wifi_info.getWifi().getNetwork_setting()%></td>
    	</tr>
    	<tr>
    		<td>X좌표</td>
    		<td><%=  wifi_info.getWifi().getLatX()%></td>
    	</tr>
    	<tr>
    		<td>Y좌표</td>
    		<td><%= wifi_info.getWifi().getLonY()%></td>
    	</tr>
    	<tr>
    		<td>작업일자</td>
     		<td><%= wifi_info.getWifi().getReg_date() %></td>
    	</tr>
    </table>
</body>
</html>