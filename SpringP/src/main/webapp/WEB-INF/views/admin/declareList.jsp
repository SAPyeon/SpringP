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
				<h1>신고리스트</h1>
				<div id="memberListTable">
					<table class="table table-bordered table table-hover">
						<thead class="thead-light">
							<tr>
								<th scope="col">댓글번호</th>
								<th scope="col">신고자 아이디</th>
								<th scope="col">신고사유</th>
								<th scope="col">댓글내용</th>
								<th scope="col">신고일</th>
								<th scope="col">댓글삭제</th>
								<th scope="col">댓글유지</th>
							</tr>
						</thead>
						<c:forEach items="${declareReplList}" var="declareReplList" varStatus="status">
							<tr>
								<td>${declareReplList.rno}</td>
								<td>${declareReplList.id}</td>
								<td>${declareReplList.reason}</td>
								<td>${declareReplList.reply.reply}</td>
								<td>${declareReplList.regdate}</td>
								<td><button type="button" class="btn_replDelete btn btn-secondary btn-sm" data-rno="${declareReplList.rno}">삭제</button></td>
								<td><button type="button" class="btn btn-secondary btn-sm" class="btn_repl">유지</button></td>
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
	<script type="text/javascript">
	$(".btn_replDelete").on("click",function(){
		console.log("rno = "+$(this).data("rno"))
		const data = {rno:$(this).data("rno")}
		if(confirm("댓글을 삭제하겠습니까?")){
			$.ajax({
				type : "delete",
				url : "/replies/delete",
				data : JSON.stringify(data),
				contentType : "application/json; charset=utf-8",
				success : function() {
					declareReplDelete(data);
				}
			})
		}
		
	})
	function declareReplDelete(data){
		$.ajax({
			type : "delete",
			url : "/admin/declareReplDelete",
			data : JSON.stringify(data),
			contentType : "application/json; charset=utf-8",
			success : function() {
				alert('댓글이 삭제되었습니다.')
				location.reload();
			}
		})
	}
	</script>	
</body>
</html>