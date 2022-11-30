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
</head>
<body>
<div class="container">
		<%@ include file="../header.jsp"%>
		<div class="row main">
			<div class="board col-xs-12">
				<h1>멤버리스트</h1>
				<div id="memberListTable">
					<table class="table table-bordered table table-hover">
						<thead class="thead-light">
							<tr>
								<th scope="col">글번호</th>
								<th scope="col">아이디</th>
								<th scope="col">이름</th>
								<th scope="col">전화번호</th>
								<th scope="col">포인트</th>
								<th scope="col">관리자여부</th>
							</tr>
						</thead>
						<c:forEach items="${memberList}" var="memberList" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td><a href="/admin/memberDetail?id=${memberList.id}">${memberList.id}</a></td>
								<td>${memberList.name}</td>
								<td>${memberList.phone}</td>
								<td>${memberList.point}</td>
								<td>${memberList.authority}</td>
								
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
	
</body>
</html>