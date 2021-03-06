// JavaScript Document
onload = function(){
	newForm();
}
var newForm = function(){
	//start to create a new user
	var registerForm = document.getElementById('register-form');
	registerForm.onsubmit = function(){
			var id = document.getElementById('id').value;
			var password = document.getElementById('password').value;
			var name = document.getElementById('name').value;
			var characteristic = document.getElementsByName('characteristic');
			var chArray = new Array(characteristic.length);
			for(var i=0; i<characteristic.length; i++){
			if(characteristic[i].checked) chArray[i] = 1;
			else chArray[i] = 0;
		}
		var reg = /\w/;
		
		if(!id.match(reg)){
			alert('Id is not accepted.');
			return false;
		}
		
		if(!password.match(/\w{4,16}/)){
			alert('The length of password must be 4 to 16.');
			return false;
		}
		ajaxRequest('POST', encodeURI('user'), {'Content-Type': 'application/json;charset=UTF-8'}, 
				'{ "id":"' + id + '", "password":"' + password +'", "username":"' + escape(name) + '","preference":' 
				+ '[' + chArray + '] }', 
				function(status, headers, body) { //success
				
					alert('Register successfully. Back to login.');
					history.back();
				
				}, function(status, headers, body) { //error
					switch(status){
						case 400:
							alert('The id has been registered.');
							break;
						case 406:
							alert('The data is wrong, lease register again.');
							break;
						
					}
				}, null);	
				
		return false;

		}
		// end to create a new user
		var backBtn = document.getElementById('back-btn');
		backBtn.onclick = function(){
		history.back();
		}	

}

