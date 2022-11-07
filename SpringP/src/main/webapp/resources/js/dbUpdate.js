/**
 * 
 */
function basDt(n) {
	const today = new Date();
	const yesterday = new Date(today.setDate(today.getDate() - n))
			.toLocaleDateString().replace(/\./g, '').split(' ');
	//날짜 월, 일이 한글자일 때 0을 붙여줌
	if(yesterday[1].length==1){
        yesterday[1] = "0"+yesterday[1];
    }
    if(yesterday[2].length==1){
        yesterday[2] = "0"+yesterday[2];
    }
	return yesterday[0] + yesterday[1] + yesterday[2]// 20221018

}
console.log(basDt(1))

dataParam = {
	numOfRows : "1000",
	mrktCls : "KOSPI",
	itmsNm : null,
	likeItmsNm : null,
	basDt : basDt(1)
};

callStockList(dataParam);

let count = 1;

function callStockList(dataParam) {
	$.getJSON("/Datadetail", dataParam, function(data) {
		console.log(data.length)
		if (data.length != 0) {
			$(data).each(function(i, st) {
				if (st.basDt != basDt(1)) {
					return false;
				}
			})
			console.log(dataParam.basDt)
			DBUpdate(data)
		} else {
			count += 1;
			console.log("기준날짜 데이터 없음")
			console.log("count = " + count)
			dataParam.basDt = basDt(count)
			console.log("기준날짜 변경 = " + dataParam.basDt);
			callStockList(dataParam);
		}
	})// getJSON끝
}// 함수끝

function DBUpdate(data) {
	const DBUpdate_Stock = document.querySelector("#DBUpdate_Stock");
	DBUpdate_Stock.addEventListener("click", function() {
		if (data != 0) {
			$.ajax({
				url : "/DBUpdate_Stock",
				type : "post",
				data : {
					numOfRows : data.length,
				},
				success : function(data) {
					alert("업데이트성공");
				},
				error : function() {
					alert("error");
				}
			})// ajax끝
		} else {
			alert("업데이트 할 정보가 없습니다.")
		}
	})
}// 함수끝

const DBUpdate_Com = document.querySelector("#DBUpdate_Com");
DBUpdate_Com.addEventListener("click", function() {
	$.ajax({
		url : "/DBUpdate_Com",
		type : "post",
		success : function(data) {
			alert("업데이트성공");
		},
		error : function() {
			alert("error");
		},
	})
});
