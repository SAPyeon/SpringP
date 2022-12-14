/**
 * 
 */
// 회원가입 버튼 클릭시 회원가입 페이지 이동
const gotoRegiter = document.querySelector("#button_createid");
gotoRegiter.addEventListener("click", function() {
	location.href = "/member/signup"
})

// 네이버 로그인 버튼 클릭
const btn_naverlogin = document.querySelector("#button_naverlogin");

const naverLoginUrl = document.querySelector("#naverLoginUrl");
// console.log(naverLoginUrl.value)

btn_naverlogin.addEventListener("click", gotologinNaver);

function gotologinNaver() {
	location.href = naverLoginUrl.value;
}

// 네이버 로그인을 위한 팝업창 생성
// function showLoginPopup(){
// let uri = naverLoginUrl.value;
// // 사용자가 사용하기 편하게끔 팝업창으로 띄어준다.
// window.open(uri, "Naver Login Test PopupScreen", "width=500, height=600");
// }

// 카카오 로그인 버튼 클릭
const btn_kakaologin = document.querySelector("#button_kakaologin");

const kakaoLoginUrl = document.querySelector("#kakaoLoginUrl");
// console.log(kakaoLoginUrl.value)

btn_kakaologin.addEventListener("click", gotologinKakao);

function gotologinKakao() {
	location.href = kakaoLoginUrl.value;
}

// 아이디/비밀번호 찾기 버튼 클릭시 해당 페이지 이동
const btn_findid = document.querySelector("#button_findid");

btn_findid.addEventListener("click", function() {
	location.href = "/member/findUser"
})

// 아이디 저장 체크박스 - 쿠키 저장
// 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
const key = getCookie("key");
$("#logId").val(key);

// 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
if ($("#logId").val() != "") {
	$("#saveId").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
}

$("#saveId").change(function() { // 체크박스에 변화가 있다면,
	if ($("#saveId").is(":checked")) { // ID 저장하기 체크했을 때,
		setCookie("key", $("#logId").val(), 7); // 7일 동안 쿠키 보관
	} else { // ID 저장하기 체크 해제 시,
		deleteCookie("key");
	}
});

// ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
$("#logId").keyup(function() { // ID 입력 칸에 ID를 입력할 때,
	if ($("#saveId").is(":checked")) { // ID 저장하기를 체크한 상태라면,
		setCookie("key", $("#logId").val(), 7); // 7일 동안 쿠키 보관
	}
});

// 쿠키 저장하기
// setCookie => saveid함수에서 넘겨준 시간이 현재시간과 비교해서 쿠키를 생성하고 지워주는 역할
function setCookie(cookieName, value, exdays) {
	const exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	const cookieValue = escape(value)
			+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
	document.cookie = cookieName + "=" + cookieValue;
}

// 쿠키 삭제
function deleteCookie(cookieName) {
	const expireDate = new Date();
	expireDate.setDate(expireDate.getDate() - 1); // 만료일이 음수이면 데이터를 저장하지 않음
	document.cookie = cookieName + "= " + "; expires="
			+ expireDate.toGMTString();
}

// 쿠키 가져오기
function getCookie(cookieName) {
	cookieName = cookieName + '=';
	const cookieData = document.cookie;
	let start = cookieData.indexOf(cookieName);
	let cookieValue = '';
	if (start != -1) { // 쿠키가 존재하면
		start += cookieName.length;
		console.log("start위치 : " + start);
		let end = cookieData.indexOf(';', start);
		if (end == -1) // 쿠키 값의 마지막 위치 인덱스 번호 설정
			end = cookieData.length;
		console.log("end위치  : " + end);
		cookieValue = cookieData.substring(start, end);
	}
	return unescape(cookieValue);
}
