/**
 * 
 */
// 메인 슬라이드(slick사용)
$('.center').slick({
  slidesToShow: 1,  // 한번에 보여질 슬라이드 갯수
  slidesToScroll: 1, // 슬라이드시 보여질 슬라이드 갯수
  autoplay: true, // 자동넘김
  autoplaySpeed: 2000, // 넘김 속도
  arrows:true,		// 이전,다음버튼
  dots : true, 		// 스크롤바 아래 점으로 페이지네이션 여부
  prevArrow : "<button type='button' class='slick-prev'>prev</button>",
  nextArrow : "<button type='button' class='slick-next'>next</button>",
  responsive:[{
		breakpoint:768,
		settings:{
			arrows:true,
			dots : true, 		// 스크롤바 아래 점으로 페이지네이션 여부
			centerMode:true,
			centerPadding:'40px',
			slidesToShow:1	
		}

	}]
});


// 주식 종목 top 5
$.getJSON("/StockList/", { p: 1 }, function (list) {
    let str = "";
   
    for (let i = 0; i < 5; i++) {
    	let [a,b] = list[i].discussionRoomUrl.split('?');// 주소값 파라미터에서 "?"뒤 값들을 자바스크립트 변수 저장
    	let code = b;	
        str += `
	<tr><div class="d-flex justify-content-between">
	<td>${i+1}</td>	
	<td><a href="/board/detail?itmsNm=${list[i].stockName}&${code}">${list[i].stockName}</a></td>
	`
        if (list[i].dayRange.indexOf('-')>=0) {
            str += `
            <td style='color:blue'>${list[i].price}</td>
            <td style='color:blue'>${list[i].diffAmount}</td>`
        } else if (list[i].diffAmount == 0) {
            str += `
            <td>${list[i].price}</td>
            <td>${list[i].diffAmount}</td>`
        } else {
            str += `
            <td style='color:red'>${list[i].price}</td>
            <td style='color:red'>${list[i].diffAmount}</td>`
        }
        str += `<td>${list[i].dayRange}</td></div></tr>`
    }
    $("#main_stockList").html(str);
})

