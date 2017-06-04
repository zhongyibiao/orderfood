require(['config'],function(config){

	require.config(config);
	require(['qaV'], function (qaV){
		qaV.init();
	});
});