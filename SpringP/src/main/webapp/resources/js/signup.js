/**
 * 
 */
// 제출버튼 클릭시 유효성검사
const btn_signup = document.querySelector("#btn_signUp");
const form = document.getElementById("needs-validation");
btn_signup.addEventListener("click", function(event) {
	if (form.checkValidity() == false) {
		event.preventDefault();
		event.stopPropagation();
		form.classList.add("was-validated");
	}else{
		form.submit();
	}
})

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
	data={id:idVal};
	confirmId(data);
})

function confirmId(data){
	$.getJSON("/findId",data,function(result){
			console.log(result)
			if(result===1){
				alert('이미 등록된 아이디 입니다.');
				id.value = '';
			}else{
				alert('사용할 수 있는 아이디 입니다.');
			}
	})
}

// 이름중복체크
























