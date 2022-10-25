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
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp"%>

		<div class="row main">
			<div class="input-form col-md-12 mx-auto">
				<h1>회원가입</h1>
				<form action="/member/signup" method="post">
					<div class="col-md-6 mb-3">
						<label for="id">ID</label> <input type="text" id="id" placeholder="id...." name="id">
						<div class="invalid-feedback">
               			 아이디를 입력해주세요.
             		 	</div>
					</div>
					<div class="col-md-6 mb-3">
						<label for="pw">password</label> <input type="password" id="pw"	placeholder="password...." name="password">
						<div class="invalid-feedback">
               			 비밀번호를 입력해주세요.
             		 	</div>
					</div>
					<div class="col-md-6 mb-3">
						<label for="name">name</label> <input type="text" id="name" placeholder="name...." name="name">
						<div class="invalid-feedback">
               			 이름을 입력해주세요.
             		 	</div>
					</div>
					<button class="btn btn-primary btn-lg btn-block" type="submit">회원가입하기</button>
				</form>
				<label>회원가입시 1,000,000 포인트 지급</label>
			</div>
		</div>
		<%@ include file="../footer.jsp"%>
	</div>
</body>
</html>