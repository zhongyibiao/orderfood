define(['jquery', 'vue','bootstrap.min'], function ($, Vue,bootstrap){
		var qaV ={
			init:function(){
				$("#header").load("header.html");
				$("#footer").load("footer.html");		
			}
		};
		return qaV;
	});