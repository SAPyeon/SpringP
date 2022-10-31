/**
 * 
 */
const idVal = document.querySelector("#id")
const pwVal = document.querySelector("#pw")
const nameVal = document.querySelector("#name")
const form = document.querySelector("#needs-validation")

// 유효성검사
form.addEventListener("submit",function(e){
	e.preventDefault();
	const regExp = /^[a-z]+[a-z0-9]{5,19}$/g;
	if(regExp.test(idVal.value)){
		alert("영문자로 시작하는 영문자 또는 숫자 6~20자를 입력해주세요.")
	}
	form.classList.add('.valid-feedback');
})