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
	        <tr>
	            <th>글번호</th>
	            <th>종목명</th>
	            <th>현재가</th>
	            <th>전일비</th>
				<th>등락률</th>
				<th>액면가</th>
				<th>시가총액</th>
				<th>상장 주식 수</th>
				<th>외국인 비율</th>
				<th>거래량</th>
				<th>PER</th>
				<th>ROE</th>
				<th>토론방</th>
	        </tr>`
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





























