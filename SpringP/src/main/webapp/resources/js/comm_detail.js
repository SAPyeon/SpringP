/**
 * 
 */
const inputBnoVal = $("#bno").val();
console.log("bno = "+inputBnoVal)

deleteBoard();

function deleteBoard(){
	$("#delete").on("click",function(){
		if(confirm("삭제하시겠습니까?")){
			$.ajax({
				type:"post",
				url:"/community/delete?bno="+inputBnoVal,
				success:function(){
					alert('삭제되었습니다.')
					location.href="/community/list";
				}
			})	
		}
		
	})	
}



