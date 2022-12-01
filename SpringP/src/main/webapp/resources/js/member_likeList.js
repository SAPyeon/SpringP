/**
 * 
 */

const srtncd = $(".srtnCd");

for(let i = 0; i<srtncd.length;i++){
	
	//현재 시세 불러오기
	dataPrice = {
		code : srtncd[i].value,
		codeName : $(".itmsNm")[i].value
	}
	console.log(dataPrice)
	let str = "";
	let str2 = "";
	let str3 = "";
	
	$.getJSON("/StockDetail", dataPrice, function(stock) {
		// 불러온 데이터 정보 입력
		const price = stock.price; 	// 주가
		const dayRange = stock.dayRange; // 등락률
		const diffAmount = stock.diffAmount; // 전일비
		console.log(dayRange)
		if (dayRange.indexOf('-') >= 0) {
			str += `<span style="color:blue">${price}</span>`
			str2 += `<span style="color:blue">${dayRange}</span>`
			str3 += `<span style="color:blue">${diffAmount}</span>`
		} else if (diffAmount == 0) {
			str += `<span>${stock.price}</span>`
			str2 += `<span>${stock.dayRange}</span>`
			str3 += `<span>${stock.diffAmount}</span>`
		} else {
			str += `<span style="color:red">${price}</span>`
			str2 += `<span style="color:red">${dayRange}</span>`
			str3 += `<span style="color:red">${diffAmount}</span>`
		}
		$(".priceToday").eq(i).html(str);
		$(".dayRange").eq(i).html(str2);
		$(".diffAmount").eq(i).html(str3);
		
	})// getJSON 끝
	
}

$(".btn_likeDelete").on("click", function() {
	console.log("code = "+$(this).data("code"))
	console.log($("#loginId").val())// post로 변경
	data = {
		srtnCd : $(this).data("code"),
		id : $("#loginId").val()
	}
	console.log(data)
	if (confirm("해당종목을 삭제하시겠습니까?")) {
		likeDelete(data);
		location.reload();
	}
	location.reload();
//	location.href = "/member/likeList";

})

// 즐겨찾기 삭제
function likeDelete(data) {
	console.log(data)
	$.ajax({
		type : "delete",
		url : "/likeDelete",
		data : JSON.stringify(data),
		contentType : "application/json; charset=utf-8",
		success : function() {
			alert('즐겨찾기에 삭제되었습니다.')
			location.reload();
		}
	})
}


// 상승 하락에 대한 폰트 색상 설정
function fontColor(){
	$(".dayRange").each(function(i,list){
		if(list.innerText.indexOf("-")==0){
			$(this).addClass("decrease")
			$(this).prev("td.diffAmount").addClass("decrease")
		}else if(list.innerText.indexOf("+")==0){
			$(this).addClass("increase")
			$(this).prev("td.diffAmount").addClass("increase")
		}
	})

}


