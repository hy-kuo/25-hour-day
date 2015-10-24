// JavaScript Document

onload = function(){
	initUser();
	initBtns();
};



var initUser = function(){
		//user to register			
		var registerBtn = document.getElementById('register');
		registerBtn.onclick = function(){
			var commentArea=document.getElementById('comment-area');
			addClass(commentArea,'content-hidden');

				location.href = 'register.html';
				//end of user to regiser
				}

				// handle login
				var loginForm = document.getElementById('login-form'); 
				loginForm.onsubmit =  function(){
				var id = document.getElementById('id').value;
				var password = document.getElementById('password').value;
				
				
				ajaxRequest ('POST',encodeURI('login'), {'Content-Type': 'application/json;charset=UTF-8'}, 
				'{' + '"id": "' + id + '", "password" :"' + password +'"' + '}',
				function(status,headers, body){//success callback
				
			
				
				var login = document.getElementById('login');
				addClass(login,'content-hidden');

				var logout = document.getElementById('logout');
					removeClass(logout, 'content-hidden');								
					checklogin();
				var memberArea = document.getElementById('member-area');
				removeClass(memberArea, 'content-hidden');

					
				},function(status,headers, body){//error callback
					alert('Id or password is wrong!');
					return;
				},null);
				return false;
				
				}
				//end of login
				
				//start logout
				var logoutForm = document.getElementById('logout-form');
				logoutForm.onsubmit = function(){
					ajaxRequest('PUT', encodeURI('login'), null, null
							, function(status, headers, body) { //success
								
								var logout = document.getElementById('logout');
									addClass(logout, 'content-hidden');		
									var login = document.getElementById('login');
									removeClass(login,'content-hidden');
									var memberArea = document.getElementById('member-area');
									addClass(memberArea, 'content-hidden');
							}, function(status, headers, body) { //error
									alert('ERROR!!!!');				
							}, null);
					checklogin();

					return false;
				}
				//end logout
				checklogin();

				

};
var checklogin = function(){
		ajaxRequest('GET', encodeURI('login'), {'Accept': 'application/json;'}, null
			, function(status, headers, body) { //success
				var user = eval('(' + body + ')');
				initRecommendationButton(user);
				initCreateButton(user);
			 	initOldActivityButton(user);

				var login = document.getElementById('login');
				addClass(login,'content-hidden');
				var username = document.getElementById('username');
				username.innerHTML =escapeHtml(unescape(user.username)) ;
				
				
				var logout = document.getElementById('logout');
					removeClass(logout, 'content-hidden');	
					getActivityList(user);
					var activityArea = document.getElementById('Activity-List-Area');
					removeClass(activityArea, 'content-hidden');
					var memberArea = document.getElementById('member-area');
					removeClass(memberArea, 'content-hidden');

		}, function(status, headers, body) { //error
				var login = document.getElementById('login');
				removeClass(login,'content-hidden');
				
			}, null);
	
	};

var initCreateButton = function(user){
	var createButton = document.getElementById('newActivity');
	createButton.onclick = function(){
		
		var createForm = document.getElementById('createActivity');
		hasClass(createForm, 'content-hidden') ? removeClass(createForm, 'content-hidden') 
			: addClass(createForm, 'content-hidden');

	}
	var createForm = document.getElementById('newidea');
	createForm.onsubmit = function(){
		//escape(formEl.elements['mail-title'].value)
		var characteristic = new Array(5);
		
		for(var j=0; j<5; j++){
		var ch = document.getElementsByName('characteristic' + j);
			
			for(var i=0; i<5; i++){
				if(ch[i].checked) characteristic[j] = ch[i].value;
				else characteristic[j] = 0;
			}
		}
		ajaxRequest ('POST',encodeURI('activity'), {'Content-Type': 'application/json;charset=UTF-8'}, 
				'{ "title":"' + escape(createForm.elements['activity-title'].value) + '",' +
			'"content":"' + escape(createForm.elements['activity-text'].value) + '",' +
			'"founder":"' + user.id + '",' +
			'"characteristic":' + '[' + characteristic + ']'  + ',' +
			'"scoredTimes":' +1 +
			'}',
				function(status,headers, body){//success callback
					var login = document.getElementById('login');
					addClass(login,'content-hidden');
					var createForm = document.getElementById('createActivity');
					removeClass(createForm, 'content-hidden');
					alert('Thanks for your idea !');
					location.href='home.html';
				},function(status,headers, body){//error callback
					alert('Something is wrong!');
					return;
				},null);
				return false;
				}
	
	};


