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
		<div class="row main d-flex flex-column justify-content-center">
			<div class="text-center">
				<h1>신고</h1>
				<form action="/community/replDeclaration" method="post"
					class="border mb-3 my-3 col-xs-6 form-control" id="form_decl">
					<div class="d-flex flex-column bd-highlight mb-3">
						<div class="col col-xm-12">
							<label>작성자</label> <input type="text" value="${declUser.id}"
								name="id"> <input type="hidden" value="${declUser.rno}"
								name="rno">
						</div>
						<div>
							<div class="col col-xm-12">
								<label for="declar">신고사유</label>
								<div>
									<textarea name="reason" cols="30px" rows="10px"></textarea>
								</div>
							</div>
						</div>
					</div>
					
					<button class="btn btn-primary" id="btn_declare">신고하기</button>
				</form>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
	$(function() {
	    $("#btn_declare").click( function() {
	         $('#form_decl').submit();
	         setTimeout(function() {   
	             window.close();
	          }, 100);
	      });
	</script>	
</body>

</html>

