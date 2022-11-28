/**
 * 
 */

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

fontColor();

function fontColor(){
	$(".dayRange").each(function(i, list){
//		console.log(list.innerText.indexOf("-"))
		//console.log($(this).prev("td.diffAmount").addClass("decrease"));
		if(list.innerText.indexOf("-")==0){
			$(this).addClass("decrease")
			$(this).prev("td.diffAmount").addClass("decrease")
		}else if(list.innerText.indexOf("+")==0){
			$(this).addClass("increase")
			$(this).prev("td.diffAmount").addClass("increase")
		}
	})

}

const srtncd = $(".srtnCd");

for(let i = 0; i<srtncd.length;i++){
	
	//현재 시세 불러오기
	dataPrice = {
		code : srtncd[i].value,
		codeName : $(".itmsNm")[i].value
	}
	console.log(dataPrice)
	let str = "";
	
	$.getJSON("/StockDetail", dataPrice, function(stock) {
		// 불러온 데이터 정보 입력
		const price = stock.price; // 주가
		if ($(".dayRange").text().indexOf('-') >= 0) {
			str += `<span style="color:blue">${price}</span>`
		} else if ($(".diffAmount") == 0) {
			str += `<span>${stock.price}</span>`
		} else {
			str += `<span style="color:red">${price}</span>`
		}
		console.log($(this).value)
		$(".priceToday").html(str);
	})// getJSON 끝
}
