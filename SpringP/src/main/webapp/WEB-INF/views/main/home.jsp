<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page session="false"%> --%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<title>HOME</title>
<!-- Bootstrap Javascript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
<style type="text/css">
.img {
	height: 460px;
	width: 1296px;
}

.box_style {
	padding: 15px;
	font-size: 20px;
	border: 1px solid rgba(187,187,187,.7);
}

h3 {
	font-weight: bold;
	font-size: 35px;
}
table{
	width:100%;
}
</style>
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp"%>

		<div class="row main">
			<div class="board col-xs-12">
				
				<div class="center">
					<div>
						<img alt="stock"
							src="https://cdn.pixabay.com/photo/2018/08/10/15/43/entrepreneur-3597096_960_720.jpg"
							class="img">
					</div>
					<div>
						<img alt="stock"
							src="https://cdn.pixabay.com/photo/2014/09/15/17/23/euro-447214_960_720.jpg"
							class="img">
					</div>
					<div>
						<img alt="stock"
							src="https://cdn.pixabay.com/photo/2017/04/20/09/09/businessman-2245098_960_720.jpg"
							class="img">
					</div>
				</div>
				<div class="d-flex justify-content-between">
					<div class="col-sm-5 my-3 box_style">
						<div>
							<h3>최신글</h3>
							<div class="text-end">
								<a href="/community/list">더보기</a>
							</div>
							<hr>
						</div>
						<div>
							<ol>
								<c:forEach items="${commList}" var="list" begin="0" end="4">
									<li><div class="d-flex bd-highlight mb-1">
											<span class="me-auto p-2 bd-highlight"><a href="/community/detail?bno=${list.bno}">${list.title}</a></span>
											<span class="p-2 bd-highlight">${list.name}</span>
										</div></li>
								</c:forEach>
							</ol>
						</div>
					</div>

					<div class="col-sm-5 my-3 box_style">
						<div>
							<h3>공지사항</h3>
							<div class="text-end">
								<a href="/community/list">더보기</a>
							</div>
							<hr>
						</div>
						<div>
							<ol>
								<c:forEach items="${notice}" var="notice" begin="0" end="4">
									<li><div class="d-flex bd-highlight mb-1">
											<span class="me-auto p-2 bd-highlight"><a href="/community/detail?bno=${notice.bno}">${notice.title}</a></span>
											<span class="p-2 bd-highlight">${notice.name}</span>
										</div></li>
								</c:forEach>
							</ol>
						</div>

					</div>

				</div>
				<div class="my-5">
					<div class="col-sm-7 box_style">
						<div>
							<h3>인기종목</h3>
							<div class="text-end">
								<a href="/board/list">더보기</a>
							</div>
							<hr>
							<div class="d-flex justify-content-center">
								<table id="main_stockList">

								</table>
							</div>
						</div>

					</div>
				</div>
				<P>The time on the server is ${serverTime}.</P>
			</div>
		</div>
		<%@ include file="../footer.jsp"%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
	<script type="text/javascript" src="../resources/js/home.js"></script>
</body>
</html>
