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

//const Rtotal = document.getElementById('Rtotal')

const list_element = document.querySelector('#reply_content');
const pagination_element = document.querySelector("#pagination");


let current_page = 1; //현재페이지
let amount = 5; // 페이지에 나타낼 갯수
let startPage = 1;
let endPage = startPage + 9;


$("#btn_reply").on("click",function(){
	const replyVal = $("#reply").val();
	console.log(replyVal);
	if($("#loginId").val()!=''){
		
			add({bno:bnoVal, reply:replyVal, name:loginName, id:$("#loginId").val()});
		
	}else{
		alert('로그인 후 이용가능합니다.');
	}
})

// 댓글 추가하는 함수
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
		//페이징처리
		let total = Math.ceil(data.length / amount);
		console.log("리뷰총갯수="+total)
		DisplayList(data, list_element, amount, current_page);
		SetupPagination(data, pagination_element, amount, current_page, startPage);
		if(data.length===0){
			$("#prev").css("display","none")
			$("#next").css("display","none")
		}
		if(data!=''){
			$("#next").on("click", function (e) {
				startPage = startPage+10;
		        if(startPage > total){
		            startPage = startPage -10;
		        }else{
		        	current_page = startPage;
		        }
		        DisplayList(data, list_element, amount, current_page);
		        SetupPagination(data, pagination_element, amount, current_page,startPage);
		        
			})
			$("#prev").on("click",function(e){
				if(startPage === 1){
					e.preventDefault();
				}else{
					startPage = startPage-10;
					if(startPage<1){
						startPage = 1;
						DisplayList(data, list_element, amount, current_page);
					}else{
						endPage= startPage+9
						DisplayList(data, list_element, amount, endPage);
					}
					current_page = endPage;
					SetupPagination(data, pagination_element, amount, current_page,startPage);	
				}
				
			})
			}
		
		clickAndDelete();
		clickAndModify();
		showLoginPopup();
		
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
							<div><label>작성자 : </label>
								<span>${data.name}</span>
								<span><a href="#" style="font-size: 13px;" class="declaration" data-rno="${data.rno}">신고하기</a></span>
							</div>
							<div>
								<div class="my-3" style="font-size: 18px;">${data.reply}</div>
								<div style="font-size: 13px;">${data.regdate}</div>
								<button type="button" data-rno="${data.rno}" data-id="${data.id}" class="btn_replmodify btn btn-default"
									style="font-size: 13px;">수정하기</button>
								<button type="button" data-rno="${data.rno}" data-id="${data.id}" class="btn_repldelete btn btn-default"
									style="font-size: 13px;">삭제하기</button>
							</div>
							`;
					e.target.parentNode.parentNode.innerHTML = str;
					clickAndModify();
					showLoginPopup();
				})
		})
	})
}

// 신고 팝업창 생성
function showLoginPopup(){
	$(".declaration").on("click",function(e){
		if($("#loginId").val()!=''){
			window.name="communityDetail";
			let uri = "/community/replDeclaration?rno="+$(this).data("rno")
			// 사용자가 사용하기 편하게끔 팝업창으로 띄어준다.
			window.open(uri, "declare", "width=500, height=600, top=100, left=200");
		}else{
			alert('로그인 후 이용가능합니다.')
		}
		
	})
	 
  
}


//댓글 페이징처리

//리스트를 보여주는 함수
function DisplayList(items, wrapper, amount, page) {
    wrapper.innerHTML = "";
    page--; // 페이지번호를 1 다운

    let start = amount * page; //현재페이지:(2-1)*5 = 5
    let end = start + amount; // 5+5 = 10

    let paginatedItems = items.slice(start, end); //6-10번까지 불러오기
    //데이터보여지는 곳 - GETJSON 데이터는 여기로
    for(let list of paginatedItems){
		str=` <div class="border col col-sm-8 align-self-center mb-2" data-rno="${list.rno}">
        <div><label>작성자 : </label>
            <span>${list.name}</span>
            <span><a href="#" style="font-size: 13px;" class="declaration" data-rno="${list.rno}">신고하기</a></span>
        </div>
        <div>
            <div class="my-3" style="font-size: 18px;">${list.reply}</div>
            <div style="font-size: 13px;">${list.regdate}</div>
            <button type="button" data-rno="${list.rno}" data-id="${list.id}" class="btn_replmodify btn btn-default"
                style="font-size: 13px;">수정하기</button>
            <button type="button" data-rno="${list.rno}" data-id="${list.id}" class="btn_repldelete btn btn-default"
                style="font-size: 13px;">삭제하기</button>
        </div>
    </div>
		`;
	$("#reply_content").append(str);
	}
}


//버튼 안에 숫자를 넣는 함수
function SetupPagination(items, wrapper, amount, current_page , startPage) {
	if(items.length != 0){
    	wrapper.innerHTML = "";
        let page_count = Math.ceil(items.length / amount); 
        let PageGroup = Math.ceil(current_page / 10) * 10; 
        let endPage = startPage + 9;
        let RealEnd = page_count; // 21
        if (RealEnd < endPage) {
            endPage = RealEnd ;
        }
        console.log("startPage = "+startPage);
        if(startPage===1){
        	$("#prev").css("display","none")
        }else{
        	$("#prev").css("display","")
        }
        if(endPage === RealEnd){
        	$("#next").css("display","none")
        }else{
        	$("#next").css("display","")
        }
        
        for (let i = startPage; i < endPage+1; i++) {
            let btn = PaginationButton(i, items);
            wrapper.appendChild(btn);
        }
        
	}
}

//버튼만들어 페이징 하는 함수
function PaginationButton(page, items) {
    let button = document.createElement('button');
    button.innerText = page;
    button.classList.add("btn_pa")
    if (current_page == page) {
            button.classList.add('re')
    }
    button.addEventListener("click", function () {
        //button.classList.remove('re')
        current_page = page;           
        DisplayList(items, list_element, amount, current_page);
        let current_btn = document.querySelector('.re');
        current_btn.classList.remove('re');
        button.classList.add('re');
    })
    return button;
}



