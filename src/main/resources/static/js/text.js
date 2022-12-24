	    function createXMLHTTPObject(){
	        var XMLHttpFactories = [// 兼容不同浏览器和版本的创建函数数组
	            function () {return new XMLHttpRequest()},
	            function () {return new ActiveXObject("Msxml2.XMLHTTP")},
	            function () {return new ActiveXObject("Msxml3.XMLHTTP")},
	            function () {return new ActiveXObject("Microsoft.XMLHTTP")},
	        ];
	        var xmlhttp = false;
	        for (var i = 0; i < XMLHttpFactories.length; i ++ ){ 
	    	//尝试调用匿名函数，如果成功则返回XMLHttpRequest对象，否则继续调用下一个
	            try{
	                xmlhttp = XMLHttpFactories[i]();
	            }catch (e){
	                continue; 			// 如果发生异常，则继续下一个函数调用
	            }
	            break; 					// 如果成功，则中止循环
	        }
	        return xmlhttp; 				// 返回对象实例
	    }
