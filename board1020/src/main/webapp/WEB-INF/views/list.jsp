<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp"%>

<style>
.card-img-top{
	width: 500px;
	height: 500px;
}
</style>

<main>
	<h1>!!!!!!게시글 목록!!!!</h1>
		<div class="container">
			<div class="row">
				
			<c:forEach var="board" items="${test }">
			<div class="col-sm-3 col-md-6 col-lg-4 col-xl-2">
				<div class="card" style="width: 100%">
					<!-- <img class="card-img-top" src="img_avatar1.png" alt="Card image"
						style="width: 100%"> -->
					<img class="card-img-top" src="${board.image }" style="width: 100%">
					<div class="card-body">
						<h4 class="card-title">${board.title }</h4>
						<!-- <p class="card-text">${board.content }</p> -->
						<a href="/all/detail/${board.id }" class="btn btn-primary stretched-link">See Profile</a>
					</div>
				</div>
				</div>
			</c:forEach>
			
			</div>
		</div>

<c:if test="${boards != null}">
	<ul class="pagination">
		<li class="${boards.first==true ? 'page-item disabled' : 'page-item' }">
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
</c:if>

</main>

<%@include file="layout/footer.jsp"%>