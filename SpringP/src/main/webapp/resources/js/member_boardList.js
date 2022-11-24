/**
 * 
 */

$("#btn_likeDelete").on("click",function(){
		console.log($("#srtnCd").val())
		console.log($("#loginId").val())// post로 변경
		data={srtnCd:$("#srtnCd").val(), id:$("#loginId").val()}
		if(confirm("해당종목을 삭제하시겠습니까?")){
			likeDelete(data);	
		}
		location.href="/member/likeList";
		
	})
	// 즐겨찾기 삭제
	function likeDelete(data) {
		console.log(data)
		$.ajax({
			type : "delete",
			url : "/likeDelete",
			data : JSON.stringify(data),
			contentType : "application/json; charset=utf-8",
			success : function() {
				alert('즐겨찾기에 삭제되었습니다.')
				$("#star").css("color", "grey");
			}
		})
	}


