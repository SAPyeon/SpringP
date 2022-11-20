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
#signUpFrame {
	border-radius: 23px
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
			<div class="input-form col-md-6 offset-md-3 border mx-auto m-5"
				id="signUpFrame">
				<h1>회원가입</h1>
				<form action="/member/signup" method="post" id="needs-validation"
					novalidate class="row g-3">
					<div class="form-group">
						<label for="id" class="form-label">ID</label> <input type="checkbox" disabled id="chk_id">	
						<input type="text"
							id="id" placeholder="id...." name="id" required	class="form-control form-control-lg">
						<button type="button" class="btn btn-primary btn-sm mt-1" id="confirm_id">아이디 중복조회</button>
						
						<div id="valid-id" class="valid-feedback">사용할 수 있는 아이디입니다.</div>
						<div id="invalid-id" class="invalid-feedback">영문자로 시작하는 영문자 또는 숫자 6~20자를 입력해주세요.</div>
					</div>
					<div class="form-group">
						<label for="pw" class="form-label">PASSWORD</label> <input type="checkbox" disabled id="chk_pw"> 
						<input type="password" id="pw" placeholder="password...." name="password" required class="form-control form-control-lg">
						<div id="valid-pw" class="valid-feedback">사용할 수 있는 비밀번호입니다.</div>
						<div id="invalid-pw" class="invalid-feedback">6~20글자를 입력해주세요.</div>
					</div>
					<div class="form-group">
						<label for="pwMore" class="form-label">PASSWORD 재확인</label> <input type="checkbox" disabled id="chk_pwMore"> 
						<input
							type="password" id="pwMore" 
							required class="form-control form-control-lg">
						<div class="valid-feedback" id="valid-pwMore">비밀번호와 일치합니다.</div>
						<div class="invalid-feedback" id="invalid-pwMore">비밀번호와 불일치합니다.</div>
					</div>
					<div class="form-group">
						<label for="name" class="form-label">NAME</label><input type="checkbox" disabled id="chk_name"> 
						<input
							type="text" id="name" placeholder="name...." name="name" required
							class="form-control form-control-lg">
						<div class="valid-feedback" id="valid-name">사용할 수 있는 이름입니다.</div>
						<div class="invalid-feedback" id="invalid-name">특수문자를 제외한 1~20자의 이름을 입력해주세요</div>
					</div>
					<div class="form-group">
						<label for="phone" class="form-label">PHONE("-"없이 입력)</label> <input type="checkbox" disabled id="chk_phone"> 
						<input
							type="text" id="phone" placeholder="phone...." name="phone"
							required class="form-control form-control-lg">
						<div class="valid-feedback" id="valid-phone">사용할 수 있는 전화번호입니다.</div>
						<div class="invalid-feedback" id="invalid-phone">전화번호를 입력해주세요.</div>
					</div>
					<button class="btn btn-primary btn-lg btn-block" type="button"
						id="btn_signUp">회원가입하기</button>
				</form>
				<label class="col-md-6 offset-md-3">회원가입시 1,000,000 포인트 지급</label>
			</div>
		</div>
		<%@ include file="../footer.jsp"%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="../resources/js/signup.js"></script>
	
</body>
</html>