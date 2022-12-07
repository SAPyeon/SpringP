/**
 * 
 */


// 유효성검사 성공하면 체크
function chkUnchk(a, b) {
	const disabledChk = a
	disabledChk.prop("disabled", false);
	disabledChk.prop("checked", b);
	disabledChk.prop("disabled", true);
}

function toggle(a, b) {
	a.toggle();
	b.toggle();
}

// 아이디 중복조회 함수
function confirmId(data) {
	$.getJSON("/member/findId", data, function(result) {
		console.log(result)
		if (result === 1) {
			alert('이미 등록된 아이디 입니다.');
			$("#id").val("");
			chkUnchk($("#chk_id"), false);
			$("#valid-id").hide();
			$("#invalid-id").show();
		} else {
			alert('사용할 수 있는 아이디 입니다.');
			chkUnchk($("#chk_id"), true);
			$("#invalid-id").hide();
			$("#valid-id").show();
		}
	})
}

// 유효성 검사 함수
function check() {
	// 아이디 유효성 검사
	const regExp_id = RegExp(/^[a-z]{1}[a-z0-9-_]{5,19}$/g);
	$("#id").on("input", function() {
		chkUnchk($("#chk_id"), false);
		$("#invalid-id").hide();
		$("#valid-id").hide();
	})

	// 아이디 중복체크
	$("#confirm_id").on("click", function() {
		idVal = $("#id").val();
		console.log(idVal)
		data = {
			id : idVal
		};
		if (!regExp_id.test($("#id").val())) {
			$("#invalid-id").show();
		} else {
			confirmId(data);
		}
	})

	

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

	// 이름 유효성 검사
	const regExp_name = RegExp(/^[가-힣ㄱ-ㅎㅏ-ㅣa-z0-9-_]{1,10}$/g);

	$("#name").on("input", function() {
		chkUnchk($("#chk_name"), false);
		if (!regExp_name.test($(this).val())) {
			$("#valid-name").hide();
			$("#invalid-name").show();
		} else {
			$("#valid-name").show();
			$("#invalid-name").hide();
			chkUnchk($("#chk_name"), true);
			console.log(regExp_name.test($(this).val()))
		}

	})

	// 전화번호 유효성 검사
	const regExp_phone = RegExp(/^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/);

	$("#phone").on("input", function(e) {
		chkUnchk($("#chk_phone"), false);
		if (!regExp_phone.test($(this).val())) {
			$("#valid-phone").hide();
			$("#invalid-phone").show();
		} else {
			$("#valid-phone").show();
			$("#invalid-phone").hide();
			chkUnchk($("#chk_phone"), true);
		}

	})
}

// 약관동의 체크박스체크
//전체동의
const allcheck = document.querySelector('#allcheck');
const agreement = document.querySelectorAll('.agreement');
const check_agree = document.querySelector('#chk_agree');
const query = 'input[name = "check"]:checked';
const query2 = 'input[name = "check2"]:checked';

function makeAllCheck(query, a, b , c) {
	console.log(typeof c)
	
    for (let check of b) {
        //console.log(b.length)
        a.addEventListener('input', () => {
            if (a.checked === true) {
            	check.checked = true;
            	if(typeof c !== 'undefined'){
            		chkUnchk(c, true);
            	}
                
            } else {
                check.checked = false;
                if(typeof c !== 'undefined'){
                	chkUnchk(c, false);
            	}
            }
        })
        check.addEventListener('change', () => {
            getCheckedCnt(query);
            //console.log(selectedElementsCnt)
            if (b.length === selectedElementsCnt) {
                a.checked = true;
                if(typeof c !== 'undefined'){
            		chkUnchk(c, true);
            	}
            }
            else if (check.checked === false) {
                a.checked = false;
                if(typeof c !== 'undefined'){
                	chkUnchk(c, false);
            	}
            }
        })
    }
}

//체크된 갯수세기
function getCheckedCnt(query) {
    const selectedElements = document.querySelectorAll(query);
    selectedElementsCnt = selectedElements.length;
    console.log(selectedElementsCnt)
    return selectedElementsCnt;
}

//마케팅 동의
const allcheckMrk = document.querySelector("#check_allMrk");
const marketchk = document.querySelectorAll(".marketchk")


check();

makeAllCheck(query, allcheck, agreement, $("#chk_agree"));

makeAllCheck(query2, allcheckMrk, marketchk);

console.log($($(".marketchk")[0]).is(":checked"))



$("#btn_signUp").on("click",function(e) {
			e.preventDefault();
			// 마케팅 체크 전달
			$("input[name='agree_email']").val($($(".marketchk")[0]).is(":checked"))	
			$("input[name='agree_sms']").val($($(".marketchk")[1]).is(":checked"))	
			$("input[name='agree_app']").val($($(".marketchk")[2]).is(":checked"))	
			
			check();
			if(!$("#chk_id").is(":checked")){
				console.log($("#chk_id").is(":checked"))
				alert('id 입력란을 확인하세요')
				$("#id").focus();
			}
			else if(!$("#chk_pw").is(":checked")){
				alert('password 입력란을 확인하세요')
				$("#pw").focus();
			}
			else if(!$("#chk_pwMore").is(":checked")){
				alert('비밀번호 재확인 입력란을 확인하세요')
				$("#pwMore").focus();
			}
			else if(!$("#chk_name").is(":checked")){
				alert('Name 입력란을 확인하세요')
				$("#name").focus();
			}
			else if(!$("#chk_phone").is(":checked")){
				alert('Phone 입력란을 확인하세요')
				$("#phone").focus();
			}
			else if(!$("#chk_agree").is(":checked")){
				alert('이용약관에 동의하세요')
			}
			else if ($("#chk_id").is(":checked") && $("#chk_pw").is(":checked")
					&& $("#chk_pwMore").is(":checked")
					&& $("#chk_name").is(":checked")
					&& $("#chk_phone").is(":checked")
					&& $("#chk_agree").is(":checked")) {
				$("#needs-validation").submit();
			}
})

//내용보기 팝업창생성
$(".read_agreement").on("click",function(){
	let uri = "/member/readAgree"
	window.open(uri, "readAgree", "width=600, height=700, top = 100, left=200")	
	
	
})
