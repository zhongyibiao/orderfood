require(['config'],function(config){

	require.config(config);
	require(['jquery'], function ($){
		$('.list-li').hover(function(){
			var left = [];
			$('.list-li').each(function(index,el){
				left[index]=parseInt($(this).width());
			});
			var liWid = parseInt($(this).width()),index = $(this).index(),allLeft=0;
			if(index==0){
				left[index]=0;
			}else{
				for(var i=0;i<index;i++){
					allLeft+=left[i];
				} 
				if (liWid>100) {
					left[index] =parseInt(allLeft+($(this).width())/2);
				}else{
					left[index] = parseInt(allLeft+10);
				}
			}
			var liLeft = left[$(this).index()];
			$('.list-bar').stop().animate({'left':liLeft},100);
		},function(){
			//鼠标离开后回初始状态
			defaultStatus();
		});
		var defaultStatus =function(){
			var that = this;
			var left = [];
			$('.list-li').each(function(index,el){
				left[index]=parseInt($(this).width());
				if($(this).hasClass('cur')){
					var liWid = parseInt($(this).width()),index = $(this).index(),allLeft=0;
					if(index==0){
						left[index]=0;
					}else{
						for(var i=0;i<index;i++){
							allLeft+=left[i];
						} 
						if (liWid>67) {
							left[index] =parseInt(allLeft+($(this).width()-28)/2);
						}else{
							left[index] = parseInt(allLeft+67);
						}
					}
					var liLeft = left[$(this).index()];
					$('.list-bar').stop().animate({'left':liLeft},100,function(){$(this).show()});
				}
			});
		};
	});
});