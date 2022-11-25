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
	
<style type="text/css">
.regExp{
	text-align: right;
	font-size:12px;
	color: red;
	display:none;
}

</style>	
	
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp"%>
		<div class="row main d-flex flex-column justify-content-center">
			<form class="border mb-3 col col-sm-5 align-self-center my-3" id="form_member">
			<div>
				<div class="d-flex justify-content-between my-1">
					<label>ID : </label> <input type="text"	value=${memberInfo.id} name = "id" readonly>
				</div>
				<div class="d-flex justify-content-between my-1">
					<label>PW : </label> <input type="password"	value=${memberInfo.password} name="password" readonly>
				</div>
				<div class="regExp">
					<p>6~20글자를 입력해주세요.</p>
					<input type="checkbox" id="chk_pw" disabled checked>
				</div>
				<div class="d-flex justify-content-between my-1">
					<label>NAME : </label> <input type="text" value=${memberInfo.name} name = "name" readonly>
				</div>
				<div class="regExp">
				<p>특수문자를 제외한 1~20자의 이름을 입력해주세요.</p>
				<input type="checkbox" id="chk_name" disabled checked>
				</div>
				<div class="d-flex justify-content-between my-1">
					<label>PHONE : </label> <input type="text" value=${memberInfo.phone} name="phone" readonly>
					
				</div>
				<div class="regExp">
				<p>전화번호를 입력해주세요.("-"없이 입력)</p>
				<input type="checkbox" id="chk_phone" disabled checked>
				</div>
				<div class="d-flex justify-content-between my-1">
					<label>POINT : </label> <input type="text" value=${memberInfo.point} readonly>
				</div>
			</div>
			
				<div class="d-flex justify-content-between my-1">
					<button class="btn btn-primary" type="button" id="btn_mem_Modify">수정</button>
					<button class="btn btn-primary" type="button" id="btn_mem_Remove">회원탈퇴</button>
				</div>
			</form>
			<a href="/member/likeList">즐겨찾기 목록보기</a>
			<a href="/member/boardList">내가쓴 글 보기</a>
		</div>
		<%@ include file="../footer.jsp"%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="../resources/js/member_mypage.js"></script>
</body>
</html>