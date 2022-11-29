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
				<h2 class="text-center">비밀번호 재설정</h2>
				<div class="row g-3">
					<form action="/member/modiPassword" method="post"
						id="needs-validation" novalidate class="row g-3">
						<input type="hidden" value="${user.id}" name="id">
						<input type="hidden" value="${user.name}" name="name">
						<input type="hidden" value="${user.phone}" name="phone">
						<div class="form-group">
							<label for="pw" class="form-label">PASSWORD</label> <input
								type="checkbox" disabled id="chk_pw"> <input
								type="password" id="pw" placeholder="password...."
								name="password" required class="form-control form-control-lg">
							<div id="valid-pw" class="valid-feedback">사용할 수 있는 비밀번호입니다.</div>
							<div id="invalid-pw" class="invalid-feedback">6~20글자를
								입력해주세요.</div>
						</div>
						<div class="form-group">
							<label for="pwMore" class="form-label">PASSWORD 재확인</label> <input
								type="checkbox" disabled id="chk_pwMore"> <input
								type="password" id="pwMore" required
								class="form-control form-control-lg">
							<div class="valid-feedback" id="valid-pwMore">비밀번호와 일치합니다.</div>
							<div class="invalid-feedback" id="invalid-pwMore">비밀번호와
								불일치합니다.</div>
						</div>
						<button class="btn btn-primary btn-lg btn-block" type="submit" id="btn_modiPw">비밀번호
							재설정</button>
					</form>
				</div>
			</div>
		</div>
		<%@ include file="../footer.jsp"%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
		// 유효성검사 성공하면 체크
		function chkUnchk(a, b) {
			const disabledChk = a
			disabledChk.prop("disabled", false);
			disabledChk.prop("checked", b);
			disabledChk.prop("disabled", true);
		}
		// 비밀번호 유효성 검사
		const regExp_pw = RegExp(/^[a-z0-9-_]{6,19}$/g);

		$("#pw").on("input", function() {
			chkUnchk($("#chk_pw"), false);
			$("#pwMore").val("");
			chkUnchk($("#chk_pwMore"), false);
			if (!regExp_pw.test($(this).val())) {
				$("#valid-pw").hide();
				$("#invalid-pw").show();
			} else {
				$("#valid-pw").show();
				$("#invalid-pw").hide();
				chkUnchk($("#chk_pw"), true);
				console.log(!regExp_pw.test($(this).val()))
			}

		})

		// 비밀번호 재확인
		$("#pwMore").on("input", function() {
			chkUnchk($("#chk_pwMore"), false);
			if ($("#pw").val() === $(this).val()) {
				$("#valid-pwMore").show();
				$("#invalid-pwMore").hide();
				chkUnchk($("#chk_pwMore"), true);
			} else {
				$("#valid-pwMore").hide();
				$("#invalid-pwMore").show();
			}

		})

		$("#btn_modiPw").on(
				"click",
				function(e) {
					e.preventDefault();
					//check();
					if (!$("#chk_pw").is(":checked")) {
						alert('password 입력란을 확인하세요')
						$("#pw").focus();
					} else if (!$("#chk_pwMore").is(":checked")) {
						alert('비밀번호 재확인 입력란을 확인하세요')
						$("#pwMore").focus();
					}

					else if ($("#chk_pw").is(":checked")
							&& $("#chk_pwMore").is(":checked")) {
						if(confirm("비밀번호를 수정하시겠습니까?")){
							$("#needs-validation").submit();	
						}
						
					}
				})
	</script>
</body>
</html>