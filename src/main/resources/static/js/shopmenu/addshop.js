require(['config'],function(config){

	require.config(config);
	require(['addshopV'], function (addshopV){
		addshopV.init();
	});
});