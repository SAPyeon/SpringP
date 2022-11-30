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
							<label>작성자</label> 
							<input type="text" value="${declUser.id}" name="id" readonly> 
							<input type="hidden" value="${declUser.rno}" name="rno">
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
	    $("#btn_declare").click( function(e) {
	    	e.preventDefault();
	    	
	    	console.log($("input[name=rno]").val())
	    	console.log($("input[name=id]").val())
	    	console.log($("textarea[name=reason]").val())
	    	
	    	
	    	const formData = new FormData();
	    	formData.append("rno",$("input[name=rno]").val())
	    	formData.append("id",$("input[name=id]").val())
	    	formData.append("reason",$("textarea[name=reason]").val())
	    	
	         window.opener.document.location.href = window.opener.document.URL;
	    	if(confirm("신고하시겠습니까?")){
	    		$.ajax({
		    		type : "post",
					url : "/community/replDeclaration",
					data : formData, // 데이터를 formData메서드를 사용함
					contentType: false, //자바에서 충돌을 방지하기 위해 contentType과 processData를 false로 함
					processData: false,
					success:function(result){
						alert("신고되었습니다.")
							window.close();	
					}
		    	})
	    	}
	     })
	</script>
</body>

</html>

