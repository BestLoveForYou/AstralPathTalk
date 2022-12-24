    var stompClient = null;
    var name1 = null;
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
        $("#chat").show();
    }
    else {
        $("#conversation").hide();
        $("#chat").hide();
    }
    $("#greetings").html("");
}
function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body));
        });
    });
}
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
}
function sendName() {
    stompClient.send("/app/hello",
        {},
        JSON.stringify({'name': name1,'content':$("#content").val()}));
}
function showGreeting(message) {
    $("#greetings")
        .append("<div>" + message.name+":"+message.content + "</div>");
}

$(function () {
    $( "#send" ).click(function() { sendName(); });
});
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
    var xmlHttp = createXMLHTTPObject();

    function request(url)
    {
    	xmlHttp.open("POST",url, false);
    	xmlHttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');	
    	xmlHttp.send("callback=functionName");
    	alert("验证码已发送!请查看邮箱.");
    	window.location.href="/user/reg2.html";
    }
    	var url = "/user/getUserLogin"; 
    	xmlHttp.open("POST",url, false);
    	xmlHttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');	
    	xmlHttp.send("callback=functionName");
    	var obj = JSON.parse(xmlHttp.responseText);
    	if(obj.username == undefined) {
			window.location.href="/user/index.html";
		} else {
			connect();
			name1 = obj.username;
		}