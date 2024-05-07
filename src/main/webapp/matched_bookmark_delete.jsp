<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="repository.bookmark_repo"%>
<%@page import="javacode.bookmark_dao"%>
<%
	int idParam = Integer.parseInt(request.getParameter("id"));
	bookmark_repo repo = new bookmark_repo();
	bookmark_dao dao = repo.findById(idParam);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크 삭제</title>
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
    <h1> 북마크 삭제</h1>
    
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
    <form action="delete_bookmark_servlet" method="get">
        <table border = "1">
			<tr>
				<td> 북마크 이름</td>
				<td><%= dao.getName() %></td>
			</tr>
			<tr>
				<td> 와이파이명 </td>
				<td><%= dao.getWifi_name() %></td>
			</tr>
			<tr>
				<td> 등록일자</td>
				<td><%= dao.getReg_date() %></td>
			</tr>
				    	<tr align="center" bgcolor="white">
		    	<td style = "text-align: center;" colspan="2">
		    	<a href = "matched_bookmark.jsp"> 돌아가기 </a>
		    	<span> &nbsp;|&nbsp;</span>
		    	<button type = "submit"> 삭제 </button>
		    	<input type="hidden" name="id" value="<%= dao.getId() %>">
		    	</td>
	    	</tr>
    	</table>
    </form>	
</body>
</html>