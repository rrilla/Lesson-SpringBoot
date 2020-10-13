<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>글쓰기 페이지</h1>
<hr />
<form action="/save2" method="post">
	<input type="text" name="title" />
	<input type="text" name="content" />
	<button>글쓰기 완료</button>
</form>
</body>
</html>