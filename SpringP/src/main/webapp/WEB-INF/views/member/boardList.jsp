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
				<h1>내가쓴 글</h1>
				<div id="memberComuunityListTable">
					<h3>커뮤니티 게시판 글</h3>
					<table class="table table-bordered table table-hover">
						<thead class="thead-light">
							<tr>
								<th scope="col">글번호</th>
								<th scope="col">제목</th>
								<th scope="col">내용</th>
								<th scope="col">조회수</th>
								<th scope="col">등록일</th>
								<th scope="col">게시글삭제</th>
							</tr>
						</thead>
						<c:forEach items="${memCommList}" var="memCommList" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td><a href="/community/detail?bno=${memCommList.bno}">${memCommList.title}</a></td>
								<td>${memCommList.content}</td>
								<td>${memCommList.cnt}</td>
								<td>${memCommList.regdate}</td>
								<td><button type="button" data-bno="${memCommList.bno}" class="btn_boardDelete">삭제</button></td>
								
							</tr>
						</c:forEach>
					</table>
				</div>
				<div id="memberComuunityReplyListTable">
					<h3>커뮤니티 게시판 글</h3>
					<table class="table table-bordered table table-hover">
						<thead class="thead-light">
							<tr>
								<th scope="col">글번호</th>
								<th scope="col">내용</th>
								<th scope="col">등록일</th>
								<th scope="col">게시판보기</th>
								<th scope="col">댓글삭제</th>
							</tr>
						</thead>
						<c:forEach items="${memCommReplyList}" var="memCommReplyList" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td>${memCommReplyList.reply}</td>
								<td>${memCommReplyList.regdate}</td>
								<td><a href="/community/detail?bno=${memCommReplyList.bno}">해당글보기</a></td>
								<td><button type="button" data-rno="${memCommReplyList.rno}" class="btn_replDelete">삭제</button></td>
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
	<script type="text/javascript" src="../resources/js/member_boardList.js"></script>
</body>
</html>