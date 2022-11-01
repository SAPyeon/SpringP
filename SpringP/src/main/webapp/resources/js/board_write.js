/**
 * 
 */
ClassicEditor
	.create( document.querySelector( '#editor' ), {
		language: "ko",
       // Enable the CKFinder button in the toolbar.
	    toolbar: [ 'ckfinder'],
        ckfinder: {
        		// Upload the images to the server using the CKFinder
				// QuickUpload command.
        		uploadUrl: '/uploadAjaxAction'
        			// Define the CKFinder configuration (if necessary).
        },})
	.catch( error => {
		console.error( error );
	} );
