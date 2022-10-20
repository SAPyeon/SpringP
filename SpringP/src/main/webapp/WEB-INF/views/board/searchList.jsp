<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>searchList</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<!-- Bootstrap Javascript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
</head>
<style>
#paging{
	text-align:center;
	margin-bottom:10px;
}
#paging a{
	margin: 5px;
}
</style>
<body>
	<div class="container">
		<%@ include file="../header.jsp"%>

		<div class="row main">
			<div class="board col-xs-12">
				<h1>증권목록</h1>

				<div id="stockListTable">
					<table class="table table-bordered table table-hover">
					<tr>
					<th>종목코드</th>
					<th>종목명</th>
					<th>기준일자</th>
					<th>종가</th>
					<th>등락률</th>
					<th>시가</th>
					<th>거래량</th>
					<th>시가총액</th>
					
					</tr>
						<c:forEach items="${stock}" var="stock">
							<tr>
								<td>${stock.srtnCd}</td>
								<td><a href="/board/detail?itmsNm=${stock.itmsNm}&code=${stock.srtnCd}">${stock.itmsNm}</a></td>
								<td>${stock.basDt}</td>
								<td class="numFormat">${stock.clpr}</td>
								<td>${stock.fltRt}</td>
								<td class="numFormat">${stock.mkp}</td>
								<td class="numFormat">${stock.trqu}</td>
								<td class="numFormat">${stock.mrktTotAmt}</td>
								
							</tr>
						</c:forEach>
					</table>
				</div>
				<div id="paging">
					 <input type="hidden" value="${paging.cri.pageNum}" id="currentPageNum">  
					 
					<!-- prev(이전)이 true이면 이전버튼 활성화 -->
					<c:if test="${paging.prev}">
						<a
							href="/board/searchList?pageNum=${paging.startPage-1}&amount=${paging.cri.amount}&search=${paging.cri.search}">이전</a>
					</c:if>
					<c:forEach begin="${paging.startPage}" end="${paging.endPage}"
						var="num">
						<a
							href="/board/searchList?pageNum=${num}&amount=${paging.cri.amount}&search=${paging.cri.search}" class="pageNumber">${num}</a>
					</c:forEach>
					<!-- next(다음)이 true이면 다음버튼 활성화 -->
					<c:if test="${paging.next}">
						<a
							href="/board/searchList?pageNum=${paging.endPage+1}&amount=${paging.cri.amount}&search=${paging.cri.search}">다음</a>
					</c:if>
				</div>
			</div>
		</div>
		<%@ include file="../footer.jsp"%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="../resources/js/board_searchList.js"></script>
</body>
</html>