/*var initRecommendationButton = function(user){
		var RecommendationButton = document.getElementById('someIdeas');
		RecommendationButton.onclick = function(){
		ajaxRequest('GET', encodeURI('recommendation/'+ user.id), {'Accept': 'application/json;'}, null
			, function(status, headers, body) {//success
			var recommendations = eval('(' + body + ')');
		if(recommendations.length == 0) {
			listEl.innerHTML = '<div class="list-no-activity">There is no recommendation here!</div>';
			return;
		}
				var listEl = document.getElementById('Activity-List-Area');
						var htmlFrag = '';
		for(var i = 0; i < recommendations.length; i++) {
			htmlFrag += '<div class="list-activity">'
					+ '<div class="list-activity-id">' + recommendations[i].id + '</div>' 
					// escape in case title and text contain chars sensitive to HTML parsing 
					+ '<div class="list-activity-title">' + escapeHtml(unescape(recommendations[i].title)) + '</div>' 
					+ '<div class="list-activity-founder"> by ' +  escapeHtml(unescape(recommendations[i].founder)) + ' at </div>'  
					+ '<div class="list-activity-stamp">' + new Date(recommendations[i].stamp).toString().split(/(GMT|UTC)/i)[0] + '</div>'  
					+ '</div><br />';
		}
		listEl.innerHTML = htmlFrag;
//--------------
	var activityEl = listEl.firstChild;
		var i = 0;
		while(activityEl) {
			activityEl.onmouseover = (function(el) {
				return function() { // closure
					addClass(el, 'list-activity-over');
				};
			})(activityEl);
			
			activityEl.onmouseout = (function(el) {
				return function(e) { // closure
					var e = e || window.event;
					var tEl = e.srcElement || e.target;
					var rEl = e.relatedTarget || e.toElement;
					//alert('out: [tEl] ' + tEl.nodeName + ' ' + tEl.className + ' [rEl] ' + rEl.nodeName + ' ' + rEl.className);
					do { // ignore bubbled up events
						if(rEl == this) 
							return;  
						rEl = rEl.parentNode;
					} while(rEl.nodeName != 'BODY')
					removeClass(el, 'list-activity-over');
				};
			})(activityEl);
			
			var activityId = activityEl.firstChild;
			activityEl.onclick = (function(el){
					return function(e){//closure
						
						listEl.innerHTML='<img src="img/loading.gif"/>';
						showDetail(el.innerHTML);

						}
				})(activityId);
			activityEl = activityEl.nextSibling;

		}

//--------------
				
				
			}, function(status, headers, body){//error
				alert('get recommendations list error!');
			},null);
		return false;
		}

};*/
var initRecommendationButton = function(user){
var RecommendationButton = document.getElementById('someIdeas');
var reClicked = 0;
RecommendationButton.onclick = function(){
var commentArea = document.getElementById('comment-area');
addClass(commentArea,'content-hidden');
if(reClicked==0){
reClicked=1;
ajaxRequest('GET', encodeURI('recommendation/'+ user.id), {'Accept': 'application/json;'}, null
, function(status, headers, body) {//success
var recommendations = eval('(' + body + ')');
if(recommendations.length == 0) {
listEl.innerHTML = '<div class="list-no-activity">There is no recommendation here!</div>';
return;
}
var listEl = document.getElementById('Activity-List-Area');
var htmlFrag = '';
for(var i = 0; i < recommendations.length; i++) {
htmlFrag += '<div class="list-activity">'
+ '<div class="list-activity-id">' + recommendations[i].id + '</div>' 
// escape in case title and text contain chars sensitive to HTML parsing 
+ '<div class="list-activity-title">' + escapeHtml(unescape(recommendations[i].title)) + '</div>' 
+ '<div class="list-activity-content">' + escapeHtml(unescape(recommendations[i].content)) + '</div>' 
+ '<div class="list-activity-founder"> by ' + escapeHtml(unescape(recommendations[i].founder)) + ' at </div>' 
+ '<div class="list-activity-stamp">' + new Date(recommendations[i].stamp).toString().split(/(GMT|UTC)/i)[0] + '</div>' 
+ '</div><br />';
}
listEl.innerHTML = htmlFrag;

var activityEl = listEl.firstChild;
var i = 0;
while(activityEl) {
	activityEl.onmouseover = (function(el) {
		return function() { // closure
			addClass(el, 'list-activity-over');
		};
	})(activityEl);
	
	activityEl.onmouseout = (function(el) {
		return function(e) { // closure
			var e = e || window.event;
			var tEl = e.srcElement || e.target;
			var rEl = e.relatedTarget || e.toElement;
			//alert('out: [tEl] ' + tEl.nodeName + ' ' + tEl.className + ' [rEl] ' + rEl.nodeName + ' ' + rEl.className);
			do { // ignore bubbled up events
				if(rEl == this) 
					return;  
				rEl = rEl.parentNode;
			} while(rEl.nodeName != 'BODY')
			removeClass(el, 'list-activity-over');
		};
	})(activityEl);
	
	var activityId = activityEl.firstChild;
	activityEl.onclick = (function(el){
			return function(e){//closure
				
				listEl.innerHTML='<img src="img/loading.gif"/>';
				showDetail(el.innerHTML,user);

				}
		})(activityId);
	activityEl = activityEl.nextSibling;

}



}, function(status, headers, body){//error
alert('get recommendations list error!');
},null);}
else {
getActivityList();
reClicked=0;
}

return false;
}
}


