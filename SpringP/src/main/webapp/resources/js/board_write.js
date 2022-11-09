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
console.log('lilil')
const ckeditor_config = {
		resize_enaleb : false,
		enterMode:CKEDITOR.ENTER_BR,
		shiftEnterMode : CKEDITOR.ENTER_P,
		filebrowserUploadUrl : "/uploadAjaxAction"
}

CKEDITOR.replace("editor",ckeditor_config);

const Form = document.querySelector("form");
const btn = document.querySelector("#btn_submit");
const content = document.querySelector("#content")

btn.addEventListener("click",function(e){
	e.preventDefault();
	const titleVal = CKEDITOR.instances.editor.getData();
	content.value = titleVal;
	Form.submit();
})































