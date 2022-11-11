/**
 * 
 */
let p = 1;
DisplayStockList(p);

$("#prev").on("click",function(){
	p -=1;
	if(p<1){
		p=1;
	}
	DisplayStockList(p);
})
$("#next").on("click",function(){
	p +=1;
	DisplayStockList(p);
})
function DisplayStockList(p){
	console.log(p)
	$.getJSON("/StockList/",{p:p},function(list){
		let str= ""; 
		str +=`
		<table class="table table-bordered table table-hover" >
			<thead class="thead-light">
	        <tr>
	            <th scope="col">글번호</th>
	            <th scope="col">종목명</th>
	            <th scope="col">현재가</th>
	            <th scope="col">전일비</th>
				<th scope="col">등락률</th>
				<th scope="col">액면가</th>
				<th scope="col">시가총액</th>
				<th scope="col">상장 주식 수</th>
				<th scope="col">외국인 비율</th>
				<th scope="col">거래량</th>
				<th scope="col">PER</th>
				<th scope="col">ROE</th>
				<th scope="col">토론방</th>
	        </tr>
	        </thead>`
		$(list).each(function(i,stock){
			let [a,b] = stock.discussionRoomUrl.split('?');
			let code = b;
			//console.log(b)
	          str+=    `<tr>
	                        <td>${stock.no}</td>
	                        <td><a href="/board/detail?itmsNm=${stock.stockName}&${code}">${stock.stockName}</a></td>
	                        <td>${stock.price}</td>`
	                        if(stock.dayRange.indexOf('-')>=0){
	                        	str+=`<td style="color:blue">${stock.diffAmount}</td>`
	                        }else if(stock.diffAmount == 0){
	                        	str+=`<td>${stock.diffAmount}</td>`
	                        }
	                        else{
	                        	str+=`<td style="color:red">${stock.diffAmount}</td>`
	                        }
	                        
	        	  str+=`<td>${stock.dayRange}</td>
	        	  <td>${stock.parValue}</td>
	        	  <td>${stock.marketCap}</td>
	        	  <td>${stock.numberOfListedShares}</td>
	        	  <td>${stock.foreignOwnRate}</td>
	        	  <td>${stock.turnover}</td>
	        	  <td>${stock.per}</td>
	        	  <td>${stock.roe}</td>
	        	  <td><a href="${stock.discussionRoomUrl}">토론방</a></td>
	                    </tr>`
		})
		str+=`</table>`;
		$("#stockListTable").html(str);
		console.log(list);
	})
	
}





























