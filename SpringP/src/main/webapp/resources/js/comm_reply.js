/**
 * 
 */
// url 파라미터 값에서 bno 가져오기

const urlParams = new URL(location.href).searchParams;

const bnoVal = urlParams.get('bno');

console.log(bnoVal)
list(bnoVal)

$("#btn_reply").on("click",function(){
	const replyVal = $("#reply").val();
	const loginName = $("#loginName").val();
	console.log(replyVal);
	console.log(loginName);
	
	add({bno:bnoVal,reply:replyVal,id:loginName});

})

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

function list(bno){
	$.getJSON("/replies/"+bno+".json", function(data){
		for(let list of data){
			str=`<div class="border col col-sm-8 align-self-center">
				<div><label>작성자 : </label>${list.name}</div>
				<div class="col align-self-end">
				<a href="#">신고하기</a>
				</div>
				<div>${list.reply}</div>
				<div>${list.regdate}</div>
				</div>
			`;
		$("#reply_content").append(str);
		}
		
	})
}




