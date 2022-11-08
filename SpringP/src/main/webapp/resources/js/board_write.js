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
		filebrowserUploadUrl : "/uploadAjaxAction"
}

CKEDITOR.replace("editor",ckeditor_config);
