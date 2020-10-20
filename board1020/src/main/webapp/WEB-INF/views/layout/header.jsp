<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- include libraries(jQuery, bootstrap) -->
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<!-- include summernote css/js-->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>

<style>
/* Make the image fully responsive */
.carousel-inner img {
	width: 100%;
	height: 80%;
}
</style>

</head>

<script>
	$(function() {
		$.ajax({
			type:"get",
			url:"/all/session",
			success:function(data,textStatus){
				if(data == "1"){
					$('#test').attr("href","/logout");
					$('#test').text("로그아웃");
				}
			},error:function(data,textStatus){
				console.log("세션유무 확인 서버 에러.")
			}
		});
	});
</script>

<body>

	<header>

		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<a class="navbar-brand" href="/all/list">홈</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/all/list">홈</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/saveForm">글쓰기</a></li>
					<li class="nav-item"><a id="test" class="nav-link" href="/all/loginForm">로그인</a></li>
					
					<!--<c:choose>	
						<c:when test="${user == null}">
							<li class="nav-item"><a id="test" class="nav-link" href="/all/loginForm">로그인</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a id="test" class="nav-link" href="/logout">로그아웃</a></li>
						</c:otherwise>
					</c:choose>-->
					
				</ul>
			</div>
		</nav>
		<form action="/all/search">
			<select name="type">
			  <option value="'title'" selected="selected">제목</option>
			  <option value="content">내용</option>
			  <option value="" >작성자</option>
			</select><br />
			검색<input type="text" name="search" placeholder="아직 제목 검색만 가능" />
			<button>ㄱㄱ</button>
		</form>

		<!-- <div id="demo" class="carousel slide" data-ride="carousel">
			<ul class="carousel-indicators">
				<li data-target="#demo" data-slide-to="0" class="active"></li>
				<li data-target="#demo" data-slide-to="1"></li>
				<li data-target="#demo" data-slide-to="2"></li>
			</ul>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="/resources/image/back1.jpg" alt="Los Angeles" width="1100" height="500">
					<div class="carousel-caption">
						<h3>Los Angeles</h3>
						<p>We had such a great time in LA!</p>
					</div>
				</div>
				<div class="carousel-item">
					<img src="/resources/image/back2.jpg" alt="Chicago" width="1100" height="500">
					<div class="carousel-caption">
						<h3>Chicago</h3>
						<p>Thank you, Chicago!</p>
					</div>
				</div>
				<div class="carousel-item">
					<img src="/resources/image/back3.jpg" alt="New York" width="1100" height="500">
					<div class="carousel-caption">
						<h3>New York</h3>
						<p>We love the Big Apple!</p>
					</div>
				</div>
			</div>
			<a class="carousel-control-prev" href="#demo" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a> <a class="carousel-control-next" href="#demo" data-slide="next">
				<span class="carousel-control-next-icon"></span>
			</a>
		</div> -->

	</header>