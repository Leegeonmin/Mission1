<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String size = request.getAttribute("count").toString();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<h1><%= size %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
	<a href ="index.jsp">홈 으로 가기</a>
</body>
</html>