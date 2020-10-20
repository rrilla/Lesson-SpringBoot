<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp" %>

<main>
	<h1>상세보기</h1>
	<table border="1">
		<tr>
			<td>ID</td>
			<td>TITLE</td>
			<td>READCOUNT</td>
			<td>CREATEDATE</td>
		</tr>
		<tr>
			<td>${board.id }</td>
			<td><input id="title" type="text" value="${board.title }"/></td>
			<td>${board.readCount }</td>
			<td>${board.createDate }</td>
		</tr>
	</table>
	<textarea id="content" >${board.content }</textarea>
	<button onclick="deleteBoard(${board.id})" type="button" class="btn btn-danger">삭제</button>
	 <button onclick="updateBoard(${board.id})" type="button" class="btn btn-secondary">수정</button>
</main>

<script>

	//써머노트 적용
	$('#content').summernote({
		height: 300,                 // 에디터 높이
		minHeight: null,             // 최소 높이
		maxHeight: null,             // 최대 높이
		focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		lang: "ko-KR",					// 한글 설정
		placeholder: '최대 2048자까지 쓸 수 있습니다',	//placeholder 설정
		callbacks: {	//여기 부분이 이미지를 첨부하는 부분
			onImageUpload : function(files) {
				if(files.length > 1){
					alert("파일 하나씩만 업로드가능 ㅜㅜ");
					return false;
				}
				imageUpload(files[0], this);
			}
		}
	});

	function deleteBoard(id){
		fetch("/user/board/"+id, {
			method:"delete"
				}).then(res => res.text())
		.then(res => {
				if(res == "ok"){
					alert("삭제 성공.");
					location.href = "/all/list";
				}else{
						alert("삭제 실패.")
					}
			});
	}

	function updateBoard(id) {
		let title_el = document.querySelector("#title");
		let content_el = document.querySelector("#content");

		let title = document.querySelector("#title").value;
		let content = document.querySelector("#content").value;

		let board = {
					title:title,
					content:content
				};
		
		fetch("/user/board/"+id,{
			method:"put",
			headers:{
				'Content-Type': 'application/json; charset=utf-8',
			},
			body : JSON.stringify(board)
		}).then(res => res.text())	//나중엔 text가아닌 dto를 json으로 받야아함.
		.then(res => {
			if(res == "ok"){
				alert("수정완료!!ㅊㅋ");
				location.reload();	
			}else{
				alert("수정실패ㅜㅜ");
			}
		})
		
	}
		
</script>

<%@include file="layout/footer.jsp" %>