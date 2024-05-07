<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="repository.bookmark_repo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javacode.bookmark_dao"%>
<% 
	bookmark_repo repo = new bookmark_repo();
	ArrayList<bookmark_dao> list = repo.findMatchedBookmarkInfoAll();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크 보기</title>
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
    <h1> 북마크 보기</h1>
    
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
    
        <table border = "1">
    	    <thead>
	    	<tr align="center" bgcolor="white">
		    	<th>ID</th>
		    	<th>북마크 이름</th>
		    	<th>와이파이명</th>
		    	<th>등록일자</th>
		    	<th>비고</th>
	    	</tr>
	    </thead>
	    <tbody>
	    		<% for(bookmark_dao ele : list) {%>
	    	
	    	<tr>
	    		<td><%= ele.getId() %></td>
	    		<td><%= ele.getName() %></td>
	    		<td><%= ele.getWifi_name() %></td>	
	    		<td><%= ele.getReg_date() %></td>
	    		<td><a href = "matched_bookmark_delete.jsp?id=<%=ele.getId() %>">삭제</a></td>

	    	</tr>
	    	<% } %>
	    </tbody>
	   </table>
</body>
</html>