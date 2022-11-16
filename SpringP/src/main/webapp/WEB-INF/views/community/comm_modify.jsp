<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
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
				<h1>글쓰기</h1>
				<form action="/community/modify" method="post" id="writeFrom">
					<div class="mb-3">
						<label class="col-sm-2">제목</label> <input type="text" name="title"
							class="input-form col-sm-12" placeholder="title...." value="${detail.title}">
					</div>
					<div class="mb-3">
						<label class="col-sm-2">내용</label>
						<div id="editor" contenteditable="true">${detail.content}</div>
					</div>
					<div class ="mb-3">
						<button type="submit" class="btn btn-primary btn-sm"
							id="btn_submit">제출</button>
					</div>
					<input type="hidden" name="content" id="content">
					<input type="hidden" name="bno" value="${detail.bno}">
				</form>
			</div>
		</div>
		<%@ include file="../footer.jsp"%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="../resources/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="../resources/js/board_write.js"></script>

</body>
</html>