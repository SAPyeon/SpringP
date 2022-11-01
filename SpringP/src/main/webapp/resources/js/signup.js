/**
 * 
 */
"use strict";
window.addEventListener("load", function() {
	var form = document.getElementById("needs-validation");
	form.addEventListener("click", function(event) {
		if (form.checkValidity() == false) {
			event.preventDefault();
			event.stopPropagation();
			form.classList.add("was-validated");
		}else{
			form.submit();
		}
	}, false);
}, false);
