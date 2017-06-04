define(['jquery', 'vue','bootstrap.min'], function ($, Vue,bootstrap){
		var indexV ={
			init:function(){
				var that =this;
				var data="Hello Vue.js!";
//				var vm = new Vue({
//				    el: '#demo',
//				    data: {
//				      message: data
//				    }
//				  });
				$("#header").load("header.html");
				$("#footer").load("footer.html");		
			}
		};
		return indexV;
	});