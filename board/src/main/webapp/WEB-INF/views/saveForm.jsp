<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp"%>

<main>
	<h1>글쓰기 페이지</h1>
	<hr />
	제목 : <input id="title" type="text" name="title" /><br /> 내용 :
	<textarea id="summernote" name="content"></textarea>
	<br />
	<div id="imageBoard"></div>
	<button onclick="save()">글쓰기 완료</button>
</main>

<script>

//써머노트 적용
$('#summernote').summernote({
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

//써머노트에 이미지업로드
function imageUpload(file, editor) {
	data = new FormData();
	data.append("file", file);
	$.ajax({
		data : data,
		type : "POST",
		url : "/imageUpload",
		contentType : false,
		processData : false,
		success : function(data) {
			$(editor).summernote('insertImage', data.url);
		}
	});
}

//글 작성
function save(){
	let title = document.querySelector("#title").value;
	let content = document.querySelector("#summernote").value;

	let board = {
		title : title,
		content : content
	};

	fetch("/save", {
		method:"post",
		headers:{
			'Content-Type': 'application/json; charset=utf-8',
		},
		body : JSON.stringify(board)
	}).then(res => res.text())	//나중엔 text가아닌 dto를 json으로 받야아함.
	.then(res => {
		if(res == "ok"){
			alert("글 작성 완료!!ㅊㅋ");
			location.href="../list"
		}else{
			alert("글 작성 실패ㅜㅜ");
		}
	})
}
	
</script>

<%@include file="layout/footer.jsp"%>