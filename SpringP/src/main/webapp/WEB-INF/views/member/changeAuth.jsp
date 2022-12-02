<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		<div class="row main mb-3 align-self-center" role="main" id="main">
			<div class="input-form col-md-6 offset-md-3 border mx-auto m-5"
				id="signUpFrame">
				<h1>관리자신청</h1>
				<form action="/member/changeAuth" method="post" class="row g-3">
					<div class="form-group">
						<label for="id" class="form-label">ID</label>
						<input type="text"
							id="id" name="id" required	value="${loginId}" class="form-control form-control-lg" readonly>
					</div>
					<div class="form-group">
						<label for="id" class="form-label">기존 등급</label>
						<input type="text"
							 required value="회원" class="form-control form-control-lg" readonly name="beforeAuthno">
					</div>
					<div class="form-group">
						<label for="id" class="form-label">변경등급</label>
						<input type="text"
							 required value="관리자" class="form-control form-control-lg" readonly name="afterAuthno">
					</div>
					<div class="form-group">
						<label for="reasonChange" class="form-label">변경사유</label>
						<textarea rows="" cols="" class="form-control form-control-lg" name="reasonChange"></textarea>
					</div>
					<button class="btn btn-secondary btn-lg btn-block" type="submit"
						id="btn_signUp">신청하기</button>
				</form>
			</div>
		</div>
		<%@ include file="../footer.jsp"%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</body>
</html>