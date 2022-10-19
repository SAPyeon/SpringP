/**
 * 
 */
function basDt(n) {
	const today = new Date();
	const yesterday = new Date(today.setDate(today.getDate() - n))
			.toLocaleDateString().replace(/\./g, '').split(' ');

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

function callStockList(dataParam) {
	let count = 0;
	$.getJSON("/Datadetail", dataParam, function(data) {
		console.log(data.length)
		$(data).each(function(i, st) {
			if (st.basDt != basDt(1)) {
				return false;
			}
		})
		DBUpdate(data)
		
	})
}

function DBUpdate(data){
	const DBUpdate = document.querySelector("#DBUpdate");
	DBUpdate.addEventListener("click", function() {
		if (data.length != 0) {
			$.getJSON("/DBUpdate", {numOfRows : data.length}, function(data) {
				alert("업데이트성공")
			})
		} else {
			alert("업데이트 목록이 없습니다.")
		}
	})
}


