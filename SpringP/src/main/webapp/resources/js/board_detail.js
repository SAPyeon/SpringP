/**
 * 
 */
const urlParamsD = new URL(location.href).searchParams;
const srtnCd = urlParamsD.get('code');
const itmsNm = urlParamsD.get('itmsNm');
const loginID = $("#loginId").val();

dataParam = {
	numOfRows : "500",
	itmsNm : itmsNm,
	likeItmsNm : null,
	basDt : null,
	mrktCls : "KOSPI"
};

// 주식시세 공공데이터 불러오기
$.getJSON("/Datadetail", dataParam, function(data) {
	console.log(dataParam)
	let str = "";
	const basDt = [];
	const chartData = [];
	console.log(data)
	// 테이블 헤드부분

	// 데이터 역순으로 정렬(왼->오른쪽으로 갈수록 최신순)
	const reverse = data.reverse(); // 원본배열이 변경됨
	// 데이터 집어넣기
	$(data).each(function(i, stock) { // data자체가 역순으로 정렬
		basDt[i] = stock.basDt;
		chartData[i] = stock.clpr;

	})

	makeChart(basDt, chartData);

})// getJSON 끝

// 차트만들기 함수
function makeChart(basDt, chartData) {
	new Chart(document.getElementById("chartItems"), {
		// type:'bar',//bar차트
		data : {
			labels : basDt,
			datasets : [ {
				type : 'line', // 연결선
				label : "종가",
				backgroundColor : "blue",// 동그라미색깔
				borderColor : "blue",// 선색깔
				data : chartData,
				fill : false, // 라인차트 안에 색깔 채우기
				order : 1
			// 순서
			}, ]
		},
		options : {
			legend : {
				display : false, // 차트별로 구분할때 라벨을쓰고 싶을떄
			},
			title : {
				display : true,
				text : '최근5개년 주가흐름'
			},
			scales : {
				xAxes : [ { // x축
					gridLines : {
						display : false,
						color : "black"
					},
					scaleLabel : {
						display : true,
					// labelString: "날짜",
					// fontColor: "red"
					}
				} ],
				yAxes : [ { // y축
					gridLines : {
						color : "black",
						borderDash : [ 2, 5 ],
					},
					scaleLabel : {
						display : true,
					// labelString: "주가",
					// fontColor: "green"
					},
					ticks : { // y축(왼쪽) 구분라인
						beginAtZero : false,
						callback : function(value, index, values) {
							return value.toLocaleString("ko-KR");
						},
					}
				} ]
			},
			elements : {
				point : {
					radius : 0
				}
			// line포인트 제거
			}
		}

	});
};// makeChart끝

// 숫자포맷
function priceToString(price) {
	return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

// 현재 시세 불러오기
dataPrice = {
	code : srtnCd,
	codeName : itmsNm
}

$.getJSON("/StockDetail", dataPrice, function(stock) {
	// 불러온 데이터 정보 입력
	const price = stock.price; // 주가
	const diffAmount = stock.diffAmount; // 전일비
	const dayRange = stock.dayRange; // 등락률
	const parValue = stock.parValue; // 액면가
	const marketCap = stock.marketCap; // 시가총액
	const numberOfListedShares = stock.numberOfListedShares; // 상장주식수
	const foreignOwnRate = stock.foreignOwnRate; // 외국인비율
	const turnover = stock.turnover; // 거래량
	const per = stock.per; // per

	let str = "";
	if (dayRange.indexOf('-') >= 0) {
		str += `<h2 style="color:blue">${price}</h2>`
	} else if (diffAmount == 0) {
		str += `<h2>${stock.price}</h2>`
	} else {
		str += `<h2 style="color:red">${price}</h2>`
	}
	$("#priceToday").html(str);

	// 즐겨찾기 데이터
	const data = {
		srtnCd : srtnCd,
		id : loginID,
		itmsNm : itmsNm
	}

	const dataInsert = {
		srtnCd : srtnCd,
		id : loginID,
		itmsNm : itmsNm,
		diffAmount : diffAmount,
		dayRange : dayRange,
		parValue : parValue,
		marketCap : marketCap,
		numberOfListedShares : numberOfListedShares,
		foreignOwnRate : foreignOwnRate,
		turnover : turnover,
		per : per
	}

	likeCRUD(data, dataInsert);

})// getJSON 끝

// 즐겨찾기 관련 데이터 crud
function likeCRUD(data, dataInsert) {

	if (loginID != "") {
		findLike(data, dataInsert);
	} else {
		$("#star").on("click", function() {
			alert("로그인 후 이용가능합니다.")
		})
	}

	// 즐겨찾기목록 불러오기
	function findLike(data, dataInsert) {
		$.getJSON("/findLike", data, function(result) {
			console.log("result = " + result);
			if (result == 1) {
				console.log("이미 즐겨찾기 있음")
				$("#star").css("color", "yellow");
			}
			likeUpdate(result, data, dataInsert);
		})
	}

	// 즐겨찾기 업데이트
	function likeUpdate(result, data, dataInsert) {
		$("#star").on("click", function() {
			if (result == 1) {
				likeDelete(data)
			} else if (result == 0) {
				likeInsert(dataInsert);
			}
			location.reload();
		})

	}

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
				$("#star").css("color", "grey");
			}
		})
	}

	// 즐겨찾기 추가
	function likeInsert(dataInsert) {
		$.ajax({
			type : "put",
			url : "/likeInsert",
			data : JSON.stringify(dataInsert),
			contentType : "application/json; charset=utf-8",
			success : function() {
				alert('즐겨찾기에 추가되었습니다.')
				$("#star").css("color", "yellow");
			}
		})
	}

}
