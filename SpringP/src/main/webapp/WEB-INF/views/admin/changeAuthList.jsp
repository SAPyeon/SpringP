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
				<h1>관리자 신청리스트</h1>
				<div id="memberListTable">
					<table class="table table-bordered table table-hover">
						<thead class="thead-light">
							<tr>
								<th scope="col">신청번호</th>
								<th scope="col">신청자 아이디</th>
								<th scope="col">기존번호</th>
								<th scope="col">변경번호</th>
								<th scope="col">변경사유</th>
								<th scope="col">신청일</th>
								<th scope="col">승인</th>
								<th scope="col">회원활동보기</th>
								<th scope="col">관리자승인</th>
							</tr>
						</thead>
						<c:forEach items="${AuthList}" var="AuthList" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td>${AuthList.id}</td>
								<td>${AuthList.beforeAuthno}</td>
								<td>${AuthList.afterAuthno}</td>
								<td>${AuthList.reasonChange}</td>
								<td>${AuthList.reasonChange}</td>
								<td>${AuthList.allow}</td>
								<td><button type="button" class="btn_gotoMemberDetail btn btn-secondary btn-sm" data-id="${AuthList.id}">회원활동보기</button></td>
								<td><button type="button" class="btn_allowAdmin btn btn-secondary btn-sm" data-id="${AuthList.id}">승인</button></td>
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
$(".btn_gotoMemberDetail").on("click",function(){
	location.href="/admin/memberDetail?id="+$(this).data("id")
})

$(".btn_allowAdmin").on("click",function(){
	data={id:$(this).data("id"),authority:true,authno:"20"}
	$.ajax({
		type:"post",
		url:"/admin/ChangeAllowAuth",
		data: JSON.stringify(data),
		contentType:"application/json; charset=utf-8",
		success:function(){
			alert("등급업")
		}
	})
})

</script>
</body>
</html>