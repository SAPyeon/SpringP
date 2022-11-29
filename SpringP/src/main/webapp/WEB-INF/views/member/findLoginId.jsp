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
		<h1>회원찾기</h1>
			<div class="col-md-6 offset-md-3 border mx-auto m-5">
				<h2 class="text-center">아이디찾기</h2>
				<div class="row g-3">
						<p>당신의 아이디는 <span>${findUser.id}</span> 입니다.</p>
						<p>당신의 이름은 <span>${findUser.name}</span> 입니다.</p>
					<a href="/member/login">로그인하기</a>
					<a href="/member/findUser?user=2">비밀번호찾기</a>
				</div>
								
			</div>
		</div>
		<%@ include file="../footer.jsp"%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
</body>
</html>