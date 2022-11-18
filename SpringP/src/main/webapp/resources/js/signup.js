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























