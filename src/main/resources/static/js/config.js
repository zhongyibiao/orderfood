define(
	[

	],
	function() {
		return {
			baseUrl: "js",
			paths: {
				"jquery": "lib/jquery",
				"vue":"lib/vue",
				"bootstrap.min":"lib/bootstrap/js/bootstrap.min",
				"bootstrap":"lib/bootstrap/js/bootstrap",
				"indexV":"view/indexV",
				"qaV":"view/qaV",
				"adminCenterV":"view/adminCenterV"
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
		        }
			}
		}
	})
