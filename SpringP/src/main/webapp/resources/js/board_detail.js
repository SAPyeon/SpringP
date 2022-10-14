/**
 * 
 */
$("#codeName").val();
let codeName = $("#codeName").val();

$.getJSON("/Datadetail",{itmsNm:codeName},function(data){
	let str="";
	const basDt=[];
	const chartData = [];
	const barColor=[];
	
	str+=`<table class="table table-bordered table table-hover">
		<tr>
			<th>종목코드</th>
			<th>종목명</th>
			<th>시장구분</th>
			<th>종가</th>
			<th>등락률</th>
			<th>시가</th>
			<th>기준일자</th>
			<th>고가</th>
			<th>저가</th>
			<th>거래량</th>
			<th>시가총액</th>
		</tr>`
	$(data).each(function(i,stock){
		str+=`<tr>
				<td>${stock.srtnCd}</td>
				<td>${stock.itmsNm}</td>
				<td>${stock.mrktCtg}</td>
				<td>${stock.clpr}</td>
				<td>${stock.fltRt}</td>
				<td>${stock.mkp}</td>
				<td>${stock.basDt}</td>
				<td>${stock.hipr}</td>
				<td>${stock.lopr}</td>
				<td>${stock.trqu}</td>
				<td>${stock.mrktTotAmt}</td>
			</tr>`;
		basDt[i] = stock.basDt;
		chartData[i] = stock.clpr;
		barColor[i] = "red";
	})
	str+=`</table>`;
	$("#stockList").html(str);
	makeBarChart(basDt,chartData,barColor);
	
})// getJSON 끝


function makeBarChart(basDt,chartData,barColor){
	new Chart(document.getElementById("bar-chart"), 
		{
		type:'bar',
		data: {
			labels: basDt,
			datasets: [
				
				{
					
					label: "가격바",
					backgroundColor: barColor,
					data: chartData
				},
				{
					type:'line',
					label: "가격라인",
					backgroundColor : "blue",
					borderColor: "blue",
					data: chartData,
					fill: false
				}
				]
		},
		options: {
			legend: { display: false },
			title: {
				display: true,
				text: '주가흐름'
			},
			scales: {
				xAxes: [{
				      gridLines: {
				        display: false,
				        color: "black"
				      },
				      scaleLabel: {
				        display: true,
				        labelString: "날짜",
				        fontColor: "red"
				      }
				    }],
				    yAxes: [{
				      gridLines: {
				        color: "black",
				        borderDash: [2, 5],
				      },
				      scaleLabel: {
				          display: true,
				          labelString: "주가",
				          fontColor: "green"
				        }
				    }]
			}
		}
		
	});
};// makeBarChart끝


