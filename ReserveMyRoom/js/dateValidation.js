function checkForm() {
// Fetching values from all input fields and storing them in variables.
var checkInDate = document.getElementsByName("checkInDate")[0].value;
var checkOutDate = document.getElementsByName("checkOutDate")[0].value;


//Check input Fields Should not be blanks.
	if (checkInDate == '' || checkOutDate == '') {
		alert("Please Fill All Fields");
		document.getElementById("errorText").innerHTML = '';
	} 
	else {
		//Notifying error fields
		var username1 = document.getElementById("username");
		var password1 = document.getElementById("password");
		var email1 = document.getElementById("email");
		var website1 = document.getElementById("website");
		//Check All Values/Informations Filled by User are Valid Or Not.If All Fields Are invalid Then Generate alert.
		if (checkInDate < checkOutDate) {
			//Submit Form When All values are valid.
			document.getElementById("myForm").submit();
		}
		else if(checkInDate == checkOutDate){
			document.getElementById("errorText").innerHTML = 'CheckInDate and CheckOutDate cannot be the same. Please verify the input and try again.';
		}
		else {
			document.getElementById("errorText").innerHTML = 'CheckInDate cannot be less than the CheckOutDate. Please verify the input and try again.';
		}
	}
}