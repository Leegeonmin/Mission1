<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javacode.history_dao"%>
<%@page import="repository.history_repo"%>
<%@page	import="java.util.Collections"%>
<% 
	history_repo history_repository = new history_repo();
	ArrayList<history_dao> daoList = history_repository.findHistoryInfoAll();
	if(daoList != null){
		Collections.reverse(daoList);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>히스토리 페이지</title>
  <style>
    /* 테이블 헤더의 스타일 */
    th {
        color: white; /* 텍스트 색을 흰색으로 설정 */
        background-color: green; /* 배경색을 초록색으로 설정 */
        padding: 10px; /* 내부 여백 설정 */
    }
        table {
        width: 100%; /* 테이블 너비를 화면 전체로 설정 */
        border-collapse: collapse; /* 테이블 셀 경계를 합치기 */
    }
    th, td {
        padding: 8px; /* 셀 내부 여백 설정 */
        text-align: left; /* 셀 텍스트 정렬 왼쪽으로 설정 */
        border: 1px solid black; /* 테두리 설정 */
    }
</style>
</head>
<body>
	<h1> 위치 히스토리 목록</h1>
	
	<a href = "index.jsp"> 홈 </a>
    <span>&nbsp;|&nbsp;</span>
    <a href = "history.jsp"> 위치 히스토리 목록 </a>
    <span>&nbsp;|&nbsp;</span>
    <a href = "save_wifi_servlet"> Open API 와이파이 정보 가져오기 </a>
	<p></p>
    <table border = "1">
	    <thead>
	    	<tr align="center" bgcolor="white">
		    	<th>ID</th>
		    	<th>X좌표</th>
		    	<th>Y좌표</th>
		    	<th>조회일자</th>
		    	<th>비고</th>
	    	</tr>
	    </thead>
	    <tbody>
	    	<% for(history_dao ele : daoList){%>
	    	<tr >
	    		<td><%= ele.getId() %> </td>
	    		<td><%= ele.getLatX() %> </td>
	    		<td><%= ele.getLonY() %> </td>
	    		<td><%= ele.getReg_date() %> </td>
	    		<td>
	    			<form action="delete_history_servlet" medthod="get">
	    				<input type="hidden" name="id" value="<%= ele.getId() %>">	
	    				<button type = "submit">삭제
	    				</button>
	    			</form>

	    		</td>
	    	</tr>
	    	<% } %>
	    </tbody>
	</table>
</body>
</html>