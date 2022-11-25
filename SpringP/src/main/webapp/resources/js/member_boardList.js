/**
 * 
 */
//
//$("#btn_likeDelete").on("click",function(){
//		console.log($("#srtnCd").val())
//		console.log($("#loginId").val())// post로 변경
//		data={srtnCd:$("#srtnCd").val(), id:$("#loginId").val()}
//		if(confirm("해당종목을 삭제하시겠습니까?")){
//			likeDelete(data);	
//		}
//		location.href="/member/likeList";
//		
//	})
//	// 즐겨찾기 삭제
//	function likeDelete(data) {
//		console.log(data)
//		$.ajax({
//			type : "delete",
//			url : "/likeDelete",
//			data : JSON.stringify(data),
//			contentType : "application/json; charset=utf-8",
//			success : function() {
//				alert('즐겨찾기에 삭제되었습니다.')
//				$("#star").css("color", "grey");
//			}
//		})
//	}

$(".btn_boardDelete").on("click",function(){
	console.log("bno = "+$(this).data("bno"))
	
	const data = {bno:$(this).data("bno")}
	if(confirm("게시글을 삭제하겠습니까?")){
		$.ajax({
			type : "post",
			url : "/member/boardDelete",
			data : JSON.stringify(data),
			contentType : "application/json; charset=utf-8",
			success : function() {
				alert('게시글이 삭제되었습니다.')
				location.reload();
			}
		})
	}
	
})

$(".btn_replDelete").on("click",function(){
	console.log("rno = "+$(this).data("rno"))
	
	const data = {rno:$(this).data("rno")}
	if(confirm("게시글을 삭제하겠습니까?")){
		$.ajax({
			type : "delete",
			url : "/replies/delete",
			data : JSON.stringify(data),
			contentType : "application/json; charset=utf-8",
			success : function() {
				alert('댓글이 삭제되었습니다.')
				location.reload();
			}
		})
	}
	
})


