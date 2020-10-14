<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>!!!!!영화 목록!!!!</h1>
	<table border="1">
		<tr>
			<td>ID</td>
			<td>TITLE</td>
			<td>SCORE</td>
			<td>LEVEL</td>
			<td>INFO</td>
			<td>director</td>
			<td>actor</td>
		</tr>
		<c:forEach var="movie" items="${movies }">
		<tr>
			<td>${movie.id }</td>
			<td>${movie.title }</td>
			<td>${movie.score }</td>
			<td>${movie.level }</td>
			<td>${movie.info }</td>
			<td>${movie.director }</td>
			<td>${movie.actor }</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>