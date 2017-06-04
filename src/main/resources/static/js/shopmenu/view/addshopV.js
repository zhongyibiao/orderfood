define(['jquery', 'vue','bootstrap.min','jcrop','picturePlus','jqueryFrom'], function ($, Vue,bootstrap,jcrop,picturePlus,jqueryFrom){
		var indexV ={
			init:function(){
				var that =this;
				console.info("aaaaaaaaaaaaaa");
				$("#addStandard").on("click",function(){
					$("#showStandard .stand").append("<p><input type='text'class='menuConfName'/>份<input type='text' class='menuPrice'/>元</p>");					
					$("#showStandard .opt").append("<i class='icon-thumbs-up'></i>")
				});
				this.uploadImg();
				this.bindEven();
			},
			bindEven:function(){
				var that =this;
//				$("#submitBtn").on("click",function(){
//					var img =$("$preview").attr("src");
//					
//				});
				$("#submitBtn").on("click",function(){
					that.saveData();
				});
			},
			uploadImg:function(){
				$("#uploadImg").on("change",function(){
//					$("#uploadImgForm").submit();
					var form = $("#uploadImgForm");  
					var options  = {    
							         url:'/upload',    
							         type:'post',    
							         success:function(data)    
							         {    
							        	 var imgUrl ="http://localhost:8080/myimgs/"+data;
							        	 $("[name=menuImg]").val(imgUrl);
							          }    
							       };    
							       form.ajaxSubmit(options);  
			    });
			},
			saveData:function(){
				var data ={};
				data.menuImg =$("[name=menuImg]").val();
				data.menuName =$("[name=menuName]").val();
				data.tastePoint=$("[name=tastePoint]").val();
				var menuDetail =$("#showStandard .stand").find("p");//配置规格
				var menuConfs=[];
				for(var i=0;i<menuDetail.length;i++){
					var menuConf ={};
					menuConf.menuConfName =$(menuDetail[i]).find(".menuConfName").val();
					menuConf.menuPrice =$(menuDetail[i]).find(".menuPrice").val();
					menuConfs.push(menuConf);
				}
				data.menuConfs=menuConfs;
				$.ajax({
					url:"/menuInfo",
					type:"post",
					contentType:"multipart/form-data",
					data:data,
					dataType: "json",//返回结果格式  
					success:function(data){
						debugger;
					},
					error:function(err){
						debugger;
					}
				});
			}
		};
		return indexV;
	});