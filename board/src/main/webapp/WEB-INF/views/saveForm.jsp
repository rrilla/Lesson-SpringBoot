<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp"%>

<main>
	<h1>글쓰기 페이지</h1>
	<hr />
		제목 : <input id="title" type="text" name="title" /><br /> 내용 :
		<textarea id="summernote" name="content"></textarea>
		<br />
		<button onclick="save()">글쓰기 완료</button>
</main>

<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			height : 300,
			minHeight : null,
			maxHeight : null,
			focus : true,
			callbacks : {
				onImageUpload : function(files, editor, welEditable) {
					for (var i = files.length - 1; i >= 0; i--) {
						sendFile(files[i], this);
					}
				}
			}
		});
	});

	function sendFile(file, el) {
		var form_data = new FormData();
		form_data.append('file', file);
		$
				.ajax({
					data : form_data,
					type : "POST",
					url : '/image',
					cache : false,
					contentType : false,
					enctype : 'multipart/form-data',
					processData : false,
					success : function(url) {
						$(el).summernote('editor.insertImage', url);
						$('#imageBoard > ul')
								.append(
										'<li><img src="'+url+'" width="480" height="auto"/></li>');
					}
				});
	}
</script>

<script>

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