<<<<<<< HEAD
/**
 * 
 */
//유효성검사 pattern
const regExp_id = /^[a-z]{1}[a-z0-9-_]{5,19}$/g; 
const regExp_pw = /[a-z0-9-_]{6,19}$/g;
const regExp_name = /^[a-z]{1}[a-z0-9-_]{5,19}$/g;
const regExp_phone =  /^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/;
const id_check = document.querySelector("#id_check");

// 제출버튼 클릭시 유효성검사
const btn_signup = document.querySelector("#btn_signUp");
const form = document.getElementById("needs-validation");
btn_signup.addEventListener("click", function(event) {
	if (form.checkValidity() == false || id_check.checked == false) {
		event.preventDefault();
		event.stopPropagation();
		form.classList.add("was-validated");
	}else{
		form.submit();
	}
})

// 폼작성시 유효성 검사





// 비밀번호 재확인
const password = document.querySelector("#pw");
const pwMore = document.querySelector("#pwMore");
const V_pwmore = document.querySelector("#valid-pwMore");
const InV_pwmore = document.querySelector("#invalid-pwMore");

pwMore.addEventListener("input",()=>{
	if(password.value === pwMore.value){
		InV_pwmore.style.display = 'none';
		V_pwmore.style.display = 'block';
	}else{
		V_pwmore.style.display = 'none';
		InV_pwmore.style.display = 'block';
	}
})

// 아이디 중복체크
const confirm_id = document.querySelector("#confirm_id");
const id = document.querySelector("#id");
let idVal ='';

confirm_id.addEventListener("click",()=>{
	idVal = id.value;
	console.log(idVal)
	
	if(idVal.length)
	
	data={id:idVal};
	confirmId(data);
})


function confirmId(data){
	$.getJSON("/member/findId",data,function(result){
			console.log(result)
			if(result===1){
				alert('이미 등록된 아이디 입니다.');
				id.value = '';
			}else{
				alert('사용할 수 있는 아이디 입니다.');
				id_check.disabled = false;
				//$("#id_check").checked = true;
			}
	})
}

// 이름중복체크
function confirmId(data){
	$.getJSON("/member/findName",data,function(result){
			console.log(result)
			if(result===1){
				alert('이미 등록된 아이디 입니다.');
				id.value = '';
			}else{
				alert('사용할 수 있는 아이디 입니다.');
			}
	})
}























=======
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

function confirmId(data) {
	$.getJSON("/findId", data, function(result) {
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

check();

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

$("#btn_signUp").on("click",function(e) {
			e.preventDefault();
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
			else if ($("#chk_id").is(":checked") && $("#chk_pw").is(":checked")
					&& $("#chk_pwMore").is(":checked")
					&& $("#chk_name").is(":checked")
					&& $("#chk_phone").is(":checked")) {
				$("#needs-validation").submit();
			}
			

})
>>>>>>> refs/heads/regExp
