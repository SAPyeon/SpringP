<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
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

<link rel="stylesheet" href="../resources/css/login.css">
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp"%>

		<div class="row main">
			<form action="/member/login" method="post" id="loginForm">
				<h1>login</h1>
				<div id="id">
					<input type="text" placeholder="id" class="text_id" name="id">
				<div id="pw">	
					<input type="password" placeholder="password" class="text_id"
						name="password">
				</div>
				</div>
				<div id="checkbox">
					<label><input type="checkbox" value="save" class="checkbox">
						아이디 저장</label> <label><input type="checkbox" value="notsave"
						class="checkbox">보안접속</label>
				</div>
				<div class="loginbutton">
					<div>
						<input type="submit" id="button_login" value="로그인" />
					</div>

				</div>
				<div class="errorMessage"></div>
				<div id="id_fc">
					<button id="button_findid" type="button">아이디/비밀번호 찾기</button>
					<button id="button_createid" type="button">회원가입</button>
				</div>
				<div id="kakao_login" class="loginbutton">
					<button id="button_kakaologin" type="button">카카오로 로그인</button>
				</div>
				<div id="naver_login" class="loginbutton">
					<button id="button_naverlogin" type="button">네이버로 로그인</button>
					<!-- <img src="https://static.nid.naver.com/oauth/big_g.PNG?version=js-2.0.1" height="50"> -->
				</div>
			</form>
			<input type="hidden" value="${urlNaver}" id="naverLoginUrl">
			<input type="hidden" value="${urlKakao}" id="kakaoLoginUrl">
		</div>
		<%@ include file="../footer.jsp"%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="../resources/js/member_login.js"></script>
</body>
</html>