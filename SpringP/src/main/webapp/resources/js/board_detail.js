/**
 * 
 */
let codeName = $("#codeName").val();

dataParam = {numOfRows:"500",itmsNm:codeName,likeItmsNm:null,basDt:null,mrktCls:"KOSPI"};

//주식시세 공공데이터 불러오기
$.getJSON("/Datadetail",dataParam,function(data){
	console.log(dataParam)
	let str="";
	const basDt=[];
	const chartData = [];
	console.log(data)
	//테이블 헤드부분

	//데이터 역순으로 정렬(왼->오른쪽으로 갈수록 최신순)	
	const reverse = data.reverse(); //원본배열이 변경됨
	//데이터 집어넣기	
	$(data).each(function(i,stock){ //data자체가 역순으로 정렬
		basDt[i] = stock.basDt;
		chartData[i] = stock.clpr;
		
	})

	makeChart(basDt,chartData);
	
})// getJSON 끝

//차트만들기 함수
function makeChart(basDt,chartData){
	new Chart(document.getElementById("chartItems"), 
		{
		//type:'bar',//bar차트
		data: {
			labels: basDt,
			datasets: [
				{
					type:'line', //연결선
					label: "종가",
					backgroundColor : "blue",//동그라미색깔
					borderColor: "blue",//선색깔
					data: chartData,
					fill: false, //라인차트 안에 색깔 채우기
					order: 1 //순서
				},
				]
		},
		options: {
			legend: {
				display: false, //차트별로 구분할때 라벨을쓰고 싶을떄
				},
			title: {
				display: true,
				text: '최근5개년 주가흐름'
			},
			scales: {
				xAxes: [{ //x축
				      gridLines: {
				        display: false,
				        color: "black"
				      },
				      scaleLabel: {
				        display: true,
				        //labelString: "날짜",
				        //fontColor: "red"
				      }
				    }],
				    yAxes: [{ //y축
				      gridLines: {
				        color: "black",
				        borderDash: [2, 5],
				      },
				      scaleLabel: {
				          display: true,
				          //labelString: "주가",
				          //fontColor: "green"
				        },
				       ticks: { //y축(왼쪽) 구분라인
				            beginAtZero: false,
				            callback: function(value, index, values) {
				            return value.toLocaleString("ko-KR");
				            },
				        }
				    }]
			},
			elements:{
				point:{radius:0} //line포인트 제거
			}
		}
		
	});
};// makeBarChart끝

//숫자포맷
function priceToString(price) {
	return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}


