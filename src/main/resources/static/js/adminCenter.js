require(['config'],function(config){

	require.config(config);
	require(['adminCenterV'], function (adminCenterV){
		adminCenterV.init();
	});
});