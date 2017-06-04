require(['config'],function(config){

	require.config(config);
	require(['indexV'], function (indexV){
		indexV.init();
	});
});