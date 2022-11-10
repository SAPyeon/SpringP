/**
 * 
 */

//  
//ClassicEditor
//	.create( document.querySelector( '#editor' ), {
//		language: "ko",
//		
//        ckfinder: {
//    		uploadUrl: '/uploadAjaxAction'
//    	},
//    	simpleUpload:
//        {
//            uploadUrl: "/uploadAjaxAction",
//            withCredentials: true,
//        }
//       // Enable the CKFinder button in the toolbar.
//	    
//    })
//    .then( editor => {
//		window.editor = editor;
//	} )
//	.catch( error => {
//		console.error( error );
//	} );

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































