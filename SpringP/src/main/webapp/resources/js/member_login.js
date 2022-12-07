/**
 * 
 */
// 회원가입 버튼 클릭시 회원가입 페이지 이동
const gotoRegiter = document.querySelector("#button_createid");
gotoRegiter.addEventListener("click",function(){
	location.href="/member/signup"
})

// 네이버 로그인 버튼 클릭
const btn_naverlogin = document.querySelector("#button_naverlogin");

const naverLoginUrl = document.querySelector("#naverLoginUrl");
console.log(naverLoginUrl.value)

btn_naverlogin.addEventListener("click",gotologinNaver);

function gotologinNaver(){
	location.href=naverLoginUrl.value;
}

// 네이버 로그인을 위한 팝업창 생성
//function showLoginPopup(){
//	let uri = naverLoginUrl.value; 
//    // 사용자가 사용하기 편하게끔 팝업창으로 띄어준다.
//    window.open(uri, "Naver Login Test PopupScreen", "width=500, height=600");
//}

//카카오 로그인 버튼 클릭
const btn_kakaologin = document.querySelector("#button_kakaologin");

const kakaoLoginUrl = document.querySelector("#kakaoLoginUrl");
console.log(kakaoLoginUrl.value)

btn_kakaologin.addEventListener("click",gotologinKakao);

function gotologinKakao(){
	location.href=kakaoLoginUrl.value;
}

// 아이디/비밀번호 찾기 버튼 클릭시 해당 페이지 이동
const btn_findid = document.querySelector("#button_findid");

btn_findid.addEventListener("click",function(){
	location.href="/member/findUser"
})