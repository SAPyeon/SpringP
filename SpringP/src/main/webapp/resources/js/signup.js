/**
 * 
 */

const btn_signup = document.querySelector("#btn_signUp")
const form = document.getElementById("needs-validation");
btn_signup.addEventListener("click", function(event) {
	if (form.checkValidity() == false) {
		event.preventDefault();
		event.stopPropagation();
		form.classList.add("was-validated");
	}else{
		form.submit();
	}
})