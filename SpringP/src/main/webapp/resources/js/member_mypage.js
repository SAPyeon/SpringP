/**
 * 
 */
// readonly 제거
function readOnlyfalse() {
	$("input[name='password']").attr("readonly", false)
	$("input[name='name']").attr("readonly", false)
	$("input[name='phone']").attr("readonly", false)
}

// 유효성검사 성공하면 체크
function chkUnchk(a, b) {
	const disabledChk = a
	disabledChk.prop("disabled", false);
	disabledChk.prop("checked", b);
	disabledChk.prop("disabled", true);
}

// 수정하기
$("#btn_mem_Modify").on(
		"click",
		function() {
			const pwVal = prompt("비밀번호를 입력하세요")
			if (pwVal == $("input[name='password']").val()) {
				readOnlyfalse();
				$('.regExp').show();
				check();
				$(this).off("click").on(
						'click',
						function() {
							if ($("#chk_pw").is(':checked') == true
									&& $("#chk_name").is(':checked') == true
									&& $("#chk_phone").is(':checked') == true) {
								formSubmit('/member/infoModify');
							} else {
								alert("유효성검사 실패")
							}
						})

			} else {
				alert('비밀번호 불일치');
			}

		})

// 유효성검사
function check() {

	// 비밀번호 유효성 검사
	const regExp_pw = RegExp(/^[a-z]+[a-z0-9]{5,19}$/g);

	$("input[name='password']").on("input", function() {
		console.log($(this).val());
		const testVal = regExp_pw.test($(this).val());
		console.log(testVal)
		if (testVal == false) {
			if ($("#chk_pw").is(':checked') == true) {
				chkUnchk($("#chk_pw"), false)
			}
		} else {
			chkUnchk($("#chk_pw"), true)
		}
	})

	// 이름 유효성 검사
	const regExp_name = RegExp(/^[가-힣ㄱ-ㅎㅏ-ㅣa-z0-9-_]{1,10}$/g);

	$("input[name='name']").on("input", function() {
		chkUnchk($("#chk_name"), false)
		const testVal2 = regExp_name.test($(this).val());
		console.log(testVal2)
		if (testVal2 == false) {
			if ($("#chk_name").is(':checked') == true) {
				chkUnchk($("#chk_name"), false)
			}
		} else {
			chkUnchk($("#chk_name"), true)
		}

	})

	// 전화번호 유효성 검사
	const regExp_phone = RegExp(/^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/);

	$("input[name='phone']").on("input", function() {
		chkUnchk($("#chk_phone"), false)
		const testVal3 = regExp_phone.test($(this).val());
		console.log(testVal3)
		if (testVal3 == false) {
			if ($("#chk_phone").is(':checked') == true) {
				chkUnchk($("#chk_phone"), false)
			}
		} else {
			chkUnchk($("#chk_phone"), true)
		}

	})
}

function formSubmit(url) {
	$("#form_member").attr("action", url);
	$("#form_member").attr("method", "post");
	$("#form_member").submit();
}

// 회원탈퇴
$("#btn_mem_Remove").on("click", function() {
	
	if (confirm("삭제하시겠습니까?")) {
		const reason = prompt("탈퇴이유 작성")
		const pwVal = prompt("비밀번호를 입력하세요")
		if (pwVal == $("input[name='password']").val()) {
			alert('삭제완료')
			formSubmit("/member/Withdrawal?reason="+reason)
		}
	}
})
