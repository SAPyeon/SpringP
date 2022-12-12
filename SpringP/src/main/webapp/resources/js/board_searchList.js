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

const srtncd = $(".srtnCd");

for(let i = 0; i<srtncd.length;i++){
	dataPrice={code :srtncd[i].value, codeName: $(".itmsNm")[i].value}
	$.getJSON("/StockDetail", dataPrice, function(stock){
		let str="";
		let str2="";
		const price = stock.price; 	// 주가
		const dayRange = stock.dayRange; // 등락률
		
		console.log(dayRange)
		if (dayRange.indexOf('-') >= 0) {
			str += `<span style="color:blue">${price}</span>`
			str2 += `<span style="color:blue">${dayRange}</span>`
		} else if (dayRange.indexOf('+') >= 0) {
			str += `<span style="color:red">${price}</span>`
			str2 += `<span style="color:red">${dayRange}</span>`
		} else {
			str += `<span>${stock.price}</span>`
			str2 += `<span>${stock.dayRange}</span>`
		}
		$(".priceToday").eq(i).html(str);
		$(".dayRange").eq(i).html(str2);
		
	})
}

