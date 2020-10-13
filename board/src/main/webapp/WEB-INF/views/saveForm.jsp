<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp" %>

<main>
	<h1>글쓰기 페이지</h1>
	<hr />
	<form action="/save" method="post">
		제목 : <input type="text" name="title" /><br />
		내용 : <input type="text" name="content" /><br />
		<button>글쓰기 완료</button>
	</form>
</main>

<%@include file="layout/footer.jsp" %>