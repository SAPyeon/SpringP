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
#content{min-height: 400px;}
</style>
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp"%>
		<div class="row main d-flex flex-column justify-content-center">
			<h1>글</h1>
			<input type="hidden" value="${loginName}" id="loginName">
			<input type="hidden" value="${detail.bno}" id="bno">
			<div class="border mb-3 col col-sm-8 align-self-center my-3" id="title">${detail.title}</div>
			<div class="border mb-3 col col-sm-8 align-self-center" id="title"><label>작성자 : </label><label>${detail.name}(${detail.id})</label></div>
			<div class="border mb-3 col col-sm-8 align-self-center p-3" id="content">${detail.content}</div>
			<div style="text-align:right"><a href="/community/list" class="text-right">목록보기</a></div>
			<div style="text-align:right">
			<a href="/community/modify?bno=${detail.bno}" class="text-right">수정</a>
			<a href="javascript:void(0)" class="text-right" id="delete">삭제</a>
			</div>
			<div><label>댓글</label></div>
			<hr>
			<div class="row d-flex justify-content-center m-3">
				<textarea name="reply" class="col col-sm-7 align-self-center mr-2" id="reply"></textarea>
				<button type="submit" class="btn btn-primary col col-sm-1 mx-2" id="btn_reply">작성</button>
			</div>
			<div id="reply_content" class="row main d-flex flex-column justify-content-center"></div>
		</div>
		<%@ include file="../footer.jsp"%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script type="text/javascript" src="../resources/js/comm_detail.js"></script>
<script type="text/javascript" src="../resources/js/comm_reply.js"></script>
</body>
</html>