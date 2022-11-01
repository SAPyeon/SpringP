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
</head>
<body>

	<div class="container">
		<%@ include file="../header.jsp"%>

		<div class="row main">
			<div class="board col-xs-12">
				<h1>메인</h1>
				<c:choose>
					<c:when test="${loginName==null}">
						<a href="/member/login">로그인하기</a>
					</c:when>
					<c:otherwise>
						<p>Welcome! ${loginName}님</p>
						<a href="/member/logout">로그아웃하기</a>
					</c:otherwise>
				</c:choose>
				<a href="/board/list">주가리스트보기</a>
				<a href="/board/info">상품글보기</a>
				<a href="/board/write">상품글쓰기</a>
				<P>The time on the server is ${serverTime}.</P>
			</div>
		</div>
		<%@ include file="../footer.jsp"%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="../resources/js/home.js"></script>

</body>
</html>
