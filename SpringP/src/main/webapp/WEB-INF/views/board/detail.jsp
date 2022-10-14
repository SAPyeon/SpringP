<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<link rel="stylesheet" href="../resources/css/board_detail.css">
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp"%>
		<div class="row main">
			<div class="board col-xs-12">
				<input type="hidden" value="${codeName}" id="codeName">
				<h1>${codeName}</h1>
				<div id="chart">
					<canvas id="bar-chart" width="400" height="225"></canvas>
				</div>
				<div id="stockList"></div>
				<div>
					<a href="/board/list">목록보기</a>
				</div>
			</div>
		</div>

		<%@ include file="../footer.jsp"%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="../resources/js/board_detail.js"></script>
</body>
</html>