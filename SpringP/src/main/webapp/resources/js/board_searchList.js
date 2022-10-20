/**
 * 
 */
/*
//url 파라미터 가져오기
const url = new URL(window.location.href);
const urlParam = url.searchParams;
console.log(urlParam.get('likeItmsNm'));
console.log(urlParam.get('pageNo'));
*/

//숫자포맷
function priceToString(price) {
	return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

const numFormat = document.querySelectorAll(".numFormat");

numFormat.forEach((e)=>{
	e.innerText = priceToString(e.innerText);
})