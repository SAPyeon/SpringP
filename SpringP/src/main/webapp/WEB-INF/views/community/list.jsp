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
 .notice{
 	background:#ced4da;
 }
 </style>
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp"%>
		<div class="row main d-flex justify-content-center">
			<div class="board col-sm-10">
				<h1>리스트</h1>
				<div  class="row justify-content-end">
					<div class="col-auto">
						<button class="btn btn-primary mb-2" id="btnWrite">글쓰기</button>
					</div>
				</div>
				<table  class="table table-bordered table table-hover">
					<thead class="thead-light">
						<tr>
							<th scope="col">#</th>
							<th scope="col">제목</th>
							<th scope="col">작성자</th>
							<th scope="col">조회수</th>
							<th scope="col">등록일</th>
						</tr>
					</thead>
					<c:forEach items="${notice}" var="notice" varStatus="status">
						<tr class="notice">
							<td scope="row">#</td>
							<td><a href="/community/detail?bno=${notice.bno}">${notice.title}</a></td>
							<td>관리자</td>
							<td></td>
							<td>${notice.regdate}</td>
						</tr>
					</c:forEach>
					
					<c:forEach items="${commlist}" var="list" varStatus="status">
						<tr>
							<td scope="row">${status.count}</td>
							<td><a href="/community/detail?bno=${list.bno}">${list.title}</a></td>
							<td>${list.name}</td>
							<td>${list.cnt}</td>
							<td>${list.regdate}</td>
						</tr>

					</c:forEach>
				</table>
				<%-- ${paging} --%>
				<!-- prev(이전)이 true이면 이전버튼 활성화 -->
				<div   class="d-flex justify-content-center">
					<c:if test="${paging.prev}">
						<a
							href="/community/list?pageNum=${paging.startPage-1}&amount=${paging.cri.amount}">이전</a>
					</c:if>
					<!-- begin(1)이 end(10)될 동안 반복 (1이 6이 될 동안 반복) -->
					<c:forEach begin="${paging.startPage}" end="${paging.endPage}"
						var="num">
						<a
							href="/community/list?pageNum=${num}&amount=${paging.cri.amount}">${num}</a>
					</c:forEach>
					<!-- next(다음)이 true이면 다음버튼 활성화 -->
					<c:if test="${paging.next}">
						<a
							href="/community/list?pageNum=${paging.endPage+1}&amount=${paging.cri.amount}">다음</a>
					</c:if>
				</div>
			</div>
		</div>
		<%@ include file="../footer.jsp"%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
		$("#btnWrite").on("click", function() {
			if($("#loginId").val()!=''){
				location.href = "/community/write"	
			}else{
				alert('로그인 후 이용가능합니다.')
			}
			
		})
	</script>
</body>
</html>