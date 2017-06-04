define(
	[

	],
	function() {
		return {
			baseUrl: "../js",
			paths: {
				"jquery": "shopmenu/lib/jquery.min",
				"vue":"lib/vue",
				"bootstrap.min":"lib/bootstrap/js/bootstrap.min",
				"bootstrap":"lib/bootstrap/js/bootstrap",
				"jcrop":"lib/jqueryplus/jquery.Jcrop.min",
				"jqueryFrom":"lib/jqueryplus/jquery.form",
				"picturePlus":"lib/jqueryplus/picturePlus",
				
				"addshopV":"shopmenu/view/addshopV"
			},
			shim: {
				"jquery": {
					"exports": "$"
				},
				"bootstrap": {
		            deps: ['jquery']
		        },
		        "bootstrap.min": {
		            deps: ['jquery']
		        },
		        "jcrop":{
		        	deps: ['jquery']
		        },
		        "picturePlus":{
		        	deps: ['jquery']
		        },
		        "jqueryFrom":{
		        	deps: ['jquery']
		        }
		        
			}
		}
	})
