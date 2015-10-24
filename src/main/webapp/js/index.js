// JavaScript Document

onload = function(){
	var logo = document.getElementById('logob');

		logo.onmouseout = function(){
		this.src = 'img/logo_black.png';
		var content = document.getElementById('content');
		removeClass(content, 'onmouseover');
		removeClass(body, 'onmouseover');
		}
		
		logo.onmouseover = function(){
		this.src = 'img/logo_white.png';
		var content = document.getElementById('content');
		addClass(content, 'onmouseover');
		addClass(body, 'onmouseover');		
		}	
	}