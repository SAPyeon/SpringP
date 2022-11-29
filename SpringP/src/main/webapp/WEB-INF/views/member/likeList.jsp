<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

<style type="text/css">
.increase{
	color:red;
}
.decrease{
	color:blue;
}
.decrease:hover{
	color:blue;
}
</style>		
	
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp"%>
		<div class="row main">
			<div class="board col-xs-12">
				<h1>즐겨찾기목록</h1>
				<div id="stockListTable">
					<table class="table table-bordered table table-hover">
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
								<th scope="col"></th>
							</tr>
						</thead>
						<c:forEach items="${likeList}" var="likeList" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td><a href="/board/detail?itmsNm=${likeList.itmsNm}&code=${likeList.srtnCd}">${likeList.itmsNm}</a></td>
								<td class="priceToday">현재가</td>
								<td class="diffAmount">${likeList.diffAmount}</td>
								<td class="dayRange">${likeList.dayRange}</td>
								<td>${likeList.parValue}</td>
								<td>${likeList.marketCap}</td>
								<td>${likeList.numberOfListedShares}</td>
								<td>${likeList.foreignOwnRate}</td>
								<td>${likeList.turnover}</td>
								<td>${likeList.per}</td>
								<td><button type="button" class="btn_likeDelete" data-code="${likeList.srtnCd}">삭제</button></td>
								<input type="hidden" value="${likeList.itmsNm}" class="itmsNm">
								<input type="hidden" value="${likeList.srtnCd}" class="srtnCd">
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<%@ include file="../footer.jsp"%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="../resources/js/member_likeList.js"></script>	
	
</body>
</html>