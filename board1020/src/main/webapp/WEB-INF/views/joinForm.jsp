<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp"%>

<main>
	<h1>회원가입 페이지</h1>
	<hr />
		<input id="username" type="text" name="username" placeholder="유저이름"/><br />
		<input id="password" type="password" name="password" placeholder="비번"/><br />
		<input id="email" type="email" name="email" placeholder="이메일"/><br />
		 <button onclick="join()" class="btn btn-secondary">가입 완료</button>
</main>

<script>
//글 작성
function join(){
	let username = document.querySelector("#username").value;
	let password = document.querySelector("#password").value;
	let email = document.querySelector("#email").value;

	let user = {
		username : username,
		password : password,
		email : email
	};

	$.ajax({
		type:"post",
		url:"/all/joinProc",
		data:JSON.stringify(user),
		contentType:"application/json",
		success:function(data,textStatus){
			if(data=='ok'){
				alert("회원가입 성공ㅊㅋ");
				location.href=document.referrer;
			}else{
				alert("회원가입 실패ㅜㅜ")
			}
		},error:function(data,textStatus){
			alert("서버 에러.")
		}
	});
	
}
</script>

<%@include file="layout/footer.jsp"%>