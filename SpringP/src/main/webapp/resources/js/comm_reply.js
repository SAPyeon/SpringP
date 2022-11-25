/**
 * 
 */
// url 파라미터 값에서 bno 가져오기

const urlParams = new URL(location.href).searchParams;

const bnoVal = urlParams.get('bno');

console.log(bnoVal)
list(bnoVal)

const loginName = $("#loginName").val();
	
console.log(loginName);
console.log($("#loginId").val())	

$("#btn_reply").on("click",function(){
	
	const replyVal = $("#reply").val();
	console.log(replyVal);
	if($("#loginId").val()!=''){
		add({bno:bnoVal, reply:replyVal, name:loginName, id:$("#loginId").val()});
	}else{
		alert('로그인 후 이용가능합니다.');
	}

})

//댓글 추가하는 함수
function add(reply){
	$.ajax({
		type:"post",
		url:"/replies/new",
		data:JSON.stringify(reply),
		contentType:"application/json; charset=utf-8",
		success:function(result){
			if(result === "success"){
				alert("댓글쓰기 성공")
				location.reload();
			}
		}
			
	})
}
// 댓글리스트를 불러오는 함수
function list(bno){
	$.getJSON("/replies/"+bno+".json", function(data){
		for(let list of data){
			str=`<div class="border col col-sm-8 align-self-center" data-rno=${list.rno}>
				<div><label>작성자 : </label>${list.name}</div>
				<div class="col align-self-end">
				<a href="#">신고하기</a>
				</div>
				<div>
				<div id="">${list.reply}</div>
				<div>${list.regdate}</div>
				<button type="button" data-rno=${list.rno} data-id=${list.id} class="btn_replmodify">수정하기</button>
				<button type="button" data-rno=${list.rno} data-id=${list.id} class="btn_repldelete">삭제하기</button>
				</div>
				</div>
			`;
		$("#reply_content").append(str);
		}
		clickAndDelete();
		clickAndModify();
	})
	
}

// 버튼 클릭해서 리뷰 삭제하는 함수
function clickAndDelete(){
	$(".btn_repldelete").on("click",function(e){
		
		const rnoVal = $(this).data("rno");
		const ReplIdVal = $(this).data("id");
		const datacheck = {rno:rnoVal, id:ReplIdVal}
		if(confirm("리뷰를 삭제하시겠습니까?")){
			$.ajax({
				type:"delete",
				url:"/replies/delete",
				data : JSON.stringify(datacheck),
				contentType : "application/json;charset=utf-8",
				success: function(){	
						alert('삭제되었습니다.')
						location.reload();
				}
			})
		}		
	})

}
// 버튼 클릭해서 리뷰 수정하는 함수
function clickAndModify(){
	$(".btn_replmodify").on("click",function(){
		const rnoModiVal = $(this).data("rno")
		let str = `
				<div><input type="text" id="replyModi"></div>
				<button type="button" data-rno=${rnoModiVal} id="btn_replmodify">수정하기</button>
				<button type="button" data-rno=${rnoModiVal} id="btn_replmodify_cancel">취소하기</button>
				</div>`
		$(this).parent().html(str)
		
		$("#btn_replmodify").on("click",function(){
			const rnoModiVal = $(this).data("rno")
			console.log("수정rno = "+rnoModiVal)
			console.log("수정할 내용 = "+$("#replyModi").val())
			data={rno:rnoModiVal, reply: $("#replyModi").val()}
			if(confirm("리뷰를 수정하시겠습니까?")){
				$.ajax({
					type:"post",
					url:"/replies/modify",
					data : JSON.stringify(data),
					contentType : "application/json;charset=utf-8",
					success: function(){	
							alert('수정완료')
							location.reload();
					}
				})
			}
		})
		$("#btn_replmodify_cancel").on("click",function(e){
			const rnoCancelVal = $(this).data('rno')
			console.log("취소rno = "+ rnoCancelVal)
			dataS={rno:rnoCancelVal}
			$.getJSON("/replies/select",dataS, function(data){
				console.log(data)
					let str=`
							<div><label>작성자 : </label>${data.name}</div>
							<div class="col align-self-end">
							<a href="#">신고하기</a>
							</div>
							<div>
							<div id="">${data.reply}</div>
							<div>${data.regdate}</div>
							<button type="button" data-rno=${data.rno} data-id=${data.id} class="btn_replmodify">수정하기</button>
							<button type="button" data-rno=${data.rno} data-id=${data.id} class="btn_repldelete">삭제하기</button>									
							</div>
							`;
					e.target.parentNode.parentNode.innerHTML = str;
					clickAndModify()
				})
		})
	})
}


