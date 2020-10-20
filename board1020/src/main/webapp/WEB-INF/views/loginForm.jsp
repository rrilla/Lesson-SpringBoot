<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@include file="layout/header.jsp"%>

<main>
	<h1>ㅎㅇㅎㅇ로그인폼임!</h1>
	<hr />
	<form action="/all/loginProc" method="post">
		<input type="text" name="username" placeholder="아디"/><br />
		<input type="password" name="password" placeholder="비번"/><br />
		<button>로그인ㄱㄱ</button>
	</form>
</main>

<%@include file="layout/footer.jsp"%>