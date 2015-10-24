// JavaScript Document
var showDetail = function(id, user) {
var detail = document.getElementById('detailArea');
removeClass(detail, 'content-hidden');
showComments();

initComments(id,user);
initDetails(id);
getComments(id);

};
var initComments = function(id,user) {
var commentFormEl = document.getElementById('comment-form');
commentFormEl.onsubmit = function() {
ajaxRequest('POST', encodeURI('comment/' + id), {
'Content-Type' : 'application/json;charset=UTF-8'
}, '{' + // escape in case title and text contain chars sensitive to
// JSON parsing
'"activity":"' + id + '",' + '"text":"'
+ escapeHtml(commentFormEl.elements['comment'].value) + '",'

+'"founder":"'+user.id+'"'
+ '}', function(status, headers, body) {
// success callback
getComments(id);
}, function(status, headers, body) { // error callback
Commentstatus404Handler();
}, null); // run callbacks in global scope
return false;
};
};
var initDetails = function(id) {
ajaxRequest(
'GET',
encodeURI('activity/' + id),
{
'Accept' : 'application/json;'
},
null,
function(status, headers, body) { // success
var activity = eval('(' + body + ')');
var detailArea = document.getElementById('Activity-List-Area');
var htmlFrag = '';
htmlFrag += '<div class="activity-id">'
+ activity.id
+ '</div>'
// escape in case title and text contain chars sensitive
// to HTML parsing
+ '<div class="activity-title"><span class="title">Title:<br/></span>'
+ escapeHtml(unescape(activity.title))
+ '</div><br/>'
+ '<div class="activity-content"><span class="title">Content:<br/></span>'
+ escapeHtml(unescape(activity.content))
+ '</div>'
+ '<div class="activity-founder"> by '
+ escapeHtml(unescape(activity.founder))
+ ' at </div>'
+ '<div class="activity-stamp">'
+ new Date(activity.stamp).toString().split(
/(GMT|UTC)/i)[0]
+ '</div><br/><span id ="score" class="title">Score:<br/></span>'
+ '<div id="activity-char0"> <span class"title">娛樂 </span>'
+ '<span id ="char0Value">'
+ activity.characteristic[0]
+ '</span>'
+ '<img id="plus-ch0" src="img/Plus.png" width="13" height="13" /></div>'
+ '<div id="activity-char1"> <span class"title">獵奇 </span> '
+ '<span id ="char1Value">'
+ activity.characteristic[1]
+ '</span>'
+ '<img id="plus-ch1" src="img/Plus.png" width="13" height="13" /></div>'
+ '<div id="activity-char2"><span class"title"> 殺時間 </span> '
+ '<span id ="char2Value">'
+ activity.characteristic[2]
+ '</span>'
+ '<img id="plus-ch2" src="img/Plus.png" width="13" height="13" /></div>'
+ '<div id="activity-char3"> <span class"title">經濟 </span>'
+ '<span id ="char3Value">'
+ activity.characteristic[3]
+ '</span>'
+ '<img id="plus-ch3" src="img/Plus.png" width="13" height="13" /></div>'
+ '<div id="activity-char4"> <span class"title">同樂 </span>'
+ '<span id ="char4Value">'
+ activity.characteristic[4]
+ '</span>'
+ '<img id="plus-ch4" src="img/Plus.png" width="13" height="13" /></div>';
detailArea.innerHTML = htmlFrag;
initplusBtns(activity.id);
}, function(status, headers, body) { // error
alert('Get details of activity error ' + status);
}, null);
};
var initplusBtns = function(id) {
var plus0 = document.getElementById('plus-ch0');
var plus1 = document.getElementById('plus-ch1');
var plus2 = document.getElementById('plus-ch2');
var plus3 = document.getElementById('plus-ch3');
var plus4 = document.getElementById('plus-ch4');
plus0.onmouseover = function() {
this.src = 'img/Plus_over.png';
}
plus0.onmouseout = function() {
this.src = 'img/Plus.png';
}
plus1.onmouseover = function() {
this.src = 'img/Plus_over.png';
}
plus1.onmouseout = function() {
this.src = 'img/Plus.png';
}
plus2.onmouseover = function() {
this.src = 'img/Plus_over.png';
}
plus2.onmouseout = function() {
this.src = 'img/Plus.png';
}
plus3.onmouseover = function() {
this.src = 'img/Plus_over.png';
}
plus3.onmouseout = function() {
this.src = 'img/Plus.png';
}
plus4.onmouseover = function() {
this.src = 'img/Plus_over.png';
}
plus4.onmouseout = function() {
this.src = 'img/Plus.png';
}
plus0.onclick = function() {
ajaxRequest('PUT', encodeURI('activity/' + id), {
'Content-Type' : 'application/json;charset=UTF-8'
}, '0', function(status, headers, body) { // success
var char0Value = document.getElementById('char0Value');
var value = parseInt(char0Value.innerHTML);
value = value + 1
htmlFrag = value;
char0Value.innerHTML = htmlFrag;
alert('Thanks for your scoring !');
}, function(status, headers, body) { // error
alert('Error' + status);
}, null);
}
plus1.onclick = function() {
ajaxRequest('PUT', encodeURI('activity/' + id), {
'Content-Type' : 'application/json;charset=UTF-8'
}, '1', function(status, headers, body) { // success
var char1Value = document.getElementById('char1Value');
var value = parseInt(char1Value.innerHTML);
value = value + 1
htmlFrag = value;
char1Value.innerHTML = htmlFrag;
alert('Thanks for your scoring !');
}, function(status, headers, body) { // error
alert('Error' + status);
}, null);
}
plus2.onclick = function() {
ajaxRequest('PUT', encodeURI('activity/' + id), {
'Content-Type' : 'application/json;charset=UTF-8'
}, '2', function(status, headers, body) { // success
var char2Value = document.getElementById('char2Value');
var value = parseInt(char2Value.innerHTML);
value = value + 1
htmlFrag = value;
char2Value.innerHTML = htmlFrag;
alert('Thanks for your scoring !');
}, function(status, headers, body) { // error
alert('Error' + status);
}, null);
}
plus3.onclick = function() {
ajaxRequest('PUT', encodeURI('activity/' + id), {
'Content-Type' : 'application/json;charset=UTF-8'
}, '3', function(status, headers, body) { // success
var char3Value = document.getElementById('char3Value');
var value = parseInt(char3Value.innerHTML);
value = value + 1
htmlFrag = value;
char3Value.innerHTML = htmlFrag;
alert('Thanks for your scoring !');
}, function(status, headers, body) { // error
alert('Error' + status);
}, null);
}
plus4.onclick = function() {
ajaxRequest('PUT', encodeURI('activity/' + id), {
'Content-Type' : 'application/json;charset=UTF-8'
}, '4', function(status, headers, body) { // success
var char4Value = document.getElementById('char4Value');
var value = parseInt(char4Value.innerHTML);
value = value + 1
htmlFrag = value;
char4Value.innerHTML = htmlFrag;
alert('Thanks for your scoring !');
}, function(status, headers, body) { // error
alert('Error' + status);
}, null);
}
}
var getComments = function(id) {
var listEl = document.getElementById('comment-list');
ajaxRequest(
'GET',
encodeURI('comment/' + id),
{
'Accept' : 'application/json'
},
null,
function(status, headers, body) { // success callback
var listEl = document.getElementById('comment-list');
var comments = eval('(' + body + ')');
if (comments.length == 0) {
listEl.innerHTML = '<div class="list-no-comment">No comment!</div>';
showComments();
return;
}
var htmlFrag = '';
for ( var i = 0; i < comments.length; i++) {
htmlFrag += '<li>'
+ unescape(comments[i].text)
+ '<p class= "founder">By '
+ comments[i].founder
+ '</p><p class="time">  at '
+ new Date(comments[i].stamp).toString().split(
/(GMT|UTC)/i)[0] + '</p>' + '</li>'
+ '<br />';
// alert('do_GET_htmlFrag: ' + htmlFrag);
}
listEl.innerHTML = htmlFrag;
showComments();
},
function(status, headers, body) { // error callback
var listEl = document.getElementById('comment-list');
listEl.innerHTML = '<div class="list-no-comment">No comment!</div>';
removeClass(listEl, 'content-hidden');
// alert('error: ' + status);
}, null); // run callbacks in global scope
}
var showComments = function() {
var commentEl = document.getElementById('comment-area');
var listEl = document.getElementById('comment-list');
removeClass(commentEl, 'content-hidden');
removeClass(listEl, 'content-hidden');
};



////////

var initOldActivityButton = function(user){
	var oldActivityButton = document.getElementById('oldActivity');
	var oldClicked = 0;
	oldActivityButton.onclick = function(){
		var commentArea = document.getElementById('comment-area');
		addClass(commentArea,'content-hidden');

	if(oldClicked==0){
	oldClicked = 1;
	getOldActivity(user);	
	}
	else{
	getActivityList(user);
	oldClicked = 0;
	}	
	return false;
	}
	}
	var getOldActivity = function(user) {
	var listEl = document.getElementById('Activity-List-Area');
	ajaxRequest(
	'DELETE',
	encodeURI('activity/' + user.id),
	{'Accept' : 'application/json'},
	null,
	function(status, headers, body) { // success callback
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
	}
	var showOldActivity = function() {
	var listEl = document.getElementById('Activity-List-Area');
	removeClass(listEl, 'content-hidden');
	};