var getActivityList = function(user){
	ajaxRequest('GET', encodeURI('activity'), {'Accept': 'application/json;'}, null
			, function(status, headers, body) {//success
		
		//show activities
		var listEl = document.getElementById('Activity-List-Area');
		var activities = eval('(' + body + ')');
		if(activities.length == 0) {
			listEl.innerHTML = '<div class="list-no-activity">There is no activity here!</div>';
			return;
		}
		
		var htmlFrag = '';
		for(var i = 0; i < activities.length; i++) {
			htmlFrag += '<div class="list-activity">'
					+ '<div class="list-activity-id">' + activities[i].id + '</div>' 
					// escape in case title and text contain chars sensitive to HTML parsing 
					+ '<div class="list-activity-title">' + escapeHtml(unescape(activities[i].title)) + '</div>' 
					+ '<div class="list-activity-founder"> by ' +  escapeHtml(unescape(activities[i].founder)) + ' at </div>'  
					+ '<div class="list-activity-stamp">' + new Date(activities[i].stamp).toString().split(/(GMT|UTC)/i)[0] + '</div>'  
					+ '</div><br />';
		}
		listEl.innerHTML = htmlFrag;
		//end of show activities
			
		var activityEl = listEl.firstChild;
		var i = 0;
		while(activityEl) {
			activityEl.onmouseover = (function(el) {
				return function() { // closure
					addClass(el, 'list-activity-over');
				};
			})(activityEl);
			
			activityEl.onmouseout = (function(el) {
				return function(e) { // closure
					var e = e || window.event;
					var tEl = e.srcElement || e.target;
					var rEl = e.relatedTarget || e.toElement;
					//alert('out: [tEl] ' + tEl.nodeName + ' ' + tEl.className + ' [rEl] ' + rEl.nodeName + ' ' + rEl.className);
					do { // ignore bubbled up events
						if(rEl == this) 
							return;  
						rEl = rEl.parentNode;
					} while(rEl.nodeName != 'BODY')
					removeClass(el, 'list-activity-over');
				};
			})(activityEl);
			
			var activityId = activityEl.firstChild;
			activityEl.onclick = (function(el){
					return function(){//closure
						listEl.innerHTML='<img src="img/loading.gif"/>';
						showDetail(el.innerHTML,user);
												
						}
				})(activityId);
			activityEl = activityEl.nextSibling;

		}
			}, function(status, headers, body){//error
				alert('get  activity list error!');
			},null);
		return false;
	};
	
var initBtns = function(){
	var someIdeasBtn = document.getElementById('someIdeas');
		someIdeasBtn.onmouseover = function(){
		this.src = 'img/button/someIdeas_over.png';
	}
	someIdeasBtn.onmouseout = function(){
		this.src = 'img/button/someIdeas.png';
	}	
	
	var newActivityBtn = document.getElementById('newActivity');
	
	newActivityBtn.onmouseover = function(){
		this.src = 'img/button/newActivity_over.png';
	}
	newActivityBtn.onmouseout = function(){
		this.src = 'img/button/newActivity.png';
	}
	var oldActivityBtn = document.getElementById('oldActivity');
	
	oldActivityBtn.onmouseover = function(){
		this.src = 'img/button/oldActivity_over.png';
	}
	oldActivityBtn.onmouseout = function(){
		this.src = 'img/button/oldActivity.png';
	}	
	
	
};

