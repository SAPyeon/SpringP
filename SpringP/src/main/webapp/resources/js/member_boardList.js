/**
 * 
 */

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
	if(confirm("댓글을 삭제하겠습니까?")){
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


