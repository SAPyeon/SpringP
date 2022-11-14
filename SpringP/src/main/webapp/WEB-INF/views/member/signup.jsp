<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<!-- Bootstrap Javascript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<style type="text/css">
#signUpFrame{
	border-radius:23px
}
#btn_signUp {
	margin: 30px auto;
	width: 98%;
}
</style>
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp"%>
		<div class="row main mb-3 align-self-center" role="main" id="main">
			<div
				class="input-form col-md-6 offset-md-3 border mx-auto m-5" id="signUpFrame">
				<h1>회원가입</h1>
				<form action="/member/signup" method="post" id="needs-validation"
					novalidate class="row g-3">
					<div class="form-group">
						<label for="id" class="form-label">ID</label> <input type="text"
							id="id" placeholder="id...." name="id" required
							pattern="^[a-z]+[a-z0-9]{5,19}$"
							class="form-control form-control-lg">
						<div class="valid-feedback">사용할 수 있는 아이디입니다.</div>
						<div class="invalid-feedback">영문자로 시작하는 영문자 또는 숫자 6~20자를
							입력해주세요.</div>
					</div>
					<div class="form-group">
						<label for="pw" class="form-label">PASSWORD</label> <input
							type="password" id="pw" placeholder="password...."
							name="password" required class="form-control form-control-lg">
						<div class="valid-feedback">사용할 수 있는 비밀번호입니다.</div>
						<div class="invalid-feedback">비밀번호를 입력해주세요.</div>
					</div>
					<div class="form-group">
						<label for="name" class="form-label">NAME</label> <input
							type="text" id="name" placeholder="name...." name="name" required
							class="form-control form-control-lg">
						<div class="valid-feedback">사용할 수 있는 이름입니다.</div>
						<div class="invalid-feedback">이름을 입력해주세요.</div>
					</div>
					<button class="btn btn-primary btn-lg btn-block" type="submit"
						id="btn_signUp">회원가입하기</button>
				</form>
				<label class="col-md-6 offset-md-3">회원가입시 1,000,000 포인트 지급</label>
			</div>
		</div>
		<%@ include file="../footer.jsp"%>
	</div>
	<script type="text/javascript" src="../resources/js/signup.js"></script>
</body>
</html>