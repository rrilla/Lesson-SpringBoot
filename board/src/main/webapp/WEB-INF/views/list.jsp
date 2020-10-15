<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp"%>

<main>
	<h1>!!!!!!게시글 목록!!!!</h1>
		
		<div class="container">
				<h2>Stretched Link in Card</h2>
				<p>Add the .stretched-link class to a link inside the card, and
					it will make the whole card clickable (the card will act as a
					link):</p>
					
		<c:forEach var="board" items="${boards.content }">
				<div class="card" style="width: 400px">
					<img class="card-img-top" src="img_avatar1.png" alt="Card image"
						style="width: 100%">
					<div class="card-body">
						<h4 class="card-title">${board.title }</h4>
						<p class="card-text">${board.content }</p>
						<a href="/detail/${board.id }" class="btn btn-primary stretched-link">See Profile</a>
					</div>
				</div>
		</c:forEach>
		
		</div>

	<ul class="pagination">

		<li
			class="${boards.first==true ? 'page-item disabled' : 'page-item' }">
			<a class="page-link" href="list?page=${boards.number - 1} ">Previous</a>
		</li>

			<li class="page-item active"><a class="page-link" href="#">${boards.number + 1 }</a></li>
			<li class="page-item"><a class="page-link" href="#">${boards.number + 2 }</a></li>
			<li class="page-item"><a class="page-link" href="#">${boards.number + 3 }</a></li>
			<li class="page-item"><a class="page-link" href="#">${boards.number + 4 }</a></li>
			<li class="page-item"><a class="page-link" href="#">${boards.number + 5 }</a></li>

		<li class="${boards.last==true ? 'page-item disabled' : 'page-item' }">
			<a class="page-link" href="list?page=${boards.number + 1}">Next</a>
		</li>

	</ul>

</main>

<%@include file="layout/footer.jsp"%>

