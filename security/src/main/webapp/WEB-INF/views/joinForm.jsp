<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>joinForm!!</h1>
	<hr />
	<form action="/joinProc" method="post">
		<input type="text" name="username" placeholder="유저네임"/><br /> 
		<input type="password" name="password" placeholder="비번"/><br />
		<input type="email" name="email" placeholder="이메일" /><br />
		<button>회원가입</button>
	</form>
	
</body>
</html>