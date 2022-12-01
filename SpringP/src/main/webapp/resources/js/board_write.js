/**
 * 
 */

const ckeditor_config = {
		resize_enaleb : false,
		enterMode:CKEDITOR.ENTER_BR,
		shiftEnterMode : CKEDITOR.ENTER_P,
		filebrowserUploadUrl : "/uploadAjaxAction",
		height : 500
}

CKEDITOR.replace("editor",ckeditor_config);

const Form = document.querySelector("#writeFrom");
const btn = document.querySelector("#btn_submit");
const content = document.querySelector("#content")

btn.addEventListener("click",function(e){
	e.preventDefault();
	const titleVal = CKEDITOR.instances.editor.getData();
	content.value = titleVal;
	Form.submit();
})

$("#chk_notice").on("change",function(){
	if($(this).is(":checked")){
		$("#writeFrom").attr("action","/admin/notice")
	}else{
		$("#writeFrom").attr("action","/community/write")
	}
	console.log($("#writeFrom").attr("action"))
})



